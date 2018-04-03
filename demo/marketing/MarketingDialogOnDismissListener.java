package com.tado.android.demo.marketing;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/tado/android/demo/marketing/MarketingDialogOnDismissListener;", "Landroid/content/DialogInterface$OnClickListener;", "()V", "onClick", "", "dialog", "Landroid/content/DialogInterface;", "which", "", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: MarketingDialogOnDismissListener.kt */
public final class MarketingDialogOnDismissListener implements OnClickListener {
    public void onClick(@Nullable DialogInterface dialog, int which) {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
