package com.tado.android.report;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.format.DateFormat;
import com.tado.C0676R;
import com.tado.android.controllers.CapabilitiesController;
import com.tado.android.controllers.ZoneController;
import com.tado.android.menu.ZoneItem;
import com.tado.android.report.ChartToolbarViewElement.ToolbarButtonTypeEnum;
import com.tado.android.report.interfaces.ChartDrawingIntervalInterface;
import com.tado.android.report.model.ChartRangeValue;
import com.tado.android.report.model.ChartRawMeasurementPoint;
import com.tado.android.report.model.ChartSetting;
import com.tado.android.report.model.ChartStripe;
import com.tado.android.report.model.ChartWeatherInterval;
import com.tado.android.report.model.ChartWeatherSlot;
import com.tado.android.report.model.ChartXValueRange;
import com.tado.android.report.model.DisplayValue;
import com.tado.android.report.model.IntervalTimeSeries;
import com.tado.android.report.model.StripeTypeColor;
import com.tado.android.rest.model.AcActivityEnum;
import com.tado.android.rest.model.CallForHeatEnum;
import com.tado.android.rest.model.DayReport;
import com.tado.android.rest.model.Humidity;
import com.tado.android.rest.model.ReportDataInterval;
import com.tado.android.rest.model.ReportDataPoint;
import com.tado.android.rest.model.ReportWeatherConditionValue;
import com.tado.android.rest.model.ReportWeatherSlotsHoursEnum;
import com.tado.android.rest.model.ReportWeatherSlotsTimeSeries;
import com.tado.android.rest.model.StripeTypeEnum;
import com.tado.android.rest.model.StripeValue;
import com.tado.android.rest.model.Temperature;
import com.tado.android.rest.model.Zone.TypeEnum;
import com.tado.android.rest.model.installation.GenericZoneSetting;
import com.tado.android.utils.UserConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormat;

class TadoReportViewFactory {
    private static final int HUMIDITY_STEP = 5;
    private static final int MIN_HUMIDITY_PERCENTAGE_SCALE = 10;
    private static final int MIN_TEMPERATURE_CELSIUS_SCALE = 3;
    private static final int MIN_TEMPERATURE_FAHRENHEIT_SCALE = 5;
    private static final int TEMPERATURE_STEP = 1;
    private Context context;
    private ZoneItem currentZoneItem;
    private DayReport dayReport;
    private float maxHumidityYValue = 0.0f;
    private float maxTemperatureYValue = 0.0f;
    private float minHumidityYValue = 0.0f;
    private float minTemperatureYValue = 0.0f;
    private DateTime selectedDate;
    private TypeEnum zoneType = TypeEnum.HEATING;

    class C10621 implements Comparator<ChartRawMeasurementPoint> {
        C10621() {
        }

        public int compare(ChartRawMeasurementPoint o1, ChartRawMeasurementPoint o2) {
            return (int) (o1.getTimestamp() - o2.getTimestamp());
        }
    }

    TadoReportViewFactory() {
    }

    TadoReportView createTadoReportView(Context context, DayReport dayReport, DateTime selectedDate) {
        this.context = context.getApplicationContext();
        this.dayReport = dayReport;
        this.selectedDate = selectedDate;
        TadoReportView report = new TadoReportView(context, selectedDate, dayReport);
        if (dayReport.getMeasuredData() != null) {
            if (!(dayReport.getMeasuredData().getInsideTemperature() == null || dayReport.getMeasuredData().getInsideTemperature().getMax() == null || dayReport.getMeasuredData().getInsideTemperature().getMin() == null)) {
                this.minTemperatureYValue = ((Temperature) dayReport.getMeasuredData().getInsideTemperature().getMin()).getTemperatureValue();
                this.maxTemperatureYValue = ((Temperature) dayReport.getMeasuredData().getInsideTemperature().getMax()).getTemperatureValue();
            }
            if (!(dayReport.getMeasuredData().getHumidity() == null || dayReport.getMeasuredData().getHumidity().getMax() == null || dayReport.getMeasuredData().getHumidity().getMin() == null)) {
                this.minHumidityYValue = ((Humidity) dayReport.getMeasuredData().getHumidity().getMin()).getDisplayValue();
                this.maxHumidityYValue = ((Humidity) dayReport.getMeasuredData().getHumidity().getMax()).getDisplayValue();
            }
        }
        this.currentZoneItem = ZoneController.INSTANCE.getZoneItemById(UserConfig.getCurrentZone().intValue());
        if (this.currentZoneItem != null) {
            this.zoneType = this.currentZoneItem.getZoneType();
        }
        initTadoReport(report);
        return report;
    }

