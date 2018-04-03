package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;

public class ReportDataPoint<T> {
    @SerializedName("timestamp")
    String timestamp;
    @SerializedName("value")
    T value;

    public String getTimestamp() {
        return this.timestamp;
    }

    public T getValue() {
        return this.value;
    }
}
