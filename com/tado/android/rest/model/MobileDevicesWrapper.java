package com.tado.android.rest.model;

import java.util.ArrayList;
import java.util.List;

public class MobileDevicesWrapper {
    List<MobileDevice> mMobileDevices = new ArrayList();

    public List<MobileDevice> getMobileDevices() {
        return this.mMobileDevices;
    }

    public void setMobileDevices(List<MobileDevice> mobileDevices) {
        this.mMobileDevices = mobileDevices;
    }
}
