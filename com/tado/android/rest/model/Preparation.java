package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import com.tado.android.rest.model.installation.HeatingZoneSetting;
import java.io.Serializable;

public class Preparation implements Serializable {
    @SerializedName("end")
    private String mEnd;
    @SerializedName("setting")
    private HeatingZoneSetting mSetting;

    public String getEnd() {
        return this.mEnd;
    }

    public HeatingZoneSetting getSetting() {
        return this.mSetting;
    }
}
