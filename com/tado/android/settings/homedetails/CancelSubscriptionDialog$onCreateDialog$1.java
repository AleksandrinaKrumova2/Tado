package com.tado.android.settings.homedetails;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v4.app.FragmentActivity;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.rest.service.TadoApiService;
import com.tado.android.settings.homedetails.data.HomeRepository;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "dialog", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "which", "", "onClick"}, k = 3, mv = {1, 1, 9})
/* compiled from: CancelSubscriptionDialog.kt */
final class CancelSubscriptionDialog$onCreateDialog$1 implements OnClickListener {
    final /* synthetic */ CancelSubscriptionDialog this$0;

    CancelSubscriptionDialog$onCreateDialog$1(CancelSubscriptionDialog cancelSubscriptionDialog) {
        this.this$0 = cancelSubscriptionDialog;
    }

    public final void onClick(DialogInterface dialog, int which) {
        TadoApiService tadoRestService = RestServiceGenerator.getTadoRestService();
        Intrinsics.checkExpressionValueIsNotNull(tadoRestService, "RestServiceGenerator.getTadoRestService()");
        HomeRepository homeRepository = new HomeRepository(tadoRestService);
        FragmentActivity activity = this.this$0.getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity!!");
        homeRepository.cancelPremiumSubscription(activity, this.this$0.getListener());
        this.this$0.dismiss();
    }
}
