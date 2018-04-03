package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzdkn extends zzbfm {
    public static final Creator<zzdkn> CREATOR = new zzdko();
    public final String zzkxz;
    public final zzdjy zzkyf;
    private zzdjy zzkyg;
    public final String zzkyi;
    private float zzkyj;
    private zzdki[] zzkyp;
    private boolean zzkyq;

    public zzdkn(zzdki[] com_google_android_gms_internal_zzdkiArr, zzdjy com_google_android_gms_internal_zzdjy, zzdjy com_google_android_gms_internal_zzdjy2, String str, float f, String str2, boolean z) {
        this.zzkyp = com_google_android_gms_internal_zzdkiArr;
        this.zzkyf = com_google_android_gms_internal_zzdjy;
        this.zzkyg = com_google_android_gms_internal_zzdjy2;
        this.zzkyi = str;
        this.zzkyj = f;
        this.zzkxz = str2;
        this.zzkyq = z;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbfp.zze(parcel);
        zzbfp.zza(parcel, 2, this.zzkyp, i, false);
        zzbfp.zza(parcel, 3, this.zzkyf, i, false);
        zzbfp.zza(parcel, 4, this.zzkyg, i, false);
        zzbfp.zza(parcel, 5, this.zzkyi, false);
        zzbfp.zza(parcel, 6, this.zzkyj);
        zzbfp.zza(parcel, 7, this.zzkxz, false);
        zzbfp.zza(parcel, 8, this.zzkyq);
        zzbfp.zzai(parcel, zze);
    }
}
