package com.tado.android.rest.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.SerializedName;

public class UserHome implements Parcelable {
    public static final Creator<UserHome> CREATOR = new C10721();
    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;

    static class C10721 implements Creator<UserHome> {
        C10721() {
        }

        public UserHome createFromParcel(Parcel in) {
            return new UserHome(in);
        }

        public UserHome[] newArray(int size) {
            return new UserHome[size];
        }
    }

    protected UserHome(Parcel in) {
        this.mId = in.readInt();
        this.mName = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mId);
        dest.writeString(this.mName);
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
}
