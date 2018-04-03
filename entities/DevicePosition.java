package com.tado.android.entities;

public class DevicePosition {
    public static final String HORIZONTAL = "HORIZONTAL";
    public static final String VERTICAL = "VERTICAL";
    private String devicePositioning;

    public DevicePosition(String devicePositioning) {
        this.devicePositioning = devicePositioning;
    }

    public String getDevicePositioning() {
        return this.devicePositioning;
    }

    public void setDevicePositioning(String devicePositioning) {
        this.devicePositioning = devicePositioning;
    }
}
