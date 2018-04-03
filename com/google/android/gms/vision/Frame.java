package com.google.android.gms.vision;

import android.graphics.Bitmap;
import android.graphics.Color;
import java.nio.ByteBuffer;

public class Frame {
    public static final int ROTATION_0 = 0;
    public static final int ROTATION_180 = 2;
    public static final int ROTATION_270 = 3;
    public static final int ROTATION_90 = 1;
    private Bitmap mBitmap;
    private Metadata zzkwd;
    private ByteBuffer zzkwe;

    public static class Builder {
        private Frame zzkwf = new Frame();

        public Frame build() {
            if (this.zzkwf.zzkwe != null || this.zzkwf.mBitmap != null) {
                return this.zzkwf;
            }
            throw new IllegalStateException("Missing image data.  Call either setBitmap or setImageData to specify the image");
        }

        public Builder setBitmap(Bitmap bitmap) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            this.zzkwf.mBitmap = bitmap;
            Metadata metadata = this.zzkwf.getMetadata();
            metadata.zzalv = width;
            metadata.zzalw = height;
            return this;
        }

        public Builder setId(int i) {
            this.zzkwf.getMetadata().mId = i;
            return this;
        }

        public Builder setImageData(ByteBuffer byteBuffer, int i, int i2, int i3) {
            if (byteBuffer == null) {
                throw new IllegalArgumentException("Null image data supplied.");
            } else if (byteBuffer.capacity() < i * i2) {
                throw new IllegalArgumentException("Invalid image data size.");
            } else {
                switch (i3) {
                    case 16:
                    case 17:
                    case 842094169:
                        this.zzkwf.zzkwe = byteBuffer;
                        Metadata metadata = this.zzkwf.getMetadata();
                        metadata.zzalv = i;
                        metadata.zzalw = i2;
                        metadata.format = i3;
                        return this;
                    default:
                        throw new IllegalArgumentException("Unsupported image format: " + i3);
                }
            }
        }

        public Builder setRotation(int i) {
            this.zzkwf.getMetadata().zzchn = i;
            return this;
        }

        public Builder setTimestampMillis(long j) {
            this.zzkwf.getMetadata().zzhwo = j;
            return this;
        }
    }

    public static class Metadata {
        private int format = -1;
        private int mId;
        private int zzalv;
        private int zzalw;
        private int zzchn;
        private long zzhwo;

        public Metadata(Metadata metadata) {
            this.zzalv = metadata.getWidth();
            this.zzalw = metadata.getHeight();
            this.mId = metadata.getId();
            this.zzhwo = metadata.getTimestampMillis();
            this.zzchn = metadata.getRotation();
        }

        public int getFormat() {
            return this.format;
        }

        public int getHeight() {
            return this.zzalw;
        }

        public int getId() {
            return this.mId;
        }

        public int getRotation() {
            return this.zzchn;
        }

        public long getTimestampMillis() {
            return this.zzhwo;
        }

        public int getWidth() {
            return this.zzalv;
        }

        public final void zzbjr() {
            if (this.zzchn % 2 != 0) {
                int i = this.zzalv;
                this.zzalv = this.zzalw;
                this.zzalw = i;
            }
            this.zzchn = 0;
        }
    }

    private Frame() {
        this.zzkwd = new Metadata();
        this.zzkwe = null;
        this.mBitmap = null;
    }

    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    public ByteBuffer getGrayscaleImageData() {
        int i = 0;
        if (this.mBitmap == null) {
            return this.zzkwe;
        }
        int width = this.mBitmap.getWidth();
        int height = this.mBitmap.getHeight();
        int[] iArr = new int[(width * height)];
        this.mBitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        byte[] bArr = new byte[(width * height)];
        while (i < iArr.length) {
            bArr[i] = (byte) ((int) (((((float) Color.red(iArr[i])) * 0.299f) + (((float) Color.green(iArr[i])) * 0.587f)) + (((float) Color.blue(iArr[i])) * 0.114f)));
            i++;
        }
        return ByteBuffer.wrap(bArr);
    }

    public Metadata getMetadata() {
        return this.zzkwd;
    }
}
