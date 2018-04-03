package com.google.android.gms.vision;

import android.util.SparseArray;
import com.google.android.gms.vision.Detector.Detections;
import com.google.android.gms.vision.Detector.Processor;
import java.util.HashSet;
import java.util.Set;

public class MultiProcessor<T> implements Processor<T> {
    private int zzkvz;
    private Factory<T> zzkwl;
    private SparseArray<zza> zzkwm;

    public static class Builder<T> {
        private MultiProcessor<T> zzkwn = new MultiProcessor();

        public Builder(Factory<T> factory) {
            if (factory == null) {
                throw new IllegalArgumentException("No factory supplied.");
            }
            this.zzkwn.zzkwl = factory;
        }

        public MultiProcessor<T> build() {
            return this.zzkwn;
        }

        public Builder<T> setMaxGapFrames(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("Invalid max gap: " + i);
            }
            this.zzkwn.zzkvz = i;
            return this;
        }
    }

    public interface Factory<T> {
        Tracker<T> create(T t);
    }

    class zza {
        private Tracker<T> zzkvy;
        private int zzkwc;

        private zza(MultiProcessor multiProcessor) {
            this.zzkwc = 0;
        }
    }

    private MultiProcessor() {
        this.zzkwm = new SparseArray();
        this.zzkvz = 3;
    }

    private final void zza(Detections<T> detections) {
        SparseArray detectedItems = detections.getDetectedItems();
        for (int i = 0; i < detectedItems.size(); i++) {
            int keyAt = detectedItems.keyAt(i);
            Object valueAt = detectedItems.valueAt(i);
            zza com_google_android_gms_vision_MultiProcessor_zza = (zza) this.zzkwm.get(keyAt);
            com_google_android_gms_vision_MultiProcessor_zza.zzkwc = 0;
            com_google_android_gms_vision_MultiProcessor_zza.zzkvy.onUpdate(detections, valueAt);
        }
    }

    public void receiveDetections(Detections<T> detections) {
        int i = 0;
        SparseArray detectedItems = detections.getDetectedItems();
        for (int i2 = 0; i2 < detectedItems.size(); i2++) {
            int keyAt = detectedItems.keyAt(i2);
            Object valueAt = detectedItems.valueAt(i2);
            if (this.zzkwm.get(keyAt) == null) {
                zza com_google_android_gms_vision_MultiProcessor_zza = new zza();
                com_google_android_gms_vision_MultiProcessor_zza.zzkvy = this.zzkwl.create(valueAt);
                com_google_android_gms_vision_MultiProcessor_zza.zzkvy.onNewItem(keyAt, valueAt);
                this.zzkwm.append(keyAt, com_google_android_gms_vision_MultiProcessor_zza);
            }
        }
        detectedItems = detections.getDetectedItems();
        Set<Integer> hashSet = new HashSet();
        while (i < this.zzkwm.size()) {
            int keyAt2 = this.zzkwm.keyAt(i);
            if (detectedItems.get(keyAt2) == null) {
                zza com_google_android_gms_vision_MultiProcessor_zza2 = (zza) this.zzkwm.valueAt(i);
                com_google_android_gms_vision_MultiProcessor_zza2.zzkwc = com_google_android_gms_vision_MultiProcessor_zza2.zzkwc + 1;
                if (com_google_android_gms_vision_MultiProcessor_zza2.zzkwc >= this.zzkvz) {
                    com_google_android_gms_vision_MultiProcessor_zza2.zzkvy.onDone();
                    hashSet.add(Integer.valueOf(keyAt2));
                } else {
                    com_google_android_gms_vision_MultiProcessor_zza2.zzkvy.onMissing(detections);
                }
            }
            i++;
        }
        for (Integer intValue : hashSet) {
            this.zzkwm.delete(intValue.intValue());
        }
        zza(detections);
    }

    public void release() {
        for (int i = 0; i < this.zzkwm.size(); i++) {
            ((zza) this.zzkwm.valueAt(i)).zzkvy.onDone();
        }
        this.zzkwm.clear();
    }
}
