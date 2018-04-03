package com.tado.android.notifications;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatterBuilder;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0016\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0001¨\u0006\u000b"}, d2 = {"getDisplayMonth", "", "esrMonth", "locale", "Ljava/util/Locale;", "getFormattedSavings", "savings", "", "getNotificationTimeMillis", "", "time", "4.9.3-1500409030_tadoRelease"}, k = 2, mv = {1, 1, 9})
/* compiled from: NotificationFormatter.kt */
public final class NotificationFormatterKt {
    @NotNull
    public static final String getDisplayMonth(@NotNull String esrMonth, @NotNull Locale locale) {
        Intrinsics.checkParameterIsNotNull(esrMonth, "esrMonth");
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM").parse(esrMonth);
            Calendar calendar = Calendar.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            calendar.setTime(date);
            String displayName = calendar.getDisplayName(2, 2, locale);
            Intrinsics.checkExpressionValueIsNotNull(displayName, "calendar.getDisplayName(…H, Calendar.LONG, locale)");
            return displayName;
        } catch (ParseException e) {
            return "";
        }
    }

    @NotNull
    public static final String getFormattedSavings(double savings, @NotNull Locale locale) {
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        String format = NumberFormat.getNumberInstance(locale).format(savings);
        Intrinsics.checkExpressionValueIsNotNull(format, "formatter.format(savings)");
        return format;
    }

    public static final long getNotificationTimeMillis(@NotNull String time) {
        DateTime withTimeAtStartOfDay;
        Intrinsics.checkParameterIsNotNull(time, "time");
        DateTime datetime = DateTime.parse(time, new DateTimeFormatterBuilder().appendHourOfDay(2).appendLiteral(":").appendMinuteOfHour(2).toFormatter());
        try {
            withTimeAtStartOfDay = DateTime.now().withTimeAtStartOfDay();
            Intrinsics.checkExpressionValueIsNotNull(datetime, "datetime");
            DateTime date = withTimeAtStartOfDay.plusMinutes(datetime.getMinuteOfDay());
            Intrinsics.checkExpressionValueIsNotNull(date, "date");
            if (date.isBeforeNow()) {
                date = date.plusDays(1);
            }
            Intrinsics.checkExpressionValueIsNotNull(date, "date");
            return date.getMillis();
        } catch (ParseException e) {
            withTimeAtStartOfDay = DateTime.now();
            Intrinsics.checkExpressionValueIsNotNull(withTimeAtStartOfDay, "DateTime.now()");
            return withTimeAtStartOfDay.getMillis();
        }
    }
}
