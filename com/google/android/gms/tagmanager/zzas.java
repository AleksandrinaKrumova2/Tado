package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbh;
import com.google.android.gms.internal.zzbs;
import java.util.Map;

final class zzas extends zzbr {
    private static final String ID = zzbg.CUSTOM_VAR.toString();
    private static final String NAME = zzbh.NAME.toString();
    private static final String zzket = zzbh.DEFAULT_VALUE.toString();
    private final DataLayer zzkde;

    public zzas(DataLayer dataLayer) {
        super(ID, NAME);
        this.zzkde = dataLayer;
    }

    public final boolean zzbdp() {
        return false;
    }

    public final zzbs zzv(Map<String, zzbs> map) {
        Object obj = this.zzkde.get(zzgk.zzb((zzbs) map.get(NAME)));
        if (obj != null) {
            return zzgk.zzam(obj);
        }
        zzbs com_google_android_gms_internal_zzbs = (zzbs) map.get(zzket);
        return com_google_android_gms_internal_zzbs != null ? com_google_android_gms_internal_zzbs : zzgk.zzbgs();
    }
}
