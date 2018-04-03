package com.google.android.gms.vision;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.os.SystemClock;
import android.support.annotation.RequiresPermission;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import com.google.android.gms.common.images.Size;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CameraSource {
    @SuppressLint({"InlinedApi"})
    public static final int CAMERA_FACING_BACK = 0;
    @SuppressLint({"InlinedApi"})
    public static final int CAMERA_FACING_FRONT = 1;
    private Context mContext;
    private int zzchn;
    private final Object zzkuw;
    private Camera zzkux;
    private int zzkuy;
    private Size zzkuz;
    private float zzkva;
    private int zzkvb;
    private int zzkvc;
    private boolean zzkvd;
    private SurfaceTexture zzkve;
    private boolean zzkvf;
    private Thread zzkvg;
    private zzb zzkvh;
    private Map<byte[], ByteBuffer> zzkvi;

    public static class Builder {
        private final Detector<?> zzkvj;
        private CameraSource zzkvk = new CameraSource();

        public Builder(Context context, Detector<?> detector) {
            if (context == null) {
                throw new IllegalArgumentException("No context supplied.");
            } else if (detector == null) {
                throw new IllegalArgumentException("No detector supplied.");
            } else {
                this.zzkvj = detector;
                this.zzkvk.mContext = context;
            }
        }

        public CameraSource build() {
            CameraSource cameraSource = this.zzkvk;
            CameraSource cameraSource2 = this.zzkvk;
            cameraSource2.getClass();
            cameraSource.zzkvh = new zzb(cameraSource2, this.zzkvj);
            return this.zzkvk;
        }

        public Builder setAutoFocusEnabled(boolean z) {
            this.zzkvk.zzkvd = z;
            return this;
        }

        public Builder setFacing(int i) {
            if (i == 0 || i == 1) {
                this.zzkvk.zzkuy = i;
                return this;
            }
            throw new IllegalArgumentException("Invalid camera: " + i);
        }

        public Builder setRequestedFps(float f) {
            if (f <= 0.0f) {
                throw new IllegalArgumentException("Invalid fps: " + f);
            }
            this.zzkvk.zzkva = f;
            return this;
        }

        public Builder setRequestedPreviewSize(int i, int i2) {
            if (i <= 0 || i > 1000000 || i2 <= 0 || i2 > 1000000) {
                throw new IllegalArgumentException("Invalid preview size: " + i + "x" + i2);
            }
            this.zzkvk.zzkvb = i;
            this.zzkvk.zzkvc = i2;
            return this;
        }
    }

    public interface PictureCallback {
        void onPictureTaken(byte[] bArr);
    }

    public interface ShutterCallback {
        void onShutter();
    }

    class zza implements PreviewCallback {
        private /* synthetic */ CameraSource zzkvl;

        private zza(CameraSource cameraSource) {
            this.zzkvl = cameraSource;
        }

        public final void onPreviewFrame(byte[] bArr, Camera camera) {
            this.zzkvl.zzkvh.zza(bArr, camera);
        }
    }

    class zzb implements Runnable {
        private boolean mActive = true;
        private final Object mLock = new Object();
        private long zzdvq = SystemClock.elapsedRealtime();
        private Detector<?> zzkvj;
        private /* synthetic */ CameraSource zzkvl;
        private long zzkvm;
        private int zzkvn = 0;
        private ByteBuffer zzkvo;

        zzb(CameraSource cameraSource, Detector<?> detector) {
            this.zzkvl = cameraSource;
            this.zzkvj = detector;
        }

        @SuppressLint({"Assert"})
        final void release() {
            this.zzkvj.release();
            this.zzkvj = null;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        @android.annotation.SuppressLint({"InlinedApi"})
        public final void run() {
            /*
            r6 = this;
        L_0x0000:
            r1 = r6.mLock;
            monitor-enter(r1);
        L_0x0003:
            r0 = r6.mActive;	 Catch:{ all -> 0x0023 }
            if (r0 == 0) goto L_0x001d;
        L_0x0007:
            r0 = r6.zzkvo;	 Catch:{ all -> 0x0023 }
            if (r0 != 0) goto L_0x001d;
        L_0x000b:
            r0 = r6.mLock;	 Catch:{ InterruptedException -> 0x0011 }
            r0.wait();	 Catch:{ InterruptedException -> 0x0011 }
            goto L_0x0003;
        L_0x0011:
            r0 = move-exception;
            r2 = "CameraSource";
            r3 = "Frame processing loop terminated.";
            android.util.Log.d(r2, r3, r0);	 Catch:{ all -> 0x0023 }
            monitor-exit(r1);	 Catch:{ all -> 0x0023 }
        L_0x001c:
            return;
        L_0x001d:
            r0 = r6.mActive;	 Catch:{ all -> 0x0023 }
            if (r0 != 0) goto L_0x0026;
        L_0x0021:
            monitor-exit(r1);	 Catch:{ all -> 0x0023 }
            goto L_0x001c;
        L_0x0023:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0023 }
            throw r0;
        L_0x0026:
            r0 = new com.google.android.gms.vision.Frame$Builder;	 Catch:{ all -> 0x0023 }
            r0.<init>();	 Catch:{ all -> 0x0023 }
            r2 = r6.zzkvo;	 Catch:{ all -> 0x0023 }
            r3 = r6.zzkvl;	 Catch:{ all -> 0x0023 }
            r3 = r3.zzkuz;	 Catch:{ all -> 0x0023 }
            r3 = r3.getWidth();	 Catch:{ all -> 0x0023 }
            r4 = r6.zzkvl;	 Catch:{ all -> 0x0023 }
            r4 = r4.zzkuz;	 Catch:{ all -> 0x0023 }
            r4 = r4.getHeight();	 Catch:{ all -> 0x0023 }
            r5 = 17;
            r0 = r0.setImageData(r2, r3, r4, r5);	 Catch:{ all -> 0x0023 }
            r2 = r6.zzkvn;	 Catch:{ all -> 0x0023 }
            r0 = r0.setId(r2);	 Catch:{ all -> 0x0023 }
            r2 = r6.zzkvm;	 Catch:{ all -> 0x0023 }
            r0 = r0.setTimestampMillis(r2);	 Catch:{ all -> 0x0023 }
            r2 = r6.zzkvl;	 Catch:{ all -> 0x0023 }
            r2 = r2.zzchn;	 Catch:{ all -> 0x0023 }
            r0 = r0.setRotation(r2);	 Catch:{ all -> 0x0023 }
            r0 = r0.build();	 Catch:{ all -> 0x0023 }
            r2 = r6.zzkvo;	 Catch:{ all -> 0x0023 }
            r3 = 0;
            r6.zzkvo = r3;	 Catch:{ all -> 0x0023 }
            monitor-exit(r1);	 Catch:{ all -> 0x0023 }
            r1 = r6.zzkvj;	 Catch:{ Throwable -> 0x007a }
            r1.receiveFrame(r0);	 Catch:{ Throwable -> 0x007a }
            r0 = r6.zzkvl;
            r0 = r0.zzkux;
            r1 = r2.array();
            r0.addCallbackBuffer(r1);
            goto L_0x0000;
        L_0x007a:
            r0 = move-exception;
            r1 = "CameraSource";
            r3 = "Exception thrown from receiver.";
            android.util.Log.e(r1, r3, r0);	 Catch:{ all -> 0x0093 }
            r0 = r6.zzkvl;
            r0 = r0.zzkux;
            r1 = r2.array();
            r0.addCallbackBuffer(r1);
            goto L_0x0000;
        L_0x0093:
            r0 = move-exception;
            r1 = r6.zzkvl;
            r1 = r1.zzkux;
            r2 = r2.array();
            r1.addCallbackBuffer(r2);
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.vision.CameraSource.zzb.run():void");
        }

        final void setActive(boolean z) {
            synchronized (this.mLock) {
                this.mActive = z;
                this.mLock.notifyAll();
            }
        }

        final void zza(byte[] bArr, Camera camera) {
            synchronized (this.mLock) {
                if (this.zzkvo != null) {
                    camera.addCallbackBuffer(this.zzkvo.array());
                    this.zzkvo = null;
                }
                if (this.zzkvl.zzkvi.containsKey(bArr)) {
                    this.zzkvm = SystemClock.elapsedRealtime() - this.zzdvq;
                    this.zzkvn++;
                    this.zzkvo = (ByteBuffer) this.zzkvl.zzkvi.get(bArr);
                    this.mLock.notifyAll();
                    return;
                }
                Log.d("CameraSource", "Skipping frame. Could not find ByteBuffer associated with the image data from the camera.");
            }
        }
    }

    class zzc implements android.hardware.Camera.PictureCallback {
        private /* synthetic */ CameraSource zzkvl;
        private PictureCallback zzkvp;

        private zzc(CameraSource cameraSource) {
            this.zzkvl = cameraSource;
        }

        public final void onPictureTaken(byte[] bArr, Camera camera) {
            if (this.zzkvp != null) {
                this.zzkvp.onPictureTaken(bArr);
            }
            synchronized (this.zzkvl.zzkuw) {
                if (this.zzkvl.zzkux != null) {
                    this.zzkvl.zzkux.startPreview();
                }
            }
        }
    }

    static class zzd implements android.hardware.Camera.ShutterCallback {
        private ShutterCallback zzkvq;

        private zzd() {
        }

        public final void onShutter() {
            if (this.zzkvq != null) {
                this.zzkvq.onShutter();
            }
        }
    }

    static class zze {
        private Size zzkvr;
        private Size zzkvs;

        public zze(Camera.Size size, Camera.Size size2) {
            this.zzkvr = new Size(size.width, size.height);
            if (size2 != null) {
                this.zzkvs = new Size(size2.width, size2.height);
            }
        }

        public final Size zzbjp() {
            return this.zzkvr;
        }

        public final Size zzbjq() {
            return this.zzkvs;
        }
    }

    private CameraSource() {
        this.zzkuw = new Object();
        this.zzkuy = 0;
        this.zzkva = BitmapDescriptorFactory.HUE_ORANGE;
        this.zzkvb = 1024;
        this.zzkvc = 768;
        this.zzkvd = false;
        this.zzkvi = new HashMap();
    }

    private static zze zza(Camera camera, int i, int i2) {
        zze com_google_android_gms_vision_CameraSource_zze = null;
        Parameters parameters = camera.getParameters();
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
        List arrayList = new ArrayList();
        for (Camera.Size size : supportedPreviewSizes) {
            float f = ((float) size.width) / ((float) size.height);
            for (Camera.Size size2 : supportedPictureSizes) {
                if (Math.abs(f - (((float) size2.width) / ((float) size2.height))) < 0.01f) {
                    arrayList.add(new zze(size, size2));
                    break;
                }
            }
        }
        if (arrayList.size() == 0) {
            Log.w("CameraSource", "No preview sizes have a corresponding same-aspect-ratio picture size");
            for (Camera.Size size3 : supportedPreviewSizes) {
                arrayList.add(new zze(size3, null));
            }
        }
        int i3 = Integer.MAX_VALUE;
        ArrayList arrayList2 = (ArrayList) arrayList;
        int size4 = arrayList2.size();
        int i4 = 0;
        while (i4 < size4) {
            zze com_google_android_gms_vision_CameraSource_zze2;
            int i5;
            int i6 = i4 + 1;
            zze com_google_android_gms_vision_CameraSource_zze3 = (zze) arrayList2.get(i4);
            Size zzbjp = com_google_android_gms_vision_CameraSource_zze3.zzbjp();
            i4 = Math.abs(zzbjp.getHeight() - i2) + Math.abs(zzbjp.getWidth() - i);
            if (i4 < i3) {
                int i7 = i4;
                com_google_android_gms_vision_CameraSource_zze2 = com_google_android_gms_vision_CameraSource_zze3;
                i5 = i7;
            } else {
                i5 = i3;
                com_google_android_gms_vision_CameraSource_zze2 = com_google_android_gms_vision_CameraSource_zze;
            }
            i3 = i5;
            com_google_android_gms_vision_CameraSource_zze = com_google_android_gms_vision_CameraSource_zze2;
            i4 = i6;
        }
        return com_google_android_gms_vision_CameraSource_zze;
    }

    @SuppressLint({"InlinedApi"})
    private final byte[] zza(Size size) {
        Object obj = new byte[(((int) Math.ceil(((double) ((long) (ImageFormat.getBitsPerPixel(17) * (size.getHeight() * size.getWidth())))) / 8.0d)) + 1)];
        ByteBuffer wrap = ByteBuffer.wrap(obj);
        if (wrap.hasArray() && wrap.array() == obj) {
            this.zzkvi.put(obj, wrap);
            return obj;
        }
        throw new IllegalStateException("Failed to create valid buffer for camera source.");
    }

    @SuppressLint({"InlinedApi"})
    private static int[] zza(Camera camera, float f) {
        int i = (int) (1000.0f * f);
        int[] iArr = null;
        int i2 = Integer.MAX_VALUE;
        for (int[] iArr2 : camera.getParameters().getSupportedPreviewFpsRange()) {
            int[] iArr3;
            int i3;
            int abs = Math.abs(i - iArr2[0]) + Math.abs(i - iArr2[1]);
            if (abs < i2) {
                int i4 = abs;
                iArr3 = iArr2;
                i3 = i4;
            } else {
                i3 = i2;
                iArr3 = iArr;
            }
            i2 = i3;
            iArr = iArr3;
        }
        return iArr;
    }

    @SuppressLint({"InlinedApi"})
    private final Camera zzbjo() throws IOException {
        int i;
        int i2 = 0;
        int i3 = this.zzkuy;
        CameraInfo cameraInfo = new CameraInfo();
        for (i = 0; i < Camera.getNumberOfCameras(); i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == i3) {
                i3 = i;
                break;
            }
        }
        i3 = -1;
        if (i3 == -1) {
            throw new IOException("Could not find requested camera.");
        }
        Camera open = Camera.open(i3);
        zze zza = zza(open, this.zzkvb, this.zzkvc);
        if (zza == null) {
            throw new IOException("Could not find suitable preview size.");
        }
        Size zzbjq = zza.zzbjq();
        this.zzkuz = zza.zzbjp();
        int[] zza2 = zza(open, this.zzkva);
        if (zza2 == null) {
            throw new IOException("Could not find suitable preview frames per second range.");
        }
        Parameters parameters = open.getParameters();
        if (zzbjq != null) {
            parameters.setPictureSize(zzbjq.getWidth(), zzbjq.getHeight());
        }
        parameters.setPreviewSize(this.zzkuz.getWidth(), this.zzkuz.getHeight());
        parameters.setPreviewFpsRange(zza2[0], zza2[1]);
        parameters.setPreviewFormat(17);
        i = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getRotation();
        switch (i) {
            case 0:
                break;
            case 1:
                i2 = 90;
                break;
            case 2:
                i2 = 180;
                break;
            case 3:
                i2 = 270;
                break;
            default:
                Log.e("CameraSource", "Bad rotation value: " + i);
                break;
        }
        CameraInfo cameraInfo2 = new CameraInfo();
        Camera.getCameraInfo(i3, cameraInfo2);
        if (cameraInfo2.facing == 1) {
            i2 = (cameraInfo2.orientation + i2) % 360;
            i = (360 - i2) % 360;
        } else {
            i = ((cameraInfo2.orientation - i2) + 360) % 360;
            i2 = i;
        }
        this.zzchn = i2 / 90;
        open.setDisplayOrientation(i);
        parameters.setRotation(i2);
        if (this.zzkvd) {
            if (parameters.getSupportedFocusModes().contains("continuous-video")) {
                parameters.setFocusMode("continuous-video");
            } else {
                Log.i("CameraSource", "Camera auto focus is not supported on this device.");
            }
        }
        open.setParameters(parameters);
        open.setPreviewCallbackWithBuffer(new zza());
        open.addCallbackBuffer(zza(this.zzkuz));
        open.addCallbackBuffer(zza(this.zzkuz));
        open.addCallbackBuffer(zza(this.zzkuz));
        open.addCallbackBuffer(zza(this.zzkuz));
        return open;
    }

    public int getCameraFacing() {
        return this.zzkuy;
    }

    public Size getPreviewSize() {
        return this.zzkuz;
    }

    public void release() {
        synchronized (this.zzkuw) {
            stop();
            this.zzkvh.release();
        }
    }

    @RequiresPermission("android.permission.CAMERA")
    public CameraSource start() throws IOException {
        synchronized (this.zzkuw) {
            if (this.zzkux != null) {
            } else {
                this.zzkux = zzbjo();
                this.zzkve = new SurfaceTexture(100);
                this.zzkux.setPreviewTexture(this.zzkve);
                this.zzkvf = true;
                this.zzkux.startPreview();
                this.zzkvg = new Thread(this.zzkvh);
                this.zzkvh.setActive(true);
                this.zzkvg.start();
            }
        }
        return this;
    }

    @RequiresPermission("android.permission.CAMERA")
    public CameraSource start(SurfaceHolder surfaceHolder) throws IOException {
        synchronized (this.zzkuw) {
            if (this.zzkux != null) {
            } else {
                this.zzkux = zzbjo();
                this.zzkux.setPreviewDisplay(surfaceHolder);
                this.zzkux.startPreview();
                this.zzkvg = new Thread(this.zzkvh);
                this.zzkvh.setActive(true);
                this.zzkvg.start();
                this.zzkvf = false;
            }
        }
        return this;
    }

    public void stop() {
        synchronized (this.zzkuw) {
            this.zzkvh.setActive(false);
            if (this.zzkvg != null) {
                try {
                    this.zzkvg.join();
                } catch (InterruptedException e) {
                    Log.d("CameraSource", "Frame processing thread interrupted on release.");
                }
                this.zzkvg = null;
            }
            if (this.zzkux != null) {
                this.zzkux.stopPreview();
                this.zzkux.setPreviewCallbackWithBuffer(null);
                try {
                    if (this.zzkvf) {
                        this.zzkux.setPreviewTexture(null);
                    } else {
                        this.zzkux.setPreviewDisplay(null);
                    }
                } catch (Exception e2) {
                    String valueOf = String.valueOf(e2);
                    Log.e("CameraSource", new StringBuilder(String.valueOf(valueOf).length() + 32).append("Failed to clear camera preview: ").append(valueOf).toString());
                }
                this.zzkux.release();
                this.zzkux = null;
            }
            this.zzkvi.clear();
        }
    }

    public void takePicture(ShutterCallback shutterCallback, PictureCallback pictureCallback) {
        synchronized (this.zzkuw) {
            if (this.zzkux != null) {
                android.hardware.Camera.ShutterCallback com_google_android_gms_vision_CameraSource_zzd = new zzd();
                com_google_android_gms_vision_CameraSource_zzd.zzkvq = shutterCallback;
                android.hardware.Camera.PictureCallback com_google_android_gms_vision_CameraSource_zzc = new zzc();
                com_google_android_gms_vision_CameraSource_zzc.zzkvp = pictureCallback;
                this.zzkux.takePicture(com_google_android_gms_vision_CameraSource_zzd, null, null, com_google_android_gms_vision_CameraSource_zzc);
            }
        }
    }
}
