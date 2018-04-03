package com.tado.android.entities;

import com.google.gson.annotations.SerializedName;

public class Temperatures {
    @SerializedName("max")
    private TemperaturesMinMax mMax;
    @SerializedName("min")
    private TemperaturesMinMax mMin;

    public TemperaturesMinMax getMin() {
        return this.mMin;
    }

    public void setMin(TemperaturesMinMax min) {
        this.mMin = min;
    }

    public TemperaturesMinMax getMax() {
        return this.mMax;
    }

    public void setMax(TemperaturesMinMax max) {
        this.mMax = max;
    }
}
