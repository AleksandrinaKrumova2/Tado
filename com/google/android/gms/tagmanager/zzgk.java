package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class zzgk {
    private static final Object zzkkl = null;
    private static Long zzkkm = new Long(0);
    private static Double zzkkn = new Double(0.0d);
    private static zzgj zzkko = zzgj.zzbi(0);
    private static String zzkkp = new String("");
    private static Boolean zzkkq = new Boolean(false);
    private static List<Object> zzkkr = new ArrayList(0);
    private static Map<Object, Object> zzkks = new HashMap();
    private static zzbs zzkkt = zzam(zzkkp);

    private static double getDouble(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        zzdj.m10e("getDouble received non-Number");
        return 0.0d;
    }

    private static String zzal(Object obj) {
        return obj == null ? zzkkp : obj.toString();
    }

    public static zzbs zzam(Object obj) {
        boolean z = false;
        zzbs com_google_android_gms_internal_zzbs = new zzbs();
        if (obj instanceof zzbs) {
            return (zzbs) obj;
        }
        if (obj instanceof String) {
            com_google_android_gms_internal_zzbs.type = 1;
            com_google_android_gms_internal_zzbs.string = (String) obj;
        } else if (obj instanceof List) {
            com_google_android_gms_internal_zzbs.type = 2;
            List<Object> list = (List) obj;
            r5 = new ArrayList(list.size());
            r1 = false;
            for (Object zzam : list) {
                zzbs zzam2 = zzam(zzam);
                if (zzam2 == zzkkt) {
                    return zzkkt;
                }
                r0 = r1 || zzam2.zzyu;
                r5.add(zzam2);
                r1 = r0;
            }
            com_google_android_gms_internal_zzbs.zzyl = (zzbs[]) r5.toArray(new zzbs[0]);
            z = r1;
        } else if (obj instanceof Map) {
            com_google_android_gms_internal_zzbs.type = 3;
            Set<Entry> entrySet = ((Map) obj).entrySet();
            r5 = new ArrayList(entrySet.size());
            List arrayList = new ArrayList(entrySet.size());
            r1 = false;
            for (Entry entry : entrySet) {
                zzbs zzam3 = zzam(entry.getKey());
                zzbs zzam4 = zzam(entry.getValue());
                if (zzam3 == zzkkt || zzam4 == zzkkt) {
                    return zzkkt;
                }
                r0 = r1 || zzam3.zzyu || zzam4.zzyu;
                r5.add(zzam3);
                arrayList.add(zzam4);
                r1 = r0;
            }
            com_google_android_gms_internal_zzbs.zzym = (zzbs[]) r5.toArray(new zzbs[0]);
            com_google_android_gms_internal_zzbs.zzyn = (zzbs[]) arrayList.toArray(new zzbs[0]);
            z = r1;
        } else if (zzan(obj)) {
            com_google_android_gms_internal_zzbs.type = 1;
            com_google_android_gms_internal_zzbs.string = obj.toString();
        } else if (zzao(obj)) {
            com_google_android_gms_internal_zzbs.type = 6;
            com_google_android_gms_internal_zzbs.zzyq = zzap(obj);
        } else if (obj instanceof Boolean) {
            com_google_android_gms_internal_zzbs.type = 8;
            com_google_android_gms_internal_zzbs.zzyr = ((Boolean) obj).booleanValue();
        } else {
            String str = "Converting to Value from unknown object type: ";
            String valueOf = String.valueOf(obj == null ? "null" : obj.getClass().toString());
            zzdj.m10e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            return zzkkt;
        }
        com_google_android_gms_internal_zzbs.zzyu = z;
        return com_google_android_gms_internal_zzbs;
    }

    private static boolean zzan(Object obj) {
        return (obj instanceof Double) || (obj instanceof Float) || ((obj instanceof zzgj) && ((zzgj) obj).zzbgk());
    }

    private static boolean zzao(Object obj) {
        return (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || ((obj instanceof zzgj) && ((zzgj) obj).zzbgl());
    }

    private static long zzap(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        zzdj.m10e("getInt64 received non-Number");
        return 0;
    }

    public static String zzb(zzbs com_google_android_gms_internal_zzbs) {
        return zzal(zzg(com_google_android_gms_internal_zzbs));
    }

    public static Object zzbgm() {
        return null;
    }

    public static Long zzbgn() {
        return zzkkm;
    }

    public static Double zzbgo() {
        return zzkkn;
    }

    public static Boolean zzbgp() {
        return zzkkq;
    }

    public static zzgj zzbgq() {
        return zzkko;
    }

    public static String zzbgr() {
        return zzkkp;
    }

    public static zzbs zzbgs() {
        return zzkkt;
    }

    public static zzgj zzc(zzbs com_google_android_gms_internal_zzbs) {
        Object zzg = zzg(com_google_android_gms_internal_zzbs);
        return zzg instanceof zzgj ? (zzgj) zzg : zzao(zzg) ? zzgj.zzbi(zzap(zzg)) : zzan(zzg) ? zzgj.zza(Double.valueOf(getDouble(zzg))) : zzmd(zzal(zzg));
    }

    public static Long zzd(zzbs com_google_android_gms_internal_zzbs) {
        Object zzg = zzg(com_google_android_gms_internal_zzbs);
        if (zzao(zzg)) {
            return Long.valueOf(zzap(zzg));
        }
        zzgj zzmd = zzmd(zzal(zzg));
        return zzmd == zzkko ? zzkkm : Long.valueOf(zzmd.longValue());
    }

    public static Double zze(zzbs com_google_android_gms_internal_zzbs) {
        Object zzg = zzg(com_google_android_gms_internal_zzbs);
        if (zzan(zzg)) {
            return Double.valueOf(getDouble(zzg));
        }
        zzgj zzmd = zzmd(zzal(zzg));
        return zzmd == zzkko ? zzkkn : Double.valueOf(zzmd.doubleValue());
    }

    public static Boolean zzf(zzbs com_google_android_gms_internal_zzbs) {
        Object zzg = zzg(com_google_android_gms_internal_zzbs);
        if (zzg instanceof Boolean) {
            return (Boolean) zzg;
        }
        String zzal = zzal(zzg);
        return "true".equalsIgnoreCase(zzal) ? Boolean.TRUE : "false".equalsIgnoreCase(zzal) ? Boolean.FALSE : zzkkq;
    }

    public static Object zzg(zzbs com_google_android_gms_internal_zzbs) {
        int i = 0;
        if (com_google_android_gms_internal_zzbs == null) {
            return null;
        }
        zzbs[] com_google_android_gms_internal_zzbsArr;
        int length;
        switch (com_google_android_gms_internal_zzbs.type) {
            case 1:
                return com_google_android_gms_internal_zzbs.string;
            case 2:
                ArrayList arrayList = new ArrayList(com_google_android_gms_internal_zzbs.zzyl.length);
                com_google_android_gms_internal_zzbsArr = com_google_android_gms_internal_zzbs.zzyl;
                length = com_google_android_gms_internal_zzbsArr.length;
                while (i < length) {
                    Object zzg = zzg(com_google_android_gms_internal_zzbsArr[i]);
                    if (zzg == null) {
                        return null;
                    }
                    arrayList.add(zzg);
                    i++;
                }
                return arrayList;
            case 3:
                if (com_google_android_gms_internal_zzbs.zzym.length != com_google_android_gms_internal_zzbs.zzyn.length) {
                    String str = "Converting an invalid value to object: ";
                    String valueOf = String.valueOf(com_google_android_gms_internal_zzbs.toString());
                    zzdj.m10e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                    return null;
                }
                Map hashMap = new HashMap(com_google_android_gms_internal_zzbs.zzyn.length);
                while (i < com_google_android_gms_internal_zzbs.zzym.length) {
                    Object zzg2 = zzg(com_google_android_gms_internal_zzbs.zzym[i]);
                    Object zzg3 = zzg(com_google_android_gms_internal_zzbs.zzyn[i]);
                    if (zzg2 == null || zzg3 == null) {
                        return null;
                    }
                    hashMap.put(zzg2, zzg3);
                    i++;
                }
                return hashMap;
            case 4:
                zzdj.m10e("Trying to convert a macro reference to object");
                return null;
            case 5:
                zzdj.m10e("Trying to convert a function id to object");
                return null;
            case 6:
                return Long.valueOf(com_google_android_gms_internal_zzbs.zzyq);
            case 7:
                StringBuffer stringBuffer = new StringBuffer();
                com_google_android_gms_internal_zzbsArr = com_google_android_gms_internal_zzbs.zzys;
                length = com_google_android_gms_internal_zzbsArr.length;
                while (i < length) {
                    String zzb = zzb(com_google_android_gms_internal_zzbsArr[i]);
                    if (zzb == zzkkp) {
                        return null;
                    }
                    stringBuffer.append(zzb);
                    i++;
                }
                return stringBuffer.toString();
            case 8:
                return Boolean.valueOf(com_google_android_gms_internal_zzbs.zzyr);
            default:
                zzdj.m10e("Failed to convert a value of type: " + com_google_android_gms_internal_zzbs.type);
                return null;
        }
    }

    public static zzbs zzmc(String str) {
        zzbs com_google_android_gms_internal_zzbs = new zzbs();
        com_google_android_gms_internal_zzbs.type = 5;
        com_google_android_gms_internal_zzbs.zzyp = str;
        return com_google_android_gms_internal_zzbs;
    }

    private static zzgj zzmd(String str) {
        try {
            return zzgj.zzmb(str);
        } catch (NumberFormatException e) {
            zzdj.m10e(new StringBuilder(String.valueOf(str).length() + 33).append("Failed to convert '").append(str).append("' to a number.").toString());
            return zzkko;
        }
    }
}