    private List<ChartRawMeasurementPoint> initIntervalsInTemperaturePoints(List<ChartRawMeasurementPoint> temperaturePoints, List<ChartXValueRange> intervals) {
        if (!temperaturePoints.isEmpty()) {
            ChartRawMeasurementPoint previousPoint = null;
            ChartRawMeasurementPoint nextPoint = null;
            for (ChartXValueRange interval : intervals) {
                int i = 0;
                while (i < 2) {
                    ChartRawMeasurementPoint intervalPoint = new ChartRawMeasurementPoint(i == 0 ? interval.getStart() : interval.getEnd(), 0.0f);
                    int position = Collections.binarySearch(temperaturePoints, intervalPoint, new C10621());
                    if (position < 0) {
                        position *= -1;
                        if (position - 1 >= 0 && position - 1 < temperaturePoints.size()) {
                            nextPoint = (ChartRawMeasurementPoint) temperaturePoints.get(position - 1);
                        }
                        if (position - 2 >= 0) {
                            previousPoint = (ChartRawMeasurementPoint) temperaturePoints.get(position - 2);
                        }
                        temperaturePoints.add(position - 1, ChartUtils.interpolateRawMeasurementPoint(intervalPoint, previousPoint, nextPoint));
                    }
                    i++;
                }
            }
        }
        return temperaturePoints;
    }

    private List<ChartRawMeasurementPoint> initChartIntervalsInTemperaturePoints(List<ChartRawMeasurementPoint> temperaturePoints, List<? extends ChartDrawingIntervalInterface> deviceActivityIntervals) {
        List<ChartXValueRange> activityIntervals = new ArrayList(deviceActivityIntervals.size());
        for (ChartDrawingIntervalInterface intervalActivityPoint : deviceActivityIntervals) {
            if (intervalActivityPoint.shouldDrawInterval()) {
                activityIntervals.add(intervalActivityPoint.getInterval());
            }
        }
        return initIntervalsInTemperaturePoints(temperaturePoints, activityIntervals);
    }

    private List<ChartSetting> initSettingsPoints(List<ReportDataInterval<GenericZoneSetting>> settingsList) {
        List<ChartSetting> settingList = new ArrayList(settingsList.size());
        for (ReportDataInterval<GenericZoneSetting> settingsInterval : settingsList) {
            settingList.add(new ChartSetting(new ChartXValueRange(ChartUtils.parseTimestampWithHomeTimeZone(settingsInterval.getFrom()).getMillis(), ChartUtils.parseTimestampWithHomeTimeZone(settingsInterval.getTo()).getMillis()), (GenericZoneSetting) settingsInterval.getValue()));
        }
        return settingList;
    }

