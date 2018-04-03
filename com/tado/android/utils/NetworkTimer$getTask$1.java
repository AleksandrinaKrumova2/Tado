package com.tado.android.utils;

import java.util.TimerTask;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"com/tado/android/utils/NetworkTimer$getTask$1", "Ljava/util/TimerTask;", "(Lcom/tado/android/utils/NetworkTimer;)V", "run", "", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: NetworkTimer.kt */
public final class NetworkTimer$getTask$1 extends TimerTask {
    final /* synthetic */ NetworkTimer this$0;

    NetworkTimer$getTask$1(NetworkTimer $outer) {
        this.this$0 = $outer;
    }

    public void run() {
        this.this$0.getHandler().post(new NetworkTimer$getTask$1$run$1(this));
    }
}
