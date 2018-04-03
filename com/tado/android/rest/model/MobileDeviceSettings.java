package com.tado.android.rest.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.SerializedName;

public class MobileDeviceSettings implements Parcelable {
    public static final Creator<MobileDeviceSettings> CREATOR = new C10701();
    @SerializedName("geoTrackingEnabled")
    private boolean mGeoTrackingEnabled;

    static class C10701 implements ClassLoaderCreator<MobileDeviceSettings> {
        C10701() {
        }

        public MobileDeviceSettings createFromParcel(Parcel source, ClassLoader loader) {
            return new MobileDeviceSettings(source);
        }

        public MobileDeviceSettings createFromParcel(Parcel source) {
            return new MobileDeviceSettings(source);
        }

        public MobileDeviceSettings[] newArray(int size) {
            return new MobileDeviceSettings[0];
        }
    }

    public MobileDeviceSettings(boolean mGeoTrackingEnabled) {
        this.mGeoTrackingEnabled = mGeoTrackingEnabled;
    }

    private MobileDeviceSettings(Parcel source) {
        this.mGeoTrackingEnabled = source.readByte() != (byte) 0;
    }

    public boolean isGeoTrackingEnabled() {
        return this.mGeoTrackingEnabled;
    }

    public void setGeoTrackingEnabled(boolean geoTrackingEnabled) {
        this.mGeoTrackingEnabled = geoTrackingEnabled;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (this.mGeoTrackingEnabled ? 1 : 0));
    }
}
