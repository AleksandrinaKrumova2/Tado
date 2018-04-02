package com.google.android.gms.analytics;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzd;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class zzg {
    private final zzd zzata;
    private final zzi zzdpi;
    private boolean zzdpj;
    private long zzdpk;
    private long zzdpl;
    private long zzdpm;
    private long zzdpn;
    private long zzdpo;
    private boolean zzdpp;
    private final Map<Class<? extends zzh>, zzh> zzdpq;
    private final List<zzm> zzdpr;

    private zzg(zzg com_google_android_gms_analytics_zzg) {
        this.zzdpi = com_google_android_gms_analytics_zzg.zzdpi;
        this.zzata = com_google_android_gms_analytics_zzg.zzata;
        this.zzdpk = com_google_android_gms_analytics_zzg.zzdpk;
        this.zzdpl = com_google_android_gms_analytics_zzg.zzdpl;
        this.zzdpm = com_google_android_gms_analytics_zzg.zzdpm;
        this.zzdpn = com_google_android_gms_analytics_zzg.zzdpn;
        this.zzdpo = com_google_android_gms_analytics_zzg.zzdpo;
        this.zzdpr = new ArrayList(com_google_android_gms_analytics_zzg.zzdpr);
        this.zzdpq = new HashMap(com_google_android_gms_analytics_zzg.zzdpq.size());
        for (Entry entry : com_google_android_gms_analytics_zzg.zzdpq.entrySet()) {
            zzh zzc = zzc((Class) entry.getKey());
            ((zzh) entry.getValue()).zzb(zzc);
            this.zzdpq.put((Class) entry.getKey(), zzc);
        }
    }

    zzg(zzi com_google_android_gms_analytics_zzi, zzd com_google_android_gms_common_util_zzd) {
        zzbq.checkNotNull(com_google_android_gms_analytics_zzi);
        zzbq.checkNotNull(com_google_android_gms_common_util_zzd);
        this.zzdpi = com_google_android_gms_analytics_zzi;
        this.zzata = com_google_android_gms_common_util_zzd;
        this.zzdpn = 1800000;
        this.zzdpo = 3024000000L;
        this.zzdpq = new HashMap();
        this.zzdpr = new ArrayList();
    }

    @TargetApi(19)
    private static <T extends zzh> T zzc(Class<T> cls) {
        try {
            return (zzh) cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Throwable e) {
            if (e instanceof InstantiationException) {
                throw new IllegalArgumentException("dataType doesn't have default constructor", e);
            } else if (e instanceof IllegalAccessException) {
                throw new IllegalArgumentException("dataType default constructor is not accessible", e);
            } else if (VERSION.SDK_INT < 19 || !(e instanceof ReflectiveOperationException)) {
                throw new RuntimeException(e);
            } else {
                throw new IllegalArgumentException("Linkage exception", e);
            }
        }
    }

    public final List<zzm> getTransports() {
        return this.zzdpr;
    }

    public final <T extends zzh> T zza(Class<T> cls) {
        return (zzh) this.zzdpq.get(cls);
    }

    public final void zza(zzh com_google_android_gms_analytics_zzh) {
        zzbq.checkNotNull(com_google_android_gms_analytics_zzh);
        Class cls = com_google_android_gms_analytics_zzh.getClass();
        if (cls.getSuperclass() != zzh.class) {
            throw new IllegalArgumentException();
        }
        com_google_android_gms_analytics_zzh.zzb(zzb(cls));
    }

    public final <T extends zzh> T zzb(Class<T> cls) {
        zzh com_google_android_gms_analytics_zzh = (zzh) this.zzdpq.get(cls);
        if (com_google_android_gms_analytics_zzh != null) {
            return com_google_android_gms_analytics_zzh;
        }
        T zzc = zzc(cls);
        this.zzdpq.put(cls, zzc);
        return zzc;
    }

    public final void zzl(long j) {
        this.zzdpl = j;
    }

    public final zzg zzus() {
        return new zzg(this);
    }

    public final Collection<zzh> zzut() {
        return this.zzdpq.values();
    }

    public final long zzuu() {
        return this.zzdpk;
    }

    public final void zzuv() {
        this.zzdpi.zzvb().zze(this);
    }

    public final boolean zzuw() {
        return this.zzdpj;
    }

    final void zzux() {
        this.zzdpm = this.zzata.elapsedRealtime();
        if (this.zzdpl != 0) {
            this.zzdpk = this.zzdpl;
        } else {
            this.zzdpk = this.zzata.currentTimeMillis();
        }
        this.zzdpj = true;
    }

    final zzi zzuy() {
        return this.zzdpi;
    }

    final boolean zzuz() {
        return this.zzdpp;
    }

    final void zzva() {
        this.zzdpp = true;
    }
}
