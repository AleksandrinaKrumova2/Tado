package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Message;

final class zzfs implements zzfr {
    private Handler handler;
    final /* synthetic */ zzfo zzkjw;

    private zzfs(zzfo com_google_android_gms_tagmanager_zzfo) {
        this.zzkjw = com_google_android_gms_tagmanager_zzfo;
        this.handler = new Handler(this.zzkjw.zzkjk.getMainLooper(), new zzft(this));
    }

    private final Message obtainMessage() {
        return this.handler.obtainMessage(1, zzfo.zzkjj);
    }

    public final void cancel() {
        this.handler.removeMessages(1, zzfo.zzkjj);
    }

    public final void zzbgj() {
        this.handler.removeMessages(1, zzfo.zzkjj);
        this.handler.sendMessage(obtainMessage());
    }

    public final void zzs(long j) {
        this.handler.removeMessages(1, zzfo.zzkjj);
        this.handler.sendMessageDelayed(obtainMessage(), j);
    }
}
