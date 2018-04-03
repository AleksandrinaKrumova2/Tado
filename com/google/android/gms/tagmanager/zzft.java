package com.google.android.gms.tagmanager;

import android.os.Handler.Callback;
import android.os.Message;

final class zzft implements Callback {
    private /* synthetic */ zzfs zzkjx;

    zzft(zzfs com_google_android_gms_tagmanager_zzfs) {
        this.zzkjx = com_google_android_gms_tagmanager_zzfs;
    }

    public final boolean handleMessage(Message message) {
        if (1 == message.what && zzfo.zzkjj.equals(message.obj)) {
            this.zzkjx.zzkjw.dispatch();
            if (!this.zzkjx.zzkjw.isPowerSaveMode()) {
                this.zzkjx.zzs((long) this.zzkjx.zzkjw.zzkjn);
            }
        }
        return true;
    }
}
