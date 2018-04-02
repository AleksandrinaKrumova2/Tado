package com.google.android.gms.analytics;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzapd;
import com.google.android.gms.internal.zzapi;
import com.google.android.gms.internal.zzasl;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzj {
    private static volatile zzj zzdpv;
    private final Context mContext;
    private final List<Object> zzdpw = new CopyOnWriteArrayList();
    private final zze zzdpx = new zze();
    private final zza zzdpy = new zza(this);
    private volatile zzapd zzdpz;
    private UncaughtExceptionHandler zzdqa;

    class zza extends ThreadPoolExecutor {
        final /* synthetic */ zzj zzdqc;

        public zza(zzj com_google_android_gms_analytics_zzj) {
            this.zzdqc = com_google_android_gms_analytics_zzj;
            super(1, 1, 1, TimeUnit.MINUTES, new LinkedBlockingQueue());
            setThreadFactory(new zzb());
            allowCoreThreadTimeOut(true);
        }

        protected final <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
            return new zzl(this, runnable, t);
        }
    }

    static class zzb implements ThreadFactory {
        private static final AtomicInteger zzdqe = new AtomicInteger();

        private zzb() {
        }

        public final Thread newThread(Runnable runnable) {
            return new zzc(runnable, "measurement-" + zzdqe.incrementAndGet());
        }
    }

    static class zzc extends Thread {
        zzc(Runnable runnable, String str) {
            super(runnable, str);
        }

        public final void run() {
            Process.setThreadPriority(10);
            super.run();
        }
    }

    private zzj(Context context) {
        Context applicationContext = context.getApplicationContext();
        zzbq.checkNotNull(applicationContext);
        this.mContext = applicationContext;
    }

    private static void zzb(zzg com_google_android_gms_analytics_zzg) {
        zzbq.zzgn("deliver should be called from worker thread");
        zzbq.checkArgument(com_google_android_gms_analytics_zzg.zzuw(), "Measurement must be submitted");
        List<zzm> transports = com_google_android_gms_analytics_zzg.getTransports();
        if (!transports.isEmpty()) {
            Set hashSet = new HashSet();
            for (zzm com_google_android_gms_analytics_zzm : transports) {
                Uri zzup = com_google_android_gms_analytics_zzm.zzup();
                if (!hashSet.contains(zzup)) {
                    hashSet.add(zzup);
                    com_google_android_gms_analytics_zzm.zzb(com_google_android_gms_analytics_zzg);
                }
            }
        }
    }

    public static zzj zzbl(Context context) {
        zzbq.checkNotNull(context);
        if (zzdpv == null) {
            synchronized (zzj.class) {
                if (zzdpv == null) {
                    zzdpv = new zzj(context);
                }
            }
        }
        return zzdpv;
    }

    public static void zzve() {
        if (!(Thread.currentThread() instanceof zzc)) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final <V> Future<V> zza(Callable<V> callable) {
        zzbq.checkNotNull(callable);
        if (!(Thread.currentThread() instanceof zzc)) {
            return this.zzdpy.submit(callable);
        }
        Future futureTask = new FutureTask(callable);
        futureTask.run();
        return futureTask;
    }

    public final void zza(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.zzdqa = uncaughtExceptionHandler;
    }

    public final void zzc(Runnable runnable) {
        zzbq.checkNotNull(runnable);
        this.zzdpy.submit(runnable);
    }

    final void zze(zzg com_google_android_gms_analytics_zzg) {
        if (com_google_android_gms_analytics_zzg.zzuz()) {
            throw new IllegalStateException("Measurement prototype can't be submitted");
        } else if (com_google_android_gms_analytics_zzg.zzuw()) {
            throw new IllegalStateException("Measurement can only be submitted once");
        } else {
            zzg zzus = com_google_android_gms_analytics_zzg.zzus();
            zzus.zzux();
            this.zzdpy.execute(new zzk(this, zzus));
        }
    }

    public final zzapd zzvc() {
        if (this.zzdpz == null) {
            synchronized (this) {
                if (this.zzdpz == null) {
                    zzapd com_google_android_gms_internal_zzapd = new zzapd();
                    PackageManager packageManager = this.mContext.getPackageManager();
                    String packageName = this.mContext.getPackageName();
                    com_google_android_gms_internal_zzapd.setAppId(packageName);
                    com_google_android_gms_internal_zzapd.setAppInstallerId(packageManager.getInstallerPackageName(packageName));
                    String str = null;
                    try {
                        PackageInfo packageInfo = packageManager.getPackageInfo(this.mContext.getPackageName(), 0);
                        if (packageInfo != null) {
                            CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                            if (!TextUtils.isEmpty(applicationLabel)) {
                                packageName = applicationLabel.toString();
                            }
                            str = packageInfo.versionName;
                        }
                    } catch (NameNotFoundException e) {
                        String str2 = "GAv4";
                        String str3 = "Error retrieving package info: appName set to ";
                        String valueOf = String.valueOf(packageName);
                        Log.e(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                    }
                    com_google_android_gms_internal_zzapd.setAppName(packageName);
                    com_google_android_gms_internal_zzapd.setAppVersion(str);
                    this.zzdpz = com_google_android_gms_internal_zzapd;
                }
            }
        }
        return this.zzdpz;
    }

    public final zzapi zzvd() {
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        zzapi com_google_android_gms_internal_zzapi = new zzapi();
        com_google_android_gms_internal_zzapi.setLanguage(zzasl.zza(Locale.getDefault()));
        com_google_android_gms_internal_zzapi.zzchl = displayMetrics.widthPixels;
        com_google_android_gms_internal_zzapi.zzchm = displayMetrics.heightPixels;
        return com_google_android_gms_internal_zzapi;
    }
}
