package com.google.android.gms.internal;

import com.google.android.gms.analytics.zzh;
import com.google.android.gms.common.internal.zzbq;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzaph extends zzh<zzaph> {
    private final Map<String, Object> zzbsr = new HashMap();

    public final void set(String str, String str2) {
        zzbq.zzgm(str);
        if (str != null && str.startsWith("&")) {
            str = str.substring(1);
        }
        zzbq.zzh(str, "Name can not be empty or \"&\"");
        this.zzbsr.put(str, str2);
    }

    public final String toString() {
        return zzh.zzl(this.zzbsr);
    }

    public final /* synthetic */ void zzb(zzh com_google_android_gms_analytics_zzh) {
        zzaph com_google_android_gms_internal_zzaph = (zzaph) com_google_android_gms_analytics_zzh;
        zzbq.checkNotNull(com_google_android_gms_internal_zzaph);
        com_google_android_gms_internal_zzaph.zzbsr.putAll(this.zzbsr);
    }

    public final Map<String, Object> zzvt() {
        return Collections.unmodifiableMap(this.zzbsr);
    }
}
