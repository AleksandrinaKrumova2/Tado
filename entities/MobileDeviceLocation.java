package com.tado.android.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.SerializedName;

public class MobileDeviceLocation implements Parcelable {
    public static final Creator<MobileDeviceLocation> CREATOR = new C07851();
    @SerializedName("atHome")
    private boolean mAtHome;
    @SerializedName("bearingFromHome")
    private Bearing mBearing;
    @SerializedName("stale")
    private boolean mStale;
    @SerializedName("relativeDistanceFromHomeFence")
    private double relativeDistanceFromHomeFence;

    static class C07851 implements Creator<MobileDeviceLocation> {
        C07851() {
        }

        public MobileDeviceLocation createFromParcel(Parcel in) {
            return new MobileDeviceLocation(in);
        }

        public MobileDeviceLocation[] newArray(int size) {
            return new MobileDeviceLocation[size];
        }
    }

    protected MobileDeviceLocation(Parcel in) {
        boolean z;
        boolean z2 = true;
        if (in.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.mStale = z;
        if (in.readByte() == (byte) 0) {
            z2 = false;
        }
        this.mAtHome = z2;
        this.mBearing = (Bearing) in.readParcelable(getClass().getClassLoader());
        this.relativeDistanceFromHomeFence = in.readDouble();
    }

    public boolean isStale() {
        return this.mStale;
    }

    public void setStale(boolean stale) {
        this.mStale = stale;
    }

    public boolean isAtHome() {
        return this.mAtHome;
    }

    public void setAtHome(boolean atHome) {
        this.mAtHome = atHome;
    }

    public Bearing getBearing() {
        return this.mBearing;
    }

    public void setBearing(Bearing bearing) {
        this.mBearing = bearing;
    }

    public double getRelativeDistanceFromHomeFence() {
        return this.relativeDistanceFromHomeFence;
    }

    public void setRelativeDistanceFromHomeFence(double relativeDistanceFromHomeFence) {
        this.relativeDistanceFromHomeFence = relativeDistanceFromHomeFence;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i;
        int i2 = 1;
        if (this.mStale) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeByte((byte) i);
        if (!this.mAtHome) {
            i2 = 0;
        }
        dest.writeByte((byte) i2);
        dest.writeParcelable(this.mBearing, flags);
        dest.writeDouble(this.relativeDistanceFromHomeFence);
    }
}
