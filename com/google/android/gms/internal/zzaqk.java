package com.google.android.gms.internal;

import android.content.ComponentName;

final class zzaqk implements Runnable {
    private /* synthetic */ ComponentName val$name;
    private /* synthetic */ zzaqi zzduf;

    zzaqk(zzaqi com_google_android_gms_internal_zzaqi, ComponentName componentName) {
        this.zzduf = com_google_android_gms_internal_zzaqi;
        this.val$name = componentName;
    }

    public final void run() {
        this.zzduf.zzdub.onServiceDisconnected(this.val$name);
    }
}
