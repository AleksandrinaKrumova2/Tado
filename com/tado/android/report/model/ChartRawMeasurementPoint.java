package com.tado.android.report.model;

public class ChartRawMeasurementPoint {
    private boolean interpolated;
    private float measurement;
    private long timestamp;

    public ChartRawMeasurementPoint(long timestamp, float measurement) {
        this.timestamp = timestamp;
        this.measurement = measurement;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public float getMeasurement() {
        return this.measurement;
    }

    public void setMeasurement(float measurement) {
        this.measurement = measurement;
    }

    public void setInterpolated(boolean interpolated) {
        this.interpolated = interpolated;
    }

    public boolean isInterpolated() {
        return this.interpolated;
    }
}
