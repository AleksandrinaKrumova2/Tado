package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class zzdje {
    private final String zzffg;
    private final List<zzdjg> zzksd;
    private final Map<String, List<zzdjc>> zzkse;
    private final int zzksf;

    private zzdje(List<zzdjg> list, Map<String, List<zzdjc>> map, String str, int i) {
        this.zzksd = Collections.unmodifiableList(list);
        this.zzkse = Collections.unmodifiableMap(map);
        this.zzffg = str;
        this.zzksf = i;
    }

    public static zzdjf zzbjd() {
        return new zzdjf();
    }

    public final String getVersion() {
        return this.zzffg;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzksd);
        String valueOf2 = String.valueOf(this.zzkse);
        return new StringBuilder((String.valueOf(valueOf).length() + 17) + String.valueOf(valueOf2).length()).append("Rules: ").append(valueOf).append("  Macros: ").append(valueOf2).toString();
    }

    public final List<zzdjg> zzbii() {
        return this.zzksd;
    }

    public final Map<String, List<zzdjc>> zzbje() {
        return this.zzkse;
    }
}
