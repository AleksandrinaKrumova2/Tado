package com.tado.android.entities;

import com.google.gson.annotations.SerializedName;
import com.tado.android.controllers.CapabilitiesController;

public class StateTemperature {
    @SerializedName("celsius")
    private float mCelsius;
    @SerializedName("fahrenheit")
    private float mFahrenheit;
    @SerializedName("timestamp")
    private String mTimestamp;

    public float getCelsius() {
        return this.mCelsius;
    }

    public void setCelsius(float celsius) {
        this.mCelsius = celsius;
    }

    public float getFahrenheit() {
        return this.mFahrenheit;
    }

    public void setFahrenheit(float fahrenheit) {
        this.mFahrenheit = fahrenheit;
    }

    public String getTimestamp() {
        return this.mTimestamp;
    }

    public void setTimestamp(String timestamp) {
        this.mTimestamp = timestamp;
    }

    public int getTemperature() {
        if (CapabilitiesController.INSTANCE.isCelsiusTemperatureUnit()) {
            return Math.round(this.mCelsius);
        }
        return Math.round(this.mFahrenheit);
    }
}
