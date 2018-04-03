package com.tado.android.controllers;

import com.tado.android.utils.NetworkTimer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/tado/android/controllers/MobileDevicesTimer;", "Lcom/tado/android/utils/NetworkTimer;", "()V", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: MobileDevicesTimer.kt */
public final class MobileDevicesTimer extends NetworkTimer {

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 9})
    /* compiled from: MobileDevicesTimer.kt */
    static final class C07531 extends Lambda implements Function0<Unit> {
        public static final C07531 INSTANCE = new C07531();

        C07531() {
            super(0);
        }

        public final void invoke() {
            ZoneController.INSTANCE.callGetMobileDevice();
        }
    }

    public MobileDevicesTimer() {
        super(60000, 0, C07531.INSTANCE);
    }
}
