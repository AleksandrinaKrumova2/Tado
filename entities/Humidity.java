package com.tado.android.entities;

import com.google.gson.annotations.SerializedName;

public class Humidity {
    @SerializedName("percentage")
    private float mPercentage;
    @SerializedName("timestamp")
    private String mTimestamp;

    public float getPercentage() {
        return this.mPercentage;
    }

    public void setPercentage(float percentage) {
        this.mPercentage = percentage;
    }

    public String getTimestamp() {
        return this.mTimestamp;
    }

    public void setTimestamp(String timestamp) {
        this.mTimestamp = timestamp;
    }
}
