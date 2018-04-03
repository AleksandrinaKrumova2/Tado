package com.tado.android.utils;

import com.tado.android.entities.WeatherEnum;
import com.tado.android.rest.model.ReportWeatherConditionValue;

public class WeatherUtils {
    private static final String NO_TEMPERATURE_PLACEHOLDER = "--°";

    public static String getFormattedTemperatureForWeatherConditionValue(ReportWeatherConditionValue weatherConditionValue) {
        return weatherConditionValue != null ? weatherConditionValue.getTemperature().getFormattedTemperatureValue(1.0f) : NO_TEMPERATURE_PLACEHOLDER;
    }

    public static WeatherEnum getWetherEnumForWeatherConditionValue(ReportWeatherConditionValue weatherConditionValue) {
        return weatherConditionValue != null ? weatherConditionValue.getCondition() : WeatherEnum.NO_WEATHER;
    }
}
