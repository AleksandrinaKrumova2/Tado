package com.tado.android.settings;

import com.tado.android.rest.model.HomeInfo.LicenseEnum;
import com.tado.android.utils.Constants;
import com.tado.android.utils.Util;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl.Builder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Months;
import org.joda.time.ReadableInstant;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u001f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0015\u001a\u00020\nH\u0002J\b\u0010\u0016\u001a\u00020\nH\u0002J\u0018\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u0011H\u0002J\u0018\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u0011H\u0002J\b\u0010\u001b\u001a\u00020\nH\u0002J\b\u0010\u001c\u001a\u00020\nH\u0002J\u0018\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\nH\u0014J\u0018\u0010 \u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\nH\u0002J\u0006\u0010!\u001a\u00020\u0011J8\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020\u00112\u0006\u0010%\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010'\u001a\u00020(H\u0002J\u0010\u0010)\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020\u0011H\u0016J\u0006\u0010+\u001a\u00020,J\u0016\u0010-\u001a\u00020,2\u0006\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u0011J\u000e\u0010.\u001a\u00020,2\u0006\u0010*\u001a\u00020\u0011R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n \u000b*\u0004\u0018\u00010\r0\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006/"}, d2 = {"Lcom/tado/android/settings/EnergySavingsPresenter;", "", "view", "Lcom/tado/android/settings/EnergySavingsView;", "isDemo", "", "session", "Lcom/tado/android/settings/Session;", "(Lcom/tado/android/settings/EnergySavingsView;ZLcom/tado/android/settings/Session;)V", "firstReportDate", "Lorg/joda/time/DateTime;", "kotlin.jvm.PlatformType", "homeTimeZone", "Lorg/joda/time/DateTimeZone;", "previousDate", "", "selectedMonth", "", "selectedYear", "getView", "()Lcom/tado/android/settings/EnergySavingsView;", "getBeginningOfCurrentMonth", "getEarliestDate", "getFormattedDateForRequest", "year", "month", "getFormattedDateForUI", "getHomeCreation", "getLastSelectableMonth", "getNumberOfMonths", "startDate", "endDate", "getNumberOfMonthsSpan", "getNumberOfReports", "getUrl", "language", "homeId", "username", "password", "licenseEnum", "Lcom/tado/android/rest/model/HomeInfo$LicenseEnum;", "getUrlForPosition", "position", "onMonthPickerClick", "", "showReportForDate", "showReportForPosition", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: EnergySavingsPresenter.kt */
public class EnergySavingsPresenter {
    private final DateTime firstReportDate = new DateTime().withYear(2017).withMonthOfYear(8).withDayOfMonth(1).withTimeAtStartOfDay().withZone(DateTimeZone.forID(this.session.getHomeTimeZone()));
    private final DateTimeZone homeTimeZone = DateTimeZone.forID(this.session.getHomeTimeZone());
    private final boolean isDemo;
    private String previousDate = "";
    private int selectedMonth = getLastSelectableMonth().getMonthOfYear();
    private int selectedYear = getLastSelectableMonth().getYear();
    private final Session session;
    @Nullable
    private final EnergySavingsView view;

    public EnergySavingsPresenter(@Nullable EnergySavingsView view, boolean isDemo, @NotNull Session session) {
        Intrinsics.checkParameterIsNotNull(session, SettingsJsonConstants.SESSION_KEY);
        this.view = view;
        this.isDemo = isDemo;
        this.session = session;
    }

    @Nullable
    public final EnergySavingsView getView() {
        return this.view;
    }

    private final DateTime getEarliestDate() {
        DateTime withTimeAtStartOfDay = ((DateTime) ComparisonsKt___ComparisonsKt.minOf((Comparable) DateTime.now().minusMonths(1), ComparisonsKt___ComparisonsKt.maxOf((Comparable) getHomeCreation(), (Comparable) this.firstReportDate))).withDayOfMonth(1).withTimeAtStartOfDay();
        Intrinsics.checkExpressionValueIsNotNull(withTimeAtStartOfDay, "minOf(DateTime.now().min…1).withTimeAtStartOfDay()");
        return withTimeAtStartOfDay;
    }

    private final DateTime getHomeCreation() {
        return new DateTime(this.session.getHomeCreation(), this.homeTimeZone);
    }

    private final DateTime getBeginningOfCurrentMonth() {
        return new DateTime(DateTime.now().withDayOfMonth(1).withTimeAtStartOfDay(), this.homeTimeZone);
    }

    private final DateTime getLastSelectableMonth() {
        DateTime minusMonths = getBeginningOfCurrentMonth().minusMonths(1);
        Intrinsics.checkExpressionValueIsNotNull(minusMonths, "getBeginningOfCurrentMonth().minusMonths(1)");
        return minusMonths;
    }

