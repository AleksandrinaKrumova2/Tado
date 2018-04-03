package com.tado.android.location;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.os.EnvironmentCompat;
import android.text.TextUtils;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.tado.android.app.TadoApplication;
import com.tado.android.location.lifeline.ILocationCheckScheduler;
import com.tado.android.location.playservices.LocationApiPlayServices;
import com.tado.android.location.updates.ILocationUpdateScheduler;
import com.tado.android.mvp.model.HomeWifiRepository;
import com.tado.android.notifications.SettingsUtil;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.GeolocationConfig;
import com.tado.android.rest.model.GeolocationUpdate;
import com.tado.android.rest.model.GeolocationUpdate.Device;
import com.tado.android.rest.model.GeolocationUpdate.Device.Settings;
import com.tado.android.rest.model.GeolocationUpdate.Device.Status;
import com.tado.android.rest.model.GeolocationUpdate.Device.Status.Connection;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.DebugUtil;
import com.tado.android.utils.GeolocationLogEntry;
import com.tado.android.utils.GeolocationLogger;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.Snitcher.Builder;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import org.joda.time.DateTime;
import retrofit2.Call;
import retrofit2.Response;

public class LocationManager {
    static final String KEY_LOCATION_ID = "id";
    static final String KEY_POSTED = "posted";
    private static final boolean USE_JOB_DISPATCHER = true;
    private static final int WIFI_SCAN_VALIDITY_IN_MS = 60000;
    private final String TAG = "LocationManager";
    private LocationApi api;
    private List<LocationCluster> cellClusters = new LinkedList();
    private LocationConfiguration configuration;
    private ILocationCheckScheduler locationCheckScheduler;
    private ReentrantLock locationLock = new ReentrantLock(true);
    private ILocationUpdateScheduler locationUpdateScheduler;

    public LocationManager(@NonNull LocationConfiguration configuration, LocationApiPlayServices api, ILocationCheckScheduler locationCheckService, ILocationUpdateScheduler locationUpdateScheduler) {
        Snitcher.start().toLogger().log("TadoLocationManager", "Using location configuration:\n %s", configuration.toString());
        this.configuration = configuration;
        this.locationUpdateScheduler = locationUpdateScheduler;
        this.locationCheckScheduler = locationCheckService;
        setApi(api, configuration);
        locationCheckService.scheduleAlarm((long) configuration.getWakeupIntervalMillis());
        startTrackingIfEnabled();
    }

    public void startTrackingIfEnabled() {
        if (this.api == null) {
            throw new NullPointerException("api is null. Provide an api with setApi");
        } else if (UserConfig.isLocationBasedControlEnabled()) {
            this.api.startTracking();
        }
    }

    public void stopTracking() {
        if (this.api == null) {
            throw new NullPointerException("api is null. Provide an api with setApi");
        }
        this.api.stopTracking();
        this.locationUpdateScheduler.cancelAll();
        this.locationCheckScheduler.cancelAll();
    }

    private void setApi(@NonNull LocationApi api, LocationConfiguration configuration) {
        this.api = api;
        if (configuration == null) {
            throw new IllegalStateException("Must provide a location configuration first");
        }
        this.api.setLocationConfig(configuration);
    }

    public LocationConfiguration getLocationConfiguration() {
        return this.configuration;
    }

    public void updateLocationConfigurationAndStartTrackingIfEnabled(@NonNull LocationConfiguration configuration) {
        this.configuration = configuration;
        this.api.setLocationConfig(configuration);
        startTrackingIfEnabled();
    }

    public void postLastKnownLocation(final LocationAcquisitionMode mode, final LocationTrigger trigger) {
        if (UserConfig.isLocationBasedControlEnabled()) {
            this.api.getLastKnownLocation(new OnLastKnownLocation() {
                public void onLastKnownLocation(Location lastLocation) {
                    if (lastLocation == null) {
                        Snitcher.start().toLogger().log(3, "LocationManager", "No Location available at the moment, cannot act on sendCurrentGeolocation callback, waiting for first regular location", new Object[0]);
                        return;
                    }
                    LocationUtil.addUUID(lastLocation);
                    LocationUtil.setAcquisitionMode(lastLocation, mode);
                    LocationUtil.setTriggerMode(lastLocation, trigger);
                    LocationManager.this.postLocation(TadoApplication.getTadoAppContext(), lastLocation);
                }
            });
        }
    }

    public void getLastKnownLocation(OnLastKnownLocation onLastKnownLocation) {
        this.api.getLastKnownLocation(onLastKnownLocation);
    }

