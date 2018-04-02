package com.google.android.gms.internal;

import java.lang.Thread.UncaughtExceptionHandler;

final class zzaqd implements UncaughtExceptionHandler {
    private /* synthetic */ zzaqc zzdtr;

    zzaqd(zzaqc com_google_android_gms_internal_zzaqc) {
        this.zzdtr = com_google_android_gms_internal_zzaqc;
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        zzapz zzxh = this.zzdtr.zzxh();
        if (zzxh != null) {
            zzxh.zze("Job execution failed", th);
        }
    }
}
