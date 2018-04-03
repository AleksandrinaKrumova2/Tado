package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;

public class ReportDateRange {
    @SerializedName("fromDate")
    private String mFromDate;
    @SerializedName("toDate")
    private String mToDate;

    public String getFromDate() {
        return this.mFromDate;
    }

    public void setFromDate(String fromDate) {
        this.mFromDate = fromDate;
    }

    public String getToDate() {
        return this.mToDate;
    }

    public void setToDate(String toDate) {
        this.mToDate = toDate;
    }
}
