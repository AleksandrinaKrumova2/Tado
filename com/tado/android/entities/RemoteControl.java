package com.tado.android.entities;

import com.google.gson.annotations.Expose;

public class RemoteControl {
    public static final String UNIT_CELSIUS = "CELSIUS";
    public static final String UNIT_FAHRENHEIT = "FAHRENHEIT";
    @Expose
    private String commandType = null;
    @Expose
    private String temperatureUnit = null;

    public String getCommandType() {
        return this.commandType;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }

    public String getTemperatureUnit() {
        return this.temperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }
}
