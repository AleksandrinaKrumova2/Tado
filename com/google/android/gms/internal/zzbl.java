package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbl extends zzfjm<zzbl> {
    private static volatile zzbl[] zzwl;
    public String key;
    public long zzwm;
    public long zzwn;
    public boolean zzwo;
    public long zzwp;

    public zzbl() {
        this.key = "";
        this.zzwm = 0;
        this.zzwn = 2147483647L;
        this.zzwo = false;
        this.zzwp = 0;
        this.zzpnc = null;
        this.zzpfd = -1;
    }

    public static zzbl[] zzt() {
        if (zzwl == null) {
            synchronized (zzfjq.zzpnk) {
                if (zzwl == null) {
                    zzwl = new zzbl[0];
                }
            }
        }
        return zzwl;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbl)) {
            return false;
        }
        zzbl com_google_android_gms_internal_zzbl = (zzbl) obj;
        if (this.key == null) {
            if (com_google_android_gms_internal_zzbl.key != null) {
                return false;
            }
        } else if (!this.key.equals(com_google_android_gms_internal_zzbl.key)) {
            return false;
        }
        return this.zzwm != com_google_android_gms_internal_zzbl.zzwm ? false : this.zzwn != com_google_android_gms_internal_zzbl.zzwn ? false : this.zzwo != com_google_android_gms_internal_zzbl.zzwo ? false : this.zzwp != com_google_android_gms_internal_zzbl.zzwp ? false : (this.zzpnc == null || this.zzpnc.isEmpty()) ? com_google_android_gms_internal_zzbl.zzpnc == null || com_google_android_gms_internal_zzbl.zzpnc.isEmpty() : this.zzpnc.equals(com_google_android_gms_internal_zzbl.zzpnc);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((((this.zzwo ? 1231 : 1237) + (((((((this.key == null ? 0 : this.key.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31) + ((int) (this.zzwm ^ (this.zzwm >>> 32)))) * 31) + ((int) (this.zzwn ^ (this.zzwn >>> 32)))) * 31)) * 31) + ((int) (this.zzwp ^ (this.zzwp >>> 32)))) * 31;
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
                case 10:
                    this.key = com_google_android_gms_internal_zzfjj.readString();
                    continue;
                case 16:
                    this.zzwm = com_google_android_gms_internal_zzfjj.zzcwn();
                    continue;
                case 24:
                    this.zzwn = com_google_android_gms_internal_zzfjj.zzcwn();
                    continue;
                case 32:
                    this.zzwo = com_google_android_gms_internal_zzfjj.zzcvz();
                    continue;
                case 40:
                    this.zzwp = com_google_android_gms_internal_zzfjj.zzcwn();
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
        if (!(this.key == null || this.key.equals(""))) {
            com_google_android_gms_internal_zzfjk.zzn(1, this.key);
        }
        if (this.zzwm != 0) {
            com_google_android_gms_internal_zzfjk.zzf(2, this.zzwm);
        }
        if (this.zzwn != 2147483647L) {
            com_google_android_gms_internal_zzfjk.zzf(3, this.zzwn);
        }
        if (this.zzwo) {
            com_google_android_gms_internal_zzfjk.zzl(4, this.zzwo);
        }
        if (this.zzwp != 0) {
            com_google_android_gms_internal_zzfjk.zzf(5, this.zzwp);
        }
        super.zza(com_google_android_gms_internal_zzfjk);
    }

    protected final int zzq() {
        int zzq = super.zzq();
        if (!(this.key == null || this.key.equals(""))) {
            zzq += zzfjk.zzo(1, this.key);
        }
        if (this.zzwm != 0) {
            zzq += zzfjk.zzc(2, this.zzwm);
        }
        if (this.zzwn != 2147483647L) {
            zzq += zzfjk.zzc(3, this.zzwn);
        }
        if (this.zzwo) {
            zzq += zzfjk.zzlg(4) + 1;
        }
        return this.zzwp != 0 ? zzq + zzfjk.zzc(5, this.zzwp) : zzq;
    }
}
