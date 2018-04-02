package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzd;

final class zzash {
    private long mStartTime;
    private final zzd zzata;

    public zzash(zzd com_google_android_gms_common_util_zzd) {
        zzbq.checkNotNull(com_google_android_gms_common_util_zzd);
        this.zzata = com_google_android_gms_common_util_zzd;
    }

    public zzash(zzd com_google_android_gms_common_util_zzd, long j) {
        zzbq.checkNotNull(com_google_android_gms_common_util_zzd);
        this.zzata = com_google_android_gms_common_util_zzd;
        this.mStartTime = j;
    }

    public final void clear() {
        this.mStartTime = 0;
    }

    public final void start() {
        this.mStartTime = this.zzata.elapsedRealtime();
    }

    public final boolean zzu(long j) {
        return this.mStartTime == 0 || this.zzata.elapsedRealtime() - this.mStartTime > j;
    }
}
