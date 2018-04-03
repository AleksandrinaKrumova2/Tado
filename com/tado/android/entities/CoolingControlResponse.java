package com.tado.android.entities;

public class CoolingControlResponse {
    private Manufacturer acManufacturer;
    private ServerError[] errors;
    private String temperatureUnit;

    public String getTemperatureUnit() {
        return this.temperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    public Manufacturer getAcManufacturer() {
        return this.acManufacturer;
    }

    public void setAcManufacturer(Manufacturer acManufacturer) {
        this.acManufacturer = acManufacturer;
    }

    public ServerError[] getErrors() {
        return this.errors;
    }

    public void setErrors(ServerError[] errors) {
        this.errors = errors;
    }
}
