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
import java.util.List;

class ChartHotWaterProductionViewElement extends ReportViewElement implements ChartToolbarCommandInterface {
    private Paint gradientPaint;
    private Bitmap hotWaterIcon;
    private List<ChartXValueRange> hotWaterProductionList;
    private Path path;
    private SparseBooleanArray toolbarButtonsState = new SparseBooleanArray(1);

    ChartHotWaterProductionViewElement(List<ChartXValueRange> hotWaterProductionList, long minXValue, long maxXValue, float minYValue, float maxYValue, boolean hotWaterProductionState) {
        this.minXValue = minXValue;
        this.maxXValue = maxXValue;
        this.minTemperatureYValue = minYValue;
        this.maxTemperatureYValue = maxYValue;
        this.hotWaterProductionList = hotWaterProductionList;
        this.toolbarButtonsState.put(ToolbarButtonTypeEnum.HOT_WATER.ordinal(), hotWaterProductionState);
    }

    public void init(ChartInfoInterface chartInfo) {
        super.init(chartInfo);
        this.gradientPaint = new Paint();
        this.gradientPaint.setShader(new LinearGradient((float) chartInfo.getChartLeftX(), chartInfo.getCanvasTopY(), (float) chartInfo.getChartLeftX(), chartInfo.getChartBottomY(), ColorUtils.setAlphaComponent(ContextCompat.getColor(TadoApplication.getTadoAppContext(), C0676R.color.report_hot_water), 76), ColorUtils.setAlphaComponent(ContextCompat.getColor(TadoApplication.getTadoAppContext(), C0676R.color.report_hot_water), 178), TileMode.CLAMP));
        this.path = new Path();
        this.hotWaterIcon = ChartResources.getScaledCompatBitmap(C0676R.drawable.chart_icon_hot_water, TadoApplication.getTadoAppContext(), (int) ChartUtils.getDIPValue(22.0f));
    }

    public void drawViewElement(Canvas canvas) {
        if (this.toolbarButtonsState.get(ToolbarButtonTypeEnum.HOT_WATER.ordinal()) && !this.inspectionModeActive) {
            for (ChartXValueRange range : this.hotWaterProductionList) {
                this.path = new Path();
                float startX = calculateCoordinate((float) range.getStart(), CoordinateType.X_COORDINATE);
                float endX = calculateCoordinate((float) range.getEnd(), CoordinateType.X_COORDINATE);
                this.path.moveTo(startX, this.chartInfo.getCanvasBottomY());
                this.path.lineTo(startX, this.chartInfo.getCanvasTopY());
                this.path.lineTo(endX, this.chartInfo.getCanvasTopY());
                this.path.lineTo(endX, this.chartInfo.getCanvasBottomY());
                canvas.drawPath(this.path, this.gradientPaint);
                float hotWaterAreaWidth = endX - startX;
                float middleX = startX + (hotWaterAreaWidth / 2.0f);
                if (this.hotWaterIcon != null && ((float) this.hotWaterIcon.getWidth()) + ReportViewElement.getDp(2.0f) < hotWaterAreaWidth) {
                    canvas.drawBitmap(this.hotWaterIcon, middleX - (((float) this.hotWaterIcon.getWidth()) / 2.0f), (this.chartInfo.getCanvasBottomY() - (this.chartInfo.getBottomPadding() * 2.0f)) - ((float) this.hotWaterIcon.getHeight()), null);
                }
            }
        }
    }

    public ChartInfoInterface getChartInfoInterface() {
        return this.chartInfo;
    }

    public ReportViewElementType getType() {
        return ReportViewElementType.CHART_HOT_WATER_PRODUCTION;
    }

    public void execute(Pair<ToolbarButtonTypeEnum, Boolean> state) {
        this.toolbarButtonsState.put(((ToolbarButtonTypeEnum) state.first).ordinal(), ((Boolean) state.second).booleanValue());
    }
}
