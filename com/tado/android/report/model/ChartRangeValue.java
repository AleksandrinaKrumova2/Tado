package com.tado.android.report.model;

import com.tado.android.report.BaseDrawingBehavior;
import com.tado.android.report.interfaces.ChartDrawingIntervalInterface;

public class ChartRangeValue<T> implements ChartDrawingIntervalInterface {
    private BaseDrawingBehavior<T> drawingBehavior = new BaseDrawingBehavior();
    private ChartXValueRange range;
    private T value;

    public ChartRangeValue(ChartXValueRange range, T value) {
        this.range = range;
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    public ChartXValueRange getInterval() {
        return this.range;
    }

    public boolean shouldDrawInterval() {
        return this.drawingBehavior.shouldDrawInterval(getValue());
    }

    public int getColor() {
        return this.drawingBehavior.getColor(getValue());
    }

    public void setDrawingBehavior(BaseDrawingBehavior<T> drawingBehavior) {
        this.drawingBehavior = drawingBehavior;
    }
}