    public synchronized void postLocation(Context ctx, @NonNull Location location) {
        boolean z = true;
        synchronized (this) {
            if (shouldPost(location)) {
                try {
                    GeolocationUpdate update = prepareLocationForUpdate(location, ctx);
                    Snitcher.start().toLogger().log(3, "LocationManager", "scheduling job for Location with mode = %s triggered by %s using %s", LocationUtil.getLocationAcquisitionMode(location), LocationUtil.getLocationTrigger(location), "JOBDISPATCHER");
                    ILocationUpdateScheduler iLocationUpdateScheduler = this.locationUpdateScheduler;
                    if (getLastScheduledLocation() == null) {
                        z = false;
                    }
                    if (iLocationUpdateScheduler.scheduleLocationUpdate(update, z)) {
                        setLastScheduledLocation(location);
                    } else {
                        Snitcher.start().toLogger().log(6, "LocationManager", "Can't schedule job.", new Object[0]);
                    }
                } catch (Exception exception) {
                    Snitcher.start().toLogger().toCrashlytics().logException("LocationManager", "Error starting LocationPostService", exception);
                }
            } else {
                Snitcher.start().toLogger().log(6, "LocationManager", "postLocation: Won't send location, does not qualify. Mode is %s", LocationUtil.getLocationAcquisitionMode(location));
                LocationUtil.updateLocationsListAndShowNotification(location, PostState.FILTERED, ctx, LocationUtil.getLocationTrigger(location));
                DebugUtil.logDroppedLocation(location);
            }
        }
    }

    private boolean shouldSkipFilters(LocationTrigger trigger) {
        switch (trigger) {
            case APP_TRIGGERED:
            case MANUAL:
            case ALARM:
                return true;
            default:
                return false;
        }
    }

