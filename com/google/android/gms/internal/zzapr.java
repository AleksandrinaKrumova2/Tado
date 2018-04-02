package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.internal.zzbq;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class zzapr extends zzaqa {
    private final zzaqo zzdss;

    public zzapr(zzaqc com_google_android_gms_internal_zzaqc, zzaqe com_google_android_gms_internal_zzaqe) {
        super(com_google_android_gms_internal_zzaqc);
        zzbq.checkNotNull(com_google_android_gms_internal_zzaqe);
        this.zzdss = new zzaqo(com_google_android_gms_internal_zzaqc, com_google_android_gms_internal_zzaqe);
    }

    final void onServiceConnected() {
        zzj.zzve();
        this.zzdss.onServiceConnected();
    }

    public final void setLocalDispatchPeriod(int i) {
        zzxf();
        zzb("setLocalDispatchPeriod (sec)", Integer.valueOf(i));
        zzwv().zzc(new zzaps(this, i));
    }

    public final void start() {
        this.zzdss.start();
    }

    public final long zza(zzaqf com_google_android_gms_internal_zzaqf) {
        zzxf();
        zzbq.checkNotNull(com_google_android_gms_internal_zzaqf);
        zzj.zzve();
        long zza = this.zzdss.zza(com_google_android_gms_internal_zzaqf, true);
        if (zza == 0) {
            this.zzdss.zzb(com_google_android_gms_internal_zzaqf);
        }
        return zza;
    }

    public final void zza(zzarj com_google_android_gms_internal_zzarj) {
        zzxf();
        zzwv().zzc(new zzapx(this, com_google_android_gms_internal_zzarj));
    }

    public final void zza(zzarq com_google_android_gms_internal_zzarq) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzarq);
        zzxf();
        zzb("Hit delivery requested", com_google_android_gms_internal_zzarq);
        zzwv().zzc(new zzapv(this, com_google_android_gms_internal_zzarq));
    }

    public final void zza(String str, Runnable runnable) {
        zzbq.zzh(str, "campaign param can't be empty");
        zzwv().zzc(new zzapu(this, str, runnable));
    }

    protected final void zzvf() {
        this.zzdss.initialize();
    }

    public final void zzwm() {
        zzxf();
        zzwv().zzc(new zzapw(this));
    }

    public final void zzwn() {
        zzxf();
        Context context = getContext();
        if (zzasc.zzbk(context) && zzasd.zzbo(context)) {
            Intent intent = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            intent.setComponent(new ComponentName(context, "com.google.android.gms.analytics.AnalyticsService"));
            context.startService(intent);
            return;
        }
        zza(null);
    }

    public final boolean zzwo() {
        zzxf();
        try {
            zzwv().zza(new zzapy(this)).get(4, TimeUnit.SECONDS);
            return true;
        } catch (InterruptedException e) {
            zzd("syncDispatchLocalHits interrupted", e);
            return false;
        } catch (ExecutionException e2) {
            zze("syncDispatchLocalHits failed", e2);
            return false;
        } catch (TimeoutException e3) {
            zzd("syncDispatchLocalHits timed out", e3);
            return false;
        }
    }

    public final void zzwp() {
        zzxf();
        zzj.zzve();
        zzapz com_google_android_gms_internal_zzapz = this.zzdss;
        zzj.zzve();
        com_google_android_gms_internal_zzapz.zzxf();
        com_google_android_gms_internal_zzapz.zzdu("Service disconnected");
    }

    final void zzwq() {
        zzj.zzve();
        this.zzdss.zzwq();
    }
}
