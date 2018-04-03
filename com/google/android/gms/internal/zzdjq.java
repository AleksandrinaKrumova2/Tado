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
import com.google.android.gms.vision.barcode.Barcode;
import java.nio.ByteBuffer;

public final class zzdjq extends zzdjv<zzdjr> {
    private final zzdjo zzkwp;

    public zzdjq(Context context, zzdjo com_google_android_gms_internal_zzdjo) {
        super(context, "BarcodeNativeHandle");
        this.zzkwp = com_google_android_gms_internal_zzdjo;
        zzbjv();
    }

    protected final /* synthetic */ Object zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, zzc {
        zzdjt com_google_android_gms_internal_zzdjt;
        IBinder zzhb = dynamiteModule.zzhb("com.google.android.gms.vision.barcode.ChimeraNativeBarcodeDetectorCreator");
        if (zzhb == null) {
            com_google_android_gms_internal_zzdjt = null;
        } else {
            IInterface queryLocalInterface = zzhb.queryLocalInterface("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetectorCreator");
            com_google_android_gms_internal_zzdjt = queryLocalInterface instanceof zzdjt ? (zzdjt) queryLocalInterface : new zzdju(zzhb);
        }
        return com_google_android_gms_internal_zzdjt == null ? null : com_google_android_gms_internal_zzdjt.zza(zzn.zzz(context), this.zzkwp);
    }

    public final Barcode[] zza(Bitmap bitmap, zzdjw com_google_android_gms_internal_zzdjw) {
        if (!isOperational()) {
            return new Barcode[0];
        }
        try {
            return ((zzdjr) zzbjv()).zzb(zzn.zzz(bitmap), com_google_android_gms_internal_zzdjw);
        } catch (Throwable e) {
            Log.e("BarcodeNativeHandle", "Error calling native barcode detector", e);
            return new Barcode[0];
        }
    }

    public final Barcode[] zza(ByteBuffer byteBuffer, zzdjw com_google_android_gms_internal_zzdjw) {
        if (!isOperational()) {
            return new Barcode[0];
        }
        try {
            return ((zzdjr) zzbjv()).zza(zzn.zzz(byteBuffer), com_google_android_gms_internal_zzdjw);
        } catch (Throwable e) {
            Log.e("BarcodeNativeHandle", "Error calling native barcode detector", e);
            return new Barcode[0];
        }
    }

    protected final void zzbjs() throws RemoteException {
        if (isOperational()) {
            ((zzdjr) zzbjv()).zzbjt();
        }
    }
}
