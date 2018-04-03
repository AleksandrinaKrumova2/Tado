package com.tado.android.report.model;

import com.google.gson.annotations.SerializedName;
import com.tado.android.rest.model.ReportDataPoint;
import java.util.List;

public class DataPointTimeSeries<T> extends BaseTimeSeries {
    @SerializedName("dataPoints")
    private List<ReportDataPoint<T>> dataPoints;
    @SerializedName("max")
    T max;
    @SerializedName("min")
    T min;

    public List<ReportDataPoint<T>> getDataPoints() {
        return this.dataPoints;
    }

    public T getMax() {
        return this.max;
    }

    public T getMin() {
        return this.min;
    }
}
