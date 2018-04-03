package com.tado.android.report;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.util.Pair;
import android.util.SparseBooleanArray;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import com.tado.android.report.ChartToolbarViewElement.ToolbarButtonTypeEnum;
import com.tado.android.report.ReportViewElement.ReportViewElementType;
import com.tado.android.report.interfaces.ChartDrawingIntervalInterface;
import com.tado.android.report.interfaces.ChartInfoInterface;
import com.tado.android.report.interfaces.ChartToolbarCommandInterface;
import com.tado.android.report.model.ChartPoint;
import com.tado.android.report.model.ChartRangeValue;
import com.tado.android.report.model.ChartRawMeasurementPoint;
import com.tado.android.report.model.ChartSetting;
import com.tado.android.report.model.ChartStripe;
import com.tado.android.rest.model.CallForHeatEnum;
import com.tado.android.rest.model.StripeTypeEnum;
import com.tado.android.rest.model.Zone.TypeEnum;
import com.tado.android.rest.model.installation.CoolingZoneSetting;
import com.tado.android.rest.model.installation.GenericZoneSetting;
import com.tado.android.rest.model.installation.ModeEnum;
import com.tado.android.utils.Constants;
import com.tado.android.utils.ResourceFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

class ChartInfoLineViewElement extends ReportViewElement implements ChartToolbarCommandInterface {
    private static final String TEMPERATURE_PLACEHOLDER = "-.-°";
    private Rect bounds;
    private List<ChartRangeValue<CallForHeatEnum>> callForHeats;
    private boolean debugMode = false;
    private List<ChartPoint> humidityChartPoints;
    private List<ChartRawMeasurementPoint> humidityRawMeasurementPoints;
    private int lineColor;
    private String measuringPlaceholderText;
    private Map<StripeTypeEnum, String> messagesMap = new HashMap();
    private float rxy;
    private Paint setPointTextPaint;
    private List<ChartSetting> settings;
    private float step;
    private ChartStripe stripe;
    private List<ChartStripe> stripes;
    private float tempY;
    private List<ChartPoint> temperatureChartPoints;
    private List<ChartRawMeasurementPoint> temperatureRawMeasurementPoints;
    private Paint textPaint;
    private SparseBooleanArray toolbarButtonsState;
    private long xValue;
    private TypeEnum zoneType;

    ChartInfoLineViewElement(List<ChartStripe> stripes, List<ChartRawMeasurementPoint> points, List<ChartRawMeasurementPoint> humidityPoints, List<ChartSetting> settingList, List<ChartRangeValue<CallForHeatEnum>> callForHeatList, float minY, float maxY, long minX, long maxX, float minHumidityY, float maxHumidityY, Map<StripeTypeEnum, String> messagesMap, boolean humidityState, float step, TypeEnum zoneType) {
        this.stripes = stripes;
        this.temperatureRawMeasurementPoints = points;
        this.minXValue = minX;
        this.maxXValue = maxX;
        this.minTemperatureYValue = minY;
        this.maxTemperatureYValue = maxY;
        this.minHumidityYValue = minHumidityY;
        this.maxHumidityYValue = maxHumidityY;
        this.messagesMap = messagesMap;
        this.humidityRawMeasurementPoints = humidityPoints;
        this.callForHeats = callForHeatList;
        this.settings = settingList;
        this.step = step;
        this.zoneType = zoneType;
        this.toolbarButtonsState = new SparseBooleanArray(1);
        this.toolbarButtonsState.put(ToolbarButtonTypeEnum.HUMIDITY.ordinal(), humidityState);
    }

    public void init(ChartInfoInterface chartInfo) {
        super.init(chartInfo);
        this.measuringPlaceholderText = TypeEnum.AIR_CONDITIONING == this.zoneType ? "23°" : "23.9°";
        this.textPaint = ChartUtils.getPaint((int) C0676R.color.white, 1.0f, Style.FILL);
        this.textPaint.setTextSize(getTextSize(24.0f));
        this.setPointTextPaint = ChartUtils.getPaint("#FFFFFF", 1.0f, Style.FILL);
        this.setPointTextPaint.setTextSize(getTextSize(14.0f));
        this.bounds = new Rect();
        this.rxy = ReportViewElement.getDp(Constants.MAX_OFFSET_CELSIUS);
        initCharPoints();
    }

