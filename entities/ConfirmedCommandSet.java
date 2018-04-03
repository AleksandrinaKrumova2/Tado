package com.tado.android.entities;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class ConfirmedCommandSet implements Serializable {
    @SerializedName("code")
    private int mCode;
    @SerializedName("commandType")
    private String mCommandType;
    @SerializedName("name")
    private String mName;
    @SerializedName("supportedModes")
    private List<String> mSupportedModes;
    @SerializedName("temperatureUnit")
    private String mTemperatureUnit;

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

    public String getTemperatureUnit() {
        return this.mTemperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.mTemperatureUnit = temperatureUnit;
    }

    public List<String> getSupportedModes() {
        return this.mSupportedModes;
    }

    public void setSupportedModes(List<String> supportedModes) {
        this.mSupportedModes = supportedModes;
    }
}
