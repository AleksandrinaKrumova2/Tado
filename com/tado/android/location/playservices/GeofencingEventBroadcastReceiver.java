package com.tado.android.location.playservices;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.v4.widget.AutoScrollHelper;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingEvent;
import com.tado.android.app.TadoApplication;
import com.tado.android.location.LocationAcquisitionMode;
import com.tado.android.location.LocationTrigger;
import com.tado.android.location.LocationUtil;
import com.tado.android.utils.Snitcher;
import java.util.List;

public class GeofencingEventBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "GeofenceIntentService";

    public void onReceive(Context context, Intent intent) {
        GeofencingEvent event = null;
        try {
            event = GeofencingEvent.fromIntent(intent);
        } catch (Exception e) {
            Snitcher.start().toLogger().log(3, TAG, e.getMessage(), new Object[0]);
        }
        if (event == null || event.hasError()) {
            Snitcher.start().toLogger().log(3, TAG, GeofenceStatusCodes.getStatusCodeString(event.getErrorCode()), new Object[0]);
            return;
        }
        Snitcher.start().toLogger().log(TAG, "Geofence event received at %f, %f (%f)", Double.valueOf(event.getTriggeringLocation().getLatitude()), Double.valueOf(event.getTriggeringLocation().getLongitude()), Float.valueOf(event.getTriggeringLocation().getAccuracy()));
        int geofenceTransition = event.getGeofenceTransition();
        if (geofenceTransition == 1 || geofenceTransition == 2 || geofenceTransition == 4) {
            List<Geofence> geofenceList = event.getTriggeringGeofences();
            Snitcher.start().toLogger().log(TAG, "Number of geofences triggered %d", Integer.valueOf(geofenceList.size()));
            Geofence closestGeofence = (Geofence) geofenceList.get(0);
            float minDist = AutoScrollHelper.NO_MAX;
            for (Geofence geofence : geofenceList) {
                float dist = TadoApplication.locationManager.getDistanceFromGeofence(geofence.getRequestId());
                if (dist < minDist) {
                    minDist = dist;
                    closestGeofence = geofence;
                }
            }
            String geofenceTriggered = "UNKNOWN";
            switch (geofenceTransition) {
                case 1:
                    Snitcher.start().toLogger().log(3, TAG, "postGeofence: Geofence ENTER:  %s with distance %f to home", closestGeofence.toString(), Float.valueOf(minDist));
                    geofenceTriggered = String.format("%s %s", new Object[]{closestGeofence.toString(), "GEOFENCE_TRANSITION_ENTER"});
                    break;
                case 2:
                    Snitcher.start().toLogger().log(3, TAG, "postGeofence: Geofence LEFT:  %s with distance %f to home", closestGeofence.toString(), Float.valueOf(minDist));
                    geofenceTriggered = String.format("%s %s", new Object[]{closestGeofence.toString(), "GEOFENCE_TRANSITION_EXIT"});
                    break;
                case 4:
                    Snitcher.start().toLogger().log(3, TAG, "postGeofence: Geofence DWELL:  %s with distance %f to home", closestGeofence.toString(), Float.valueOf(minDist));
                    geofenceTriggered = String.format("%s %s", new Object[]{closestGeofence.toString(), "GEOFENCE_TRANSITION_DWELL"});
                    break;
            }
            final Location location = event.getTriggeringLocation();
            if (location != null) {
                LocationUtil.addUUID(location);
                LocationUtil.setGeofenceList(location, geofenceList, geofenceTriggered);
                LocationUtil.setAcquisitionMode(location, LocationAcquisitionMode.GEOFENCING);
                LocationUtil.setTriggerMode(location, LocationTrigger.GEOFENCE);
                final Context context2 = context;
                new Thread(new Runnable() {
                    public void run() {
                        TadoApplication.locationManager.postLocation(context2.getApplicationContext(), location);
                    }
                }).start();
                return;
            }
            Snitcher.start().toLogger().log(3, TAG, "postGeofence: Did not find last location for this geofence =(", new Object[0]);
            return;
        }
        Snitcher.start().toLogger().log(3, TAG, "Geofence error %s", GeofenceStatusCodes.getStatusCodeString(event.getErrorCode()));
    }
}
