package com.google.android.gms.vision;

import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.vision.Detector.Detections;
import com.google.android.gms.vision.Detector.Processor;

public abstract class FocusingProcessor<T> implements Processor<T> {
    private Detector<T> zzkvj;
    private Tracker<T> zzkvy;
    private int zzkvz = 3;
    private boolean zzkwa = false;
    private int zzkwb;
    private int zzkwc = 0;

    public FocusingProcessor(Detector<T> detector, Tracker<T> tracker) {
        this.zzkvj = detector;
        this.zzkvy = tracker;
    }

    public void receiveDetections(Detections<T> detections) {
        SparseArray detectedItems = detections.getDetectedItems();
        if (detectedItems.size() == 0) {
            if (this.zzkwc == this.zzkvz) {
                this.zzkvy.onDone();
                this.zzkwa = false;
            } else {
                this.zzkvy.onMissing(detections);
            }
            this.zzkwc++;
            return;
        }
        this.zzkwc = 0;
        if (this.zzkwa) {
            Object obj = detectedItems.get(this.zzkwb);
            if (obj != null) {
                this.zzkvy.onUpdate(detections, obj);
                return;
            } else {
                this.zzkvy.onDone();
                this.zzkwa = false;
            }
        }
        int selectFocus = selectFocus(detections);
        Object obj2 = detectedItems.get(selectFocus);
        if (obj2 == null) {
            Log.w("FocusingProcessor", "Invalid focus selected: " + selectFocus);
            return;
        }
        this.zzkwa = true;
        this.zzkwb = selectFocus;
        this.zzkvj.setFocus(this.zzkwb);
        this.zzkvy.onNewItem(this.zzkwb, obj2);
        this.zzkvy.onUpdate(detections, obj2);
    }

    public void release() {
        this.zzkvy.onDone();
    }

    public abstract int selectFocus(Detections<T> detections);

    protected final void zzew(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Invalid max gap: " + i);
        }
        this.zzkvz = i;
    }
}
