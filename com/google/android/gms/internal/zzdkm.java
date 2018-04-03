package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzdkm implements Creator<zzdkl> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzbfn.zzd(parcel);
        while (parcel.dataPosition() < zzd) {
            zzbfn.zzb(parcel, parcel.readInt());
        }
        zzbfn.zzaf(parcel, zzd);
        return new zzdkl();
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzdkl[i];
    }
}
