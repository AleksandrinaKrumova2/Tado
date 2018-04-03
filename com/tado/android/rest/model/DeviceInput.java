package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;

public class DeviceInput {
    @SerializedName("authKey")
    private String authKey;
    @SerializedName("serialNo")
    private String serialNo;

    public DeviceInput(String serialNo, String authKey) {
        this.authKey = authKey;
        this.serialNo = serialNo;
    }
}
