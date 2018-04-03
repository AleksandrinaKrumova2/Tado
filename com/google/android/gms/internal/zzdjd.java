package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

public final class zzdjd {
    private zzbs zzkjc;
    private final Map<String, zzbs> zzksg;

    private zzdjd() {
        this.zzksg = new HashMap();
    }

    public final zzdjd zzb(String str, zzbs com_google_android_gms_internal_zzbs) {
        this.zzksg.put(str, com_google_android_gms_internal_zzbs);
        return this;
    }

    public final zzdjc zzbjc() {
        return new zzdjc(this.zzksg, this.zzkjc);
    }

    public final zzdjd zzl(zzbs com_google_android_gms_internal_zzbs) {
        this.zzkjc = com_google_android_gms_internal_zzbs;
        return this;
    }
}
