package org.jetbrains.anko.custom;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.AnkoAsyncContext;
import org.jetbrains.anko.AsyncKt;
import org.jetbrains.anko.AsyncKt$runOnUiThread$2;
import org.jetbrains.anko.BackgroundExecutor;
import org.jetbrains.anko.internals.AnkoInternals;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000H\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aD\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\u0004\b\u0000\u0010\u0003*\u0002H\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u001d\u0010\u0006\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020\u00020\u0007¢\u0006\u0002\b\tH\u0007¢\u0006\u0002\u0010\n\u001a<\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\u0004\b\u0000\u0010\u0003*\u0002H\u00032\u001d\u0010\u0006\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020\u00020\u0007¢\u0006\u0002\b\tH\u0007¢\u0006\u0002\u0010\u000b\u001aJ\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\r*\u0002H\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u001d\u0010\u0006\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\r0\u0007¢\u0006\u0002\b\tH\u0007¢\u0006\u0002\u0010\n\u001aB\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\r*\u0002H\u00032\u001d\u0010\u0006\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\r0\u0007¢\u0006\u0002\b\tH\u0007¢\u0006\u0002\u0010\u000b\u001a2\u0010\u000e\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u00020\u0007H\b¢\u0006\u0002\u0010\u0011\u001a-\u0010\u000e\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00122\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u00020\u0007H\b\u001a\u001d\u0010\u0013\u001a\u00020\u0002*\u00020\u00142\u000e\b\u0004\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u0015H\b\u001a%\u0010\u0013\u001a\u00020\u0002*\u00020\u00162\u0017\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00020\u0007¢\u0006\u0002\b\tH\u0007\u001a/\u0010\u0017\u001a\u0002H\u0003\"\b\b\u0000\u0010\u0003*\u00020\u0018*\u0002H\u00032\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00020\u0007H\u0007¢\u0006\u0002\u0010\u0019¨\u0006\u001a"}, d2 = {"async", "Ljava/util/concurrent/Future;", "", "T", "executorService", "Ljava/util/concurrent/ExecutorService;", "task", "Lkotlin/Function1;", "Lorg/jetbrains/anko/AnkoAsyncContext;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Ljava/util/concurrent/ExecutorService;Lkotlin/jvm/functions/Function1;)Ljava/util/concurrent/Future;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/util/concurrent/Future;", "asyncResult", "R", "forEachReversed", "", "f", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "", "onUiThread", "Landroid/app/Fragment;", "Lkotlin/Function0;", "Landroid/content/Context;", "style", "Landroid/view/View;", "(Landroid/view/View;Lkotlin/jvm/functions/Function1;)Landroid/view/View;", "commons_release"}, k = 2, mv = {1, 1, 5})
/* compiled from: Deprecated.kt */
public final class DeprecatedKt {
    @Deprecated(message = "Use forEachReversedByIndex(f) instead.", replaceWith = @ReplaceWith(expression = "forEachReversedByIndex(f)", imports = {"org.jetbrains.anko.collections.forEachReversedByIndex"}))
    public static final <T> void forEachReversed(@NotNull T[] $receiver, @NotNull Function1<? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        T[] $receiver$iv = $receiver;
        for (int i$iv = $receiver$iv.length - 1; i$iv >= 0; i$iv--) {
            f.invoke($receiver$iv[i$iv]);
        }
    }

    @Deprecated(message = "Use forEachReversedByIndex(f) instead.", replaceWith = @ReplaceWith(expression = "forEachReversedByIndex(f)", imports = {"org.jetbrains.anko.collections.forEachReversedByIndex"}))
    public static final <T> void forEachReversed(@NotNull List<? extends T> $receiver, @NotNull Function1<? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        List<? extends T> $receiver$iv = $receiver;
        for (int i$iv = $receiver$iv.size() - 1; i$iv >= 0; i$iv--) {
            f.invoke($receiver$iv.get(i$iv));
        }
    }

