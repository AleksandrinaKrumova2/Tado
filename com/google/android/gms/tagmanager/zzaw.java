package com.google.android.gms.tagmanager;

final class zzaw implements Runnable {
    private /* synthetic */ zzat zzkfa;
    private /* synthetic */ String zzkfc;

    zzaw(zzat com_google_android_gms_tagmanager_zzat, String str) {
        this.zzkfa = com_google_android_gms_tagmanager_zzat;
        this.zzkfc = str;
    }

    public final void run() {
        this.zzkfa.zzlj(this.zzkfc);
    }
}
