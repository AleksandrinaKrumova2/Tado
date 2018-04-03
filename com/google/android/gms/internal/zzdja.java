package com.google.android.gms.internal;

import com.google.android.gms.internal.zzbi.zza;
import com.google.android.gms.tagmanager.zzdj;
import com.google.android.gms.tagmanager.zzgk;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class zzdja {
    private static zzbs zza(int i, zzbo com_google_android_gms_internal_zzbo, zzbs[] com_google_android_gms_internal_zzbsArr, Set<Integer> set) throws zzdji {
        int i2 = 0;
        if (set.contains(Integer.valueOf(i))) {
            String valueOf = String.valueOf(set);
            zzmu(new StringBuilder(String.valueOf(valueOf).length() + 90).append("Value cycle detected.  Current value reference: ").append(i).append(".  Previous value references: ").append(valueOf).append(".").toString());
        }
        zzbs com_google_android_gms_internal_zzbs = (zzbs) zza(com_google_android_gms_internal_zzbo.zzww, i, "values");
        if (com_google_android_gms_internal_zzbsArr[i] != null) {
            return com_google_android_gms_internal_zzbsArr[i];
        }
        zzbs com_google_android_gms_internal_zzbs2 = null;
        set.add(Integer.valueOf(i));
        zza zzk;
        int[] iArr;
        int length;
        int i3;
        int i4;
        switch (com_google_android_gms_internal_zzbs.type) {
            case 1:
            case 5:
            case 6:
            case 8:
                com_google_android_gms_internal_zzbs2 = com_google_android_gms_internal_zzbs;
                break;
            case 2:
                zzk = zzk(com_google_android_gms_internal_zzbs);
                com_google_android_gms_internal_zzbs2 = zzj(com_google_android_gms_internal_zzbs);
                com_google_android_gms_internal_zzbs2.zzyl = new zzbs[zzk.zzxx.length];
                iArr = zzk.zzxx;
                length = iArr.length;
                i3 = 0;
                while (i2 < length) {
                    i4 = i3 + 1;
                    com_google_android_gms_internal_zzbs2.zzyl[i3] = zza(iArr[i2], com_google_android_gms_internal_zzbo, com_google_android_gms_internal_zzbsArr, (Set) set);
                    i2++;
                    i3 = i4;
                }
                break;
            case 3:
                com_google_android_gms_internal_zzbs2 = zzj(com_google_android_gms_internal_zzbs);
                zza zzk2 = zzk(com_google_android_gms_internal_zzbs);
                if (zzk2.zzxy.length != zzk2.zzxz.length) {
                    i3 = zzk2.zzxy.length;
                    zzmu("Uneven map keys (" + i3 + ") and map values (" + zzk2.zzxz.length + ")");
                }
                com_google_android_gms_internal_zzbs2.zzym = new zzbs[zzk2.zzxy.length];
                com_google_android_gms_internal_zzbs2.zzyn = new zzbs[zzk2.zzxy.length];
                int[] iArr2 = zzk2.zzxy;
                int length2 = iArr2.length;
                i3 = 0;
                i4 = 0;
                while (i3 < length2) {
                    int i5 = i4 + 1;
                    com_google_android_gms_internal_zzbs2.zzym[i4] = zza(iArr2[i3], com_google_android_gms_internal_zzbo, com_google_android_gms_internal_zzbsArr, (Set) set);
                    i3++;
                    i4 = i5;
                }
                iArr = zzk2.zzxz;
                length = iArr.length;
                i3 = 0;
                while (i2 < length) {
                    i4 = i3 + 1;
                    com_google_android_gms_internal_zzbs2.zzyn[i3] = zza(iArr[i2], com_google_android_gms_internal_zzbo, com_google_android_gms_internal_zzbsArr, (Set) set);
                    i2++;
                    i3 = i4;
                }
                break;
            case 4:
                com_google_android_gms_internal_zzbs2 = zzj(com_google_android_gms_internal_zzbs);
                com_google_android_gms_internal_zzbs2.zzyo = zzgk.zzb(zza(zzk(com_google_android_gms_internal_zzbs).zzyc, com_google_android_gms_internal_zzbo, com_google_android_gms_internal_zzbsArr, (Set) set));
                break;
            case 7:
                com_google_android_gms_internal_zzbs2 = zzj(com_google_android_gms_internal_zzbs);
                zzk = zzk(com_google_android_gms_internal_zzbs);
                com_google_android_gms_internal_zzbs2.zzys = new zzbs[zzk.zzyb.length];
                iArr = zzk.zzyb;
                length = iArr.length;
                i3 = 0;
                while (i2 < length) {
                    i4 = i3 + 1;
                    com_google_android_gms_internal_zzbs2.zzys[i3] = zza(iArr[i2], com_google_android_gms_internal_zzbo, com_google_android_gms_internal_zzbsArr, (Set) set);
                    i2++;
                    i3 = i4;
                }
                break;
        }
        if (com_google_android_gms_internal_zzbs2 == null) {
            valueOf = String.valueOf(com_google_android_gms_internal_zzbs);
            zzmu(new StringBuilder(String.valueOf(valueOf).length() + 15).append("Invalid value: ").append(valueOf).toString());
        }
        com_google_android_gms_internal_zzbsArr[i] = com_google_android_gms_internal_zzbs2;
        set.remove(Integer.valueOf(i));
        return com_google_android_gms_internal_zzbs2;
    }

    private static zzdjc zza(zzbk com_google_android_gms_internal_zzbk, zzbo com_google_android_gms_internal_zzbo, zzbs[] com_google_android_gms_internal_zzbsArr, int i) throws zzdji {
        zzdjd zzbjb = zzdjc.zzbjb();
        for (int valueOf : com_google_android_gms_internal_zzbk.zzwh) {
            zzbn com_google_android_gms_internal_zzbn = (zzbn) zza(com_google_android_gms_internal_zzbo.zzwx, Integer.valueOf(valueOf).intValue(), "properties");
            String str = (String) zza(com_google_android_gms_internal_zzbo.zzwv, com_google_android_gms_internal_zzbn.key, "keys");
            zzbs com_google_android_gms_internal_zzbs = (zzbs) zza(com_google_android_gms_internal_zzbsArr, com_google_android_gms_internal_zzbn.value, "values");
            if (zzbh.PUSH_AFTER_EVALUATE.toString().equals(str)) {
                zzbjb.zzl(com_google_android_gms_internal_zzbs);
            } else {
                zzbjb.zzb(str, com_google_android_gms_internal_zzbs);
            }
        }
        return zzbjb.zzbjc();
    }

    public static zzdje zza(zzbo com_google_android_gms_internal_zzbo) throws zzdji {
        int i;
        int i2 = 0;
        zzbs[] com_google_android_gms_internal_zzbsArr = new zzbs[com_google_android_gms_internal_zzbo.zzww.length];
        for (i = 0; i < com_google_android_gms_internal_zzbo.zzww.length; i++) {
            zza(i, com_google_android_gms_internal_zzbo, com_google_android_gms_internal_zzbsArr, new HashSet(0));
        }
        zzdjf zzbjd = zzdje.zzbjd();
        List arrayList = new ArrayList();
        for (i = 0; i < com_google_android_gms_internal_zzbo.zzwz.length; i++) {
            arrayList.add(zza(com_google_android_gms_internal_zzbo.zzwz[i], com_google_android_gms_internal_zzbo, com_google_android_gms_internal_zzbsArr, i));
        }
        List arrayList2 = new ArrayList();
        for (i = 0; i < com_google_android_gms_internal_zzbo.zzxa.length; i++) {
            arrayList2.add(zza(com_google_android_gms_internal_zzbo.zzxa[i], com_google_android_gms_internal_zzbo, com_google_android_gms_internal_zzbsArr, i));
        }
        List arrayList3 = new ArrayList();
        for (i = 0; i < com_google_android_gms_internal_zzbo.zzwy.length; i++) {
            zzdjc zza = zza(com_google_android_gms_internal_zzbo.zzwy[i], com_google_android_gms_internal_zzbo, com_google_android_gms_internal_zzbsArr, i);
            zzbjd.zzc(zza);
            arrayList3.add(zza);
        }
        zzbp[] com_google_android_gms_internal_zzbpArr = com_google_android_gms_internal_zzbo.zzxb;
        int length = com_google_android_gms_internal_zzbpArr.length;
        while (i2 < length) {
            zzbjd.zzb(zza(com_google_android_gms_internal_zzbpArr[i2], arrayList, arrayList3, arrayList2, com_google_android_gms_internal_zzbo));
            i2++;
        }
        zzbjd.zznh(com_google_android_gms_internal_zzbo.version);
        zzbjd.zzev(com_google_android_gms_internal_zzbo.zzxj);
        return zzbjd.zzbjf();
    }

    private static zzdjg zza(zzbp com_google_android_gms_internal_zzbp, List<zzdjc> list, List<zzdjc> list2, List<zzdjc> list3, zzbo com_google_android_gms_internal_zzbo) {
        zzdjh com_google_android_gms_internal_zzdjh = new zzdjh();
        for (int valueOf : com_google_android_gms_internal_zzbp.zzxl) {
            com_google_android_gms_internal_zzdjh.zzd((zzdjc) list3.get(Integer.valueOf(valueOf).intValue()));
        }
        for (int valueOf2 : com_google_android_gms_internal_zzbp.zzxm) {
            com_google_android_gms_internal_zzdjh.zze((zzdjc) list3.get(Integer.valueOf(valueOf2).intValue()));
        }
        for (int valueOf22 : com_google_android_gms_internal_zzbp.zzxn) {
            com_google_android_gms_internal_zzdjh.zzf((zzdjc) list.get(Integer.valueOf(valueOf22).intValue()));
        }
        for (int valueOf3 : com_google_android_gms_internal_zzbp.zzxp) {
            com_google_android_gms_internal_zzdjh.zzni(com_google_android_gms_internal_zzbo.zzww[Integer.valueOf(valueOf3).intValue()].string);
        }
        for (int valueOf222 : com_google_android_gms_internal_zzbp.zzxo) {
            com_google_android_gms_internal_zzdjh.zzg((zzdjc) list.get(Integer.valueOf(valueOf222).intValue()));
        }
        for (int valueOf32 : com_google_android_gms_internal_zzbp.zzxq) {
            com_google_android_gms_internal_zzdjh.zznj(com_google_android_gms_internal_zzbo.zzww[Integer.valueOf(valueOf32).intValue()].string);
        }
        for (int valueOf2222 : com_google_android_gms_internal_zzbp.zzxr) {
            com_google_android_gms_internal_zzdjh.zzh((zzdjc) list2.get(Integer.valueOf(valueOf2222).intValue()));
        }
        for (int valueOf322 : com_google_android_gms_internal_zzbp.zzxt) {
            com_google_android_gms_internal_zzdjh.zznk(com_google_android_gms_internal_zzbo.zzww[Integer.valueOf(valueOf322).intValue()].string);
        }
        for (int valueOf22222 : com_google_android_gms_internal_zzbp.zzxs) {
            com_google_android_gms_internal_zzdjh.zzi((zzdjc) list2.get(Integer.valueOf(valueOf22222).intValue()));
        }
        for (int valueOf4 : com_google_android_gms_internal_zzbp.zzxu) {
            com_google_android_gms_internal_zzdjh.zznl(com_google_android_gms_internal_zzbo.zzww[Integer.valueOf(valueOf4).intValue()].string);
        }
        return com_google_android_gms_internal_zzdjh.zzbji();
    }

    private static <T> T zza(T[] tArr, int i, String str) throws zzdji {
        if (i < 0 || i >= tArr.length) {
            zzmu(new StringBuilder(String.valueOf(str).length() + 45).append("Index out of bounds detected: ").append(i).append(" in ").append(str).toString());
        }
        return tArr[i];
    }

    public static void zzb(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static zzbs zzj(zzbs com_google_android_gms_internal_zzbs) {
        zzbs com_google_android_gms_internal_zzbs2 = new zzbs();
        com_google_android_gms_internal_zzbs2.type = com_google_android_gms_internal_zzbs.type;
        com_google_android_gms_internal_zzbs2.zzyt = (int[]) com_google_android_gms_internal_zzbs.zzyt.clone();
        if (com_google_android_gms_internal_zzbs.zzyu) {
            com_google_android_gms_internal_zzbs2.zzyu = com_google_android_gms_internal_zzbs.zzyu;
        }
        return com_google_android_gms_internal_zzbs2;
    }

    private static zza zzk(zzbs com_google_android_gms_internal_zzbs) throws zzdji {
        if (((zza) com_google_android_gms_internal_zzbs.zza(zza.zzxv)) == null) {
            String valueOf = String.valueOf(com_google_android_gms_internal_zzbs);
            zzmu(new StringBuilder(String.valueOf(valueOf).length() + 54).append("Expected a ServingValue and didn't get one. Value is: ").append(valueOf).toString());
        }
        return (zza) com_google_android_gms_internal_zzbs.zza(zza.zzxv);
    }

    private static void zzmu(String str) throws zzdji {
        zzdj.m10e(str);
        throw new zzdji(str);
    }
}
