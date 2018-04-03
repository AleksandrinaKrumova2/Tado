package com.tado.android.times.view.model;

public enum CoolingSwingStateEnum {
    ON,
    OFF;

    public static boolean getBooleanValue(String swing) {
        return swing.equalsIgnoreCase("on");
    }

    public static boolean getBooleanValue(CoolingSwingStateEnum swing) {
        return ON == swing;
    }

    public static CoolingSwingStateEnum getCoolingSwingStateEnum(boolean swing) {
        if (swing) {
            return ON;
        }
        return OFF;
    }

    public static String getCoolingSwingString(boolean swing) {
        if (swing) {
            return "ON";
        }
        return "OFF";
    }
}
