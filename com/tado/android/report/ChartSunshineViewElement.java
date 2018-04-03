package com.tado.android.report;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader.TileMode;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.util.Pair;
import android.util.SparseBooleanArray;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import com.tado.android.report.ChartToolbarViewElement.ToolbarButtonTypeEnum;
import com.tado.android.report.ChartUtils.CoordinateType;
import com.tado.android.report.ReportViewElement.ReportViewElementType;
import com.tado.android.report.interfaces.ChartInfoInterface;
import com.tado.android.report.interfaces.ChartToolbarCommandInterface;
import com.tado.android.report.model.ChartXValueRange;
import java.util.ArrayList;
import java.util.List;

class ChartSunshineViewElement extends ReportViewElement implements ChartToolbarCommandInterface {
    private float endX;
    private Paint gradientPaint;
    private float iconY;
    private float middleX;
    private Path path;
    private float startX;
    private float sunAreaWidth;
    private Bitmap sunIcon;
    private float sunPadding;
    private List<Pair<Path, Integer>> sunshinePaths;
    private List<ChartXValueRange> sunshineRange;
    private SparseBooleanArray toolbarButtonsState = new SparseBooleanArray(1);
    private float f402y;

    ChartSunshineViewElement(List<ChartXValueRange> sunshineRange, long minXValue, long maxXValue, float minYValue, float maxYValue, boolean sunshineState) {
        this.minXValue = minXValue;
        this.maxXValue = maxXValue;
        this.minTemperatureYValue = minYValue;
        this.maxTemperatureYValue = maxYValue;
        this.sunshineRange = sunshineRange;
        this.toolbarButtonsState.put(ToolbarButtonTypeEnum.WEATHER.ordinal(), sunshineState);
        this.sunshinePaths = new ArrayList(0);
    }

    public void drawViewElement(Canvas canvas) {
        if (this.toolbarButtonsState.get(ToolbarButtonTypeEnum.WEATHER.ordinal()) && !this.inspectionModeActive && this.sunshinePaths.size() > 0) {
            for (Pair<Path, Integer> pathIntegerPair : this.sunshinePaths) {
                canvas.drawPath((Path) pathIntegerPair.first, this.gradientPaint);
                if (((Integer) pathIntegerPair.second).intValue() != 0) {
                    canvas.drawBitmap(getSunIcon(), (float) ((Integer) pathIntegerPair.second).intValue(), this.iconY, null);
                }
            }
        }
    }

    public void init(ChartInfoInterface chartInfo) {
        super.init(chartInfo);
        this.elementX = chartInfo.getChartLeftX();
        this.elementY = chartInfo.getCanvasTopY() - 20.0f;
        this.elementRadius = 25.0f;
        this.sunPadding = ReportViewElement.getDp(8.0f);
        this.gradientPaint = new Paint();
        this.f402y = chartInfo.getChartTopY();
        this.gradientPaint.setShader(new LinearGradient((float) chartInfo.getChartLeftX(), chartInfo.getCanvasTopY(), (float) chartInfo.getChartLeftX(), this.f402y, ColorUtils.setAlphaComponent(ContextCompat.getColor(TadoApplication.getTadoAppContext(), C0676R.color.report_sunshine), 77), ColorUtils.setAlphaComponent(ContextCompat.getColor(TadoApplication.getTadoAppContext(), C0676R.color.report_sunshine), 0), TileMode.CLAMP));
        this.sunshinePaths = initPaths(this.sunshineRange);
        this.iconY = chartInfo.getCanvasTopY() + this.sunPadding;
    }

    public ChartInfoInterface getChartInfoInterface() {
        return this.chartInfo;
    }

    public ReportViewElementType getType() {
        return ReportViewElementType.CHART_SUNSHINE;
    }

    public void execute(Pair<ToolbarButtonTypeEnum, Boolean> state) {
        this.toolbarButtonsState.put(((ToolbarButtonTypeEnum) state.first).ordinal(), ((Boolean) state.second).booleanValue());
    }

    private List<Pair<Path, Integer>> initPaths(List<ChartXValueRange> sunshineRange) {
        float sunIconWidth = ((float) getSunIcon().getWidth()) + ReportViewElement.getDp(2.0f);
        for (ChartXValueRange range : sunshineRange) {
            this.path = new Path();
            this.startX = calculateCoordinate((float) range.getStart(), CoordinateType.X_COORDINATE);
            this.endX = calculateCoordinate((float) range.getEnd(), CoordinateType.X_COORDINATE);
            this.path.moveTo(this.startX, this.f402y);
            this.path.lineTo(this.startX, this.chartInfo.getCanvasTopY());
            this.path.lineTo(this.endX, this.chartInfo.getCanvasTopY());
            this.path.lineTo(this.endX, this.f402y);
            this.sunAreaWidth = this.endX - this.startX;
            this.middleX = 0.0f;
            if (sunIconWidth < this.sunAreaWidth) {
                this.middleX = (this.startX + (this.sunAreaWidth / 2.0f)) - (((float) getSunIcon().getWidth()) / 2.0f);
            }
            this.sunshinePaths.add(new Pair(this.path, Integer.valueOf((int) this.middleX)));
        }
        return this.sunshinePaths;
    }

    private Bitmap getSunIcon() {
        if (this.sunIcon == null) {
            this.sunIcon = ChartResources.getScaledCompatBitmap(C0676R.drawable.chart_icon_sun, TadoApplication.getTadoAppContext(), (int) ChartUtils.getDIPValue(20.0f));
        }
        return this.sunIcon;
    }
}
