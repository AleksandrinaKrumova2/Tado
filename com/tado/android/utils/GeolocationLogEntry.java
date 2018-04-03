package com.tado.android.utils;

import com.google.gson.GsonBuilder;

public class GeolocationLogEntry implements BaseLogEntryInterface {
    private float mAccuracy = 0.0f;
    private double mAltitude = 0.0d;
    private int mAttempts;
    private String mDate;
    private String mDebugString;
    private DeviceStateLogEntry mDeviceStateLogEntry;
    private double mLatitude = 0.0d;
    private double mLongitude = 0.0d;
    private String mMode;
    private String mReason;
    private String mSent;
    private float mSpeed = 0.0f;

    public double getLatitude() {
        return this.mLatitude;
    }

    public void setLatitude(double latitude) {
        this.mLatitude = latitude;
    }

    public double getLongitude() {
        return this.mLongitude;
    }

    public void setLongitude(double longitude) {
        this.mLongitude = longitude;
    }

    public float getAccuracy() {
        return this.mAccuracy;
    }

    public void setAccuracy(float accuracy) {
        this.mAccuracy = accuracy;
    }

    public String getDate() {
        return this.mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public String getSent() {
        return this.mSent;
    }

    public void setSent(String sent) {
        this.mSent = sent;
    }

    public String getReason() {
        return this.mReason;
    }

    public void setReason(String reason) {
        this.mReason = reason;
    }

    public DeviceStateLogEntry getDeviceStateLogEntry() {
        return this.mDeviceStateLogEntry;
    }

    public void setDeviceStateLogEntry(DeviceStateLogEntry deviceStateLogEntry) {
        this.mDeviceStateLogEntry = deviceStateLogEntry;
    }

    public byte[] getBytes() {
        return (new GsonBuilder().create().toJson((Object) this) + ",").getBytes();
    }

    public String getDebugString() {
        return this.mDebugString;
    }

    public void setDebugString(String debugString) {
        this.mDebugString = debugString;
    }

    public int getAttempts() {
        return this.mAttempts;
    }

    public void setAttempts(int attempts) {
        this.mAttempts = attempts;
    }

    public float getSpeed() {
        return this.mSpeed;
    }

    public void setSpeed(float speed) {
        this.mSpeed = speed;
    }

    public double getAltitude() {
        return this.mAltitude;
    }

    public void setAltitude(double altitude) {
        this.mAltitude = altitude;
    }

    public String getMode() {
        return this.mMode;
    }

    public void setMode(String mode) {
        this.mMode = mode;
    }
}
