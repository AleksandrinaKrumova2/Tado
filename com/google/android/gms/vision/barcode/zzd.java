package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.gms.internal.zzbfn;
import com.google.android.gms.vision.barcode.Barcode.CalendarDateTime;

public final class zzd implements Creator<CalendarDateTime> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        boolean z = false;
        int zzd = zzbfn.zzd(parcel);
        String str = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case 2:
                    i6 = zzbfn.zzg(parcel, readInt);
                    break;
                case 3:
                    i5 = zzbfn.zzg(parcel, readInt);
                    break;
                case 4:
                    i4 = zzbfn.zzg(parcel, readInt);
                    break;
                case 5:
                    i3 = zzbfn.zzg(parcel, readInt);
                    break;
                case 6:
                    i2 = zzbfn.zzg(parcel, readInt);
                    break;
                case 7:
                    i = zzbfn.zzg(parcel, readInt);
                    break;
                case 8:
                    z = zzbfn.zzc(parcel, readInt);
                    break;
                case 9:
                    str = zzbfn.zzq(parcel, readInt);
                    break;
                default:
                    zzbfn.zzb(parcel, readInt);
                    break;
            }
        }
        zzbfn.zzaf(parcel, zzd);
        return new CalendarDateTime(i6, i5, i4, i3, i2, i, z, str);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new CalendarDateTime[i];
    }
}
