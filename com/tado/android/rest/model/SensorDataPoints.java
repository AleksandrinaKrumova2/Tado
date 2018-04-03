package com.tado.android.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class SensorDataPoints implements Serializable {
    @SerializedName("humidity")
    @Expose
    private SensorDataPoint mHumidity;
    @SerializedName("insideTemperature")
    @Expose
    private SensorDataPoint mInsideTemperature;

    public SensorDataPoint getInsideTemperature() {
        return this.mInsideTemperature;
    }

    public SensorDataPoint getHumidity() {
        return this.mHumidity;
    }
}
