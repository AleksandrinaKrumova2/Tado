package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;

public class IntRange {
    @SerializedName("max")
    private int mMax;
    @SerializedName("min")
    private int mMin;
    @SerializedName("step")
    private float mStep;

    public float getStep() {
        return this.mStep;
    }

    public void setStep(float step) {
        this.mStep = step;
    }

    public int getMin() {
        return this.mMin;
    }

    public void setMin(int min) {
        this.mMin = min;
    }

    public int getMax() {
        return this.mMax;
    }

    public void setMax(int max) {
        this.mMax = max;
    }

    public int hashCode() {
        return ((((this.mMin + 527) * 31) + this.mMax) * 31) + Float.floatToIntBits(this.mStep);
    }
}
