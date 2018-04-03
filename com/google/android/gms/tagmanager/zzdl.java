package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbh;
import com.google.android.gms.internal.zzbs;
import java.util.Map;

final class zzdl extends zzbr {
    private static final String ID = zzbg.LOWERCASE_STRING.toString();
    private static final String zzkfo = zzbh.ARG0.toString();

    public zzdl() {
        super(ID, zzkfo);
    }

    public final boolean zzbdp() {
        return true;
    }

    public final zzbs zzv(Map<String, zzbs> map) {
        return zzgk.zzam(zzgk.zzb((zzbs) map.get(zzkfo)).toLowerCase());
    }
}
