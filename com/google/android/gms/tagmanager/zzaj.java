package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbs;
import java.util.Map;

final class zzaj extends zzbr {
    private static final String ID = zzbg.CONTAINER_VERSION.toString();
    private final String zzffg;

    public zzaj(String str) {
        super(ID, new String[0]);
        this.zzffg = str;
    }

    public final boolean zzbdp() {
        return true;
    }

    public final zzbs zzv(Map<String, zzbs> map) {
        return this.zzffg == null ? zzgk.zzbgs() : zzgk.zzam(this.zzffg);
    }
}
