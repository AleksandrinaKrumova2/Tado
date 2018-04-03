package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzdkb extends zzeu implements zzdka {
    zzdkb(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.text.internal.client.INativeTextRecognizer");
    }

    public final zzdke[] zza(IObjectWrapper iObjectWrapper, zzdjw com_google_android_gms_internal_zzdjw, zzdkg com_google_android_gms_internal_zzdkg) throws RemoteException {
        Parcel zzbe = zzbe();
        zzew.zza(zzbe, (IInterface) iObjectWrapper);
        zzew.zza(zzbe, (Parcelable) com_google_android_gms_internal_zzdjw);
        zzew.zza(zzbe, (Parcelable) com_google_android_gms_internal_zzdkg);
        Parcel zza = zza(3, zzbe);
        zzdke[] com_google_android_gms_internal_zzdkeArr = (zzdke[]) zza.createTypedArray(zzdke.CREATOR);
        zza.recycle();
        return com_google_android_gms_internal_zzdkeArr;
    }

    public final void zzbjw() throws RemoteException {
        zzb(2, zzbe());
    }
}
