package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbj extends zzfjm<zzbj> {
    private int level;
    private int zzwe;
    private int zzwf;

    public zzbj() {
        this.level = 1;
        this.zzwe = 0;
        this.zzwf = 0;
        this.zzpnc = null;
        this.zzpfd = -1;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.zzbj zze(com.google.android.gms.internal.zzfjj r7) throws java.io.IOException {
        /*
        r6 = this;
    L_0x0000:
        r0 = r7.zzcvt();
        switch(r0) {
            case 0: goto L_0x000d;
            case 8: goto L_0x000e;
            case 16: goto L_0x0040;
            case 24: goto L_0x0047;
            default: goto L_0x0007;
        };
    L_0x0007:
        r0 = super.zza(r7, r0);
        if (r0 != 0) goto L_0x0000;
    L_0x000d:
        return r6;
    L_0x000e:
        r1 = r7.getPosition();
        r2 = r7.zzcwi();	 Catch:{ IllegalArgumentException -> 0x0035 }
        switch(r2) {
            case 1: goto L_0x003d;
            case 2: goto L_0x003d;
            case 3: goto L_0x003d;
            default: goto L_0x0019;
        };	 Catch:{ IllegalArgumentException -> 0x0035 }
    L_0x0019:
        r3 = new java.lang.IllegalArgumentException;	 Catch:{ IllegalArgumentException -> 0x0035 }
        r4 = 42;
        r5 = new java.lang.StringBuilder;	 Catch:{ IllegalArgumentException -> 0x0035 }
        r5.<init>(r4);	 Catch:{ IllegalArgumentException -> 0x0035 }
        r2 = r5.append(r2);	 Catch:{ IllegalArgumentException -> 0x0035 }
        r4 = " is not a valid enum CacheLevel";
        r2 = r2.append(r4);	 Catch:{ IllegalArgumentException -> 0x0035 }
        r2 = r2.toString();	 Catch:{ IllegalArgumentException -> 0x0035 }
        r3.<init>(r2);	 Catch:{ IllegalArgumentException -> 0x0035 }
        throw r3;	 Catch:{ IllegalArgumentException -> 0x0035 }
    L_0x0035:
        r2 = move-exception;
        r7.zzmg(r1);
        r6.zza(r7, r0);
        goto L_0x0000;
    L_0x003d:
        r6.level = r2;	 Catch:{ IllegalArgumentException -> 0x0035 }
        goto L_0x0000;
    L_0x0040:
        r0 = r7.zzcwi();
        r6.zzwe = r0;
        goto L_0x0000;
    L_0x0047:
        r0 = r7.zzcwi();
        r6.zzwf = r0;
        goto L_0x0000;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbj.zze(com.google.android.gms.internal.zzfjj):com.google.android.gms.internal.zzbj");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbj)) {
            return false;
        }
        zzbj com_google_android_gms_internal_zzbj = (zzbj) obj;
        return this.level != com_google_android_gms_internal_zzbj.level ? false : this.zzwe != com_google_android_gms_internal_zzbj.zzwe ? false : this.zzwf != com_google_android_gms_internal_zzbj.zzwf ? false : (this.zzpnc == null || this.zzpnc.isEmpty()) ? com_google_android_gms_internal_zzbj.zzpnc == null || com_google_android_gms_internal_zzbj.zzpnc.isEmpty() : this.zzpnc.equals(com_google_android_gms_internal_zzbj.zzpnc);
    }

    public final int hashCode() {
        int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.level) * 31) + this.zzwe) * 31) + this.zzwf) * 31;
        int hashCode2 = (this.zzpnc == null || this.zzpnc.isEmpty()) ? 0 : this.zzpnc.hashCode();
        return hashCode2 + hashCode;
    }

    public final /* synthetic */ zzfjs zza(zzfjj com_google_android_gms_internal_zzfjj) throws IOException {
        return zze(com_google_android_gms_internal_zzfjj);
    }

    public final void zza(zzfjk com_google_android_gms_internal_zzfjk) throws IOException {
        if (this.level != 1) {
            com_google_android_gms_internal_zzfjk.zzaa(1, this.level);
        }
        if (this.zzwe != 0) {
            com_google_android_gms_internal_zzfjk.zzaa(2, this.zzwe);
        }
        if (this.zzwf != 0) {
            com_google_android_gms_internal_zzfjk.zzaa(3, this.zzwf);
        }
        super.zza(com_google_android_gms_internal_zzfjk);
    }

    protected final int zzq() {
        int zzq = super.zzq();
        if (this.level != 1) {
            zzq += zzfjk.zzad(1, this.level);
        }
        if (this.zzwe != 0) {
            zzq += zzfjk.zzad(2, this.zzwe);
        }
        return this.zzwf != 0 ? zzq + zzfjk.zzad(3, this.zzwf) : zzq;
    }
}
