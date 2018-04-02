package com.google.android.gms.analytics;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.util.Log;
import com.google.android.gms.internal.zzaqa;
import com.google.android.gms.internal.zzaqc;
import com.google.android.gms.internal.zzarl;
import com.google.android.gms.internal.zzaru;
import com.google.android.gms.internal.zzasi;
import com.google.android.gms.internal.zzask;
import com.google.android.gms.internal.zzasm;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class GoogleAnalytics extends zza {
    private static List<Runnable> zzdou = new ArrayList();
    private boolean zzare;
    private Set<zza> zzdov = new HashSet();
    private boolean zzdow;
    private boolean zzdox;
    private volatile boolean zzdoy;
    private boolean zzdoz;

    interface zza {
        void zzl(Activity activity);

        void zzm(Activity activity);
    }

    @TargetApi(14)
    class zzb implements ActivityLifecycleCallbacks {
        private /* synthetic */ GoogleAnalytics zzdpa;

        zzb(GoogleAnalytics googleAnalytics) {
            this.zzdpa = googleAnalytics;
        }

        public final void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public final void onActivityDestroyed(Activity activity) {
        }

        public final void onActivityPaused(Activity activity) {
        }

        public final void onActivityResumed(Activity activity) {
        }

        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public final void onActivityStarted(Activity activity) {
            this.zzdpa.zzj(activity);
        }

        public final void onActivityStopped(Activity activity) {
            this.zzdpa.zzk(activity);
        }
    }

    public GoogleAnalytics(zzaqc com_google_android_gms_internal_zzaqc) {
        super(com_google_android_gms_internal_zzaqc);
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public static GoogleAnalytics getInstance(Context context) {
        return zzaqc.zzbm(context).zzxi();
    }

    public static void zzur() {
        synchronized (GoogleAnalytics.class) {
            if (zzdou != null) {
                for (Runnable run : zzdou) {
                    run.run();
                }
                zzdou = null;
            }
        }
    }

    public final void dispatchLocalHits() {
        zzum().zzwx().zzwn();
    }

    @TargetApi(14)
    public final void enableAutoActivityReports(Application application) {
        if (!this.zzdow) {
            application.registerActivityLifecycleCallbacks(new zzb(this));
            this.zzdow = true;
        }
    }

    public final boolean getAppOptOut() {
        return this.zzdoy;
    }

    @Deprecated
    public final Logger getLogger() {
        return zzaru.getLogger();
    }

    public final void initialize() {
        zzasm zzwz = zzum().zzwz();
        zzwz.zzaai();
        if (zzwz.zzaaj()) {
            setDryRun(zzwz.zzaak());
        }
        zzwz.zzaai();
        this.zzare = true;
    }

    public final boolean isDryRunEnabled() {
        return this.zzdox;
    }

    public final boolean isInitialized() {
        return this.zzare;
    }

    public final Tracker newTracker(int i) {
        zzaqa tracker;
        synchronized (this) {
            tracker = new Tracker(zzum(), null, null);
            if (i > 0) {
                zzask com_google_android_gms_internal_zzask = (zzask) new zzasi(zzum()).zzav(i);
                if (com_google_android_gms_internal_zzask != null) {
                    tracker.zza(com_google_android_gms_internal_zzask);
                }
            }
            tracker.initialize();
        }
        return tracker;
    }

    public final Tracker newTracker(String str) {
        zzaqa tracker;
        synchronized (this) {
            tracker = new Tracker(zzum(), str, null);
            tracker.initialize();
        }
        return tracker;
    }

    public final void reportActivityStart(Activity activity) {
        if (!this.zzdow) {
            zzj(activity);
        }
    }

    public final void reportActivityStop(Activity activity) {
        if (!this.zzdow) {
            zzk(activity);
        }
    }

    public final void setAppOptOut(boolean z) {
        this.zzdoy = z;
        if (this.zzdoy) {
            zzum().zzwx().zzwm();
        }
    }

    public final void setDryRun(boolean z) {
        this.zzdox = z;
    }

    public final void setLocalDispatchPeriod(int i) {
        zzum().zzwx().setLocalDispatchPeriod(i);
    }

    @Deprecated
    public final void setLogger(Logger logger) {
        zzaru.setLogger(logger);
        if (!this.zzdoz) {
            String str = (String) zzarl.zzdvy.get();
            Log.i((String) zzarl.zzdvy.get(), new StringBuilder(String.valueOf(str).length() + 112).append("GoogleAnalytics.setLogger() is deprecated. To enable debug logging, please run:\nadb shell setprop log.tag.").append(str).append(" DEBUG").toString());
            this.zzdoz = true;
        }
    }

    final void zza(zza com_google_android_gms_analytics_GoogleAnalytics_zza) {
        this.zzdov.add(com_google_android_gms_analytics_GoogleAnalytics_zza);
        Context context = zzum().getContext();
        if (context instanceof Application) {
            enableAutoActivityReports((Application) context);
        }
    }

    final void zzb(zza com_google_android_gms_analytics_GoogleAnalytics_zza) {
        this.zzdov.remove(com_google_android_gms_analytics_GoogleAnalytics_zza);
    }

    final void zzj(Activity activity) {
        for (zza zzl : this.zzdov) {
            zzl.zzl(activity);
        }
    }

    final void zzk(Activity activity) {
        for (zza zzm : this.zzdov) {
            zzm.zzm(activity);
        }
    }
}
