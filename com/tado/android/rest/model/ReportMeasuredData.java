package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import com.tado.android.report.model.DataPointTimeSeries;
import com.tado.android.report.model.IntervalTimeSeries;

public class ReportMeasuredData {
    @SerializedName("humidity")
    DataPointTimeSeries<Humidity> humidity;
    @SerializedName("insideTemperature")
    DataPointTimeSeries<Temperature> insideTemperature;
    @SerializedName("measuringDeviceConnected")
    IntervalTimeSeries<Boolean> measuringDeviceConnected;

    public IntervalTimeSeries<Boolean> getMeasuringDeviceConnected() {
        return this.measuringDeviceConnected;
    }

    public DataPointTimeSeries<Temperature> getInsideTemperature() {
        return this.insideTemperature;
    }

    public DataPointTimeSeries<Humidity> getHumidity() {
        return this.humidity;
    }
}
