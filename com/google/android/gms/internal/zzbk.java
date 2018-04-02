package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbk extends zzfjm<zzbk> {
    private static volatile zzbk[] zzwg;
    private int name;
    public int[] zzwh;
    private int zzwi;
    private boolean zzwj;
    private boolean zzwk;

    public zzbk() {
        this.zzwh = zzfjv.zzpnp;
        this.zzwi = 0;
        this.name = 0;
        this.zzwj = false;
        this.zzwk = false;
        this.zzpnc = null;
        this.zzpfd = -1;
    }

    public static zzbk[] zzs() {
        if (zzwg == null) {
            synchronized (zzfjq.zzpnk) {
                if (zzwg == null) {
                    zzwg = new zzbk[0];
                }
            }
        }
        return zzwg;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbk)) {
            return false;
        }
        zzbk com_google_android_gms_internal_zzbk = (zzbk) obj;
        return !zzfjq.equals(this.zzwh, com_google_android_gms_internal_zzbk.zzwh) ? false : this.zzwi != com_google_android_gms_internal_zzbk.zzwi ? false : this.name != com_google_android_gms_internal_zzbk.name ? false : this.zzwj != com_google_android_gms_internal_zzbk.zzwj ? false : this.zzwk != com_google_android_gms_internal_zzbk.zzwk ? false : (this.zzpnc == null || this.zzpnc.isEmpty()) ? com_google_android_gms_internal_zzbk.zzpnc == null || com_google_android_gms_internal_zzbk.zzpnc.isEmpty() : this.zzpnc.equals(com_google_android_gms_internal_zzbk.zzpnc);
    }

    public final int hashCode() {
        int i = 1231;
        int hashCode = ((this.zzwj ? 1231 : 1237) + ((((((((getClass().getName().hashCode() + 527) * 31) + zzfjq.hashCode(this.zzwh)) * 31) + this.zzwi) * 31) + this.name) * 31)) * 31;
        if (!this.zzwk) {
            i = 1237;
        }
        i = (hashCode + i) * 31;
        hashCode = (this.zzpnc == null || this.zzpnc.isEmpty()) ? 0 : this.zzpnc.hashCode();
        return hashCode + i;
    }

    public final /* synthetic */ zzfjs zza(zzfjj com_google_android_gms_internal_zzfjj) throws IOException {
        while (true) {
            int zzcvt = com_google_android_gms_internal_zzfjj.zzcvt();
            int zzb;
            switch (zzcvt) {
                case 0:
                    break;
                case 8:
                    this.zzwk = com_google_android_gms_internal_zzfjj.zzcvz();
                    continue;
                case 16:
                    this.zzwi = com_google_android_gms_internal_zzfjj.zzcwi();
                    continue;
                case 24:
                    zzb = zzfjv.zzb(com_google_android_gms_internal_zzfjj, 24);
                    zzcvt = this.zzwh == null ? 0 : this.zzwh.length;
                    Object obj = new int[(zzb + zzcvt)];
                    if (zzcvt != 0) {
                        System.arraycopy(this.zzwh, 0, obj, 0, zzcvt);
                    }
                    while (zzcvt < obj.length - 1) {
                        obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                        com_google_android_gms_internal_zzfjj.zzcvt();
                        zzcvt++;
                    }
                    obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                    this.zzwh = obj;
                    continue;
                case 26:
                    int zzks = com_google_android_gms_internal_zzfjj.zzks(com_google_android_gms_internal_zzfjj.zzcwi());
                    zzb = com_google_android_gms_internal_zzfjj.getPosition();
                    zzcvt = 0;
                    while (com_google_android_gms_internal_zzfjj.zzcwk() > 0) {
                        com_google_android_gms_internal_zzfjj.zzcwi();
                        zzcvt++;
                    }
                    com_google_android_gms_internal_zzfjj.zzmg(zzb);
                    zzb = this.zzwh == null ? 0 : this.zzwh.length;
                    Object obj2 = new int[(zzcvt + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzwh, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = com_google_android_gms_internal_zzfjj.zzcwi();
                        zzb++;
                    }
                    this.zzwh = obj2;
                    com_google_android_gms_internal_zzfjj.zzkt(zzks);
                    continue;
                case 32:
                    this.name = com_google_android_gms_internal_zzfjj.zzcwi();
                    continue;
                case 48:
                    this.zzwj = com_google_android_gms_internal_zzfjj.zzcvz();
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
        if (this.zzwk) {
            com_google_android_gms_internal_zzfjk.zzl(1, this.zzwk);
        }
        com_google_android_gms_internal_zzfjk.zzaa(2, this.zzwi);
        if (this.zzwh != null && this.zzwh.length > 0) {
            for (int zzaa : this.zzwh) {
                com_google_android_gms_internal_zzfjk.zzaa(3, zzaa);
            }
        }
        if (this.name != 0) {
            com_google_android_gms_internal_zzfjk.zzaa(4, this.name);
        }
        if (this.zzwj) {
            com_google_android_gms_internal_zzfjk.zzl(6, this.zzwj);
        }
        super.zza(com_google_android_gms_internal_zzfjk);
    }

    protected final int zzq() {
        int i = 0;
        int zzq = super.zzq();
        if (this.zzwk) {
            zzq += zzfjk.zzlg(1) + 1;
        }
        int zzad = zzfjk.zzad(2, this.zzwi) + zzq;
        if (this.zzwh == null || this.zzwh.length <= 0) {
            zzq = zzad;
        } else {
            for (int zzlh : this.zzwh) {
                i += zzfjk.zzlh(zzlh);
            }
            zzq = (zzad + i) + (this.zzwh.length * 1);
        }
        if (this.name != 0) {
            zzq += zzfjk.zzad(4, this.name);
        }
        return this.zzwj ? zzq + (zzfjk.zzlg(6) + 1) : zzq;
    }
}
