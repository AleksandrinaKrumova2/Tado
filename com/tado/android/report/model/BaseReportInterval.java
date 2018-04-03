package com.tado.android.report.model;

import com.google.gson.annotations.SerializedName;

public class BaseReportInterval {
    @SerializedName("from")
    String from;
    @SerializedName("to")
    String to;

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }
}
