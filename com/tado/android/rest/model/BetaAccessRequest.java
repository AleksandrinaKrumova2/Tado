package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;

public class BetaAccessRequest {
    @SerializedName("email")
    private String mEmail;
    @SerializedName("firstName")
    private String mFirstName;
    @SerializedName("lastName")
    private String mLastName;

    public String getEmail() {
        return this.mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public String getFirstName() {
        return this.mFirstName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public String getLastName() {
        return this.mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }
}
