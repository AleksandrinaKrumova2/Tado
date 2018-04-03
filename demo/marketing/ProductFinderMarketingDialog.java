package com.tado.android.demo.marketing;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import com.tado.C0676R;
import com.tado.android.utils.ResourceFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014¨\u0006\t"}, d2 = {"Lcom/tado/android/demo/marketing/ProductFinderMarketingDialog;", "Lcom/tado/android/demo/marketing/AbstractMarketingDialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: ProductFinderMarketingDialog.kt */
public final class ProductFinderMarketingDialog extends AbstractMarketingDialog {
    public ProductFinderMarketingDialog(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Drawable vectorSupportDrawable = ResourceFactory.getVectorSupportDrawable(context, C0676R.drawable.ic_demo_alert_product_finder);
        Intrinsics.checkExpressionValueIsNotNull(vectorSupportDrawable, "ResourceFactory.getVecto…emo_alert_product_finder)");
        super(context, C0676R.string.demoMode_alerts_findProduct_message, C0676R.string.demoMode_alerts_findProduct_title, vectorSupportDrawable);
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dialog dialog = this;
        Button button = (Button) findViewById(C0676R.id.first_button);
        Intrinsics.checkExpressionValueIsNotNull(button, "first_button");
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        String string = getContext().getString(C0676R.string.demoMode_alerts_findProduct_findSetupURL);
        Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.stri…findProduct_findSetupURL)");
        initDialogButton(dialog, button, C0676R.string.demoMode_alerts_findProduct_findSetupButton, new MarketingDialogOnClickListener(context, string));
        dialog = this;
        button = (Button) findViewById(C0676R.id.second_button);
        Intrinsics.checkExpressionValueIsNotNull(button, "second_button");
        initDialogButton(dialog, button, C0676R.string.demoMode_alerts_noThanksButton, new MarketingDialogOnDismissListener());
    }
}
