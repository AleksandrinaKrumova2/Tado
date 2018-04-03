package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Map;

public final class zzdjc {
    private final zzbs zzkjc;
    private final Map<String, zzbs> zzksg;

    private zzdjc(Map<String, zzbs> map, zzbs com_google_android_gms_internal_zzbs) {
        this.zzksg = map;
        this.zzkjc = com_google_android_gms_internal_zzbs;
    }

    public static zzdjd zzbjb() {
        return new zzdjd();
    }

    public final String toString() {
        String valueOf = String.valueOf(Collections.unmodifiableMap(this.zzksg));
        String valueOf2 = String.valueOf(this.zzkjc);
        return new StringBuilder((String.valueOf(valueOf).length() + 32) + String.valueOf(valueOf2).length()).append("Properties: ").append(valueOf).append(" pushAfterEvaluate: ").append(valueOf2).toString();
    }

    public final void zza(String str, zzbs com_google_android_gms_internal_zzbs) {
        this.zzksg.put(str, com_google_android_gms_internal_zzbs);
    }

    public final zzbs zzbfy() {
        return this.zzkjc;
    }

    public final Map<String, zzbs> zzbik() {
        return Collections.unmodifiableMap(this.zzksg);
    }
}
