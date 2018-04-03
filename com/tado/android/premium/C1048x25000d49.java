package com.tado.android.premium;

import android.text.SpannableString;
import android.widget.Button;
import com.tado.C0676R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.anko.AnkoAsyncContext;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/tado/android/premium/PremiumCarouselActivity;", "invoke"}, k = 3, mv = {1, 1, 9})
/* compiled from: PremiumCarouselActivity.kt */
final class C1048x25000d49 extends Lambda implements Function1<PremiumCarouselActivity, Unit> {
    final /* synthetic */ SpannableString $sb;
    final /* synthetic */ AnkoAsyncContext receiver$0$inlined;
    final /* synthetic */ PremiumCarouselActivity$onCreate$1 this$0;

    C1048x25000d49(SpannableString spannableString, PremiumCarouselActivity$onCreate$1 premiumCarouselActivity$onCreate$1, AnkoAsyncContext ankoAsyncContext) {
        this.$sb = spannableString;
        this.this$0 = premiumCarouselActivity$onCreate$1;
        this.receiver$0$inlined = ankoAsyncContext;
        super(1);
    }

    public final void invoke(@NotNull PremiumCarouselActivity it) {
        Intrinsics.checkParameterIsNotNull(it, "it");
        ((Button) this.this$0.this$0._$_findCachedViewById(C0676R.id.premium_button)).setText(this.$sb);
    }
}
