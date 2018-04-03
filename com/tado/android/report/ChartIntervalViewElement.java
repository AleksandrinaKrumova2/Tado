package com.tado.android.report;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import com.tado.android.report.ReportViewElement.ReportViewElementType;
import com.tado.android.report.interfaces.ChartInfoInterface;
import com.tado.android.rest.model.ReportDataPoint;
import com.tado.android.rest.model.Temperature;
import com.tado.android.utils.TimeUtils;
import java.util.Locale;
import org.joda.time.DateTime;

class ChartIntervalViewElement extends ReportViewElement {
    private DateTime fromIntervalDate;
    private boolean is24HoursFormat;
    private ReportDataPoint<Temperature> lastDataPoint;
    private long maxXAxisValue;
    private long minXAxisValue;
    private float paddingX;
    private float paddingY;
    private Paint paint;
    private DateTime selectedDate;
    private String text = "";
    private float textHeight;
    private float textWidth;
    private DateTime toIntervalDate;
    private long f400x;
    private float f401y;

    ChartIntervalViewElement(DateTime selectedDate, DateTime toIntervalDate, DateTime fromIntervalDate, ReportDataPoint<Temperature> doubleDataPoint, long minXAxisValue, long maxXAxisValue, boolean is24HoursFormat) {
        this.selectedDate = selectedDate;
        this.toIntervalDate = toIntervalDate;
        this.fromIntervalDate = fromIntervalDate;
        this.minXAxisValue = minXAxisValue;
        this.maxXAxisValue = maxXAxisValue;
        this.lastDataPoint = doubleDataPoint;
        this.is24HoursFormat = is24HoursFormat;
    }

    public void init(ChartInfoInterface chartInfo) {
        super.init(chartInfo);
        this.paint = ChartUtils.getPaint("#FF92A8B4", ChartUtils.getDIPValue(0.5f), Style.FILL);
        this.paint.setTextSize(ChartUtils.getDIPValue(11.0f));
        this.paddingX = ChartUtils.getDIPValue(8.0f);
        this.paddingY = ChartUtils.getDIPValue(12.0f);
        this.text = TimeUtils.formatTimeToAmPm(this.toIntervalDate.getHourOfDay(), this.toIntervalDate.getMinuteOfHour(), this.is24HoursFormat);
        if (this.lastDataPoint != null) {
            this.text += String.format(Locale.US, " %.1f°", new Object[]{Float.valueOf(((Temperature) this.lastDataPoint.getValue()).getTemperatureValue())});
        }
        Rect bounds = new Rect();
        this.paint.getTextBounds(this.text, 0, this.text.length(), bounds);
        this.textHeight = (float) Math.abs(bounds.height());
        this.textWidth = this.paint.measureText(this.text);
        this.f401y = getChartInfoInterface().getCanvasBottomY() + getChartInfoInterface().getBottomPadding();
    }

    public void drawViewElement(Canvas canvas) {
        if (this.selectedDate.toLocalDate().equals(new DateTime().toLocalDate())) {
            this.f400x = getX(this.toIntervalDate);
            canvas.drawLine((float) this.f400x, this.f401y, (float) this.f400x, getChartInfoInterface().getCanvasTopY(), this.paint);
            if (!this.inspectionModeActive && (((float) this.f400x) + this.paddingX) + this.textWidth < ((float) getChartInfoInterface().getChartRightX())) {
                this.f400x = (long) (((float) this.f400x) + this.paddingX);
                canvas.drawText(this.text, (float) this.f400x, (getChartInfoInterface().getCanvasTopY() + this.textHeight) + this.paddingY, this.paint);
            }
        }
        if (this.selectedDate.toLocalDate().equals(this.fromIntervalDate.toLocalDate())) {
            this.f400x = getX(this.fromIntervalDate);
            Canvas canvas2 = canvas;
            canvas2.drawLine((float) this.f400x, getChartInfoInterface().getBottomPadding() + getChartInfoInterface().getCanvasBottomY(), (float) this.f400x, getChartInfoInterface().getCanvasTopY(), this.paint);
        }
    }

    public ChartInfoInterface getChartInfoInterface() {
        return this.chartInfo;
    }

    public ReportViewElementType getType() {
        return ReportViewElementType.CHART_INTERVAL_VIEW;
    }

    private long getX(DateTime date) {
        return ChartUtils.calculateXCoordinate(this.minXAxisValue, this.maxXAxisValue, getChartInfoInterface().getChartRightX() - getChartInfoInterface().getChartLeftX(), date.getMillis());
    }
}
