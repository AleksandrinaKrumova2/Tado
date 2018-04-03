package com.tado.android.report;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.TypedValue;
import com.tado.android.app.TadoApplication;
import com.tado.android.report.model.ChartRawMeasurementPoint;
import com.tado.android.utils.Constants;
import com.tado.android.utils.TimeUtils;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class ChartUtils {

    static class C10521 implements Comparator<ChartRawMeasurementPoint> {
        C10521() {
        }

        public int compare(ChartRawMeasurementPoint lhs, ChartRawMeasurementPoint rhs) {
            return (int) (lhs.getTimestamp() - rhs.getTimestamp());
        }
    }

    public enum CoordinateType {
        X_COORDINATE,
        Y_COORDINATE
    }

    public static Paint getPaint(String color, float strokeWidth, Style style) {
        Paint paint = getPaint(color);
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(style);
        return paint;
    }

    public static Paint getPaint(@ColorRes int color, float strokeWidth, Style style) {
        Paint paint = getPaint(color);
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(style);
        return paint;
    }

    public static Paint getPaint(String color) {
        Paint paint = getPaint();
        paint.setColor(Color.parseColor(color));
        return paint;
    }

    public static Paint getPaint(int colorResource) {
        Paint paint = getPaint();
        paint.setColor(ContextCompat.getColor(TadoApplication.getTadoAppContext(), colorResource));
        return paint;
    }

    private static Paint getPaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Style.FILL);
        return paint;
    }

    public static TextPaint getTextPaint(@ColorRes int color, float strokeWidth, Style style) {
        TextPaint paint = new TextPaint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Style.FILL);
        paint.setColor(ContextCompat.getColor(TadoApplication.getTadoAppContext(), color));
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(style);
        return paint;
    }

    public static float calculateCoordinate(float min, float max, float sizeInPixels, float value, CoordinateType coordinateType) {
        float length = max - min;
        if (length == 0.0f) {
            return 0.0f;
        }
        if (coordinateType == CoordinateType.Y_COORDINATE) {
            return sizeInPixels - (((value - min) / length) * sizeInPixels);
        }
        return ((value - min) / length) * sizeInPixels;
    }

    public static long calculateXCoordinate(long min, long max, long sizeInPixels, long value) {
        long length = max - min;
        if (length == 0) {
            return 0;
        }
        return (long) ((((float) (value - min)) / (((float) length) * 1.0f)) * ((float) sizeInPixels));
    }

    public static long getTimeFromXCoordinate(long min, long max, float sizeInPixels, float x, long startX) {
        return (long) ((((x - ((float) startX)) * ((float) (max - min))) / (1.0f * sizeInPixels)) + ((float) min));
    }

    public static String formatTime(long time) {
        return DateTimeFormat.forPattern("HH:mm").print(time);
    }

    public static float getTimeInHours(long time) {
        return ((float) Math.round((((float) time) / 3600000.0f) * Constants.MAX_OFFSET_CELSIUS)) / Constants.MAX_OFFSET_CELSIUS;
    }

    public static float getDIPValue(float value) {
        return TadoApplication.getDensityFactor() * value;
    }

    public static float dpToPx(float dp, Context context) {
        return TypedValue.applyDimension(1, dp, context.getResources().getDisplayMetrics());
    }

    public static void sortPoints(List<ChartRawMeasurementPoint> unsortedPoints) {
        Collections.sort(unsortedPoints, new C10521());
    }

    public static DateTime parseTimestampWithHomeTimeZone(String timestamp) {
        return new DateTime((Object) timestamp, TimeUtils.getHomeTimeZone());
    }

    public static ChartRawMeasurementPoint interpolateRawMeasurementPoint(@NonNull ChartRawMeasurementPoint interpolatedPoint, ChartRawMeasurementPoint previousPoint, ChartRawMeasurementPoint nextPoint) {
        if (nextPoint != null && (previousPoint == null || interpolatedPoint.getTimestamp() == nextPoint.getTimestamp())) {
            interpolatedPoint.setMeasurement(nextPoint.getMeasurement());
        } else if (previousPoint == null || !(nextPoint == null || interpolatedPoint.getTimestamp() == previousPoint.getTimestamp())) {
            interpolatedPoint.setInterpolated(true);
            float firstWeight = (float) (interpolatedPoint.getTimestamp() - previousPoint.getTimestamp());
            float secondWeight = (float) (nextPoint.getTimestamp() - interpolatedPoint.getTimestamp());
            interpolatedPoint.setMeasurement(((previousPoint.getMeasurement() * secondWeight) + (nextPoint.getMeasurement() * firstWeight)) / (firstWeight + secondWeight));
        } else {
            interpolatedPoint.setMeasurement(previousPoint.getMeasurement());
        }
        return interpolatedPoint;
    }

    public static boolean isCurrentDay(DateTime selectedDate) {
        return selectedDate.toLocalDate().equals(new DateTime().toLocalDate());
    }

    static float getTextYForMiddleAlignment(float middleY, String text, Paint textPaint) {
        Rect bounds = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), bounds);
        return middleY - (((float) bounds.top) / 2.0f);
    }
}
