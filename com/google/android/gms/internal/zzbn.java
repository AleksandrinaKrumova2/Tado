package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbn extends zzfjm<zzbn> {
    private static volatile zzbn[] zzwt;
    public int key;
    public int value;

    public zzbn() {
        this.key = 0;
        this.value = 0;
        this.zzpnc = null;
        this.zzpfd = -1;
    }

    public static zzbn[] zzu() {
        if (zzwt == null) {
            synchronized (zzfjq.zzpnk) {
                if (zzwt == null) {
                    zzwt = new zzbn[0];
                }
            }
        }
        return zzwt;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbn)) {
            return false;
        }
        zzbn com_google_android_gms_internal_zzbn = (zzbn) obj;
        return this.key != com_google_android_gms_internal_zzbn.key ? false : this.value != com_google_android_gms_internal_zzbn.value ? false : (this.zzpnc == null || this.zzpnc.isEmpty()) ? com_google_android_gms_internal_zzbn.zzpnc == null || com_google_android_gms_internal_zzbn.zzpnc.isEmpty() : this.zzpnc.equals(com_google_android_gms_internal_zzbn.zzpnc);
    }

    public final int hashCode() {
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.key) * 31) + this.value) * 31;
        int hashCode2 = (this.zzpnc == null || this.zzpnc.isEmpty()) ? 0 : this.zzpnc.hashCode();
        return hashCode2 + hashCode;
    }

    public final /* synthetic */ zzfjs zza(zzfjj com_google_android_gms_internal_zzfjj) throws IOException {
        while (true) {
            int zzcvt = com_google_android_gms_internal_zzfjj.zzcvt();
            switch (zzcvt) {
                case 0:
                    break;
                case 8:
                    this.key = com_google_android_gms_internal_zzfjj.zzcwi();
                    continue;
                case 16:
                    this.value = com_google_android_gms_internal_zzfjj.zzcwi();
                    continue;
                default:
                    if (!super.zza(com_google_android_gms_internal_zzfjj, zzcvt)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }

    public final void zza(zzfjk com_google_android_gms_internal_zzfjk) throws IOException {
        com_google_android_gms_internal_zzfjk.zzaa(1, this.key);
        com_google_android_gms_internal_zzfjk.zzaa(2, this.value);
        super.zza(com_google_android_gms_internal_zzfjk);
    }

    protected final int zzq() {
        return (super.zzq() + zzfjk.zzad(1, this.key)) + zzfjk.zzad(2, this.value);
    }
}
