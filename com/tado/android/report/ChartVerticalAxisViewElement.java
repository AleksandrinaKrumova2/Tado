package com.tado.android.report;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.Pair;
import android.util.SparseBooleanArray;
import com.tado.C0676R;
import com.tado.android.report.ChartToolbarViewElement.ToolbarButtonTypeEnum;
import com.tado.android.report.ChartUtils.CoordinateType;
import com.tado.android.report.ReportViewElement.ReportViewElementType;
import com.tado.android.report.interfaces.ChartInfoInterface;
import com.tado.android.report.interfaces.ChartToolbarCommandInterface;

class ChartVerticalAxisViewElement extends ReportViewElement implements ChartToolbarCommandInterface {
    private float axisHeight;
    private int item;
    private String labelHumidityTextDecorator = "%";
    private String labelString;
    private String labelTextDecorator = "°";
    private Paint linePaint;
    private String maxHumidityLabel;
    private String maxTemperatureLabel;
    private float maxY;
    private float middleY;
    private String minHumidityLabel;
    private String minTemperatureLabel;
    private float minY;
    private boolean noData = false;
    private float padding;
    private String placeholderLabelValue;
    private boolean showGrid = true;
    private boolean showLabels = false;
    private Paint textHumidityPaint;
    private Rect textRect;
    private float textSize = 11.0f;
    private Paint textTemperaturePaint;
    private float textWidth = 1.0f;
    private SparseBooleanArray toolbarButtonsState = new SparseBooleanArray(1);
    private float f405x;

    ChartVerticalAxisViewElement(boolean humidityState) {
        this.toolbarButtonsState.put(ToolbarButtonTypeEnum.HUMIDITY.ordinal(), humidityState);
    }

    public void drawViewElement(Canvas canvas) {
        boolean z = this.chartInfo.isVerticalAxisTextVisible() && !this.inspectionModeActive;
        this.showLabels = z;
        drawHorizontalLines(canvas, (float) this.chartInfo.getChartLeftX(), (float) this.chartInfo.getChartRightX(), this.minY, this.middleY, this.maxY, this.linePaint);
        if (this.showLabels && !this.noData) {
            drawVerticalLineLabel(canvas, this.f405x, this.minY - this.padding, this.minTemperatureLabel, this.textTemperaturePaint);
            drawVerticalLineLabel(canvas, this.f405x, this.maxY - this.padding, this.maxTemperatureLabel, this.textTemperaturePaint);
            if (this.toolbarButtonsState.get(ToolbarButtonTypeEnum.HUMIDITY.ordinal())) {
                drawVerticalLineLabel(canvas, this.f405x, (this.minY + ((float) this.textRect.height())) + this.padding, this.minHumidityLabel, this.textHumidityPaint);
                drawVerticalLineLabel(canvas, this.f405x, (this.maxY + ((float) this.textRect.height())) + this.padding, this.maxHumidityLabel, this.textHumidityPaint);
            }
        }
    }

    private void drawHorizontalLines(Canvas canvas, float startX, float endX, float minY, float middleY, float maxY, Paint paint) {
        canvas.drawLine(startX, minY, endX, minY, paint);
        canvas.drawLine(startX, middleY, endX, middleY, paint);
        canvas.drawLine(startX, maxY, endX, maxY, paint);
    }

    private void drawVerticalLineLabel(Canvas canvas, float x, float y, String label, Paint paint) {
        canvas.drawText(label, x, y, paint);
    }

    private String formatLabel(float value, String decorator) {
        return String.format("%.0f%s", new Object[]{Float.valueOf(value), decorator});
    }

    public float getTextSize() {
        return this.textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public void init(ChartInfoInterface chartInfo) {
        super.init(chartInfo);
        this.axisHeight = chartInfo.getChartBottomY() - chartInfo.getChartTopY();
        this.f405x = ((float) chartInfo.getChartLeftX()) + ReportViewElement.getDp(6.0f);
        this.minY = getYCoordinate(this.minTemperatureYValue);
        this.middleY = getYCoordinate(((this.maxTemperatureYValue - this.minTemperatureYValue) / 2.0f) + this.minTemperatureYValue);
        this.maxY = getYCoordinate(this.maxTemperatureYValue);
        this.minTemperatureLabel = formatLabel(this.minTemperatureYValue, this.labelTextDecorator);
        this.maxTemperatureLabel = formatLabel(this.maxTemperatureYValue, this.labelTextDecorator);
        this.minHumidityLabel = formatLabel(this.minHumidityYValue, this.labelHumidityTextDecorator);
        this.maxHumidityLabel = formatLabel(this.maxHumidityYValue, this.labelHumidityTextDecorator);
        this.linePaint = ChartUtils.getPaint("#4C92A8B4", ReportViewElement.getDp(0.5f), Style.FILL_AND_STROKE);
        this.textTemperaturePaint = ChartUtils.getPaint((int) C0676R.color.report_axis_text_color, this.textWidth, Style.FILL);
        this.textTemperaturePaint.setTextSize(ChartUtils.getDIPValue(this.textSize));
        this.textHumidityPaint = ChartUtils.getPaint((int) C0676R.color.humidity_dash_line, this.textWidth, Style.FILL);
        this.textHumidityPaint.setTextSize(ChartUtils.getDIPValue(this.textSize));
        this.textRect = new Rect();
        this.placeholderLabelValue = "99" + this.labelTextDecorator + this.labelHumidityTextDecorator;
        this.textTemperaturePaint.getTextBounds(this.placeholderLabelValue, 0, this.placeholderLabelValue.length(), this.textRect);
        this.padding = ChartUtils.getDIPValue(3.0f);
    }

    public float calculateCoordinate(float value, CoordinateType type) {
        return ChartUtils.calculateCoordinate(this.minTemperatureYValue, this.maxTemperatureYValue, this.axisHeight, value, type);
    }

    public ChartInfoInterface getChartInfoInterface() {
        return this.chartInfo;
    }

    public ReportViewElementType getType() {
        return ReportViewElementType.CHART_VERTICAL_AXIS;
    }

    public void setNoData(boolean noData) {
        this.noData = noData;
    }

    public void execute(Pair<ToolbarButtonTypeEnum, Boolean> state) {
        this.toolbarButtonsState.put(((ToolbarButtonTypeEnum) state.first).ordinal(), ((Boolean) state.second).booleanValue());
    }
}
