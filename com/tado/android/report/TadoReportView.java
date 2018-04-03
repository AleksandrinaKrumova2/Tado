package com.tado.android.report;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Display;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.tado.android.report.ChartToolbarViewElement.ToolbarButtonTypeEnum;
import com.tado.android.report.interfaces.ChartInfoInterface;
import com.tado.android.report.interfaces.ChartToolbarCommandInterface;
import com.tado.android.report.interfaces.OnToolbarButtonListener;
import com.tado.android.report.interfaces.TadoReportInterface;
import com.tado.android.report.model.ChartRawMeasurementPoint;
import com.tado.android.rest.model.DayReport;
import com.tado.android.utils.UserConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;

public class TadoReportView extends View implements OnTouchListener, ChartInfoInterface, OnGestureListener, OnToolbarButtonListener {
    private static final int PADDING_TIME = 900000;
    private String backgroundColor = "#FFFFFF";
    private float bottomPadding;
    private float bottomPaddingFactor = 0.025f;
    private TadoReportInterface caller;
    private float canvasBottomY;
    private float canvasTopY;
    private float chartHeight;
    private float chartWidth;
    private GestureDetectorCompat detector;
    private List<ReportViewElement> elementList = new ArrayList();
    private float firstMeasurementPointX;
    private int height;
    private boolean horizontalAxisTextVisible = true;
    private boolean inspectionModeActive = false;
    private boolean inspectionModeAvailable;
    private float lastMeasurementPointX;
    private long leftBottomX;
    private float leftBottomY;
    private float leftPadding;
    private float leftPaddingFactor = 0.08f;
    private float leftTopX;
    private float leftTopY;
    private long maxInspectionX;
    private float maxTemperatureYAxisValue;
    private long maxXAxisValue;
    private float maxYHumidityYAxisValue;
    private long minInspectionX;
    private float minTemperatureYAxisValue;
    private long minXAxisValue;
    private float minYHumidityYAxisValue;
    private float padding;
    private List<ChartRawMeasurementPoint> rawHumidityPoints;
    private List<ChartRawMeasurementPoint> rawMeasurementPoints;
    private long rightBottomX;
    private float rightBottomY;
    private float rightPadding;
    private float rightPaddingFactor = 0.0f;
    private long rightTopX;
    private float rightTopY;
    private DateTime selectedDate;
    private boolean sorted = false;
    private Map<ToolbarButtonTypeEnum, List<ChartToolbarCommandInterface>> toolbarButtonReportElementsMap;
    private ChartToolbarViewElement toolbarElement;
    private boolean toolbarMode = false;
    private float topPadding;
    private float topPaddingFactor = 0.1f;
    private boolean verticalAxisTextVisible = true;
    private int width;
    private float f406x;
    private float f407y;

    class C10611 implements Comparator<ReportViewElement> {
        C10611() {
        }

        public int compare(ReportViewElement o1, ReportViewElement o2) {
            return o1.getType().compareTo(o2.getType());
        }
    }

    public TadoReportView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TadoReportView(Context context) {
        super(context);
        init();
    }

    public TadoReportView(Context context, DateTime selectedDate, DayReport dayReport) {
        super(context);
        init();
        this.selectedDate = selectedDate;
        this.inspectionModeAvailable = dayReport.getInterval() != null;
        long minXInspectionValue = selectedDate.withTimeAtStartOfDay().getMillis();
        long maxXInspectionValue = selectedDate.plusDays(1).withTimeAtStartOfDay().getMillis();
        this.minXAxisValue = minXInspectionValue - 900000;
        this.maxXAxisValue = 900000 + maxXInspectionValue;
        this.minInspectionX = ChartUtils.calculateXCoordinate(this.minXAxisValue, this.maxXAxisValue, this.rightBottomX - this.leftBottomX, minXInspectionValue);
        if (ChartUtils.isCurrentDay(selectedDate)) {
            this.maxInspectionX = ChartUtils.calculateXCoordinate(this.minXAxisValue, this.maxXAxisValue, this.rightBottomX - this.leftBottomX, ChartUtils.parseTimestampWithHomeTimeZone(dayReport.getInterval().getTo()).getMillis());
        } else {
            this.maxInspectionX = ChartUtils.calculateXCoordinate(this.minXAxisValue, this.maxXAxisValue, this.rightBottomX - this.leftBottomX, maxXInspectionValue);
        }
    }

    private void init() {
        setOnTouchListener(this);
        initChartAttributes();
        this.detector = new GestureDetectorCompat(getContext(), this);
        this.toolbarButtonReportElementsMap = new EnumMap(ToolbarButtonTypeEnum.class);
        this.toolbarElement = new ChartToolbarViewElement();
        addReportViewElement(this.toolbarElement);
    }

