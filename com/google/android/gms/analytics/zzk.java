package com.google.android.gms.analytics;

import java.util.Iterator;

final class zzk implements Runnable {
    private /* synthetic */ zzg zzdqb;
    private /* synthetic */ zzj zzdqc;

    zzk(zzj com_google_android_gms_analytics_zzj, zzg com_google_android_gms_analytics_zzg) {
        this.zzdqc = com_google_android_gms_analytics_zzj;
        this.zzdqb = com_google_android_gms_analytics_zzg;
    }

    public final void run() {
        this.zzdqb.zzuy().zza(this.zzdqb);
        Iterator it = this.zzdqc.zzdpw.iterator();
        while (it.hasNext()) {
            it.next();
        }
        zzj.zzb(this.zzdqb);
    }
}
