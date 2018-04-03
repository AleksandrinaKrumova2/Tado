package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class OpenWindow implements Serializable {
    @SerializedName("detectedTime")
    private String detectedTime;
    @SerializedName("durationInSeconds")
    private int durationInSeconds;
    @SerializedName("expiry")
    private String expiry;
    @SerializedName("remainingTimeInSeconds")
    private int remainingTimeInSeconds;

    public int getDurationInSeconds() {
        return this.durationInSeconds;
    }

    public String getExpiry() {
        return this.expiry;
    }

    public int getRemainingTimeInSeconds() {
        return this.remainingTimeInSeconds;
    }

    public String getDetectedTime() {
        return this.detectedTime;
    }
}
