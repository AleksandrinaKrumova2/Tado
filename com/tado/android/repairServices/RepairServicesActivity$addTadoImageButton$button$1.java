package com.tado.android.repairServices;

import android.app.Activity;
import com.tado.android.analytics.AnalyticsConstants.Events;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.demo.DemoUtils;
import com.tado.android.rest.callback.presenters.GeneralErrorAlertPresenter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 9})
/* compiled from: RepairServicesActivity.kt */
final class RepairServicesActivity$addTadoImageButton$button$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ RepairServicesButton $repairServicesButton;
    final /* synthetic */ String $url;
    final /* synthetic */ RepairServicesActivity this$0;

    RepairServicesActivity$addTadoImageButton$button$1(RepairServicesActivity repairServicesActivity, String str, RepairServicesButton repairServicesButton) {
        this.this$0 = repairServicesActivity;
        this.$url = str;
        this.$repairServicesButton = repairServicesButton;
        super(0);
    }

    public final void invoke() {
        if (DemoUtils.isInDemoMode()) {
            new GeneralErrorAlertPresenter(this.this$0).onNotSupportedInDemoMode();
            return;
        }
        this.this$0.startActivity(RepairServicesWebViewActivity.Companion.newIntent(this.this$0, this.$url));
        AnalyticsHelper.trackEvent((Activity) this.this$0, Events.REPAIR_SERVICES, this.$repairServicesButton.getTrackingInfo());
    }
}
