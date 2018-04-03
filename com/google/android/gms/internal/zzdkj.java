package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzdkj implements Creator<zzdki> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzbfn.zzd(parcel);
        while (parcel.dataPosition() < zzd) {
            zzbfn.zzb(parcel, parcel.readInt());
        }
        zzbfn.zzaf(parcel, zzd);
        return new zzdki();
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzdki[i];
    }
}
