package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
import java.util.Map;

public final class zzars extends zzeu implements zzarr {
    zzars(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.analytics.internal.IAnalyticsService");
    }

    public final void zza(Map map, long j, String str, List<zzaqx> list) throws RemoteException {
        Parcel zzbe = zzbe();
        zzbe.writeMap(map);
        zzbe.writeLong(j);
        zzbe.writeString(str);
        zzbe.writeTypedList(list);
        zzb(1, zzbe);
    }

    public final void zzwm() throws RemoteException {
        zzb(2, zzbe());
    }
}
