package com.google.android.gms.internal;

import android.os.Looper;

final class zzarg implements Runnable {
    private /* synthetic */ zzarf zzdvr;

    zzarg(zzarf com_google_android_gms_internal_zzarf) {
        this.zzdvr = com_google_android_gms_internal_zzarf;
    }

    public final void run() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.zzdvr.zzdta.zzwv().zzc(this);
            return;
        }
        boolean zzdx = this.zzdvr.zzdx();
        this.zzdvr.zzdvq = 0;
        if (zzdx) {
            this.zzdvr.run();
        }
    }
}
