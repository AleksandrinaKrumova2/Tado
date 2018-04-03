package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbh;
import com.google.android.gms.internal.zzbs;
import java.util.List;
import java.util.Map;

final class zzaz extends zzgi {
    private static final String ID = zzbg.DATA_LAYER_WRITE.toString();
    private static final String VALUE = zzbh.VALUE.toString();
    private static final String zzkfe = zzbh.CLEAR_PERSISTENT_DATA_LAYER_PREFIX.toString();
    private final DataLayer zzkde;

    public zzaz(DataLayer dataLayer) {
        super(ID, VALUE);
        this.zzkde = dataLayer;
    }

    public final void zzx(Map<String, zzbs> map) {
        zzbs com_google_android_gms_internal_zzbs = (zzbs) map.get(VALUE);
        if (!(com_google_android_gms_internal_zzbs == null || com_google_android_gms_internal_zzbs == zzgk.zzbgm())) {
            Object zzg = zzgk.zzg(com_google_android_gms_internal_zzbs);
            if (zzg instanceof List) {
                for (Object zzg2 : (List) zzg2) {
                    if (zzg2 instanceof Map) {
                        this.zzkde.push((Map) zzg2);
                    }
                }
            }
        }
        com_google_android_gms_internal_zzbs = (zzbs) map.get(zzkfe);
        if (com_google_android_gms_internal_zzbs != null && com_google_android_gms_internal_zzbs != zzgk.zzbgm()) {
            String zzb = zzgk.zzb(com_google_android_gms_internal_zzbs);
            if (zzb != zzgk.zzbgr()) {
                this.zzkde.zzlg(zzb);
            }
        }
    }
}