    private void initCharPoints() {
        if (!(this.temperatureRawMeasurementPoints == null || this.temperatureRawMeasurementPoints.isEmpty())) {
            this.temperatureChartPoints = getChartPoints(this.temperatureRawMeasurementPoints, false);
        }
        if (this.humidityRawMeasurementPoints != null && !this.humidityRawMeasurementPoints.isEmpty()) {
            this.humidityChartPoints = getChartPoints(this.humidityRawMeasurementPoints, true);
        }
    }

    private List<ChartPoint> getChartPoints(List<ChartRawMeasurementPoint> rawMeasurementPoints, boolean isHumidity) {
        List<ChartPoint> points = new ArrayList(rawMeasurementPoints.size());
        for (ChartRawMeasurementPoint point : rawMeasurementPoints) {
            points.add(new ChartPoint(getXCoordinate(point.getTimestamp()), isHumidity ? getYCoordinate(point.getMeasurement()) : getYCoordinate(point.getMeasurement())));
        }
        return points;
    }

    public void drawViewElement(Canvas canvas) {
        if (this.inspectionModeActive) {
            String temperatureText = getInterpolatedValueText((float) this.touchedX, this.temperatureRawMeasurementPoints, this.temperatureChartPoints, false);
            String humidityText = getInterpolatedValueText((float) this.touchedX, this.humidityRawMeasurementPoints, this.humidityChartPoints, true);
            float leftMiddlePoint = ReportViewElement.getDp(28.0f);
            float rightMiddlePoint = ReportViewElement.getDp(28.0f);
            float height = ReportViewElement.getDp(51.0f);
            float middleHeight = height / 2.0f;
            float topY = this.chartInfo.getCanvasTopY() + ReportViewElement.getDp(6.0f);
            this.textPaint.getTextBounds(this.measuringPlaceholderText, 0, this.measuringPlaceholderText.length(), this.bounds);
            float leftWidth = ReportViewElement.getDp(65.0f);
            float rightWidth = ReportViewElement.getDp(65.0f);
            float padding = ReportViewElement.getDp(7.0f);
            float iconPadding = ReportViewElement.getDp(3.0f);
            float selectedX = (float) this.touchedX;
            if (((float) this.touchedX) - leftWidth < 0.0f) {
                selectedX = leftWidth + 1.0f;
            } else if (((float) this.touchedX) + rightWidth > ((float) this.chartInfo.getChartRightX())) {
                selectedX = (((float) this.chartInfo.getChartRightX()) - rightWidth) - 1.0f;
            }
            Bitmap callForHeatBitmap = getCallForHeatBitmap((float) this.touchedX, this.callForHeats);
            Bitmap settingBitmap = getSettingBitmap((float) this.touchedX, this.settings);
            Bitmap humidityBitmap = ChartResources.getScaledCompatBitmap(C0676R.drawable.ic_humidity, TadoApplication.getTadoAppContext(), (int) ChartUtils.getDIPValue(12.0f));
            String settingsText = getSettingsText((float) this.touchedX, this.settings, getChartStripe());
            float leftRectX = selectedX - leftWidth;
            float rightRectX = selectedX + rightWidth;
            float textX = (selectedX - leftWidth) + padding;
            float callForHeatX = ((selectedX + rightWidth) - padding) - ((float) (callForHeatBitmap != null ? callForHeatBitmap.getWidth() : 0));
            float humidityIconX = (selectedX - leftWidth) + padding;
            float humidityTextX = (humidityIconX + iconPadding) + ((float) humidityBitmap.getWidth());
            float rightTextX = (selectedX + ((rightMiddlePoint + rightWidth) / 2.0f)) - (this.setPointTextPaint.measureText(settingsText) / 2.0f);
            float settingsBitmapX = (rightTextX - iconPadding) - ((float) settingBitmap.getWidth());
            this.textPaint.setColorFilter(new PorterDuffColorFilter(-1, Mode.SRC_ATOP));
            if (this.inspectionModeActive) {
                this.stripe = getChartStripe();
                if (this.stripe != null) {
                    this.lineColor = this.stripe.getColorPair().darkColorRes;
                    drawStateRangePath(canvas, this.stripe);
                    canvas.drawRoundRect(new RectF(leftRectX, topY, rightRectX, topY + height), this.rxy, this.rxy, ChartUtils.getPaint(this.lineColor, 1.0f, Style.FILL_AND_STROKE));
                    if (this.stripe.isBreakingStripe()) {
                        boolean oneLineMessage;
                        float messageX = selectedX - leftMiddlePoint;
                        float rightTextPadding = ReportViewElement.getDp(4.0f);
                        String message = (String) this.messagesMap.get(this.stripe.getType());
                        if (message == null) {
                            message = "";
                        }
                        if (StripeTypeEnum.MEASURING_DEVICE_DISCONNECTED == this.stripe.getType()) {
                            Bitmap noConnectionBitmap = ChartResources.getCompatBitmap(C0676R.drawable.ic_no_remote_control, TadoApplication.getTadoAppContext());
                            canvas.drawBitmap(noConnectionBitmap, leftRectX + padding, ((height / 2.0f) + topY) - (((float) noConnectionBitmap.getHeight()) / 2.0f), this.textPaint);
                        }
                        Paint messagePaint = ChartUtils.getPaint("#FFFFFF", 1.0f, Style.FILL);
                        messagePaint.setTextSize(ReportViewElement.getDp(12.0f));
                        String[] messageLines = new String[2];
                        if ((messageX + messagePaint.measureText(message)) + rightTextPadding > selectedX + rightWidth) {
                            messageLines = divideMessage(message, messagePaint, ((selectedX + rightWidth) - rightTextPadding) - messageX);
                            oneLineMessage = false;
                        } else {
                            messageLines[0] = message;
                            oneLineMessage = true;
                        }
                        Rect messageBounds = new Rect();
                        messagePaint.getTextBounds(message, 0, message.length(), messageBounds);
                        float messageY = (topY + middleHeight) + (((float) Math.abs(messageBounds.height())) / 2.0f);
                        int i = 0;
                        while (i < messageLines.length) {
                            message = messageLines[i];
                            if (message != null) {
                                messagePaint.getTextBounds(message, 0, message.length(), messageBounds);
                                if (!oneLineMessage) {
                                    if (i == 0) {
                                        messageY = (topY + middleHeight) - ReportViewElement.getDp(1.0f);
                                    } else {
                                        messageY = ((topY + middleHeight) + ((float) Math.abs(messageBounds.height()))) + iconPadding;
                                    }
                                }
                                canvas.drawText(message, messageX, messageY, messagePaint);
                                if (oneLineMessage) {
                                    break;
                                }
                                i++;
                            } else {
                                return;
                            }
                        }
                    }
                    canvas.drawText(temperatureText, textX, (((float) this.bounds.height()) + topY) + padding, this.textPaint);
                    this.setPointTextPaint.getTextBounds(humidityText, 0, humidityText.length(), this.bounds);
                    canvas.drawText(humidityText, humidityTextX, (topY + height) - padding, this.setPointTextPaint);
                    if (callForHeatBitmap != null) {
                        canvas.drawBitmap(callForHeatBitmap, callForHeatX, topY + padding, null);
                    }
                    canvas.drawBitmap(settingBitmap, settingsBitmapX, ((topY + height) - padding) - ((float) settingBitmap.getHeight()), this.textPaint);
                    canvas.drawBitmap(humidityBitmap, humidityIconX, ((topY + height) - padding) - ((float) humidityBitmap.getHeight()), this.textPaint);
                    canvas.drawText(settingsText, rightTextX, (topY + height) - padding, this.setPointTextPaint);
                    canvas.drawLine((float) this.touchedX, this.chartInfo.getCanvasTopY() + height, (float) this.touchedX, this.chartInfo.getBottomPadding() + this.chartInfo.getCanvasBottomY(), ChartUtils.getPaint(this.lineColor, ChartUtils.getDIPValue(1.0f), Style.FILL));
                    if (!this.stripe.isBreakingStripe()) {
                        this.tempY = getYFromPoints(this.temperatureRawMeasurementPoints, (float) this.xValue, (float) this.touchedY, false);
                        canvas.drawCircle((float) this.touchedX, this.tempY, ReportViewElement.getDp(5.0f), ChartUtils.getPaint("#FFFFFF", 0.0f, Style.FILL));
                        canvas.drawCircle((float) this.touchedX, this.tempY, ReportViewElement.getDp(5.0f), ChartUtils.getPaint(this.lineColor, ReportViewElement.getDp(1.0f), Style.STROKE));
                        if (this.toolbarButtonsState.get(ToolbarButtonTypeEnum.HUMIDITY.ordinal())) {
                            this.tempY = getYFromPoints(this.humidityRawMeasurementPoints, (float) this.xValue, (float) this.touchedY, true);
                            canvas.drawCircle((float) this.touchedX, this.tempY, ReportViewElement.getDp(5.0f), ChartUtils.getPaint("#FFFFFF", 0.0f, Style.FILL));
                            canvas.drawCircle((float) this.touchedX, this.tempY, ReportViewElement.getDp(5.0f), ChartUtils.getPaint(this.lineColor, ReportViewElement.getDp(1.0f), Style.STROKE));
                        }
                    }
                    if (this.debugMode) {
                        canvas.drawLine(selectedX - leftMiddlePoint, 0.0f, selectedX - leftMiddlePoint, 150.0f, ChartUtils.getPaint("#00FF20", 1.0f, Style.FILL_AND_STROKE));
                        canvas.drawLine(selectedX + rightMiddlePoint, 0.0f, selectedX + rightMiddlePoint, 150.0f, ChartUtils.getPaint("#00FF20", 1.0f, Style.FILL_AND_STROKE));
                        canvas.drawLine(selectedX - 130.0f, topY + middleHeight, selectedX + 130.0f, topY + (height / 2.0f), ChartUtils.getPaint("#00FF20", 1.0f, Style.FILL_AND_STROKE));
                        canvas.drawLine(selectedX - 130.0f, topY, selectedX + 130.0f, topY, ChartUtils.getPaint("#00FF20", 1.0f, Style.FILL_AND_STROKE));
                        canvas.drawLine(selectedX - 130.0f, topY + height, selectedX + 130.0f, topY + height, ChartUtils.getPaint("#00FF20", 1.0f, Style.FILL_AND_STROKE));
                    }
                    drawModeIconsLayout(canvas, this.stripe, this.textPaint);
                }
            }
        }
    }

