package com.google.android.gms.vision;

import android.util.SparseArray;
import com.google.android.gms.vision.Detector.Processor;
import java.util.ArrayList;
import java.util.List;

public class MultiDetector extends Detector<Object> {
    private List<Detector<? extends Object>> zzkwj;

    public static class Builder {
        private MultiDetector zzkwk = new MultiDetector();

        public Builder add(Detector<? extends Object> detector) {
            this.zzkwk.zzkwj.add(detector);
            return this;
        }

        public MultiDetector build() {
            if (this.zzkwk.zzkwj.size() != 0) {
                return this.zzkwk;
            }
            throw new RuntimeException("No underlying detectors added to MultiDetector.");
        }
    }

    private MultiDetector() {
        this.zzkwj = new ArrayList();
    }

    public SparseArray<Object> detect(Frame frame) {
        SparseArray<Object> sparseArray = new SparseArray();
        for (Detector detect : this.zzkwj) {
            SparseArray detect2 = detect.detect(frame);
            for (int i = 0; i < detect2.size(); i++) {
                int keyAt = detect2.keyAt(i);
                if (sparseArray.get(keyAt) != null) {
                    throw new IllegalStateException("Detection ID overlap for id = " + keyAt + "  This means that one of the detectors is not using global IDs.");
                }
                sparseArray.append(keyAt, detect2.valueAt(i));
            }
        }
        return sparseArray;
    }

    public boolean isOperational() {
        for (Detector isOperational : this.zzkwj) {
            if (!isOperational.isOperational()) {
                return false;
            }
        }
        return true;
    }

    public void receiveFrame(Frame frame) {
        for (Detector receiveFrame : this.zzkwj) {
            receiveFrame.receiveFrame(frame);
        }
    }

    public void release() {
        for (Detector release : this.zzkwj) {
            release.release();
        }
        this.zzkwj.clear();
    }

    public void setProcessor(Processor<Object> processor) {
        throw new UnsupportedOperationException("MultiDetector.setProcessor is not supported.  You should set a processor instance on each underlying detector instead.");
    }
}
