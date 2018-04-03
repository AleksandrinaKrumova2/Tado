package com.tado.android.rest.model.hvac;

import com.google.gson.annotations.SerializedName;

public class SrtDevice {
    @SerializedName("serialNo")
    private String serialNo;

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }
}
