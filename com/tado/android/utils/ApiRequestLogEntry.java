package com.tado.android.utils;

import com.google.gson.GsonBuilder;

public class ApiRequestLogEntry implements BaseLogEntryInterface {
    private String address;
    private String requestContent;

    public byte[] getBytes() {
        return (new GsonBuilder().create().toJson((Object) this) + ",").getBytes();
    }

    public String getRequestContent() {
        return this.requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
