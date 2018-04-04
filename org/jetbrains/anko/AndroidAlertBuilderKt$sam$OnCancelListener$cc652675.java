package org.jetbrains.anko;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 1}, k = 3, mv = {1, 1, 5})
/* compiled from: AndroidAlertBuilder.kt */
final class AndroidAlertBuilderKt$sam$OnCancelListener$cc652675 implements OnCancelListener {
    private final /* synthetic */ Function1 function;

    AndroidAlertBuilderKt$sam$OnCancelListener$cc652675(Function1 function1) {
        this.function = function1;
    }

    public final /* synthetic */ void onCancel(DialogInterface p0) {
        Intrinsics.checkExpressionValueIsNotNull(this.function.invoke(p0), "invoke(...)");
    }
}
