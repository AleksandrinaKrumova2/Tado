package com.google.android.gms.internal;

import com.google.android.gms.common.util.zzd;

public final class zzart {
    private final zzd zzata;
    private final String zzdrp;
    private final long zzdxz;
    private final int zzdya;
    private double zzdyb;
    private long zzdyc;
    private final Object zzdyd;

    private zzart(int i, long j, String str, zzd com_google_android_gms_common_util_zzd) {
        this.zzdyd = new Object();
        this.zzdya = 60;
        this.zzdyb = (double) this.zzdya;
        this.zzdxz = 2000;
        this.zzdrp = str;
        this.zzata = com_google_android_gms_common_util_zzd;
    }

    public zzart(String str, zzd com_google_android_gms_common_util_zzd) {
        this(60, 2000, str, com_google_android_gms_common_util_zzd);
    }

    public final boolean zzzn() {
        boolean z;
        synchronized (this.zzdyd) {
            long currentTimeMillis = this.zzata.currentTimeMillis();
            if (this.zzdyb < ((double) this.zzdya)) {
                double d = ((double) (currentTimeMillis - this.zzdyc)) / ((double) this.zzdxz);
                if (d > 0.0d) {
                    this.zzdyb = Math.min((double) this.zzdya, d + this.zzdyb);
                }
            }
            this.zzdyc = currentTimeMillis;
            if (this.zzdyb >= 1.0d) {
                this.zzdyb -= 1.0d;
                z = true;
            } else {
                String str = this.zzdrp;
                zzaru.zzcu(new StringBuilder(String.valueOf(str).length() + 34).append("Excessive ").append(str).append(" detected; call ignored.").toString());
                z = false;
            }
        }
        return z;
    }
}
