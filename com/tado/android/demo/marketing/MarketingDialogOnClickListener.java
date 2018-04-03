package com.tado.android.demo.marketing;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.IntentsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001a\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/tado/android/demo/marketing/MarketingDialogOnClickListener;", "Landroid/content/DialogInterface$OnClickListener;", "context", "Landroid/content/Context;", "uriString", "", "(Landroid/content/Context;Ljava/lang/String;)V", "weakContext", "Ljava/lang/ref/WeakReference;", "onClick", "", "dialog", "Landroid/content/DialogInterface;", "which", "", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: MarketingDialogOnClickListener.kt */
public final class MarketingDialogOnClickListener implements OnClickListener {
    private final String uriString;
    private WeakReference<Context> weakContext;

    public MarketingDialogOnClickListener(@NotNull Context context, @NotNull String uriString) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(uriString, "uriString");
        this.uriString = uriString;
        this.weakContext = new WeakReference(context);
    }

    public void onClick(@Nullable DialogInterface dialog, int which) {
        if (dialog != null) {
            dialog.dismiss();
        }
        Context context = (Context) this.weakContext.get();
        if (context != null) {
            IntentsKt.browse$default(context, this.uriString, false, 2, null);
        }
    }
}
