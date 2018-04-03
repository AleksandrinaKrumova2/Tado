package com.tado.android.demo.marketing;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.widget.Button;
import android.widget.TextView;
import com.tado.C0676R;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B+\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ*\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0001\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0017H\u0004J\u0012\u0010\u0018\u001a\u00020\u00112\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\u0016\u0010\u001b\u001a\u00020\u00112\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00110\u001dH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/tado/android/demo/marketing/AbstractMarketingDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "bodyMessageRes", "", "titleRes", "icon", "Landroid/graphics/drawable/Drawable;", "(Landroid/content/Context;IILandroid/graphics/drawable/Drawable;)V", "hasThirdButton", "", "getHasThirdButton", "()Z", "setHasThirdButton", "(Z)V", "initDialogButton", "", "dialog", "button", "Landroid/widget/Button;", "title", "listener", "Landroid/content/DialogInterface$OnClickListener;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "present", "callback", "Lkotlin/Function0;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: AbstractMarketingDialog.kt */
public abstract class AbstractMarketingDialog extends Dialog {
    private final int bodyMessageRes;
    private boolean hasThirdButton;
    private final Drawable icon;
    private final int titleRes;

    public AbstractMarketingDialog(@Nullable Context context, @StringRes int bodyMessageRes, @StringRes int titleRes, @NotNull Drawable icon) {
        Intrinsics.checkParameterIsNotNull(icon, SettingsJsonConstants.APP_ICON_KEY);
        super(context);
        this.bodyMessageRes = bodyMessageRes;
        this.titleRes = titleRes;
        this.icon = icon;
    }

    protected final boolean getHasThirdButton() {
        return this.hasThirdButton;
    }

    protected final void setHasThirdButton(boolean <set-?>) {
        this.hasThirdButton = <set-?>;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.dialog_marketing_layout);
        ((TextView) findViewById(C0676R.id.message)).setText(this.bodyMessageRes);
        ((TextView) findViewById(C0676R.id.message)).setCompoundDrawablesWithIntrinsicBounds(this.icon, null, null, null);
        ((TextView) findViewById(C0676R.id.title)).setText(this.titleRes);
    }

    public void present(@NotNull Function0<Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        show();
        callback.invoke();
    }

    protected final void initDialogButton(@NotNull Dialog dialog, @NotNull Button button, @StringRes int title, @NotNull OnClickListener listener) {
        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
        Intrinsics.checkParameterIsNotNull(button, "button");
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Button $receiver = button;
        $receiver.setText(title);
        $receiver.setVisibility(0);
        $receiver.setOnClickListener(new AbstractMarketingDialog$initDialogButton$$inlined$with$lambda$1(title, listener, dialog));
    }
}
