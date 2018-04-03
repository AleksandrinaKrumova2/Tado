package com.tado.android.report.model;

import com.tado.android.report.ChartToolbarViewElement.ToolbarButtonTypeEnum;

public class ChartToolbarButtonStateChangeEvent {
    private ToolbarButtonTypeEnum buttonTypeEnum;
    private boolean enabled;

    public ChartToolbarButtonStateChangeEvent(ToolbarButtonTypeEnum typeEnum, boolean enabled) {
        this.buttonTypeEnum = typeEnum;
        this.enabled = enabled;
    }

    public ToolbarButtonTypeEnum getButtonTypeEnum() {
        return this.buttonTypeEnum;
    }

    public boolean isEnabled() {
        return this.enabled;
    }
}
