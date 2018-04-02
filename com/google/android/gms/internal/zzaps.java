package com.google.android.gms.internal;

final class zzaps implements Runnable {
    private /* synthetic */ int zzdst;
    private /* synthetic */ zzapr zzdsu;

    zzaps(zzapr com_google_android_gms_internal_zzapr, int i) {
        this.zzdsu = com_google_android_gms_internal_zzapr;
        this.zzdst = i;
    }

    public final void run() {
        this.zzdsu.zzdss.zzr(((long) this.zzdst) * 1000);
    }
}
