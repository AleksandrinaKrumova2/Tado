package com.tado.android.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ActivityDataPoints implements Serializable {
    @SerializedName("heatingPower")
    @Expose
    private ActivityDataPoint mHeatingPower;

    public ActivityDataPoint getHeatingPower() {
        return this.mHeatingPower;
    }

    public void setHeatingPower(ActivityDataPoint heatingPower) {
        this.mHeatingPower = heatingPower;
    }
}
