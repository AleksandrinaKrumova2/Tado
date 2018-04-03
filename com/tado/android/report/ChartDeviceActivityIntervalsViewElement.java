package com.tado.android.report;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Pair;
import android.util.SparseBooleanArray;
import com.tado.C0676R;
import com.tado.android.report.ChartToolbarViewElement.ToolbarButtonTypeEnum;
import com.tado.android.report.ReportViewElement.ReportViewElementType;
import com.tado.android.report.interfaces.ChartInfoInterface;
import com.tado.android.report.interfaces.ChartToolbarCommandInterface;
import com.tado.android.report.model.ChartRangeValue;
import com.tado.android.report.model.ChartRawMeasurementPoint;
import com.tado.android.report.model.ChartStripe;
import com.tado.android.report.model.ChartXValueRange;
import java.util.ArrayList;
import java.util.List;

class ChartDeviceActivityIntervalsViewElement<T> extends ReportViewElement implements ChartToolbarCommandInterface {
    private List<ChartRangeValue<T>> activityList;
    private Paint paint;
    private List<ChartRawMeasurementPoint> points;
    private List<ChartStripe> stripes;
    private SparseBooleanArray toolbarButtonsState = new SparseBooleanArray(1);
    private ToolbarButtonTypeEnum typeEnum;

    ChartDeviceActivityIntervalsViewElement(List<ChartRawMeasurementPoint> points, List<ChartRangeValue<T>> intervalList, List<ChartStripe> stripes, ToolbarButtonTypeEnum typeEnum, boolean callForHeatState) {
        this.points = points;
        this.stripes = stripes;
        this.toolbarButtonsState.put(typeEnum.ordinal(), callForHeatState);
        this.typeEnum = typeEnum;
        this.activityList = intervalList;
    }

    public void init(ChartInfoInterface chartInfo) {
        super.init(chartInfo);
        this.paint = ChartUtils.getPaint((int) C0676R.color.cooling_blue);
    }

    public void drawViewElement(Canvas canvas) {
        if (this.toolbarButtonsState.get(this.typeEnum.ordinal())) {
            drawActiveIntervalPaths(canvas, this.points, this.activityList, this.inspectionModeActive);
        }
    }

    public ChartInfoInterface getChartInfoInterface() {
        return this.chartInfo;
    }

    public ReportViewElementType getType() {
        return ReportViewElementType.CHART_DEVICE_ACTIVITY;
    }

    private void drawActiveIntervalPaths(Canvas canvas, List<ChartRawMeasurementPoint> points, List<ChartRangeValue<T>> intervalList, boolean touched) {
        for (ChartRangeValue<T> interval : intervalList) {
            if (interval.shouldDrawInterval()) {
                Path path = new Path();
                List<ChartRawMeasurementPoint> subsetPoints = getPointsForRange(points, touched ? getSelectedRange(getChartStripe((float) this.touchedX).getInterval(), interval.getInterval()) : interval.getInterval());
                if (subsetPoints.size() > 1) {
                    path.moveTo((float) getXCoordinate(((ChartRawMeasurementPoint) subsetPoints.get(0)).getTimestamp()), this.chartInfo.getCanvasBottomY());
                    for (int i = 0; i < subsetPoints.size(); i++) {
                        ChartRawMeasurementPoint point = (ChartRawMeasurementPoint) subsetPoints.get(i);
                        path.lineTo((float) getXCoordinate(point.getTimestamp()), getYCoordinate(point.getMeasurement()));
                    }
                    path.lineTo((float) getXCoordinate(((ChartRawMeasurementPoint) subsetPoints.get(subsetPoints.size() - 1)).getTimestamp()), this.chartInfo.getCanvasBottomY());
                    this.paint.setColor(interval.getColor());
                    canvas.drawPath(path, this.paint);
                }
            }
        }
    }

    private ChartXValueRange getSelectedRange(ChartXValueRange stripeRange, ChartXValueRange range) {
        long start = 0;
        long end = 0;
        if ((range.getStart() >= stripeRange.getStart() && range.getStart() < stripeRange.getEnd()) || (range.getEnd() >= stripeRange.getStart() && range.getEnd() < stripeRange.getEnd())) {
            start = range.getStart();
            end = range.getEnd();
            if (stripeRange.getStart() > range.getStart()) {
                start = stripeRange.getStart();
            }
            if (stripeRange.getEnd() < range.getEnd()) {
                end = stripeRange.getEnd();
            }
        }
        return new ChartXValueRange(start, end);
    }

    private List<ChartRawMeasurementPoint> getPointsForRange(List<ChartRawMeasurementPoint> points, ChartXValueRange range) {
        List<ChartRawMeasurementPoint> subsetPoints = new ArrayList();
        ChartRawMeasurementPoint previousPoint = null;
        for (ChartRawMeasurementPoint point : points) {
            if (point.getTimestamp() >= range.getStart() && point.getTimestamp() <= range.getEnd()) {
                if (subsetPoints.size() == 0) {
                    if (previousPoint == null) {
                        previousPoint = point;
                    }
                    subsetPoints.add(getInterpolatedPoint(point, previousPoint, range.getStart()));
                }
                subsetPoints.add(point);
            } else if (point.getTimestamp() > range.getEnd() && subsetPoints.size() > 0) {
                if (previousPoint.getTimestamp() == range.getEnd()) {
                    break;
                }
                subsetPoints.add(getInterpolatedPoint(point, previousPoint, range.getEnd()));
            }
            previousPoint = point;
        }
        return subsetPoints;
    }

    private ChartRawMeasurementPoint getInterpolatedPoint(ChartRawMeasurementPoint nextPoint, ChartRawMeasurementPoint previousPoint, long timestamp) {
        return ChartUtils.interpolateRawMeasurementPoint(new ChartRawMeasurementPoint(timestamp, 0.0f), previousPoint, nextPoint);
    }

    private ChartStripe getChartStripe(float x) {
        return (ChartStripe) getRangeValueForTouchpoint(x, this.stripes);
    }

    public void execute(Pair<ToolbarButtonTypeEnum, Boolean> state) {
        this.toolbarButtonsState.put(((ToolbarButtonTypeEnum) state.first).ordinal(), ((Boolean) state.second).booleanValue());
    }
}
