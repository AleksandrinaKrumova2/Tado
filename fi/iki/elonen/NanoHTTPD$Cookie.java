package fi.iki.elonen;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class NanoHTTPD$Cookie {
    private final String f425e;
    private final String f426n;
    private final String f427v;

    public static String getHTTPTime(int days) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        calendar.add(5, days);
        return dateFormat.format(calendar.getTime());
    }

    public NanoHTTPD$Cookie(String name, String value) {
        this(name, value, 30);
    }

    public NanoHTTPD$Cookie(String name, String value, int numDays) {
        this.f426n = name;
        this.f427v = value;
        this.f425e = getHTTPTime(numDays);
    }

    public NanoHTTPD$Cookie(String name, String value, String expires) {
        this.f426n = name;
        this.f427v = value;
        this.f425e = expires;
    }

    public String getHTTPHeader() {
        return String.format("%s=%s; expires=%s", new Object[]{this.f426n, this.f427v, this.f425e});
    }
}
