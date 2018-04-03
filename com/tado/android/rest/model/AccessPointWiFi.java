package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;

public class AccessPointWiFi {
    @SerializedName("ssid")
    private String mSsid;

    public String getSsid() {
        return this.mSsid;
    }

    public void setSsid(String ssid) {
        this.mSsid = ssid;
    }
}
