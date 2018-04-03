package com.google.android.gms.vision.barcode;

import android.content.Context;
import android.util.SparseArray;
import com.google.android.gms.internal.zzdjo;
import com.google.android.gms.internal.zzdjq;
import com.google.android.gms.internal.zzdjw;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;

public final class BarcodeDetector extends Detector<Barcode> {
    private final zzdjq zzkwo;

    public static class Builder {
        private Context mContext;
        private zzdjo zzkwp = new zzdjo();

        public Builder(Context context) {
            this.mContext = context;
        }

        public BarcodeDetector build() {
            return new BarcodeDetector(new zzdjq(this.mContext, this.zzkwp));
        }

        public Builder setBarcodeFormats(int i) {
            this.zzkwp.zzkwq = i;
            return this;
        }
    }

    private BarcodeDetector() {
        throw new IllegalStateException("Default constructor called");
    }

    private BarcodeDetector(zzdjq com_google_android_gms_internal_zzdjq) {
        this.zzkwo = com_google_android_gms_internal_zzdjq;
    }

    public final SparseArray<Barcode> detect(Frame frame) {
        if (frame == null) {
            throw new IllegalArgumentException("No frame supplied.");
        }
        Barcode[] zza;
        zzdjw zzc = zzdjw.zzc(frame);
        if (frame.getBitmap() != null) {
            zza = this.zzkwo.zza(frame.getBitmap(), zzc);
            if (zza == null) {
                throw new IllegalArgumentException("Internal barcode detector error; check logcat output.");
            }
        }
        zza = this.zzkwo.zza(frame.getGrayscaleImageData(), zzc);
        SparseArray<Barcode> sparseArray = new SparseArray(zza.length);
        for (Barcode barcode : zza) {
            sparseArray.append(barcode.rawValue.hashCode(), barcode);
        }
        return sparseArray;
    }

    public final boolean isOperational() {
        return this.zzkwo.isOperational();
    }

    public final void release() {
        super.release();
        this.zzkwo.zzbju();
    }
}
