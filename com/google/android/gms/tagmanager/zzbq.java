package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbl;
import com.google.android.gms.internal.zzbm;
import com.google.android.gms.internal.zzbs;
import java.util.Map;

final class zzbq {
    private static void zza(DataLayer dataLayer, zzbm com_google_android_gms_internal_zzbm) {
        for (zzbl com_google_android_gms_internal_zzbl : com_google_android_gms_internal_zzbm.zzws) {
            if (com_google_android_gms_internal_zzbl.key == null) {
                zzdj.zzcu("GaExperimentRandom: No key");
            } else {
                Object obj = dataLayer.get(com_google_android_gms_internal_zzbl.key);
                Long valueOf = !(obj instanceof Number) ? null : Long.valueOf(((Number) obj).longValue());
                long j = com_google_android_gms_internal_zzbl.zzwm;
                long j2 = com_google_android_gms_internal_zzbl.zzwn;
                if (!com_google_android_gms_internal_zzbl.zzwo || valueOf == null || valueOf.longValue() < j || valueOf.longValue() > j2) {
                    if (j <= j2) {
                        obj = Long.valueOf(Math.round((Math.random() * ((double) (j2 - j))) + ((double) j)));
                    } else {
                        zzdj.zzcu("GaExperimentRandom: random range invalid");
                    }
                }
                dataLayer.zzlg(com_google_android_gms_internal_zzbl.key);
                Map zzn = DataLayer.zzn(com_google_android_gms_internal_zzbl.key, obj);
                if (com_google_android_gms_internal_zzbl.zzwp > 0) {
                    if (zzn.containsKey("gtm")) {
                        Object obj2 = zzn.get("gtm");
                        if (obj2 instanceof Map) {
                            ((Map) obj2).put("lifetime", Long.valueOf(com_google_android_gms_internal_zzbl.zzwp));
                        } else {
                            zzdj.zzcu("GaExperimentRandom: gtm not a map");
                        }
                    } else {
                        zzn.put("gtm", DataLayer.mapOf("lifetime", Long.valueOf(com_google_android_gms_internal_zzbl.zzwp)));
                    }
                }
                dataLayer.push(zzn);
            }
        }
    }

    public static void zza(DataLayer dataLayer, com.google.android.gms.internal.zzbq com_google_android_gms_internal_zzbq) {
        if (com_google_android_gms_internal_zzbq.zzyg == null) {
            zzdj.zzcu("supplemental missing experimentSupplemental");
            return;
        }
        for (zzbs zzb : com_google_android_gms_internal_zzbq.zzyg.zzwr) {
            dataLayer.zzlg(zzgk.zzb(zzb));
        }
        for (zzbs zzg : com_google_android_gms_internal_zzbq.zzyg.zzwq) {
            Map map;
            Object zzg2 = zzgk.zzg(zzg);
            if (zzg2 instanceof Map) {
                map = (Map) zzg2;
            } else {
                String valueOf = String.valueOf(zzg2);
                zzdj.zzcu(new StringBuilder(String.valueOf(valueOf).length() + 36).append("value: ").append(valueOf).append(" is not a map value, ignored.").toString());
                map = null;
            }
            if (map != null) {
                dataLayer.push(map);
            }
        }
        zza(dataLayer, com_google_android_gms_internal_zzbq.zzyg);
    }
}
