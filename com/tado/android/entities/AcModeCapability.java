package com.tado.android.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class AcModeCapability {
    @SerializedName("fanSpeeds")
    @Expose
    private List<String> mFanSpeeds;
    @SerializedName("swings")
    @Expose
    private List<String> mSwings;
    @SerializedName("temperatures")
    private Temperatures mTemperatures;

    public List<String> getSwings() {
        return this.mSwings;
    }

    public void setSwings(List<String> swings) {
        this.mSwings = swings;
    }

    public Temperatures getTemperatures() {
        return this.mTemperatures;
    }

    public void setTemperatures(Temperatures temperatures) {
        this.mTemperatures = temperatures;
    }

    public List<String> getFanSpeeds() {
        return this.mFanSpeeds;
    }

    public void setFanSpeeds(List<String> fanSpeeds) {
        this.mFanSpeeds = fanSpeeds;
    }
}