    private void drawModeIconsLayout(Canvas canvas, ChartStripe stripe, Paint paint) {
        float startX = (float) getXCoordinate(stripe.getInterval().getStart());
        float endX = (float) getXCoordinate(stripe.getInterval().getEnd());
        float highlightAreaWidth = endX - startX;
        float middleX = (highlightAreaWidth / 2.0f) + ((float) getXCoordinate(stripe.getInterval().getStart()));
        paint.setColorFilter(new PorterDuffColorFilter(-1, Mode.SRC_ATOP));
        List<Bitmap> modeIcons = ChartResources.getModeIcons(stripe.getType(), TadoApplication.getTadoAppContext());
        if (modeIcons.size() > 0) {
            float modeIconX;
            float iconPadding = ReportViewElement.getDp(4.0f);
            if (((float) ((Bitmap) modeIcons.get(0)).getWidth()) + ReportViewElement.getDp(2.0f) < highlightAreaWidth) {
                modeIconX = middleX - (((float) ((Bitmap) modeIcons.get(0)).getWidth()) / 2.0f);
            } else {
                paint.setColorFilter(new PorterDuffColorFilter(stripe.getColorPair().darkColor, Mode.SRC_ATOP));
                if (((float) ((Bitmap) modeIcons.get(0)).getWidth()) + (endX + iconPadding) < ((float) this.chartInfo.getChartRightX())) {
                    modeIconX = endX + iconPadding;
                } else {
                    modeIconX = (startX - iconPadding) - ((float) ((Bitmap) modeIcons.get(0)).getWidth());
                }
            }
            drawModeIcons(canvas, modeIcons, modeIconX, paint);
        }
    }

