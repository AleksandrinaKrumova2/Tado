package com.tado.android.report.model;

import com.tado.android.rest.model.StripeTypeEnum;
import com.tado.android.utils.ColorFactory.ColorPair;

public class ChartStripe extends ChartRangeValue<StripeTypeColor> {
    public ChartStripe(ChartXValueRange range, StripeTypeColor value) {
        super(range, value);
    }

    public ColorPair getColorPair() {
        return ((StripeTypeColor) getValue()).getColorPair();
    }

    public StripeTypeEnum getType() {
        return ((StripeTypeColor) getValue()).getType();
    }

    public boolean hasNoMeasureData() {
        return StripeTypeEnum.MEASURING_DEVICE_DISCONNECTED == getType();
    }

    public boolean isDayReportUnavailable() {
        return StripeTypeEnum.DAY_REPORT_UNAVAILABLE == getType();
    }

    public boolean isBreakingStripe() {
        return hasNoMeasureData() || isDayReportUnavailable();
    }
}
