package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;

public class MobileDeviceMetadata {
    @SerializedName("device")
    private MobileDeviceDeviceMetadata mDevice;
    @SerializedName("tadoApp")
    private MobileDeviceTadoAppMetadata mTadoApp;

    public MobileDeviceDeviceMetadata getDevice() {
        return this.mDevice;
    }

    public void setDevice(MobileDeviceDeviceMetadata device) {
        this.mDevice = device;
    }

    public MobileDeviceTadoAppMetadata getTadoApp() {
        return this.mTadoApp;
    }

    public void setTadoApp(MobileDeviceTadoAppMetadata tadoApp) {
        this.mTadoApp = tadoApp;
    }
}
