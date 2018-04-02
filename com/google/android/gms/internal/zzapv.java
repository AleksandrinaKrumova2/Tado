package com.google.android.gms.internal;

final class zzapv implements Runnable {
    private /* synthetic */ zzapr zzdsu;
    private /* synthetic */ zzarq zzdsy;

    zzapv(zzapr com_google_android_gms_internal_zzapr, zzarq com_google_android_gms_internal_zzarq) {
        this.zzdsu = com_google_android_gms_internal_zzapr;
        this.zzdsy = com_google_android_gms_internal_zzarq;
    }

    public final void run() {
        this.zzdsu.zzdss.zza(this.zzdsy);
    }
}
