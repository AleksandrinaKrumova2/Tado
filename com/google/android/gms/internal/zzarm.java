package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public final class zzarm<V> {
    private final V zzdxn;
    private final zzbey<V> zzdxo;

    private zzarm(zzbey<V> com_google_android_gms_internal_zzbey_V, V v) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzbey_V);
        this.zzdxo = com_google_android_gms_internal_zzbey_V;
        this.zzdxn = v;
    }

    static zzarm<Float> zza(String str, float f, float f2) {
        return new zzarm(zzbey.zza(str, Float.valueOf(0.5f)), Float.valueOf(0.5f));
    }

    static zzarm<Integer> zza(String str, int i, int i2) {
        return new zzarm(zzbey.zza(str, Integer.valueOf(i2)), Integer.valueOf(i));
    }

    static zzarm<Long> zza(String str, long j, long j2) {
        return new zzarm(zzbey.zza(str, Long.valueOf(j2)), Long.valueOf(j));
    }

    static zzarm<Boolean> zza(String str, boolean z, boolean z2) {
        return new zzarm(zzbey.zze(str, z2), Boolean.valueOf(z));
    }

    static zzarm<String> zzc(String str, String str2, String str3) {
        return new zzarm(zzbey.zzs(str, str3), str2);
    }

    public final V get() {
        return this.zzdxn;
    }
}
