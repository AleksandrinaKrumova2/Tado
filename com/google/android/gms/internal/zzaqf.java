package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzaqf {
    private final Map<String, String> zzbsr;
    private final String zzdrv;
    private final long zzdtt = 0;
    private final String zzdtu;
    private final boolean zzdtv;
    private long zzdtw;

    public zzaqf(long j, String str, String str2, boolean z, long j2, Map<String, String> map) {
        zzbq.zzgm(str);
        zzbq.zzgm(str2);
        this.zzdrv = str;
        this.zzdtu = str2;
        this.zzdtv = z;
        this.zzdtw = j2;
        if (map != null) {
            this.zzbsr = new HashMap(map);
        } else {
            this.zzbsr = Collections.emptyMap();
        }
    }

    public final Map<String, String> zzjh() {
        return this.zzbsr;
    }

    public final void zzm(long j) {
        this.zzdtw = j;
    }

    public final String zzvz() {
        return this.zzdrv;
    }

    public final long zzxm() {
        return this.zzdtt;
    }

    public final String zzxn() {
        return this.zzdtu;
    }

    public final boolean zzxo() {
        return this.zzdtv;
    }

    public final long zzxp() {
        return this.zzdtw;
    }
}
