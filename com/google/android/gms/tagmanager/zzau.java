package com.google.android.gms.tagmanager;

import java.util.List;

final class zzau implements Runnable {
    private /* synthetic */ List zzkey;
    private /* synthetic */ long zzkez;
    private /* synthetic */ zzat zzkfa;

    zzau(zzat com_google_android_gms_tagmanager_zzat, List list, long j) {
        this.zzkfa = com_google_android_gms_tagmanager_zzat;
        this.zzkey = list;
        this.zzkez = j;
    }

    public final void run() {
        this.zzkfa.zzb(this.zzkey, this.zzkez);
    }
}
