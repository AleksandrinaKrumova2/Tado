package com.google.android.gms.internal;

import com.tado.android.utils.Constants;
import java.io.IOException;

public final class zzbo extends zzfjm<zzbo> {
    public String version;
    private String[] zzwu;
    public String[] zzwv;
    public zzbs[] zzww;
    public zzbn[] zzwx;
    public zzbk[] zzwy;
    public zzbk[] zzwz;
    public zzbk[] zzxa;
    public zzbp[] zzxb;
    private String zzxc;
    private String zzxd;
    private String zzxe;
    private zzbj zzxf;
    private float zzxg;
    private boolean zzxh;
    private String[] zzxi;
    public int zzxj;

    public zzbo() {
        this.zzwu = zzfjv.EMPTY_STRING_ARRAY;
        this.zzwv = zzfjv.EMPTY_STRING_ARRAY;
        this.zzww = zzbs.zzx();
        this.zzwx = zzbn.zzu();
        this.zzwy = zzbk.zzs();
        this.zzwz = zzbk.zzs();
        this.zzxa = zzbk.zzs();
        this.zzxb = zzbp.zzv();
        this.zzxc = "";
        this.zzxd = "";
        this.zzxe = "0";
        this.version = "";
        this.zzxf = null;
        this.zzxg = 0.0f;
        this.zzxh = false;
        this.zzxi = zzfjv.EMPTY_STRING_ARRAY;
        this.zzxj = 0;
        this.zzpnc = null;
        this.zzpfd = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbo)) {
            return false;
        }
        zzbo com_google_android_gms_internal_zzbo = (zzbo) obj;
        if (!zzfjq.equals(this.zzwu, com_google_android_gms_internal_zzbo.zzwu)) {
            return false;
        }
        if (!zzfjq.equals(this.zzwv, com_google_android_gms_internal_zzbo.zzwv)) {
            return false;
        }
        if (!zzfjq.equals(this.zzww, com_google_android_gms_internal_zzbo.zzww)) {
            return false;
        }
        if (!zzfjq.equals(this.zzwx, com_google_android_gms_internal_zzbo.zzwx)) {
            return false;
        }
        if (!zzfjq.equals(this.zzwy, com_google_android_gms_internal_zzbo.zzwy)) {
            return false;
        }
        if (!zzfjq.equals(this.zzwz, com_google_android_gms_internal_zzbo.zzwz)) {
            return false;
        }
        if (!zzfjq.equals(this.zzxa, com_google_android_gms_internal_zzbo.zzxa)) {
            return false;
        }
        if (!zzfjq.equals(this.zzxb, com_google_android_gms_internal_zzbo.zzxb)) {
            return false;
        }
        if (this.zzxc == null) {
            if (com_google_android_gms_internal_zzbo.zzxc != null) {
                return false;
            }
        } else if (!this.zzxc.equals(com_google_android_gms_internal_zzbo.zzxc)) {
            return false;
        }
        if (this.zzxd == null) {
            if (com_google_android_gms_internal_zzbo.zzxd != null) {
                return false;
            }
        } else if (!this.zzxd.equals(com_google_android_gms_internal_zzbo.zzxd)) {
            return false;
        }
        if (this.zzxe == null) {
            if (com_google_android_gms_internal_zzbo.zzxe != null) {
                return false;
            }
        } else if (!this.zzxe.equals(com_google_android_gms_internal_zzbo.zzxe)) {
            return false;
        }
        if (this.version == null) {
            if (com_google_android_gms_internal_zzbo.version != null) {
                return false;
            }
        } else if (!this.version.equals(com_google_android_gms_internal_zzbo.version)) {
            return false;
        }
        if (this.zzxf == null) {
            if (com_google_android_gms_internal_zzbo.zzxf != null) {
                return false;
            }
        } else if (!this.zzxf.equals(com_google_android_gms_internal_zzbo.zzxf)) {
            return false;
        }
        return Float.floatToIntBits(this.zzxg) != Float.floatToIntBits(com_google_android_gms_internal_zzbo.zzxg) ? false : this.zzxh != com_google_android_gms_internal_zzbo.zzxh ? false : !zzfjq.equals(this.zzxi, com_google_android_gms_internal_zzbo.zzxi) ? false : this.zzxj != com_google_android_gms_internal_zzbo.zzxj ? false : (this.zzpnc == null || this.zzpnc.isEmpty()) ? com_google_android_gms_internal_zzbo.zzpnc == null || com_google_android_gms_internal_zzbo.zzpnc.isEmpty() : this.zzpnc.equals(com_google_android_gms_internal_zzbo.zzpnc);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (this.version == null ? 0 : this.version.hashCode()) + (((this.zzxe == null ? 0 : this.zzxe.hashCode()) + (((this.zzxd == null ? 0 : this.zzxd.hashCode()) + (((this.zzxc == null ? 0 : this.zzxc.hashCode()) + ((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + zzfjq.hashCode(this.zzwu)) * 31) + zzfjq.hashCode(this.zzwv)) * 31) + zzfjq.hashCode(this.zzww)) * 31) + zzfjq.hashCode(this.zzwx)) * 31) + zzfjq.hashCode(this.zzwy)) * 31) + zzfjq.hashCode(this.zzwz)) * 31) + zzfjq.hashCode(this.zzxa)) * 31) + zzfjq.hashCode(this.zzxb)) * 31)) * 31)) * 31)) * 31);
        zzbj com_google_android_gms_internal_zzbj = this.zzxf;
        hashCode = ((((((this.zzxh ? 1231 : 1237) + (((((com_google_android_gms_internal_zzbj == null ? 0 : com_google_android_gms_internal_zzbj.hashCode()) + (hashCode * 31)) * 31) + Float.floatToIntBits(this.zzxg)) * 31)) * 31) + zzfjq.hashCode(this.zzxi)) * 31) + this.zzxj) * 31;
        if (!(this.zzpnc == null || this.zzpnc.isEmpty())) {
            i = this.zzpnc.hashCode();
        }
        return hashCode + i;
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
                    zzcvt = this.zzwv == null ? 0 : this.zzwv.length;
                    obj = new String[(zzb + zzcvt)];
                    if (zzcvt != 0) {
                        System.arraycopy(this.zzwv, 0, obj, 0, zzcvt);
                    }
                    while (zzcvt < obj.length - 1) {
                        obj[zzcvt] = com_google_android_gms_internal_zzfjj.readString();
                        com_google_android_gms_internal_zzfjj.zzcvt();
                        zzcvt++;
                    }
                    obj[zzcvt] = com_google_android_gms_internal_zzfjj.readString();
                    this.zzwv = obj;
                    continue;
                case 18:
                    zzb = zzfjv.zzb(com_google_android_gms_internal_zzfjj, 18);
                    zzcvt = this.zzww == null ? 0 : this.zzww.length;
                    obj = new zzbs[(zzb + zzcvt)];
                    if (zzcvt != 0) {
                        System.arraycopy(this.zzww, 0, obj, 0, zzcvt);
                    }
                    while (zzcvt < obj.length - 1) {
                        obj[zzcvt] = new zzbs();
                        com_google_android_gms_internal_zzfjj.zza(obj[zzcvt]);
                        com_google_android_gms_internal_zzfjj.zzcvt();
                        zzcvt++;
                    }
                    obj[zzcvt] = new zzbs();
                    com_google_android_gms_internal_zzfjj.zza(obj[zzcvt]);
                    this.zzww = obj;
                    continue;
                case 26:
                    zzb = zzfjv.zzb(com_google_android_gms_internal_zzfjj, 26);
                    zzcvt = this.zzwx == null ? 0 : this.zzwx.length;
                    obj = new zzbn[(zzb + zzcvt)];
                    if (zzcvt != 0) {
                        System.arraycopy(this.zzwx, 0, obj, 0, zzcvt);
                    }
                    while (zzcvt < obj.length - 1) {
                        obj[zzcvt] = new zzbn();
                        com_google_android_gms_internal_zzfjj.zza(obj[zzcvt]);
                        com_google_android_gms_internal_zzfjj.zzcvt();
                        zzcvt++;
                    }
                    obj[zzcvt] = new zzbn();
                    com_google_android_gms_internal_zzfjj.zza(obj[zzcvt]);
                    this.zzwx = obj;
                    continue;
                case 34:
                    zzb = zzfjv.zzb(com_google_android_gms_internal_zzfjj, 34);
                    zzcvt = this.zzwy == null ? 0 : this.zzwy.length;
                    obj = new zzbk[(zzb + zzcvt)];
                    if (zzcvt != 0) {
                        System.arraycopy(this.zzwy, 0, obj, 0, zzcvt);
                    }
                    while (zzcvt < obj.length - 1) {
                        obj[zzcvt] = new zzbk();
                        com_google_android_gms_internal_zzfjj.zza(obj[zzcvt]);
                        com_google_android_gms_internal_zzfjj.zzcvt();
                        zzcvt++;
                    }
                    obj[zzcvt] = new zzbk();
                    com_google_android_gms_internal_zzfjj.zza(obj[zzcvt]);
                    this.zzwy = obj;
                    continue;
                case 42:
                    zzb = zzfjv.zzb(com_google_android_gms_internal_zzfjj, 42);
                    zzcvt = this.zzwz == null ? 0 : this.zzwz.length;
                    obj = new zzbk[(zzb + zzcvt)];
                    if (zzcvt != 0) {
                        System.arraycopy(this.zzwz, 0, obj, 0, zzcvt);
                    }
                    while (zzcvt < obj.length - 1) {
                        obj[zzcvt] = new zzbk();
                        com_google_android_gms_internal_zzfjj.zza(obj[zzcvt]);
                        com_google_android_gms_internal_zzfjj.zzcvt();
                        zzcvt++;
                    }
                    obj[zzcvt] = new zzbk();
                    com_google_android_gms_internal_zzfjj.zza(obj[zzcvt]);
                    this.zzwz = obj;
                    continue;
                case 50:
                    zzb = zzfjv.zzb(com_google_android_gms_internal_zzfjj, 50);
                    zzcvt = this.zzxa == null ? 0 : this.zzxa.length;
                    obj = new zzbk[(zzb + zzcvt)];
                    if (zzcvt != 0) {
                        System.arraycopy(this.zzxa, 0, obj, 0, zzcvt);
                    }
                    while (zzcvt < obj.length - 1) {
                        obj[zzcvt] = new zzbk();
                        com_google_android_gms_internal_zzfjj.zza(obj[zzcvt]);
                        com_google_android_gms_internal_zzfjj.zzcvt();
                        zzcvt++;
                    }
                    obj[zzcvt] = new zzbk();
                    com_google_android_gms_internal_zzfjj.zza(obj[zzcvt]);
                    this.zzxa = obj;
                    continue;
                case 58:
                    zzb = zzfjv.zzb(com_google_android_gms_internal_zzfjj, 58);
                    zzcvt = this.zzxb == null ? 0 : this.zzxb.length;
                    obj = new zzbp[(zzb + zzcvt)];
                    if (zzcvt != 0) {
                        System.arraycopy(this.zzxb, 0, obj, 0, zzcvt);
                    }
                    while (zzcvt < obj.length - 1) {
                        obj[zzcvt] = new zzbp();
                        com_google_android_gms_internal_zzfjj.zza(obj[zzcvt]);
                        com_google_android_gms_internal_zzfjj.zzcvt();
                        zzcvt++;
                    }
                    obj[zzcvt] = new zzbp();
                    com_google_android_gms_internal_zzfjj.zza(obj[zzcvt]);
                    this.zzxb = obj;
                    continue;
                case 74:
                    this.zzxc = com_google_android_gms_internal_zzfjj.readString();
                    continue;
                case 82:
                    this.zzxd = com_google_android_gms_internal_zzfjj.readString();
                    continue;
                case 98:
                    this.zzxe = com_google_android_gms_internal_zzfjj.readString();
                    continue;
                case 106:
                    this.version = com_google_android_gms_internal_zzfjj.readString();
                    continue;
                case 114:
                    if (this.zzxf == null) {
                        this.zzxf = new zzbj();
                    }
                    com_google_android_gms_internal_zzfjj.zza(this.zzxf);
                    continue;
                case 125:
                    this.zzxg = Float.intBitsToFloat(com_google_android_gms_internal_zzfjj.zzcwo());
                    continue;
                case Constants.DEFAULT_HOT_WATER_TEMPERATURE_FAHRENHEIT /*130*/:
                    zzb = zzfjv.zzb(com_google_android_gms_internal_zzfjj, Constants.DEFAULT_HOT_WATER_TEMPERATURE_FAHRENHEIT);
                    zzcvt = this.zzxi == null ? 0 : this.zzxi.length;
                    obj = new String[(zzb + zzcvt)];
                    if (zzcvt != 0) {
                        System.arraycopy(this.zzxi, 0, obj, 0, zzcvt);
                    }
                    while (zzcvt < obj.length - 1) {
                        obj[zzcvt] = com_google_android_gms_internal_zzfjj.readString();
                        com_google_android_gms_internal_zzfjj.zzcvt();
                        zzcvt++;
                    }
                    obj[zzcvt] = com_google_android_gms_internal_zzfjj.readString();
                    this.zzxi = obj;
                    continue;
                case 136:
                    this.zzxj = com_google_android_gms_internal_zzfjj.zzcwi();
                    continue;
                case 144:
                    this.zzxh = com_google_android_gms_internal_zzfjj.zzcvz();
                    continue;
                case 154:
                    zzb = zzfjv.zzb(com_google_android_gms_internal_zzfjj, 154);
                    zzcvt = this.zzwu == null ? 0 : this.zzwu.length;
                    obj = new String[(zzb + zzcvt)];
                    if (zzcvt != 0) {
                        System.arraycopy(this.zzwu, 0, obj, 0, zzcvt);
                    }
                    while (zzcvt < obj.length - 1) {
                        obj[zzcvt] = com_google_android_gms_internal_zzfjj.readString();
                        com_google_android_gms_internal_zzfjj.zzcvt();
                        zzcvt++;
                    }
                    obj[zzcvt] = com_google_android_gms_internal_zzfjj.readString();
                    this.zzwu = obj;
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
        if (this.zzwv != null && this.zzwv.length > 0) {
            for (String str : this.zzwv) {
                if (str != null) {
                    com_google_android_gms_internal_zzfjk.zzn(1, str);
                }
            }
        }
        if (this.zzww != null && this.zzww.length > 0) {
            for (zzfjs com_google_android_gms_internal_zzfjs : this.zzww) {
                if (com_google_android_gms_internal_zzfjs != null) {
                    com_google_android_gms_internal_zzfjk.zza(2, com_google_android_gms_internal_zzfjs);
                }
            }
        }
        if (this.zzwx != null && this.zzwx.length > 0) {
            for (zzfjs com_google_android_gms_internal_zzfjs2 : this.zzwx) {
                if (com_google_android_gms_internal_zzfjs2 != null) {
                    com_google_android_gms_internal_zzfjk.zza(3, com_google_android_gms_internal_zzfjs2);
                }
            }
        }
        if (this.zzwy != null && this.zzwy.length > 0) {
            for (zzfjs com_google_android_gms_internal_zzfjs22 : this.zzwy) {
                if (com_google_android_gms_internal_zzfjs22 != null) {
                    com_google_android_gms_internal_zzfjk.zza(4, com_google_android_gms_internal_zzfjs22);
                }
            }
        }
        if (this.zzwz != null && this.zzwz.length > 0) {
            for (zzfjs com_google_android_gms_internal_zzfjs222 : this.zzwz) {
                if (com_google_android_gms_internal_zzfjs222 != null) {
                    com_google_android_gms_internal_zzfjk.zza(5, com_google_android_gms_internal_zzfjs222);
                }
            }
        }
        if (this.zzxa != null && this.zzxa.length > 0) {
            for (zzfjs com_google_android_gms_internal_zzfjs2222 : this.zzxa) {
                if (com_google_android_gms_internal_zzfjs2222 != null) {
                    com_google_android_gms_internal_zzfjk.zza(6, com_google_android_gms_internal_zzfjs2222);
                }
            }
        }
        if (this.zzxb != null && this.zzxb.length > 0) {
            for (zzfjs com_google_android_gms_internal_zzfjs22222 : this.zzxb) {
                if (com_google_android_gms_internal_zzfjs22222 != null) {
                    com_google_android_gms_internal_zzfjk.zza(7, com_google_android_gms_internal_zzfjs22222);
                }
            }
        }
        if (!(this.zzxc == null || this.zzxc.equals(""))) {
            com_google_android_gms_internal_zzfjk.zzn(9, this.zzxc);
        }
        if (!(this.zzxd == null || this.zzxd.equals(""))) {
            com_google_android_gms_internal_zzfjk.zzn(10, this.zzxd);
        }
        if (!(this.zzxe == null || this.zzxe.equals("0"))) {
            com_google_android_gms_internal_zzfjk.zzn(12, this.zzxe);
        }
        if (!(this.version == null || this.version.equals(""))) {
            com_google_android_gms_internal_zzfjk.zzn(13, this.version);
        }
        if (this.zzxf != null) {
            com_google_android_gms_internal_zzfjk.zza(14, this.zzxf);
        }
        if (Float.floatToIntBits(this.zzxg) != Float.floatToIntBits(0.0f)) {
            com_google_android_gms_internal_zzfjk.zzc(15, this.zzxg);
        }
        if (this.zzxi != null && this.zzxi.length > 0) {
            for (String str2 : this.zzxi) {
                if (str2 != null) {
                    com_google_android_gms_internal_zzfjk.zzn(16, str2);
                }
            }
        }
        if (this.zzxj != 0) {
            com_google_android_gms_internal_zzfjk.zzaa(17, this.zzxj);
        }
        if (this.zzxh) {
            com_google_android_gms_internal_zzfjk.zzl(18, this.zzxh);
        }
        if (this.zzwu != null && this.zzwu.length > 0) {
            while (i < this.zzwu.length) {
                String str3 = this.zzwu[i];
                if (str3 != null) {
                    com_google_android_gms_internal_zzfjk.zzn(19, str3);
                }
                i++;
            }
        }
        super.zza(com_google_android_gms_internal_zzfjk);
    }

    protected final int zzq() {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        int zzq = super.zzq();
        if (this.zzwv == null || this.zzwv.length <= 0) {
            i = zzq;
        } else {
            i2 = 0;
            i3 = 0;
            for (String str : this.zzwv) {
                if (str != null) {
                    i3++;
                    i2 += zzfjk.zztt(str);
                }
            }
            i = (zzq + i2) + (i3 * 1);
        }
        if (this.zzww != null && this.zzww.length > 0) {
            i2 = i;
            for (zzfjs com_google_android_gms_internal_zzfjs : this.zzww) {
                if (com_google_android_gms_internal_zzfjs != null) {
                    i2 += zzfjk.zzb(2, com_google_android_gms_internal_zzfjs);
                }
            }
            i = i2;
        }
        if (this.zzwx != null && this.zzwx.length > 0) {
            i2 = i;
            for (zzfjs com_google_android_gms_internal_zzfjs2 : this.zzwx) {
                if (com_google_android_gms_internal_zzfjs2 != null) {
                    i2 += zzfjk.zzb(3, com_google_android_gms_internal_zzfjs2);
                }
            }
            i = i2;
        }
        if (this.zzwy != null && this.zzwy.length > 0) {
            i2 = i;
            for (zzfjs com_google_android_gms_internal_zzfjs22 : this.zzwy) {
                if (com_google_android_gms_internal_zzfjs22 != null) {
                    i2 += zzfjk.zzb(4, com_google_android_gms_internal_zzfjs22);
                }
            }
            i = i2;
        }
        if (this.zzwz != null && this.zzwz.length > 0) {
            i2 = i;
            for (zzfjs com_google_android_gms_internal_zzfjs222 : this.zzwz) {
                if (com_google_android_gms_internal_zzfjs222 != null) {
                    i2 += zzfjk.zzb(5, com_google_android_gms_internal_zzfjs222);
                }
            }
            i = i2;
        }
        if (this.zzxa != null && this.zzxa.length > 0) {
            i2 = i;
            for (zzfjs com_google_android_gms_internal_zzfjs2222 : this.zzxa) {
                if (com_google_android_gms_internal_zzfjs2222 != null) {
                    i2 += zzfjk.zzb(6, com_google_android_gms_internal_zzfjs2222);
                }
            }
            i = i2;
        }
        if (this.zzxb != null && this.zzxb.length > 0) {
            i2 = i;
            for (zzfjs com_google_android_gms_internal_zzfjs22222 : this.zzxb) {
                if (com_google_android_gms_internal_zzfjs22222 != null) {
                    i2 += zzfjk.zzb(7, com_google_android_gms_internal_zzfjs22222);
                }
            }
            i = i2;
        }
        if (!(this.zzxc == null || this.zzxc.equals(""))) {
            i += zzfjk.zzo(9, this.zzxc);
        }
        if (!(this.zzxd == null || this.zzxd.equals(""))) {
            i += zzfjk.zzo(10, this.zzxd);
        }
        if (!(this.zzxe == null || this.zzxe.equals("0"))) {
            i += zzfjk.zzo(12, this.zzxe);
        }
        if (!(this.version == null || this.version.equals(""))) {
            i += zzfjk.zzo(13, this.version);
        }
        if (this.zzxf != null) {
            i += zzfjk.zzb(14, this.zzxf);
        }
        if (Float.floatToIntBits(this.zzxg) != Float.floatToIntBits(0.0f)) {
            i += zzfjk.zzlg(15) + 4;
        }
        if (this.zzxi != null && this.zzxi.length > 0) {
            i3 = 0;
            zzq = 0;
            for (String str2 : this.zzxi) {
                if (str2 != null) {
                    zzq++;
                    i3 += zzfjk.zztt(str2);
                }
            }
            i = (i + i3) + (zzq * 2);
        }
        if (this.zzxj != 0) {
            i += zzfjk.zzad(17, this.zzxj);
        }
        if (this.zzxh) {
            i += zzfjk.zzlg(18) + 1;
        }
        if (this.zzwu == null || this.zzwu.length <= 0) {
            return i;
        }
        i2 = 0;
        i3 = 0;
        while (i4 < this.zzwu.length) {
            String str3 = this.zzwu[i4];
            if (str3 != null) {
                i3++;
                i2 += zzfjk.zztt(str3);
            }
            i4++;
        }
        return (i + i2) + (i3 * 2);
    }
}