    private void initTadoReport(TadoReportView report) {
        List<ChartStripe> stripeList = initChartStripes(this.dayReport.getStripes().getDataIntervals());
        List<ChartSetting> settingList = initSettingsPoints(this.dayReport.getSettings().getDataIntervals());
        List<ChartRawMeasurementPoint> pointsList = initPointsList(this.dayReport.getMeasuredData().getInsideTemperature().getDataPoints());
        List<ChartRangeValue<AcActivityEnum>> acActivityList = initActivityList(this.dayReport.getAcActivity(), new AcActivityDrawingBehavior());
        List<ChartRangeValue<CallForHeatEnum>> callForHeatList = initActivityList(this.dayReport.getCallForHeat(), new CallForHeatDrawingBehavior());
        pointsList = initChartIntervalsInTemperaturePoints(pointsList, stripeList);
        if (!acActivityList.isEmpty()) {
            pointsList = initChartIntervalsInTemperaturePoints(pointsList, acActivityList);
        }
        if (!callForHeatList.isEmpty()) {
            pointsList = initChartIntervalsInTemperaturePoints(pointsList, callForHeatList);
        }
        report.addReportViewElement(getXAxis(report.getSelectedDate(), report.getMinXAxisValue(), report.getMaxXAxisValue()), new ToolbarButtonTypeEnum[0]);
        ReportViewElement yAxisViewElement = getYAxis(this.minTemperatureYValue, this.maxTemperatureYValue, this.minHumidityYValue, this.maxHumidityYValue, this.dayReport.getMeasuredData().getHumidity() != null);
        report.setMinTemperatureYAxisValue(yAxisViewElement.getMinTemperatureYValue());
        report.setMaxTemperatureYAxisValue(yAxisViewElement.getMaxTemperatureYValue());
        if (!(this.dayReport.getMeasuredData() == null || this.dayReport.getMeasuredData().getHumidity() == null)) {
            report.setMinYHumidityYAxisValue(yAxisViewElement.getMinHumidityYValue());
            report.setMaxYHumidityYAxisValue(yAxisViewElement.getMaxHumidityYValue());
        }
        ToolbarButtonTypeEnum[] humiditySupport = (this.dayReport.getMeasuredData() == null || this.dayReport.getMeasuredData().getHumidity() == null) ? new ToolbarButtonTypeEnum[0] : new ToolbarButtonTypeEnum[]{ToolbarButtonTypeEnum.HUMIDITY};
        report.addReportViewElement(yAxisViewElement, humiditySupport);
        ChartLineViewElement lineView = new ChartLineViewElement(getPointsList(pointsList, stripeList), pointsList, stripeList, report.getMinXAxisValue(), report.getMaxXAxisValue(), report.getMinTemperatureYAxisValue(), report.getMaxTemperatureYAxisValue(), UserConfig.getCurrentZoneReportToolbarButtonState(ToolbarButtonTypeEnum.CALL_FOR_HEAT.name(), true), !ChartUtils.isCurrentDay(this.selectedDate));
        ToolbarButtonTypeEnum[] toolbarButtonTypeEnumArr = new ToolbarButtonTypeEnum[1];
        ToolbarButtonTypeEnum toolbarButtonTypeEnum = (this.currentZoneItem == null || !this.currentZoneItem.isCoolingZone()) ? ToolbarButtonTypeEnum.CALL_FOR_HEAT : ToolbarButtonTypeEnum.AC_ACTIVITY;
        toolbarButtonTypeEnumArr[0] = toolbarButtonTypeEnum;
        report.addReportViewElement(lineView, toolbarButtonTypeEnumArr);
        ReportViewElement humidityLine = getChartHumidityLineViewElement(report, stripeList);
        report.addReportViewElement(new ChartSunshineViewElement(initBooleanRangeList(this.dayReport.getWeatherIntervals().getSunny()), report.getMinXAxisValue(), report.getMaxXAxisValue(), report.getMinTemperatureYAxisValue(), report.getMaxTemperatureYAxisValue(), UserConfig.getCurrentZoneReportToolbarButtonState(ToolbarButtonTypeEnum.WEATHER.name(), false)), ToolbarButtonTypeEnum.WEATHER);
        report.setRawMeasurementPoints(pointsList);
        report.setRawHumidityPoints(humidityLine.getRawMeasurementPoints());
        report.addReportViewElement(humidityLine, humiditySupport);
        report.addReportViewElement(new ChartDoneViewElement(), new ToolbarButtonTypeEnum[0]);
        report.addReportViewElement(new ChartIntervalViewElement(this.selectedDate, ChartUtils.parseTimestampWithHomeTimeZone(this.dayReport.getInterval().getTo()), ChartUtils.parseTimestampWithHomeTimeZone(this.dayReport.getInterval().getFrom()), this.dayReport.getMeasuredData().getInsideTemperature().getDataPoints().size() > 0 ? (ReportDataPoint) this.dayReport.getMeasuredData().getInsideTemperature().getDataPoints().get(this.dayReport.getMeasuredData().getInsideTemperature().getDataPoints().size() - 1) : null, report.getMinXAxisValue(), report.getMaxXAxisValue(), DateFormat.is24HourFormat(this.context)), new ToolbarButtonTypeEnum[0]);
        report.addReportViewElement(getChartDateAndTimeElement(this.currentZoneItem, this.selectedDate, this.dayReport.getWeatherIntervals().getCondition().getDataIntervals()), new ToolbarButtonTypeEnum[0]);
        report.addReportViewElement(new ChartStripeBarElementView(stripeList), new ToolbarButtonTypeEnum[0]);
        Map<StripeTypeEnum, String> messages = new HashMap();
        messages.put(StripeTypeEnum.DAY_REPORT_UNAVAILABLE, this.context.getResources().getString(C0676R.string.report_pin_noDataAvailableLabel));
        messages.put(StripeTypeEnum.MEASURING_DEVICE_DISCONNECTED, this.context.getResources().getString(C0676R.string.report_pin_noRemoteAccessLabel));
        report.addReportViewElement(new ChartInfoLineViewElement(stripeList, report.getRawMeasurementPoints(), humidityLine.getRawMeasurementPoints(), settingList, callForHeatList, report.getMinTemperatureYAxisValue(), report.getMaxTemperatureYAxisValue(), report.getMinXAxisValue(), report.getMaxXAxisValue(), report.getMinYHumidityYAxisValue(), report.getMaxYHumidityYAxisValue(), messages, UserConfig.getCurrentZoneReportToolbarButtonState(ToolbarButtonTypeEnum.HUMIDITY.name(), false), CapabilitiesController.INSTANCE.isCoolingZone() ? 1.0f : 0.1f, this.zoneType), humiditySupport);
        if (this.dayReport.getHotWaterProduction() != null) {
            report.addReportViewElement(new ChartHotWaterProductionViewElement(initBooleanRangeList(this.dayReport.getHotWaterProduction()), report.getMinXAxisValue(), report.getMaxXAxisValue(), report.getMinTemperatureYAxisValue(), report.getMaxTemperatureYAxisValue(), UserConfig.getCurrentZoneReportToolbarButtonState(ToolbarButtonTypeEnum.HOT_WATER.name(), false)), ToolbarButtonTypeEnum.HOT_WATER);
        }
        report.addReportViewElement(new ChartWeatherSlotsViewElement(initWeatherSlots(this.dayReport.getWeatherIntervals().getSlots()), this.selectedDate, UserConfig.getCurrentZoneReportToolbarButtonState(ToolbarButtonTypeEnum.WEATHER.name(), false)), ToolbarButtonTypeEnum.WEATHER);
        if (this.currentZoneItem != null && this.currentZoneItem.isHeatingZone()) {
            report.addReportViewElement(new ChartDeviceActivityIntervalsViewElement(report.getRawMeasurementPoints(), callForHeatList, stripeList, ToolbarButtonTypeEnum.CALL_FOR_HEAT, UserConfig.getCurrentZoneReportToolbarButtonState(ToolbarButtonTypeEnum.CALL_FOR_HEAT.name(), true)), ToolbarButtonTypeEnum.CALL_FOR_HEAT);
        } else if (this.currentZoneItem != null && this.currentZoneItem.isCoolingZone()) {
            report.addReportViewElement(new ChartDeviceActivityIntervalsViewElement(report.getRawMeasurementPoints(), acActivityList, stripeList, ToolbarButtonTypeEnum.AC_ACTIVITY, UserConfig.getCurrentZoneReportToolbarButtonState(ToolbarButtonTypeEnum.AC_ACTIVITY.name(), true)), ToolbarButtonTypeEnum.AC_ACTIVITY);
        }
        report.invalidate();
    }

