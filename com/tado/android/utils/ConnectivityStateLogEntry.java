package com.tado.android.utils;

import android.net.NetworkInfo.State;

public class ConnectivityStateLogEntry {
    private String mCurrentWifi;
    private String mReason;
    private State mState;
    private String mSubtypeName;
    private String mTypeName;

    public String getReason() {
        return this.mReason;
    }

    public void setReason(String reason) {
        this.mReason = reason;
    }

    public String getSubtypeName() {
        return this.mSubtypeName;
    }

    public void setSubtypeName(String subtypeName) {
        this.mSubtypeName = subtypeName;
    }

    public String getTypeName() {
        return this.mTypeName;
    }

    public void setTypeName(String typeName) {
        this.mTypeName = typeName;
    }

    public String getCurrentWifi() {
        return this.mCurrentWifi;
    }

    public void setCurrentWifi(String currentWifi) {
        this.mCurrentWifi = currentWifi;
    }

    public State getState() {
        return this.mState;
    }

    public void setState(State state) {
        this.mState = state;
    }
}
