package com.tado.android.report;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import com.tado.android.report.ReportViewElement.ReportViewElementType;
import com.tado.android.report.interfaces.ChartInfoInterface;
import com.tado.android.report.model.ChartToolbarButtonStateChangeEvent;
import com.tado.android.utils.UserConfig;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class ChartToolbarViewElement extends ReportViewElement {
    private static final float BUTTON_DIAMETER = ReportViewElement.getDp(44.0f);
    private Paint backgroundButtonPaint;
    private boolean hide;
    private Paint paint;
    private Set<ToolbarButtonTypeEnum> supportedToolbarButtons;
    private Paint tempPaint;
    private List<ToolbarButton> toolbarButtons;
    private boolean touched = false;

    class ToolbarButton {
        private boolean activated;
        float diameter = ChartToolbarViewElement.BUTTON_DIAMETER;
        private Bitmap normalBitmap;
        private ToolbarButtonTypeEnum type;
        float f403x;
        float f404y;

        public ToolbarButton(ToolbarButtonTypeEnum type, float x, float y, boolean activated, Bitmap normalBitmap) {
            this.type = type;
            this.f403x = x;
            this.f404y = y;
            this.activated = activated;
            this.normalBitmap = normalBitmap;
        }

        public boolean onTouch(float x, float y) {
            float radius = this.diameter / 2.0f;
            if (x < this.f403x - radius || x > this.f403x + radius || y < this.f404y - radius || y > this.f404y + radius) {
                return false;
            }
            ChartToolbarViewElement.this.touched = false;
            return true;
        }

        public Paint getPaint(Paint paint) {
            paint.reset();
            paint.setAntiAlias(true);
            paint.setDither(true);
            paint.setColor(ContextCompat.getColor(TadoApplication.getTadoAppContext(), getBackgroundColor(this.activated)));
            paint.setStyle(Style.FILL);
            return paint;
        }

        public Paint getStrokePaint(Paint paint) {
            paint.reset();
            paint.setColor(ContextCompat.getColor(TadoApplication.getTadoAppContext(), getBackgroundColor(this.activated)));
            paint.setStrokeWidth(ReportViewElement.getDp(1.0f));
            paint.setStyle(Style.STROKE);
            return paint;
        }

        @ColorRes
        private int getBackgroundColor(boolean activated) {
            if (activated) {
                return C0676R.color.report_toolbar_button_background_enabled;
            }
            return C0676R.color.report_toolbar_button_background_disabled;
        }

        @ColorRes
        private int getIconColor(boolean activated) {
            if (activated) {
                return C0676R.color.report_toolbar_button_icon_enabled;
            }
            return C0676R.color.report_toolbar_button_icon_disabled;
        }

        public Bitmap getBitmap() {
            return this.normalBitmap;
        }

        public ToolbarButtonTypeEnum getType() {
            return this.type;
        }
    }

    public enum ToolbarButtonTypeEnum {
        CALL_FOR_HEAT(true),
        AC_ACTIVITY(true),
        HUMIDITY(false),
        WEATHER(false),
        HOT_WATER(false);
        
        private boolean defaultEnabled;

        private ToolbarButtonTypeEnum(boolean defaultEnabled) {
            this.defaultEnabled = defaultEnabled;
        }

        public boolean isDefaultEnabled() {
            return this.defaultEnabled;
        }
    }

    public void drawViewElement(Canvas canvas) {
        if (this.supportedToolbarButtons.size() > this.toolbarButtons.size()) {
            createToolbarButtons();
        }
        if (this.toolbarButtons.size() != 0 && !this.hide) {
            for (ToolbarButton button : this.toolbarButtons) {
                if (button.activated) {
                    canvas.drawCircle(button.f403x, button.f404y, button.diameter / 2.0f, button.getPaint(this.tempPaint));
                } else {
                    canvas.drawCircle(button.f403x, button.f404y, button.diameter / 2.0f, this.backgroundButtonPaint);
                    canvas.drawCircle(button.f403x, button.f404y, button.diameter / 2.0f, button.getStrokePaint(this.tempPaint));
                }
                this.paint.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(TadoApplication.getTadoAppContext(), button.getIconColor(button.activated)), Mode.SRC_ATOP));
                canvas.drawBitmap(button.getBitmap(), button.f403x - (((float) button.getBitmap().getWidth()) / 2.0f), button.f404y - (((float) button.getBitmap().getHeight()) / 2.0f), this.paint);
            }
        }
    }

    public void updateToolbarButtons() {
        if (this.toolbarButtons != null) {
            for (ToolbarButton button : this.toolbarButtons) {
                button.activated = UserConfig.getCurrentZoneReportToolbarButtonState(button.type.name(), button.activated);
            }
        }
    }

    public void init(ChartInfoInterface chartInfo) {
        super.init(chartInfo);
        this.supportedToolbarButtons = EnumSet.noneOf(ToolbarButtonTypeEnum.class);
        this.toolbarButtons = new ArrayList();
        this.paint = new Paint();
        this.tempPaint = new Paint();
        this.backgroundButtonPaint = ChartUtils.getPaint((int) C0676R.color.white, 0.0f, Style.FILL);
        createToolbarButtons();
    }

    public ChartInfoInterface getChartInfoInterface() {
        return this.chartInfo;
    }

    public void setTouch(int eventAction, float x, float y) {
        super.setTouch(eventAction, x, y);
        if (this.toolbarButtons.size() != 0 && !this.inspectionModeActive) {
            for (ToolbarButton button : this.toolbarButtons) {
                if (eventAction == 1 && button.onTouch((float) this.touchedX, (float) this.touchedY)) {
                    button.activated = !button.activated;
                    UserConfig.setCurrentZoneReportToolbarButtonState(button.getType().name(), button.activated);
                    TadoApplication.getBus().post(new ChartToolbarButtonStateChangeEvent(button.getType(), button.activated));
                }
            }
        }
    }

    public void inspectionModeActivation(boolean inspectionModeActive) {
        super.inspectionModeActivation(inspectionModeActive);
        this.hide = inspectionModeActive;
        this.touched = inspectionModeActive;
    }

    public ReportViewElementType getType() {
        return ReportViewElementType.CHART_TOOLBAR;
    }

    public void addSupportedToolbarButton(ToolbarButtonTypeEnum supportedToolbarButton) {
        this.supportedToolbarButtons.add(supportedToolbarButton);
    }

    private void createToolbarButtons() {
        if (this.supportedToolbarButtons.size() > this.toolbarButtons.size()) {
            int buttonIndex = 0;
            for (ToolbarButtonTypeEnum type : this.supportedToolbarButtons) {
                this.toolbarButtons.add(new ToolbarButton(type, (((float) this.chartInfo.getChartRightX()) - ReportViewElement.getDp(15.0f)) - ReportViewElement.getDp(BitmapDescriptorFactory.HUE_ORANGE), (this.chartInfo.getCanvasTopY() + ReportViewElement.getDp(BitmapDescriptorFactory.HUE_YELLOW)) + (((float) buttonIndex) * (BUTTON_DIAMETER + (4.0f * ReportViewElement.getDp(2.0f)))), UserConfig.getCurrentZoneReportToolbarButtonState(type.name(), type.isDefaultEnabled()), ChartResources.getToolbarButtonBitmap(type)));
                buttonIndex++;
            }
        }
    }
}
