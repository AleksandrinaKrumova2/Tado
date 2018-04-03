package com.tado.android.report;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import com.tado.C0676R;
import com.tado.android.report.ChartUtils.CoordinateType;
import com.tado.android.report.ReportViewElement.ReportViewElementType;
import com.tado.android.report.interfaces.ChartInfoInterface;
import com.tado.android.utils.TimeUtils;
import java.util.List;
import org.joda.time.DateTime;

class ChartHorizontalAxisViewElement extends ReportViewElement {
    private float axisWidth;
    private Rect bounds;
    private boolean is24HourFormat;
    private int item;
    private String itemString;
    private List<Integer> labelList;
    private float maxValue;
    private float minValue;
    private float padding;
    private Paint paint;
    private DateTime selectedDate;
    private float textSize = 11.0f;
    private float textWidth = 1.0f;
    private float f399x;

    ChartHorizontalAxisViewElement(List<Integer> labelList, DateTime selectedDate, boolean is24HourFormat) {
        this.labelList = labelList;
        this.selectedDate = selectedDate;
        this.is24HourFormat = is24HourFormat;
    }

    public void drawViewElement(Canvas canvas) {
        if (this.chartInfo.isHorizontalAxisTextVisible() && !this.inspectionModeActive && this.labelList.size() > 0) {
            this.paint.getTextBounds(Integer.toString(((Integer) this.labelList.get(0)).intValue()), 0, 1, this.bounds);
            for (int i = 0; i < this.labelList.size(); i++) {
                this.item = ((Integer) this.labelList.get(i)).intValue();
                this.itemString = TimeUtils.formatTimeToAmPm(this.item, 0, this.is24HourFormat);
                this.f399x = ((float) getXCoordinate(this.selectedDate.withTimeAtStartOfDay().plusHours(this.item).getMillis())) - (this.paint.measureText(this.itemString) / 2.0f);
                canvas.drawText(this.itemString, this.f399x, this.chartInfo.getCanvasBottomY() - this.padding, this.paint);
            }
        }
    }

    public float getTextSize() {
        return this.textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public void init(ChartInfoInterface chartInfo) {
        super.init(chartInfo);
        this.bounds = new Rect();
        this.axisWidth = (float) (chartInfo.getChartRightX() - chartInfo.getChartLeftX());
        this.paint = ChartUtils.getPaint((int) C0676R.color.report_axis_text_color, this.textWidth, Style.FILL);
        this.paint.setTextSize(ChartUtils.getDIPValue(this.textSize));
        this.padding = ChartUtils.getDIPValue(6.0f);
    }

    public float calculateCoordinate(float value, CoordinateType type) {
        return ChartUtils.calculateCoordinate(this.minValue, this.maxValue, this.axisWidth, value, type);
    }

    public ChartInfoInterface getChartInfoInterface() {
        return this.chartInfo;
    }

    public ReportViewElementType getType() {
        return ReportViewElementType.CHART_HORIZONTAL_AXIS;
    }

    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }
}
