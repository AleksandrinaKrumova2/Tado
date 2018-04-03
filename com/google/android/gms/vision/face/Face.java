package com.google.android.gms.vision.face;

import android.graphics.PointF;
import java.util.Arrays;
import java.util.List;

public class Face {
    public static final float UNCOMPUTED_PROBABILITY = -1.0f;
    private int mId;
    private float zziut;
    private float zziuu;
    private PointF zzkwr;
    private float zzkws;
    private float zzkwt;
    private List<Landmark> zzkwu;
    private float zzkwv;
    private float zzkww;
    private float zzkwx;

    public Face(int i, PointF pointF, float f, float f2, float f3, float f4, Landmark[] landmarkArr, float f5, float f6, float f7) {
        this.mId = i;
        this.zzkwr = pointF;
        this.zziut = f;
        this.zziuu = f2;
        this.zzkws = f3;
        this.zzkwt = f4;
        this.zzkwu = Arrays.asList(landmarkArr);
        if (f5 < 0.0f || f5 > 1.0f) {
            this.zzkwv = -1.0f;
        } else {
            this.zzkwv = f5;
        }
        if (f6 < 0.0f || f6 > 1.0f) {
            this.zzkww = -1.0f;
        } else {
            this.zzkww = f6;
        }
        if (f7 < 0.0f || f7 > 1.0f) {
            this.zzkwx = -1.0f;
        } else {
            this.zzkwx = f7;
        }
    }

    public float getEulerY() {
        return this.zzkws;
    }

    public float getEulerZ() {
        return this.zzkwt;
    }

    public float getHeight() {
        return this.zziuu;
    }

    public int getId() {
        return this.mId;
    }

    public float getIsLeftEyeOpenProbability() {
        return this.zzkwv;
    }

    public float getIsRightEyeOpenProbability() {
        return this.zzkww;
    }

    public float getIsSmilingProbability() {
        return this.zzkwx;
    }

    public List<Landmark> getLandmarks() {
        return this.zzkwu;
    }

    public PointF getPosition() {
        return new PointF(this.zzkwr.x - (this.zziut / 2.0f), this.zzkwr.y - (this.zziuu / 2.0f));
    }

    public float getWidth() {
        return this.zziut;
    }
}
