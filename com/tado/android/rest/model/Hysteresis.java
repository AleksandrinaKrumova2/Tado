package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import com.tado.android.controllers.CapabilitiesController;
import java.io.Serializable;

public class Hysteresis implements Serializable {
    @SerializedName("celsius")
    private Float mCelsius;
    @SerializedName("fahrenheit")
    private Float mFahrenheit;

    public void setValue(float value) {
        if (CapabilitiesController.INSTANCE.isCelsiusTemperatureUnit()) {
            this.mCelsius = Float.valueOf(value);
            this.mFahrenheit = null;
            return;
        }
        this.mFahrenheit = Float.valueOf(value);
        this.mCelsius = null;
    }

    public float getValue() {
        if (CapabilitiesController.INSTANCE.isCelsiusTemperatureUnit()) {
            return this.mCelsius.floatValue();
        }
        return this.mFahrenheit.floatValue();
    }
}
