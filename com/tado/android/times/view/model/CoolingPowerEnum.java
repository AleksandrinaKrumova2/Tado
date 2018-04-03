package com.tado.android.times.view.model;

public enum CoolingPowerEnum {
    ON,
    OFF;

    public static boolean getBooleanValue(String power) {
        return power.equalsIgnoreCase("on");
    }

    public static boolean getBooleanValue(CoolingPowerEnum power) {
        return ON == power;
    }

    public static CoolingPowerEnum getCoolingPower(boolean power) {
        if (power) {
            return ON;
        }
        return OFF;
    }

    public static String getCoolingPowerString(boolean power) {
        if (power) {
            return "ON";
        }
        return "OFF";
    }
}
