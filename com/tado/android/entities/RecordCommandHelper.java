package com.tado.android.entities;

public class RecordCommandHelper {
    private int buttonStringId;
    private int imageResourceId;
    private String keyString;
    private int titleId;

    public RecordCommandHelper(int titleId, int buttonStringId, int imageResourceId, String keyString) {
        this.titleId = titleId;
        this.buttonStringId = buttonStringId;
        this.imageResourceId = imageResourceId;
        this.keyString = keyString;
    }

    public int getTitleId() {
        return this.titleId;
    }

    public int getButtonStringId() {
        return this.buttonStringId;
    }

    public int getImageResourceId() {
        return this.imageResourceId;
    }

    public String getKeyString() {
        return this.keyString;
    }
}
