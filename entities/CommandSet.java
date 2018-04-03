package com.tado.android.entities;

import com.google.gson.annotations.SerializedName;

public class CommandSet {
    @SerializedName("code")
    private int mCode;
    @SerializedName("commandType")
    private String mCommandType;

    public int getCode() {
        return this.mCode;
    }

    public void setCode(int code) {
        this.mCode = code;
    }

    public String getCommandType() {
        return this.mCommandType;
    }

    public void setCommandType(String commandType) {
        this.mCommandType = commandType;
    }
}
