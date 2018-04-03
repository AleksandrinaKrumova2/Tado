package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbs;

final class zzfi {
    private zzea<zzbs> zzkjb;
    private zzbs zzkjc;

    public zzfi(zzea<zzbs> com_google_android_gms_tagmanager_zzea_com_google_android_gms_internal_zzbs, zzbs com_google_android_gms_internal_zzbs) {
        this.zzkjb = com_google_android_gms_tagmanager_zzea_com_google_android_gms_internal_zzbs;
        this.zzkjc = com_google_android_gms_internal_zzbs;
    }

    public final int getSize() {
        return (this.zzkjc == null ? 0 : this.zzkjc.zzdam()) + ((zzbs) this.zzkjb.getObject()).zzdam();
    }

    public final zzea<zzbs> zzbfx() {
        return this.zzkjb;
    }

    public final zzbs zzbfy() {
        return this.zzkjc;
    }
}
