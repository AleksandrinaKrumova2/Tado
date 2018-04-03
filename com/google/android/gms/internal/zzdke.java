package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzdke extends zzbfm {
    public static final Creator<zzdke> CREATOR = new zzdkf();
    public final String zzkxz;
    public final zzdkn[] zzkye;
    public final zzdjy zzkyf;
    private zzdjy zzkyg;
    private zzdjy zzkyh;
    public final String zzkyi;
    private float zzkyj;
    private int zzkyk;
    public final boolean zzkyl;
    public final int zzkym;
    public final int zzkyn;

    public zzdke(zzdkn[] com_google_android_gms_internal_zzdknArr, zzdjy com_google_android_gms_internal_zzdjy, zzdjy com_google_android_gms_internal_zzdjy2, zzdjy com_google_android_gms_internal_zzdjy3, String str, float f, String str2, int i, boolean z, int i2, int i3) {
        this.zzkye = com_google_android_gms_internal_zzdknArr;
        this.zzkyf = com_google_android_gms_internal_zzdjy;
        this.zzkyg = com_google_android_gms_internal_zzdjy2;
        this.zzkyh = com_google_android_gms_internal_zzdjy3;
        this.zzkyi = str;
        this.zzkyj = f;
        this.zzkxz = str2;
        this.zzkyk = i;
        this.zzkyl = z;
        this.zzkym = i2;
        this.zzkyn = i3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbfp.zze(parcel);
        zzbfp.zza(parcel, 2, this.zzkye, i, false);
        zzbfp.zza(parcel, 3, this.zzkyf, i, false);
        zzbfp.zza(parcel, 4, this.zzkyg, i, false);
        zzbfp.zza(parcel, 5, this.zzkyh, i, false);
        zzbfp.zza(parcel, 6, this.zzkyi, false);
        zzbfp.zza(parcel, 7, this.zzkyj);
        zzbfp.zza(parcel, 8, this.zzkxz, false);
        zzbfp.zzc(parcel, 9, this.zzkyk);
        zzbfp.zza(parcel, 10, this.zzkyl);
        zzbfp.zzc(parcel, 11, this.zzkym);
        zzbfp.zzc(parcel, 12, this.zzkyn);
        zzbfp.zzai(parcel, zze);
    }
}
