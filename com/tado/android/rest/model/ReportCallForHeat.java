package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ReportCallForHeat {
    @SerializedName("dataIntervals")
    private List<ReportCallForHeatInterval> dataIntervals;
    @SerializedName("timeSeriesType")
    private String timeSeriesType;
    @SerializedName("valueType")
    private String valueType;

    public List<ReportCallForHeatInterval> getDataIntervals() {
        return this.dataIntervals;
    }
}
