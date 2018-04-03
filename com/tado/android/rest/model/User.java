package com.tado.android.rest.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User implements Parcelable {
    public static final Creator<User> CREATOR = new C10711();
    @SerializedName("email")
    private String mEmail;
    @SerializedName("homes")
    private List<UserHome> mHomes;
    @SerializedName("locale")
    private String mLocale;
    @SerializedName("mobileDevices")
    private List<MobileDevice> mMobileDevices = new ArrayList();
    @SerializedName("name")
    private String mName;
    @SerializedName("username")
    private String mUsername;

    static class C10711 implements Creator<User> {
        C10711() {
        }

        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    }

    protected User(Parcel in) {
        this.mEmail = in.readString();
        this.mName = in.readString();
        this.mUsername = in.readString();
        this.mHomes = in.createTypedArrayList(UserHome.CREATOR);
        this.mLocale = in.readString();
        this.mMobileDevices = in.createTypedArrayList(MobileDevice.CREATOR);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mEmail);
        dest.writeString(this.mName);
        dest.writeString(this.mUsername);
        dest.writeTypedList(this.mHomes);
        dest.writeString(this.mLocale);
        dest.writeTypedList(this.mMobileDevices);
    }

    public int describeContents() {
        return 0;
    }

    public String getEmail() {
        return this.mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getUsername() {
        return this.mUsername;
    }

    public void setUsername(String username) {
        this.mUsername = username;
    }

    @NonNull
    public List<UserHome> getHomes() {
        return this.mHomes != null ? this.mHomes : Collections.emptyList();
    }

    public String getLocale() {
        return this.mLocale;
    }

    public void setLocale(String locale) {
        this.mLocale = locale;
    }

    public List<MobileDevice> getMobileDevices() {
        return this.mMobileDevices;
    }
}
