package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;

public final class zzbgr implements Creator<zzbgo> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        zzbgh com_google_android_gms_internal_zzbgh = null;
        int i = 0;
        int zzd = zzbfn.zzd(parcel);
        String str = null;
        String str2 = null;
        boolean z = false;
        int i2 = 0;
        boolean z2 = false;
        int i3 = 0;
        int i4 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case 1:
                    i4 = zzbfn.zzg(parcel, readInt);
                    break;
                case 2:
                    i3 = zzbfn.zzg(parcel, readInt);
                    break;
                case 3:
                    z2 = zzbfn.zzc(parcel, readInt);
                    break;
                case 4:
                    i2 = zzbfn.zzg(parcel, readInt);
                    break;
                case 5:
                    z = zzbfn.zzc(parcel, readInt);
                    break;
                case 6:
                    str2 = zzbfn.zzq(parcel, readInt);
                    break;
                case 7:
                    i = zzbfn.zzg(parcel, readInt);
                    break;
                case 8:
                    str = zzbfn.zzq(parcel, readInt);
                    break;
                case 9:
                    com_google_android_gms_internal_zzbgh = (zzbgh) zzbfn.zza(parcel, readInt, zzbgh.CREATOR);
                    break;
                default:
                    zzbfn.zzb(parcel, readInt);
                    break;
            }
        }
        zzbfn.zzaf(parcel, zzd);
        return new zzbgo(i4, i3, z2, i2, z, str2, i, str, com_google_android_gms_internal_zzbgh);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbgo[i];
    }
}
