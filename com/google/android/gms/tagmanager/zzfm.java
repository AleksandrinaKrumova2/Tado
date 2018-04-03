package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.zzd;
import com.google.android.gms.common.util.zzh;

final class zzfm implements zzek {
    private final zzd zzata;
    private final long zzdxz;
    private final int zzdya;
    private double zzdyb;
    private final Object zzdyd;
    private long zzkji;

    public zzfm() {
        this(60, 2000);
    }

    private zzfm(int i, long j) {
        this.zzdyd = new Object();
        this.zzdya = 60;
        this.zzdyb = (double) this.zzdya;
        this.zzdxz = 2000;
        this.zzata = zzh.zzamg();
    }

    public final boolean zzzn() {
        boolean z;
        synchronized (this.zzdyd) {
            long currentTimeMillis = this.zzata.currentTimeMillis();
            if (this.zzdyb < ((double) this.zzdya)) {
                double d = ((double) (currentTimeMillis - this.zzkji)) / ((double) this.zzdxz);
                if (d > 0.0d) {
                    this.zzdyb = Math.min((double) this.zzdya, d + this.zzdyb);
                }
            }
            this.zzkji = currentTimeMillis;
            if (this.zzdyb >= 1.0d) {
                this.zzdyb -= 1.0d;
                z = true;
            } else {
                zzdj.zzcu("No more tokens available.");
                z = false;
            }
        }
        return z;
    }
}