    private void initChartAttributes() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        DisplayMetrics realDisplayMetrics = new DisplayMetrics();
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        display.getMetrics(displayMetrics);
        display.getRealMetrics(realDisplayMetrics);
        this.height = realDisplayMetrics.heightPixels;
        this.width = displayMetrics.widthPixels;
        if (this.width < this.height) {
            int temp = this.height;
            this.height = this.width;
            this.width = temp;
        }
        this.padding = ((float) this.height) * 0.156f;
        this.leftPadding = ((float) this.width) * this.leftPaddingFactor;
        this.rightPadding = ((float) this.width) * this.rightPaddingFactor;
        this.leftPadding = 0.0f;
        this.rightPadding = 0.0f;
        this.topPadding = ((float) this.height) * this.topPaddingFactor;
        this.bottomPadding = ((float) this.height) * this.bottomPaddingFactor;
        this.chartHeight = ((((float) this.height) - this.topPadding) - this.bottomPadding) - (2.0f * this.padding);
        this.chartWidth = (((float) this.width) - this.leftPadding) - this.rightPadding;
        this.leftTopX = this.leftPadding;
        this.leftTopY = this.topPadding + this.padding;
        this.leftBottomX = (long) this.leftPadding;
        this.leftBottomY = (((float) this.height) - this.bottomPadding) - this.padding;
        this.rightTopX = (long) (((float) this.width) - this.rightPadding);
        this.rightTopY = this.topPadding + this.padding;
        this.rightBottomX = (long) (((float) this.width) - this.rightPadding);
        this.rightBottomY = (((float) this.height) - this.bottomPadding) - this.padding;
        this.canvasTopY = this.topPadding;
        this.canvasBottomY = ((float) this.height) - this.bottomPadding;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        if (!this.sorted) {
            sortReportViewElements(this.elementList);
            this.sorted = true;
        }
        for (ReportViewElement element : this.elementList) {
            element.drawViewElement(canvas);
        }
    }

    public void setRawMeasurementPoints(List<ChartRawMeasurementPoint> rawMeasurementPoints) {
        this.rawMeasurementPoints = rawMeasurementPoints;
        if (rawMeasurementPoints != null && !rawMeasurementPoints.isEmpty()) {
            this.firstMeasurementPointX = (float) ChartUtils.calculateXCoordinate(this.minXAxisValue, this.maxXAxisValue, this.rightBottomX - this.leftBottomX, ((ChartRawMeasurementPoint) rawMeasurementPoints.get(0)).getTimestamp());
            this.lastMeasurementPointX = (float) ChartUtils.calculateXCoordinate(this.minXAxisValue, this.maxXAxisValue, this.rightBottomX - this.leftBottomX, ((ChartRawMeasurementPoint) rawMeasurementPoints.get(rawMeasurementPoints.size() - 1)).getTimestamp());
        }
    }

    public List<ChartRawMeasurementPoint> getRawMeasurementPoints() {
        return this.rawMeasurementPoints;
    }

    public void setRawHumidityPoints(List<ChartRawMeasurementPoint> rawHumidityPoints) {
        this.rawHumidityPoints = rawHumidityPoints;
    }

    private void drawBackground(Canvas canvas) {
        canvas.drawColor(Color.parseColor(this.backgroundColor));
    }

    public void addReportViewElement(ReportViewElement elementView, ToolbarButtonTypeEnum... supportedToolbarButtons) {
        addReportViewElement(elementView);
        if (supportedToolbarButtons != null) {
            int length = supportedToolbarButtons.length;
            int i = 0;
            while (i < length) {
                ToolbarButtonTypeEnum buttonTypeEnum = supportedToolbarButtons[i];
                if (!this.toolbarButtonReportElementsMap.containsKey(buttonTypeEnum)) {
                    this.toolbarButtonReportElementsMap.put(buttonTypeEnum, new ArrayList());
                    this.toolbarElement.addSupportedToolbarButton(buttonTypeEnum);
                }
                if (elementView instanceof ChartToolbarCommandInterface) {
                    ((List) this.toolbarButtonReportElementsMap.get(buttonTypeEnum)).add((ChartToolbarCommandInterface) elementView);
                    i++;
                } else {
                    throw new IllegalArgumentException("ReportViewElement of type " + elementView.getType() + " should implement " + ChartToolbarCommandInterface.class.getName());
                }
            }
        }
    }

    private void addReportViewElement(ReportViewElement elementView) {
        if (elementView != null) {
            this.elementList.add(elementView);
            elementView.init(this);
            elementView.setMinXValue(this.minXAxisValue);
            elementView.setMaxXValue(this.maxXAxisValue);
            elementView.setMinTemperatureYValue(this.minTemperatureYAxisValue);
            elementView.setMaxTemperatureYValue(this.maxTemperatureYAxisValue);
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        this.detector.onTouchEvent(event);
        this.f406x = event.getX();
        this.f407y = event.getY();
        if (event.getAction() == 1 && this.inspectionModeActive) {
            this.inspectionModeActive = false;
        }
        if (this.inspectionModeActive) {
            if (this.f406x < ((float) this.minInspectionX)) {
                this.f406x = (float) ((int) this.minInspectionX);
            } else if (this.f406x > ((float) this.maxInspectionX)) {
                this.f406x = (float) ((int) this.maxInspectionX);
            }
            if (!(this.rawMeasurementPoints == null || this.rawMeasurementPoints.isEmpty())) {
                if (this.f406x > this.lastMeasurementPointX) {
                    this.f406x = this.lastMeasurementPointX;
                } else if (this.f406x < this.firstMeasurementPointX) {
                    this.f406x = this.firstMeasurementPointX;
                }
            }
        }
        for (ReportViewElement reportView : this.elementList) {
            reportView.setTouch(event.getAction(), this.f406x, this.f407y);
        }
        for (ReportViewElement reportViewElement : this.elementList) {
            reportViewElement.inspectionModeActivation(this.inspectionModeActive);
        }
        invalidate();
        return true;
    }

    public float getCanvasBottomY() {
        return this.canvasBottomY;
    }

    public float getCanvasTopY() {
        return this.canvasTopY;
    }

    public float getChartBottomY() {
        return this.leftBottomY;
    }

    public long getChartLeftX() {
        return this.leftBottomX;
    }

    public float getChartTopY() {
        return this.leftTopY;
    }

    public long getChartRightX() {
        return this.rightBottomX;
    }

    public float getBottomPadding() {
        return this.bottomPadding;
    }

    public float getLastPointX() {
        return this.lastMeasurementPointX;
    }

    public boolean isVerticalAxisTextVisible() {
        return this.verticalAxisTextVisible;
    }

    public boolean isHorizontalAxisTextVisible() {
        return this.horizontalAxisTextVisible;
    }

    public long getMinXAxisValue() {
        return this.minXAxisValue;
    }

    public long getMaxXAxisValue() {
        return this.maxXAxisValue;
    }

    public float getMinTemperatureYAxisValue() {
        return this.minTemperatureYAxisValue;
    }

    public void setMinTemperatureYAxisValue(float minTemperatureYAxisValue) {
        this.minTemperatureYAxisValue = minTemperatureYAxisValue;
    }

    public float getMaxTemperatureYAxisValue() {
        return this.maxTemperatureYAxisValue;
    }

    public void setMaxTemperatureYAxisValue(float maxTemperatureYAxisValue) {
        this.maxTemperatureYAxisValue = maxTemperatureYAxisValue;
    }

    public void setVerticalAxisTextVisible(boolean verticalAxisTextVisible) {
        this.verticalAxisTextVisible = verticalAxisTextVisible;
    }

    public void setHorizontalAxisTextVisible(boolean horizontalAxisTextVisible) {
        this.horizontalAxisTextVisible = horizontalAxisTextVisible;
    }

    public DateTime getSelectedDate() {
        return this.selectedDate;
    }

    public boolean onDown(MotionEvent arg0) {
        return true;
    }

    public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {
        return false;
    }

    public void onLongPress(MotionEvent event) {
        if (this.inspectionModeAvailable && !this.inspectionModeActive) {
            this.inspectionModeActive = true;
            onTouch(this, event);
        }
    }

    public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {
        return true;
    }

    public void onShowPress(MotionEvent arg0) {
    }

    public boolean onSingleTapUp(MotionEvent arg0) {
        return false;
    }

    public void setMinYHumidityYAxisValue(float minYHumidityYAxisValue) {
        this.minYHumidityYAxisValue = minYHumidityYAxisValue;
    }

    public void setMaxYHumidityYAxisValue(float maxYHumidityYAxisValue) {
        this.maxYHumidityYAxisValue = maxYHumidityYAxisValue;
    }

    public float getMinYHumidityYAxisValue() {
        return this.minYHumidityYAxisValue;
    }

    public float getMaxYHumidityYAxisValue() {
        return this.maxYHumidityYAxisValue;
    }

    public void onToolbarButtonClick(ToolbarButtonTypeEnum typeEnum, boolean state) {
        List<ChartToolbarCommandInterface> listeners = (List) this.toolbarButtonReportElementsMap.get(typeEnum);
        if (listeners != null) {
            for (ChartToolbarCommandInterface listener : listeners) {
                listener.execute(new Pair(typeEnum, Boolean.valueOf(state)));
            }
        }
        if (this.toolbarElement != null) {
            this.toolbarElement.updateToolbarButtons();
        }
        invalidate();
    }

    private boolean getToolbarButtonState(ToolbarButtonTypeEnum typeEnum) {
        return UserConfig.getCurrentZoneReportToolbarButtonState(typeEnum.name(), typeEnum.isDefaultEnabled());
    }

    private void sortReportViewElements(List<ReportViewElement> elements) {
        Collections.sort(elements, new C10611());
    }

    private float getPointXAt(int position) {
        return (float) ChartUtils.calculateXCoordinate(this.minXAxisValue, this.maxXAxisValue, this.rightBottomX - this.leftBottomX, ((ChartRawMeasurementPoint) this.rawMeasurementPoints.get(position)).getTimestamp());
    }
}
