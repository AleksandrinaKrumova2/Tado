package com.tado.android.entities;

import com.google.gson.annotations.SerializedName;

public class WeatherState {
    @SerializedName("timestamp")
    private String mTimestamp;
    @SerializedName("value")
    private WeatherEnum mValue;

    public WeatherEnum getValue() {
        return this.mValue;
    }

    public void setValue(WeatherEnum value) {
        this.mValue = value;
    }

    public String getTimestamp() {
        return this.mTimestamp;
    }

    public void setTimestamp(String timestamp) {
        this.mTimestamp = timestamp;
    }
}
