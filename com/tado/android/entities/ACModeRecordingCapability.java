package com.tado.android.entities;

public class ACModeRecordingCapability {
    private String fanSpeed;
    private String swing;
    private TemperatureRange temperatures;

    public String getFanSpeed() {
        return this.fanSpeed;
    }

    public void setFanSpeed(String fanSpeed) {
        this.fanSpeed = fanSpeed;
    }

    public String getSwing() {
        return this.swing;
    }

    public void setSwing(String swing) {
        this.swing = swing;
    }

    public TemperatureRange getTemperatures() {
        return this.temperatures;
    }

    public void setTemperatures(TemperatureRange temperatures) {
        this.temperatures = temperatures;
    }
}
