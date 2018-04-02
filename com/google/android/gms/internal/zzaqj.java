package com.google.android.gms.internal;

final class zzaqj implements Runnable {
    private /* synthetic */ zzarr zzdue;
    private /* synthetic */ zzaqi zzduf;

    zzaqj(zzaqi com_google_android_gms_internal_zzaqi, zzarr com_google_android_gms_internal_zzarr) {
        this.zzduf = com_google_android_gms_internal_zzaqi;
        this.zzdue = com_google_android_gms_internal_zzarr;
    }

    public final void run() {
        if (!this.zzduf.zzdub.isConnected()) {
            this.zzduf.zzdub.zzdv("Connected to service after a timeout");
            this.zzduf.zzdub.zza(this.zzdue);
        }
    }
}
