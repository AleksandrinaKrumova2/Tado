package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.apps.common.proguard.UsedByNative;
import com.google.android.gms.internal.zzbfm;
import com.google.android.gms.internal.zzbfp;

@UsedByNative("wrapper.cc")
public final class LandmarkParcel extends zzbfm {
    public static final Creator<LandmarkParcel> CREATOR = new zzi();
    public final int type;
    private int versionCode;
    public final float f11x;
    public final float f12y;

    public LandmarkParcel(int i, float f, float f2, int i2) {
        this.versionCode = i;
        this.f11x = f;
        this.f12y = f2;
        this.type = i2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbfp.zze(parcel);
        zzbfp.zzc(parcel, 1, this.versionCode);
        zzbfp.zza(parcel, 2, this.f11x);
        zzbfp.zza(parcel, 3, this.f12y);
        zzbfp.zzc(parcel, 4, this.type);
        zzbfp.zzai(parcel, zze);
    }
}
