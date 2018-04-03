package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzdjy extends zzbfm {
    public static final Creator<zzdjy> CREATOR = new zzdjz();
    public final int height;
    public final int left;
    public final int top;
    public final int width;
    public final float zzkyd;

    public zzdjy(int i, int i2, int i3, int i4, float f) {
        this.left = i;
        this.top = i2;
        this.width = i3;
        this.height = i4;
        this.zzkyd = f;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbfp.zze(parcel);
        zzbfp.zzc(parcel, 2, this.left);
        zzbfp.zzc(parcel, 3, this.top);
        zzbfp.zzc(parcel, 4, this.width);
        zzbfp.zzc(parcel, 5, this.height);
        zzbfp.zza(parcel, 6, this.zzkyd);
        zzbfp.zzai(parcel, zze);
    }
}
