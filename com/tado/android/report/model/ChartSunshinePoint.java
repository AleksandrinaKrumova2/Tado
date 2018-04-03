package com.tado.android.report.model;

public class ChartSunshinePoint {
    private ChartSunshinePointType type;
    private long f410x;
    private float f411y;

    public enum ChartSunshinePointType {
        START,
        MIDDLE,
        END
    }

    public ChartSunshinePoint(long x, float y, ChartSunshinePointType type) {
        this.f410x = x;
        this.f411y = y;
        this.type = type;
    }

    public long getX() {
        return this.f410x;
    }

    public void setX(long x) {
        this.f410x = x;
    }

    public float getY() {
        return this.f411y;
    }

    public void setY(float y) {
        this.f411y = y;
    }

    public ChartSunshinePointType getType() {
        return this.type;
    }

    public void setType(ChartSunshinePointType type) {
        this.type = type;
    }
}
