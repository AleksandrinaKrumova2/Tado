package com.google.android.gms.tagmanager;

final class zzav implements Runnable {
    private /* synthetic */ zzat zzkfa;
    private /* synthetic */ zzaq zzkfb;

    zzav(zzat com_google_android_gms_tagmanager_zzat, zzaq com_google_android_gms_tagmanager_zzaq) {
        this.zzkfa = com_google_android_gms_tagmanager_zzat;
        this.zzkfb = com_google_android_gms_tagmanager_zzaq;
    }

    public final void run() {
        this.zzkfb.zzak(this.zzkfa.zzbel());
    }
}
