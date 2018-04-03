package com.tado.android.location.playservices;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationResult;
import com.nhaarman.supertooltips.ToolTipRelativeLayout;
import com.tado.android.app.TadoApplication;
import com.tado.android.location.LocationTrigger;
import com.tado.android.location.LocationUtil;
import com.tado.android.location.OnLastKnownLocation;
import com.tado.android.location.PostState;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.Snitcher.Builder;
import com.tado.android.utils.UserConfig;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FusedLocationBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "FusedLocationBroadcastReceiver";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss", Locale.UK);

    class C10152 implements OnLastKnownLocation {
        C10152() {
        }

        public void onLastKnownLocation(Location result) {
            boolean equals = LocationUtil.sameLocation(UserConfig.getLastSeenLocation(), result);
            Builder toLogger = Snitcher.start().toLogger();
            String str = FusedLocationBroadcastReceiver.TAG;
            String str2 = "last known location is %f, %f (%f) but not sending it. %s to last known location";
            Object[] objArr = new Object[4];
            objArr[0] = Double.valueOf(result.getLatitude());
            objArr[1] = Double.valueOf(result.getLongitude());
            objArr[2] = Float.valueOf(result.getAccuracy());
            objArr[3] = equals ? "equal" : "not equal";
            toLogger.log(5, str, str2, objArr);
            toLogger = Snitcher.start().toLogger();
            str = FusedLocationBroadcastReceiver.TAG;
            str2 = "location is %s to last seen location";
            objArr = new Object[1];
            objArr[0] = equals ? "equal" : "not equal";
            toLogger.log(5, str, str2, objArr);
        }
    }

    public void onReceive(final Context context, Intent intent) {
        try {
            Builder toLogger = Snitcher.start().toLogger();
            String str = TAG;
            String str2 = "onReceive: %s \n hasLocationAvailability: %b \n hasResult %b";
            Object[] objArr = new Object[3];
            objArr[0] = intent != null ? intent.toString() : "intent is NULL";
            objArr[1] = Boolean.valueOf(LocationAvailability.hasLocationAvailability(intent));
            objArr[2] = Boolean.valueOf(LocationResult.hasResult(intent));
            toLogger.log(3, str, str2, objArr);
            if (LocationResult.hasResult(intent)) {
                LocationResult result = LocationResult.extractResult(intent);
                if (result != null) {
                    final Location location = result.getLastLocation();
                    if (location != null) {
                        LocationUtil.addUUID(location);
                        Snitcher.start().toLogger().log(3, TAG, "\n******** \n** location received %f , %f (%f m.) \n** at %s \n** (id: %s) \n********", Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), Float.valueOf(location.getAccuracy()), dateFormat.format(new Date(location.getTime())), location.getExtras().getString(ToolTipRelativeLayout.ID));
                        LocationUtil.updateLocationsListAndShowNotification(location, PostState.UNKNOWN, context, LocationTrigger.FUSED);
                        LocationUtil.setAcquisitionMode(location, TadoApplication.locationManager.getLocationConfiguration().getFusedAccuracyAsEnum());
                        LocationUtil.setTriggerMode(location, LocationTrigger.FUSED);
                        new Thread(new Runnable() {
                            public void run() {
                                TadoApplication.locationManager.postLocation(context.getApplicationContext(), location);
                            }
                        }).start();
                        return;
                    }
                    Snitcher.start().toLogger().log(6, TAG, "****** onReceive intent result has NULL location WTF! *******", new Object[0]);
                    return;
                }
                Snitcher.start().toLogger().log(6, TAG, "****** onReceive intent RESULT was NULL WTF!*******", new Object[0]);
                return;
            }
            Snitcher.start().toLogger().log(6, TAG, "****** onReceive intent HAS NO RESULT *******", new Object[0]);
            if (!LocationAvailability.hasLocationAvailability(intent)) {
                Snitcher.start().toLogger().log(5, TAG, "Location is not available", new Object[0]);
            } else if (LocationAvailability.extractLocationAvailability(intent).isLocationAvailable()) {
                TadoApplication.locationManager.getLastKnownLocation(new C10152());
            }
        } catch (Exception e) {
            Snitcher.start().toLogger().logException(e);
        }
    }
}
