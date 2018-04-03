package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;

public class ClosedLoopControlConfig {
    @SerializedName("hysteresis")
    private Hysteresis hysteresis;
    @SerializedName("minOnOffTimeInSeconds")
    private Integer minOnOffTimeInSeconds;

    public Hysteresis getHysteresis() {
        return this.hysteresis;
    }

    public void setHysteresis(Hysteresis hysteresis) {
        this.hysteresis = hysteresis;
    }

    public Integer getMinOnOffTimeInSeconds() {
        return this.minOnOffTimeInSeconds;
    }

    public void setMinOnOffTimeInSeconds(Integer minOnOffTimeInSeconds) {
        this.minOnOffTimeInSeconds = minOnOffTimeInSeconds;
    }
}
