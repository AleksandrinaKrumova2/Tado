package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;

public final class zzdko implements Creator<zzdkn> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        int zzd = zzbfn.zzd(parcel);
        float f = 0.0f;
        boolean z = false;
        String str2 = null;
        zzdjy com_google_android_gms_internal_zzdjy = null;
        zzdjy com_google_android_gms_internal_zzdjy2 = null;
        zzdki[] com_google_android_gms_internal_zzdkiArr = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case 2:
                    com_google_android_gms_internal_zzdkiArr = (zzdki[]) zzbfn.zzb(parcel, readInt, zzdki.CREATOR);
                    break;
                case 3:
                    com_google_android_gms_internal_zzdjy2 = (zzdjy) zzbfn.zza(parcel, readInt, zzdjy.CREATOR);
                    break;
                case 4:
                    com_google_android_gms_internal_zzdjy = (zzdjy) zzbfn.zza(parcel, readInt, zzdjy.CREATOR);
                    break;
                case 5:
                    str2 = zzbfn.zzq(parcel, readInt);
                    break;
                case 6:
                    f = zzbfn.zzl(parcel, readInt);
                    break;
                case 7:
                    str = zzbfn.zzq(parcel, readInt);
                    break;
                case 8:
                    z = zzbfn.zzc(parcel, readInt);
                    break;
                default:
                    zzbfn.zzb(parcel, readInt);
                    break;
            }
        }
        zzbfn.zzaf(parcel, zzd);
        return new zzdkn(com_google_android_gms_internal_zzdkiArr, com_google_android_gms_internal_zzdjy2, com_google_android_gms_internal_zzdjy, str2, f, str, z);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzdkn[i];
    }
}
