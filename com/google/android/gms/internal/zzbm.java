package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbm extends zzfjm<zzbm> {
    public zzbs[] zzwq;
    public zzbs[] zzwr;
    public zzbl[] zzws;

    public zzbm() {
        this.zzwq = zzbs.zzx();
        this.zzwr = zzbs.zzx();
        this.zzws = zzbl.zzt();
        this.zzpnc = null;
        this.zzpfd = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbm)) {
            return false;
        }
        zzbm com_google_android_gms_internal_zzbm = (zzbm) obj;
        return !zzfjq.equals(this.zzwq, com_google_android_gms_internal_zzbm.zzwq) ? false : !zzfjq.equals(this.zzwr, com_google_android_gms_internal_zzbm.zzwr) ? false : !zzfjq.equals(this.zzws, com_google_android_gms_internal_zzbm.zzws) ? false : (this.zzpnc == null || this.zzpnc.isEmpty()) ? com_google_android_gms_internal_zzbm.zzpnc == null || com_google_android_gms_internal_zzbm.zzpnc.isEmpty() : this.zzpnc.equals(com_google_android_gms_internal_zzbm.zzpnc);
    }

    public final int hashCode() {
        int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + zzfjq.hashCode(this.zzwq)) * 31) + zzfjq.hashCode(this.zzwr)) * 31) + zzfjq.hashCode(this.zzws)) * 31;
        int hashCode2 = (this.zzpnc == null || this.zzpnc.isEmpty()) ? 0 : this.zzpnc.hashCode();
        return hashCode2 + hashCode;
    }

    public final /* synthetic */ zzfjs zza(zzfjj com_google_android_gms_internal_zzfjj) throws IOException {
        while (true) {
            int zzcvt = com_google_android_gms_internal_zzfjj.zzcvt();
            int zzb;
            Object obj;
            switch (zzcvt) {
                case 0:
                    break;
                case 10:
                    zzb = zzfjv.zzb(com_google_android_gms_internal_zzfjj, 10);
                    zzcvt = this.zzwq == null ? 0 : this.zzwq.length;
                    obj = new zzbs[(zzb + zzcvt)];
                    if (zzcvt != 0) {
                        System.arraycopy(this.zzwq, 0, obj, 0, zzcvt);
                    }
                    while (zzcvt < obj.length - 1) {
                        obj[zzcvt] = new zzbs();
                        com_google_android_gms_internal_zzfjj.zza(obj[zzcvt]);
                        com_google_android_gms_internal_zzfjj.zzcvt();
                        zzcvt++;
                    }
                    obj[zzcvt] = new zzbs();
                    com_google_android_gms_internal_zzfjj.zza(obj[zzcvt]);
                    this.zzwq = obj;
                    continue;
                case 18:
                    zzb = zzfjv.zzb(com_google_android_gms_internal_zzfjj, 18);
                    zzcvt = this.zzwr == null ? 0 : this.zzwr.length;
                    obj = new zzbs[(zzb + zzcvt)];
                    if (zzcvt != 0) {
                        System.arraycopy(this.zzwr, 0, obj, 0, zzcvt);
                    }
                    while (zzcvt < obj.length - 1) {
                        obj[zzcvt] = new zzbs();
                        com_google_android_gms_internal_zzfjj.zza(obj[zzcvt]);
                        com_google_android_gms_internal_zzfjj.zzcvt();
                        zzcvt++;
                    }
                    obj[zzcvt] = new zzbs();
                    com_google_android_gms_internal_zzfjj.zza(obj[zzcvt]);
                    this.zzwr = obj;
                    continue;
                case 26:
                    zzb = zzfjv.zzb(com_google_android_gms_internal_zzfjj, 26);
                    zzcvt = this.zzws == null ? 0 : this.zzws.length;
                    obj = new zzbl[(zzb + zzcvt)];
                    if (zzcvt != 0) {
                        System.arraycopy(this.zzws, 0, obj, 0, zzcvt);
                    }
                    while (zzcvt < obj.length - 1) {
                        obj[zzcvt] = new zzbl();
                        com_google_android_gms_internal_zzfjj.zza(obj[zzcvt]);
                        com_google_android_gms_internal_zzfjj.zzcvt();
                        zzcvt++;
                    }
                    obj[zzcvt] = new zzbl();
                    com_google_android_gms_internal_zzfjj.zza(obj[zzcvt]);
                    this.zzws = obj;
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
        int i = 0;
        if (this.zzwq != null && this.zzwq.length > 0) {
            for (zzfjs com_google_android_gms_internal_zzfjs : this.zzwq) {
                if (com_google_android_gms_internal_zzfjs != null) {
                    com_google_android_gms_internal_zzfjk.zza(1, com_google_android_gms_internal_zzfjs);
                }
            }
        }
        if (this.zzwr != null && this.zzwr.length > 0) {
            for (zzfjs com_google_android_gms_internal_zzfjs2 : this.zzwr) {
                if (com_google_android_gms_internal_zzfjs2 != null) {
                    com_google_android_gms_internal_zzfjk.zza(2, com_google_android_gms_internal_zzfjs2);
                }
            }
        }
        if (this.zzws != null && this.zzws.length > 0) {
            while (i < this.zzws.length) {
                zzfjs com_google_android_gms_internal_zzfjs3 = this.zzws[i];
                if (com_google_android_gms_internal_zzfjs3 != null) {
                    com_google_android_gms_internal_zzfjk.zza(3, com_google_android_gms_internal_zzfjs3);
                }
                i++;
            }
        }
        super.zza(com_google_android_gms_internal_zzfjk);
    }

    protected final int zzq() {
        int i;
        int i2 = 0;
        int zzq = super.zzq();
        if (this.zzwq != null && this.zzwq.length > 0) {
            i = zzq;
            for (zzfjs com_google_android_gms_internal_zzfjs : this.zzwq) {
                if (com_google_android_gms_internal_zzfjs != null) {
                    i += zzfjk.zzb(1, com_google_android_gms_internal_zzfjs);
                }
            }
            zzq = i;
        }
        if (this.zzwr != null && this.zzwr.length > 0) {
            i = zzq;
            for (zzfjs com_google_android_gms_internal_zzfjs2 : this.zzwr) {
                if (com_google_android_gms_internal_zzfjs2 != null) {
                    i += zzfjk.zzb(2, com_google_android_gms_internal_zzfjs2);
                }
            }
            zzq = i;
        }
        if (this.zzws != null && this.zzws.length > 0) {
            while (i2 < this.zzws.length) {
                zzfjs com_google_android_gms_internal_zzfjs3 = this.zzws[i2];
                if (com_google_android_gms_internal_zzfjs3 != null) {
                    zzq += zzfjk.zzb(3, com_google_android_gms_internal_zzfjs3);
                }
                i2++;
            }
        }
        return zzq;
    }
}
