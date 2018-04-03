package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbs;
import java.util.Map;

final class zzgf extends zzbr {
    private static final String ID = zzbg.TIME.toString();

    public zzgf() {
        super(ID, new String[0]);
    }

    public final boolean zzbdp() {
        return false;
    }

    public final zzbs zzv(Map<String, zzbs> map) {
        return zzgk.zzam(Long.valueOf(System.currentTimeMillis()));
    }
}
