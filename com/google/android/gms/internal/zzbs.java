package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbs extends zzfjm<zzbs> {
    private static volatile zzbs[] zzyk;
    public String string;
    public int type;
    public zzbs[] zzyl;
    public zzbs[] zzym;
    public zzbs[] zzyn;
    public String zzyo;
    public String zzyp;
    public long zzyq;
    public boolean zzyr;
    public zzbs[] zzys;
    public int[] zzyt;
    public boolean zzyu;

    public zzbs() {
        this.type = 1;
        this.string = "";
        this.zzyl = zzx();
        this.zzym = zzx();
        this.zzyn = zzx();
        this.zzyo = "";
        this.zzyp = "";
        this.zzyq = 0;
        this.zzyr = false;
        this.zzys = zzx();
        this.zzyt = zzfjv.zzpnp;
        this.zzyu = false;
        this.zzpnc = null;
        this.zzpfd = -1;
    }

    private static int zze(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
                return i;
            default:
                throw new IllegalArgumentException(i + " is not a valid enum Escaping");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.zzbs zzf(com.google.android.gms.internal.zzfjj r10) throws java.io.IOException {
        /*
        r9 = this;
        r8 = 80;
        r1 = 0;
    L_0x0003:
        r3 = r10.zzcvt();
        switch(r3) {
            case 0: goto L_0x0010;
            case 8: goto L_0x0011;
            case 18: goto L_0x0043;
            case 26: goto L_0x004a;
            case 34: goto L_0x008a;
            case 42: goto L_0x00ca;
            case 50: goto L_0x010a;
            case 58: goto L_0x0112;
            case 64: goto L_0x011a;
            case 72: goto L_0x0122;
            case 80: goto L_0x012a;
            case 82: goto L_0x017a;
            case 90: goto L_0x01d5;
            case 96: goto L_0x0215;
            default: goto L_0x000a;
        };
    L_0x000a:
        r0 = super.zza(r10, r3);
        if (r0 != 0) goto L_0x0003;
    L_0x0010:
        return r9;
    L_0x0011:
        r0 = r10.getPosition();
        r2 = r10.zzcwi();	 Catch:{ IllegalArgumentException -> 0x0038 }
        switch(r2) {
            case 1: goto L_0x0040;
            case 2: goto L_0x0040;
            case 3: goto L_0x0040;
            case 4: goto L_0x0040;
            case 5: goto L_0x0040;
            case 6: goto L_0x0040;
            case 7: goto L_0x0040;
            case 8: goto L_0x0040;
            default: goto L_0x001c;
        };	 Catch:{ IllegalArgumentException -> 0x0038 }
    L_0x001c:
        r4 = new java.lang.IllegalArgumentException;	 Catch:{ IllegalArgumentException -> 0x0038 }
        r5 = 36;
        r6 = new java.lang.StringBuilder;	 Catch:{ IllegalArgumentException -> 0x0038 }
        r6.<init>(r5);	 Catch:{ IllegalArgumentException -> 0x0038 }
        r2 = r6.append(r2);	 Catch:{ IllegalArgumentException -> 0x0038 }
        r5 = " is not a valid enum Type";
        r2 = r2.append(r5);	 Catch:{ IllegalArgumentException -> 0x0038 }
        r2 = r2.toString();	 Catch:{ IllegalArgumentException -> 0x0038 }
        r4.<init>(r2);	 Catch:{ IllegalArgumentException -> 0x0038 }
        throw r4;	 Catch:{ IllegalArgumentException -> 0x0038 }
    L_0x0038:
        r2 = move-exception;
        r10.zzmg(r0);
        r9.zza(r10, r3);
        goto L_0x0003;
    L_0x0040:
        r9.type = r2;	 Catch:{ IllegalArgumentException -> 0x0038 }
        goto L_0x0003;
    L_0x0043:
        r0 = r10.readString();
        r9.string = r0;
        goto L_0x0003;
    L_0x004a:
        r0 = 26;
        r2 = com.google.android.gms.internal.zzfjv.zzb(r10, r0);
        r0 = r9.zzyl;
        if (r0 != 0) goto L_0x0076;
    L_0x0054:
        r0 = r1;
    L_0x0055:
        r2 = r2 + r0;
        r2 = new com.google.android.gms.internal.zzbs[r2];
        if (r0 == 0) goto L_0x005f;
    L_0x005a:
        r3 = r9.zzyl;
        java.lang.System.arraycopy(r3, r1, r2, r1, r0);
    L_0x005f:
        r3 = r2.length;
        r3 = r3 + -1;
        if (r0 >= r3) goto L_0x007a;
    L_0x0064:
        r3 = new com.google.android.gms.internal.zzbs;
        r3.<init>();
        r2[r0] = r3;
        r3 = r2[r0];
        r10.zza(r3);
        r10.zzcvt();
        r0 = r0 + 1;
        goto L_0x005f;
    L_0x0076:
        r0 = r9.zzyl;
        r0 = r0.length;
        goto L_0x0055;
    L_0x007a:
        r3 = new com.google.android.gms.internal.zzbs;
        r3.<init>();
        r2[r0] = r3;
        r0 = r2[r0];
        r10.zza(r0);
        r9.zzyl = r2;
        goto L_0x0003;
    L_0x008a:
        r0 = 34;
        r2 = com.google.android.gms.internal.zzfjv.zzb(r10, r0);
        r0 = r9.zzym;
        if (r0 != 0) goto L_0x00b6;
    L_0x0094:
        r0 = r1;
    L_0x0095:
        r2 = r2 + r0;
        r2 = new com.google.android.gms.internal.zzbs[r2];
        if (r0 == 0) goto L_0x009f;
    L_0x009a:
        r3 = r9.zzym;
        java.lang.System.arraycopy(r3, r1, r2, r1, r0);
    L_0x009f:
        r3 = r2.length;
        r3 = r3 + -1;
        if (r0 >= r3) goto L_0x00ba;
    L_0x00a4:
        r3 = new com.google.android.gms.internal.zzbs;
        r3.<init>();
        r2[r0] = r3;
        r3 = r2[r0];
        r10.zza(r3);
        r10.zzcvt();
        r0 = r0 + 1;
        goto L_0x009f;
    L_0x00b6:
        r0 = r9.zzym;
        r0 = r0.length;
        goto L_0x0095;
    L_0x00ba:
        r3 = new com.google.android.gms.internal.zzbs;
        r3.<init>();
        r2[r0] = r3;
        r0 = r2[r0];
        r10.zza(r0);
        r9.zzym = r2;
        goto L_0x0003;
    L_0x00ca:
        r0 = 42;
        r2 = com.google.android.gms.internal.zzfjv.zzb(r10, r0);
        r0 = r9.zzyn;
        if (r0 != 0) goto L_0x00f6;
    L_0x00d4:
        r0 = r1;
    L_0x00d5:
        r2 = r2 + r0;
        r2 = new com.google.android.gms.internal.zzbs[r2];
        if (r0 == 0) goto L_0x00df;
    L_0x00da:
        r3 = r9.zzyn;
        java.lang.System.arraycopy(r3, r1, r2, r1, r0);
    L_0x00df:
        r3 = r2.length;
        r3 = r3 + -1;
        if (r0 >= r3) goto L_0x00fa;
    L_0x00e4:
        r3 = new com.google.android.gms.internal.zzbs;
        r3.<init>();
        r2[r0] = r3;
        r3 = r2[r0];
        r10.zza(r3);
        r10.zzcvt();
        r0 = r0 + 1;
        goto L_0x00df;
    L_0x00f6:
        r0 = r9.zzyn;
        r0 = r0.length;
        goto L_0x00d5;
    L_0x00fa:
        r3 = new com.google.android.gms.internal.zzbs;
        r3.<init>();
        r2[r0] = r3;
        r0 = r2[r0];
        r10.zza(r0);
        r9.zzyn = r2;
        goto L_0x0003;
    L_0x010a:
        r0 = r10.readString();
        r9.zzyo = r0;
        goto L_0x0003;
    L_0x0112:
        r0 = r10.readString();
        r9.zzyp = r0;
        goto L_0x0003;
    L_0x011a:
        r2 = r10.zzcwn();
        r9.zzyq = r2;
        goto L_0x0003;
    L_0x0122:
        r0 = r10.zzcvz();
        r9.zzyu = r0;
        goto L_0x0003;
    L_0x012a:
        r4 = com.google.android.gms.internal.zzfjv.zzb(r10, r8);
        r5 = new int[r4];
        r2 = r1;
        r0 = r1;
    L_0x0132:
        if (r2 >= r4) goto L_0x0154;
    L_0x0134:
        if (r2 == 0) goto L_0x0139;
    L_0x0136:
        r10.zzcvt();
    L_0x0139:
        r6 = r10.getPosition();
        r7 = r10.zzcwi();	 Catch:{ IllegalArgumentException -> 0x014c }
        r7 = zze(r7);	 Catch:{ IllegalArgumentException -> 0x014c }
        r5[r0] = r7;	 Catch:{ IllegalArgumentException -> 0x014c }
        r0 = r0 + 1;
    L_0x0149:
        r2 = r2 + 1;
        goto L_0x0132;
    L_0x014c:
        r7 = move-exception;
        r10.zzmg(r6);
        r9.zza(r10, r3);
        goto L_0x0149;
    L_0x0154:
        if (r0 == 0) goto L_0x0003;
    L_0x0156:
        r2 = r9.zzyt;
        if (r2 != 0) goto L_0x0164;
    L_0x015a:
        r2 = r1;
    L_0x015b:
        if (r2 != 0) goto L_0x0168;
    L_0x015d:
        r3 = r5.length;
        if (r0 != r3) goto L_0x0168;
    L_0x0160:
        r9.zzyt = r5;
        goto L_0x0003;
    L_0x0164:
        r2 = r9.zzyt;
        r2 = r2.length;
        goto L_0x015b;
    L_0x0168:
        r3 = r2 + r0;
        r3 = new int[r3];
        if (r2 == 0) goto L_0x0173;
    L_0x016e:
        r4 = r9.zzyt;
        java.lang.System.arraycopy(r4, r1, r3, r1, r2);
    L_0x0173:
        java.lang.System.arraycopy(r5, r1, r3, r2, r0);
        r9.zzyt = r3;
        goto L_0x0003;
    L_0x017a:
        r0 = r10.zzcwi();
        r3 = r10.zzks(r0);
        r2 = r10.getPosition();
        r0 = r1;
    L_0x0187:
        r4 = r10.zzcwk();
        if (r4 <= 0) goto L_0x0197;
    L_0x018d:
        r4 = r10.zzcwi();	 Catch:{ IllegalArgumentException -> 0x021d }
        zze(r4);	 Catch:{ IllegalArgumentException -> 0x021d }
        r0 = r0 + 1;
        goto L_0x0187;
    L_0x0197:
        if (r0 == 0) goto L_0x01d0;
    L_0x0199:
        r10.zzmg(r2);
        r2 = r9.zzyt;
        if (r2 != 0) goto L_0x01c2;
    L_0x01a0:
        r2 = r1;
    L_0x01a1:
        r0 = r0 + r2;
        r0 = new int[r0];
        if (r2 == 0) goto L_0x01ab;
    L_0x01a6:
        r4 = r9.zzyt;
        java.lang.System.arraycopy(r4, r1, r0, r1, r2);
    L_0x01ab:
        r4 = r10.zzcwk();
        if (r4 <= 0) goto L_0x01ce;
    L_0x01b1:
        r4 = r10.getPosition();
        r5 = r10.zzcwi();	 Catch:{ IllegalArgumentException -> 0x01c6 }
        r5 = zze(r5);	 Catch:{ IllegalArgumentException -> 0x01c6 }
        r0[r2] = r5;	 Catch:{ IllegalArgumentException -> 0x01c6 }
        r2 = r2 + 1;
        goto L_0x01ab;
    L_0x01c2:
        r2 = r9.zzyt;
        r2 = r2.length;
        goto L_0x01a1;
    L_0x01c6:
        r5 = move-exception;
        r10.zzmg(r4);
        r9.zza(r10, r8);
        goto L_0x01ab;
    L_0x01ce:
        r9.zzyt = r0;
    L_0x01d0:
        r10.zzkt(r3);
        goto L_0x0003;
    L_0x01d5:
        r0 = 90;
        r2 = com.google.android.gms.internal.zzfjv.zzb(r10, r0);
        r0 = r9.zzys;
        if (r0 != 0) goto L_0x0201;
    L_0x01df:
        r0 = r1;
    L_0x01e0:
        r2 = r2 + r0;
        r2 = new com.google.android.gms.internal.zzbs[r2];
        if (r0 == 0) goto L_0x01ea;
    L_0x01e5:
        r3 = r9.zzys;
        java.lang.System.arraycopy(r3, r1, r2, r1, r0);
    L_0x01ea:
        r3 = r2.length;
        r3 = r3 + -1;
        if (r0 >= r3) goto L_0x0205;
    L_0x01ef:
        r3 = new com.google.android.gms.internal.zzbs;
        r3.<init>();
        r2[r0] = r3;
        r3 = r2[r0];
        r10.zza(r3);
        r10.zzcvt();
        r0 = r0 + 1;
        goto L_0x01ea;
    L_0x0201:
        r0 = r9.zzys;
        r0 = r0.length;
        goto L_0x01e0;
    L_0x0205:
        r3 = new com.google.android.gms.internal.zzbs;
        r3.<init>();
        r2[r0] = r3;
        r0 = r2[r0];
        r10.zza(r0);
        r9.zzys = r2;
        goto L_0x0003;
    L_0x0215:
        r0 = r10.zzcvz();
        r9.zzyr = r0;
        goto L_0x0003;
    L_0x021d:
        r4 = move-exception;
        goto L_0x0187;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbs.zzf(com.google.android.gms.internal.zzfjj):com.google.android.gms.internal.zzbs");
    }

    public static zzbs[] zzx() {
        if (zzyk == null) {
            synchronized (zzfjq.zzpnk) {
                if (zzyk == null) {
                    zzyk = new zzbs[0];
                }
            }
        }
        return zzyk;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbs)) {
            return false;
        }
        zzbs com_google_android_gms_internal_zzbs = (zzbs) obj;
        if (this.type != com_google_android_gms_internal_zzbs.type) {
            return false;
        }
        if (this.string == null) {
            if (com_google_android_gms_internal_zzbs.string != null) {
                return false;
            }
        } else if (!this.string.equals(com_google_android_gms_internal_zzbs.string)) {
            return false;
        }
        if (!zzfjq.equals(this.zzyl, com_google_android_gms_internal_zzbs.zzyl)) {
            return false;
        }
        if (!zzfjq.equals(this.zzym, com_google_android_gms_internal_zzbs.zzym)) {
            return false;
        }
        if (!zzfjq.equals(this.zzyn, com_google_android_gms_internal_zzbs.zzyn)) {
            return false;
        }
        if (this.zzyo == null) {
            if (com_google_android_gms_internal_zzbs.zzyo != null) {
                return false;
            }
        } else if (!this.zzyo.equals(com_google_android_gms_internal_zzbs.zzyo)) {
            return false;
        }
        if (this.zzyp == null) {
            if (com_google_android_gms_internal_zzbs.zzyp != null) {
                return false;
            }
        } else if (!this.zzyp.equals(com_google_android_gms_internal_zzbs.zzyp)) {
            return false;
        }
        return this.zzyq != com_google_android_gms_internal_zzbs.zzyq ? false : this.zzyr != com_google_android_gms_internal_zzbs.zzyr ? false : !zzfjq.equals(this.zzys, com_google_android_gms_internal_zzbs.zzys) ? false : !zzfjq.equals(this.zzyt, com_google_android_gms_internal_zzbs.zzyt) ? false : this.zzyu != com_google_android_gms_internal_zzbs.zzyu ? false : (this.zzpnc == null || this.zzpnc.isEmpty()) ? com_google_android_gms_internal_zzbs.zzpnc == null || com_google_android_gms_internal_zzbs.zzpnc.isEmpty() : this.zzpnc.equals(com_google_android_gms_internal_zzbs.zzpnc);
    }

    public final int hashCode() {
        int i = 1231;
        int i2 = 0;
        int hashCode = ((((((this.zzyr ? 1231 : 1237) + (((((this.zzyp == null ? 0 : this.zzyp.hashCode()) + (((this.zzyo == null ? 0 : this.zzyo.hashCode()) + (((((((((this.string == null ? 0 : this.string.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + this.type) * 31)) * 31) + zzfjq.hashCode(this.zzyl)) * 31) + zzfjq.hashCode(this.zzym)) * 31) + zzfjq.hashCode(this.zzyn)) * 31)) * 31)) * 31) + ((int) (this.zzyq ^ (this.zzyq >>> 32)))) * 31)) * 31) + zzfjq.hashCode(this.zzys)) * 31) + zzfjq.hashCode(this.zzyt)) * 31;
        if (!this.zzyu) {
            i = 1237;
        }
        hashCode = (hashCode + i) * 31;
        if (!(this.zzpnc == null || this.zzpnc.isEmpty())) {
            i2 = this.zzpnc.hashCode();
        }
        return hashCode + i2;
    }

    public final /* synthetic */ zzfjs zza(zzfjj com_google_android_gms_internal_zzfjj) throws IOException {
        return zzf(com_google_android_gms_internal_zzfjj);
    }

    public final void zza(zzfjk com_google_android_gms_internal_zzfjk) throws IOException {
        int i = 0;
        com_google_android_gms_internal_zzfjk.zzaa(1, this.type);
        if (!(this.string == null || this.string.equals(""))) {
            com_google_android_gms_internal_zzfjk.zzn(2, this.string);
        }
        if (this.zzyl != null && this.zzyl.length > 0) {
            for (zzfjs com_google_android_gms_internal_zzfjs : this.zzyl) {
                if (com_google_android_gms_internal_zzfjs != null) {
                    com_google_android_gms_internal_zzfjk.zza(3, com_google_android_gms_internal_zzfjs);
                }
            }
        }
        if (this.zzym != null && this.zzym.length > 0) {
            for (zzfjs com_google_android_gms_internal_zzfjs2 : this.zzym) {
                if (com_google_android_gms_internal_zzfjs2 != null) {
                    com_google_android_gms_internal_zzfjk.zza(4, com_google_android_gms_internal_zzfjs2);
                }
            }
        }
        if (this.zzyn != null && this.zzyn.length > 0) {
            for (zzfjs com_google_android_gms_internal_zzfjs22 : this.zzyn) {
                if (com_google_android_gms_internal_zzfjs22 != null) {
                    com_google_android_gms_internal_zzfjk.zza(5, com_google_android_gms_internal_zzfjs22);
                }
            }
        }
        if (!(this.zzyo == null || this.zzyo.equals(""))) {
            com_google_android_gms_internal_zzfjk.zzn(6, this.zzyo);
        }
        if (!(this.zzyp == null || this.zzyp.equals(""))) {
            com_google_android_gms_internal_zzfjk.zzn(7, this.zzyp);
        }
        if (this.zzyq != 0) {
            com_google_android_gms_internal_zzfjk.zzf(8, this.zzyq);
        }
        if (this.zzyu) {
            com_google_android_gms_internal_zzfjk.zzl(9, this.zzyu);
        }
        if (this.zzyt != null && this.zzyt.length > 0) {
            for (int zzaa : this.zzyt) {
                com_google_android_gms_internal_zzfjk.zzaa(10, zzaa);
            }
        }
        if (this.zzys != null && this.zzys.length > 0) {
            while (i < this.zzys.length) {
                zzfjs com_google_android_gms_internal_zzfjs3 = this.zzys[i];
                if (com_google_android_gms_internal_zzfjs3 != null) {
                    com_google_android_gms_internal_zzfjk.zza(11, com_google_android_gms_internal_zzfjs3);
                }
                i++;
            }
        }
        if (this.zzyr) {
            com_google_android_gms_internal_zzfjk.zzl(12, this.zzyr);
        }
        super.zza(com_google_android_gms_internal_zzfjk);
    }

    protected final int zzq() {
        int i;
        int i2 = 0;
        int zzq = super.zzq() + zzfjk.zzad(1, this.type);
        if (!(this.string == null || this.string.equals(""))) {
            zzq += zzfjk.zzo(2, this.string);
        }
        if (this.zzyl != null && this.zzyl.length > 0) {
            i = zzq;
            for (zzfjs com_google_android_gms_internal_zzfjs : this.zzyl) {
                if (com_google_android_gms_internal_zzfjs != null) {
                    i += zzfjk.zzb(3, com_google_android_gms_internal_zzfjs);
                }
            }
            zzq = i;
        }
        if (this.zzym != null && this.zzym.length > 0) {
            i = zzq;
            for (zzfjs com_google_android_gms_internal_zzfjs2 : this.zzym) {
                if (com_google_android_gms_internal_zzfjs2 != null) {
                    i += zzfjk.zzb(4, com_google_android_gms_internal_zzfjs2);
                }
            }
            zzq = i;
        }
        if (this.zzyn != null && this.zzyn.length > 0) {
            i = zzq;
            for (zzfjs com_google_android_gms_internal_zzfjs22 : this.zzyn) {
                if (com_google_android_gms_internal_zzfjs22 != null) {
                    i += zzfjk.zzb(5, com_google_android_gms_internal_zzfjs22);
                }
            }
            zzq = i;
        }
        if (!(this.zzyo == null || this.zzyo.equals(""))) {
            zzq += zzfjk.zzo(6, this.zzyo);
        }
        if (!(this.zzyp == null || this.zzyp.equals(""))) {
            zzq += zzfjk.zzo(7, this.zzyp);
        }
        if (this.zzyq != 0) {
            zzq += zzfjk.zzc(8, this.zzyq);
        }
        if (this.zzyu) {
            zzq += zzfjk.zzlg(9) + 1;
        }
        if (this.zzyt != null && this.zzyt.length > 0) {
            int i3 = 0;
            for (int zzlh : this.zzyt) {
                i3 += zzfjk.zzlh(zzlh);
            }
            zzq = (zzq + i3) + (this.zzyt.length * 1);
        }
        if (this.zzys != null && this.zzys.length > 0) {
            while (i2 < this.zzys.length) {
                zzfjs com_google_android_gms_internal_zzfjs3 = this.zzys[i2];
                if (com_google_android_gms_internal_zzfjs3 != null) {
                    zzq += zzfjk.zzb(11, com_google_android_gms_internal_zzfjs3);
                }
                i2++;
            }
        }
        return this.zzyr ? zzq + (zzfjk.zzlg(12) + 1) : zzq;
    }
}
