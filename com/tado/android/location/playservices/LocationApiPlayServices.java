package com.tado.android.location.playservices;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.tado.android.location.LocationApi;
import com.tado.android.location.LocationConfiguration;
import com.tado.android.location.OnLastKnownLocation;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.Snitcher.Builder;
import com.tado.android.utils.UserConfig;
import java.util.ArrayList;
import java.util.List;

public class LocationApiPlayServices implements LocationApi {
    public static final int FUSED_INTENT_INVALID = 1;
    public static final int GEOFENCING_INTENT_INVALID = 2;
    private static final String TAG = "LocationApiPlayServices";
    private LocationConfiguration configuration;
    private Context context;
    private boolean fusedNeedRestart = true;
    private boolean geofencingNeedRestart = true;

    class C10171 implements OnCompleteListener<Void> {
        C10171() {
        }

        public void onComplete(@NonNull Task<Void> task) {
            boolean z;
            Builder toLogger = Snitcher.start().toLogger();
            String str = LocationApiPlayServices.TAG;
            String str2 = "Starting Fused Location Provider: %s";
            Object[] objArr = new Object[1];
            objArr[0] = task.isSuccessful() ? "success" : "failed";
            toLogger.log(str, str2, objArr);
            LocationApiPlayServices locationApiPlayServices = LocationApiPlayServices.this;
            if (task.isSuccessful()) {
                z = false;
            } else {
                z = true;
            }
            locationApiPlayServices.fusedNeedRestart = z;
        }
    }

    class C10214 implements OnCompleteListener<LocationSettingsResponse> {
        C10214() {
        }

