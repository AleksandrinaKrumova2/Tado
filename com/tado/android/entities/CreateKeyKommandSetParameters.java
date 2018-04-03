package com.tado.android.entities;

public class CreateKeyKommandSetParameters {
    public static final int MAX_TEMP_C = 35;
    public static final int MAX_TEMP_F = 95;
    public static final int MIN_TEMP_C = 10;
    public static final int MIN_TEMP_C_HIGH = 15;
    public static final int MIN_TEMP_F = 50;
    public static final int MIN_TEMP_F_HIGH = 59;
    private int tempMax;
    private int tempMin;

    public CreateKeyKommandSetParameters(int tempMin, int tempMax) {
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public int getTempMin() {
        return this.tempMin;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
    }

    public int getTempMax() {
        return this.tempMax;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }
}
