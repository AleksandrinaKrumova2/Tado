package com.tado.android.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.SerializedName;

public class Bearing implements Parcelable {
    public static final Creator<Bearing> CREATOR = new C07831();
    @SerializedName("degrees")
    private double mDegrees;
    @SerializedName("radians")
    private double mRadians;

    static class C07831 implements Creator<Bearing> {
        C07831() {
        }

        public Bearing createFromParcel(Parcel in) {
            return new Bearing(in);
        }

        public Bearing[] newArray(int size) {
            return new Bearing[size];
        }
    }

    protected Bearing(Parcel in) {
        this.mDegrees = in.readDouble();
        this.mRadians = in.readDouble();
    }

    public double getDegrees() {
        return this.mDegrees;
    }

    public void setDegrees(double degrees) {
        this.mDegrees = degrees;
    }

    public double getRadians() {
        return this.mRadians;
    }

    public void setRadians(double radians) {
        this.mRadians = radians;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.mDegrees);
        dest.writeDouble(this.mRadians);
    }
}
