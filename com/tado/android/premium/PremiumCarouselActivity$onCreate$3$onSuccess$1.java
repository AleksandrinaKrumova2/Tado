package com.tado.android.premium;

import android.view.View;
import android.view.View.OnClickListener;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import org.jetbrains.anko.internals.AnkoInternals;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 9})
/* compiled from: PremiumCarouselActivity.kt */
final class PremiumCarouselActivity$onCreate$3$onSuccess$1 implements OnClickListener {
    final /* synthetic */ PremiumCarouselActivity$onCreate$3 this$0;

    PremiumCarouselActivity$onCreate$3$onSuccess$1(PremiumCarouselActivity$onCreate$3 premiumCarouselActivity$onCreate$3) {
        this.this$0 = premiumCarouselActivity$onCreate$3;
    }

    public final void onClick(View it) {
        this.this$0.this$0.startActivity(AnkoInternals.createIntent(this.this$0.this$0, PremiumPaymentActivity.class, new Pair[]{TuplesKt.to("account", this.this$0.this$0.getAccountName()), TuplesKt.to(Param.PRICE, this.this$0.this$0.getPriceTag())}));
        this.this$0.this$0.finish();
    }
}