    @NonNull
    private ChartDateAndTimeViewElement getChartDateAndTimeElement(ZoneItem currentZoneItem, DateTime selectedDate, List<ReportDataInterval<ReportWeatherConditionValue>> weatherData) {
        ChartDateAndTimeViewElement header = new ChartDateAndTimeViewElement(UserConfig.getCurrentZoneName(), currentZoneItem != null ? currentZoneItem.getZoneImageResource() : C0676R.drawable.zone_list_device_st);
        header.setSelectedDay(DateTimeFormat.forPattern("EEEE, d MMMM YYYY").print((ReadableInstant) selectedDate));
        header.setWeatherIntervals(initWeatherConditionIntervals(weatherData));
        return header;
    }

    @NonNull
    private ChartHumidityLineViewElement getChartHumidityLineViewElement(TadoReportView report, List<ChartStripe> stripeList) {
        ChartHumidityLineViewElement humidityLine = new ChartHumidityLineViewElement(report.getMinXAxisValue(), report.getMaxXAxisValue(), report.getMinYHumidityYAxisValue(), report.getMaxYHumidityYAxisValue(), UserConfig.getCurrentZoneReportToolbarButtonState(ToolbarButtonTypeEnum.HUMIDITY.name(), false));
        if (!(this.dayReport.getMeasuredData() == null || this.dayReport.getMeasuredData().getHumidity() == null)) {
            humidityLine.setRawMeasurementPoints(initPointsList(this.dayReport.getMeasuredData().getHumidity().getDataPoints()));
            humidityLine.setRawMeasurementPointsLists(getPointsList(humidityLine.getRawMeasurementPoints(), stripeList));
        }
        return humidityLine;
    }

