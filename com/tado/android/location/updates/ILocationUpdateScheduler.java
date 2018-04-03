package com.tado.android.location.updates;

import com.tado.android.location.ICancelableScheduler;
import com.tado.android.rest.model.GeolocationUpdate;

public interface ILocationUpdateScheduler extends ICancelableScheduler {
    boolean scheduleLocationUpdate(GeolocationUpdate geolocationUpdate, boolean z);
}
