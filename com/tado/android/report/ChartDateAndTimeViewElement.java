package com.tado.android.report;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.Pair;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import com.tado.android.entities.WeatherEnum;
import com.tado.android.report.ChartUtils.CoordinateType;
import com.tado.android.report.ReportViewElement.ReportViewElementType;
import com.tado.android.report.interfaces.ChartInfoInterface;
import com.tado.android.report.model.ChartWeatherInterval;
import com.tado.android.rest.model.ReportWeatherConditionValue;
import com.tado.android.utils.ResourceFactory;
import com.tado.android.utils.WeatherUtils;
import java.util.List;

class ChartDateAndTimeViewElement extends ReportViewElement {
    private final float DEFAULT_PADDING = ReportViewElement.getDp(4.0f);
    private Rect bounds;
    private Bitmap currentWeatherBitmap;
    private String dayText = "";
    private Bitmap dropdownBitmap;
    private float leftPadding = this.DEFAULT_PADDING;
    private float leftStartPadding;
    private TextPaint textPaint;
    private List<ChartWeatherInterval> weatherIntervals;
    private Pair<String, WeatherEnum> weatherPair;
    private TextPaint weatherTextPaint;
    private Bitmap zoneIconBitmap;
    @DrawableRes
    private int zoneIconRes;
    private float zoneIconSize = ReportViewElement.getDp(25.0f);
    private String zoneName;

    public ChartDateAndTimeViewElement(String zoneName, @DrawableRes int zoneIconRes) {
        this.zoneName = zoneName;
        this.zoneIconRes = zoneIconRes;
    }

    public void init(ChartInfoInterface chartInfo) {
        super.init(chartInfo);
        this.zoneIconBitmap = ChartResources.getScaledCompatBitmap(this.zoneIconRes, TadoApplication.getTadoAppContext(), (int) this.zoneIconSize);
        this.dropdownBitmap = ChartResources.getCompatBitmap(C0676R.drawable.ic_arrow_drop_down_black_24dp, TadoApplication.getTadoAppContext());
        initTextPaint();
        initWeatherTextPaint();
    }

