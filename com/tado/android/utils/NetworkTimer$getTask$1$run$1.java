package com.tado.android.utils;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 9})
/* compiled from: NetworkTimer.kt */
final class NetworkTimer$getTask$1$run$1 implements Runnable {
    final /* synthetic */ NetworkTimer$getTask$1 this$0;

    NetworkTimer$getTask$1$run$1(NetworkTimer$getTask$1 networkTimer$getTask$1) {
        this.this$0 = networkTimer$getTask$1;
    }

    public final void run() {
        Snitcher.start().log(3, "HvacTimer", "running " + this.this$0.getClass().getSimpleName() + " timer", new Object[0]);
        if (Util.isNetworkAvailable()) {
            this.this$0.this$0.getCall().invoke();
        }
    }
}
