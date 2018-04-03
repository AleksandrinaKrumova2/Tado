package com.tado.android.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tado.android.controllers.CapabilitiesController;

public class TemperaturesMinMax {
    @SerializedName("celsius")
    @Expose
    private float mCelsius;
    @SerializedName("fahrenheit")
    @Expose
    private float mFahrenheit;

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

    public float getTemperature() {
        if (CapabilitiesController.INSTANCE.isCelsiusTemperatureUnit()) {
            return this.mCelsius;
        }
        return this.mFahrenheit;
    }

    public void setTemperature(int temperature) {
        if (CapabilitiesController.INSTANCE.isCelsiusTemperatureUnit()) {
            this.mCelsius = (float) temperature;
        } else {
            this.mFahrenheit = (float) temperature;
        }
    }
}
