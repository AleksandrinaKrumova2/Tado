package com.tado.android.utils;

import com.google.gson.GsonBuilder;

public class ApiResponseLogEntry implements BaseLogEntryInterface {
    private String exceptionMessage;
    private String stream;

    public String getStream() {
        return this.stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getExceptionMessage() {
        return this.exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public byte[] getBytes() {
        return (new GsonBuilder().create().toJson((Object) this) + ",").getBytes();
    }
}
