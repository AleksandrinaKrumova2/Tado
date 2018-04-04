package org.jetbrains.anko;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000X\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a.\u0010\u0004\u001a\u00020\u0005\"\b\b\u0000\u0010\u0006*\u00020\u0007*\b\u0012\u0004\u0012\u0002H\u00060\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\u00030\u0001\u001a;\u0010\u0004\u001a\u00020\u0005\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\n0\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\u00030\u0001H\u0007¢\u0006\u0002\b\u000b\u001a9\u0010\f\u001a\u00020\u0005\"\b\b\u0000\u0010\u0006*\u00020\u0007*\b\u0012\u0004\u0012\u0002H\u00060\b2\u001d\u0010\t\u001a\u0019\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\u00030\r¢\u0006\u0002\b\u000f\u001aF\u0010\f\u001a\u00020\u0005\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\n0\b2\u001d\u0010\t\u001a\u0019\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\u00030\r¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0002\b\u0010\u001aZ\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u0012\"\u0004\b\u0000\u0010\u0006*\u0002H\u00062\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00012\u0006\u0010\u0014\u001a\u00020\u00152\u001d\u0010\u0016\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\b\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u000f¢\u0006\u0002\u0010\u0017\u001aR\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u0012\"\u0004\b\u0000\u0010\u0006*\u0002H\u00062\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00012\u001d\u0010\u0016\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\b\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u000f¢\u0006\u0002\u0010\u0018\u001a`\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u0012\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u001a*\u0002H\u00062\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00012\u0006\u0010\u0014\u001a\u00020\u00152\u001d\u0010\u0016\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\b\u0012\u0004\u0012\u0002H\u001a0\u0001¢\u0006\u0002\b\u000f¢\u0006\u0002\u0010\u0017\u001aX\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u0012\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u001a*\u0002H\u00062\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00012\u001d\u0010\u0016\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\b\u0012\u0004\u0012\u0002H\u001a0\u0001¢\u0006\u0002\b\u000f¢\u0006\u0002\u0010\u0018\u001a.\u0010\u001b\u001a\u00020\u0005\"\b\b\u0000\u0010\u0006*\u00020\u001c*\b\u0012\u0004\u0012\u0002H\u00060\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\u00030\u0001\u001a9\u0010\u001d\u001a\u00020\u0005\"\b\b\u0000\u0010\u0006*\u00020\u001c*\b\u0012\u0004\u0012\u0002H\u00060\b2\u001d\u0010\t\u001a\u0019\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\u00030\r¢\u0006\u0002\b\u000f\u001a,\u0010\u001e\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00060\b2\u0014\u0010\t\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u0001H\u0006\u0012\u0004\u0012\u00020\u00030\u0001\u001a\u001d\u0010\u001f\u001a\u00020\u0003*\u00020\u001c2\u000e\b\u0004\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030 H\b\u001a#\u0010\u001f\u001a\u00020\u0003*\u00020\u000e2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u000f\u001a*\u0010!\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00060\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\u00030\u0001\"\u001a\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"crashLogger", "Lkotlin/Function1;", "", "", "activityUiThread", "", "T", "Landroid/app/Activity;", "Lorg/jetbrains/anko/AnkoAsyncContext;", "f", "Lorg/jetbrains/anko/AnkoContext;", "activityContextUiThread", "activityUiThreadWithContext", "Lkotlin/Function2;", "Landroid/content/Context;", "Lkotlin/ExtensionFunctionType;", "activityContextUiThreadWithContext", "doAsync", "Ljava/util/concurrent/Future;", "exceptionHandler", "executorService", "Ljava/util/concurrent/ExecutorService;", "task", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Ljava/util/concurrent/ExecutorService;Lkotlin/jvm/functions/Function1;)Ljava/util/concurrent/Future;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/util/concurrent/Future;", "doAsyncResult", "R", "fragmentUiThread", "Landroid/app/Fragment;", "fragmentUiThreadWithContext", "onComplete", "runOnUiThread", "Lkotlin/Function0;", "uiThread", "commons_release"}, k = 2, mv = {1, 1, 5})
/* compiled from: Async.kt */
public final class AsyncKt {
    private static final Function1<Throwable, Unit> crashLogger = AsyncKt$crashLogger$1.INSTANCE;

