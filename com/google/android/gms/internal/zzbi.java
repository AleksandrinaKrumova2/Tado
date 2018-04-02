package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbi {

    public static final class zza extends zzfjm<zza> {
        public static final zzfjn<zzbs, zza> zzxv = zzfjn.zza(11, zza.class, 810);
        private static final zza[] zzxw = new zza[0];
        public int[] zzxx;
        public int[] zzxy;
        public int[] zzxz;
        private int zzya;
        public int[] zzyb;
        public int zzyc;
        private int zzyd;

        public zza() {
            this.zzxx = zzfjv.zzpnp;
            this.zzxy = zzfjv.zzpnp;
            this.zzxz = zzfjv.zzpnp;
            this.zzya = 0;
            this.zzyb = zzfjv.zzpnp;
            this.zzyc = 0;
            this.zzyd = 0;
            this.zzpnc = null;
            this.zzpfd = -1;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza com_google_android_gms_internal_zzbi_zza = (zza) obj;
            return !zzfjq.equals(this.zzxx, com_google_android_gms_internal_zzbi_zza.zzxx) ? false : !zzfjq.equals(this.zzxy, com_google_android_gms_internal_zzbi_zza.zzxy) ? false : !zzfjq.equals(this.zzxz, com_google_android_gms_internal_zzbi_zza.zzxz) ? false : this.zzya != com_google_android_gms_internal_zzbi_zza.zzya ? false : !zzfjq.equals(this.zzyb, com_google_android_gms_internal_zzbi_zza.zzyb) ? false : this.zzyc != com_google_android_gms_internal_zzbi_zza.zzyc ? false : this.zzyd != com_google_android_gms_internal_zzbi_zza.zzyd ? false : (this.zzpnc == null || this.zzpnc.isEmpty()) ? com_google_android_gms_internal_zzbi_zza.zzpnc == null || com_google_android_gms_internal_zzbi_zza.zzpnc.isEmpty() : this.zzpnc.equals(com_google_android_gms_internal_zzbi_zza.zzpnc);
        }

        public final int hashCode() {
            int hashCode = (((((((((((((((getClass().getName().hashCode() + 527) * 31) + zzfjq.hashCode(this.zzxx)) * 31) + zzfjq.hashCode(this.zzxy)) * 31) + zzfjq.hashCode(this.zzxz)) * 31) + this.zzya) * 31) + zzfjq.hashCode(this.zzyb)) * 31) + this.zzyc) * 31) + this.zzyd) * 31;
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
                        zzcvt = this.zzxx == null ? 0 : this.zzxx.length;
                        obj = new int[(zzb + zzcvt)];
                        if (zzcvt != 0) {
                            System.arraycopy(this.zzxx, 0, obj, 0, zzcvt);
                        }
                        while (zzcvt < obj.length - 1) {
                            obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                            com_google_android_gms_internal_zzfjj.zzcvt();
                            zzcvt++;
                        }
                        obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                        this.zzxx = obj;
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
                        zzb = this.zzxx == null ? 0 : this.zzxx.length;
                        obj2 = new int[(zzcvt + zzb)];
                        if (zzb != 0) {
                            System.arraycopy(this.zzxx, 0, obj2, 0, zzb);
                        }
                        while (zzb < obj2.length) {
                            obj2[zzb] = com_google_android_gms_internal_zzfjj.zzcwi();
                            zzb++;
                        }
                        this.zzxx = obj2;
                        com_google_android_gms_internal_zzfjj.zzkt(zzks);
                        continue;
                    case 16:
                        zzb = zzfjv.zzb(com_google_android_gms_internal_zzfjj, 16);
                        zzcvt = this.zzxy == null ? 0 : this.zzxy.length;
                        obj = new int[(zzb + zzcvt)];
                        if (zzcvt != 0) {
                            System.arraycopy(this.zzxy, 0, obj, 0, zzcvt);
                        }
                        while (zzcvt < obj.length - 1) {
                            obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                            com_google_android_gms_internal_zzfjj.zzcvt();
                            zzcvt++;
                        }
                        obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                        this.zzxy = obj;
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
                        zzb = this.zzxy == null ? 0 : this.zzxy.length;
                        obj2 = new int[(zzcvt + zzb)];
                        if (zzb != 0) {
                            System.arraycopy(this.zzxy, 0, obj2, 0, zzb);
                        }
                        while (zzb < obj2.length) {
                            obj2[zzb] = com_google_android_gms_internal_zzfjj.zzcwi();
                            zzb++;
                        }
                        this.zzxy = obj2;
                        com_google_android_gms_internal_zzfjj.zzkt(zzks);
                        continue;
                    case 24:
                        zzb = zzfjv.zzb(com_google_android_gms_internal_zzfjj, 24);
                        zzcvt = this.zzxz == null ? 0 : this.zzxz.length;
                        obj = new int[(zzb + zzcvt)];
                        if (zzcvt != 0) {
                            System.arraycopy(this.zzxz, 0, obj, 0, zzcvt);
                        }
                        while (zzcvt < obj.length - 1) {
                            obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                            com_google_android_gms_internal_zzfjj.zzcvt();
                            zzcvt++;
                        }
                        obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                        this.zzxz = obj;
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
                        zzb = this.zzxz == null ? 0 : this.zzxz.length;
                        obj2 = new int[(zzcvt + zzb)];
                        if (zzb != 0) {
                            System.arraycopy(this.zzxz, 0, obj2, 0, zzb);
                        }
                        while (zzb < obj2.length) {
                            obj2[zzb] = com_google_android_gms_internal_zzfjj.zzcwi();
                            zzb++;
                        }
                        this.zzxz = obj2;
                        com_google_android_gms_internal_zzfjj.zzkt(zzks);
                        continue;
                    case 32:
                        this.zzya = com_google_android_gms_internal_zzfjj.zzcwi();
                        continue;
                    case 40:
                        zzb = zzfjv.zzb(com_google_android_gms_internal_zzfjj, 40);
                        zzcvt = this.zzyb == null ? 0 : this.zzyb.length;
                        obj = new int[(zzb + zzcvt)];
                        if (zzcvt != 0) {
                            System.arraycopy(this.zzyb, 0, obj, 0, zzcvt);
                        }
                        while (zzcvt < obj.length - 1) {
                            obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                            com_google_android_gms_internal_zzfjj.zzcvt();
                            zzcvt++;
                        }
                        obj[zzcvt] = com_google_android_gms_internal_zzfjj.zzcwi();
                        this.zzyb = obj;
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
                        zzb = this.zzyb == null ? 0 : this.zzyb.length;
                        obj2 = new int[(zzcvt + zzb)];
                        if (zzb != 0) {
                            System.arraycopy(this.zzyb, 0, obj2, 0, zzb);
                        }
                        while (zzb < obj2.length) {
                            obj2[zzb] = com_google_android_gms_internal_zzfjj.zzcwi();
                            zzb++;
                        }
                        this.zzyb = obj2;
                        com_google_android_gms_internal_zzfjj.zzkt(zzks);
                        continue;
                    case 48:
                        this.zzyc = com_google_android_gms_internal_zzfjj.zzcwi();
                        continue;
                    case 56:
                        this.zzyd = com_google_android_gms_internal_zzfjj.zzcwi();
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
            if (this.zzxx != null && this.zzxx.length > 0) {
                for (int zzaa : this.zzxx) {
                    com_google_android_gms_internal_zzfjk.zzaa(1, zzaa);
                }
            }
            if (this.zzxy != null && this.zzxy.length > 0) {
                for (int zzaa2 : this.zzxy) {
                    com_google_android_gms_internal_zzfjk.zzaa(2, zzaa2);
                }
            }
            if (this.zzxz != null && this.zzxz.length > 0) {
                for (int zzaa22 : this.zzxz) {
                    com_google_android_gms_internal_zzfjk.zzaa(3, zzaa22);
                }
            }
            if (this.zzya != 0) {
                com_google_android_gms_internal_zzfjk.zzaa(4, this.zzya);
            }
            if (this.zzyb != null && this.zzyb.length > 0) {
                while (i < this.zzyb.length) {
                    com_google_android_gms_internal_zzfjk.zzaa(5, this.zzyb[i]);
                    i++;
                }
            }
            if (this.zzyc != 0) {
                com_google_android_gms_internal_zzfjk.zzaa(6, this.zzyc);
            }
            if (this.zzyd != 0) {
                com_google_android_gms_internal_zzfjk.zzaa(7, this.zzyd);
            }
            super.zza(com_google_android_gms_internal_zzfjk);
        }

        protected final int zzq() {
            int i;
            int i2;
            int i3 = 0;
            int zzq = super.zzq();
            if (this.zzxx == null || this.zzxx.length <= 0) {
                i = zzq;
            } else {
                i2 = 0;
                for (int zzlh : this.zzxx) {
                    i2 += zzfjk.zzlh(zzlh);
                }
                i = (zzq + i2) + (this.zzxx.length * 1);
            }
            if (this.zzxy != null && this.zzxy.length > 0) {
                zzq = 0;
                for (int zzlh2 : this.zzxy) {
                    zzq += zzfjk.zzlh(zzlh2);
                }
                i = (i + zzq) + (this.zzxy.length * 1);
            }
            if (this.zzxz != null && this.zzxz.length > 0) {
                zzq = 0;
                for (int zzlh22 : this.zzxz) {
                    zzq += zzfjk.zzlh(zzlh22);
                }
                i = (i + zzq) + (this.zzxz.length * 1);
            }
            if (this.zzya != 0) {
                i += zzfjk.zzad(4, this.zzya);
            }
            if (this.zzyb != null && this.zzyb.length > 0) {
                i2 = 0;
                while (i3 < this.zzyb.length) {
                    i2 += zzfjk.zzlh(this.zzyb[i3]);
                    i3++;
                }
                i = (i + i2) + (this.zzyb.length * 1);
            }
            if (this.zzyc != 0) {
                i += zzfjk.zzad(6, this.zzyc);
            }
            return this.zzyd != 0 ? i + zzfjk.zzad(7, this.zzyd) : i;
        }
    }
}
