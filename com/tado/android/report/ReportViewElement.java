package com.tado.android.report;

import android.support.annotation.Nullable;
import com.tado.android.report.ChartUtils.CoordinateType;
import com.tado.android.report.interfaces.ChartInfoInterface;
import com.tado.android.report.interfaces.ReportViewElementInterface;
import com.tado.android.report.model.ChartRangeValue;
import java.util.List;

public abstract class ReportViewElement implements ReportViewElementInterface {
    protected ChartInfoInterface chartInfo;
    protected float elementRadius;
    protected boolean elementTouched;
    protected long elementX;
    protected float elementY;
    boolean inspectionModeActive;
    protected float maxHumidityYValue;
    protected float maxTemperatureYValue;
    protected long maxXValue;
    protected float minHumidityYValue;
    protected float minTemperatureYValue;
    protected long minXValue;
    protected int touchedX;
    protected int touchedY;

    public enum ReportViewElementType {
        CHART_HORIZONTAL_AXIS,
        CHART_VERTICAL_AXIS,
        CHART_SUNSHINE,
        CHART_LINE,
        CHART_DONE_VIEW,
        CHART_INTERVAL_VIEW,
        CHART_DATE_AND_TIME,
        CHART_STRIPE_BAR,
        CHART_HOT_WATER_PRODUCTION,
        CHART_WEATHER_SLOTS_VIEW,
        CHART_DEVICE_ACTIVITY,
        CHART_INFO_LINE,
        CHART_TOOLBAR
    }

    public void setTouch(int eventAction, float x, float y) {
        this.touchedX = (int) x;
        this.touchedY = (int) y;
    }

    public void inspectionModeActivation(boolean inspectionModeActive) {
        this.inspectionModeActive = inspectionModeActive;
    }

    public void init(ChartInfoInterface chartInfo) {
        this.chartInfo = chartInfo;
    }

    public float getYHumidityCoordinate(float value) {
        return ChartUtils.calculateCoordinate(this.minHumidityYValue, this.maxHumidityYValue, getChartInfoInterface().getChartBottomY() - getChartInfoInterface().getChartTopY(), value, CoordinateType.Y_COORDINATE) + this.chartInfo.getChartTopY();
    }

    public float getYCoordinate(float value) {
        return calculateCoordinate(value, CoordinateType.Y_COORDINATE) + this.chartInfo.getChartTopY();
    }

    public long getXCoordinate(long value) {
        return ChartUtils.calculateXCoordinate(this.minXValue, this.maxXValue, getChartInfoInterface().getChartRightX() - getChartInfoInterface().getChartLeftX(), value);
    }

    public float calculateCoordinate(float value, CoordinateType type) {
        if (CoordinateType.X_COORDINATE == type) {
            return ChartUtils.calculateCoordinate((float) this.minXValue, (float) this.maxXValue, (float) (getChartInfoInterface().getChartRightX() - getChartInfoInterface().getChartLeftX()), value, type);
        }
        return ChartUtils.calculateCoordinate(this.minTemperatureYValue, this.maxTemperatureYValue, getChartInfoInterface().getChartBottomY() - getChartInfoInterface().getChartTopY(), value, type);
    }

    public long getTimeFromXCoordinate(float x) {
        return ChartUtils.getTimeFromXCoordinate(this.minXValue, this.maxXValue, (float) (this.chartInfo.getChartRightX() - this.chartInfo.getChartLeftX()), x, this.chartInfo.getChartLeftX());
    }

    @Nullable
    public <T extends ChartRangeValue> T getRangeValueForTouchpoint(float x, List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        for (T chartRangeValue : list) {
            if (chartRangeValue.getInterval().isInRangeExludingEnd(getTimeFromXCoordinate(x))) {
                return chartRangeValue;
            }
        }
        return (ChartRangeValue) list.get(list.size() - 1);
    }

    public static float getDp(float points) {
        return ChartUtils.getDIPValue(points);
    }

    public void setMinXValue(long minXValue) {
        this.minXValue = minXValue;
    }

    public void setMaxXValue(long maxXValue) {
        this.maxXValue = maxXValue;
    }

    public float getMinTemperatureYValue() {
        return this.minTemperatureYValue;
    }

    public void setMinTemperatureYValue(float minTemperatureYValue) {
        this.minTemperatureYValue = minTemperatureYValue;
    }

    public float getMaxTemperatureYValue() {
        return this.maxTemperatureYValue;
    }

    public void setMaxTemperatureYValue(float maxTemperatureYValue) {
        this.maxTemperatureYValue = maxTemperatureYValue;
    }

    public float getMinHumidityYValue() {
        return this.minHumidityYValue;
    }

    public void setMinHumidityYValue(float minHumidityYValue) {
        this.minHumidityYValue = minHumidityYValue;
    }

    public float getMaxHumidityYValue() {
        return this.maxHumidityYValue;
    }

    public void setMaxHumidityYValue(float maxHumidityYValue) {
        this.maxHumidityYValue = maxHumidityYValue;
    }
}