    public static final void runOnUiThread(@NotNull Context $receiver, @NotNull Function1<? super Context, Unit> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        if (Intrinsics.areEqual(ContextHelper.INSTANCE.getMainThread(), Thread.currentThread())) {
            f.invoke($receiver);
        } else {
            ContextHelper.INSTANCE.getHandler().post(new AsyncKt$runOnUiThread$1($receiver, f));
        }
    }

    public static final void runOnUiThread(@NotNull Fragment $receiver, @NotNull Function0<Unit> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        Activity activity = $receiver.getActivity();
        if (activity != null) {
            activity.runOnUiThread(new AsyncKt$runOnUiThread$2(f));
        }
    }

    public static final <T> void onComplete(@NotNull AnkoAsyncContext<T> $receiver, @NotNull Function1<? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        Object ref = $receiver.getWeakRef().get();
        if (Intrinsics.areEqual(ContextHelper.INSTANCE.getMainThread(), Thread.currentThread())) {
            f.invoke(ref);
        } else {
            ContextHelper.INSTANCE.getHandler().post(new AsyncKt$onComplete$1(f, ref));
        }
    }

    public static final <T> boolean uiThread(@NotNull AnkoAsyncContext<T> $receiver, @NotNull Function1<? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        Object ref = $receiver.getWeakRef().get();
        if (ref == null) {
            return false;
        }
        if (Intrinsics.areEqual(ContextHelper.INSTANCE.getMainThread(), Thread.currentThread())) {
            f.invoke(ref);
        } else {
            ContextHelper.INSTANCE.getHandler().post(new AsyncKt$uiThread$1(f, ref));
        }
        return true;
    }

    public static final <T extends Activity> boolean activityUiThread(@NotNull AnkoAsyncContext<T> $receiver, @NotNull Function1<? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        Activity activity = (Activity) $receiver.getWeakRef().get();
        if (activity == null || activity.isFinishing()) {
            return false;
        }
        activity.runOnUiThread(new AsyncKt$activityUiThread$1(f, activity));
        return true;
    }

    public static final <T extends Activity> boolean activityUiThreadWithContext(@NotNull AnkoAsyncContext<T> $receiver, @NotNull Function2<? super Context, ? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        Activity activity = (Activity) $receiver.getWeakRef().get();
        if (activity == null || activity.isFinishing()) {
            return false;
        }
        activity.runOnUiThread(new AsyncKt$activityUiThreadWithContext$1(f, activity));
        return true;
    }

    @JvmName(name = "activityContextUiThread")
    public static final <T extends Activity> boolean activityContextUiThread(@NotNull AnkoAsyncContext<AnkoContext<T>> $receiver, @NotNull Function1<? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        AnkoContext ankoContext = (AnkoContext) $receiver.getWeakRef().get();
        if (ankoContext != null) {
            Activity activity = (Activity) ankoContext.getOwner();
            if (activity != null) {
                if (activity.isFinishing()) {
                    return false;
                }
                activity.runOnUiThread(new AsyncKt$activityUiThread$2(f, activity));
                return true;
            }
        }
        return false;
    }

    @JvmName(name = "activityContextUiThreadWithContext")
    public static final <T extends Activity> boolean activityContextUiThreadWithContext(@NotNull AnkoAsyncContext<AnkoContext<T>> $receiver, @NotNull Function2<? super Context, ? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        AnkoContext ankoContext = (AnkoContext) $receiver.getWeakRef().get();
        if (ankoContext != null) {
            Activity activity = (Activity) ankoContext.getOwner();
            if (activity != null) {
                if (activity.isFinishing()) {
                    return false;
                }
                activity.runOnUiThread(new AsyncKt$activityUiThreadWithContext$2(f, activity));
                return true;
            }
        }
        return false;
    }

    public static final <T extends Fragment> boolean fragmentUiThread(@NotNull AnkoAsyncContext<T> $receiver, @NotNull Function1<? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        Fragment fragment = (Fragment) $receiver.getWeakRef().get();
        if (fragment == null || fragment.isDetached()) {
            return false;
        }
        Activity activity = fragment.getActivity();
        if (activity == null) {
            return false;
        }
        activity.runOnUiThread(new AsyncKt$fragmentUiThread$1(f, fragment));
        return true;
    }

