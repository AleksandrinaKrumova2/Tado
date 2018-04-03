package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import com.tado.android.controllers.CapabilitiesController;

public class ZoneCapabilities {
    public static final String AIR_CONDITIONING = "AIR_CONDITIONING";
    public static final String HEATING = "HEATING";
    public static final String HOT_WATER = "HOT_WATER";
    @SerializedName("AUTO")
    private CoolingModeCapabilities mAuto;
    @SerializedName("canSetTemperature")
    private boolean mCanSetTemperature;
    @SerializedName("COOL")
    private CoolingModeCapabilities mCool;
    @SerializedName("DRY")
    private CoolingModeCapabilities mDry;
    @SerializedName("FAN")
    private CoolingModeCapabilities mFan;
    @SerializedName("HEAT")
    private CoolingModeCapabilities mHeat;
    @SerializedName("temperatures")
    private TemperatureRange mTemperatures;
    @SerializedName("type")
    private String mType;

    public boolean isCanSetTemperature() {
        return this.mCanSetTemperature;
    }

    public void setCanSetTemperature(boolean canSetTemperature) {
        this.mCanSetTemperature = canSetTemperature;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public CoolingModeCapabilities getDefaultModeCapabilities() {
        if (CapabilitiesController.INSTANCE.isCool()) {
            return this.mCool;
        }
        if (CapabilitiesController.INSTANCE.isDry()) {
            return this.mDry;
        }
        if (CapabilitiesController.INSTANCE.isFan()) {
            return this.mFan;
        }
        if (CapabilitiesController.INSTANCE.isAuto()) {
            return this.mAuto;
        }
        if (CapabilitiesController.INSTANCE.isHeat()) {
            return this.mHeat;
        }
        throw new IllegalStateException("no CoolingModeCapabilities are present for the getDefaultModeCapabilities");
    }

    public CoolingModeCapabilities getCool() {
        return this.mCool;
    }

    public void setCool(CoolingModeCapabilities cool) {
        this.mCool = cool;
    }

    public CoolingModeCapabilities getHeat() {
        return this.mHeat;
    }

    public void setHeat(CoolingModeCapabilities heat) {
        this.mHeat = heat;
    }

    public CoolingModeCapabilities getDry() {
        return this.mDry;
    }

    public void setDry(CoolingModeCapabilities dry) {
        this.mDry = dry;
    }

    public CoolingModeCapabilities getFan() {
        return this.mFan;
    }

    public void setFan(CoolingModeCapabilities fan) {
        this.mFan = fan;
    }

    public CoolingModeCapabilities getAuto() {
        return this.mAuto;
    }

    public void setAuto(CoolingModeCapabilities auto) {
        this.mAuto = auto;
    }

    public TemperatureRange getTemperatures() {
        return this.mTemperatures;
    }

    public void setTemperatures(TemperatureRange temperatures) {
        this.mTemperatures = temperatures;
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        int hashCode = ((this.mType == null ? 0 : this.mType.hashCode()) + 527) * 31;
        if (this.mCanSetTemperature) {
            i = 1;
        } else {
            i = 0;
        }
        i = (((((((((((hashCode + i) * 31) + (this.mCool == null ? 0 : this.mCool.hashCode())) * 31) + (this.mHeat == null ? 0 : this.mHeat.hashCode())) * 31) + (this.mDry == null ? 0 : this.mDry.hashCode())) * 31) + (this.mFan == null ? 0 : this.mFan.hashCode())) * 31) + (this.mAuto == null ? 0 : this.mAuto.hashCode())) * 31;
        if (this.mTemperatures != null) {
            i2 = this.mTemperatures.hashCode();
        }
        return i + i2;
    }

    public boolean isHeatingZone() {
        return "HEATING".equalsIgnoreCase(this.mType);
    }

    public boolean isHotWaterZone() {
        return "HOT_WATER".equalsIgnoreCase(this.mType);
    }

    public boolean isCoolingZone() {
        return "AIR_CONDITIONING".equalsIgnoreCase(this.mType);
    }
}
