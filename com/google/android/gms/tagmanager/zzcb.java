package com.google.android.gms.tagmanager;

final class zzcb implements Runnable {
    private /* synthetic */ String zzcml;
    private /* synthetic */ zzbz zzkgb;
    private /* synthetic */ long zzkgc;
    private /* synthetic */ zzca zzkgd;

    zzcb(zzca com_google_android_gms_tagmanager_zzca, zzbz com_google_android_gms_tagmanager_zzbz, long j, String str) {
        this.zzkgd = com_google_android_gms_tagmanager_zzca;
        this.zzkgb = com_google_android_gms_tagmanager_zzbz;
        this.zzkgc = j;
        this.zzcml = str;
    }

    public final void run() {
        if (this.zzkgd.zzkga == null) {
            zzfo zzbgg = zzfo.zzbgg();
            zzbgg.zza(this.zzkgd.mContext, this.zzkgb);
            this.zzkgd.zzkga = zzbgg.zzbgh();
        }
        this.zzkgd.zzkga.zzb(this.zzkgc, this.zzcml);
    }
}
