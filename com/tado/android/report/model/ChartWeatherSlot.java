package com.tado.android.report.model;

import com.tado.android.entities.WeatherEnum;
import com.tado.android.rest.model.Temperature;

public class ChartWeatherSlot {
    private int slotIndex;
    private Temperature temperature;
    private WeatherEnum weatherEnum;

    public ChartWeatherSlot(WeatherEnum weatherEnum, Temperature temperature, int slotIndex) {
        this.weatherEnum = weatherEnum;
        this.temperature = temperature;
        this.slotIndex = slotIndex;
    }

    public WeatherEnum getWeatherEnum() {
        return this.weatherEnum;
    }

    public Temperature getTemperature() {
        return this.temperature;
    }

    public int getSlotIndex() {
        return this.slotIndex;
    }
}
