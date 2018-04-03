package com.tado.android.report;

import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import com.tado.android.rest.model.AcActivityEnum;

class AcActivityDrawingBehavior extends BaseDrawingBehavior<AcActivityEnum> {
    AcActivityDrawingBehavior() {
    }

    public boolean shouldDrawInterval(AcActivityEnum value) {
        return AcActivityEnum.ON == value;
    }

    public int getColor(AcActivityEnum value) {
        float transparencyPercentage;
        switch (value) {
            case ON:
                transparencyPercentage = 0.6f;
                break;
            default:
                transparencyPercentage = 0.0f;
                break;
        }
        return ColorUtils.setAlphaComponent(ContextCompat.getColor(TadoApplication.getTadoAppContext(), C0676R.color.report_call_for_heat_base), (int) (255.0f * transparencyPercentage));
    }
}
