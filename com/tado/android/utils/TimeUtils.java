package com.tado.android.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Period;
import org.joda.time.ReadableInstant;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.PeriodFormatterBuilder;

public class TimeUtils {
    public static final int MINUTES_IN_A_DAY = 1440;
    public static final int SECONDS_IN_A_DAY = 86400;

    public static String GetUTCdatetimeAsString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(new Date());
    }

    public static String GetUTCdatetimeAsStringSubHours(int hours) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(Long.valueOf(new Date().getTime() - ((long) (((hours * 60) * 60) * 1000))));
    }

    public static Date GetUTCdatetimeAsDate(String timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date utcTime = null;
        try {
            utcTime = sdf.parse(timestamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return utcTime;
    }

    public static long getMillisfFromHomeTimezoneTimestamp(String timestamp) {
        DateTimeZone.setDefault(getHomeTimeZone());
        return new DateTime((Object) timestamp).getMillis();
    }

    public static String getHoursAndMinutesInHomeTimezone(String timestamp) {
        DateTimeZone.setDefault(getHomeTimeZone());
        return DateTimeFormat.forPattern("HH:mm").print(new DateTime((Object) timestamp));
    }

    public static String getHoursAndMinutesDescriptiveInHomeTimezone(String timestamp) {
        DateTimeZone.setDefault(getHomeTimeZone());
        ReadableInstant dateTime = new DateTime((Object) timestamp);
        DateTime now = DateTime.now();
        if (dateTime.getMillis() <= now.getMillis() - (now.getMillis() % 86400000)) {
            return DateTimeFormat.forPattern("dd/MM HH:mm").print(dateTime);
        }
        return TadoApplication.getTadoAppContext().getString(C0676R.string.notification_today) + " " + DateTimeFormat.forPattern("HH:mm").print(dateTime);
    }

    public static DateTime GetHomeTimezoneAsDate(String timestamp) {
        DateTimeZone.setDefault(getHomeTimeZone());
        return new DateTime((Object) timestamp);
    }

    public static String getDateCurrentTimeZone(Date timestamp) {
        try {
            String timezone = UserConfig.getHomeTimezone();
            if (timezone == null || timezone.equals("")) {
                timezone = "UTC";
            }
            GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone(timezone));
            cal.setTime(timestamp);
            Date currenTimeZone = cal.getTime();
            if (cal.getTime().getTime() <= new Date().getTime() - (new Date().getTime() % 86400000)) {
                return new SimpleDateFormat("dd/MM HH:mm").format(currenTimeZone);
            }
            return TadoApplication.getTadoAppContext().getString(C0676R.string.notification_today) + " " + new SimpleDateFormat("HH:mm").format(currenTimeZone);
        } catch (Exception e) {
            return "";
        }
    }

    public static int convertStringHourAndMinutesToSeconds(String value) {
        if (value == null || value.equals("") || !value.contains(":")) {
            return 0;
        }
        String[] time = value.split(":");
        int hours = 0;
        int minutes = 0;
        if (!(time[0] == null || time[0].equals(""))) {
            hours = Integer.parseInt(time[0]);
        }
        if (!(time[1] == null || time[1].equals(""))) {
            minutes = Integer.parseInt(time[1]);
        }
        return ((hours * 60) + minutes) * 60;
    }

    @Deprecated
    public static String convertSecondsToStringHourAndMinutes(int seconds) {
        return getHoursAndMinutesString(getHours(seconds), getMinutesRemainder(seconds));
    }

    public static int getSeconds(int hours, int minutes) {
        return getMinutes(hours, minutes) * 60;
    }

    public static int getMinutes(int hours, int minutes) {
        return (hours * 60) + minutes;
    }

    public static String getHoursAndMinutesString(int hours, int minutes) {
        String time = "";
        if (hours < 10) {
            time = "0";
        }
        time = time + hours + ":";
        if (hours > 23) {
            time = "00:";
        }
        if (minutes < 10) {
            time = time + "0";
        }
        return time + minutes;
    }

    public static String getHoursAndMinutesFromMinutes(int minutes) {
        int hours = minutes / 60;
        int min = minutes % 60;
        return String.format(Locale.US, "%d:%02d", new Object[]{Integer.valueOf(hours), Integer.valueOf(min)});
    }

    public static String formatTimeToLocale(int hours, int minutes, @Nullable Locale locale) {
        Locale useLocale;
        GregorianCalendar dateTime = new GregorianCalendar();
        dateTime.set(11, hours);
        dateTime.set(12, minutes);
        if (locale == null) {
            useLocale = Locale.getDefault();
        } else {
            useLocale = locale;
        }
        return DateFormat.getTimeInstance(3, useLocale).format(dateTime.getTime());
    }

    public static String formatTimeToLocale(int hours, int minutes) {
        return formatTimeToLocale(hours, minutes, Locale.getDefault());
    }

    public static String formatTimeToLocale(String time, @Nullable Locale locale) {
        try {
            Date parsedDate = new SimpleDateFormat("HH:mm", locale != null ? locale : Locale.getDefault()).parse(time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parsedDate);
            time = formatTimeToLocale(calendar.get(11), calendar.get(12), locale);
        } catch (ParseException e) {
        }
        return time;
    }

    public static String formatTimeToLocale(String time) {
        return formatTimeToLocale(time, Locale.getDefault());
    }

    public static int getHours(int seconds) {
        return getHours(seconds, true);
    }

    public static int getHours(int seconds, boolean roundToDay) {
        int value = (seconds / 60) / 60;
        if (roundToDay) {
            return value % 24;
        }
        return value;
    }

    public static int getMinutesRemainder(int seconds) {
        return (seconds / 60) % 60;
    }

    public static String getTimeFromSeconds(int secondsToFormat) {
        return new PeriodFormatterBuilder().printZeroAlways().appendHours().minimumPrintedDigits(2).appendSeparator(":").appendMinutes().appendSeparator(":").appendSeconds().toFormatter().print(new Period(Seconds.seconds(secondsToFormat)).toStandardDuration().toPeriod());
    }

    public static String formatHoursToAmPm(int hours, boolean is24HourFormat) {
        SimpleDateFormat sdf;
        if (is24HourFormat) {
            sdf = new SimpleDateFormat("H", Locale.getDefault());
        } else {
            sdf = new SimpleDateFormat("h a", Locale.getDefault());
        }
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(11, hours);
        return sdf.format(calendar.getTime());
    }

    public static String formatTimeToAmPm(int hours, int minutes, boolean is24HourFormat) {
        SimpleDateFormat sdf;
        if (is24HourFormat) {
            sdf = new SimpleDateFormat("H:mm", Locale.US);
        } else {
            sdf = new SimpleDateFormat("h:mm a", Locale.US);
        }
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(11, hours);
        calendar.set(12, minutes);
        return sdf.format(calendar.getTime());
    }

    public static int getCurrentDayOfMonthInHomeTimeZone() {
        DateTimeZone.setDefault(getHomeTimeZone());
        return new DateTime(System.currentTimeMillis()).getDayOfMonth();
    }

    public static int getMonthOfYearInHomeTimeZone() {
        return getCurrentDateTimeInHomeTimeZone().getMonthOfYear();
    }

    @NonNull
    private static DateTime getCurrentDateTimeInHomeTimeZone() {
        DateTimeZone.setDefault(getHomeTimeZone());
        return new DateTime(System.currentTimeMillis());
    }

    @NonNull
    public static DateTimeZone getHomeTimeZone() {
        return getTimeZone(UserConfig.getHomeTimezone());
    }

    @NonNull
    public static DateTimeZone getTimeZone(String timezone) {
        try {
            return DateTimeZone.forID(timezone);
        } catch (Exception e) {
            return DateTimeZone.getDefault();
        }
    }
}
