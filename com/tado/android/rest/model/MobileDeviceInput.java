package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;

public class MobileDeviceInput {
    @SerializedName("metadata")
    private MobileDeviceMetadata mMetadata;
    @SerializedName("name")
    private String mName;
    @SerializedName("settings")
    private MobileDeviceSettings mSettings;

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public MobileDeviceMetadata getMetadata() {
        return this.mMetadata;
    }

    public void setMetadata(MobileDeviceMetadata metadata) {
        this.mMetadata = metadata;
    }

    public MobileDeviceSettings getSettings() {
        return this.mSettings;
    }

    public void setSettings(MobileDeviceSettings settings) {
        this.mSettings = settings;
    }
}