    private <T> List<ChartRangeValue<T>> initActivityList(IntervalTimeSeries<T> intervalTimeSeries, BaseDrawingBehavior drawingBehavior) {
        if (intervalTimeSeries == null) {
            return new ArrayList(0);
        }
        return initActivityPoints(intervalTimeSeries.getDataIntervals(), drawingBehavior);
    }

    private <T> List<ChartRangeValue<T>> initActivityPoints(List<ReportDataInterval<T>> intervals, BaseDrawingBehavior drawingBehavior) {
        List<ChartRangeValue<T>> activityList = new ArrayList();
        for (ReportDataInterval<T> interval : intervals) {
            ChartRangeValue<T> chartRangeValue = new ChartRangeValue(new ChartXValueRange(ChartUtils.parseTimestampWithHomeTimeZone(interval.getFrom()).getMillis(), ChartUtils.parseTimestampWithHomeTimeZone(interval.getTo()).getMillis()), interval.getValue());
            chartRangeValue.setDrawingBehavior(drawingBehavior);
            activityList.add(chartRangeValue);
        }
        return activityList;
    }

    private List<ChartXValueRange> initBooleanRangeList(IntervalTimeSeries<Boolean> booleanIntervalTimeSeries) {
        List<ChartXValueRange> range = new ArrayList();
        if (booleanIntervalTimeSeries == null || booleanIntervalTimeSeries.getDataIntervals() == null) {
            return range;
        }
        return getChartXValueRangeList(booleanIntervalTimeSeries.getDataIntervals());
    }

    private List<ChartXValueRange> getChartXValueRangeList(List<ReportDataInterval<Boolean>> booleanDataIntervalList) {
        List<ChartXValueRange> range = new ArrayList();
        if (booleanDataIntervalList != null) {
            for (ReportDataInterval<Boolean> interval : booleanDataIntervalList) {
                if (((Boolean) interval.getValue()).booleanValue()) {
                    range.add(new ChartXValueRange(ChartUtils.parseTimestampWithHomeTimeZone(interval.getFrom()).getMillis(), ChartUtils.parseTimestampWithHomeTimeZone(interval.getTo()).getMillis()));
                }
            }
        }
        return range;
    }

