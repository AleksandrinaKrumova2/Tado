package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import com.tado.android.report.model.BaseReportInterval;

public class ReportDataInterval<T> extends BaseReportInterval {
    @SerializedName("value")
    T value;

    public T getValue() {
        return this.value;
    }
}
