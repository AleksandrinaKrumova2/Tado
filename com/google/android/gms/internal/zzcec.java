package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzcm;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

final class zzcec extends zzcem {
    private /* synthetic */ LocationRequest zzild;
    private /* synthetic */ LocationListener zzile;

    zzcec(zzceb com_google_android_gms_internal_zzceb, GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationListener locationListener) {
        this.zzild = locationRequest;
        this.zzile = locationListener;
        super(googleApiClient);
    }

    protected final /* synthetic */ void zza(zzb com_google_android_gms_common_api_Api_zzb) throws RemoteException {
        ((zzcfk) com_google_android_gms_common_api_Api_zzb).zza(this.zzild, zzcm.zzb(this.zzile, zzcgc.zzavy(), LocationListener.class.getSimpleName()), new zzcen(this));
    }
}
