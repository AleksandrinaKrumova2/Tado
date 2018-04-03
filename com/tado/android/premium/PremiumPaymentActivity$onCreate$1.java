package com.tado.android.premium;

import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.View.OnClickListener;
import com.tado.C0676R;
import com.tado.android.rest.model.HomeInfo.LicenseEnum;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.rest.service.TadoApiService;
import com.tado.android.settings.homedetails.data.HomeRepository;
import com.tado.android.settings.interfaces.GenericCallbackListener;
import com.tado.android.utils.UserConfig;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.internals.AnkoInternals;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 9})
/* compiled from: PremiumPaymentActivity.kt */
final class PremiumPaymentActivity$onCreate$1 implements OnClickListener {
    final /* synthetic */ String $accountName;
    final /* synthetic */ PremiumPaymentActivity this$0;

    PremiumPaymentActivity$onCreate$1(PremiumPaymentActivity premiumPaymentActivity, String str) {
        this.this$0 = premiumPaymentActivity;
        this.$accountName = str;
    }

    public final void onClick(View it) {
        AppCompatButton appCompatButton = (AppCompatButton) this.this$0._$_findCachedViewById(C0676R.id.premium_pay_button);
        Intrinsics.checkExpressionValueIsNotNull(appCompatButton, "premium_pay_button");
        appCompatButton.setEnabled(false);
        TadoApiService tadoRestService = RestServiceGenerator.getTadoRestService();
        Intrinsics.checkExpressionValueIsNotNull(tadoRestService, "RestServiceGenerator.getTadoRestService()");
        new HomeRepository(tadoRestService).upgradeToPremium(this.this$0, new GenericCallbackListener<Void>() {
            public void onSuccess(@Nullable Void body) {
                UserConfig.setLicense(LicenseEnum.PREMIUM);
                AnkoInternals.internalStartActivity(this.this$0, PremiumConfirmationActivity.class, new Pair[]{TuplesKt.to("account", this.$accountName)});
                this.this$0.finish();
            }

            public void onFailure() {
                AppCompatButton appCompatButton = (AppCompatButton) this.this$0._$_findCachedViewById(C0676R.id.premium_pay_button);
                Intrinsics.checkExpressionValueIsNotNull(appCompatButton, "premium_pay_button");
                appCompatButton.setEnabled(true);
            }
        });
    }
}
