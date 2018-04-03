package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;

public final class zzdkf implements Creator<zzdke> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        String str = null;
        int zzd = zzbfn.zzd(parcel);
        float f = 0.0f;
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        String str2 = null;
        zzdjy com_google_android_gms_internal_zzdjy = null;
        zzdjy com_google_android_gms_internal_zzdjy2 = null;
        zzdjy com_google_android_gms_internal_zzdjy3 = null;
        zzdkn[] com_google_android_gms_internal_zzdknArr = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case 2:
                    com_google_android_gms_internal_zzdknArr = (zzdkn[]) zzbfn.zzb(parcel, readInt, zzdkn.CREATOR);
                    break;
                case 3:
                    com_google_android_gms_internal_zzdjy3 = (zzdjy) zzbfn.zza(parcel, readInt, zzdjy.CREATOR);
                    break;
                case 4:
                    com_google_android_gms_internal_zzdjy2 = (zzdjy) zzbfn.zza(parcel, readInt, zzdjy.CREATOR);
                    break;
                case 5:
                    com_google_android_gms_internal_zzdjy = (zzdjy) zzbfn.zza(parcel, readInt, zzdjy.CREATOR);
                    break;
                case 6:
                    str2 = zzbfn.zzq(parcel, readInt);
                    break;
                case 7:
                    f = zzbfn.zzl(parcel, readInt);
                    break;
                case 8:
                    str = zzbfn.zzq(parcel, readInt);
                    break;
                case 9:
                    i3 = zzbfn.zzg(parcel, readInt);
                    break;
                case 10:
                    z = zzbfn.zzc(parcel, readInt);
                    break;
                case 11:
                    i2 = zzbfn.zzg(parcel, readInt);
                    break;
                case 12:
                    i = zzbfn.zzg(parcel, readInt);
                    break;
                default:
                    zzbfn.zzb(parcel, readInt);
                    break;
            }
        }
        zzbfn.zzaf(parcel, zzd);
        return new zzdke(com_google_android_gms_internal_zzdknArr, com_google_android_gms_internal_zzdjy3, com_google_android_gms_internal_zzdjy2, com_google_android_gms_internal_zzdjy, str2, f, str, i3, z, i2, i);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzdke[i];
    }
}
