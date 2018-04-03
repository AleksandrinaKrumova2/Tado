package com.tado.android.utils;

import com.google.gson.GsonBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SimpleLogEntry implements BaseLogEntryInterface {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss", Locale.GERMANY);
    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = dateFormat.format(new Date(System.currentTimeMillis())) + " " + message;
    }

    public byte[] getBytes() {
        return (new GsonBuilder().create().toJson((Object) this) + ",").getBytes();
    }
}
