package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.internal.zzbq;

public final class zzasd<T extends Context & zzasg> {
    private static Boolean zzdyv;
    private final Handler mHandler = new Handler();
    private final T zzdyu;

    public zzasd(T t) {
        zzbq.checkNotNull(t);
        this.zzdyu = t;
    }

    private final void zza(Integer num, JobParameters jobParameters) {
        zzaqc zzbm = zzaqc.zzbm(this.zzdyu);
        zzbm.zzwx().zza(new zzase(this, num, zzbm, zzbm.zzwt(), jobParameters));
    }

    public static boolean zzbo(Context context) {
        zzbq.checkNotNull(context);
        if (zzdyv != null) {
            return zzdyv.booleanValue();
        }
        boolean zzt = zzasl.zzt(context, "com.google.android.gms.analytics.AnalyticsService");
        zzdyv = Boolean.valueOf(zzt);
        return zzt;
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public final void onCreate() {
        zzaqc.zzbm(this.zzdyu).zzwt().zzdu("Local AnalyticsService is starting up");
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public final void onDestroy() {
        zzaqc.zzbm(this.zzdyu).zzwt().zzdu("Local AnalyticsService is shutting down");
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public final int onStartCommand(Intent intent, int i, int i2) {
        try {
            synchronized (zzasc.sLock) {
                zzcxt com_google_android_gms_internal_zzcxt = zzasc.zzdyt;
                if (com_google_android_gms_internal_zzcxt != null && com_google_android_gms_internal_zzcxt.isHeld()) {
                    com_google_android_gms_internal_zzcxt.release();
                }
            }
        } catch (SecurityException e) {
        }
        zzapz zzwt = zzaqc.zzbm(this.zzdyu).zzwt();
        if (intent == null) {
            zzwt.zzdx("AnalyticsService started with null intent");
        } else {
            String action = intent.getAction();
            zzwt.zza("Local AnalyticsService called. startId, action", Integer.valueOf(i2), action);
            if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(action)) {
                zza(Integer.valueOf(i2), null);
            }
        }
        return 2;
    }

    @TargetApi(24)
    public final boolean onStartJob(JobParameters jobParameters) {
        zzapz zzwt = zzaqc.zzbm(this.zzdyu).zzwt();
        String string = jobParameters.getExtras().getString("action");
        zzwt.zza("Local AnalyticsJobService called. action", string);
        if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(string)) {
            zza(null, jobParameters);
        }
        return true;
    }
}
