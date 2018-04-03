package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbo;
import com.google.android.gms.internal.zzbr;
import com.google.android.gms.internal.zzdiy;

final class zzad implements zzdi<zzdiy> {
    private /* synthetic */ zzy zzkee;

    private zzad(zzy com_google_android_gms_tagmanager_zzy) {
        this.zzkee = com_google_android_gms_tagmanager_zzy;
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        zzbr com_google_android_gms_internal_zzbr;
        zzdiy com_google_android_gms_internal_zzdiy = (zzdiy) obj;
        if (com_google_android_gms_internal_zzdiy.zzkto != null) {
            com_google_android_gms_internal_zzbr = com_google_android_gms_internal_zzdiy.zzkto;
        } else {
            zzbo com_google_android_gms_internal_zzbo = com_google_android_gms_internal_zzdiy.zzyi;
            com_google_android_gms_internal_zzbr = new zzbr();
            com_google_android_gms_internal_zzbr.zzyi = com_google_android_gms_internal_zzbo;
            com_google_android_gms_internal_zzbr.zzyh = null;
            com_google_android_gms_internal_zzbr.zzyj = com_google_android_gms_internal_zzbo.version;
        }
        this.zzkee.zza(com_google_android_gms_internal_zzbr, com_google_android_gms_internal_zzdiy.zzktn, true);
    }

    public final void zzei(int i) {
        if (!this.zzkee.zzkdz) {
            this.zzkee.zzbg(0);
        }
    }
}
