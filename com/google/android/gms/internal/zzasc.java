package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.internal.zzbq;

public final class zzasc {
    static Object sLock = new Object();
    private static Boolean zzdoo;
    static zzcxt zzdyt;

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public static void onReceive(Context context, Intent intent) {
        zzapz zzwt = zzaqc.zzbm(context).zzwt();
        if (intent == null) {
            zzwt.zzdx("AnalyticsReceiver called with null intent");
            return;
        }
        String action = intent.getAction();
        zzwt.zza("Local AnalyticsReceiver got", action);
        if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(action)) {
            boolean zzbo = zzasd.zzbo(context);
            Intent intent2 = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            intent2.setComponent(new ComponentName(context, "com.google.android.gms.analytics.AnalyticsService"));
            intent2.setAction("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            synchronized (sLock) {
                context.startService(intent2);
                if (zzbo) {
                    try {
                        if (zzdyt == null) {
                            zzcxt com_google_android_gms_internal_zzcxt = new zzcxt(context, 1, "Analytics WakeLock");
                            zzdyt = com_google_android_gms_internal_zzcxt;
                            com_google_android_gms_internal_zzcxt.setReferenceCounted(false);
                        }
                        zzdyt.acquire(1000);
                    } catch (SecurityException e) {
                        zzwt.zzdx("Analytics service at risk of not starting. For more reliable analytics, add the WAKE_LOCK permission to your manifest. See http://goo.gl/8Rd3yj for instructions.");
                    }
                    return;
                }
            }
        }
    }

    public static boolean zzbk(Context context) {
        zzbq.checkNotNull(context);
        if (zzdoo != null) {
            return zzdoo.booleanValue();
        }
        boolean zzb = zzasl.zzb(context, "com.google.android.gms.analytics.AnalyticsReceiver", false);
        zzdoo = Boolean.valueOf(zzb);
        return zzb;
    }
}
