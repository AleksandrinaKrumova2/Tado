package com.tado.android.rest.model;

import com.google.gson.annotations.Expose;
import com.tado.android.entities.ACSetting;

public class AcSetting {
    @Expose
    private String fanSpeed;
    @Expose
    private String mode;
    @Expose
    private String power;
    @Expose
    private String swing;
    @Expose
    private Integer temperature;

    public String getPower() {
        return this.power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Integer getTemperature() {
        return this.temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

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

    public void copy(ACSetting acSettings) {
        this.fanSpeed = acSettings.getFanSpeed();
        this.mode = acSettings.getMode();
        this.power = acSettings.getPower();
        this.swing = acSettings.getSwing();
        this.temperature = acSettings.getTemperature();
    }
}
