package com.tado.android.entities;

import com.google.gson.annotations.Expose;

public class ACSpecs {
    @Expose
    private boolean acUnitDisplaysSetPointTemperature;
    @Expose
    private Manufacturer manufacturer;
    @Expose
    private RemoteControl remoteControl;

    public RemoteControl getRemoteControl() {
        if (this.remoteControl == null) {
            this.remoteControl = new RemoteControl();
        }
        return this.remoteControl;
    }

    public void setRemoteControl(RemoteControl remoteControl) {
        this.remoteControl = remoteControl;
    }

    public Manufacturer getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public boolean isAcUnitDisplaysSetPointTemperature() {
        return this.acUnitDisplaysSetPointTemperature;
    }

    public void setAcUnitDisplaysSetPointTemperature(boolean acUnitDisplaysSetPointTemperature) {
        this.acUnitDisplaysSetPointTemperature = acUnitDisplaysSetPointTemperature;
    }
}
