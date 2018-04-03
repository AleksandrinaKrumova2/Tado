package com.tado.android.report;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import com.tado.android.report.ReportViewElement.ReportViewElementType;
import com.tado.android.report.interfaces.ChartInfoInterface;

class ChartDoneViewElement extends ReportViewElement {
    static final String DONE_BUTTON_PRESSED = "doneButtonPressed";
    private Rect bounds = new Rect();
    private String done = TadoApplication.getTadoAppContext().getString(C0676R.string.report_doneButton);
    private boolean hide;
    private float padding;
    private float textHeight;
    private Paint textPaint;
    private float textWidth;
    private float f398x;

    ChartDoneViewElement() {
    }

    public void drawViewElement(Canvas canvas) {
        if (!this.hide) {
            canvas.drawText(this.done, this.f398x, this.textHeight, this.textPaint);
        }
    }

    public void init(ChartInfoInterface chartInfo) {
        super.init(chartInfo);
        this.textPaint = ChartUtils.getPaint((int) C0676R.color.report_done_button_text, ChartUtils.getDIPValue(0.5f), Style.FILL);
        this.textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        this.padding = ChartUtils.getDIPValue(16.0f);
        this.textPaint.setTextSize(ChartUtils.getDIPValue(16.0f));
        this.textWidth = this.textPaint.measureText(this.done);
        this.textPaint.getTextBounds(this.done, 0, this.done.length(), this.bounds);
        this.textHeight = ChartUtils.getTextYForMiddleAlignment(chartInfo.getCanvasTopY() / 2.0f, this.done, this.textPaint);
        this.f398x = (((float) chartInfo.getChartRightX()) - this.padding) - this.textWidth;
    }

    public ChartInfoInterface getChartInfoInterface() {
        return this.chartInfo;
    }

    public void setTouch(int eventAction, float x, float y) {
        super.setTouch(eventAction, x, y);
        if (eventAction == 1 && !this.inspectionModeActive && ((float) this.touchedX) >= this.f398x - this.padding && ((float) this.touchedY) <= this.chartInfo.getCanvasTopY()) {
            TadoApplication.getBus().post(DONE_BUTTON_PRESSED);
        }
    }

    public void inspectionModeActivation(boolean inspectionModeActive) {
        super.inspectionModeActivation(inspectionModeActive);
        this.hide = inspectionModeActive;
    }

    public ReportViewElementType getType() {
        return ReportViewElementType.CHART_DONE_VIEW;
    }
}
