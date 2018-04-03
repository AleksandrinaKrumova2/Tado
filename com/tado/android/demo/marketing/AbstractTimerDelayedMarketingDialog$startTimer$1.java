package com.tado.android.demo.marketing;

import android.os.CountDownTimer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/tado/android/demo/marketing/AbstractTimerDelayedMarketingDialog$startTimer$1", "Landroid/os/CountDownTimer;", "(Lcom/tado/android/demo/marketing/AbstractTimerDelayedMarketingDialog;Lkotlin/jvm/functions/Function0;JJJ)V", "onFinish", "", "onTick", "millisUntilFinished", "", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: AbstractTimerDelayedMarketingDialog.kt */
public final class AbstractTimerDelayedMarketingDialog$startTimer$1 extends CountDownTimer {
    final /* synthetic */ Function0 $callback;
    final /* synthetic */ long $delay;
    final /* synthetic */ AbstractTimerDelayedMarketingDialog this$0;

    AbstractTimerDelayedMarketingDialog$startTimer$1(AbstractTimerDelayedMarketingDialog $outer, Function0 $captured_local_variable$1, long $captured_local_variable$2, long $super_call_param$3, long $super_call_param$4) {
        this.this$0 = $outer;
        this.$callback = $captured_local_variable$1;
        this.$delay = $captured_local_variable$2;
        super($super_call_param$3, $super_call_param$4);
    }

    public void onFinish() {
        this.this$0.show();
        this.$callback.invoke();
    }

    public void onTick(long millisUntilFinished) {
    }
}
