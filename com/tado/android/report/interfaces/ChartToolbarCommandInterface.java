package com.tado.android.report.interfaces;

import android.util.Pair;
import com.tado.android.report.ChartToolbarViewElement.ToolbarButtonTypeEnum;

public interface ChartToolbarCommandInterface {
    void execute(Pair<ToolbarButtonTypeEnum, Boolean> pair);
}
