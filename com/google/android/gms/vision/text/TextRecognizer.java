package com.google.android.gms.vision.text;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.util.SparseArray;
import com.google.android.gms.internal.zzdjw;
import com.google.android.gms.internal.zzdke;
import com.google.android.gms.internal.zzdkg;
import com.google.android.gms.internal.zzdkk;
import com.google.android.gms.internal.zzdkl;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.Frame.Metadata;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public final class TextRecognizer extends Detector<TextBlock> {
    private final zzdkk zzkyb;

    public static class Builder {
        private Context mContext;
        private zzdkl zzkyc = new zzdkl();

        public Builder(Context context) {
            this.mContext = context;
        }

        public TextRecognizer build() {
            return new TextRecognizer(new zzdkk(this.mContext, this.zzkyc));
        }
    }

    private TextRecognizer() {
        throw new IllegalStateException("Default constructor called");
    }

    private TextRecognizer(zzdkk com_google_android_gms_internal_zzdkk) {
        this.zzkyb = com_google_android_gms_internal_zzdkk;
    }

    private static SparseArray<TextBlock> zza(zzdke[] com_google_android_gms_internal_zzdkeArr) {
        int i = 0;
        SparseArray sparseArray = new SparseArray();
        for (zzdke com_google_android_gms_internal_zzdke : com_google_android_gms_internal_zzdkeArr) {
            SparseArray sparseArray2 = (SparseArray) sparseArray.get(com_google_android_gms_internal_zzdke.zzkym);
            if (sparseArray2 == null) {
                sparseArray2 = new SparseArray();
                sparseArray.append(com_google_android_gms_internal_zzdke.zzkym, sparseArray2);
            }
            sparseArray2.append(com_google_android_gms_internal_zzdke.zzkyn, com_google_android_gms_internal_zzdke);
        }
        SparseArray<TextBlock> sparseArray3 = new SparseArray(sparseArray.size());
        while (i < sparseArray.size()) {
            sparseArray3.append(sparseArray.keyAt(i), new TextBlock((SparseArray) sparseArray.valueAt(i)));
            i++;
        }
        return sparseArray3;
    }

    public final SparseArray<TextBlock> detect(Frame frame) {
        zzdkg com_google_android_gms_internal_zzdkg = new zzdkg(new Rect());
        if (frame == null) {
            throw new IllegalArgumentException("No frame supplied.");
        }
        Bitmap bitmap;
        int i;
        int i2;
        zzdjw zzc = zzdjw.zzc(frame);
        if (frame.getBitmap() != null) {
            bitmap = frame.getBitmap();
        } else {
            byte[] array;
            Metadata metadata = frame.getMetadata();
            ByteBuffer grayscaleImageData = frame.getGrayscaleImageData();
            int format = metadata.getFormat();
            i = zzc.width;
            i2 = zzc.height;
            if (grayscaleImageData.hasArray() && grayscaleImageData.arrayOffset() == 0) {
                array = grayscaleImageData.array();
            } else {
                array = new byte[grayscaleImageData.capacity()];
                grayscaleImageData.get(array);
            }
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new YuvImage(array, format, i, i2, null).compressToJpeg(new Rect(0, 0, i, i2), 100, byteArrayOutputStream);
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            bitmap = BitmapFactory.decodeByteArray(toByteArray, 0, toByteArray.length);
        }
        i = bitmap.getWidth();
        i2 = bitmap.getHeight();
        if (zzc.rotation != 0) {
            int i3;
            Matrix matrix = new Matrix();
            switch (zzc.rotation) {
                case 0:
                    i3 = 0;
                    break;
                case 1:
                    i3 = 90;
                    break;
                case 2:
                    i3 = 180;
                    break;
                case 3:
                    i3 = 270;
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported rotation degree.");
            }
            matrix.postRotate((float) i3);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, i, i2, matrix, false);
        }
        if (zzc.rotation == 1 || zzc.rotation == 3) {
            zzc.width = i2;
            zzc.height = i;
        }
        if (!com_google_android_gms_internal_zzdkg.zzkyo.isEmpty()) {
            Rect rect;
            Rect rect2 = com_google_android_gms_internal_zzdkg.zzkyo;
            i = frame.getMetadata().getWidth();
            i2 = frame.getMetadata().getHeight();
            switch (zzc.rotation) {
                case 1:
                    rect = new Rect(i2 - rect2.bottom, rect2.left, i2 - rect2.top, rect2.right);
                    break;
                case 2:
                    rect = new Rect(i - rect2.right, i2 - rect2.bottom, i - rect2.left, i2 - rect2.top);
                    break;
                case 3:
                    rect = new Rect(rect2.top, i - rect2.right, rect2.bottom, i - rect2.left);
                    break;
                default:
                    rect = rect2;
                    break;
            }
            com_google_android_gms_internal_zzdkg.zzkyo.set(rect);
        }
        zzc.rotation = 0;
        return zza(this.zzkyb.zza(bitmap, zzc, com_google_android_gms_internal_zzdkg));
    }

    public final boolean isOperational() {
        return this.zzkyb.isOperational();
    }

    public final void release() {
        super.release();
        this.zzkyb.zzbju();
    }
}
