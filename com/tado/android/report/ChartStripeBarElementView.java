package com.tado.android.report;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import com.tado.C0676R;
import com.tado.android.report.ReportViewElement.ReportViewElementType;
import com.tado.android.report.interfaces.ChartInfoInterface;
import com.tado.android.report.model.ChartStripe;
import java.util.ArrayList;
import java.util.List;

class ChartStripeBarElementView extends ReportViewElement {
    private Paint paint;
    private List<ChartStripe> stripeList = new ArrayList();

    ChartStripeBarElementView(List<ChartStripe> stripes) {
        this.stripeList = stripes;
    }

    public void init(ChartInfoInterface chartInfo) {
        super.init(chartInfo);
        this.paint = ChartUtils.getPaint((int) C0676R.color.away, 1.0f, Style.FILL_AND_STROKE);
    }

    public void drawViewElement(Canvas canvas) {
        if (!this.stripeList.isEmpty()) {
            for (ChartStripe chartStripe : this.stripeList) {
                float leftX = (float) getXCoordinate(chartStripe.getInterval().getStart());
                float rightX = (float) getXCoordinate(chartStripe.getInterval().getEnd());
                this.paint.setColor(chartStripe.getColorPair().darkColor);
                if (this.inspectionModeActive) {
                    if (chartStripe.getInterval().isInRangeExludingEnd(getTimeFromXCoordinate((float) this.touchedX))) {
                        this.paint.setColor(chartStripe.getColorPair().darkColor);
                    } else {
                        this.paint.setColor(chartStripe.getColorPair().lightColor);
                    }
                }
                Canvas canvas2 = canvas;
                canvas2.drawRect(leftX, this.chartInfo.getCanvasBottomY(), rightX, this.chartInfo.getBottomPadding() + this.chartInfo.getCanvasBottomY(), this.paint);
            }
        }
    }

    public ChartInfoInterface getChartInfoInterface() {
        return this.chartInfo;
    }

    public ReportViewElementType getType() {
        return ReportViewElementType.CHART_STRIPE_BAR;
    }
}
