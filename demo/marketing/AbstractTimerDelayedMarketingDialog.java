package com.tado.android.demo.marketing;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB/\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\u0016\u0010\u0016\u001a\u00020\u00142\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u0018H\u0016J\u001e\u0010\u0019\u001a\u00020\u00142\u0006\u0010\f\u001a\u00020\n2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u0018H\u0002R\u000e\u0010\f\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00020\nX¤\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\t\u001a\u00020\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/tado/android/demo/marketing/AbstractTimerDelayedMarketingDialog;", "Lcom/tado/android/demo/marketing/AbstractMarketingDialog;", "context", "Landroid/content/Context;", "bodyMessageRes", "", "titleRes", "icon", "Landroid/graphics/drawable/Drawable;", "millisSinceDemoStart", "", "(Landroid/content/Context;IILandroid/graphics/drawable/Drawable;J)V", "delay", "delayInMillis", "getDelayInMillis", "()J", "getMillisSinceDemoStart", "timer", "Landroid/os/CountDownTimer;", "cancel", "", "cancelTimer", "present", "callback", "Lkotlin/Function0;", "startTimer", "Companion", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: AbstractTimerDelayedMarketingDialog.kt */
public abstract class AbstractTimerDelayedMarketingDialog extends AbstractMarketingDialog {
    public static final Companion Companion = new Companion();
    protected static final long FEATURE_TIME_DELAY_MILLIS = 3000;
    private static final long STEP_IN_MILLIS = 1000;
    private long delay = 3000;
    private final long millisSinceDemoStart;
    private CountDownTimer timer;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/tado/android/demo/marketing/AbstractTimerDelayedMarketingDialog$Companion;", "", "()V", "FEATURE_TIME_DELAY_MILLIS", "", "STEP_IN_MILLIS", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: AbstractTimerDelayedMarketingDialog.kt */
    public static final class Companion {
        private Companion() {
        }
    }

    protected abstract long getDelayInMillis();

    public AbstractTimerDelayedMarketingDialog(@Nullable Context context, int bodyMessageRes, int titleRes, @NotNull Drawable icon, long millisSinceDemoStart) {
        Intrinsics.checkParameterIsNotNull(icon, SettingsJsonConstants.APP_ICON_KEY);
        super(context, bodyMessageRes, titleRes, icon);
        this.millisSinceDemoStart = millisSinceDemoStart;
    }

    protected final long getMillisSinceDemoStart() {
        return this.millisSinceDemoStart;
    }

    public void present(@NotNull Function0<Unit> callback) {
        long j = 3000;
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        if (getDelayInMillis() > 3000) {
            j = getDelayInMillis();
        }
        this.delay = j;
        startTimer(this.delay, callback);
    }

    public void cancel() {
        super.cancel();
        cancelTimer();
    }

    private final void startTimer(long delay, Function0<Unit> callback) {
        this.timer = new AbstractTimerDelayedMarketingDialog$startTimer$1(this, callback, delay, delay, STEP_IN_MILLIS);
        CountDownTimer countDownTimer = this.timer;
        if (countDownTimer != null) {
            countDownTimer.start();
        }
    }

    private final void cancelTimer() {
        CountDownTimer countDownTimer = this.timer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
