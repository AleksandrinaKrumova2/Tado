package com.tado.android.location;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.maps.model.LatLng;
import com.nhaarman.supertooltips.ToolTipRelativeLayout;
import com.tado.android.app.TadoApplication;
import com.tado.android.notifications.NotificationUtil;
import com.tado.android.utils.Snitcher;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class LocationUtil {
    private static final String TAG = "LocationUtil";
    public static LinkedList<Location> locations = new LinkedList();

    public static String formatLocation(Location location) {
        String timeString = SimpleDateFormat.getTimeInstance(3, Locale.UK).format(new Date(location.getTime()));
        PostState posted = (PostState) location.getExtras().getSerializable("posted");
        if (posted == null) {
            posted = PostState.UNKNOWN;
        }
        return String.format(Locale.US, "%s:%s %f,%f (%.0f) %s", new Object[]{posted.toString(), timeString, Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), Float.valueOf(location.getAccuracy()), getLocationID(location)});
    }

    public static void addUUID(Location location) {
        if (location != null) {
            ensureExtras(location);
            Bundle extras = location.getExtras();
            if (!extras.containsKey(ToolTipRelativeLayout.ID)) {
                extras.putString(ToolTipRelativeLayout.ID, UUID.randomUUID().toString());
            }
            location.setExtras(extras);
        }
    }

    private static String getLocationID(Location location) {
        ensureExtras(location);
        if (location.getExtras().containsKey(ToolTipRelativeLayout.ID)) {
            return location.getExtras().getString(ToolTipRelativeLayout.ID);
        }
        return null;
    }

    static void resetLocationTime(Location location, long time) {
        location.setTime(time);
    }

    static boolean isLocationInTheFuture(Location location) {
        return location.getTime() - System.currentTimeMillis() > 120000;
    }

    public static boolean sameLocation(Location loc1, Location loc2) {
        return loc1 != null && loc2 != null && loc1.getLatitude() == loc2.getLatitude() && loc1.getLongitude() == loc2.getLongitude() && loc1.getAccuracy() == loc2.getAccuracy();
    }

    static boolean isSignificantlyOld(Location location) {
        return getLocationAgeMs(location) > 120000;
    }

    static boolean isCellTower(Location location) {
        return location.getAccuracy() > 500.0f;
    }

    @SuppressLint({"NewApi"})
    static long getLocationAgeMs(Location loc) {
        if (loc == null) {
            return 0;
        }
        if (VERSION.SDK_INT < 17) {
            return System.currentTimeMillis() - loc.getTime();
        }
        return (SystemClock.elapsedRealtimeNanos() - loc.getElapsedRealtimeNanos()) / 1000000;
    }

    static boolean isOlder(@Nullable Location location, @Nullable Location lastPostedLocation) {
        return location != null && lastPostedLocation != null && lastPostedLocation.getTime() <= System.currentTimeMillis() && location.getTime() <= lastPostedLocation.getTime();
    }

    public static synchronized void updateLocationsListAndShowNotification(final Location location, PostState state, Context context, LocationTrigger trigger) {
        synchronized (LocationUtil.class) {
            if (location != null) {
                try {
                    ensureExtras(location);
                    location.getExtras().putString(ToolTipRelativeLayout.ID, UUID.randomUUID().toString());
                    location.getExtras().putSerializable("posted", state);
                    if (location.getExtras().containsKey(LocationPostService.EXTRA_LOCATION_OVERRIDE)) {
                        location.setLatitude(location.getExtras().getDouble(LocationPostService.EXTRA_REAL_LATITUDE));
                        location.setLongitude(location.getExtras().getDouble(LocationPostService.EXTRA_REAL_LONGITUDE));
                    }
                    Snitcher.start().log(4, "LocationUpdate", "%s %f %f %d %s", getLocationID(location), Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), Long.valueOf(location.getTime()), location.getExtras().getSerializable("posted").toString());
                    if (!locations.contains(location)) {
                        locations.addFirst(location);
                    }
                    while (locations.size() > 10) {
                        locations.removeLast();
                    }
                    NotificationUtil.notifyLocationHistory(context, locations);
                    if (TadoApplication.isInternal()) {
                        LocationDbHelper db = new LocationDbHelper(context);
                        if (state == PostState.UNKNOWN) {
                            db.addLocation(location, trigger);
                        } else {
                            db.updateLocation(getLocationID(location), state);
                        }
                        db.closeDb();
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                TadoApplication.getBus().post(location);
                            }
                        });
                    }
                } catch (Exception e) {
                    Snitcher.start().toLogger().log(6, TAG, "locations for notification was empty", new Object[0]);
                    Snitcher.start().toLogger().logException(e);
                }
            }
        }
    }

    public static void addDroppedReason(@NonNull Location location, String reason) {
        ensureExtras(location);
        location.getExtras().putString(LocationPostService.EXTRA_DROPPED_REASON, location.getExtras().getString(LocationPostService.EXTRA_DROPPED_REASON, "") + " / " + reason);
    }

    public static String getDroppedReasons(@NonNull Location location) {
        ensureExtras(location);
        return location.getExtras().getString(LocationPostService.EXTRA_DROPPED_REASON, "");
    }

    public static boolean isMockLocation(@NonNull Location location) {
        try {
            return VERSION.SDK_INT >= 18 && location.isFromMockProvider();
        } catch (NoSuchMethodError e) {
            return false;
        }
    }

    public static LatLng extractRealLocation(@NonNull Location location) {
        ensureExtras(location);
        return new LatLng(location.getExtras().getDouble(LocationPostService.EXTRA_REAL_LATITUDE), location.getExtras().getDouble(LocationPostService.EXTRA_REAL_LONGITUDE));
    }

    public static void setGeofenceList(@NonNull Location location, @NonNull List<Geofence> geofenceList, String geofenceTriggered) {
        StringBuilder sb = new StringBuilder("Geofences triggered: ");
        for (Geofence geofence : geofenceList) {
            sb.append(geofence.getRequestId()).append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        ensureExtras(location);
        location.getExtras().putString(LocationPostService.EXTRA_GEOFENCE_LIST, sb.toString());
        location.getExtras().putString(LocationPostService.EXTRA_GEOFENCE_TRANSITION, geofenceTriggered);
    }

    public static void setAcquisitionMode(@NonNull Location location, @NonNull LocationAcquisitionMode mode) {
        ensureExtras(location);
        location.getExtras().putSerializable("mode", mode);
    }

    public static void setTriggerMode(@NonNull Location location, @NonNull LocationTrigger trigger) {
        ensureExtras(location);
        location.getExtras().putSerializable(LocationPostService.KEY_TRIGGER, trigger);
    }

    @NonNull
    public static LocationAcquisitionMode getLocationAcquisitionMode(@Nullable Location location) {
        LocationAcquisitionMode mode = LocationAcquisitionMode.UNKNOWN;
        if (location == null) {
            return mode;
        }
        if (isMockLocation(location)) {
            return LocationAcquisitionMode.MOCK;
        }
        ensureExtras(location);
        if (location.getExtras().getSerializable("mode") != null) {
            mode = (LocationAcquisitionMode) location.getExtras().getSerializable("mode");
        }
        return mode != null ? mode : LocationAcquisitionMode.UNKNOWN;
    }

    @NonNull
    static LocationTrigger getLocationTrigger(@NonNull Location location) {
        LocationTrigger trigger = LocationTrigger.UNKNOWN;
        ensureExtras(location);
        if (location.getExtras().containsKey(LocationPostService.KEY_TRIGGER)) {
            trigger = (LocationTrigger) location.getExtras().getSerializable(LocationPostService.KEY_TRIGGER);
        }
        return trigger != null ? trigger : LocationTrigger.UNKNOWN;
    }

    private static void ensureExtras(Location location) {
        if (location.getExtras() == null) {
            location.setExtras(new Bundle());
        }
    }

    public static boolean isValidBSSID(String bssid) {
        String DEFAULT_BSSID_ADDRESS = "00:00:00:00:00:00";
        return (bssid == null || bssid.contentEquals("00:00:00:00:00:00")) ? false : true;
    }
}
