package com.tado.android.report.model;

import com.google.gson.annotations.SerializedName;

public class BaseTimeSeries {
    @SerializedName("timeSeriesType")
    private String timeSeriesType;
    @SerializedName("valueType")
    private String valueType;
}
