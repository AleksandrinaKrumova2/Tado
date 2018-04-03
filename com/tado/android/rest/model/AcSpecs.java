package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import com.tado.android.entities.RemoteControl;

public class AcSpecs {
    @SerializedName("acUnitDisplaysSetPointTemperature")
    private Boolean mAcUnitDisplaysSetPointTemperature;
    @SerializedName("manufacturer")
    private Manufacturer mManufacturer;
    @SerializedName("remoteControl")
    private RemoteControl mRemoteControl;

    public Boolean getAcUnitDisplaysSetPointTemperature() {
        return this.mAcUnitDisplaysSetPointTemperature;
    }

    public void setAcUnitDisplaysSetPointTemperature(Boolean acUnitDisplaysSetPointTemperature) {
        this.mAcUnitDisplaysSetPointTemperature = acUnitDisplaysSetPointTemperature;
    }

    public RemoteControl getRemoteControl() {
        return this.mRemoteControl;
    }

    public void setRemoteControl(RemoteControl remoteControl) {
        this.mRemoteControl = remoteControl;
    }

    public Manufacturer getManufacturer() {
        return this.mManufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.mManufacturer = manufacturer;
    }
}
