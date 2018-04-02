package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.Map;
import java.util.Map.Entry;

public class zzarv extends zzaqa {
    private static zzarv zzdyf;

    public zzarv(zzaqc com_google_android_gms_internal_zzaqc) {
        super(com_google_android_gms_internal_zzaqc);
    }

    private static String zzo(Object obj) {
        if (obj == null) {
            return null;
        }
        Object valueOf = obj instanceof Integer ? Long.valueOf((long) ((Integer) obj).intValue()) : obj;
        if (!(valueOf instanceof Long)) {
            return valueOf instanceof Boolean ? String.valueOf(valueOf) : valueOf instanceof Throwable ? valueOf.getClass().getCanonicalName() : "-";
        } else {
            if (Math.abs(((Long) valueOf).longValue()) < 100) {
                return String.valueOf(valueOf);
            }
            String str = String.valueOf(valueOf).charAt(0) == '-' ? "-" : "";
            String valueOf2 = String.valueOf(Math.abs(((Long) valueOf).longValue()));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(Math.round(Math.pow(10.0d, (double) (valueOf2.length() - 1))));
            stringBuilder.append("...");
            stringBuilder.append(str);
            stringBuilder.append(Math.round(Math.pow(10.0d, (double) valueOf2.length()) - 1.0d));
            return stringBuilder.toString();
        }
    }

    public static zzarv zzzo() {
        return zzdyf;
    }

    public final void zza(zzarq com_google_android_gms_internal_zzarq, String str) {
        Object com_google_android_gms_internal_zzarq2 = com_google_android_gms_internal_zzarq != null ? com_google_android_gms_internal_zzarq.toString() : "no hit data";
        String str2 = "Discarding hit. ";
        String valueOf = String.valueOf(str);
        zzd(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), com_google_android_gms_internal_zzarq2);
    }

    public final synchronized void zzb(int i, String str, Object obj, Object obj2, Object obj3) {
        int i2 = 0;
        synchronized (this) {
            zzbq.checkNotNull(str);
            if (i >= 0) {
                i2 = i;
            }
            int i3 = i2 >= 9 ? 8 : i2;
            char c = zzwu().zzyp() ? 'C' : 'c';
            String str2 = "3";
            char charAt = "01VDIWEA?".charAt(i3);
            String str3 = zzaqb.VERSION;
            String zzc = zzapz.zzc(str, zzo(obj), zzo(obj2), zzo(obj3));
            String stringBuilder = new StringBuilder(((String.valueOf(str2).length() + 3) + String.valueOf(str3).length()) + String.valueOf(zzc).length()).append(str2).append(charAt).append(c).append(str3).append(":").append(zzc).toString();
            if (stringBuilder.length() > 1024) {
                stringBuilder = stringBuilder.substring(0, 1024);
            }
            zzarz zzxj = zzwr().zzxj();
            if (zzxj != null) {
                zzxj.zzaab().zzeg(stringBuilder);
            }
        }
    }

    public final void zzf(Map<String, String> map, String str) {
        Object stringBuilder;
        if (map != null) {
            StringBuilder stringBuilder2 = new StringBuilder();
            for (Entry entry : map.entrySet()) {
                if (stringBuilder2.length() > 0) {
                    stringBuilder2.append(',');
                }
                stringBuilder2.append((String) entry.getKey());
                stringBuilder2.append('=');
                stringBuilder2.append((String) entry.getValue());
            }
            stringBuilder = stringBuilder2.toString();
        } else {
            stringBuilder = "no hit data";
        }
        String str2 = "Discarding hit. ";
        String valueOf = String.valueOf(str);
        zzd(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), stringBuilder);
    }

    protected final void zzvf() {
        synchronized (zzarv.class) {
            zzdyf = this;
        }
    }
}
