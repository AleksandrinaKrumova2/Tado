package com.tado.android.entities;

public class ACSetting {
    String fanSpeed = null;
    String mode = null;
    String power = null;
    String swing = null;
    Integer temperature = null;

    public String getSwing() {
        return this.swing;
    }

    public void setSwing(String swing) {
        this.swing = swing;
    }

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
}
