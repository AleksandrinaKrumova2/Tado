package com.tado.android.report.interfaces;

import android.graphics.Canvas;
import com.tado.android.report.ChartUtils.CoordinateType;
import com.tado.android.report.ReportViewElement.ReportViewElementType;

public interface ReportViewElementInterface {
    float calculateCoordinate(float f, CoordinateType coordinateType);

    void drawViewElement(Canvas canvas);

    ChartInfoInterface getChartInfoInterface();

    ReportViewElementType getType();

    void init(ChartInfoInterface chartInfoInterface);

    void inspectionModeActivation(boolean z);

    void setTouch(int i, float f, float f2);
}