        public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
            try {
                String str;
                LocationSettingsResponse response = (LocationSettingsResponse) task.getResult(ApiException.class);
                String str2 = "Location: %s and %s \nGps: %s and %s \nNetwork: %s and %s";
                Object[] objArr = new Object[6];
                objArr[0] = response.getLocationSettingsStates().isLocationPresent() ? "present" : "NOT present";
                objArr[1] = response.getLocationSettingsStates().isLocationUsable() ? "usable" : "NOT usable";
                objArr[2] = response.getLocationSettingsStates().isGpsPresent() ? "present" : "NOT present";
                objArr[3] = response.getLocationSettingsStates().isGpsUsable() ? "usable" : "NOT usable";
                objArr[4] = response.getLocationSettingsStates().isNetworkLocationPresent() ? "present" : "NOT present";
                if (response.getLocationSettingsStates().isNetworkLocationUsable()) {
                    str = "usable";
                } else {
                    str = "NOT usable";
                }
                objArr[5] = str;
                String result = String.format(str2, objArr);
                UserConfig.setLocationServicesMode(result);
                Snitcher.start().toLogger().log(4, LocationApiPlayServices.TAG, result, new Object[0]);
            } catch (ApiException e) {
                UserConfig.setLocationServicesMode(e.getStatusMessage());
            }
        }
    }

    public LocationApiPlayServices(@NonNull Context context) {
        this.context = context.getApplicationContext();
    }

    public void startTracking() {
        if (hasPermission()) {
            int status = checkStatus();
            if (this.configuration.useFused() && this.fusedNeedRestart) {
                LocationServices.getFusedLocationProviderClient(this.context).requestLocationUpdates(getLocationRequest(this.configuration), getFusedLocationIntent(this.context)).addOnCompleteListener(new C10171());
            } else {
                LocationServices.getFusedLocationProviderClient(this.context).removeLocationUpdates(getFusedLocationIntent(this.context));
            }
            if (this.configuration.useGeofences() && (this.geofencingNeedRestart || (status & 2) == 2)) {
                final GeofencingRequest request = getGeofencingRequest();
                if (request != null) {
                    LocationServices.getGeofencingClient(this.context).removeGeofences(getGeofenceIntent(this.context)).addOnSuccessListener(new OnSuccessListener<Void>() {

                        class C10181 implements OnCompleteListener<Void> {
                            C10181() {
                            }

                            public void onComplete(@NonNull Task<Void> task) {
                                boolean z;
                                Builder toLogger = Snitcher.start().toLogger();
                                String str = LocationApiPlayServices.TAG;
                                String str2 = "Starting geofencing: %b";
                                Object[] objArr = new Object[1];
                                objArr[0] = task.isSuccessful() ? "success" : "failed";
                                toLogger.log(str, str2, objArr);
                                LocationApiPlayServices locationApiPlayServices = LocationApiPlayServices.this;
                                if (task.isSuccessful()) {
                                    z = false;
                                } else {
                                    z = true;
                                }
                                locationApiPlayServices.geofencingNeedRestart = z;
                            }
                        }

                        public void onSuccess(Void aVoid) {
                            LocationServices.getGeofencingClient(LocationApiPlayServices.this.context).addGeofences(request, LocationApiPlayServices.this.getGeofenceIntent(LocationApiPlayServices.this.context)).addOnCompleteListener(new C10181());
                        }
                    });
                } else {
                    Snitcher.start().toLogger().log(6, TAG, "geofencing request is not valid (no geofences registered yet?)", new Object[0]);
                }
            }
            logCorrectSettings();
            return;
        }
        stopTracking();
    }

    public void stopTracking() {
        LocationServices.getFusedLocationProviderClient(this.context).removeLocationUpdates(getFusedLocationIntent(this.context));
        this.fusedNeedRestart = true;
        LocationServices.getGeofencingClient(this.context).removeGeofences(getGeofenceIntent(this.context));
        this.geofencingNeedRestart = true;
    }

    public void getLastKnownLocation(final OnLastKnownLocation lastKnownLocationListener) {
        if (hasPermission()) {
            LocationServices.getFusedLocationProviderClient(this.context).getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                public void onSuccess(Location location) {
                    if (lastKnownLocationListener != null) {
                        lastKnownLocationListener.onLastKnownLocation(location);
                    }
                }
            });
        }
    }

    private void logCorrectSettings() {
        LocationServices.getSettingsClient(this.context).checkLocationSettings(new LocationSettingsRequest.Builder().addLocationRequest(getLocationRequest(this.configuration)).build()).addOnCompleteListener(new C10214());
    }

    public void reset() {
        this.geofencingNeedRestart = true;
        this.fusedNeedRestart = true;
    }

    private GeofencingRequest getGeofencingRequest() {
        List<Geofence> geofences = getGeofences(this.configuration);
        if (geofences == null) {
            return null;
        }
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.addGeofences(geofences);
        builder.setInitialTrigger(7);
        return builder.build();
    }

    private boolean hasPermission() {
        return ContextCompat.checkSelfPermission(this.context, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(this.context, "android.permission.ACCESS_COARSE_LOCATION") == 0;
    }

    public void setLocationConfig(@NonNull LocationConfiguration configuration) {
        this.configuration = configuration;
        reset();
    }

    private PendingIntent getFusedLocationIntent(Context ctx) {
        return PendingIntent.getBroadcast(ctx, 0, new Intent(ctx, FusedLocationBroadcastReceiver.class), 134217728);
    }

    private PendingIntent getGeofenceIntent(Context ctx) {
        return PendingIntent.getBroadcast(ctx, 0, new Intent(ctx, GeofencingEventBroadcastReceiver.class), 134217728);
    }

    private int checkPendingIntentsExist(Context ctx) {
        int updateIntentExist;
        int geofenceIntentExist;
        if (PendingIntent.getBroadcast(ctx, 0, new Intent(ctx, FusedLocationBroadcastReceiver.class), 536870912) == null) {
            updateIntentExist = 1;
        } else {
            updateIntentExist = 0;
        }
        if (PendingIntent.getBroadcast(ctx, 0, new Intent(ctx, GeofencingEventBroadcastReceiver.class), 536870912) == null) {
            geofenceIntentExist = 2;
        } else {
            geofenceIntentExist = 0;
        }
        if (updateIntentExist == 1 && this.configuration.useFused()) {
            Snitcher.start().toLogger().log(6, TAG, "Fused Location Pending Intent does NOT exist", new Object[0]);
        }
        if (geofenceIntentExist == 2 && this.configuration.useGeofences()) {
            Snitcher.start().toLogger().log(6, TAG, "ERROR Geofencing Pending Intent does NOT exist", new Object[0]);
        }
        return updateIntentExist & geofenceIntentExist;
    }

    @NonNull
    private LocationRequest getLocationRequest(LocationConfiguration configuration) {
        return LocationRequest.create().setPriority(configuration.getFusedAccuracy()).setInterval((long) configuration.getFusedIntervalMillis()).setFastestInterval((long) configuration.getFusedFastestIntervalMillis());
    }

    private List<Geofence> getGeofences(LocationConfiguration geolocationConfig) {
        List<Geofence> fences = new ArrayList();
        List<Float> distances = new ArrayList();
        LatLng home = null;
        if (geolocationConfig.getConfiguration() != null && geolocationConfig.getConfiguration().getHome() != null) {
            distances = geolocationConfig.getRegions();
            home = geolocationConfig.getConfiguration().getHomeLocation();
        } else if (UserConfig.getHomeLocation() != null) {
            home = UserConfig.getHomeLocation().geolocation;
        }
        if (distances.isEmpty()) {
            Snitcher.start().toLogger().log(5, TAG, "WARNING: no geofences stored, generating %d geofences with base %f and factor %f", Integer.valueOf(20), Float.valueOf(184.0f), Float.valueOf(1.3f));
            distances = generateFakeGeofences(20, 184.0f, 1.3f);
        }
        if (home == null) {
            return null;
        }
        fences = getGeofencesFromDistances(distances, home, geolocationConfig);
        if (fences.isEmpty()) {
            fences = getGeofencesFromDistances(generateFakeGeofences(20, 184.0f, 1.3f), home, geolocationConfig);
        }
        return fences;
    }

    private List<Geofence> getGeofencesFromDistances(List<Float> distances, LatLng home, LocationConfiguration geolocationConfig) {
        List<Geofence> fences = new ArrayList();
        for (int i = 0; i < distances.size(); i++) {
            if (((Float) distances.get(i)).floatValue() > 0.0f) {
                fences.add(new Geofence.Builder().setRequestId(String.valueOf(i)).setTransitionTypes(7).setCircularRegion(home.latitude, home.longitude, ((Float) distances.get(i)).floatValue()).setExpirationDuration(-1).setLoiteringDelay(geolocationConfig.getWakeupIntervalMillis()).build());
                Snitcher.start().toLogger().log(3, TAG, "Added geofence: %s %f", String.valueOf(i), distances.get(i));
            }
        }
        return fences;
    }

    private List<Float> generateFakeGeofences(int amount, float base, float factor) {
        ArrayList<Float> distances = new ArrayList(amount);
        distances.add(Float.valueOf(base));
        for (int i = 1; i < amount; i++) {
            distances.add(Float.valueOf((((float) i) * base) * factor));
        }
        return distances;
    }

    public int checkStatus() {
        return checkPendingIntentsExist(this.context);
    }
}
