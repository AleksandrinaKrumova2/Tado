package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbs;
import java.util.Map;

final class zzbp extends zzbr {
    private static final String ID = zzbg.EVENT.toString();
    private final zzfc zzkdf;

    public zzbp(zzfc com_google_android_gms_tagmanager_zzfc) {
        super(ID, new String[0]);
        this.zzkdf = com_google_android_gms_tagmanager_zzfc;
    }

    public final boolean zzbdp() {
        return false;
    }

    public final zzbs zzv(Map<String, zzbs> map) {
        String zzbfv = this.zzkdf.zzbfv();
        return zzbfv == null ? zzgk.zzbgs() : zzgk.zzam(zzbfv);
    }
}