    private void drawModeIcons(Canvas canvas, List<Bitmap> modeIcons, float x, Paint paint) {
        canvas.drawBitmap((Bitmap) modeIcons.get(0), x, (this.chartInfo.getCanvasBottomY() - ReportViewElement.getDp(8.0f)) - ((float) ((Bitmap) modeIcons.get(0)).getHeight()), paint);
        if (modeIcons.size() == 2) {
            canvas.drawBitmap((Bitmap) modeIcons.get(1), x, ((this.chartInfo.getCanvasBottomY() - ReportViewElement.getDp(8.0f)) - ((float) ((Bitmap) modeIcons.get(0)).getHeight())) - ((float) ((Bitmap) modeIcons.get(1)).getHeight()), paint);
        }
    }

    private String[] divideMessage(String message, Paint messagePaint, float maxSingleLineLength) {
        String[] messageLines = new String[2];
        String separator = " ";
        String[] messageArray = message.split(separator);
        float lineSize = 0.0f;
        float separatorSize = messagePaint.measureText(separator);
        String messageLine = "";
        int j = 0;
        for (int i = 0; i < messageArray.length; i++) {
            float wordSize = messagePaint.measureText(messageArray[i]);
            if ((wordSize + separatorSize) + lineSize < maxSingleLineLength) {
                lineSize += wordSize;
                if (messageLine.equals("")) {
                    messageLine = messageArray[i];
                } else {
                    messageLine = messageLine + " " + messageArray[i];
                }
            } else if (j <= 1) {
                int j2 = j + 1;
                messageLines[j] = messageLine;
                if (wordSize < maxSingleLineLength) {
                    lineSize = wordSize;
                    messageLine = messageArray[i];
                    j = j2;
                } else {
                    j = j2;
                }
            } else {
                String textOverflowIndicator = "...";
                if (messagePaint.measureText(messageLines[1]) + messagePaint.measureText(textOverflowIndicator) < maxSingleLineLength) {
                    messageLines[1] = messageLines[1] + textOverflowIndicator;
                }
            }
        }
        if (messageLines[0] == null) {
            messageLines[0] = messageLine;
        } else if (messageLines[1] == null && messageLine.length() > 0) {
            messageLines[1] = messageLine;
        }
        return messageLines;
    }

