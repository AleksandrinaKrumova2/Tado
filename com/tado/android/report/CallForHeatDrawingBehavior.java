package com.tado.android.report;

import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import com.tado.android.rest.model.CallForHeatEnum;

class CallForHeatDrawingBehavior extends BaseDrawingBehavior<CallForHeatEnum> {
    CallForHeatDrawingBehavior() {
    }

    public boolean shouldDrawInterval(CallForHeatEnum value) {
        return CallForHeatEnum.NONE != value;
    }

    public int getColor(CallForHeatEnum value) {
        float transparencyPercentage;
        if (CallForHeatEnum.LOW == value) {
            transparencyPercentage = 0.2f;
        } else if (CallForHeatEnum.MEDIUM == value) {
            transparencyPercentage = 0.4f;
        } else if (CallForHeatEnum.HIGH == value) {
            transparencyPercentage = 0.6f;
        } else {
            transparencyPercentage = 0.0f;
        }
        return ColorUtils.setAlphaComponent(ContextCompat.getColor(TadoApplication.getTadoAppContext(), C0676R.color.report_call_for_heat_base), (int) (255.0f * transparencyPercentage));
    }
}
