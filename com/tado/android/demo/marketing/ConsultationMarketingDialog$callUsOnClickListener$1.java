package com.tado.android.demo.marketing;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.app.AppCompatActivity;
import com.tado.android.demo.phone.view.PhoneListFragment;
import kotlin.Metadata;
import kotlin.TypeCastException;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "dialog", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "onClick"}, k = 3, mv = {1, 1, 9})
/* compiled from: ConsultationMarketingDialog.kt */
final class ConsultationMarketingDialog$callUsOnClickListener$1 implements OnClickListener {
    final /* synthetic */ ConsultationMarketingDialog this$0;

    ConsultationMarketingDialog$callUsOnClickListener$1(ConsultationMarketingDialog consultationMarketingDialog) {
        this.this$0 = consultationMarketingDialog;
    }

    public final void onClick(DialogInterface dialog, int $noName_1) {
        dialog.dismiss();
        PhoneListFragment phoneListDialogFragment = new PhoneListFragment();
        Context access$getActivityContext$p = this.this$0.activityContext;
        if (access$getActivityContext$p == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.support.v7.app.AppCompatActivity");
        }
        phoneListDialogFragment.show(((AppCompatActivity) access$getActivityContext$p).getSupportFragmentManager(), "phoneList");
    }
}
