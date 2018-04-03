package com.tado.android.settings.zonesettings;

import com.tado.android.rest.model.Zone;

public interface SrtAssignCallback {
    void allowBackNavigation(boolean z);

    void onAssignedZone(Zone zone, boolean z);

    void onServerCallFailure();
}
