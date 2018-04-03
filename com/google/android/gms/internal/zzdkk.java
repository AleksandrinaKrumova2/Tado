package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.zzc;

public final class zzdkk extends zzdjv<zzdka> {
    private final zzdkl zzkyc;

    public zzdkk(Context context, zzdkl com_google_android_gms_internal_zzdkl) {
        super(context, "TextNativeHandle");
        this.zzkyc = com_google_android_gms_internal_zzdkl;
        zzbjv();
    }

    protected final /* synthetic */ Object zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, zzc {
        zzdkc com_google_android_gms_internal_zzdkc;
        IBinder zzhb = dynamiteModule.zzhb("com.google.android.gms.vision.text.ChimeraNativeTextRecognizerCreator");
        if (zzhb == null) {
            com_google_android_gms_internal_zzdkc = null;
        } else {
            IInterface queryLocalInterface = zzhb.queryLocalInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizerCreator");
            com_google_android_gms_internal_zzdkc = queryLocalInterface instanceof zzdkc ? (zzdkc) queryLocalInterface : new zzdkd(zzhb);
        }
        return com_google_android_gms_internal_zzdkc.zza(zzn.zzz(context), this.zzkyc);
    }

    public final zzdke[] zza(Bitmap bitmap, zzdjw com_google_android_gms_internal_zzdjw, zzdkg com_google_android_gms_internal_zzdkg) {
        if (!isOperational()) {
            return new zzdke[0];
        }
        try {
            return ((zzdka) zzbjv()).zza(zzn.zzz(bitmap), com_google_android_gms_internal_zzdjw, com_google_android_gms_internal_zzdkg);
        } catch (Throwable e) {
            Log.e("TextNativeHandle", "Error calling native text recognizer", e);
            return new zzdke[0];
        }
    }

    protected final void zzbjs() throws RemoteException {
        ((zzdka) zzbjv()).zzbjw();
    }
}
