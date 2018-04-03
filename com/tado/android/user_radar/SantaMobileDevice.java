package com.tado.android.user_radar;

import com.tado.android.entities.MobileDeviceLocation;
import com.tado.android.rest.model.MobileDevice;
import com.tado.android.rest.model.MobileDeviceSettings;

public class SantaMobileDevice extends MobileDevice {
    public SantaMobileDevice() {
        setName("Ho Ho Ho");
        setId(-1);
        setSettings(new MobileDeviceSettings(true));
        MobileDeviceLocation location = new MobileDeviceLocation();
        location.setAtHome(false);
        location.setStale(false);
        setLocation(location);
    }
}