    private float getYFromPoints(List<ChartRawMeasurementPoint> points, float x, float y, boolean isHumidityPoint) {
        int i = 0;
        while (i + 1 < points.size()) {
            ChartRawMeasurementPoint leftPoint = (ChartRawMeasurementPoint) points.get(i);
            ChartRawMeasurementPoint rightPoint = (ChartRawMeasurementPoint) points.get(i + 1);
            if (x < ((float) leftPoint.getTimestamp()) || x > ((float) rightPoint.getTimestamp())) {
                i++;
            } else if (isHumidityPoint) {
                return calculateYFromX((float) this.touchedX, (float) getXCoordinate(leftPoint.getTimestamp()), getYHumidityCoordinate(leftPoint.getMeasurement()), (float) getXCoordinate(rightPoint.getTimestamp()), getYHumidityCoordinate(rightPoint.getMeasurement()));
            } else {
                return calculateYFromX((float) this.touchedX, (float) getXCoordinate(leftPoint.getTimestamp()), getYCoordinate(leftPoint.getMeasurement()), (float) getXCoordinate(rightPoint.getTimestamp()), getYCoordinate(rightPoint.getMeasurement()));
            }
        }
        return getYCoordinate(y);
    }

    private float calculateYFromX(float selectedX, float leftPointX, float leftPointY, float rightPointX, float rightPointY) {
        float m = (rightPointY - leftPointY) / (rightPointX - leftPointX);
        return (m * selectedX) + (rightPointY - (m * rightPointX));
    }

    private ChartStripe getChartStripe() {
        if (this.stripes == null || this.stripes.isEmpty()) {
            return null;
        }
        return (ChartStripe) this.stripes.get(getPositionOfItemForX(this.stripes, this.xValue));
    }

    private int getPositionOfItemForX(List<? extends ChartDrawingIntervalInterface> list, long xValue) {
        int position = 0;
        if (xValue < ((ChartDrawingIntervalInterface) list.get(0)).getInterval().getStart()) {
            return 0;
        }
        if (xValue > ((ChartDrawingIntervalInterface) list.get(list.size() - 1)).getInterval().getEnd()) {
            return list.size() - 1;
        }
        while (position < list.size()) {
            if (((ChartDrawingIntervalInterface) list.get(position)).getInterval().isInRangeExludingEnd(xValue)) {
                return position;
            }
            position++;
        }
        return position;
    }

