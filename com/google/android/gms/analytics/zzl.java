package com.google.android.gms.analytics;

import android.util.Log;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.FutureTask;

final class zzl extends FutureTask<T> {
    private /* synthetic */ zza zzdqd;

    zzl(zza com_google_android_gms_analytics_zzj_zza, Runnable runnable, Object obj) {
        this.zzdqd = com_google_android_gms_analytics_zzj_zza;
        super(runnable, obj);
    }

    protected final void setException(Throwable th) {
        UncaughtExceptionHandler zzb = this.zzdqd.zzdqc.zzdqa;
        if (zzb != null) {
            zzb.uncaughtException(Thread.currentThread(), th);
        } else if (Log.isLoggable("GAv4", 6)) {
            String valueOf = String.valueOf(th);
            Log.e("GAv4", new StringBuilder(String.valueOf(valueOf).length() + 37).append("MeasurementExecutor: job failed with ").append(valueOf).toString());
        }
        super.setException(th);
    }
}
