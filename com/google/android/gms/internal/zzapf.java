package com.google.android.gms.internal;

import com.google.android.gms.analytics.zzh;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class zzapf extends zzh<zzapf> {
    private Map<Integer, String> zzdrj = new HashMap(4);

    public final String toString() {
        Map hashMap = new HashMap();
        for (Entry entry : this.zzdrj.entrySet()) {
            String valueOf = String.valueOf(entry.getKey());
            hashMap.put(new StringBuilder(String.valueOf(valueOf).length() + 9).append("dimension").append(valueOf).toString(), entry.getValue());
        }
        return zzh.zzl(hashMap);
    }

    public final /* synthetic */ void zzb(zzh com_google_android_gms_analytics_zzh) {
        ((zzapf) com_google_android_gms_analytics_zzh).zzdrj.putAll(this.zzdrj);
    }

    public final Map<Integer, String> zzvr() {
        return Collections.unmodifiableMap(this.zzdrj);
    }
}