    private List<ChartWeatherSlot> initWeatherSlots(ReportWeatherSlotsTimeSeries weather) {
        List<ChartWeatherSlot> weatherSlots = new ArrayList(5);
        for (Entry<ReportWeatherSlotsHoursEnum, ReportWeatherConditionValue> entry : weather.getSlots().entrySet()) {
            if (((ReportWeatherConditionValue) entry.getValue()) == null) {
                weatherSlots.add(new ChartWeatherSlot(null, null, ((ReportWeatherSlotsHoursEnum) entry.getKey()).ordinal()));
            } else {
                weatherSlots.add(new ChartWeatherSlot(((ReportWeatherConditionValue) entry.getValue()).getCondition(), ((ReportWeatherConditionValue) entry.getValue()).getTemperature(), ((ReportWeatherSlotsHoursEnum) entry.getKey()).ordinal()));
            }
        }
        return weatherSlots;
    }

    private ChartHorizontalAxisViewElement getXAxis(DateTime selectedDate, long minXValue, long maxXValue) {
        List<Integer> horizontalLabelList = new ArrayList();
        horizontalLabelList.add(Integer.valueOf(4));
        horizontalLabelList.add(Integer.valueOf(8));
        horizontalLabelList.add(Integer.valueOf(12));
        horizontalLabelList.add(Integer.valueOf(16));
        horizontalLabelList.add(Integer.valueOf(20));
        ChartHorizontalAxisViewElement axis = new ChartHorizontalAxisViewElement(horizontalLabelList, selectedDate, DateFormat.is24HourFormat(this.context));
        axis.setMinValue((float) minXValue);
        axis.setMaxValue((float) maxXValue);
        return axis;
    }

    private float[] getMinMaxYAxisArray(double min, double max, int step, int minScale) {
        float[] minMaxArray = new float[2];
        if ((Math.ceil(max / ((double) step)) * ((double) step)) - (Math.floor(min / ((double) step)) * ((double) step)) >= ((double) minScale)) {
            minMaxArray[0] = (float) (Math.floor(min / ((double) step)) * ((double) step));
            minMaxArray[1] = (float) (Math.ceil(max / ((double) step)) * ((double) step));
        } else {
            minMaxArray[0] = (float) (Math.round((((min + max) / 2.0d) - ((double) (((float) minScale) / 2.0f))) / ((double) step)) * ((long) step));
            minMaxArray[1] = (float) (Math.round((((min + max) / 2.0d) + ((double) (((float) minScale) / 2.0f))) / ((double) step)) * ((long) step));
        }
        return minMaxArray;
    }

    private ChartVerticalAxisViewElement getYAxis(float minYTemperature, float maxYTemperature, float minYHumidity, float maxYHumidity, boolean hasHumidity) {
        List<Integer> verticalLabelList = new ArrayList();
        float[] minMaxTemperatureYValues = getMinMaxYAxisArray((double) minYTemperature, (double) maxYTemperature, 1, CapabilitiesController.INSTANCE.isCelsiusTemperatureUnit() ? 3 : 5);
        verticalLabelList.add(Integer.valueOf((int) minMaxTemperatureYValues[0]));
        verticalLabelList.add(Integer.valueOf((int) minMaxTemperatureYValues[1]));
        float[] minMaxHumidityYValues = new float[2];
        if (hasHumidity) {
            minMaxHumidityYValues = getMinMaxYAxisArray((double) minYHumidity, (double) maxYHumidity, 5, 10);
            verticalLabelList.add(1, Integer.valueOf((int) minMaxHumidityYValues[0]));
            verticalLabelList.add(Integer.valueOf((int) minMaxHumidityYValues[1]));
        }
        ChartVerticalAxisViewElement axis = new ChartVerticalAxisViewElement(UserConfig.getCurrentZoneReportToolbarButtonState(ToolbarButtonTypeEnum.HUMIDITY.name(), false));
        axis.setMinTemperatureYValue(minMaxTemperatureYValues[0]);
        axis.setMaxTemperatureYValue(minMaxTemperatureYValues[1]);
        axis.setMinHumidityYValue(minMaxHumidityYValues[0]);
        axis.setMaxHumidityYValue(minMaxHumidityYValues[1]);
        if (this.minTemperatureYValue == this.maxTemperatureYValue) {
            axis.setNoData(true);
        }
        return axis;
    }

