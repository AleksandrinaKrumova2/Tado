package com.tado.android.times.view.model;

public enum CoolingFanSpeedEnum {
    LOW,
    MEDIUM,
    HIGH,
    AUTO;

    public static String getStringValue(CoolingFanSpeedEnum fanSpeedEnum) {
        if (LOW == fanSpeedEnum) {
            return "LOW";
        }
        if (MEDIUM == fanSpeedEnum) {
            return "MIDDLE";
        }
        if (HIGH == fanSpeedEnum) {
            return "HIGH";
        }
        if (AUTO == fanSpeedEnum) {
            return "AUTO";
        }
        return "";
    }

    public static CoolingFanSpeedEnum getCoolingFanSpeed(String fanSpeedString) {
        if ("LOW".equalsIgnoreCase(fanSpeedString)) {
            return LOW;
        }
        if ("MIDDLE".equalsIgnoreCase(fanSpeedString)) {
            return MEDIUM;
        }
        if ("HIGH".equalsIgnoreCase(fanSpeedString)) {
            return HIGH;
        }
        if ("AUTO".equalsIgnoreCase(fanSpeedString)) {
            return AUTO;
        }
        return null;
    }
}
