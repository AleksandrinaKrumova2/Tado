package com.tado.android.report.model;

import com.google.gson.annotations.SerializedName;
import com.tado.android.rest.model.ReportDataInterval;
import java.util.List;

public class IntervalTimeSeries<T> extends BaseTimeSeries {
    @SerializedName("dataIntervals")
    private List<ReportDataInterval<T>> dataIntervals;

    public List<ReportDataInterval<T>> getDataIntervals() {
        return this.dataIntervals;
    }
}
