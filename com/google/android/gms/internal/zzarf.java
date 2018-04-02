package com.google.android.gms.internal;

import android.os.Handler;
import com.google.android.gms.common.internal.zzbq;

abstract class zzarf {
    private static volatile Handler zzdvp;
    private final zzaqc zzdta;
    private volatile long zzdvq;
    private final Runnable zzz = new zzarg(this);

    zzarf(zzaqc com_google_android_gms_internal_zzaqc) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzaqc);
        this.zzdta = com_google_android_gms_internal_zzaqc;
    }

    private final Handler getHandler() {
        if (zzdvp != null) {
            return zzdvp;
        }
        Handler handler;
        synchronized (zzarf.class) {
            if (zzdvp == null) {
                zzdvp = new Handler(this.zzdta.getContext().getMainLooper());
            }
            handler = zzdvp;
        }
        return handler;
    }

    public final void cancel() {
        this.zzdvq = 0;
        getHandler().removeCallbacks(this.zzz);
    }

    public abstract void run();

    public final boolean zzdx() {
        return this.zzdvq != 0;
    }

    public final void zzs(long j) {
        cancel();
        if (j >= 0) {
            this.zzdvq = this.zzdta.zzws().currentTimeMillis();
            if (!getHandler().postDelayed(this.zzz, j)) {
                this.zzdta.zzwt().zze("Failed to schedule delayed post. time", Long.valueOf(j));
            }
        }
    }

    public final void zzt(long j) {
        long j2 = 0;
        if (!zzdx()) {
            return;
        }
        if (j < 0) {
            cancel();
            return;
        }
        long abs = j - Math.abs(this.zzdta.zzws().currentTimeMillis() - this.zzdvq);
        if (abs >= 0) {
            j2 = abs;
        }
        getHandler().removeCallbacks(this.zzz);
        if (!getHandler().postDelayed(this.zzz, j2)) {
            this.zzdta.zzwt().zze("Failed to adjust delayed post. time", Long.valueOf(j2));
        }
    }

    public final long zzzb() {
        return this.zzdvq == 0 ? 0 : Math.abs(this.zzdta.zzws().currentTimeMillis() - this.zzdvq);
    }
}
