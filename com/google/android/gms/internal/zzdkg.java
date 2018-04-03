package com.google.android.gms.internal;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzdkg extends zzbfm {
    public static final Creator<zzdkg> CREATOR = new zzdkh();
    public final Rect zzkyo;

    public zzdkg() {
        this.zzkyo = new Rect();
    }

    public zzdkg(Rect rect) {
        this.zzkyo = rect;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbfp.zze(parcel);
        zzbfp.zza(parcel, 2, this.zzkyo, i, false);
        zzbfp.zzai(parcel, zze);
    }
}
