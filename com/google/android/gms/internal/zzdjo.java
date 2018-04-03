package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzdjo extends zzbfm {
    public static final Creator<zzdjo> CREATOR = new zzdjp();
    public int zzkwq;

    public zzdjo(int i) {
        this.zzkwq = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbfp.zze(parcel);
        zzbfp.zzc(parcel, 2, this.zzkwq);
        zzbfp.zzai(parcel, zze);
    }
}
