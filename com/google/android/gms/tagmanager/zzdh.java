package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.zzd;
import com.tado.android.utils.Constants;

final class zzdh implements zzek {
    private final zzd zzata;
    private final String zzdrp;
    private final long zzdxz = 900000;
    private final int zzdya = 5;
    private double zzdyb = ((double) Math.min(1, 5));
    private long zzdyc;
    private final Object zzdyd = new Object();
    private final long zzkgv = Constants.CHECK_FOR_SERVER_CONNECTION_TIMEOUT;

    public zzdh(int i, int i2, long j, long j2, String str, zzd com_google_android_gms_common_util_zzd) {
        this.zzdrp = str;
        this.zzata = com_google_android_gms_common_util_zzd;
    }

    public final boolean zzzn() {
        boolean z = false;
        synchronized (this.zzdyd) {
            long currentTimeMillis = this.zzata.currentTimeMillis();
            String str;
            if (currentTimeMillis - this.zzdyc < this.zzkgv) {
                str = this.zzdrp;
                zzdj.zzcu(new StringBuilder(String.valueOf(str).length() + 34).append("Excessive ").append(str).append(" detected; call ignored.").toString());
            } else {
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
                    str = this.zzdrp;
                    zzdj.zzcu(new StringBuilder(String.valueOf(str).length() + 34).append("Excessive ").append(str).append(" detected; call ignored.").toString());
                }
            }
        }
        return z;
    }
}
