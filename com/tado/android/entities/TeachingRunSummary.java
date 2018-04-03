package com.tado.android.entities;

public class TeachingRunSummary {
    String fanSpeed;
    String mode;
    String swing;
    TemperatureRange temperatures;

    public String getSwing() {
        return this.swing;
    }

    public void setSwing(String swing) {
        this.swing = swing;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getFanSpeed() {
        return this.fanSpeed;
    }

    public void setFanSpeed(String fanSpeed) {
        this.fanSpeed = fanSpeed;
    }

    public TemperatureRange getTemperatures() {
        return this.temperatures;
    }

    public void setTemperatures(TemperatureRange temperatures) {
        this.temperatures = temperatures;
    }
}
