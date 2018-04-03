package com.tado.android.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\b\u0010\u0019\u001a\u00020\u001aH\u0002J\u0006\u0010\u001b\u001a\u00020\u0007J\u0006\u0010\u001c\u001a\u00020\u0007R\u0014\u0010\t\u001a\u00020\nXD¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006\u001d"}, d2 = {"Lcom/tado/android/utils/NetworkTimer;", "", "intervalMillis", "", "delayMillis", "call", "Lkotlin/Function0;", "", "(JJLkotlin/jvm/functions/Function0;)V", "TAG", "", "getTAG", "()Ljava/lang/String;", "getCall", "()Lkotlin/jvm/functions/Function0;", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "timer", "Ljava/util/Timer;", "getTimer", "()Ljava/util/Timer;", "setTimer", "(Ljava/util/Timer;)V", "getTask", "Ljava/util/TimerTask;", "start", "stop", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: NetworkTimer.kt */
public abstract class NetworkTimer {
    @NotNull
    private final String TAG = "Timer";
    @NotNull
    private final Function0<Unit> call;
    private long delayMillis;
    @NotNull
    private final Handler handler = new Handler(Looper.getMainLooper());
    private long intervalMillis;
    @Nullable
    private Timer timer;

    public NetworkTimer(long intervalMillis, long delayMillis, @NotNull Function0<Unit> call) {
        Intrinsics.checkParameterIsNotNull(call, NotificationCompat.CATEGORY_CALL);
        this.intervalMillis = intervalMillis;
        this.delayMillis = delayMillis;
        this.call = call;
    }

    @NotNull
    public final Function0<Unit> getCall() {
        return this.call;
    }

    @NotNull
    public final String getTAG() {
        return this.TAG;
    }

    @Nullable
    public final Timer getTimer() {
        return this.timer;
    }

    public final void setTimer(@Nullable Timer <set-?>) {
        this.timer = <set-?>;
    }

    @NotNull
    public final Handler getHandler() {
        return this.handler;
    }

    private final TimerTask getTask() {
        return new NetworkTimer$getTask$1(this);
    }

    public final void start() {
        Snitcher.start().log(3, this.TAG, "starting " + getClass().getSimpleName() + " timer", new Object[0]);
        this.timer = new Timer();
        Timer timer = this.timer;
        if (timer != null) {
            timer.schedule(getTask(), this.delayMillis, this.intervalMillis);
        }
    }

    public final void stop() {
        Snitcher.start().log(3, this.TAG, "stopping " + getClass().getSimpleName() + " timer", new Object[0]);
        Timer timer = this.timer;
        if (timer != null) {
            timer.cancel();
        }
        timer = this.timer;
        if (timer != null) {
            timer.purge();
        }
    }
}
