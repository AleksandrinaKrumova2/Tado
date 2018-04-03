package com.google.android.gms.tagmanager;

final class zzed implements zzfx {
    private /* synthetic */ zzec zzkhh;

    zzed(zzec com_google_android_gms_tagmanager_zzec) {
        this.zzkhh = com_google_android_gms_tagmanager_zzec;
    }

    public final void zza(zzbx com_google_android_gms_tagmanager_zzbx) {
        this.zzkhh.zzp(com_google_android_gms_tagmanager_zzbx.zzbey());
    }

    public final void zzb(zzbx com_google_android_gms_tagmanager_zzbx) {
        this.zzkhh.zzp(com_google_android_gms_tagmanager_zzbx.zzbey());
        zzdj.m11v("Permanent failure dispatching hitId: " + com_google_android_gms_tagmanager_zzbx.zzbey());
    }

    public final void zzc(zzbx com_google_android_gms_tagmanager_zzbx) {
        long zzbez = com_google_android_gms_tagmanager_zzbx.zzbez();
        if (zzbez == 0) {
            this.zzkhh.zzh(com_google_android_gms_tagmanager_zzbx.zzbey(), this.zzkhh.zzata.currentTimeMillis());
        } else if (zzbez + 14400000 < this.zzkhh.zzata.currentTimeMillis()) {
            this.zzkhh.zzp(com_google_android_gms_tagmanager_zzbx.zzbey());
            zzdj.m11v("Giving up on failed hitId: " + com_google_android_gms_tagmanager_zzbx.zzbey());
        }
    }
}
