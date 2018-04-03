package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzdju extends zzeu implements zzdjt {
    zzdju(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetectorCreator");
    }

    public final zzdjr zza(IObjectWrapper iObjectWrapper, zzdjo com_google_android_gms_internal_zzdjo) throws RemoteException {
        zzdjr com_google_android_gms_internal_zzdjr;
        Parcel zzbe = zzbe();
        zzew.zza(zzbe, (IInterface) iObjectWrapper);
        zzew.zza(zzbe, (Parcelable) com_google_android_gms_internal_zzdjo);
        Parcel zza = zza(1, zzbe);
        IBinder readStrongBinder = zza.readStrongBinder();
        if (readStrongBinder == null) {
            com_google_android_gms_internal_zzdjr = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
            com_google_android_gms_internal_zzdjr = queryLocalInterface instanceof zzdjr ? (zzdjr) queryLocalInterface : new zzdjs(readStrongBinder);
        }
        zza.recycle();
        return com_google_android_gms_internal_zzdjr;
    }
}
