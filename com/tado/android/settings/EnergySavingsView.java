package com.tado.android.settings;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J(\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\bH&¨\u0006\u000f"}, d2 = {"Lcom/tado/android/settings/EnergySavingsView;", "", "setMonthPickerDate", "", "date", "", "showMonthPicker", "selectedYear", "", "selectedMonth", "maxDate", "Lorg/joda/time/DateTime;", "earliestDate", "showPage", "position", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: EnergySavingsPresenter.kt */
public interface EnergySavingsView {
    void setMonthPickerDate(@NotNull String str);

    void showMonthPicker(int i, int i2, @NotNull DateTime dateTime, @NotNull DateTime dateTime2);

    void showPage(int i);
}
