package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;

public class AirConditioningControl {
    @SerializedName("driver")
    private AcDriver driver;
    @SerializedName("acSpecs")
    private AcSpecs mAcSpecs;
    @SerializedName("commandConfiguration")
    private CommandConfiguration mCommandConfiguration;
    @SerializedName("id")
    private Integer mId;
    @SerializedName("type")
    private String mType;
    @SerializedName("wirelessRemote")
    private WirelessRemote mWirelessRemote;

    public String getType() {
        return this.mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public WirelessRemote getWirelessRemote() {
        return this.mWirelessRemote;
    }

    public Integer getId() {
        return this.mId;
    }

    public void setId(Integer id) {
        this.mId = id;
    }

    public CommandConfiguration getCommandConfiguration() {
        return this.mCommandConfiguration;
    }

    public AcSpecs getAcSpecs() {
        return this.mAcSpecs;
    }

    public void setAcSpecs(AcSpecs acSpecs) {
        this.mAcSpecs = acSpecs;
    }

    public AcDriver getDriver() {
        return this.driver;
    }
}
