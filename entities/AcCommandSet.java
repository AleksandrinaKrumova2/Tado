package com.tado.android.entities;

import com.google.gson.annotations.SerializedName;

public class AcCommandSet {
    @SerializedName("code")
    private int mCode;
    @SerializedName("commandType")
    private String mCommandType;
    @SerializedName("name")
    private String mName;
    @SerializedName("userAcCapabilities")
    private Capabilities mUserAcCapabilities;

    public Capabilities getUserAcCapabilities() {
        return this.mUserAcCapabilities;
    }

    public void setUserAcCapabilities(Capabilities userAcCapabilities) {
        this.mUserAcCapabilities = userAcCapabilities;
    }

    public String getCommandType() {
        return this.mCommandType;
    }

    public void setCommandType(String commandType) {
        this.mCommandType = commandType;
    }

    public int getCode() {
        return this.mCode;
    }

    public void setCode(int code) {
        this.mCode = code;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }
}