    private void initTextPaint() {
        this.textPaint = ChartUtils.getTextPaint(C0676R.color.report_done_button_text, 1.0f, Style.FILL);
        this.textPaint.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(TadoApplication.getTadoAppContext(), C0676R.color.report_done_button_text), Mode.SRC_ATOP));
        this.textPaint.setStrokeWidth(ReportViewElement.getDp(0.5f));
        this.textPaint.setTextSize(ReportViewElement.getDp(16.0f));
    }

    private void initWeatherTextPaint() {
        this.weatherTextPaint = ChartUtils.getTextPaint(C0676R.color.report_done_button_text, 1.0f, Style.FILL);
        this.weatherTextPaint.setTextSize(ReportViewElement.getDp(12.0f));
        this.bounds = new Rect();
        String measuringWeatherText = "26°";
        this.weatherTextPaint.getTextBounds(measuringWeatherText, 0, measuringWeatherText.length(), this.bounds);
    }

    public void drawViewElement(Canvas canvas) {
        String text;
        if (this.inspectionModeActive) {
            text = ChartUtils.formatTime(getTimeFromXCoordinate((float) this.touchedX));
        } else {
            text = this.dayText;
        }
        this.textPaint.getTextBounds(text, 0, text.length(), new Rect());
        float middleY = this.chartInfo.getCanvasTopY() / 2.0f;
        float textY = ChartUtils.getTextYForMiddleAlignment(middleY, text, this.textPaint);
        float iconY = middleY - (((float) this.zoneIconBitmap.getHeight()) / 2.0f);
        this.leftStartPadding = ((float) this.chartInfo.getChartLeftX()) + ReportViewElement.getDp(20.0f);
        canvas.drawLine(0.0f, this.chartInfo.getCanvasTopY(), (float) (this.chartInfo.getChartRightX() + this.chartInfo.getChartLeftX()), this.chartInfo.getCanvasTopY(), this.textPaint);
        if (this.inspectionModeActive) {
            this.weatherPair = getWeatherCondition((float) this.touchedX);
            if (this.weatherPair != null) {
                drawWeatherIconAndText(canvas, textY, iconY);
                this.leftPadding = (this.textPaint.measureText((String) this.weatherPair.first, 0, ((String) this.weatherPair.first).length()) + (this.leftStartPadding + ((float) this.currentWeatherBitmap.getWidth()))) + ReportViewElement.getDp(2.0f);
            }
            drawCenterText(text, canvas, determineTextPositionForTouchPoint(text), textY);
            return;
        }
        float centerTextX = (((float) (this.chartInfo.getChartRightX() - this.chartInfo.getChartLeftX())) / 2.0f) - (this.textPaint.measureText(text) / 2.0f);
        float textStart = (this.leftStartPadding + ((float) this.zoneIconBitmap.getWidth())) + ReportViewElement.getDp(4.0f);
        String zoneNameDisplay = TextUtils.ellipsize(this.zoneName, this.textPaint, centerTextX - textStart, TruncateAt.END).toString();
        drawZoneIconAndName(canvas, ChartUtils.getTextYForMiddleAlignment(middleY, zoneNameDisplay, this.textPaint), iconY, textStart, zoneNameDisplay);
        this.textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        drawCenterText(text, canvas, centerTextX, textY);
        float dropdownStart = (((float) (this.chartInfo.getChartRightX() - this.chartInfo.getChartLeftX())) / 2.0f) + (this.textPaint.measureText(text) / 2.0f);
        canvas.drawBitmap(this.dropdownBitmap, dropdownStart, iconY, this.textPaint);
        this.textPaint.setTypeface(Typeface.DEFAULT);
    }

    private float determineTextPositionForTouchPoint(String text) {
        float resultX = ((float) this.touchedX) - (this.textPaint.measureText(text) / 2.0f);
        float centerTextTouchedXRight = resultX + this.textPaint.measureText(text);
        if (resultX < this.leftPadding) {
            return this.leftPadding;
        }
        if (this.DEFAULT_PADDING + centerTextTouchedXRight > ((float) this.chartInfo.getChartRightX())) {
            return (((float) this.chartInfo.getChartRightX()) - this.DEFAULT_PADDING) - this.textPaint.measureText(text);
        }
        return resultX;
    }

    private void drawZoneIconAndName(Canvas canvas, float textY, float iconY, float textStart, String text) {
        canvas.drawBitmap(this.zoneIconBitmap, this.leftStartPadding, iconY, this.textPaint);
        canvas.drawText(text, textStart, textY, this.textPaint);
    }

    private void drawWeatherIconAndText(Canvas canvas, float textY, float iconY) {
        this.currentWeatherBitmap = ChartResources.getBitmap(ResourceFactory.getWeatherResource((WeatherEnum) this.weatherPair.second), (int) this.zoneIconSize, TadoApplication.getTadoAppContext());
        canvas.drawBitmap(this.currentWeatherBitmap, this.leftStartPadding, iconY, this.textPaint);
        canvas.drawText((String) this.weatherPair.first, (this.leftStartPadding + ReportViewElement.getDp(2.0f)) + ((float) this.currentWeatherBitmap.getWidth()), textY, this.weatherTextPaint);
    }

    private void drawCenterText(String text, Canvas canvas, float x, float y) {
        canvas.drawText(text, x, y, this.textPaint);
    }

    public ChartInfoInterface getChartInfoInterface() {
        return this.chartInfo;
    }

    public ReportViewElementType getType() {
        return ReportViewElementType.CHART_DATE_AND_TIME;
    }

    public void setSelectedDay(String dayText) {
        this.dayText = dayText;
    }

    private Pair<String, WeatherEnum> getWeatherCondition(float x) {
        int position = 0;
        if (x >= calculateCoordinate((float) ((ChartWeatherInterval) this.weatherIntervals.get(0)).getRange().getStart(), CoordinateType.X_COORDINATE)) {
            if (x <= calculateCoordinate((float) ((ChartWeatherInterval) this.weatherIntervals.get(this.weatherIntervals.size() - 1)).getRange().getStart(), CoordinateType.X_COORDINATE)) {
                while (position < this.weatherIntervals.size()) {
                    ChartWeatherInterval weatherInterval = (ChartWeatherInterval) this.weatherIntervals.get(position);
                    if (x >= calculateCoordinate((float) weatherInterval.getRange().getStart(), CoordinateType.X_COORDINATE) && x <= calculateCoordinate((float) weatherInterval.getRange().getEnd(), CoordinateType.X_COORDINATE)) {
                        break;
                    }
                    position++;
                }
            } else {
                position = this.weatherIntervals.size() - 1;
            }
        } else {
            position = 0;
        }
        ReportWeatherConditionValue weatherConditionValue = ((ChartWeatherInterval) this.weatherIntervals.get(position)).getWeatherConditionValue();
        return new Pair(WeatherUtils.getFormattedTemperatureForWeatherConditionValue(weatherConditionValue), WeatherUtils.getWetherEnumForWeatherConditionValue(weatherConditionValue));
    }

    public void setWeatherIntervals(List<ChartWeatherInterval> weatherIntervals) {
        this.weatherIntervals = weatherIntervals;
    }
}
