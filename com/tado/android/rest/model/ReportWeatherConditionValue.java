package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import com.tado.android.entities.WeatherEnum;

public class ReportWeatherConditionValue {
    @SerializedName("state")
    WeatherEnum condition;
    @SerializedName("temperature")
    Temperature temperature;

    public Temperature getTemperature() {
        return this.temperature;
    }

    public WeatherEnum getCondition() {
        return this.condition;
    }
}
