package com.tado.android.rest.model.installation;

import com.tado.android.rest.model.ZoneSetting;

public enum ModeEnum {
    COOL(ZoneSetting.MODE_COOL),
    HEAT(ZoneSetting.MODE_HEAT),
    DRY(ZoneSetting.MODE_DRY),
    FAN(ZoneSetting.MODE_FAN),
    AUTO("AUTO");
    
    private String value;

    private ModeEnum(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
