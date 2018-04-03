package com.tado.android.rest.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.SerializedName;

public class Invitation implements Parcelable {
    public static final Creator<Invitation> CREATOR = new C10671();
    @SerializedName("email")
    String email;
    @SerializedName("firstSent")
    String firstSent;
    @SerializedName("home")
    HomeInfo home;
    @SerializedName("inviter")
    User inviter;
    @SerializedName("lastSent")
    String lastSent;
    @SerializedName("token")
    String token;

    static class C10671 implements Creator<Invitation> {
        C10671() {
        }

        public Invitation createFromParcel(Parcel in) {
            return new Invitation(in);
        }

        public Invitation[] newArray(int size) {
            return new Invitation[size];
        }
    }

    public Invitation(String email) {
        this.email = email;
    }

    protected Invitation(Parcel in) {
        this.token = in.readString();
        this.email = in.readString();
        this.firstSent = in.readString();
        this.lastSent = in.readString();
        this.inviter = (User) in.readParcelable(User.class.getClassLoader());
        this.home = (HomeInfo) in.readParcelable(HomeInfo.class.getClassLoader());
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
        dest.writeString(this.email);
        dest.writeString(this.firstSent);
        dest.writeString(this.lastSent);
        dest.writeParcelable(this.inviter, flags);
        dest.writeParcelable(this.home, flags);
    }

    public int describeContents() {
        return 0;
    }

    public String getToken() {
        return this.token;
    }

    public String getEmail() {
        return this.email;
    }

    public String getFirstSent() {
        return this.firstSent;
    }

    public String getLastSent() {
        return this.lastSent;
    }

    public User getInviter() {
        return this.inviter;
    }

    public HomeInfo getHome() {
        return this.home;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isValid() {
        return this.email != null;
    }
}
