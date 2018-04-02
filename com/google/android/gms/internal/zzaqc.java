package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.common.util.zzh;

public class zzaqc {
    private static volatile zzaqc zzdtd;
    private final Context mContext;
    private final zzd zzata = zzh.zzamg();
    private final Context zzdte;
    private final zzard zzdtf = new zzard(this);
    private final zzarv zzdtg;
    private final zzj zzdth;
    private final zzapr zzdti;
    private final zzari zzdtj;
    private final zzasm zzdtk;
    private final zzarz zzdtl;
    private final GoogleAnalytics zzdtm;
    private final zzaqu zzdtn;
    private final zzapq zzdto;
    private final zzaqn zzdtp;
    private final zzarh zzdtq;

    private zzaqc(zzaqe com_google_android_gms_internal_zzaqe) {
        Context applicationContext = com_google_android_gms_internal_zzaqe.getApplicationContext();
        zzbq.checkNotNull(applicationContext, "Application context can't be null");
        Context zzxg = com_google_android_gms_internal_zzaqe.zzxg();
        zzbq.checkNotNull(zzxg);
        this.mContext = applicationContext;
        this.zzdte = zzxg;
        zzaqa com_google_android_gms_internal_zzarv = new zzarv(this);
        com_google_android_gms_internal_zzarv.initialize();
        this.zzdtg = com_google_android_gms_internal_zzarv;
        zzapz zzwt = zzwt();
        String str = zzaqb.VERSION;
        zzwt.zzdw(new StringBuilder(String.valueOf(str).length() + 134).append("Google Analytics ").append(str).append(" is starting up. To enable debug logging on a device run:\n  adb shell setprop log.tag.GAv4 DEBUG\n  adb logcat -s GAv4").toString());
        com_google_android_gms_internal_zzarv = new zzarz(this);
        com_google_android_gms_internal_zzarv.initialize();
        this.zzdtl = com_google_android_gms_internal_zzarv;
        com_google_android_gms_internal_zzarv = new zzasm(this);
        com_google_android_gms_internal_zzarv.initialize();
        this.zzdtk = com_google_android_gms_internal_zzarv;
        com_google_android_gms_internal_zzarv = new zzapr(this, com_google_android_gms_internal_zzaqe);
        zzaqa com_google_android_gms_internal_zzaqu = new zzaqu(this);
        zzaqa com_google_android_gms_internal_zzapq = new zzapq(this);
        zzaqa com_google_android_gms_internal_zzaqn = new zzaqn(this);
        zzaqa com_google_android_gms_internal_zzarh = new zzarh(this);
        zzj zzbl = zzj.zzbl(applicationContext);
        zzbl.zza(new zzaqd(this));
        this.zzdth = zzbl;
        GoogleAnalytics googleAnalytics = new GoogleAnalytics(this);
        com_google_android_gms_internal_zzaqu.initialize();
        this.zzdtn = com_google_android_gms_internal_zzaqu;
        com_google_android_gms_internal_zzapq.initialize();
        this.zzdto = com_google_android_gms_internal_zzapq;
        com_google_android_gms_internal_zzaqn.initialize();
        this.zzdtp = com_google_android_gms_internal_zzaqn;
        com_google_android_gms_internal_zzarh.initialize();
        this.zzdtq = com_google_android_gms_internal_zzarh;
        com_google_android_gms_internal_zzaqu = new zzari(this);
        com_google_android_gms_internal_zzaqu.initialize();
        this.zzdtj = com_google_android_gms_internal_zzaqu;
        com_google_android_gms_internal_zzarv.initialize();
        this.zzdti = com_google_android_gms_internal_zzarv;
        googleAnalytics.initialize();
        this.zzdtm = googleAnalytics;
        com_google_android_gms_internal_zzarv.start();
    }

    private static void zza(zzaqa com_google_android_gms_internal_zzaqa) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzaqa, "Analytics service not created/initialized");
        zzbq.checkArgument(com_google_android_gms_internal_zzaqa.isInitialized(), "Analytics service not initialized");
    }

    public static zzaqc zzbm(Context context) {
        zzbq.checkNotNull(context);
        if (zzdtd == null) {
            synchronized (zzaqc.class) {
                if (zzdtd == null) {
                    zzd zzamg = zzh.zzamg();
                    long elapsedRealtime = zzamg.elapsedRealtime();
                    zzaqc com_google_android_gms_internal_zzaqc = new zzaqc(new zzaqe(context));
                    zzdtd = com_google_android_gms_internal_zzaqc;
                    GoogleAnalytics.zzur();
                    elapsedRealtime = zzamg.elapsedRealtime() - elapsedRealtime;
                    long longValue = ((Long) zzarl.zzdxm.get()).longValue();
                    if (elapsedRealtime > longValue) {
                        com_google_android_gms_internal_zzaqc.zzwt().zzc("Slow initialization (ms)", Long.valueOf(elapsedRealtime), Long.valueOf(longValue));
                    }
                }
            }
        }
        return zzdtd;
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final zzd zzws() {
        return this.zzata;
    }

    public final zzarv zzwt() {
        zza(this.zzdtg);
        return this.zzdtg;
    }

    public final zzard zzwu() {
        return this.zzdtf;
    }

    public final zzj zzwv() {
        zzbq.checkNotNull(this.zzdth);
        return this.zzdth;
    }

    public final zzapr zzwx() {
        zza(this.zzdti);
        return this.zzdti;
    }

    public final zzari zzwy() {
        zza(this.zzdtj);
        return this.zzdtj;
    }

    public final zzasm zzwz() {
        zza(this.zzdtk);
        return this.zzdtk;
    }

    public final zzarz zzxa() {
        zza(this.zzdtl);
        return this.zzdtl;
    }

    public final zzaqn zzxd() {
        zza(this.zzdtp);
        return this.zzdtp;
    }

    public final zzarh zzxe() {
        return this.zzdtq;
    }

    public final Context zzxg() {
        return this.zzdte;
    }

    public final zzarv zzxh() {
        return this.zzdtg;
    }

    public final GoogleAnalytics zzxi() {
        zzbq.checkNotNull(this.zzdtm);
        zzbq.checkArgument(this.zzdtm.isInitialized(), "Analytics instance not initialized");
        return this.zzdtm;
    }

    public final zzarz zzxj() {
        return (this.zzdtl == null || !this.zzdtl.isInitialized()) ? null : this.zzdtl;
    }

    public final zzapq zzxk() {
        zza(this.zzdto);
        return this.zzdto;
    }

    public final zzaqu zzxl() {
        zza(this.zzdtn);
        return this.zzdtn;
    }
}
