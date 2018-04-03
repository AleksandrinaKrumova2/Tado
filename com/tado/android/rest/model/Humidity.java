package com.tado.android.rest.model;

import com.tado.android.report.model.DisplayValue;

public class Humidity implements DisplayValue {
    private float value;

    public Humidity(float value) {
        this.value = value;
    }

    public float getDisplayValue() {
        return this.value * 100.0f;
    }

    public float getValue() {
        return this.value;
    }
}
