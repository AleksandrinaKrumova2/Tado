package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbh;
import com.google.android.gms.internal.zzbs;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

final class zzcy extends zzbr {
    private static final String ID = zzbg.JOINER.toString();
    private static final String zzkfo = zzbh.ARG0.toString();
    private static final String zzkgg = zzbh.ITEM_SEPARATOR.toString();
    private static final String zzkgh = zzbh.KEY_VALUE_SEPARATOR.toString();
    private static final String zzkgi = zzbh.ESCAPE.toString();

    public zzcy() {
        super(ID, zzkfo);
    }

    private static String zza(String str, Integer num, Set<Character> set) {
        switch (zzcz.zzkgj[num - 1]) {
            case 1:
                try {
                    return zzgo.zzmg(str);
                } catch (Throwable e) {
                    zzdj.zzb("Joiner: unsupported encoding", e);
                    return str;
                }
            case 2:
                String replace = str.replace("\\", "\\\\");
                String str2 = replace;
                for (Character ch : set) {
                    CharSequence ch2 = ch.toString();
                    String str3 = "\\";
                    replace = String.valueOf(ch2);
                    str2 = str2.replace(ch2, replace.length() != 0 ? str3.concat(replace) : new String(str3));
                }
                return str2;
            default:
                return str;
        }
    }

    private static void zza(StringBuilder stringBuilder, String str, Integer num, Set<Character> set) {
        stringBuilder.append(zza(str, num, set));
    }

    private static void zza(Set<Character> set, String str) {
        for (int i = 0; i < str.length(); i++) {
            set.add(Character.valueOf(str.charAt(i)));
        }
    }

    public final boolean zzbdp() {
        return true;
    }

    public final zzbs zzv(Map<String, zzbs> map) {
        zzbs com_google_android_gms_internal_zzbs = (zzbs) map.get(zzkfo);
        if (com_google_android_gms_internal_zzbs == null) {
            return zzgk.zzbgs();
        }
        int i;
        Set set;
        zzbs com_google_android_gms_internal_zzbs2 = (zzbs) map.get(zzkgg);
        String zzb = com_google_android_gms_internal_zzbs2 != null ? zzgk.zzb(com_google_android_gms_internal_zzbs2) : "";
        com_google_android_gms_internal_zzbs2 = (zzbs) map.get(zzkgh);
        String zzb2 = com_google_android_gms_internal_zzbs2 != null ? zzgk.zzb(com_google_android_gms_internal_zzbs2) : "=";
        int i2 = zzda.zzkgk;
        com_google_android_gms_internal_zzbs2 = (zzbs) map.get(zzkgi);
        if (com_google_android_gms_internal_zzbs2 != null) {
            String zzb3 = zzgk.zzb(com_google_android_gms_internal_zzbs2);
            if ("url".equals(zzb3)) {
                i = zzda.zzkgl;
                set = null;
            } else if ("backslash".equals(zzb3)) {
                i = zzda.zzkgm;
                set = new HashSet();
                zza(set, zzb);
                zza(set, zzb2);
                set.remove(Character.valueOf('\\'));
            } else {
                zzb = "Joiner: unsupported escape type: ";
                String valueOf = String.valueOf(zzb3);
                zzdj.m10e(valueOf.length() != 0 ? zzb.concat(valueOf) : new String(zzb));
                return zzgk.zzbgs();
            }
        }
        set = null;
        i = i2;
        StringBuilder stringBuilder = new StringBuilder();
        switch (com_google_android_gms_internal_zzbs.type) {
            case 2:
                Object obj = 1;
                zzbs[] com_google_android_gms_internal_zzbsArr = com_google_android_gms_internal_zzbs.zzyl;
                int length = com_google_android_gms_internal_zzbsArr.length;
                int i3 = 0;
                while (i3 < length) {
                    zzbs com_google_android_gms_internal_zzbs3 = com_google_android_gms_internal_zzbsArr[i3];
                    if (obj == null) {
                        stringBuilder.append(zzb);
                    }
                    zza(stringBuilder, zzgk.zzb(com_google_android_gms_internal_zzbs3), i, set);
                    i3++;
                    obj = null;
                }
                break;
            case 3:
                for (i2 = 0; i2 < com_google_android_gms_internal_zzbs.zzym.length; i2++) {
                    if (i2 > 0) {
                        stringBuilder.append(zzb);
                    }
                    String zzb4 = zzgk.zzb(com_google_android_gms_internal_zzbs.zzym[i2]);
                    String zzb5 = zzgk.zzb(com_google_android_gms_internal_zzbs.zzyn[i2]);
                    zza(stringBuilder, zzb4, i, set);
                    stringBuilder.append(zzb2);
                    zza(stringBuilder, zzb5, i, set);
                }
                break;
            default:
                zza(stringBuilder, zzgk.zzb(com_google_android_gms_internal_zzbs), i, set);
                break;
        }
        return zzgk.zzam(stringBuilder.toString());
    }
}
