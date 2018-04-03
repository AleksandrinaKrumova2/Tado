package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import com.tado.android.rest.model.installation.GenericHardwareDevice;

public class WirelessRemote extends GenericHardwareDevice {
    @SerializedName("accessPointWiFi")
    private AccessPointWiFi mAccessPointWiFi;
    @SerializedName("commandTableUploadState")
    private String mCommandTableUploadState;

    public AccessPointWiFi getAccessPointWiFi() {
        return this.mAccessPointWiFi;
    }

    public void setAccessPointWiFi(AccessPointWiFi accessPointWiFi) {
        this.mAccessPointWiFi = accessPointWiFi;
    }

    public String getCommandTableUploadState() {
        return this.mCommandTableUploadState;
    }

    public void setCommandTableUploadState(String commandTableUploadState) {
        this.mCommandTableUploadState = commandTableUploadState;
    }
}
