package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.tagmanager.ContainerHolder.ContainerAvailableListener;

final class zzx extends Handler {
    private final ContainerAvailableListener zzkdq;
    private /* synthetic */ zzv zzkdr;

    public zzx(zzv com_google_android_gms_tagmanager_zzv, ContainerAvailableListener containerAvailableListener, Looper looper) {
        this.zzkdr = com_google_android_gms_tagmanager_zzv;
        super(looper);
        this.zzkdq = containerAvailableListener;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                this.zzkdq.onContainerAvailable(this.zzkdr, (String) message.obj);
                return;
            default:
                zzdj.m10e("Don't know how to handle this message.");
                return;
        }
    }
}
