package com.tado.android.demo.marketing;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "onDismiss"}, k = 3, mv = {1, 1, 9})
/* compiled from: AcMarketingDialog.kt */
final class AcMarketingDialog$onCreate$1 implements OnDismissListener {
    final /* synthetic */ AcMarketingDialog this$0;

    AcMarketingDialog$onCreate$1(AcMarketingDialog acMarketingDialog) {
        this.this$0 = acMarketingDialog;
    }

    public final void onDismiss(DialogInterface it) {
        this.this$0.callback.invoke();
    }
}
