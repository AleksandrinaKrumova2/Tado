package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbr extends zzfjm<zzbr> {
    public zzbq[] zzyh;
    public zzbo zzyi;
    public String zzyj;

    public zzbr() {
        this.zzyh = zzbq.zzw();
        this.zzyi = null;
        this.zzyj = "";
        this.zzpnc = null;
        this.zzpfd = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbr)) {
            return false;
        }
        zzbr com_google_android_gms_internal_zzbr = (zzbr) obj;
        if (!zzfjq.equals(this.zzyh, com_google_android_gms_internal_zzbr.zzyh)) {
            return false;
        }
        if (this.zzyi == null) {
            if (com_google_android_gms_internal_zzbr.zzyi != null) {
                return false;
            }
        } else if (!this.zzyi.equals(com_google_android_gms_internal_zzbr.zzyi)) {
            return false;
        }
        if (this.zzyj == null) {
            if (com_google_android_gms_internal_zzbr.zzyj != null) {
                return false;
            }
        } else if (!this.zzyj.equals(com_google_android_gms_internal_zzbr.zzyj)) {
            return false;
        }
        return (this.zzpnc == null || this.zzpnc.isEmpty()) ? com_google_android_gms_internal_zzbr.zzpnc == null || com_google_android_gms_internal_zzbr.zzpnc.isEmpty() : this.zzpnc.equals(com_google_android_gms_internal_zzbr.zzpnc);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((getClass().getName().hashCode() + 527) * 31) + zzfjq.hashCode(this.zzyh);
        zzbo com_google_android_gms_internal_zzbo = this.zzyi;
        hashCode = ((this.zzyj == null ? 0 : this.zzyj.hashCode()) + (((com_google_android_gms_internal_zzbo == null ? 0 : com_google_android_gms_internal_zzbo.hashCode()) + (hashCode * 31)) * 31)) * 31;
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
                    int zzb = zzfjv.zzb(com_google_android_gms_internal_zzfjj, 10);
                    zzcvt = this.zzyh == null ? 0 : this.zzyh.length;
                    Object obj = new zzbq[(zzb + zzcvt)];
                    if (zzcvt != 0) {
                        System.arraycopy(this.zzyh, 0, obj, 0, zzcvt);
                    }
                    while (zzcvt < obj.length - 1) {
                        obj[zzcvt] = new zzbq();
                        com_google_android_gms_internal_zzfjj.zza(obj[zzcvt]);
                        com_google_android_gms_internal_zzfjj.zzcvt();
                        zzcvt++;
                    }
                    obj[zzcvt] = new zzbq();
                    com_google_android_gms_internal_zzfjj.zza(obj[zzcvt]);
                    this.zzyh = obj;
                    continue;
                case 18:
                    if (this.zzyi == null) {
                        this.zzyi = new zzbo();
                    }
                    com_google_android_gms_internal_zzfjj.zza(this.zzyi);
                    continue;
                case 26:
                    this.zzyj = com_google_android_gms_internal_zzfjj.readString();
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
        if (this.zzyh != null && this.zzyh.length > 0) {
            for (zzfjs com_google_android_gms_internal_zzfjs : this.zzyh) {
                if (com_google_android_gms_internal_zzfjs != null) {
                    com_google_android_gms_internal_zzfjk.zza(1, com_google_android_gms_internal_zzfjs);
                }
            }
        }
        if (this.zzyi != null) {
            com_google_android_gms_internal_zzfjk.zza(2, this.zzyi);
        }
        if (!(this.zzyj == null || this.zzyj.equals(""))) {
            com_google_android_gms_internal_zzfjk.zzn(3, this.zzyj);
        }
        super.zza(com_google_android_gms_internal_zzfjk);
    }

    protected final int zzq() {
        int zzq = super.zzq();
        if (this.zzyh != null && this.zzyh.length > 0) {
            for (zzfjs com_google_android_gms_internal_zzfjs : this.zzyh) {
                if (com_google_android_gms_internal_zzfjs != null) {
                    zzq += zzfjk.zzb(1, com_google_android_gms_internal_zzfjs);
                }
            }
        }
        if (this.zzyi != null) {
            zzq += zzfjk.zzb(2, this.zzyi);
        }
        return (this.zzyj == null || this.zzyj.equals("")) ? zzq : zzq + zzfjk.zzo(3, this.zzyj);
    }
}
