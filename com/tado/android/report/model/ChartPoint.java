package com.tado.android.report.model;

public class ChartPoint {
    private long f408x;
    private float f409y;

    public ChartPoint(long x, float y) {
        this.f408x = x;
        this.f409y = y;
    }

    public long getX() {
        return this.f408x;
    }

    public void setX(long x) {
        this.f408x = x;
    }

    public float getY() {
        return this.f409y;
    }

    public void setY(float y) {
        this.f409y = y;
    }
}
