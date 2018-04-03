package com.tado.android.entities;

import com.google.gson.annotations.SerializedName;

public class MobileDevices {
    private String etag;
    @SerializedName("geoTrackingEnabled")
    private boolean mGeoTrackingEnabled;
    @SerializedName("location")
    private MobileDeviceLocation mLocation;
    @SerializedName("name")
    private String mName;
    @SerializedName("username")
    private String mUserName;

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getUserName() {
        return this.mUserName;
    }

    public void setUserName(String userName) {
        this.mUserName = userName;
    }

    public boolean isGeoTrackingEnabled() {
        return this.mGeoTrackingEnabled;
    }

    public void setGeoTrackingEnabled(boolean geoTrackingEnabled) {
        this.mGeoTrackingEnabled = geoTrackingEnabled;
    }

    public MobileDeviceLocation getLocation() {
        return this.mLocation;
    }

    public void setLocation(MobileDeviceLocation location) {
        this.mLocation = location;
    }

    public String getEtag() {
        return this.etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }
}
