package com.tado.android.demo.marketing;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import com.tado.C0676R;
import com.tado.android.utils.ResourceFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\n\u001a\u00020\u000bH\u0002J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u00058TX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0011"}, d2 = {"Lcom/tado/android/demo/marketing/ConsultationMarketingDialog;", "Lcom/tado/android/demo/marketing/AbstractTimerDelayedMarketingDialog;", "activityContext", "Landroid/content/Context;", "millisSinceDemoStart", "", "(Landroid/content/Context;J)V", "delayInMillis", "getDelayInMillis", "()J", "callUsOnClickListener", "Landroid/content/DialogInterface$OnClickListener;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: ConsultationMarketingDialog.kt */
public final class ConsultationMarketingDialog extends AbstractTimerDelayedMarketingDialog {
    public static final Companion Companion = new Companion();
    private static final long DELAY_IN_MILLIS = 40000;
    private final Context activityContext;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/tado/android/demo/marketing/ConsultationMarketingDialog$Companion;", "", "()V", "DELAY_IN_MILLIS", "", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: ConsultationMarketingDialog.kt */
    public static final class Companion {
        private Companion() {
        }
    }

    public ConsultationMarketingDialog(@NotNull Context activityContext, long millisSinceDemoStart) {
        Intrinsics.checkParameterIsNotNull(activityContext, "activityContext");
        Drawable vectorSupportDrawable = ResourceFactory.getVectorSupportDrawable(activityContext, C0676R.drawable.ic_demo_alert_consultation);
        Intrinsics.checkExpressionValueIsNotNull(vectorSupportDrawable, "ResourceFactory.getVecto…_demo_alert_consultation)");
        super(activityContext, C0676R.string.demoMode_alerts_requestConsultation_message, C0676R.string.demoMode_alerts_requestConsultation_title, vectorSupportDrawable, millisSinceDemoStart);
        this.activityContext = activityContext;
    }

    protected long getDelayInMillis() {
        return DELAY_IN_MILLIS - getMillisSinceDemoStart();
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasThirdButton(true);
        Dialog dialog = this;
        Button button = (Button) findViewById(C0676R.id.first_button);
        Intrinsics.checkExpressionValueIsNotNull(button, "first_button");
        initDialogButton(dialog, button, C0676R.string.demoMode_alerts_requestConsultation_callUsButton, callUsOnClickListener());
        dialog = this;
        button = (Button) findViewById(C0676R.id.second_button);
        Intrinsics.checkExpressionValueIsNotNull(button, "second_button");
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        String string = getContext().getString(C0676R.string.demoMode_alerts_requestConsultation_requestConsultationURL);
        Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.stri…n_requestConsultationURL)");
        initDialogButton(dialog, button, C0676R.string.demoMode_alerts_requestConsultation_requestConsultationButton, new MarketingDialogOnClickListener(context, string));
        dialog = this;
        button = (Button) findViewById(C0676R.id.third_button);
        Intrinsics.checkExpressionValueIsNotNull(button, "third_button");
        initDialogButton(dialog, button, C0676R.string.demoMode_alerts_noThanksButton, new MarketingDialogOnDismissListener());
    }

    private final OnClickListener callUsOnClickListener() {
        return new ConsultationMarketingDialog$callUsOnClickListener$1(this);
    }
}
