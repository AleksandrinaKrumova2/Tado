package com.tado.android.rest.model.hvac;

import com.google.gson.annotations.SerializedName;

public class InstallationFlow {
    @SerializedName("installFlowId")
    String mInstallFlowId;

    public String getInstallFlowId() {
        return this.mInstallFlowId;
    }
}
