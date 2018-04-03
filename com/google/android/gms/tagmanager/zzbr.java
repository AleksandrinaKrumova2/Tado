package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbs;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract class zzbr {
    private final Set<String> zzkfs;
    private final String zzkft;

    public zzbr(String str, String... strArr) {
        this.zzkft = str;
        this.zzkfs = new HashSet(strArr.length);
        for (Object add : strArr) {
            this.zzkfs.add(add);
        }
    }

    public abstract boolean zzbdp();

    public String zzbew() {
        return this.zzkft;
    }

    public Set<String> zzbex() {
        return this.zzkfs;
    }

    final boolean zzd(Set<String> set) {
        return set.containsAll(this.zzkfs);
    }

    public abstract zzbs zzv(Map<String, zzbs> map);
}
