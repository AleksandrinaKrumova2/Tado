package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.gms.internal.zzbfn;
import com.google.android.gms.vision.barcode.Barcode.GeoPoint;

public final class zzi implements Creator<GeoPoint> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        double d = 0.0d;
        int zzd = zzbfn.zzd(parcel);
        double d2 = 0.0d;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case 2:
                    d2 = zzbfn.zzn(parcel, readInt);
                    break;
                case 3:
                    d = zzbfn.zzn(parcel, readInt);
                    break;
                default:
                    zzbfn.zzb(parcel, readInt);
                    break;
            }
        }
        zzbfn.zzaf(parcel, zzd);
        return new GeoPoint(d2, d);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new GeoPoint[i];
    }
}
