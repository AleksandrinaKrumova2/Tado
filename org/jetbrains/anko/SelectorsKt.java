package org.jetbrains.anko;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\u001ay\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042$\b\b\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b0\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2 \b\b\u0010\u000e\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\u000fH\b\u001ar\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00072\"\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b0\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\u001e\u0010\u000e\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\u000f\u001a}\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0006\u0012\u0002\b\u00030\u00112$\b\b\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b0\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2 \b\b\u0010\u000e\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\u000fH\b¨\u0006\u0012"}, d2 = {"selector", "", "D", "Landroid/content/DialogInterface;", "Landroid/app/Fragment;", "factory", "Lkotlin/Function1;", "Landroid/content/Context;", "Lorg/jetbrains/anko/AlertBuilder;", "Lorg/jetbrains/anko/AlertBuilderFactory;", "title", "", "items", "", "onClick", "Lkotlin/Function3;", "", "Lorg/jetbrains/anko/AnkoContext;", "commons_release"}, k = 2, mv = {1, 1, 5})
/* compiled from: Selectors.kt */
public final class SelectorsKt {
    public static final <D extends DialogInterface> void selector(@NotNull AnkoContext<?> $receiver, @NotNull Function1<? super Context, ? extends AlertBuilder<? extends D>> factory, @Nullable CharSequence title, @NotNull List<? extends CharSequence> items, @NotNull Function3<? super DialogInterface, ? super CharSequence, ? super Integer, Unit> onClick) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(items, "items");
        Intrinsics.checkParameterIsNotNull(onClick, "onClick");
        selector($receiver.getCtx(), (Function1) factory, title, (List) items, (Function3) onClick);
    }

    public static final <D extends DialogInterface> void selector(@NotNull Fragment $receiver, @NotNull Function1<? super Context, ? extends AlertBuilder<? extends D>> factory, @Nullable CharSequence title, @NotNull List<? extends CharSequence> items, @NotNull Function3<? super DialogInterface, ? super CharSequence, ? super Integer, Unit> onClick) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(items, "items");
        Intrinsics.checkParameterIsNotNull(onClick, "onClick");
        selector((Context) $receiver.getActivity(), (Function1) factory, title, (List) items, (Function3) onClick);
    }

    public static final <D extends DialogInterface> void selector(@NotNull Context $receiver, @NotNull Function1<? super Context, ? extends AlertBuilder<? extends D>> factory, @Nullable CharSequence title, @NotNull List<? extends CharSequence> items, @NotNull Function3<? super DialogInterface, ? super CharSequence, ? super Integer, Unit> onClick) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(items, "items");
        Intrinsics.checkParameterIsNotNull(onClick, "onClick");
        AlertBuilder $receiver2 = (AlertBuilder) factory.invoke($receiver);
        if (title != null) {
            $receiver2.setTitle(title);
        }
        $receiver2.items((List) items, (Function3) onClick);
        $receiver2.show();
    }
}
