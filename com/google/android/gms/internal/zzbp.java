package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbp extends zzfjm<zzbp> {
    private static volatile zzbp[] zzxk;
    public int[] zzxl;
    public int[] zzxm;
    public int[] zzxn;
    public int[] zzxo;
    public int[] zzxp;
    public int[] zzxq;
    public int[] zzxr;
    public int[] zzxs;
    public int[] zzxt;
    public int[] zzxu;

    public zzbp() {
        this.zzxl = zzfjv.zzpnp;
        this.zzxm = zzfjv.zzpnp;
        this.zzxn = zzfjv.zzpnp;
        this.zzxo = zzfjv.zzpnp;
        this.zzxp = zzfjv.zzpnp;
        this.zzxq = zzfjv.zzpnp;
        this.zzxr = zzfjv.zzpnp;
        this.zzxs = zzfjv.zzpnp;
        this.zzxt = zzfjv.zzpnp;
        this.zzxu = zzfjv.zzpnp;
        this.zzpnc = null;
        this.zzpfd = -1;
    }

    public static zzbp[] zzv() {
        if (zzxk == null) {
            synchronized (zzfjq.zzpnk) {
                if (zzxk == null) {
                    zzxk = new zzbp[0];
                }
            }
        }
        return zzxk;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbp)) {
            return false;
        }
        zzbp com_google_android_gms_internal_zzbp = (zzbp) obj;
        return !zzfjq.equals(this.zzxl, com_google_android_gms_internal_zzbp.zzxl) ? false : !zzfjq.equals(this.zzxm, com_google_android_gms_internal_zzbp.zzxm) ? false : !zzfjq.equals(this.zzxn, com_google_android_gms_internal_zzbp.zzxn) ? false : !zzfjq.equals(this.zzxo, com_google_android_gms_internal_zzbp.zzxo) ? false : !zzfjq.equals(this.zzxp, com_google_android_gms_internal_zzbp.zzxp) ? false : !zzfjq.equals(this.zzxq, com_google_android_gms_internal_zzbp.zzxq) ? false : !zzfjq.equals(this.zzxr, com_google_android_gms_internal_zzbp.zzxr) ? false : !zzfjq.equals(this.zzxs, com_google_android_gms_internal_zzbp.zzxs) ? false : !zzfjq.equals(this.zzxt, com_google_android_gms_internal_zzbp.zzxt) ? false : !zzfjq.equals(this.zzxu, com_google_android_gms_internal_zzbp.zzxu) ? false : (this.zzpnc == null || this.zzpnc.isEmpty()) ? com_google_android_gms_internal_zzbp.zzpnc == null || com_google_android_gms_internal_zzbp.zzpnc.isEmpty() : this.zzpnc.equals(com_google_android_gms_internal_zzbp.zzpnc);
    }

    public final int hashCode() {
        int hashCode = (((((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + zzfjq.hashCode(this.zzxl)) * 31) + zzfjq.hashCode(this.zzxm)) * 31) + zzfjq.hashCode(this.zzxn)) * 31) + zzfjq.hashCode(this.zzxo)) * 31) + zzfjq.hashCode(this.zzxp)) * 31) + zzfjq.hashCode(this.zzxq)) * 31) + zzfjq.hashCode(this.zzxr)) * 31) + zzfjq.hashCode(this.zzxs)) * 31) + zzfjq.hashCode(this.zzxt)) * 31) + zzfjq.hashCode(this.zzxu)) * 31;
        int hashCode2 = (this.zzpnc == null || this.zzpnc.isEmpty()) ? 0 : this.zzpnc.hashCode();
        return hashCode2 + hashCode;
    }

    public final /* synthetic */ zzfjs zza(zzfjj com_google_android_gms_internal_zzfjj) throws IOException {
        while (true) {
            int zzcvt = com_google_android_gms_internal_zzfjj.zzcvt();
            int zzb;
            Object obj;
            int zzks;
            Object obj2;
            switch (zzcvt) {
                case 0:
                    break;
                case 8:
                    zzb = zzfjv.zzb(com_google_android_gms_internal_zzfjj, 8);
                    zzcvt = this.zzxl == null ? 0 : this.zzxl.length;
                    obj = new int[(zzb + zzcvt)];
                    if (zzcvt != 0) {
                        System.arraycopy(this.zzxl, 0, obj, 0, zzcvt);
                    }
                    while (zzcvt < obj.length - 1) {
                        obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                        com_google_android_gms_internal_zzfjj.zzcvt();
                        zzcvt++;
                    }
                    obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                    this.zzxl = obj;
                    continue;
                case 10:
                    zzks = com_google_android_gms_internal_zzfjj.zzks(com_google_android_gms_internal_zzfjj.zzcwi());
                    zzb = com_google_android_gms_internal_zzfjj.getPosition();
                    zzcvt = 0;
                    while (com_google_android_gms_internal_zzfjj.zzcwk() > 0) {
                        com_google_android_gms_internal_zzfjj.zzcwi();
                        zzcvt++;
                    }
                    com_google_android_gms_internal_zzfjj.zzmg(zzb);
                    zzb = this.zzxl == null ? 0 : this.zzxl.length;
                    obj2 = new int[(zzcvt + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzxl, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = com_google_android_gms_internal_zzfjj.zzcwi();
                        zzb++;
                    }
                    this.zzxl = obj2;
                    com_google_android_gms_internal_zzfjj.zzkt(zzks);
                    continue;
                case 16:
                    zzb = zzfjv.zzb(com_google_android_gms_internal_zzfjj, 16);
                    zzcvt = this.zzxm == null ? 0 : this.zzxm.length;
                    obj = new int[(zzb + zzcvt)];
                    if (zzcvt != 0) {
                        System.arraycopy(this.zzxm, 0, obj, 0, zzcvt);
                    }
                    while (zzcvt < obj.length - 1) {
                        obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                        com_google_android_gms_internal_zzfjj.zzcvt();
                        zzcvt++;
                    }
                    obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                    this.zzxm = obj;
                    continue;
                case 18:
                    zzks = com_google_android_gms_internal_zzfjj.zzks(com_google_android_gms_internal_zzfjj.zzcwi());
                    zzb = com_google_android_gms_internal_zzfjj.getPosition();
                    zzcvt = 0;
                    while (com_google_android_gms_internal_zzfjj.zzcwk() > 0) {
                        com_google_android_gms_internal_zzfjj.zzcwi();
                        zzcvt++;
                    }
                    com_google_android_gms_internal_zzfjj.zzmg(zzb);
                    zzb = this.zzxm == null ? 0 : this.zzxm.length;
                    obj2 = new int[(zzcvt + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzxm, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = com_google_android_gms_internal_zzfjj.zzcwi();
                        zzb++;
                    }
                    this.zzxm = obj2;
                    com_google_android_gms_internal_zzfjj.zzkt(zzks);
                    continue;
                case 24:
                    zzb = zzfjv.zzb(com_google_android_gms_internal_zzfjj, 24);
                    zzcvt = this.zzxn == null ? 0 : this.zzxn.length;
                    obj = new int[(zzb + zzcvt)];
                    if (zzcvt != 0) {
                        System.arraycopy(this.zzxn, 0, obj, 0, zzcvt);
                    }
                    while (zzcvt < obj.length - 1) {
                        obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                        com_google_android_gms_internal_zzfjj.zzcvt();
                        zzcvt++;
                    }
                    obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                    this.zzxn = obj;
                    continue;
                case 26:
                    zzks = com_google_android_gms_internal_zzfjj.zzks(com_google_android_gms_internal_zzfjj.zzcwi());
                    zzb = com_google_android_gms_internal_zzfjj.getPosition();
                    zzcvt = 0;
                    while (com_google_android_gms_internal_zzfjj.zzcwk() > 0) {
                        com_google_android_gms_internal_zzfjj.zzcwi();
                        zzcvt++;
                    }
                    com_google_android_gms_internal_zzfjj.zzmg(zzb);
                    zzb = this.zzxn == null ? 0 : this.zzxn.length;
                    obj2 = new int[(zzcvt + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzxn, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = com_google_android_gms_internal_zzfjj.zzcwi();
                        zzb++;
                    }
                    this.zzxn = obj2;
                    com_google_android_gms_internal_zzfjj.zzkt(zzks);
                    continue;
                case 32:
                    zzb = zzfjv.zzb(com_google_android_gms_internal_zzfjj, 32);
                    zzcvt = this.zzxo == null ? 0 : this.zzxo.length;
                    obj = new int[(zzb + zzcvt)];
                    if (zzcvt != 0) {
                        System.arraycopy(this.zzxo, 0, obj, 0, zzcvt);
                    }
                    while (zzcvt < obj.length - 1) {
                        obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                        com_google_android_gms_internal_zzfjj.zzcvt();
                        zzcvt++;
                    }
                    obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                    this.zzxo = obj;
                    continue;
                case 34:
                    zzks = com_google_android_gms_internal_zzfjj.zzks(com_google_android_gms_internal_zzfjj.zzcwi());
                    zzb = com_google_android_gms_internal_zzfjj.getPosition();
                    zzcvt = 0;
                    while (com_google_android_gms_internal_zzfjj.zzcwk() > 0) {
                        com_google_android_gms_internal_zzfjj.zzcwi();
                        zzcvt++;
                    }
                    com_google_android_gms_internal_zzfjj.zzmg(zzb);
                    zzb = this.zzxo == null ? 0 : this.zzxo.length;
                    obj2 = new int[(zzcvt + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzxo, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = com_google_android_gms_internal_zzfjj.zzcwi();
                        zzb++;
                    }
                    this.zzxo = obj2;
                    com_google_android_gms_internal_zzfjj.zzkt(zzks);
                    continue;
                case 40:
                    zzb = zzfjv.zzb(com_google_android_gms_internal_zzfjj, 40);
                    zzcvt = this.zzxp == null ? 0 : this.zzxp.length;
                    obj = new int[(zzb + zzcvt)];
                    if (zzcvt != 0) {
                        System.arraycopy(this.zzxp, 0, obj, 0, zzcvt);
                    }
                    while (zzcvt < obj.length - 1) {
                        obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                        com_google_android_gms_internal_zzfjj.zzcvt();
                        zzcvt++;
                    }
                    obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                    this.zzxp = obj;
                    continue;
                case 42:
                    zzks = com_google_android_gms_internal_zzfjj.zzks(com_google_android_gms_internal_zzfjj.zzcwi());
                    zzb = com_google_android_gms_internal_zzfjj.getPosition();
                    zzcvt = 0;
                    while (com_google_android_gms_internal_zzfjj.zzcwk() > 0) {
                        com_google_android_gms_internal_zzfjj.zzcwi();
                        zzcvt++;
                    }
                    com_google_android_gms_internal_zzfjj.zzmg(zzb);
                    zzb = this.zzxp == null ? 0 : this.zzxp.length;
                    obj2 = new int[(zzcvt + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzxp, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = com_google_android_gms_internal_zzfjj.zzcwi();
                        zzb++;
                    }
                    this.zzxp = obj2;
                    com_google_android_gms_internal_zzfjj.zzkt(zzks);
                    continue;
                case 48:
                    zzb = zzfjv.zzb(com_google_android_gms_internal_zzfjj, 48);
                    zzcvt = this.zzxq == null ? 0 : this.zzxq.length;
                    obj = new int[(zzb + zzcvt)];
                    if (zzcvt != 0) {
                        System.arraycopy(this.zzxq, 0, obj, 0, zzcvt);
                    }
                    while (zzcvt < obj.length - 1) {
                        obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                        com_google_android_gms_internal_zzfjj.zzcvt();
                        zzcvt++;
                    }
                    obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                    this.zzxq = obj;
                    continue;
                case 50:
                    zzks = com_google_android_gms_internal_zzfjj.zzks(com_google_android_gms_internal_zzfjj.zzcwi());
                    zzb = com_google_android_gms_internal_zzfjj.getPosition();
                    zzcvt = 0;
                    while (com_google_android_gms_internal_zzfjj.zzcwk() > 0) {
                        com_google_android_gms_internal_zzfjj.zzcwi();
                        zzcvt++;
                    }
                    com_google_android_gms_internal_zzfjj.zzmg(zzb);
                    zzb = this.zzxq == null ? 0 : this.zzxq.length;
                    obj2 = new int[(zzcvt + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzxq, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = com_google_android_gms_internal_zzfjj.zzcwi();
                        zzb++;
                    }
                    this.zzxq = obj2;
                    com_google_android_gms_internal_zzfjj.zzkt(zzks);
                    continue;
                case 56:
                    zzb = zzfjv.zzb(com_google_android_gms_internal_zzfjj, 56);
                    zzcvt = this.zzxr == null ? 0 : this.zzxr.length;
                    obj = new int[(zzb + zzcvt)];
                    if (zzcvt != 0) {
                        System.arraycopy(this.zzxr, 0, obj, 0, zzcvt);
                    }
                    while (zzcvt < obj.length - 1) {
                        obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                        com_google_android_gms_internal_zzfjj.zzcvt();
                        zzcvt++;
                    }
                    obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                    this.zzxr = obj;
                    continue;
                case 58:
                    zzks = com_google_android_gms_internal_zzfjj.zzks(com_google_android_gms_internal_zzfjj.zzcwi());
                    zzb = com_google_android_gms_internal_zzfjj.getPosition();
                    zzcvt = 0;
                    while (com_google_android_gms_internal_zzfjj.zzcwk() > 0) {
                        com_google_android_gms_internal_zzfjj.zzcwi();
                        zzcvt++;
                    }
                    com_google_android_gms_internal_zzfjj.zzmg(zzb);
                    zzb = this.zzxr == null ? 0 : this.zzxr.length;
                    obj2 = new int[(zzcvt + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzxr, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = com_google_android_gms_internal_zzfjj.zzcwi();
                        zzb++;
                    }
                    this.zzxr = obj2;
                    com_google_android_gms_internal_zzfjj.zzkt(zzks);
                    continue;
                case 64:
                    zzb = zzfjv.zzb(com_google_android_gms_internal_zzfjj, 64);
                    zzcvt = this.zzxs == null ? 0 : this.zzxs.length;
                    obj = new int[(zzb + zzcvt)];
                    if (zzcvt != 0) {
                        System.arraycopy(this.zzxs, 0, obj, 0, zzcvt);
                    }
                    while (zzcvt < obj.length - 1) {
                        obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                        com_google_android_gms_internal_zzfjj.zzcvt();
                        zzcvt++;
                    }
                    obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                    this.zzxs = obj;
                    continue;
                case 66:
                    zzks = com_google_android_gms_internal_zzfjj.zzks(com_google_android_gms_internal_zzfjj.zzcwi());
                    zzb = com_google_android_gms_internal_zzfjj.getPosition();
                    zzcvt = 0;
                    while (com_google_android_gms_internal_zzfjj.zzcwk() > 0) {
                        com_google_android_gms_internal_zzfjj.zzcwi();
                        zzcvt++;
                    }
                    com_google_android_gms_internal_zzfjj.zzmg(zzb);
                    zzb = this.zzxs == null ? 0 : this.zzxs.length;
                    obj2 = new int[(zzcvt + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzxs, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = com_google_android_gms_internal_zzfjj.zzcwi();
                        zzb++;
                    }
                    this.zzxs = obj2;
                    com_google_android_gms_internal_zzfjj.zzkt(zzks);
                    continue;
                case 72:
                    zzb = zzfjv.zzb(com_google_android_gms_internal_zzfjj, 72);
                    zzcvt = this.zzxt == null ? 0 : this.zzxt.length;
                    obj = new int[(zzb + zzcvt)];
                    if (zzcvt != 0) {
                        System.arraycopy(this.zzxt, 0, obj, 0, zzcvt);
                    }
                    while (zzcvt < obj.length - 1) {
                        obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                        com_google_android_gms_internal_zzfjj.zzcvt();
                        zzcvt++;
                    }
                    obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                    this.zzxt = obj;
                    continue;
                case 74:
                    zzks = com_google_android_gms_internal_zzfjj.zzks(com_google_android_gms_internal_zzfjj.zzcwi());
                    zzb = com_google_android_gms_internal_zzfjj.getPosition();
                    zzcvt = 0;
                    while (com_google_android_gms_internal_zzfjj.zzcwk() > 0) {
                        com_google_android_gms_internal_zzfjj.zzcwi();
                        zzcvt++;
                    }
                    com_google_android_gms_internal_zzfjj.zzmg(zzb);
                    zzb = this.zzxt == null ? 0 : this.zzxt.length;
                    obj2 = new int[(zzcvt + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzxt, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = com_google_android_gms_internal_zzfjj.zzcwi();
                        zzb++;
                    }
                    this.zzxt = obj2;
                    com_google_android_gms_internal_zzfjj.zzkt(zzks);
                    continue;
                case 80:
                    zzb = zzfjv.zzb(com_google_android_gms_internal_zzfjj, 80);
                    zzcvt = this.zzxu == null ? 0 : this.zzxu.length;
                    obj = new int[(zzb + zzcvt)];
                    if (zzcvt != 0) {
                        System.arraycopy(this.zzxu, 0, obj, 0, zzcvt);
                    }
                    while (zzcvt < obj.length - 1) {
                        obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                        com_google_android_gms_internal_zzfjj.zzcvt();
                        zzcvt++;
                    }
                    obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                    this.zzxu = obj;
                    continue;
                case 82:
                    zzks = com_google_android_gms_internal_zzfjj.zzks(com_google_android_gms_internal_zzfjj.zzcwi());
                    zzb = com_google_android_gms_internal_zzfjj.getPosition();
                    zzcvt = 0;
                    while (com_google_android_gms_internal_zzfjj.zzcwk() > 0) {
                        com_google_android_gms_internal_zzfjj.zzcwi();
                        zzcvt++;
                    }
                    com_google_android_gms_internal_zzfjj.zzmg(zzb);
                    zzb = this.zzxu == null ? 0 : this.zzxu.length;
                    obj2 = new int[(zzcvt + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzxu, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = com_google_android_gms_internal_zzfjj.zzcwi();
                        zzb++;
                    }
                    this.zzxu = obj2;
                    com_google_android_gms_internal_zzfjj.zzkt(zzks);
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
        if (this.zzxl != null && this.zzxl.length > 0) {
            for (int zzaa : this.zzxl) {
                com_google_android_gms_internal_zzfjk.zzaa(1, zzaa);
            }
        }
        if (this.zzxm != null && this.zzxm.length > 0) {
            for (int zzaa2 : this.zzxm) {
                com_google_android_gms_internal_zzfjk.zzaa(2, zzaa2);
            }
        }
        if (this.zzxn != null && this.zzxn.length > 0) {
            for (int zzaa22 : this.zzxn) {
                com_google_android_gms_internal_zzfjk.zzaa(3, zzaa22);
            }
        }
        if (this.zzxo != null && this.zzxo.length > 0) {
            for (int zzaa222 : this.zzxo) {
                com_google_android_gms_internal_zzfjk.zzaa(4, zzaa222);
            }
        }
        if (this.zzxp != null && this.zzxp.length > 0) {
            for (int zzaa2222 : this.zzxp) {
                com_google_android_gms_internal_zzfjk.zzaa(5, zzaa2222);
            }
        }
        if (this.zzxq != null && this.zzxq.length > 0) {
            for (int zzaa22222 : this.zzxq) {
                com_google_android_gms_internal_zzfjk.zzaa(6, zzaa22222);
            }
        }
        if (this.zzxr != null && this.zzxr.length > 0) {
            for (int zzaa222222 : this.zzxr) {
                com_google_android_gms_internal_zzfjk.zzaa(7, zzaa222222);
            }
        }
        if (this.zzxs != null && this.zzxs.length > 0) {
            for (int zzaa2222222 : this.zzxs) {
                com_google_android_gms_internal_zzfjk.zzaa(8, zzaa2222222);
            }
        }
        if (this.zzxt != null && this.zzxt.length > 0) {
            for (int zzaa22222222 : this.zzxt) {
                com_google_android_gms_internal_zzfjk.zzaa(9, zzaa22222222);
            }
        }
        if (this.zzxu != null && this.zzxu.length > 0) {
            while (i < this.zzxu.length) {
                com_google_android_gms_internal_zzfjk.zzaa(10, this.zzxu[i]);
                i++;
            }
        }
        super.zza(com_google_android_gms_internal_zzfjk);
    }

    protected final int zzq() {
        int i;
        int i2;
        int i3 = 0;
        int zzq = super.zzq();
        if (this.zzxl == null || this.zzxl.length <= 0) {
            i = zzq;
        } else {
            i2 = 0;
            for (int zzlh : this.zzxl) {
                i2 += zzfjk.zzlh(zzlh);
            }
            i = (zzq + i2) + (this.zzxl.length * 1);
        }
        if (this.zzxm != null && this.zzxm.length > 0) {
            zzq = 0;
            for (int zzlh2 : this.zzxm) {
                zzq += zzfjk.zzlh(zzlh2);
            }
            i = (i + zzq) + (this.zzxm.length * 1);
        }
        if (this.zzxn != null && this.zzxn.length > 0) {
            zzq = 0;
            for (int zzlh22 : this.zzxn) {
                zzq += zzfjk.zzlh(zzlh22);
            }
            i = (i + zzq) + (this.zzxn.length * 1);
        }
        if (this.zzxo != null && this.zzxo.length > 0) {
            zzq = 0;
            for (int zzlh222 : this.zzxo) {
                zzq += zzfjk.zzlh(zzlh222);
            }
            i = (i + zzq) + (this.zzxo.length * 1);
        }
        if (this.zzxp != null && this.zzxp.length > 0) {
            zzq = 0;
            for (int zzlh2222 : this.zzxp) {
                zzq += zzfjk.zzlh(zzlh2222);
            }
            i = (i + zzq) + (this.zzxp.length * 1);
        }
        if (this.zzxq != null && this.zzxq.length > 0) {
            zzq = 0;
            for (int zzlh22222 : this.zzxq) {
                zzq += zzfjk.zzlh(zzlh22222);
            }
            i = (i + zzq) + (this.zzxq.length * 1);
        }
        if (this.zzxr != null && this.zzxr.length > 0) {
            zzq = 0;
            for (int zzlh222222 : this.zzxr) {
                zzq += zzfjk.zzlh(zzlh222222);
            }
            i = (i + zzq) + (this.zzxr.length * 1);
        }
        if (this.zzxs != null && this.zzxs.length > 0) {
            zzq = 0;
            for (int zzlh2222222 : this.zzxs) {
                zzq += zzfjk.zzlh(zzlh2222222);
            }
            i = (i + zzq) + (this.zzxs.length * 1);
        }
        if (this.zzxt != null && this.zzxt.length > 0) {
            zzq = 0;
            for (int zzlh22222222 : this.zzxt) {
                zzq += zzfjk.zzlh(zzlh22222222);
            }
            i = (i + zzq) + (this.zzxt.length * 1);
        }
        if (this.zzxu == null || this.zzxu.length <= 0) {
            return i;
        }
        i2 = 0;
        while (i3 < this.zzxu.length) {
            i2 += zzfjk.zzlh(this.zzxu[i3]);
            i3++;
        }
        return (i + i2) + (this.zzxu.length * 1);
    }
}
