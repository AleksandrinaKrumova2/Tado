package com.google.android.gms.internal;

import android.os.Build.VERSION;

final class zzasf implements Runnable {
    private /* synthetic */ zzase zzdzb;

    zzasf(zzase com_google_android_gms_internal_zzase) {
        this.zzdzb = com_google_android_gms_internal_zzase;
    }

    public final void run() {
        if (this.zzdzb.zzdyw != null) {
            if (((zzasg) this.zzdzb.zzdza.zzdyu).callServiceStopSelfResult(this.zzdzb.zzdyw.intValue())) {
                this.zzdzb.zzdyy.zzdu("Local AnalyticsService processed last dispatch request");
            }
        } else if (VERSION.SDK_INT >= 24) {
            this.zzdzb.zzdyy.zzdu("AnalyticsJobService processed last dispatch request");
            ((zzasg) this.zzdzb.zzdza.zzdyu).zza(this.zzdzb.zzdyz, false);
        }
    }
}
