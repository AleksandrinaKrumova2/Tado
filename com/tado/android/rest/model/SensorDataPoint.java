package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import com.tado.android.controllers.CapabilitiesController;
import java.io.Serializable;

public class SensorDataPoint implements Serializable {
    public static final String PERCENTAGE = "PERCENTAGE";
    public static final String TEMPERATURE = "TEMPERATURE";
    @SerializedName("celsius")
    private float mCelsius;
    @SerializedName("fahrenheit")
    private float mFahrenheit;
    @SerializedName("percentage")
    private float mPercentage;
    @SerializedName("precision")
    private Precision mPrecision;
    @SerializedName("timestamp")
    private String mTimestamp;
    @SerializedName("type")
    private String mType;

    public Precision getPrecision() {
        return this.mPrecision;
    }

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

    public float getTemperature() {
        if (CapabilitiesController.INSTANCE.isCelsiusTemperatureUnit()) {
            return this.mCelsius;
        }
        return this.mFahrenheit;
    }
}
