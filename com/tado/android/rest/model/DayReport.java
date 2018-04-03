package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import com.tado.android.report.model.BaseReportInterval;
import com.tado.android.report.model.IntervalTimeSeries;
import com.tado.android.rest.model.installation.GenericZoneSetting;

public class DayReport {
    @SerializedName("acActivity")
    private IntervalTimeSeries<AcActivityEnum> acActivity;
    @SerializedName("callForHeat")
    private IntervalTimeSeries<CallForHeatEnum> callForHeat;
    @SerializedName("hotWaterProduction")
    private IntervalTimeSeries<Boolean> hotWaterProduction;
    @SerializedName("hoursInDay")
    private int hoursInDay;
    @SerializedName("interval")
    private BaseReportInterval interval;
    @SerializedName("measuredData")
    private ReportMeasuredData measuredData;
    @SerializedName("settings")
    private IntervalTimeSeries<GenericZoneSetting> settings;
    @SerializedName("stripes")
    private IntervalTimeSeries<StripeValue> stripes;
    @SerializedName("weather")
    private ReportWeather weatherIntervals;

    public IntervalTimeSeries<AcActivityEnum> getAcActivity() {
        return this.acActivity;
    }

    public BaseReportInterval getInterval() {
        return this.interval;
    }

    public int getHoursInDay() {
        return this.hoursInDay;
    }

    public ReportMeasuredData getMeasuredData() {
        return this.measuredData;
    }

    public IntervalTimeSeries<StripeValue> getStripes() {
        return this.stripes;
    }

    public IntervalTimeSeries<GenericZoneSetting> getSettings() {
        return this.settings;
    }

    public ReportWeather getWeatherIntervals() {
        return this.weatherIntervals;
    }

    public IntervalTimeSeries<CallForHeatEnum> getCallForHeat() {
        return this.callForHeat;
    }

    public IntervalTimeSeries<Boolean> getHotWaterProduction() {
        return this.hotWaterProduction;
    }
}
