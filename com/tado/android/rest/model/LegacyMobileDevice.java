package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;

public class LegacyMobileDevice {
    @SerializedName("password")
    private String mPassword;
    @SerializedName("username")
    private String mUsername;

    public String getUsername() {
        return this.mUsername;
    }

    public void setUsername(String username) {
        this.mUsername = username;
    }

    public String getPassword() {
        return this.mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }
}
