package com.tado.android.premium;

import android.widget.Button;
import com.tado.C0676R;
import com.tado.android.rest.model.ContactDetails;
import com.tado.android.rest.model.HomeInfo;
import com.tado.android.settings.interfaces.GenericCallbackListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\b"}, d2 = {"com/tado/android/premium/PremiumCarouselActivity$onCreate$3", "Lcom/tado/android/settings/interfaces/GenericCallbackListener;", "Lcom/tado/android/rest/model/HomeInfo;", "(Lcom/tado/android/premium/PremiumCarouselActivity;)V", "onFailure", "", "onSuccess", "body", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: PremiumCarouselActivity.kt */
public final class PremiumCarouselActivity$onCreate$3 implements GenericCallbackListener<HomeInfo> {
    final /* synthetic */ PremiumCarouselActivity this$0;

    PremiumCarouselActivity$onCreate$3(PremiumCarouselActivity $outer) {
        this.this$0 = $outer;
    }

    public void onSuccess(@Nullable HomeInfo body) {
        String email;
        Button button;
        PremiumCarouselActivity premiumCarouselActivity = this.this$0;
        if (body != null) {
            ContactDetails contactDetails = body.getContactDetails();
            if (contactDetails != null) {
                email = contactDetails.getEmail();
                premiumCarouselActivity.setAccountName(email);
                button = (Button) this.this$0._$_findCachedViewById(C0676R.id.premium_button);
                Intrinsics.checkExpressionValueIsNotNull(button, "premium_button");
                button.setEnabled(true);
                ((Button) this.this$0._$_findCachedViewById(C0676R.id.premium_button)).setOnClickListener(new PremiumCarouselActivity$onCreate$3$onSuccess$1(this));
            }
        }
        email = null;
        premiumCarouselActivity.setAccountName(email);
        button = (Button) this.this$0._$_findCachedViewById(C0676R.id.premium_button);
        Intrinsics.checkExpressionValueIsNotNull(button, "premium_button");
        button.setEnabled(true);
        ((Button) this.this$0._$_findCachedViewById(C0676R.id.premium_button)).setOnClickListener(new PremiumCarouselActivity$onCreate$3$onSuccess$1(this));
    }

    public void onFailure() {
        this.this$0.finish();
    }
}
