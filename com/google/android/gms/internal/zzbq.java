package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbq extends zzfjm<zzbq> {
    private static volatile zzbq[] zzye;
    public String name;
    private zzbs zzyf;
    public zzbm zzyg;

    public zzbq() {
        this.name = "";
        this.zzyf = null;
        this.zzyg = null;
        this.zzpnc = null;
        this.zzpfd = -1;
    }

    public static zzbq[] zzw() {
        if (zzye == null) {
            synchronized (zzfjq.zzpnk) {
                if (zzye == null) {
                    zzye = new zzbq[0];
                }
            }
        }
        return zzye;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbq)) {
            return false;
        }
        zzbq com_google_android_gms_internal_zzbq = (zzbq) obj;
        if (this.name == null) {
            if (com_google_android_gms_internal_zzbq.name != null) {
                return false;
            }
        } else if (!this.name.equals(com_google_android_gms_internal_zzbq.name)) {
            return false;
        }
        if (this.zzyf == null) {
            if (com_google_android_gms_internal_zzbq.zzyf != null) {
                return false;
            }
        } else if (!this.zzyf.equals(com_google_android_gms_internal_zzbq.zzyf)) {
            return false;
        }
        if (this.zzyg == null) {
            if (com_google_android_gms_internal_zzbq.zzyg != null) {
                return false;
            }
        } else if (!this.zzyg.equals(com_google_android_gms_internal_zzbq.zzyg)) {
            return false;
        }
        return (this.zzpnc == null || this.zzpnc.isEmpty()) ? com_google_android_gms_internal_zzbq.zzpnc == null || com_google_android_gms_internal_zzbq.zzpnc.isEmpty() : this.zzpnc.equals(com_google_android_gms_internal_zzbq.zzpnc);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (this.name == null ? 0 : this.name.hashCode()) + ((getClass().getName().hashCode() + 527) * 31);
        zzbs com_google_android_gms_internal_zzbs = this.zzyf;
        hashCode = (com_google_android_gms_internal_zzbs == null ? 0 : com_google_android_gms_internal_zzbs.hashCode()) + (hashCode * 31);
        zzbm com_google_android_gms_internal_zzbm = this.zzyg;
        hashCode = ((com_google_android_gms_internal_zzbm == null ? 0 : com_google_android_gms_internal_zzbm.hashCode()) + (hashCode * 31)) * 31;
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
                    this.name = com_google_android_gms_internal_zzfjj.readString();
                    continue;
                case 18:
                    if (this.zzyf == null) {
                        this.zzyf = new zzbs();
                    }
                    com_google_android_gms_internal_zzfjj.zza(this.zzyf);
                    continue;
                case 26:
                    if (this.zzyg == null) {
                        this.zzyg = new zzbm();
                    }
                    com_google_android_gms_internal_zzfjj.zza(this.zzyg);
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
        if (!(this.name == null || this.name.equals(""))) {
            com_google_android_gms_internal_zzfjk.zzn(1, this.name);
        }
        if (this.zzyf != null) {
            com_google_android_gms_internal_zzfjk.zza(2, this.zzyf);
        }
        if (this.zzyg != null) {
            com_google_android_gms_internal_zzfjk.zza(3, this.zzyg);
        }
        super.zza(com_google_android_gms_internal_zzfjk);
    }

    protected final int zzq() {
        int zzq = super.zzq();
        if (!(this.name == null || this.name.equals(""))) {
            zzq += zzfjk.zzo(1, this.name);
        }
        if (this.zzyf != null) {
            zzq += zzfjk.zzb(2, this.zzyf);
        }
        return this.zzyg != null ? zzq + zzfjk.zzb(3, this.zzyg) : zzq;
    }
}
