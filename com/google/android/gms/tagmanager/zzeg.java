package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbh;
import com.google.android.gms.internal.zzbs;
import java.util.Map;
import java.util.Set;

public abstract class zzeg extends zzbr {
    private static final String zzkfo = zzbh.ARG0.toString();
    private static final String zzkhl = zzbh.ARG1.toString();

    public zzeg(String str) {
        super(str, zzkfo, zzkhl);
    }

    protected abstract boolean zza(zzbs com_google_android_gms_internal_zzbs, zzbs com_google_android_gms_internal_zzbs2, Map<String, zzbs> map);

    public final boolean zzbdp() {
        return true;
    }

    public final /* bridge */ /* synthetic */ String zzbew() {
        return super.zzbew();
    }

    public final /* bridge */ /* synthetic */ Set zzbex() {
        return super.zzbex();
    }

    public final zzbs zzv(Map<String, zzbs> map) {
        for (zzbs com_google_android_gms_internal_zzbs : map.values()) {
            if (com_google_android_gms_internal_zzbs == zzgk.zzbgs()) {
                return zzgk.zzam(Boolean.valueOf(false));
            }
        }
        zzbs com_google_android_gms_internal_zzbs2 = (zzbs) map.get(zzkfo);
        zzbs com_google_android_gms_internal_zzbs3 = (zzbs) map.get(zzkhl);
        boolean zza = (com_google_android_gms_internal_zzbs2 == null || com_google_android_gms_internal_zzbs3 == null) ? false : zza(com_google_android_gms_internal_zzbs2, com_google_android_gms_internal_zzbs3, map);
        return zzgk.zzam(Boolean.valueOf(zza));
    }
}
