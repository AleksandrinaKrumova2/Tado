package com.tado.android.report;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Pair;
import android.util.SparseBooleanArray;
import com.tado.android.report.ChartToolbarViewElement.ToolbarButtonTypeEnum;
import com.tado.android.report.ChartUtils.CoordinateType;
import com.tado.android.report.ReportViewElement.ReportViewElementType;
import com.tado.android.report.interfaces.ChartInfoInterface;
import com.tado.android.report.interfaces.ChartToolbarCommandInterface;
import com.tado.android.report.model.ChartPoint;
import com.tado.android.report.model.ChartRawMeasurementPoint;
import java.util.ArrayList;
import java.util.List;

class ChartHumidityLineViewElement extends ReportViewElement implements ChartToolbarCommandInterface {
    private List<List<ChartPoint>> chartPointLists;
    private List<Path> humidityPaths = new ArrayList();
    private Paint paint;
    private List<ChartRawMeasurementPoint> rawMeasurementPoints;
    private List<List<ChartRawMeasurementPoint>> rawMeasurementPointsLists;
    private String strokeColor;
    private int strokeWidth;
    private SparseBooleanArray toolbarButtonsState = new SparseBooleanArray(1);

    ChartHumidityLineViewElement(long minXValue, long maxXValue, float minHumidityYValue, float maxHumidityYValue, boolean humidityState) {
        this.minXValue = minXValue;
        this.maxXValue = maxXValue;
        this.minHumidityYValue = minHumidityYValue;
        this.maxHumidityYValue = maxHumidityYValue;
        this.toolbarButtonsState.put(ToolbarButtonTypeEnum.HUMIDITY.ordinal(), humidityState);
    }

    public void init(ChartInfoInterface chartInfo) {
        super.init(chartInfo);
        this.paint = ChartResources.getHumidityLinePaint();
        initCoordinates();
        this.humidityPaths = initPaths();
    }

    private List<Path> initPaths() {
        for (List<ChartPoint> chartPoints : this.chartPointLists) {
            Path path = new Path();
            for (int i = 0; i < chartPoints.size(); i++) {
                ChartPoint point = (ChartPoint) chartPoints.get(i);
                if (i == 0) {
                    path.moveTo((float) point.getX(), point.getY());
                } else {
                    path.lineTo((float) point.getX(), point.getY());
                }
            }
            this.humidityPaths.add(path);
        }
        return this.humidityPaths;
    }

    public void drawViewElement(Canvas canvas) {
        initPathsAndDrawLine(canvas);
    }

    private void initPathsAndDrawLine(Canvas canvas) {
        if (this.toolbarButtonsState.get(ToolbarButtonTypeEnum.HUMIDITY.ordinal()) && !this.humidityPaths.isEmpty()) {
            for (Path path : this.humidityPaths) {
                drawLinePaths(canvas, path);
            }
        }
    }

    private void initCoordinates() {
        this.chartPointLists = new ArrayList();
        if (this.rawMeasurementPoints != null) {
            for (List<ChartRawMeasurementPoint> points : this.rawMeasurementPointsLists) {
                List<ChartPoint> internalPoints = new ArrayList();
                for (ChartRawMeasurementPoint point : points) {
                    internalPoints.add(new ChartPoint(getXCoordinate(point.getTimestamp()), getYCoordinate(point.getMeasurement())));
                }
                this.chartPointLists.add(internalPoints);
            }
        }
    }

    private void drawLinePaths(Canvas canvas, Path path) {
        if (path != null) {
            canvas.drawPath(path, this.paint);
        }
    }

    public void setRawMeasurementPointsLists(List<List<ChartRawMeasurementPoint>> rawMeasurementPointsLists) {
        this.rawMeasurementPointsLists = rawMeasurementPointsLists;
    }

    public List<List<ChartRawMeasurementPoint>> getRawMeasurementPointsLists() {
        return this.rawMeasurementPointsLists;
    }

    public int getStrokeWidth() {
        return this.strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public String getStrokeColor() {
        return this.strokeColor;
    }

    public void setStrokeColor(String strokeColor) {
        this.strokeColor = strokeColor;
    }

    public float calculateCoordinate(float value, CoordinateType type) {
        if (CoordinateType.X_COORDINATE == type) {
            return ChartUtils.calculateCoordinate((float) this.minXValue, (float) this.maxXValue, (float) (this.chartInfo.getChartRightX() - this.chartInfo.getChartLeftX()), value, type);
        }
        return ChartUtils.calculateCoordinate(this.minHumidityYValue, this.maxHumidityYValue, this.chartInfo.getChartBottomY() - this.chartInfo.getChartTopY(), value, type);
    }

    public ChartInfoInterface getChartInfoInterface() {
        return this.chartInfo;
    }

    public long getMinXValue() {
        return this.minXValue;
    }

    public void setMinXValue(long minXValue) {
        this.minXValue = minXValue;
    }

    public long getMaxXValue() {
        return this.maxXValue;
    }

    public void setMaxXValue(long maxXValue) {
        this.maxXValue = maxXValue;
    }

    public ReportViewElementType getType() {
        return ReportViewElementType.CHART_LINE;
    }

    public void execute(Pair<ToolbarButtonTypeEnum, Boolean> state) {
        this.toolbarButtonsState.put(((ToolbarButtonTypeEnum) state.first).ordinal(), ((Boolean) state.second).booleanValue());
    }

    public void setRawMeasurementPoints(List<ChartRawMeasurementPoint> rawMeasurementPoints) {
        this.rawMeasurementPoints = rawMeasurementPoints;
    }

    public List<ChartRawMeasurementPoint> getRawMeasurementPoints() {
        return this.rawMeasurementPoints;
    }
}
