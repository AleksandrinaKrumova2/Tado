package com.tado.android.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Link implements Serializable {
    public static final String OFFLINE = "OFFLINE";
    public static final String ONLINE = "ONLINE";
    @SerializedName("reason")
    @Expose
    private LinkReason mReason;
    @SerializedName("state")
    @Expose
    private String mState = OFFLINE;

    public LinkReason getReason() {
        return this.mReason;
    }

    public void setReason(LinkReason reason) {
        this.mReason = reason;
    }

    public String getState() {
        return this.mState;
    }

    public void setState(String state) {
        this.mState = state;
    }

    public boolean isOnline() {
        return this.mState.equalsIgnoreCase(ONLINE);
    }
}
