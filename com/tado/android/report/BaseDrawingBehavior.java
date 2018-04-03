package com.tado.android.report;

public class BaseDrawingBehavior<T> {
    public boolean shouldDrawInterval(T t) {
        return true;
    }

    public int getColor(T t) {
        return -1;
    }
}