    @Deprecated(message = "Use runOnUiThread(f) instead.", replaceWith = @ReplaceWith(expression = "runOnUiThread(f)", imports = {}))
    public static final void onUiThread(@NotNull Fragment $receiver, @NotNull Function0<Unit> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        Activity activity = $receiver.getActivity();
        if (activity != null) {
            activity.runOnUiThread(new AsyncKt$runOnUiThread$2(f));
        }
    }

    @Deprecated(message = "Use runOnUiThread(f) instead.", replaceWith = @ReplaceWith(expression = "runOnUiThread(f)", imports = {}))
    public static final void onUiThread(@NotNull Context $receiver, @NotNull Function1<? super Context, Unit> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        AsyncKt.runOnUiThread($receiver, (Function1) f);
    }

    @NotNull
    @Deprecated(message = "Use doAsync(task) instead.", replaceWith = @ReplaceWith(expression = "doAsync(task)", imports = {}))
    public static final <T> Future<Unit> async(T $receiver, @NotNull Function1<? super AnkoAsyncContext<T>, Unit> task) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        return BackgroundExecutor.INSTANCE.submit(new DeprecatedKt$async$1(task, new AnkoAsyncContext(new WeakReference($receiver))));
    }

    @NotNull
    @Deprecated(message = "Use doAsync(executorService, task) instead.", replaceWith = @ReplaceWith(expression = "doAsync(executorService, task)", imports = {}))
    public static final <T> Future<Unit> async(T $receiver, @NotNull ExecutorService executorService, @NotNull Function1<? super AnkoAsyncContext<T>, Unit> task) {
        Intrinsics.checkParameterIsNotNull(executorService, "executorService");
        Intrinsics.checkParameterIsNotNull(task, "task");
        Future<Unit> submit = executorService.submit(new DeprecatedKt$async$2(task, new AnkoAsyncContext(new WeakReference($receiver))));
        Intrinsics.checkExpressionValueIsNotNull(submit, "executorService.submit<Unit> { context.task() }");
        return submit;
    }

    @NotNull
    @Deprecated(message = "Use doAsyncResult(task) instead.", replaceWith = @ReplaceWith(expression = "doAsyncResult(task)", imports = {}))
    public static final <T, R> Future<R> asyncResult(T $receiver, @NotNull Function1<? super AnkoAsyncContext<T>, ? extends R> task) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        return BackgroundExecutor.INSTANCE.submit(new DeprecatedKt$asyncResult$1(task, new AnkoAsyncContext(new WeakReference($receiver))));
    }

    @NotNull
    @Deprecated(message = "Use doAsyncResult(executorService, task) instead.", replaceWith = @ReplaceWith(expression = "doAsyncResult(executorService, task)", imports = {}))
    public static final <T, R> Future<R> asyncResult(T $receiver, @NotNull ExecutorService executorService, @NotNull Function1<? super AnkoAsyncContext<T>, ? extends R> task) {
        Intrinsics.checkParameterIsNotNull(executorService, "executorService");
        Intrinsics.checkParameterIsNotNull(task, "task");
        Future<R> submit = executorService.submit(new DeprecatedKt$asyncResult$2(task, new AnkoAsyncContext(new WeakReference($receiver))));
        Intrinsics.checkExpressionValueIsNotNull(submit, "executorService.submit<R> { context.task() }");
        return submit;
    }

    @NotNull
    @Deprecated(message = "Use applyRecursively(block) instead.", replaceWith = @ReplaceWith(expression = "applyRecursively(style)", imports = {}))
    public static final <T extends View> T style(@NotNull T $receiver, @NotNull Function1<? super View, Unit> style) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(style, "style");
        T $receiver$iv = $receiver;
        AnkoInternals.INSTANCE.applyRecursively($receiver$iv, style);
        return $receiver$iv;
    }
}
