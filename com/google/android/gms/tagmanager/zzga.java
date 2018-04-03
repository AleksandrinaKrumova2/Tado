package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbs;
import java.util.Map;

abstract class zzga extends zzeg {
    public zzga(String str) {
        super(str);
    }

    protected final boolean zza(zzbs com_google_android_gms_internal_zzbs, zzbs com_google_android_gms_internal_zzbs2, Map<String, zzbs> map) {
        String zzb = zzgk.zzb(com_google_android_gms_internal_zzbs);
        String zzb2 = zzgk.zzb(com_google_android_gms_internal_zzbs2);
        return (zzb == zzgk.zzbgr() || zzb2 == zzgk.zzbgr()) ? false : zza(zzb, zzb2, (Map) map);
    }

    protected abstract boolean zza(String str, String str2, Map<String, zzbs> map);
}
