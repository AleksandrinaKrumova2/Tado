package com.tado.android.times.view.model;

import com.tado.android.entities.TemperaturesMinMax;

public class CoolingModeState {
    private String mFanSpeed;
    private String mSwingState;
    private TemperaturesMinMax mValue;

    public TemperaturesMinMax getValue() {
        return this.mValue;
    }

    public void setValue(TemperaturesMinMax value) {
        this.mValue = value;
    }

    public String getFanSpeed() {
        return this.mFanSpeed;
    }

    public void setFanSpeed(String fanSpeed) {
        this.mFanSpeed = fanSpeed;
    }

    public String getSwingState() {
        return this.mSwingState;
    }

    public void setSwingState(String swingState) {
        this.mSwingState = swingState;
    }
}
