package com.tado.android.report;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Shader.TileMode;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.util.Pair;
import android.util.SparseBooleanArray;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import com.tado.android.report.ChartToolbarViewElement.ToolbarButtonTypeEnum;
import com.tado.android.report.ChartUtils.CoordinateType;
import com.tado.android.report.ReportViewElement.ReportViewElementType;
import com.tado.android.report.interfaces.ChartInfoInterface;
import com.tado.android.report.interfaces.ChartToolbarCommandInterface;
import com.tado.android.report.model.ChartPoint;
import com.tado.android.report.model.ChartRawMeasurementPoint;
import com.tado.android.report.model.ChartStripe;
import com.tado.android.utils.Constants;
import com.tado.android.utils.Snitcher;
import java.util.ArrayList;
import java.util.List;

class ChartLineViewElement extends ReportViewElement implements ChartToolbarCommandInterface {
    private Bitmap allDayNoConnectionIcon;
    private List<List<ChartPoint>> chartPoints;
    private boolean debugMode = false;
    private Paint fillPaint;
    private float iconPadding;
    private final boolean isFullDay;
    private List<Pair<Path, Path>> lineAndUnderLinePaths;
    private Paint linePaint;
    private float middleX;
    private float middleY;
    private List<ChartRawMeasurementPoint> rawMeasurementPoints;
    private List<List<ChartRawMeasurementPoint>> rawMeasurementPointsLists;
    private List<ChartStripe> stripes;
    private int strokeWidth;
    private Paint textPaint;
    private SparseBooleanArray toolbarButtonsState;

    ChartLineViewElement(List<List<ChartRawMeasurementPoint>> pointsLists, List<ChartRawMeasurementPoint> points, List<ChartStripe> stripes, long minXValue, long maxXValue, float minTemperatureYValue, float maxTemperatureYValue, boolean callForHeatState, boolean isFullDay) {
        this.minXValue = minXValue;
        this.maxXValue = maxXValue;
        this.minTemperatureYValue = minTemperatureYValue;
        this.maxTemperatureYValue = maxTemperatureYValue;
        this.rawMeasurementPointsLists = pointsLists;
        this.stripes = stripes;
        this.rawMeasurementPoints = points;
        this.toolbarButtonsState = new SparseBooleanArray(1);
        this.toolbarButtonsState.put(ToolbarButtonTypeEnum.CALL_FOR_HEAT.ordinal(), callForHeatState);
        this.lineAndUnderLinePaths = new ArrayList();
        this.isFullDay = isFullDay;
    }

    public void drawViewElement(Canvas canvas) {
        initPathsAndDrawLine(canvas);
    }

    public void init(ChartInfoInterface chartInfo) {
        super.init(chartInfo);
        this.textPaint = ChartUtils.getPaint((int) C0676R.color.report_no_remote_active, 1.0f, Style.FILL);
        this.textPaint.setTextSize(ReportViewElement.getDp(14.0f));
        this.middleY = chartInfo.getCanvasTopY() + ((chartInfo.getCanvasBottomY() - chartInfo.getCanvasTopY()) / 2.0f);
        this.middleX = ((float) (chartInfo.getChartRightX() - chartInfo.getChartLeftX())) / 2.0f;
        this.iconPadding = ReportViewElement.getDp(8.0f);
        this.fillPaint = ChartUtils.getPaint((int) C0676R.color.white);
        this.fillPaint.setShader(new LinearGradient(0.0f, chartInfo.getCanvasTopY(), 0.0f, chartInfo.getCanvasBottomY(), ColorUtils.setAlphaComponent(ContextCompat.getColor(TadoApplication.getTadoAppContext(), C0676R.color.report_axis_text_color), 33), ColorUtils.setAlphaComponent(ContextCompat.getColor(TadoApplication.getTadoAppContext(), C0676R.color.report_axis_text_color), 0), TileMode.CLAMP));
        this.linePaint = ChartUtils.getPaint((int) C0676R.color.report_axis_text_color, ChartUtils.getDIPValue(2.0f), Style.STROKE);
        this.linePaint.setStrokeJoin(Join.ROUND);
        initCoordinates();
        initPaths();
    }

