package com.tado.android.rest.model.installation;

import com.tado.android.entities.RemoteControl;

public enum TemperatureUnitEnum {
    CELSIUS(RemoteControl.UNIT_CELSIUS),
    FAHRENHEIT(RemoteControl.UNIT_FAHRENHEIT);
    
    private String value;

    private TemperatureUnitEnum(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
