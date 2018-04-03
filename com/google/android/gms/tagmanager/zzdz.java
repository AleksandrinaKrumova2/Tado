package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbs;
import java.util.Map;

abstract class zzdz extends zzeg {
    public zzdz(String str) {
        super(str);
    }

    protected final boolean zza(zzbs com_google_android_gms_internal_zzbs, zzbs com_google_android_gms_internal_zzbs2, Map<String, zzbs> map) {
        zzgj zzc = zzgk.zzc(com_google_android_gms_internal_zzbs);
        zzgj zzc2 = zzgk.zzc(com_google_android_gms_internal_zzbs2);
        return (zzc == zzgk.zzbgq() || zzc2 == zzgk.zzbgq()) ? false : zza(zzc, zzc2, (Map) map);
    }

    protected abstract boolean zza(zzgj com_google_android_gms_tagmanager_zzgj, zzgj com_google_android_gms_tagmanager_zzgj2, Map<String, zzbs> map);
}
