package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbs;
import java.util.Map;

abstract class zzgi extends zzbr {
    public zzgi(String str, String... strArr) {
        super(str, strArr);
    }

    public boolean zzbdp() {
        return false;
    }

    public zzbs zzv(Map<String, zzbs> map) {
        zzx(map);
        return zzgk.zzbgs();
    }

    public abstract void zzx(Map<String, zzbs> map);
}
