package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import io.fabric.sdk.android.services.common.AdvertisingInfoServiceStrategy.AdvertisingInterface;

public abstract class zzfp extends zzev implements zzfo {
    public static zzfo zzc(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface(AdvertisingInterface.ADVERTISING_ID_SERVICE_INTERFACE_TOKEN);
        return queryLocalInterface instanceof zzfo ? (zzfo) queryLocalInterface : new zzfq(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        throw new NoSuchMethodError();
    }
}