    private ChartStripe getCurrentStripe(List<ChartStripe> stripes, long x) {
        for (ChartStripe stripe : stripes) {
            if (stripe.getInterval().isInRangeWithBoundaries(x)) {
                return stripe;
            }
        }
        return null;
    }

    private List<List<ChartRawMeasurementPoint>> getPointsList(List<ChartRawMeasurementPoint> pointsList, List<ChartStripe> stripes) {
        List<List<ChartRawMeasurementPoint>> pointsWithOutages = new ArrayList();
        List<ChartRawMeasurementPoint> tempPoints = new ArrayList();
        ChartRawMeasurementPoint previousPoint = null;
        ChartStripe currentStripe = null;
        for (ChartRawMeasurementPoint point : pointsList) {
            if (currentStripe == null) {
                currentStripe = getCurrentStripe(stripes, point.getTimestamp());
            }
            if (currentStripe != null) {
                if (!currentStripe.getInterval().isInRangeWithBoundaries(point.getTimestamp())) {
                    currentStripe = getCurrentStripe(stripes, point.getTimestamp());
                    if (currentStripe != null && currentStripe.isBreakingStripe()) {
                        pointsWithOutages.add(tempPoints);
                        tempPoints = new ArrayList();
                    } else if (previousPoint != null && currentStripe.getInterval().isInRangeExludingEnd(previousPoint.getTimestamp())) {
                        tempPoints.add(previousPoint);
                        previousPoint = null;
                    }
                    tempPoints.add(point);
                } else if (currentStripe.isBreakingStripe()) {
                    previousPoint = point;
                } else {
                    tempPoints.add(point);
                }
            }
        }
        if (tempPoints.size() > 0) {
            pointsWithOutages.add(tempPoints);
        }
        return pointsWithOutages;
    }

    private <T extends DisplayValue> List<ChartRawMeasurementPoint> initPointsList(List<ReportDataPoint<T>> displayDataPoints) {
        List<ChartRawMeasurementPoint> points = new ArrayList(displayDataPoints.size());
        for (ReportDataPoint<T> displayDataPoint : displayDataPoints) {
            points.add(new ChartRawMeasurementPoint(ChartUtils.parseTimestampWithHomeTimeZone(displayDataPoint.getTimestamp()).getMillis(), ((DisplayValue) displayDataPoint.getValue()).getDisplayValue()));
        }
        return points;
    }

    private List<ChartWeatherInterval> initWeatherConditionIntervals(List<ReportDataInterval<ReportWeatherConditionValue>> weatherIntervals) {
        List<ChartWeatherInterval> chartWeatherIntervals = new ArrayList();
        if (weatherIntervals != null) {
            for (ReportDataInterval<ReportWeatherConditionValue> conditionDataInterval : weatherIntervals) {
                chartWeatherIntervals.add(new ChartWeatherInterval(new ChartXValueRange(ChartUtils.parseTimestampWithHomeTimeZone(conditionDataInterval.getFrom()).getMillis(), ChartUtils.parseTimestampWithHomeTimeZone(conditionDataInterval.getTo()).getMillis()), (ReportWeatherConditionValue) conditionDataInterval.getValue()));
            }
        }
        return chartWeatherIntervals;
    }

    private List<ChartStripe> initChartStripes(List<ReportDataInterval<StripeValue>> stripes) {
        List<ChartStripe> chartStripes = new ArrayList(stripes.size());
        for (ReportDataInterval<StripeValue> interval : stripes) {
            chartStripes.add(new ChartStripe(new ChartXValueRange(ChartUtils.parseTimestampWithHomeTimeZone(interval.getFrom()).getMillis(), ChartUtils.parseTimestampWithHomeTimeZone(interval.getTo()).getMillis()), new StripeTypeColor(ChartResources.getStripeColor((StripeValue) interval.getValue()), ((StripeValue) interval.getValue()).getType())));
        }
        return chartStripes;
    }
}
