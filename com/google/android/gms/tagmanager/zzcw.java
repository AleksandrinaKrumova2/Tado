package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbh;
import com.google.android.gms.internal.zzbs;
import java.util.Map;

final class zzcw extends zzbr {
    private static final String ID = zzbg.INSTALL_REFERRER.toString();
    private static final String zzkcv = zzbh.COMPONENT.toString();
    private final Context zzair;

    public zzcw(Context context) {
        super(ID, new String[0]);
        this.zzair = context;
    }

    public final boolean zzbdp() {
        return true;
    }

    public final zzbs zzv(Map<String, zzbs> map) {
        String zzaj = zzcx.zzaj(this.zzair, ((zzbs) map.get(zzkcv)) != null ? zzgk.zzb((zzbs) map.get(zzkcv)) : null);
        return zzaj != null ? zzgk.zzam(zzaj) : zzgk.zzbgs();
    }
}
