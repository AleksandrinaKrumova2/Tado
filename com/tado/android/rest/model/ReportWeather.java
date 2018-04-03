package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import com.tado.android.report.model.IntervalTimeSeries;

public class ReportWeather {
    @SerializedName("condition")
    IntervalTimeSeries<ReportWeatherConditionValue> condition;
    @SerializedName("slots")
    ReportWeatherSlotsTimeSeries slots;
    @SerializedName("sunny")
    IntervalTimeSeries<Boolean> sunny;

    public IntervalTimeSeries<ReportWeatherConditionValue> getCondition() {
        return this.condition;
    }

    public IntervalTimeSeries<Boolean> getSunny() {
        return this.sunny;
    }

    public ReportWeatherSlotsTimeSeries getSlots() {
        return this.slots;
    }
}
