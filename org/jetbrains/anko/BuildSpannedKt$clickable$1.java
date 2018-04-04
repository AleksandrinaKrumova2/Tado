package org.jetbrains.anko;

import android.text.style.ClickableSpan;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"org/jetbrains/anko/BuildSpannedKt$clickable$1", "Landroid/text/style/ClickableSpan;", "(Lkotlin/jvm/functions/Function1;)V", "onClick", "", "widget", "Landroid/view/View;", "commons_release"}, k = 1, mv = {1, 1, 5})
/* compiled from: buildSpanned.kt */
public final class BuildSpannedKt$clickable$1 extends ClickableSpan {
    final /* synthetic */ Function1 $onClick;

    public BuildSpannedKt$clickable$1(Function1 $captured_local_variable$0) {
        this.$onClick = $captured_local_variable$0;
    }

    public void onClick(@NotNull View widget) {
        Intrinsics.checkParameterIsNotNull(widget, "widget");
        this.$onClick.invoke(widget);
    }
}
