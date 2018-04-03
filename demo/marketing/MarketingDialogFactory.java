package com.tado.android.demo.marketing;

import android.content.Context;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/tado/android/demo/marketing/MarketingDialogFactory;", "", "()V", "Companion", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: MarketingDialogFactory.kt */
public final class MarketingDialogFactory {
    public static final Companion Companion = new Companion();

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J,\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¨\u0006\u000e"}, d2 = {"Lcom/tado/android/demo/marketing/MarketingDialogFactory$Companion;", "", "()V", "create", "Lcom/tado/android/demo/marketing/AbstractMarketingDialog;", "context", "Landroid/content/Context;", "type", "Lcom/tado/android/demo/marketing/MarketingAlertTypeEnum;", "millisSinceDemoStart", "", "callback", "Lkotlin/Function0;", "", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: MarketingDialogFactory.kt */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final AbstractMarketingDialog create(@NotNull Context context, @NotNull MarketingAlertTypeEnum type, long millisSinceDemoStart, @NotNull Function0<Unit> callback) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(type, "type");
            Intrinsics.checkParameterIsNotNull(callback, "callback");
            switch (type) {
                case REQUEST_CONSULTATION:
                    return new ConsultationMarketingDialog(context, millisSinceDemoStart);
                case AC_ZONE:
                    return new AcMarketingDialog(context, millisSinceDemoStart, callback);
                case PRODUCT_FINDER:
                    return new ProductFinderMarketingDialog(context);
                case ENERGY_SAVINGS:
                    return new EnergySavingsMarketingDialog(context);
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
    }
}
