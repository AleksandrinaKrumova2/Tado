package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.gms.internal.zzbfn;
import com.google.android.gms.vision.barcode.Barcode.DriverLicense;

public final class zzg implements Creator<DriverLicense> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzbfn.zzd(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        String str12 = null;
        String str13 = null;
        String str14 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case 2:
                    str = zzbfn.zzq(parcel, readInt);
                    break;
                case 3:
                    str2 = zzbfn.zzq(parcel, readInt);
                    break;
                case 4:
                    str3 = zzbfn.zzq(parcel, readInt);
                    break;
                case 5:
                    str4 = zzbfn.zzq(parcel, readInt);
                    break;
                case 6:
                    str5 = zzbfn.zzq(parcel, readInt);
                    break;
                case 7:
                    str6 = zzbfn.zzq(parcel, readInt);
                    break;
                case 8:
                    str7 = zzbfn.zzq(parcel, readInt);
                    break;
                case 9:
                    str8 = zzbfn.zzq(parcel, readInt);
                    break;
                case 10:
                    str9 = zzbfn.zzq(parcel, readInt);
                    break;
                case 11:
                    str10 = zzbfn.zzq(parcel, readInt);
                    break;
                case 12:
                    str11 = zzbfn.zzq(parcel, readInt);
                    break;
                case 13:
                    str12 = zzbfn.zzq(parcel, readInt);
                    break;
                case 14:
                    str13 = zzbfn.zzq(parcel, readInt);
                    break;
                case 15:
                    str14 = zzbfn.zzq(parcel, readInt);
                    break;
                default:
                    zzbfn.zzb(parcel, readInt);
                    break;
            }
        }
        zzbfn.zzaf(parcel, zzd);
        return new DriverLicense(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new DriverLicense[i];
    }
}
