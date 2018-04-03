package com.tado.android.entities;

import com.google.gson.annotations.SerializedName;

public class SolarIntensity {
    @SerializedName("percentage")
    private double mPercentage;
    @SerializedName("timestamp")
    private String timestamp;

    public double getPercentage() {
        return this.mPercentage;
    }

    public void setPercentage(double percentage) {
        this.mPercentage = percentage;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
