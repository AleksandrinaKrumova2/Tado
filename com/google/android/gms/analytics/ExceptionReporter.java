package com.google.android.gms.analytics;

import android.content.Context;
import com.google.android.gms.analytics.HitBuilders.ExceptionBuilder;
import com.google.android.gms.internal.zzaru;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;

public class ExceptionReporter implements UncaughtExceptionHandler {
    private final Context mContext;
    private final UncaughtExceptionHandler zzdoq;
    private final Tracker zzdor;
    private ExceptionParser zzdos;
    private GoogleAnalytics zzdot;

    public ExceptionReporter(Tracker tracker, UncaughtExceptionHandler uncaughtExceptionHandler, Context context) {
        if (tracker == null) {
            throw new NullPointerException("tracker cannot be null");
        } else if (context == null) {
            throw new NullPointerException("context cannot be null");
        } else {
            this.zzdoq = uncaughtExceptionHandler;
            this.zzdor = tracker;
            this.zzdos = new StandardExceptionParser(context, new ArrayList());
            this.mContext = context.getApplicationContext();
            String str = "ExceptionReporter created, original handler is ";
            String valueOf = String.valueOf(uncaughtExceptionHandler == null ? "null" : uncaughtExceptionHandler.getClass().getName());
            zzaru.m3v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
    }

    public ExceptionParser getExceptionParser() {
        return this.zzdos;
    }

    public void setExceptionParser(ExceptionParser exceptionParser) {
        this.zzdos = exceptionParser;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        String str = "UncaughtException";
        if (this.zzdos != null) {
            str = this.zzdos.getDescription(thread != null ? thread.getName() : null, th);
        }
        String str2 = "Reporting uncaught exception: ";
        String valueOf = String.valueOf(str);
        zzaru.m3v(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        this.zzdor.send(new ExceptionBuilder().setDescription(str).setFatal(true).build());
        if (this.zzdot == null) {
            this.zzdot = GoogleAnalytics.getInstance(this.mContext);
        }
        zza com_google_android_gms_analytics_zza = this.zzdot;
        com_google_android_gms_analytics_zza.dispatchLocalHits();
        com_google_android_gms_analytics_zza.zzum().zzwx().zzwo();
        if (this.zzdoq != null) {
            zzaru.m3v("Passing exception to the original handler");
            this.zzdoq.uncaughtException(thread, th);
        }
    }

    final UncaughtExceptionHandler zzuq() {
        return this.zzdoq;
    }
}
