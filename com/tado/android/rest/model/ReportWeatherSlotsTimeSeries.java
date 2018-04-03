package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import com.tado.android.report.model.BaseTimeSeries;
import java.util.Map;

public class ReportWeatherSlotsTimeSeries extends BaseTimeSeries {
    @SerializedName("slots")
    Map<ReportWeatherSlotsHoursEnum, ReportWeatherConditionValue> slots;

    public Map<ReportWeatherSlotsHoursEnum, ReportWeatherConditionValue> getSlots() {
        return this.slots;
    }
}
