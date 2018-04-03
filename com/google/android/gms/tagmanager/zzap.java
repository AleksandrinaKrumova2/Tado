package com.google.android.gms.tagmanager;

import java.util.List;

final class zzap implements zzaq {
    private /* synthetic */ DataLayer zzkes;

    zzap(DataLayer dataLayer) {
        this.zzkes = dataLayer;
    }

    public final void zzak(List<zza> list) {
        for (zza com_google_android_gms_tagmanager_DataLayer_zza : list) {
            this.zzkes.zzy(DataLayer.zzn(com_google_android_gms_tagmanager_DataLayer_zza.zzbhb, com_google_android_gms_tagmanager_DataLayer_zza.mValue));
        }
        this.zzkes.zzker.countDown();
    }
}