    protected int getNumberOfMonths(@NotNull DateTime startDate, @NotNull DateTime endDate) {
        Intrinsics.checkParameterIsNotNull(startDate, "startDate");
        Intrinsics.checkParameterIsNotNull(endDate, "endDate");
        Months monthsBetween = Months.monthsBetween((ReadableInstant) startDate.withDayOfMonth(1).withTimeAtStartOfDay(), (ReadableInstant) endDate.withDayOfMonth(1).withTimeAtStartOfDay());
        Intrinsics.checkExpressionValueIsNotNull(monthsBetween, "Months.monthsBetween(sta…).withTimeAtStartOfDay())");
        return monthsBetween.getMonths();
    }

    private final int getNumberOfMonthsSpan(DateTime startDate, DateTime endDate) {
        return getNumberOfMonths(startDate, endDate) + 1;
    }

    private final String getFormattedDateForUI(int year, int month) {
        Object[] objArr = new Object[2];
        DateFormatSymbols instance = DateFormatSymbols.getInstance(Locale.getDefault());
        Intrinsics.checkExpressionValueIsNotNull(instance, "DateFormatSymbols.getInstance(Locale.getDefault())");
        objArr[0] = instance.getMonths()[month - 1];
        objArr[1] = Integer.valueOf(year);
        String format = String.format("%s %d", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(this, *args)");
        return format;
    }

    private final String getFormattedDateForRequest(int year, int month) {
        Object[] objArr = new Object[]{Integer.valueOf(year), Integer.valueOf(month)};
        String format = String.format("%d-%02d", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(this, *args)");
        return format;
    }

    public final void showReportForDate(int year, int month) {
        String date = getFormattedDateForRequest(year, month);
        if (!Intrinsics.areEqual(date, this.previousDate)) {
            this.selectedYear = year;
            this.selectedMonth = month;
            this.previousDate = date;
            DateTime current = new DateTime().withMonthOfYear(month).withYear(year).withDayOfMonth(1).withZone(this.homeTimeZone);
            DateTime earliestDate = getEarliestDate();
            Intrinsics.checkExpressionValueIsNotNull(current, "current");
            int position = getNumberOfMonthsSpan(earliestDate, current) - 1;
            EnergySavingsView energySavingsView = this.view;
            if (energySavingsView != null) {
                energySavingsView.showPage(position);
            }
            energySavingsView = this.view;
            if (energySavingsView != null) {
                energySavingsView.setMonthPickerDate(getFormattedDateForUI(year, month));
            }
        }
    }

    public final void onMonthPickerClick() {
        EnergySavingsView energySavingsView = this.view;
        if (energySavingsView != null) {
            energySavingsView.showMonthPicker(this.selectedYear, this.selectedMonth, getLastSelectableMonth(), getEarliestDate());
        }
    }

    public final void showReportForPosition(int position) {
        DateTime selectedDate = getEarliestDate().plusMonths(position);
        Intrinsics.checkExpressionValueIsNotNull(selectedDate, "selectedDate");
        this.selectedYear = selectedDate.getYear();
        this.selectedMonth = selectedDate.getMonthOfYear();
        this.previousDate = getFormattedDateForRequest(this.selectedYear, this.selectedMonth);
        EnergySavingsView energySavingsView = this.view;
        if (energySavingsView != null) {
            energySavingsView.setMonthPickerDate(getFormattedDateForUI(this.selectedYear, this.selectedMonth));
        }
    }

    public final int getNumberOfReports() {
        return Math.max(getNumberOfMonths(getEarliestDate(), getBeginningOfCurrentMonth()), 1);
    }

    @NotNull
    public String getUrlForPosition(int position) {
        DateTime currentDate = getEarliestDate().plusMonths(position);
        Intrinsics.checkExpressionValueIsNotNull(currentDate, "currentDate");
        String formattedDate = getFormattedDateForRequest(currentDate.getYear(), currentDate.getMonthOfYear());
        String supportedLanguage = Util.getSupportedLanguage();
        Intrinsics.checkExpressionValueIsNotNull(supportedLanguage, "Util.getSupportedLanguage()");
        return getUrl(supportedLanguage, this.session.getHomeId(), this.session.getUsername(), this.session.getPassword(), formattedDate, this.session.getLicense());
    }

    private final String getUrl(String language, int homeId, String username, String password, String month, LicenseEnum licenseEnum) {
        if (this.isDemo) {
            String httpUrl = new Builder().scheme("https").host("energy-savings-report.tado.com").addPathSegments("" + language + '/').build().toString();
            Intrinsics.checkExpressionValueIsNotNull(httpUrl, "HttpUrl.Builder()\n      …              .toString()");
            return httpUrl;
        }
        httpUrl = new Builder().scheme("https").host("energy-savings-report.tado.com").addPathSegments("" + language + '/').addQueryParameter("homeId", "" + homeId).addQueryParameter("month", month).addQueryParameter(Constants.KEY_EXTRA_USERNAME, username).addQueryParameter(Constants.KEY_EXTRA_PASSWORD, password).addQueryParameter("license", "" + licenseEnum).build().toString();
        Intrinsics.checkExpressionValueIsNotNull(httpUrl, "HttpUrl.Builder()\n      …              .toString()");
        return httpUrl;
    }
}
