package com.google.android.gms.analytics;

import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzd;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class zzi<T extends zzi> {
    private final zzj zzdps;
    protected final zzg zzdpt;
    private final List<Object> zzdpu = new ArrayList();

    protected zzi(zzj com_google_android_gms_analytics_zzj, zzd com_google_android_gms_common_util_zzd) {
        zzbq.checkNotNull(com_google_android_gms_analytics_zzj);
        this.zzdps = com_google_android_gms_analytics_zzj;
        zzg com_google_android_gms_analytics_zzg = new zzg(this, com_google_android_gms_common_util_zzd);
        com_google_android_gms_analytics_zzg.zzva();
        this.zzdpt = com_google_android_gms_analytics_zzg;
    }

    protected void zza(zzg com_google_android_gms_analytics_zzg) {
    }

    protected final void zzd(zzg com_google_android_gms_analytics_zzg) {
        Iterator it = this.zzdpu.iterator();
        while (it.hasNext()) {
            it.next();
        }
    }

    public zzg zzun() {
        zzg zzus = this.zzdpt.zzus();
        zzd(zzus);
        return zzus;
    }

    protected final zzj zzvb() {
        return this.zzdps;
    }
}
