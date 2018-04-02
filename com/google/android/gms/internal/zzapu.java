package com.google.android.gms.internal;

final class zzapu implements Runnable {
    private /* synthetic */ zzapr zzdsu;
    private /* synthetic */ String zzdsw;
    private /* synthetic */ Runnable zzdsx;

    zzapu(zzapr com_google_android_gms_internal_zzapr, String str, Runnable runnable) {
        this.zzdsu = com_google_android_gms_internal_zzapr;
        this.zzdsw = str;
        this.zzdsx = runnable;
    }

    public final void run() {
        this.zzdsu.zzdss.zzec(this.zzdsw);
        if (this.zzdsx != null) {
            this.zzdsx.run();
        }
    }
}
