package com.tado.android.report.model;

import com.tado.android.rest.model.installation.GenericZoneSetting;

public class ChartSetting extends ChartRangeValue<GenericZoneSetting> {
    public ChartSetting(ChartXValueRange range, GenericZoneSetting setting) {
        super(range, setting);
    }

    public boolean shouldDrawInterval() {
        return true;
    }
}
