package com.tado.android.report;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.support.v4.content.ContextCompat;
import android.util.Pair;
import android.util.SparseBooleanArray;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import com.tado.android.report.ChartToolbarViewElement.ToolbarButtonTypeEnum;
import com.tado.android.report.ReportViewElement.ReportViewElementType;
import com.tado.android.report.interfaces.ChartInfoInterface;
import com.tado.android.report.interfaces.ChartToolbarCommandInterface;
import com.tado.android.report.model.ChartWeatherSlot;
import com.tado.android.utils.Constants;
import com.tado.android.utils.ResourceFactory;
import java.util.List;
import org.joda.time.DateTime;

class ChartWeatherSlotsViewElement extends ReportViewElement implements ChartToolbarCommandInterface {
    private float iconSize;
    private float iconX;
    private boolean isSelectedDateCurrentDate = false;
    private Paint paint;
    private DateTime selectedDate;
    private List<ChartWeatherSlot> slots;
    private String text;
    private int textWidth;
    private float textX;
    private SparseBooleanArray toolbarButtonsState;
    private Bitmap weatherIcon;

    ChartWeatherSlotsViewElement(List<ChartWeatherSlot> slots, DateTime selectedDate, boolean weatherState) {
        this.slots = slots;
        this.selectedDate = selectedDate;
        this.toolbarButtonsState = new SparseBooleanArray(1);
        this.toolbarButtonsState.put(ToolbarButtonTypeEnum.WEATHER.ordinal(), weatherState);
    }

    public void init(ChartInfoInterface chartInfo) {
        super.init(chartInfo);
        this.paint = ChartUtils.getPaint((int) C0676R.color.manual, 1.0f, Style.FILL);
        this.paint.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(TadoApplication.getTadoAppContext(), C0676R.color.manual), Mode.SRC_ATOP));
        this.paint.setTextSize(ChartUtils.getDIPValue(12.0f));
        this.iconSize = ReportViewElement.getDp(Constants.MAX_OFFSET_FAHRENHEIT);
        this.isSelectedDateCurrentDate = this.selectedDate.toLocalDate().equals(new DateTime().toLocalDate());
    }

    public void drawViewElement(Canvas canvas) {
        if (!this.inspectionModeActive && !this.toolbarButtonsState.get(ToolbarButtonTypeEnum.WEATHER.ordinal())) {
            for (ChartWeatherSlot slot : this.slots) {
                this.text = slot.getTemperature() != null ? slot.getTemperature().getFormattedTemperatureValue(1.0f) : "--°";
                this.textWidth = (int) this.paint.measureText(this.text);
                this.weatherIcon = ChartResources.getBitmap(ResourceFactory.getWeatherResource(slot.getWeatherEnum()), (int) this.iconSize, TadoApplication.getTadoAppContext());
                this.iconX = getSlotX(canvas.getWidth(), slot.getSlotIndex(), this.weatherIcon.getWidth());
                this.textX = getSlotX(canvas.getWidth(), slot.getSlotIndex(), this.textWidth);
                if (!this.isSelectedDateCurrentDate || ((float) getXCoordinate(System.currentTimeMillis())) < this.iconX || ((float) getXCoordinate(System.currentTimeMillis())) > this.iconX + ((float) this.weatherIcon.getWidth())) {
                    canvas.drawBitmap(this.weatherIcon, this.iconX, this.chartInfo.getCanvasTopY() + ReportViewElement.getDp(4.0f), this.paint);
                    canvas.drawText(this.text, this.textX, (this.chartInfo.getCanvasTopY() + ReportViewElement.getDp(14.0f)) + ((float) this.weatherIcon.getHeight()), this.paint);
                }
            }
        }
    }

    public ChartInfoInterface getChartInfoInterface() {
        return this.chartInfo;
    }

    public ReportViewElementType getType() {
        return ReportViewElementType.CHART_WEATHER_SLOTS_VIEW;
    }

    private float getSlotX(int canvasWidth, int slotIndex, int itemWidth) {
        return (((float) ((slotIndex + 1) * canvasWidth)) / 6.0f) - (((float) itemWidth) / 2.0f);
    }

    public void execute(Pair<ToolbarButtonTypeEnum, Boolean> state) {
        this.toolbarButtonsState.put(((ToolbarButtonTypeEnum) state.first).ordinal(), ((Boolean) state.second).booleanValue());
    }
}