    private void drawStateRangePath(Canvas canvas, ChartStripe stripe) {
        Path belowLinePath = new Path();
        Path underLinePath = new Path();
        if (stripe != null) {
            for (ChartRawMeasurementPoint point : this.temperatureRawMeasurementPoints) {
                float x = (float) getXCoordinate(point.getTimestamp());
                float y = getYCoordinate(point.getMeasurement());
                if (stripe.getInterval().isInRangeExludingEnd(point.getTimestamp())) {
                    if (belowLinePath.isEmpty() && underLinePath.isEmpty()) {
                        belowLinePath.moveTo(x, this.chartInfo.getCanvasBottomY() + this.chartInfo.getBottomPadding());
                        belowLinePath.lineTo(x, 2.0f + y);
                        underLinePath.moveTo(x, this.chartInfo.getCanvasTopY());
                        underLinePath.lineTo(x, y - 2.0f);
                    } else {
                        belowLinePath.lineTo(x, 2.0f + y);
                        underLinePath.lineTo(x, y - 2.0f);
                    }
                } else if (point.getTimestamp() == stripe.getInterval().getEnd()) {
                    belowLinePath.lineTo(x, 2.0f + y);
                    belowLinePath.lineTo(x, this.chartInfo.getCanvasBottomY() + this.chartInfo.getBottomPadding());
                    underLinePath.lineTo(x, y - 2.0f);
                    underLinePath.lineTo(x, this.chartInfo.getCanvasTopY());
                    break;
                }
            }
            long distance = stripe.getInterval().getEnd() - stripe.getInterval().getStart();
            String timeRange = String.format("%.1f h", new Object[]{Float.valueOf(ChartUtils.getTimeInHours(distance))});
            Rect bounds = new Rect();
            Paint textPaint = new Paint();
            textPaint.getTextBounds(timeRange, 0, timeRange.length(), bounds);
            textPaint.setColor(Color.parseColor("#FFFFFF"));
            textPaint.setTextSize(getTextSize(15.0f));
            if (stripe.isBreakingStripe()) {
                underLinePath = new Path();
                underLinePath.moveTo((float) getXCoordinate(stripe.getInterval().getStart()), this.chartInfo.getCanvasBottomY());
                underLinePath.lineTo((float) getXCoordinate(stripe.getInterval().getStart()), this.chartInfo.getCanvasTopY());
                underLinePath.lineTo((float) getXCoordinate(stripe.getInterval().getEnd()), this.chartInfo.getCanvasTopY());
                underLinePath.lineTo((float) getXCoordinate(stripe.getInterval().getEnd()), this.chartInfo.getCanvasBottomY());
            } else {
                canvas.drawPath(belowLinePath, getUnderLineStripePaint(stripe.getColorPair().darkColor));
            }
            canvas.drawPath(underLinePath, getAboveLineStripePaint(stripe.getColorPair().lightColorRes));
        }
    }

    private Paint getUnderLineStripePaint(int color) {
        Paint paint = new Paint();
        float f = 0.0f;
        paint.setShader(new LinearGradient(0.0f, this.chartInfo.getCanvasTopY(), f, this.chartInfo.getCanvasBottomY(), ColorUtils.setAlphaComponent(color, 76), ColorUtils.setAlphaComponent(color, 178), TileMode.CLAMP));
        return paint;
    }

    private Paint getAboveLineStripePaint(int colorRes) {
        Paint paint = ChartUtils.getPaint(colorRes, 1.0f, Style.FILL);
        paint.setAlpha(76);
        return paint;
    }

    public ChartInfoInterface getChartInfoInterface() {
        return this.chartInfo;
    }

    public void setTouch(int eventAction, float x, float y) {
        super.setTouch(eventAction, x, y);
        if (((long) this.touchedX) >= this.chartInfo.getChartLeftX() && ((long) this.touchedX) <= this.chartInfo.getChartRightX()) {
            this.xValue = getTimeFromXCoordinate((float) this.touchedX);
        }
    }

    public void inspectionModeActivation(boolean inspectionModeActive) {
        super.inspectionModeActivation(inspectionModeActive);
        this.elementTouched = inspectionModeActive;
    }

    public ReportViewElementType getType() {
        return ReportViewElementType.CHART_INFO_LINE;
    }

    private float getTextSize(float size) {
        return ChartUtils.getDIPValue(size);
    }

    public void execute(Pair<ToolbarButtonTypeEnum, Boolean> state) {
        this.toolbarButtonsState.put(((ToolbarButtonTypeEnum) state.first).ordinal(), ((Boolean) state.second).booleanValue());
    }

    private String getSettingsText(float x, List<ChartSetting> settings, ChartStripe chartStripe) {
        return getStringValueText(getSettingForTouchpoint(x, settings), chartStripe);
    }

