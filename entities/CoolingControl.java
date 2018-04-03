package com.tado.android.entities;

import com.google.gson.annotations.SerializedName;

public class CoolingControl {
    @SerializedName("acCommandSet")
    private AcCommandSet mAcCommandSet;
    @SerializedName("acManufacturer")
    private AcManufacturer mAcManufacturer;
    @SerializedName("remoteControl")
    private RemoteControl mRemoteControl;
    @SerializedName("temperatureUnit")
    private String mTemperatureUnit;
    @SerializedName("userAcCapabilities")
    private UserAcCapabilities mUserAcCapabilities;

    public UserAcCapabilities getUserAcCapabilities() {
        return this.mUserAcCapabilities;
    }

    public void setUserAcCapabilities(UserAcCapabilities userAcCapabilities) {
        this.mUserAcCapabilities = userAcCapabilities;
    }

    public String getTemperatureUnit() {
        return this.mTemperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.mTemperatureUnit = temperatureUnit;
    }

    public AcManufacturer getAcManufacturer() {
        return this.mAcManufacturer;
    }

    public void setAcManufacturer(AcManufacturer acManufacturer) {
        this.mAcManufacturer = acManufacturer;
    }

    public RemoteControl getRemoteControl() {
        return this.mRemoteControl;
    }

    public void setRemoteControl(RemoteControl mRemoteControl) {
        this.mRemoteControl = mRemoteControl;
    }

    public AcCommandSet getAcCommandSet() {
        return this.mAcCommandSet;
    }

    public void setAcCommandSet(AcCommandSet acCommandSet) {
        this.mAcCommandSet = acCommandSet;
    }
}
