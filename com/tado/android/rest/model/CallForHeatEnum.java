package com.tado.android.rest.model;

public enum CallForHeatEnum {
    NONE,
    LOW,
    MEDIUM,
    HIGH;

    public static CallForHeatEnum getEnumForPower(float power) {
        if (power < 0.1f) {
            return NONE;
        }
        if (power < 33.3f) {
            return LOW;
        }
        if (power < 66.6f) {
            return MEDIUM;
        }
        return HIGH;
    }
}