    private List<Pair<Path, Path>> initPaths() {
        long lastX = 0;
        for (List<ChartPoint> tempPoints : this.chartPoints) {
            Path path = new Path();
            Path underLineFillPath = new Path();
            for (ChartPoint point : tempPoints) {
                if (path.isEmpty()) {
                    path.moveTo((float) point.getX(), point.getY());
                    underLineFillPath.moveTo((float) point.getX(), this.chartInfo.getCanvasBottomY());
                    underLineFillPath.lineTo((float) point.getX(), point.getY());
                }
                path.lineTo((float) point.getX(), point.getY());
                underLineFillPath.lineTo((float) point.getX(), point.getY());
                lastX = point.getX();
            }
            underLineFillPath.lineTo((float) lastX, this.chartInfo.getCanvasBottomY());
            this.lineAndUnderLinePaths.add(new Pair(path, underLineFillPath));
        }
        return this.lineAndUnderLinePaths;
    }

    private void initPathsAndDrawLine(Canvas canvas) {
        if (this.stripes != null && this.stripes.size() == 1 && this.isFullDay && !this.inspectionModeActive && (((ChartStripe) this.stripes.get(0)).hasNoMeasureData() || ((ChartStripe) this.stripes.get(0)).isDayReportUnavailable())) {
            drawAllDay(canvas, (ChartStripe) this.stripes.get(0));
            return;
        }
        if (!this.lineAndUnderLinePaths.isEmpty()) {
            for (Pair<Path, Path> pathPair : this.lineAndUnderLinePaths) {
                drawLinePaths(canvas, (Path) pathPair.first);
                if (!this.inspectionModeActive) {
                    canvas.drawPath((Path) pathPair.second, this.fillPaint);
                }
            }
        }
        if (this.debugMode) {
            for (ChartRawMeasurementPoint point : this.rawMeasurementPoints) {
                canvas.drawPoint((float) getXCoordinate(point.getTimestamp()), getYCoordinate(point.getMeasurement()), ChartUtils.getPaint(point.isInterpolated() ? C0676R.color.ac_red : C0676R.color.cooling_blue, (float) Constants.MAX_OFFSET_CELSIUS, Style.FILL_AND_STROKE));
            }
        }
    }

    private Bitmap getAllDayNoConnectionIcon() {
        if (this.allDayNoConnectionIcon == null) {
            this.allDayNoConnectionIcon = ChartResources.getScaledCompatBitmap(C0676R.drawable.all_day_no_connection, TadoApplication.getTadoAppContext(), (int) ChartUtils.getDIPValue(BitmapDescriptorFactory.HUE_YELLOW));
        }
        return this.allDayNoConnectionIcon;
    }

    private void drawAllDay(Canvas canvas, ChartStripe stripe) {
        String firstLine = "";
        Bitmap icon = null;
        if (stripe.isDayReportUnavailable()) {
            firstLine = this.chartInfo.getContext().getResources().getString(C0676R.string.report_data_noDataAvailableLabel);
        } else if (stripe.hasNoMeasureData()) {
            firstLine = this.chartInfo.getContext().getResources().getString(C0676R.string.report_data_noRemoteAccessLabel);
            icon = getAllDayNoConnectionIcon();
        }
        float xLine1 = this.middleX - (this.textPaint.measureText(firstLine) / 2.0f);
        float yLine1 = this.middleY;
        canvas.drawText(firstLine, xLine1, yLine1, this.textPaint);
        if (icon != null) {
            canvas.drawBitmap(icon, this.middleX - (((float) icon.getWidth()) / 2.0f), (yLine1 - ((float) icon.getHeight())) - this.iconPadding, null);
        }
    }

    private void initCoordinates() {
        this.chartPoints = new ArrayList();
        List<ChartPoint> tempPoints = new ArrayList();
        for (List<ChartRawMeasurementPoint> pointList : this.rawMeasurementPointsLists) {
            for (ChartRawMeasurementPoint point : pointList) {
                tempPoints.add(new ChartPoint(getXCoordinate(point.getTimestamp()), getYCoordinate(point.getMeasurement())));
            }
            this.chartPoints.add(tempPoints);
            tempPoints = new ArrayList();
        }
    }

    private void drawLinePaths(Canvas canvas, Path path) {
        if (path == null) {
            Snitcher.start().log(3, ChartLineViewElement.class.getCanonicalName(), "path null in drawLinePaths", new Object[0]);
        } else {
            canvas.drawPath(path, this.linePaint);
        }
    }

    public int getStrokeWidth() {
        return this.strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public float calculateCoordinate(float value, CoordinateType type) {
        if (CoordinateType.X_COORDINATE == type) {
            return ChartUtils.calculateCoordinate((float) this.minXValue, (float) this.maxXValue, (float) (this.chartInfo.getChartRightX() - this.chartInfo.getChartLeftX()), value, type);
        }
        return ChartUtils.calculateCoordinate(this.minTemperatureYValue, this.maxTemperatureYValue, this.chartInfo.getChartBottomY() - this.chartInfo.getChartTopY(), value, type);
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
}
