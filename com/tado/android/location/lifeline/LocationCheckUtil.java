package com.tado.android.location.lifeline;

import com.tado.android.app.TadoApplication;
import com.tado.android.location.LocationAcquisitionMode;
import com.tado.android.location.LocationTrigger;
import com.tado.android.location.jobservices.IJobServiceAdapter;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UserConfig;

class LocationCheckUtil {
    private static final String TAG = "LocationCheckUtil";

    LocationCheckUtil() {
    }

    static void checkLocationServices(IJobServiceAdapter jobServiceAdapter) {
        try {
            Snitcher.start().toLogger().log(3, TAG, "checking if location tracking is till running (aka LocationServicesLifeline)", new Object[0]);
            if (UserConfig.isLocationBasedControlEnabled()) {
                TadoApplication.locationManager.checkStatus();
                TadoApplication.locationManager.postLastKnownLocation(LocationAcquisitionMode.LAST_KNOWN_LOCATION, LocationTrigger.ALARM);
                TadoApplication.locationManager.reset();
                TadoApplication.locationManager.startTrackingIfEnabled();
                jobServiceAdapter.finishJob();
                return;
            }
            TadoApplication.locationManager.stopTracking();
            jobServiceAdapter.finishJob();
        } catch (Exception e) {
            Snitcher.start().toLogger().logException(e);
            jobServiceAdapter.rescheduleJob();
        }
    }
}
