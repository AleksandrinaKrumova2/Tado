package com.tado.android.report.model;

import com.tado.android.rest.model.ReportWeatherConditionValue;

public class ChartWeatherInterval {
    private ChartXValueRange range;
    private ReportWeatherConditionValue weatherConditionValue;

    public ChartWeatherInterval(ChartXValueRange range, ReportWeatherConditionValue weatherConditionValue) {
        this.range = range;
        this.weatherConditionValue = weatherConditionValue;
    }

    public ChartXValueRange getRange() {
        return this.range;
    }

    public ReportWeatherConditionValue getWeatherConditionValue() {
        return this.weatherConditionValue;
    }
}
