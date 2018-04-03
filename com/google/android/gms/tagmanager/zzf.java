package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbs;
import java.util.Map;

final class zzf extends zzbr {
    private static final String ID = zzbg.ADVERTISING_TRACKING_ENABLED.toString();
    private final zza zzkcu;

    public zzf(Context context) {
        this(zza.zzeb(context));
    }

    private zzf(zza com_google_android_gms_tagmanager_zza) {
        super(ID, new String[0]);
        this.zzkcu = com_google_android_gms_tagmanager_zza;
    }

    public final boolean zzbdp() {
        return false;
    }

    public final zzbs zzv(Map<String, zzbs> map) {
        return zzgk.zzam(Boolean.valueOf(!this.zzkcu.isLimitAdTrackingEnabled()));
    }
}
