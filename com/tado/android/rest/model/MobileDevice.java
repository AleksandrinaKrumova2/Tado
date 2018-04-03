package com.tado.android.rest.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import com.tado.android.entities.MobileDeviceLocation;
import java.text.Collator;
import java.util.Locale;

public class MobileDevice implements Parcelable, Comparable<MobileDevice> {
    public static final Creator<MobileDevice> CREATOR = new C10681();
    @SerializedName("deviceMetadata")
    private MobileDeviceDeviceMetadata mDeviceMetadata;
    @SerializedName("id")
    private int mId;
    @SerializedName("location")
    private MobileDeviceLocation mLocation;
    @SerializedName("name")
    private String mName;
    @SerializedName("settings")
    private MobileDeviceSettings mSettings;

    static class C10681 implements Creator<MobileDevice> {
        C10681() {
        }

        public MobileDevice createFromParcel(Parcel in) {
            return new MobileDevice(in);
        }

        public MobileDevice[] newArray(int size) {
            return new MobileDevice[size];
        }
    }

    public MobileDevice(String mName) {
        this.mName = mName;
    }

    protected MobileDevice(Parcel in) {
        this.mId = in.readInt();
        this.mName = in.readString();
        this.mSettings = (MobileDeviceSettings) in.readParcelable(getClass().getClassLoader());
        this.mLocation = (MobileDeviceLocation) in.readParcelable(getClass().getClassLoader());
        this.mDeviceMetadata = (MobileDeviceDeviceMetadata) in.readParcelable(getClass().getClassLoader());
    }

    public int getId() {
        return this.mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public MobileDeviceSettings getSettings() {
        return this.mSettings;
    }

    public void setSettings(MobileDeviceSettings settings) {
        this.mSettings = settings;
    }

    public MobileDeviceLocation getLocation() {
        return this.mLocation;
    }

    public void setLocation(MobileDeviceLocation location) {
        this.mLocation = location;
    }

    public MobileDeviceDeviceMetadata getDeviceMetadata() {
        return this.mDeviceMetadata;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mId);
        dest.writeString(this.mName);
        dest.writeParcelable(this.mSettings, flags);
        dest.writeParcelable(this.mLocation, flags);
        dest.writeParcelable(this.mDeviceMetadata, flags);
    }

    public int compareTo(@NonNull MobileDevice o) {
        Collator collator = Collator.getInstance(Locale.US);
        collator.setStrength(0);
        return collator.compare(getName(), o.getName());
    }
}
