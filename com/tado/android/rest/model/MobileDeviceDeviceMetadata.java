package com.tado.android.rest.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.SerializedName;

public class MobileDeviceDeviceMetadata implements Parcelable {
    public static final Creator<MobileDeviceDeviceMetadata> CREATOR = new C10691();
    @SerializedName("locale")
    private String mLocale;
    @SerializedName("model")
    private String mModel;
    @SerializedName("osVersion")
    private String mOsVersion;
    @SerializedName("platform")
    private String mPlatform;

    static class C10691 implements Creator<MobileDeviceDeviceMetadata> {
        C10691() {
        }

        public MobileDeviceDeviceMetadata createFromParcel(Parcel in) {
            return new MobileDeviceDeviceMetadata(in);
        }

        public MobileDeviceDeviceMetadata[] newArray(int size) {
            return new MobileDeviceDeviceMetadata[size];
        }
    }

    protected MobileDeviceDeviceMetadata(Parcel in) {
        this.mModel = in.readString();
        this.mPlatform = in.readString();
        this.mOsVersion = in.readString();
        this.mLocale = in.readString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mModel);
        dest.writeString(this.mPlatform);
        dest.writeString(this.mOsVersion);
        dest.writeString(this.mLocale);
    }

    public int describeContents() {
        return 0;
    }

    public String getModel() {
        return this.mModel;
    }

    public void setModel(String model) {
        this.mModel = model;
    }

    public String getPlatform() {
        return this.mPlatform;
    }

    public void setPlatform(String platform) {
        this.mPlatform = platform;
    }

    public String getOsVersion() {
        return this.mOsVersion;
    }

    public void setOsVersion(String osVersion) {
        this.mOsVersion = osVersion;
    }

    public String getLocale() {
        return this.mLocale;
    }

    public void setLocale(String locale) {
        this.mLocale = locale;
    }
}