    private boolean shouldPost(Location location) {
        if (location == null || (location.getLatitude() == 0.0d && location.getLongitude() == 0.0d)) {
            Snitcher.start().toLogger().log(6, "LocationManager", "Should NOT post a null location or in 0,0 ;)", new Object[0]);
            if (location != null) {
                LocationUtil.addDroppedReason(location, "Location is at 0, 0");
            }
            return false;
        }
        LocationTrigger trigger = LocationUtil.getLocationTrigger(location);
        boolean isAppTriggered = LocationTrigger.APP_TRIGGERED == trigger;
        boolean isGeofenceTriggered = LocationTrigger.GEOFENCE == trigger;
        String pw = (String) Util.either(UserConfig.getPassword(), "");
        if (((String) Util.either(UserConfig.getUsername(), "")).isEmpty() || pw.isEmpty()) {
            Snitcher.start().toLogger().log(6, "LocationManager", "##### SHOULD NOT POST because username and password are empty #####", new Object[0]);
            stopTracking();
            LocationUtil.addDroppedReason(location, "Username or password is empty");
            return false;
        }
        Snitcher.start().toLogger().log(3, "LocationManager", "shouldPost: location: %f,%f mode: %s", Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), trigger.name());
        if (LocationUtil.isLocationInTheFuture(location)) {
            LocationUtil.resetLocationTime(location, System.currentTimeMillis());
        }
        Location lastPostedLocation = getLastPostedLocation();
        Location lastSeenLocation = getLastSeenLocation();
        setLastSeenLocation(location);
        if (LocationUtil.sameLocation(lastPostedLocation, lastSeenLocation)) {
            Snitcher.start().toLogger().log(3, "LocationManager", "Last posted and last seen location are equal. Continuing", new Object[0]);
        }
        if (LocationUtil.isSignificantlyOld(location)) {
            Snitcher.start().toLogger().log(3, "LocationManager", "Location is older than 2 minutes (%d mins.). Continuing", Long.valueOf(LocationUtil.getLocationAgeMs(location) / 60000));
        }
        if (LocationUtil.sameLocation(getLastScheduledLocation(), location)) {
            Snitcher.start().toLogger().log(3, "LocationManager", "Location is same as scheduled", new Object[0]);
        }
        boolean needsWakeUp = needsWakeup(location, lastPostedLocation);
        boolean meetsCriteria = meetsDistanceAndTimeCriteria(location, lastPostedLocation, isAppTriggered, isGeofenceTriggered);
        boolean incrediblyFast = isIncrediblyFast(lastSeenLocation, location);
        boolean bootFilter = checkBootFiler(location);
        location = checkInHomeRangeAndLearnWifi(location);
        boolean validAccuracy = isValidAccuracy(location, isAppTriggered);
        boolean validLocation = isValidLocation(incrediblyFast, bootFilter, validAccuracy);
        if (!((meetsCriteria && validLocation) || needsWakeUp || shouldSkipFilters(trigger))) {
            GeolocationLogEntry geolocationLogEntry = GeolocationLogger.getGeoLocationEntry(location);
            geolocationLogEntry.setMode(trigger.name());
            StringBuilder reason = new StringBuilder();
            if (meetsCriteria) {
                reason.append("not valid location because ");
                if (incrediblyFast) {
                    reason.append(" is incredibly fast");
                    if (lastSeenLocation != null) {
                        Snitcher.start().toLogger().log(3, "LocationManager", "Location will be dropped because the speed is incredibly fast.", new Object[0]);
                    } else {
                        Snitcher.start().toLogger().log(3, "LocationManager", "Location will be dropped because the speed is incredibly fast. Last seen location was null", new Object[0]);
                    }
                    LocationUtil.addDroppedReason(location, String.format(Locale.US, "too fast (%f)", new Object[]{Float.valueOf(location.getSpeed())}));
                }
                if (bootFilter) {
                    reason.append(" the device has been recently booted");
                    LocationUtil.addDroppedReason(location, "the device has been recently booted");
                }
                if (!validAccuracy) {
                    reason.append(" is not validAccuracy ").append(location.getAccuracy());
                    LocationUtil.addDroppedReason(location, String.format(Locale.US, "not valid accuracy %f", new Object[]{Float.valueOf(location.getAccuracy())}));
                }
            } else {
                try {
                    String reasonString = String.format(Locale.US, "does not meet criteria (distance %.3f > %d minDistance and time %d s. > %d s. minTime) ", new Object[]{Float.valueOf(location.distanceTo(lastPostedLocation)), Integer.valueOf(this.configuration.getFusedDistanceFilter()), Long.valueOf((location.getTime() - lastPostedLocation.getTime()) / 1000), Integer.valueOf(this.configuration.getFusedTimeFilterMillis() / 1000)});
                    reason.append(reasonString);
                    LocationUtil.addDroppedReason(location, reasonString);
                } catch (Exception e) {
                    Snitcher.start().toLogger().toCrashlytics().logException(e);
                }
            }
            geolocationLogEntry.setReason(reason.toString());
        }
        if ((meetsCriteria && validLocation) || needsWakeUp || shouldSkipFilters(trigger)) {
            return true;
        }
        return false;
    }

    private boolean isIncrediblyFast(@Nullable Location lastSeenLocation, @NonNull Location location) {
        float speed = location.getSpeed();
        long speedTimeDeltaSeconds = 0;
        if (lastSeenLocation != null) {
            speedTimeDeltaSeconds = (location.getTime() - lastSeenLocation.getTime()) / 1000;
            speed = location.distanceTo(lastSeenLocation) / ((float) speedTimeDeltaSeconds);
            if (Float.isNaN(speed) || Float.isInfinite(speed)) {
                speed = location.getSpeed();
            } else {
                Snitcher.start().toLogger().log(3, "LocationManager", "Speed is %f Km/h in the time of %d min since last seen location.", Float.valueOf(3.6f * speed), Long.valueOf(speedTimeDeltaSeconds / 60));
            }
        }
        location.setSpeed(speed);
        Snitcher.start().toLogger().log(3, "LocationManager", "checking if speed: %f > %f and speedTimeDelta %d < %d", Float.valueOf(speed), Float.valueOf(this.configuration.getFusedSpeedFilter()), Long.valueOf(speedTimeDeltaSeconds), Integer.valueOf(this.configuration.getFusedSpeedDeltaFilter()));
        if (speed <= this.configuration.getFusedSpeedFilter() || speedTimeDeltaSeconds >= ((long) this.configuration.getFusedSpeedDeltaFilter())) {
            return false;
        }
        return true;
    }

    private boolean isValidAccuracy(@NonNull Location location, boolean isAppTriggered) {
        if (isAppTriggered) {
            if (location.getAccuracy() < ((float) this.configuration.getInstantAccuracyFilter())) {
                return true;
            }
            return false;
        } else if (location.getAccuracy() >= ((float) this.configuration.getFusedAccuracyFilter())) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isInHomeFence(@NonNull Location location) {
        Location home = UserConfig.getHome();
        return home != null && home.distanceTo(location) + location.getAccuracy() < UserConfig.getHomeFence();
    }

    private boolean isNewer(Location location, Location lastPostedLocation) {
        if (!LocationUtil.isOlder(location, lastPostedLocation)) {
            return true;
        }
        Snitcher.start().toLogger().log(3, "LocationManager", "Location time (%s) is older or same than last posted location (%s)", SimpleDateFormat.getDateTimeInstance(1, 1, Locale.ENGLISH).format(new Date(location.getTime())), SimpleDateFormat.getDateTimeInstance(1, 1, Locale.ENGLISH).format(new Date(lastPostedLocation.getTime())));
        LocationUtil.addDroppedReason(location, "Older or same than last posted");
        return false;
    }

    private Location checkInHomeRangeAndLearnWifi(@NonNull Location location) {
        Location home = UserConfig.getHome();
        float homeFence = UserConfig.getHomeFence();
        float dist = home != null ? home.distanceTo(location) : 0.0f;
        WifiInfo wifiInfo = getWifiInfo();
        if (isInHomeFence(location)) {
            if (isConnectedToAnyWifi(wifiInfo) && isLocationFreshEnoughForLearningWifi(location)) {
                learnHomeWifi(wifiInfo);
            }
        } else if (isConnectedToHomeWifi(wifiInfo) || isInRangeOfHomeWifi(getLatestWifiScanResults())) {
            Builder toLogger = Snitcher.start().toLogger();
            String str = "LocationManager";
            String str2 = "User is connected to home wifi (%s) (%s) outside of the fence (%f) and we received a location with distance %f (acc %f m.) from home";
            Object[] objArr = new Object[5];
            objArr[0] = wifiInfo != null ? wifiInfo.getSSID() : "null";
            objArr[1] = wifiInfo != null ? wifiInfo.getBSSID() : "null";
            objArr[2] = Float.valueOf(homeFence);
            objArr[3] = Float.valueOf(dist);
            objArr[4] = Float.valueOf(location.getAccuracy());
            toLogger.log(3, str, str2, objArr);
            boolean isHomeExtendedRange = homeFence > 0.0f && (dist - location.getAccuracy()) - homeFence <= this.configuration.getHomeWifiThreshold();
            if (isHomeExtendedRange && home != null) {
                Snitcher.start().toLogger().log(3, "LocationManager", "User is in extended range connected to home Wifi, sending home location (%f, %f) instead of real (%f, %f)", Double.valueOf(home.getLatitude()), Double.valueOf(home.getLongitude()), Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()));
                if (location.getExtras() == null) {
                    location.setExtras(new Bundle());
                }
                location.getExtras().putBoolean(LocationPostService.EXTRA_LOCATION_OVERRIDE, true);
                location.getExtras().putDouble(LocationPostService.EXTRA_REAL_LATITUDE, location.getLatitude());
                location.getExtras().putDouble(LocationPostService.EXTRA_REAL_LONGITUDE, location.getLongitude());
                location.setLatitude(home.getLatitude());
                location.setLongitude(home.getLongitude());
            }
        }
        return location;
    }

    private WifiInfo getWifiInfo() {
        WifiManager wifi = (WifiManager) TadoApplication.getTadoAppContext().getApplicationContext().getSystemService("wifi");
        return wifi != null ? wifi.getConnectionInfo() : null;
    }

    private List<ScanResult> getLatestWifiScanResults() {
        WifiManager wifi = (WifiManager) TadoApplication.getTadoAppContext().getApplicationContext().getSystemService("wifi");
        List<ScanResult> scanResults = null;
        if (wifi != null) {
            scanResults = wifi.getScanResults();
        }
        return scanResults != null ? scanResults : new LinkedList();
    }

    private boolean isLocationFreshEnoughForLearningWifi(Location location) {
        return LocationUtil.getLocationAgeMs(location) < 60000;
    }

    private boolean isConnectedToAnyWifi(WifiInfo wifiInfo) {
        boolean z = true;
        ConnectivityManager connManager = (ConnectivityManager) TadoApplication.getTadoAppContext().getApplicationContext().getSystemService("connectivity");
        if (connManager == null) {
            return false;
        }
        NetworkInfo mWifi = connManager.getNetworkInfo(1);
        if (wifiInfo == null || wifiInfo.getNetworkId() == -1 || mWifi == null || !mWifi.isConnected()) {
            z = false;
        }
        return z;
    }

    private boolean isConnectedToHomeWifi(WifiInfo wifiInfo) {
        return isConnectedToAnyWifi(wifiInfo) && wifiInfo.getBSSID() != null && LocationUtil.isValidBSSID(wifiInfo.getBSSID()) && HomeWifiRepository.INSTANCE.getHomeWifis().containsKey(wifiInfo.getBSSID());
    }

    private boolean isInRangeOfHomeWifi(@NonNull List<ScanResult> wifiList) {
        if (VERSION.SDK_INT < 17) {
            return false;
        }
        Map<String, String> homeBSSIDList = HomeWifiRepository.INSTANCE.getHomeWifis();
        if (homeBSSIDList.isEmpty()) {
            return false;
        }
        for (ScanResult scanResult : wifiList) {
            long scanFreshnessInMillis = ((SystemClock.elapsedRealtimeNanos() / 1000) - scanResult.timestamp) / 1000;
            if (LocationUtil.isValidBSSID(scanResult.BSSID) && homeBSSIDList.containsKey(scanResult.BSSID) && scanFreshnessInMillis < 60000) {
                Snitcher.start().toLogger().log(3, "LocationManager", "WiFi %s was in range (freshness: %d) ", scanResult.BSSID, Long.valueOf(scanFreshnessInMillis));
                return true;
            }
        }
        return false;
    }

    private void learnHomeWifi(@NonNull WifiInfo wifiInfo) {
        String bssid = wifiInfo.getBSSID();
        String ssid = wifiInfo.getSSID();
        if (bssid == null || HomeWifiRepository.INSTANCE.getHomeWifis().containsKey(bssid) || HomeWifiRepository.INSTANCE.getIgnoredWifis().containsKey(bssid)) {
            Snitcher.start().toLogger().log(3, "LocationManager", "wifi %s (%s) already learned", ssid, bssid);
            return;
        }
        HomeWifiRepository.INSTANCE.addHomeWifi(bssid, ssid);
        Snitcher.start().toLogger().log(3, "LocationManager", "Learned home wifi %s (%s)", ssid, bssid);
    }

    private boolean checkBootFiler(Location location) {
        boolean bootFilter = false;
        if (SystemClock.elapsedRealtime() < 120000 && UserConfig.isBootFilter()) {
            Snitcher.start().toLogger().log(3, "LocationManager", "Device was booted less " + SystemClock.elapsedRealtime() + " ago, will only accept non-celltower locations", new Object[0]);
            if (LocationUtil.isCellTower(location)) {
                bootFilter = true;
            } else {
                UserConfig.setBootFilter(false);
            }
            Snitcher.start().toLogger().log(3, "LocationManager", "Bootfilter " + (bootFilter ? "active" : "deactivated"), new Object[0]);
        }
        return bootFilter;
    }

    private synchronized boolean checkCellTowerCluster(Location location) {
        boolean inCellCluster;
        inCellCluster = false;
        if (this.cellClusters.size() > 0) {
            Snitcher.start().toLogger().log(3, "LocationManager", "Updating " + this.cellClusters.size() + " cell clusters...", new Object[0]);
            for (LocationCluster cluster : this.cellClusters) {
                Snitcher.start().toLogger().log(3, "LocationManager", cluster.toString(), new Object[0]);
            }
            Iterator<LocationCluster> iterator = this.cellClusters.iterator();
            while (iterator.hasNext()) {
                if (!((LocationCluster) iterator.next()).isValid()) {
                    Snitcher.start().toLogger().log(3, "LocationManager", "Removing invalid cell cluster", new Object[0]);
                    iterator.remove();
                }
            }
            if (LocationUtil.isCellTower(location)) {
                Snitcher.start().toLogger().log(3, "LocationManager", "Checking if location is in any of the " + this.cellClusters.size() + " valid cell clusters", new Object[0]);
                for (LocationCluster cluster2 : this.cellClusters) {
                    if (cluster2.contains(location)) {
                        cluster2.addLocation(location);
                        Snitcher.start().toLogger().log(3, "LocationManager", " -----------> CLUSTERFILTER: Adding location to cluster and filtering it", new Object[0]);
                        inCellCluster = true;
                        break;
                    }
                }
            }
        } else {
            Snitcher.start().toLogger().log(3, "LocationManager", "No Cell Tower clusters to check.", new Object[0]);
        }
        if (LocationUtil.isCellTower(location) && !inCellCluster) {
            Snitcher.start().toLogger().log(3, "LocationManager", "Adding new location cluster", new Object[0]);
            this.cellClusters.add(new LocationCluster(location));
        }
        return inCellCluster;
    }

    @Nullable
    private synchronized Location getLastSeenLocation() {
        Location lastSeenLocation;
        this.locationLock.lock();
        try {
            lastSeenLocation = UserConfig.getLastSeenLocation();
            if (lastSeenLocation == null || lastSeenLocation.getTime() <= System.currentTimeMillis()) {
                this.locationLock.unlock();
            } else {
                lastSeenLocation = null;
            }
        } finally {
            this.locationLock.unlock();
        }
        return lastSeenLocation;
    }

    private synchronized void setLastSeenLocation(Location location) {
        this.locationLock.lock();
        try {
            UserConfig.setLastSeenLocation(location);
            this.locationLock.unlock();
        } catch (Throwable th) {
            this.locationLock.unlock();
        }
    }

    @Nullable
    synchronized Location getLastPostedLocation() {
        Location lastPostedLocation;
        this.locationLock.lock();
        try {
            lastPostedLocation = UserConfig.getLastPostedLocation();
            if (lastPostedLocation == null || lastPostedLocation.getTime() <= System.currentTimeMillis()) {
                this.locationLock.unlock();
            } else {
                lastPostedLocation = null;
            }
        } finally {
            this.locationLock.unlock();
        }
        return lastPostedLocation;
    }

    synchronized void setLastPostedLocation(Location location) {
        this.locationLock.lock();
        try {
            UserConfig.setLastPostedLocation(location);
            this.locationLock.unlock();
        } catch (Throwable th) {
            this.locationLock.unlock();
        }
    }

    @Nullable
    private synchronized Location getLastScheduledLocation() {
        Location lastScheduledLocation;
        this.locationLock.lock();
        try {
            lastScheduledLocation = UserConfig.getLastScheduledLocation();
            if (lastScheduledLocation == null || lastScheduledLocation.getTime() <= System.currentTimeMillis()) {
                this.locationLock.unlock();
            } else {
                lastScheduledLocation = null;
            }
        } finally {
            this.locationLock.unlock();
        }
        return lastScheduledLocation;
    }

    private synchronized void setLastScheduledLocation(Location location) {
        this.locationLock.lock();
        try {
            UserConfig.setLastScheduledLocation(location);
            this.locationLock.unlock();
        } catch (Throwable th) {
            this.locationLock.unlock();
        }
    }

    private boolean meetsDistanceAndTimeCriteria(@NonNull Location location, @Nullable Location lastPostedLocation, boolean isAppTriggered, boolean isGeofenceTriggered) {
        if (lastPostedLocation == null || isGeofenceTriggered) {
            return true;
        }
        long minTime = isAppTriggered ? (long) this.configuration.getInstantTimeDeltaFilterMillis() : (long) this.configuration.getFusedTimeFilterMillis();
        float minDist = (float) this.configuration.getFusedDistanceFilter();
        long timeDelta = location.getTime() - lastPostedLocation.getTime();
        Snitcher.start().toLogger().log(3, "LocationManager", "DIST: %f meters  TIME: %d minutes since last successfully posted location.", Float.valueOf(location.distanceTo(lastPostedLocation)), Long.valueOf(timeDelta / 60000));
        if (!isAppTriggered) {
            return distance > minDist && timeDelta > minTime;
        } else {
            if (timeDelta > minTime) {
                return true;
            }
            return false;
        }
    }

    private boolean needsWakeup(@NonNull Location location, @Nullable Location lastPostedLocation) {
        if (lastPostedLocation == null) {
            return true;
        }
        boolean needsWakeUp;
        float wakeup = (float) this.configuration.getWakeupIntervalMillis();
        long timeDelta = System.currentTimeMillis() - lastPostedLocation.getTime();
        if (((float) timeDelta) > wakeup) {
            needsWakeUp = true;
        } else {
            needsWakeUp = false;
        }
        Builder toLogger = Snitcher.start().toLogger();
        String str = "LocationManager";
        String str2 = "%s (%d min. greater than %d min.)";
        Object[] objArr = new Object[3];
        objArr[0] = needsWakeUp ? "posting because needs wakeup" : "does NOT need wakeup";
        objArr[1] = Integer.valueOf(((int) timeDelta) / 60000);
        objArr[2] = Integer.valueOf(((int) wakeup) / 60000);
        toLogger.log(3, str, str2, objArr);
        return needsWakeUp;
    }

    private boolean isValidLocation(boolean incrediblyFast, boolean bootFilter, boolean validAccuracy) {
        boolean validLocation;
        if (incrediblyFast || bootFilter || !validAccuracy) {
            validLocation = false;
        } else {
            validLocation = true;
        }
        Snitcher.start().toLogger().log(3, "LocationManager", "Valid location %b because: %s", Boolean.valueOf(validLocation), getReasonForLocationValidity(incrediblyFast, bootFilter, validAccuracy));
        return validLocation;
    }

    private String getReasonForLocationValidity(boolean incrediblyFast, boolean bootFilter, boolean validAccuracy) {
        StringBuilder sb = new StringBuilder();
        if (incrediblyFast) {
            sb.append("is incredibly fast");
        }
        if (bootFilter) {
            if (sb.length() > 0) {
                sb.append(" and ");
            }
            sb.append("is boot filter");
        }
        if (!validAccuracy) {
            if (sb.length() > 0) {
                sb.append(" and ");
            }
            sb.append("accuracy is not valid");
        }
        if (sb.length() == 0) {
            sb.append("all conditions met");
        }
        return sb.toString();
    }

    private float getDistanceFromGeofence(int i) {
        if (this.configuration == null || this.configuration.getRegions().isEmpty() || i >= this.configuration.getRegions().size()) {
            return 0.0f;
        }
        return ((Float) this.configuration.getRegions().get(i)).floatValue();
    }

    public float getDistanceFromGeofence(String id) {
        return getDistanceFromGeofence(Integer.valueOf(id).intValue());
    }

    public int checkStatus() {
        return this.api.checkStatus();
    }

    private GeolocationUpdate prepareLocationForUpdate(Location location, Context context) {
        String str;
        GeolocationUpdate body = new GeolocationUpdate(location);
        LocationAcquisitionMode acquisitionMode = LocationUtil.getLocationAcquisitionMode(location);
        String mode = acquisitionMode.name();
        if (LocationAcquisitionMode.MOCK == acquisitionMode) {
            Snitcher.start().toLogger().log(6, "LocationManager", ">>> Location is MOCK...", new Object[0]);
        } else if (LocationAcquisitionMode.UNKNOWN == acquisitionMode && location.getProvider() != null) {
            mode = location.getProvider();
            Snitcher.start().toLogger().log(6, "LocationManager", "Acquisition mode is null... Using provider name %s", location.getProvider());
        }
        Snitcher.start().toLogger().log(3, "LocationManager", "Received intent to post location... mode is %s", mode);
        body.setAcquisitionMode(mode);
        LocationTrigger trigger = LocationUtil.getLocationTrigger(location);
        if (LocationTrigger.UNKNOWN == trigger) {
            Snitcher.start().toLogger().log(6, "LocationManager", "Location trigger is unknown...", new Object[0]);
        }
        body.setTriggerMode(trigger.name());
        body.setDevice(new Device(getStatus(context), getSettings(context)));
        body.setExtraInfo(String.format("location timestamp: %s", new Object[]{new DateTime(location.getTime()).toString()}));
        Builder toLogger = Snitcher.start().toLogger();
        String str2 = "LocationManager";
        String str3 = "Trying to post location %s (%s) %f %f (%f / %s / %s)";
        Object[] objArr = new Object[7];
        objArr[0] = mode;
        objArr[1] = trigger;
        objArr[2] = Double.valueOf(location.getLatitude());
        objArr[3] = Double.valueOf(location.getLongitude());
        objArr[4] = Float.valueOf(location.getAccuracy());
        objArr[5] = location.getProvider();
        if (UserConfig.isInHomeWifi()) {
            str = "in home wifi";
        } else {
            str = "NOT home wifi";
        }
        objArr[6] = str;
        toLogger.log(3, str2, str3, objArr);
        return body;
    }

    @NonNull
    private Status getStatus(Context context) {
        Intent batteryStatus = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        float battery = -1.0f;
        if (batteryStatus != null && batteryStatus.hasExtra(Param.LEVEL) && batteryStatus.hasExtra("scale")) {
            battery = ((float) batteryStatus.getIntExtra(Param.LEVEL, -1)) / ((float) batteryStatus.getIntExtra("scale", -1));
        }
        DebugUtil.setBatteryLevel((int) (100.0f * battery));
        return new Status(Float.valueOf(battery), getConnectionList(context), HomeWifiRepository.INSTANCE.getHomeWifis(), HomeWifiRepository.INSTANCE.getIgnoredWifis());
    }

    private List<Connection> getConnectionList(Context context) {
        int i = 0;
        ArrayList<Connection> connections = new ArrayList();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        int length;
        if (VERSION.SDK_INT >= 21) {
            Network[] networks = connectivityManager.getAllNetworks();
            length = networks.length;
            while (i < length) {
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(networks[i]);
                if (networkInfo != null && networkInfo.getType() == 1) {
                    addWifiToCollection(((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo(), connections);
                } else if (networkInfo != null) {
                    addNetworkToCollection(networkInfo, connections);
                }
                i++;
            }
        } else {
            NetworkInfo[] networks2 = connectivityManager.getAllNetworkInfo();
            length = networks2.length;
            while (i < length) {
                addNetworkToCollection(networks2[i], connections);
                i++;
            }
        }
        return connections;
    }

    private void addWifiToCollection(WifiInfo wifiInfo, List<Connection> connections) {
        connections.add(new Connection("WIFI", wifiInfo.getSSID(), wifiInfo.getBSSID()));
    }

    private void addNetworkToCollection(NetworkInfo networkInfo, List<Connection> connections) {
        connections.add(new Connection(networkInfo.getTypeName(), null, null));
    }

    private Settings getSettings(Context context) {
        boolean z = true;
        Settings settings = new Settings();
        try {
            if (VERSION.SDK_INT >= 21) {
                Snitcher.start().toLogger().log(3, "LocationManager", "LocationPostService Device is in power saving mode... %b", Boolean.valueOf(Util.isInPowerSavingMode(context)));
                settings.setPowerSaving(Util.isInPowerSavingMode(context));
            }
            if (VERSION.SDK_INT >= 23) {
                Snitcher.start().toLogger().log(3, "LocationManager", "LocationPostService Device idle mode... %b", Boolean.valueOf(Util.isDeviceIdle(context)));
                Builder toLogger = Snitcher.start().toLogger();
                String str = "LocationManager";
                String str2 = "LocationPostService Device ignoring battery optimizations ... %b";
                Object[] objArr = new Object[1];
                if (Util.hasBatteryOptimization(context)) {
                    z = false;
                }
                objArr[0] = Boolean.valueOf(z);
                toLogger.log(3, str, str2, objArr);
                settings.setIdleMode(Util.isDeviceIdle(context));
                settings.setBatteryOptimization(Util.hasBatteryOptimization(context));
            }
            settings.setWifi(SettingsUtil.isWifiOn(context));
            settings.setMobileData(SettingsUtil.isMobileDataOn(context));
            settings.setLocationProviders(TextUtils.join(",", ((android.location.LocationManager) context.getSystemService("location")).getProviders(true)));
            settings.setLocationServices(UserConfig.getLocationServicesMode());
        } catch (Exception e) {
            Snitcher.start().toCrashlytics().toLogger().logException(e);
        }
        return settings;
    }

    private synchronized void requestNewConfiguration(@NonNull final String configVersion) {
        RestServiceGenerator.getTadoLocationRestService().getLocationProviderConfig(UserConfig.getHomeId(), UserConfig.getMobileDeviceId()).enqueue(new TadoCallback<GeolocationConfig>() {
            public void onResponse(Call<GeolocationConfig> call, Response<GeolocationConfig> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    GeolocationConfig config = (GeolocationConfig) response.body();
                    if (config != null) {
                        config.setVersion(configVersion);
                        Snitcher.start().toLogger().log(4, "LocationManager", "New configuration %s loaded", config.getVersion());
                        UserConfig.saveLocationConfiguration(config);
                        LocationManager.this.updateLocationConfigurationAndStartTrackingIfEnabled(new LocationConfiguration(config));
                    }
                }
            }

            public void onFailure(Call<GeolocationConfig> call, Throwable t) {
                super.onFailure(call, t);
            }
        });
    }

    public void reset() {
        if (this.api == null) {
            throw new NullPointerException("api is null. Provide an api with setApi");
        }
        this.api.reset();
    }

    public void onPostSuccessful(String geoConfigVersion, GeolocationUpdate update) {
        Snitcher.start().toLogger().log(3, "LocationManager", "postLocation response successful", new Object[0]);
        if (!(geoConfigVersion == null || getLocationConfiguration().getConfigName() == null || getLocationConfiguration().getConfigName().equalsIgnoreCase(geoConfigVersion))) {
            requestNewConfiguration(geoConfigVersion);
        }
        setLastPostedLocation(update.getLocation());
        Location location = update.getLocation();
        setLastScheduledLocation(null);
        LocationUtil.updateLocationsListAndShowNotification(update.getLocation(), PostState.POSTED, TadoApplication.getTadoAppContext(), null);
        TadoApplication.getBus().post(update);
        Builder toLogger = Snitcher.start().toLogger();
        String str = "LocationManager";
        String str2 = "   HTTP post SUCCESS && json[\"success\"]= true :) (%f,%f)";
        Object[] objArr = new Object[2];
        objArr[0] = location != null ? Double.valueOf(location.getLatitude()) : EnvironmentCompat.MEDIA_UNKNOWN;
        objArr[1] = location != null ? Double.valueOf(location.getLongitude()) : EnvironmentCompat.MEDIA_UNKNOWN;
        toLogger.log(4, str, str2, objArr);
        DebugUtil.getDroppedLocations().clear();
        this.locationCheckScheduler.scheduleAlarm((long) this.configuration.getWakeupIntervalMillis());
    }

    void onPostFailed(int statusCode, Context context) {
        if (statusCode == 401 || statusCode == 403) {
            UserConfig.clearUserData();
        }
        Snitcher.start().toLogger().log(6, "LocationManager", "   response status " + statusCode, new Object[0]);
    }
}
