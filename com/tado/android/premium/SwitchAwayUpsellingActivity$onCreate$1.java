package com.tado.android.premium;

import android.view.View;
import android.view.View.OnClickListener;
import kotlin.Metadata;
import kotlin.Pair;
import org.jetbrains.anko.internals.AnkoInternals;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 9})
/* compiled from: SwitchAwayUpsellingActivity.kt */
final class SwitchAwayUpsellingActivity$onCreate$1 implements OnClickListener {
    final /* synthetic */ SwitchAwayUpsellingActivity this$0;

    SwitchAwayUpsellingActivity$onCreate$1(SwitchAwayUpsellingActivity switchAwayUpsellingActivity) {
        this.this$0 = switchAwayUpsellingActivity;
    }

    public final void onClick(View it) {
        AnkoInternals.internalStartActivity(this.this$0, PremiumCarouselActivity.class, new Pair[0]);
        this.this$0.finish();
    }
}