    public static final <T extends Fragment> boolean fragmentUiThreadWithContext(@NotNull AnkoAsyncContext<T> $receiver, @NotNull Function2<? super Context, ? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        Fragment fragment = (Fragment) $receiver.getWeakRef().get();
        if (fragment == null || fragment.isDetached()) {
            return false;
        }
        Activity activity = fragment.getActivity();
        if (activity == null) {
            return false;
        }
        activity.runOnUiThread(new AsyncKt$fragmentUiThreadWithContext$1(f, activity, fragment));
        return true;
    }

    @NotNull
    public static /* bridge */ /* synthetic */ Future doAsync$default(Object obj, Function1 function1, Function1 function12, int i, Object obj2) {
        if ((i & 1) != 0) {
            function1 = crashLogger;
        }
        return doAsync(obj, function1, function12);
    }

    @NotNull
    public static final <T> Future<Unit> doAsync(T $receiver, @Nullable Function1<? super Throwable, Unit> exceptionHandler, @NotNull Function1<? super AnkoAsyncContext<T>, Unit> task) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        return BackgroundExecutor.INSTANCE.submit(new AsyncKt$doAsync$1(task, new AnkoAsyncContext(new WeakReference($receiver)), exceptionHandler));
    }

    @NotNull
    public static /* bridge */ /* synthetic */ Future doAsync$default(Object obj, Function1 function1, ExecutorService executorService, Function1 function12, int i, Object obj2) {
        if ((i & 1) != 0) {
            function1 = crashLogger;
        }
        return doAsync(obj, function1, executorService, function12);
    }

    @NotNull
    public static final <T> Future<Unit> doAsync(T $receiver, @Nullable Function1<? super Throwable, Unit> exceptionHandler, @NotNull ExecutorService executorService, @NotNull Function1<? super AnkoAsyncContext<T>, Unit> task) {
        Intrinsics.checkParameterIsNotNull(executorService, "executorService");
        Intrinsics.checkParameterIsNotNull(task, "task");
        Future<Unit> submit = executorService.submit(new AsyncKt$doAsync$2(task, new AnkoAsyncContext(new WeakReference($receiver)), exceptionHandler));
        Intrinsics.checkExpressionValueIsNotNull(submit, "executorService.submit<U…voke(thr)\n        }\n    }");
        return submit;
    }

    @NotNull
    public static /* bridge */ /* synthetic */ Future doAsyncResult$default(Object obj, Function1 function1, Function1 function12, int i, Object obj2) {
        if ((i & 1) != 0) {
            function1 = crashLogger;
        }
        return doAsyncResult(obj, function1, function12);
    }

    @NotNull
    public static final <T, R> Future<R> doAsyncResult(T $receiver, @Nullable Function1<? super Throwable, Unit> exceptionHandler, @NotNull Function1<? super AnkoAsyncContext<T>, ? extends R> task) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        return BackgroundExecutor.INSTANCE.submit(new AsyncKt$doAsyncResult$1(task, new AnkoAsyncContext(new WeakReference($receiver)), exceptionHandler));
    }

    @NotNull
    public static /* bridge */ /* synthetic */ Future doAsyncResult$default(Object obj, Function1 function1, ExecutorService executorService, Function1 function12, int i, Object obj2) {
        if ((i & 1) != 0) {
            function1 = crashLogger;
        }
        return doAsyncResult(obj, function1, executorService, function12);
    }

    @NotNull
    public static final <T, R> Future<R> doAsyncResult(T $receiver, @Nullable Function1<? super Throwable, Unit> exceptionHandler, @NotNull ExecutorService executorService, @NotNull Function1<? super AnkoAsyncContext<T>, ? extends R> task) {
        Intrinsics.checkParameterIsNotNull(executorService, "executorService");
        Intrinsics.checkParameterIsNotNull(task, "task");
        Future<R> submit = executorService.submit(new AsyncKt$doAsyncResult$2(task, new AnkoAsyncContext(new WeakReference($receiver)), exceptionHandler));
        Intrinsics.checkExpressionValueIsNotNull(submit, "executorService.submit<R…throw thr\n        }\n    }");
        return submit;
    }
}
