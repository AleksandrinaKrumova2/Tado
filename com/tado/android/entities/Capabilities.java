package com.tado.android.entities;

import com.google.gson.annotations.SerializedName;

public class Capabilities {
    @SerializedName("AUTO")
    private AcModeCapability mAuto;
    @SerializedName("COOL")
    private AcModeCapability mCool;
    @SerializedName("DRY")
    private AcModeCapability mDry;
    @SerializedName("FAN")
    private AcModeCapability mFan;
    @SerializedName("HEAT")
    private AcModeCapability mHeat;

    public AcModeCapability getAuto() {
        return this.mAuto;
    }

    public void setAuto(AcModeCapability auto) {
        this.mAuto = auto;
    }

    public AcModeCapability getCool() {
        return this.mCool;
    }

    public void setCool(AcModeCapability cool) {
        this.mCool = cool;
    }

    public AcModeCapability getDry() {
        return this.mDry;
    }

    public void setDry(AcModeCapability dry) {
        this.mDry = dry;
    }

    public AcModeCapability getFan() {
        return this.mFan;
    }

    public void setFan(AcModeCapability fan) {
        this.mFan = fan;
    }

    public AcModeCapability getHeat() {
        return this.mHeat;
    }

    public void setHeat(AcModeCapability heat) {
        this.mHeat = heat;
    }
}
