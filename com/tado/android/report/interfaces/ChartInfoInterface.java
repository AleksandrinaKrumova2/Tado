package com.tado.android.report.interfaces;

import android.content.Context;

public interface ChartInfoInterface {
    float getBottomPadding();

    float getCanvasBottomY();

    float getCanvasTopY();

    float getChartBottomY();

    long getChartLeftX();

    long getChartRightX();

    float getChartTopY();

    Context getContext();

    float getLastPointX();

    boolean isHorizontalAxisTextVisible();

    boolean isVerticalAxisTextVisible();
}
