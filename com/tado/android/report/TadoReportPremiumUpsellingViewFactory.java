package com.tado.android.report;

import android.content.Context;
import android.support.annotation.NonNull;
import com.tado.C0676R;
import com.tado.android.controllers.ZoneController;
import com.tado.android.menu.ZoneItem;
import com.tado.android.report.ChartToolbarViewElement.ToolbarButtonTypeEnum;
import com.tado.android.rest.model.DayReport;
import com.tado.android.rest.model.Zone.TypeEnum;
import com.tado.android.utils.UserConfig;
import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormat;

class TadoReportPremiumUpsellingViewFactory {
    private ZoneItem currentZoneItem;
    private DayReport dayReport;
    private DateTime selectedDate;
    private TypeEnum zoneType = TypeEnum.HEATING;

    TadoReportPremiumUpsellingViewFactory() {
    }

    TadoReportView createTadoReportView(Context context, DayReport dayReport, DateTime selectedDate) {
        this.dayReport = dayReport;
        this.selectedDate = selectedDate;
        TadoReportView report = new TadoReportView(context, selectedDate, dayReport);
        this.currentZoneItem = ZoneController.INSTANCE.getZoneItemById(UserConfig.getCurrentZone().intValue());
        if (this.currentZoneItem != null) {
            this.zoneType = this.currentZoneItem.getZoneType();
        }
        initTadoReport(report);
        return report;
    }

    private void initTadoReport(TadoReportView report) {
        report.addReportViewElement(new ChartDoneViewElement(), new ToolbarButtonTypeEnum[0]);
        report.addReportViewElement(getChartDateAndTimeElement(this.currentZoneItem, this.selectedDate), new ToolbarButtonTypeEnum[0]);
        report.invalidate();
    }

    @NonNull
    private ChartDateAndTimeViewElement getChartDateAndTimeElement(ZoneItem currentZoneItem, DateTime selectedDate) {
        ChartDateAndTimeViewElement header = new ChartDateAndTimeViewElement(UserConfig.getCurrentZoneName(), currentZoneItem != null ? currentZoneItem.getZoneImageResource() : C0676R.drawable.zone_list_device_st);
        header.setSelectedDay(DateTimeFormat.forPattern("EEEE, d MMMM YYYY").print((ReadableInstant) selectedDate));
        return header;
    }
}
