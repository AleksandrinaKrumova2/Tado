package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;

public class MobileDeviceTadoAppMetadata {
    @SerializedName("version")
    private String mVersion;

    public String getVersion() {
        return this.mVersion;
    }

    public void setVersion(String version) {
        this.mVersion = version;
    }
}
