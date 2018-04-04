package org.jetbrains.anko;

import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 1}, k = 3, mv = {1, 1, 5})
/* compiled from: AndroidAlertBuilder.kt */
final class AndroidAlertBuilderKt$sam$OnKeyListener$4694417a implements OnKeyListener {
    private final /* synthetic */ Function3 function;

    AndroidAlertBuilderKt$sam$OnKeyListener$4694417a(Function3 function3) {
        this.function = function3;
    }

    public final /* synthetic */ boolean onKey(DialogInterface p0, int p1, KeyEvent p2) {
        Object invoke = this.function.invoke(p0, Integer.valueOf(p1), p2);
        Intrinsics.checkExpressionValueIsNotNull(invoke, "invoke(...)");
        return ((Boolean) invoke).booleanValue();
    }
}
