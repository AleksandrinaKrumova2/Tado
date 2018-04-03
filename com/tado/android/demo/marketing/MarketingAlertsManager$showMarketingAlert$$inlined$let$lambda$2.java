package com.tado.android.demo.marketing;

import android.content.Context;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 9})
/* compiled from: MarketingAlertsManager.kt */
final class MarketingAlertsManager$showMarketingAlert$$inlined$let$lambda$2 extends Lambda implements Function0<Unit> {
    final /* synthetic */ Context $context$inlined;
    final /* synthetic */ MarketingAlertTypeEnum $type$inlined;

    MarketingAlertsManager$showMarketingAlert$$inlined$let$lambda$2(Context context, MarketingAlertTypeEnum marketingAlertTypeEnum) {
        this.$context$inlined = context;
        this.$type$inlined = marketingAlertTypeEnum;
        super(0);
    }

    public final void invoke() {
        MarketingScreenState marketingScreenState = (MarketingScreenState) MarketingAlertsManager.state.get(this.$type$inlined);
        if (marketingScreenState != null) {
            marketingScreenState.setAlertShown(true);
        }
    }
}
