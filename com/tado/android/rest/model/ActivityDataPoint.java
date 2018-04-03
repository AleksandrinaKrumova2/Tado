package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ActivityDataPoint implements Serializable {
    @SerializedName("percentage")
    private float mPercentage;
    @SerializedName("timestamp")
    private String mTimestamp;
    @SerializedName("type")
    private String mType;

    public String getType() {
        return this.mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

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
