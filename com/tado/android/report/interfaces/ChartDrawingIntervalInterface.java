package com.tado.android.report.interfaces;

import com.tado.android.report.model.ChartXValueRange;

public interface ChartDrawingIntervalInterface {
    ChartXValueRange getInterval();

    boolean shouldDrawInterval();
}
