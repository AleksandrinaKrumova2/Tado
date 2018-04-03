package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzdiy;

final class zzfa implements Runnable {
    private /* synthetic */ zzey zzkij;
    private /* synthetic */ zzdiy zzkik;

    zzfa(zzey com_google_android_gms_tagmanager_zzey, zzdiy com_google_android_gms_internal_zzdiy) {
        this.zzkij = com_google_android_gms_tagmanager_zzey;
        this.zzkik = com_google_android_gms_internal_zzdiy;
    }

    public final void run() {
        this.zzkij.zzb(this.zzkik);
    }
}
