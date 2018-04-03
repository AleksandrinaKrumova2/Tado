package com.tado.android.controllers;

import com.tado.android.utils.NetworkTimer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/tado/android/controllers/HomeStateTimer;", "Lcom/tado/android/utils/NetworkTimer;", "()V", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: HomeStateTimer.kt */
public final class HomeStateTimer extends NetworkTimer {

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 9})
    /* compiled from: HomeStateTimer.kt */
    static final class C07441 extends Lambda implements Function0<Unit> {
        public static final C07441 INSTANCE = new C07441();

        C07441() {
            super(0);
        }

        public final void invoke() {
            ZoneController.INSTANCE.callGetHomeState();
        }
    }

    public HomeStateTimer() {
        super(15000, 0, C07441.INSTANCE);
    }
}
