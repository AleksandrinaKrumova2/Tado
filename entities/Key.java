package com.tado.android.entities;

public class Key {
    public static final String POWER = "POWER";
    public static final String TEMP_DOWN = "TEMP_DOWN";
    public static final String TEMP_UP = "TEMP_UP";
    private String key;

    public Key(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
