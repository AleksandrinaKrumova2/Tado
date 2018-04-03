package com.tado.android.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoolingState {
    @SerializedName("fanSpeed")
    @Expose
    private String mFanSpeed;
    @SerializedName("mode")
    @Expose
    private String mMode;
    @SerializedName("power")
    @Expose
    private String mPower;
    @SerializedName("swing")
    @Expose
    private String mSwingState;
    @SerializedName("temperature")
    @Expose
    private TemperaturesMinMax mValue;

    public String getPower() {
        return this.mPower;
    }

    public void setPower(String power) {
        this.mPower = power;
        if (power.equalsIgnoreCase("OFF")) {
            this.mMode = null;
            this.mValue = null;
            this.mFanSpeed = null;
            this.mSwingState = null;
        }
    }

    public String getMode() {
        return this.mMode;
    }

    public void setMode(String mode) {
        this.mMode = mode;
    }

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

    public String toString() {
        return "Cooling State (" + this.mPower + ", " + this.mMode + ", " + this.mValue + ", " + this.mFanSpeed + "," + this.mSwingState + ")";
    }
}
