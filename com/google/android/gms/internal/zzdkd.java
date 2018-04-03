package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzdkd extends zzeu implements zzdkc {
    zzdkd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.text.internal.client.INativeTextRecognizerCreator");
    }

    public final zzdka zza(IObjectWrapper iObjectWrapper, zzdkl com_google_android_gms_internal_zzdkl) throws RemoteException {
        zzdka com_google_android_gms_internal_zzdka;
        Parcel zzbe = zzbe();
        zzew.zza(zzbe, (IInterface) iObjectWrapper);
        zzew.zza(zzbe, (Parcelable) com_google_android_gms_internal_zzdkl);
        Parcel zza = zza(1, zzbe);
        IBinder readStrongBinder = zza.readStrongBinder();
        if (readStrongBinder == null) {
            com_google_android_gms_internal_zzdka = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizer");
            com_google_android_gms_internal_zzdka = queryLocalInterface instanceof zzdka ? (zzdka) queryLocalInterface : new zzdkb(readStrongBinder);
        }
        zza.recycle();
        return com_google_android_gms_internal_zzdka;
    }
}
