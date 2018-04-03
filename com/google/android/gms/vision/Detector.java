package com.google.android.gms.vision;

import android.util.SparseArray;
import com.google.android.gms.vision.Frame.Metadata;

public abstract class Detector<T> {
    private final Object zzkvt = new Object();
    private Processor<T> zzkvu;

    public static class Detections<T> {
        private final SparseArray<T> zzkvv;
        private final Metadata zzkvw;
        private final boolean zzkvx;

        public Detections(SparseArray<T> sparseArray, Metadata metadata, boolean z) {
            this.zzkvv = sparseArray;
            this.zzkvw = metadata;
            this.zzkvx = z;
        }

        public boolean detectorIsOperational() {
            return this.zzkvx;
        }

        public SparseArray<T> getDetectedItems() {
            return this.zzkvv;
        }

        public Metadata getFrameMetadata() {
            return this.zzkvw;
        }
    }

    public interface Processor<T> {
        void receiveDetections(Detections<T> detections);

        void release();
    }

    public abstract SparseArray<T> detect(Frame frame);

    public boolean isOperational() {
        return true;
    }

    public void receiveFrame(Frame frame) {
        Metadata metadata = new Metadata(frame.getMetadata());
        metadata.zzbjr();
        Detections detections = new Detections(detect(frame), metadata, isOperational());
        synchronized (this.zzkvt) {
            if (this.zzkvu == null) {
                throw new IllegalStateException("Detector processor must first be set with setProcessor in order to receive detection results.");
            }
            this.zzkvu.receiveDetections(detections);
        }
    }

    public void release() {
        synchronized (this.zzkvt) {
            if (this.zzkvu != null) {
                this.zzkvu.release();
                this.zzkvu = null;
            }
        }
    }

    public boolean setFocus(int i) {
        return true;
    }

    public void setProcessor(Processor<T> processor) {
        synchronized (this.zzkvt) {
            if (this.zzkvu != null) {
                this.zzkvu.release();
            }
            this.zzkvu = processor;
        }
    }
}
