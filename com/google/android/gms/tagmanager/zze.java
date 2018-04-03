package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbs;
import java.util.Map;

final class zze extends zzbr {
    private static final String ID = zzbg.ADVERTISER_ID.toString();
    private final zza zzkcu;

    public zze(Context context) {
        this(zza.zzeb(context));
    }

    private zze(zza com_google_android_gms_tagmanager_zza) {
        super(ID, new String[0]);
        this.zzkcu = com_google_android_gms_tagmanager_zza;
        this.zzkcu.zzbdj();
    }

    public final boolean zzbdp() {
        return false;
    }

    public final zzbs zzv(Map<String, zzbs> map) {
        String zzbdj = this.zzkcu.zzbdj();
        return zzbdj == null ? zzgk.zzbgs() : zzgk.zzam(zzbdj);
    }
}