    @Nullable
    private String getStringValueText(GenericZoneSetting setting, ChartStripe chartStripe) {
        if (setting == null) {
            return TEMPERATURE_PLACEHOLDER;
        }
        if (!setting.getPowerBoolean() || (chartStripe != null && StripeTypeEnum.OPEN_WINDOW_DETECTED == chartStripe.getType())) {
            return TadoApplication.getTadoAppContext().getString(C0676R.string.components_acSettingDisplay_offLabel);
        }
        if (GenericZoneSetting.TypeEnum.AIR_CONDITIONING == setting.getType() && shouldDisplayMode(((CoolingZoneSetting) setting).getMode())) {
            return ((CoolingZoneSetting) setting).getMode().name();
        }
        if (setting.getTemperature() != null) {
            return setting.getTemperature().getFormattedTemperatureValue(this.step);
        }
        return TEMPERATURE_PLACEHOLDER;
    }

    private boolean shouldDisplayMode(ModeEnum modeEnum) {
        return ModeEnum.FAN == modeEnum || ModeEnum.DRY == modeEnum || ModeEnum.AUTO == modeEnum;
    }

    @Nullable
    private GenericZoneSetting getSettingForTouchpoint(float x, List<ChartSetting> chartSettings) {
        ChartSetting setting = (ChartSetting) getRangeValueForTouchpoint(x, chartSettings);
        if (setting != null) {
            return (GenericZoneSetting) setting.getValue();
        }
        return null;
    }

    private Bitmap getSettingBitmap(float x, List<ChartSetting> settings) {
        GenericZoneSetting setting = getSettingForTouchpoint(x, settings);
        if (setting != null) {
            return ChartResources.getSettingsModeBitmap(setting, TadoApplication.getTadoAppContext());
        }
        return ChartResources.getCompatBitmap(C0676R.drawable.ic_heat_temp, TadoApplication.getTadoAppContext());
    }

    @Nullable
    private Bitmap getCallForHeatBitmap(float x, List<ChartRangeValue<CallForHeatEnum>> callForHeatList) {
        ChartRangeValue<CallForHeatEnum> callForHeat = getRangeValueForTouchpoint(x, callForHeatList);
        if (callForHeat != null) {
            return ChartResources.getScaledCompatBitmap(((Integer) ResourceFactory.getCallForHeatResourcesPair((CallForHeatEnum) callForHeat.getValue()).first).intValue(), TadoApplication.getTadoAppContext(), (int) ReportViewElement.getDp(20.0f));
        }
        return null;
    }

    private String getInterpolatedValueText(float x, List<ChartRawMeasurementPoint> rawPoints, List<ChartPoint> chartPoints, boolean isHumidityPoint) {
        if (rawPoints != null && !rawPoints.isEmpty()) {
            int i = 0;
            while (i + 1 < chartPoints.size()) {
                ChartPoint leftPoint = (ChartPoint) chartPoints.get(i);
                ChartPoint rightPoint = (ChartPoint) chartPoints.get(i + 1);
                ChartRawMeasurementPoint leftRawPoint = (ChartRawMeasurementPoint) rawPoints.get(i);
                ChartRawMeasurementPoint rightRawPoint = (ChartRawMeasurementPoint) rawPoints.get(i + 1);
                if (x < ((float) leftPoint.getX()) || x > ((float) rightPoint.getX())) {
                    i++;
                } else {
                    float firstWeight = x - ((float) leftPoint.getX());
                    float secondWeight = ((float) rightPoint.getX()) - x;
                    float interpolatedY = ((leftRawPoint.getMeasurement() * secondWeight) + (rightRawPoint.getMeasurement() * firstWeight)) / (firstWeight + secondWeight);
                    return String.format(Locale.US, isHumidityPoint ? "%.0f%%" : "%.1f°", new Object[]{Float.valueOf(interpolatedY)});
                }
            }
            Locale locale = Locale.US;
            String str = isHumidityPoint ? "%.0f%%" : "%.1f°";
            Object[] objArr = new Object[1];
            objArr[0] = Float.valueOf(((ChartRawMeasurementPoint) rawPoints.get(rawPoints.size() - 1)).getMeasurement());
            return String.format(locale, str, objArr);
        } else if (isHumidityPoint) {
            return "-%";
        } else {
            return TEMPERATURE_PLACEHOLDER;
        }
    }
}
