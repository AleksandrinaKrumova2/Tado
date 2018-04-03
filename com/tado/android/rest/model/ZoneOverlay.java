package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ZoneOverlay implements Serializable {
    @SerializedName("setting")
    private ZoneSetting mSetting;
    @SerializedName("termination")
    private OverlayTerminationCondition mTermination;

    public ZoneSetting getSetting() {
        return this.mSetting;
    }

    public void setSetting(ZoneSetting setting) {
        this.mSetting = setting;
    }

    public OverlayTerminationCondition getTermination() {
        return this.mTermination;
    }

    public void setTermination(OverlayTerminationCondition termination) {
        this.mTermination = termination;
    }
}
