package com.tado.android.entities;

public class TemperatureRange {
    int max;
    int min;

    public TemperatureRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return this.min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return this.max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String toString() {
        return this.min + "°.." + this.max + "°";
    }

    public boolean isSingleValue() {
        return this.min == this.max;
    }
}
