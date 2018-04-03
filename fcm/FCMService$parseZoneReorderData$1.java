package com.tado.android.fcm;

import android.content.Context;
import com.tado.android.app.TadoApplication;
import com.tado.android.controllers.ZoneListLoadedEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Landroid/content/Context;", "invoke"}, k = 3, mv = {1, 1, 9})
/* compiled from: FCMService.kt */
final class FCMService$parseZoneReorderData$1 extends Lambda implements Function1<Context, Unit> {
    public static final FCMService$parseZoneReorderData$1 INSTANCE = new FCMService$parseZoneReorderData$1();

    FCMService$parseZoneReorderData$1() {
        super(1);
    }

    public final void invoke(@NotNull Context $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        TadoApplication.getBus().post(new ZoneListLoadedEvent());
    }
}
