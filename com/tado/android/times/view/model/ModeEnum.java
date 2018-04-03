package com.tado.android.times.view.model;

import com.tado.android.rest.model.ZoneSetting;

public enum ModeEnum {
    COOL(ZoneSetting.MODE_COOL, 0),
    DRY(ZoneSetting.MODE_DRY, 1),
    FAN(ZoneSetting.MODE_FAN, 2),
    AUTO("AUTO", 3),
    HEAT(ZoneSetting.MODE_HEAT, 4),
    HEATING("HEATING", 5),
    HOT_WATER("HOT_WATER", 6);
    
    private String mode;
    private int position;

    private ModeEnum(String mode, int position) {
        this.mode = mode;
        this.position = position;
    }

    public String getMode() {
        return this.mode;
    }

    public int getPosition() {
        return this.position;
    }

    public static ModeEnum getModeFromString(String mode) {
        if (COOL.getMode().equalsIgnoreCase(mode)) {
            return COOL;
        }
        if (HEAT.getMode().equalsIgnoreCase(mode)) {
            return HEAT;
        }
        if (AUTO.getMode().equalsIgnoreCase(mode)) {
            return AUTO;
        }
        if (DRY.getMode().equalsIgnoreCase(mode)) {
            return DRY;
        }
        if (FAN.getMode().equalsIgnoreCase(mode)) {
            return FAN;
        }
        if (HEATING.getMode().equalsIgnoreCase(mode)) {
            return HEATING;
        }
        if (HOT_WATER.getMode().equalsIgnoreCase(mode)) {
            return HOT_WATER;
        }
        return null;
    }
}
