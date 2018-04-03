package com.tado.android.rest.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;
import com.tado.android.entities.Address;
import com.tado.android.rest.model.installation.TemperatureUnitEnum;
import java.util.Date;

public class HomeInfo implements Parcelable {
    public static final Creator<HomeInfo> CREATOR = new C10651();
    private static final int DIAGRAL_IMAGE_RESOURCE_CODE = 1;
    private static final int ENTEGA_IMAGE_RESOURCE_CODE = 5;
    private static final int GNF_IMAGE_RESOURCE_CODE = 6;
    private static final int HAGER_IMAGE_RESOURCE_CODE = 4;
    private static final int HOMESERVE_IMAGE_RESOURCE_CODE = 2;
    private static final int TADO_IMAGE_RESOURCE_CODE = 0;
    @SerializedName("awayRadiusInMeters")
    private float awayRadiusInMeters;
    @SerializedName("contactDetails")
    private ContactDetails contactDetails;
    @SerializedName("dateCreated")
    private Date dateCreated;
    @SerializedName("geolocation")
    private LatLng geolocation;
    @SerializedName("license")
    private LicenseEnum license;
    @SerializedName("address")
    private Address mAddress;
    @SerializedName("christmasModeEnabled")
    private boolean mChristmasModeEnabled;
    @SerializedName("dateTimeZone")
    private String mDateTimeZone;
    @SerializedName("id")
    private int mId;
    @SerializedName("installationCompleted")
    private boolean mInstallationCompleted;
    @SerializedName("name")
    private String mName;
    @SerializedName("partner")
    private String mPartner;
    @SerializedName("temperatureUnit")
    private String mTemperatureUnit;

    static class C10651 implements Creator<HomeInfo> {
        C10651() {
        }

        public HomeInfo createFromParcel(Parcel in) {
            return new HomeInfo(in);
        }

        public HomeInfo[] newArray(int size) {
            return new HomeInfo[size];
        }
    }

    public enum LicenseEnum {
        STANDARD,
        PREMIUM,
        NON_PREMIUM
    }

    public enum PartnerEnum {
        UNKNOWN(0),
        NONE(0),
        DIAGRAL(1),
        HOMESERVE(2),
        HAGER(4),
        ENTEGA(5),
        GAS_NATURAL_FENOSA(6);
        
        private final int code;

        private PartnerEnum(int code) {
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }
    }

    @NonNull
    public PartnerEnum getPartner() {
        try {
            return PartnerEnum.valueOf(this.mPartner);
        } catch (IllegalArgumentException e) {
            return PartnerEnum.UNKNOWN;
        } catch (NullPointerException e2) {
            return PartnerEnum.NONE;
        }
    }

    public void setPartner(String mPartner) {
        this.mPartner = mPartner;
    }

    public void writeToParcel(@NonNull Parcel dest, int flags) {
        int i;
        int i2 = 1;
        dest.writeInt(this.mId);
        dest.writeString(this.mName);
        dest.writeString(this.mDateTimeZone);
        dest.writeString(this.mTemperatureUnit);
        dest.writeByte((byte) (this.mInstallationCompleted ? 1 : 0));
        dest.writeString(this.mPartner);
        dest.writeSerializable(this.mAddress);
        if (this.mChristmasModeEnabled) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeByte((byte) i);
        dest.writeFloat(this.awayRadiusInMeters);
        if (this.geolocation != null) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeByte((byte) i);
        if (this.geolocation != null) {
            dest.writeDouble(this.geolocation.latitude);
            dest.writeDouble(this.geolocation.longitude);
        }
        if (this.contactDetails == null) {
            i2 = 0;
        }
        dest.writeByte((byte) i2);
        if (this.contactDetails != null) {
            dest.writeString(this.contactDetails.getName());
            dest.writeString(this.contactDetails.getEmail());
            dest.writeString(this.contactDetails.getPhone());
        }
        dest.writeLong(this.dateCreated.getTime());
    }

    public HomeInfo() {
        this.awayRadiusInMeters = 400.0f;
    }

    private HomeInfo(Parcel in) {
        boolean z = false;
        this.awayRadiusInMeters = 400.0f;
        this.mId = in.readInt();
        this.mName = in.readString();
        this.mDateTimeZone = in.readString();
        this.mTemperatureUnit = in.readString();
        this.mInstallationCompleted = in.readByte() != (byte) 0;
        this.mPartner = in.readString();
        this.mAddress = (Address) in.readSerializable();
        if (in.readByte() != (byte) 0) {
            z = true;
        }
        this.mChristmasModeEnabled = z;
        this.awayRadiusInMeters = in.readFloat();
        if (in.readByte() == (byte) 1) {
            this.geolocation = new LatLng(in.readDouble(), in.readDouble());
        }
        if (in.readByte() == (byte) 1) {
            this.contactDetails = new ContactDetails(in.readString(), in.readString(), in.readString());
        }
        this.dateCreated = new Date(in.readLong());
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

    public String getDateTimeZone() {
        return this.mDateTimeZone;
    }

    public void setDateTimeZone(String dateTimeZone) {
        this.mDateTimeZone = dateTimeZone;
    }

    public TemperatureUnitEnum getTemperatureUnit() {
        if (this.mTemperatureUnit != null) {
            return TemperatureUnitEnum.valueOf(this.mTemperatureUnit);
        }
        return null;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.mTemperatureUnit = temperatureUnit;
    }

    public boolean isInstallationCompleted() {
        return this.mInstallationCompleted;
    }

    public void setInstallationCompleted(boolean installationCompleted) {
        this.mInstallationCompleted = installationCompleted;
    }

    public Address getAddress() {
        return this.mAddress;
    }

    public void setAddress(Address address) {
        this.mAddress = address;
    }

    public boolean isChristmasModeEnabled() {
        return this.mChristmasModeEnabled;
    }

    public float getAwayRadiusInMeters() {
        return this.awayRadiusInMeters;
    }

    public LatLng getGeolocation() {
        return this.geolocation;
    }

    public ContactDetails getContactDetails() {
        return this.contactDetails;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }

    public int describeContents() {
        return 0;
    }

    public LicenseEnum getLicense() {
        if (this.license == null) {
            return LicenseEnum.STANDARD;
        }
        return this.license;
    }
}
