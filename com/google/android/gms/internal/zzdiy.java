package com.google.android.gms.internal;

import java.io.IOException;

public final class zzdiy extends zzfjm<zzdiy> {
    public long zzktn;
    public zzbr zzkto;
    public zzbo zzyi;

    public zzdiy() {
        this.zzktn = 0;
        this.zzyi = null;
        this.zzkto = null;
        this.zzpnc = null;
        this.zzpfd = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdiy)) {
            return false;
        }
        zzdiy com_google_android_gms_internal_zzdiy = (zzdiy) obj;
        if (this.zzktn != com_google_android_gms_internal_zzdiy.zzktn) {
            return false;
        }
        if (this.zzyi == null) {
            if (com_google_android_gms_internal_zzdiy.zzyi != null) {
                return false;
            }
        } else if (!this.zzyi.equals(com_google_android_gms_internal_zzdiy.zzyi)) {
            return false;
        }
        if (this.zzkto == null) {
            if (com_google_android_gms_internal_zzdiy.zzkto != null) {
                return false;
            }
        } else if (!this.zzkto.equals(com_google_android_gms_internal_zzdiy.zzkto)) {
            return false;
        }
        return (this.zzpnc == null || this.zzpnc.isEmpty()) ? com_google_android_gms_internal_zzdiy.zzpnc == null || com_google_android_gms_internal_zzdiy.zzpnc.isEmpty() : this.zzpnc.equals(com_google_android_gms_internal_zzdiy.zzpnc);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((getClass().getName().hashCode() + 527) * 31) + ((int) (this.zzktn ^ (this.zzktn >>> 32)));
        zzbo com_google_android_gms_internal_zzbo = this.zzyi;
        hashCode = (com_google_android_gms_internal_zzbo == null ? 0 : com_google_android_gms_internal_zzbo.hashCode()) + (hashCode * 31);
        zzbr com_google_android_gms_internal_zzbr = this.zzkto;
        hashCode = ((com_google_android_gms_internal_zzbr == null ? 0 : com_google_android_gms_internal_zzbr.hashCode()) + (hashCode * 31)) * 31;
        if (!(this.zzpnc == null || this.zzpnc.isEmpty())) {
            i = this.zzpnc.hashCode();
        }
        return hashCode + i;
    }

    public final /* synthetic */ zzfjs zza(zzfjj com_google_android_gms_internal_zzfjj) throws IOException {
        while (true) {
            int zzcvt = com_google_android_gms_internal_zzfjj.zzcvt();
            switch (zzcvt) {
                case 0:
                    break;
                case 8:
                    this.zzktn = com_google_android_gms_internal_zzfjj.zzcwn();
                    continue;
                case 18:
                    if (this.zzyi == null) {
                        this.zzyi = new zzbo();
                    }
                    com_google_android_gms_internal_zzfjj.zza(this.zzyi);
                    continue;
                case 26:
                    if (this.zzkto == null) {
                        this.zzkto = new zzbr();
                    }
                    com_google_android_gms_internal_zzfjj.zza(this.zzkto);
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
        com_google_android_gms_internal_zzfjk.zzf(1, this.zzktn);
        if (this.zzyi != null) {
            com_google_android_gms_internal_zzfjk.zza(2, this.zzyi);
        }
        if (this.zzkto != null) {
            com_google_android_gms_internal_zzfjk.zza(3, this.zzkto);
        }
        super.zza(com_google_android_gms_internal_zzfjk);
    }

    protected final int zzq() {
        int zzq = super.zzq() + zzfjk.zzc(1, this.zzktn);
        if (this.zzyi != null) {
            zzq += zzfjk.zzb(2, this.zzyi);
        }
        return this.zzkto != null ? zzq + zzfjk.zzb(3, this.zzkto) : zzq;
    }
}
