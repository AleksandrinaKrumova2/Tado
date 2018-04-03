package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Precision implements Serializable {
    @SerializedName("celsius")
    private float mCelsius;
    @SerializedName("fahrenheit")
    private float mFahrenheit;

    public float getCelsius() {
        return this.mCelsius;
    }

    public float getFahrenheit() {
        return this.mFahrenheit;
    }
}
