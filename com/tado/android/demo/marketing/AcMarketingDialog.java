package com.tado.android.demo.marketing;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import com.tado.C0676R;
import com.tado.android.utils.ResourceFactory;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\u0012\u0010\r\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u00058TX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0011"}, d2 = {"Lcom/tado/android/demo/marketing/AcMarketingDialog;", "Lcom/tado/android/demo/marketing/AbstractTimerDelayedMarketingDialog;", "context", "Landroid/content/Context;", "millisSinceDemoStart", "", "callback", "Lkotlin/Function0;", "", "(Landroid/content/Context;JLkotlin/jvm/functions/Function0;)V", "delayInMillis", "getDelayInMillis", "()J", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: AcMarketingDialog.kt */
public final class AcMarketingDialog extends AbstractTimerDelayedMarketingDialog {
    public static final Companion Companion = new Companion();
    private static final long DELAY_IN_MILLIS = 20000;
    private final Function0<Unit> callback;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/tado/android/demo/marketing/AcMarketingDialog$Companion;", "", "()V", "DELAY_IN_MILLIS", "", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: AcMarketingDialog.kt */
    public static final class Companion {
        private Companion() {
        }
    }

    public AcMarketingDialog(@NotNull Context context, long millisSinceDemoStart, @NotNull Function0<Unit> callback) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Drawable vectorSupportDrawable = ResourceFactory.getVectorSupportDrawable(context, C0676R.drawable.ic_demo_alert_ac);
        Intrinsics.checkExpressionValueIsNotNull(vectorSupportDrawable, "ResourceFactory.getVecto…rawable.ic_demo_alert_ac)");
        super(context, C0676R.string.demoMode_alerts_makeYourACSmart_message, C0676R.string.demoMode_alerts_makeYourACSmart_title, vectorSupportDrawable, millisSinceDemoStart);
        this.callback = callback;
    }

    protected long getDelayInMillis() {
        return 20000 - getMillisSinceDemoStart();
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dialog dialog = this;
        Button button = (Button) findViewById(C0676R.id.first_button);
        Intrinsics.checkExpressionValueIsNotNull(button, "first_button");
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        String string = getContext().getString(C0676R.string.demoMode_alerts_makeYourACSmart_learnMoreURL);
        Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.stri…YourACSmart_learnMoreURL)");
        initDialogButton(dialog, button, C0676R.string.demoMode_alerts_makeYourACSmart_learnMoreButton, new MarketingDialogOnClickListener(context, string));
        dialog = this;
        button = (Button) findViewById(C0676R.id.second_button);
        Intrinsics.checkExpressionValueIsNotNull(button, "second_button");
        initDialogButton(dialog, button, C0676R.string.demoMode_alerts_noThanksButton, new MarketingDialogOnDismissListener());
        setOnDismissListener(new AcMarketingDialog$onCreate$1(this));
    }
}
