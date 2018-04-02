package com.google.android.gms.internal;

import com.google.android.gms.analytics.zzh;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class zzapg extends zzh<zzapg> {
    private Map<Integer, Double> zzdrk = new HashMap(4);

    public final String toString() {
        Map hashMap = new HashMap();
        for (Entry entry : this.zzdrk.entrySet()) {
            String valueOf = String.valueOf(entry.getKey());
            hashMap.put(new StringBuilder(String.valueOf(valueOf).length() + 6).append("metric").append(valueOf).toString(), entry.getValue());
        }
        return zzh.zzl(hashMap);
    }

    public final /* synthetic */ void zzb(zzh com_google_android_gms_analytics_zzh) {
        ((zzapg) com_google_android_gms_analytics_zzh).zzdrk.putAll(this.zzdrk);
    }

    public final Map<Integer, Double> zzvs() {
        return Collections.unmodifiableMap(this.zzdrk);
    }
}
