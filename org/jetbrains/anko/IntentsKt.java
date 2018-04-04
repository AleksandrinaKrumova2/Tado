package org.jetbrains.anko;

import android.app.Activity;
import android.app.Fragment;
import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import fi.iki.elonen.NanoHTTPD;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.internals.AnkoInternals;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000V\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0001H\b\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0001\u001a#\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0001H\b\u001a\r\u0010\b\u001a\u00020\t*\u00020\tH\b\u001a\r\u0010\n\u001a\u00020\t*\u00020\tH\b\u001a\r\u0010\u000b\u001a\u00020\t*\u00020\tH\b\u001a)\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u0004H\b\u001a&\u0010\f\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u0004\u001a-\u0010\f\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u0004H\b\u001a\r\u0010\u000f\u001a\u00020\t*\u00020\tH\b\u001aN\u0010\u0010\u001a\u00020\t\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020\u0012*\u00020\u00022.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\b¢\u0006\u0002\u0010\u0016\u001aN\u0010\u0010\u001a\u00020\t\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020\u0012*\u00020\u00062.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\b¢\u0006\u0002\u0010\u0017\u001aR\u0010\u0010\u001a\u00020\t\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020\u0012*\u0006\u0012\u0002\b\u00030\u00072.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\b¢\u0006\u0002\u0010\u0018\u001a\u0015\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0004H\b\u001a\u0012\u0010\u0019\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0004\u001a\u0019\u0010\u0019\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u001a\u001a\u00020\u0004H\b\u001a\r\u0010\u001b\u001a\u00020\t*\u00020\tH\b\u001a\r\u0010\u0005\u001a\u00020\t*\u00020\tH\b\u001a\r\u0010\u001c\u001a\u00020\t*\u00020\tH\b\u001a\r\u0010\u001d\u001a\u00020\t*\u00020\tH\b\u001a\u001f\u0010\u001e\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u0004H\b\u001a\u001c\u0010\u001e\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u0004\u001a#\u0010\u001e\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u0004H\b\u001a\u001f\u0010\u001f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u0004H\b\u001a\u001c\u0010\u001f\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u0004\u001a#\u0010\u001f\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u0004H\b\u001a\r\u0010 \u001a\u00020\t*\u00020\tH\b\u001aN\u0010!\u001a\u00020\"\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020#*\u00020\u00022.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\b¢\u0006\u0002\u0010$\u001aN\u0010!\u001a\u00020\"\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020#*\u00020\u00062.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\b¢\u0006\u0002\u0010%\u001aR\u0010!\u001a\u00020\"\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020#*\u0006\u0012\u0002\b\u00030\u00072.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\b¢\u0006\u0002\u0010&\u001aV\u0010'\u001a\u00020\"\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020#*\u00020#2\u0006\u0010(\u001a\u00020)2.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\b¢\u0006\u0002\u0010*\u001aV\u0010'\u001a\u00020\"\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020#*\u00020\u00022\u0006\u0010(\u001a\u00020)2.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\b¢\u0006\u0002\u0010+\u001aP\u0010,\u001a\u0004\u0018\u00010-\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020.*\u00020\u00022.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\b¢\u0006\u0002\u0010/\u001aP\u0010,\u001a\u0004\u0018\u00010-\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020.*\u00020\u00062.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\b¢\u0006\u0002\u00100\u001aT\u0010,\u001a\u0004\u0018\u00010-\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020.*\u0006\u0012\u0002\b\u00030\u00072.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\b¢\u0006\u0002\u00101\u001aN\u00102\u001a\u00020\u0001\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020.*\u00020\u00022.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\b¢\u0006\u0002\u00103\u001aN\u00102\u001a\u00020\u0001\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020.*\u00020\u00062.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\b¢\u0006\u0002\u00104\u001aR\u00102\u001a\u00020\u0001\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020.*\u0006\u0012\u0002\b\u00030\u00072.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\b¢\u0006\u0002\u00105¨\u00066"}, d2 = {"browse", "", "Landroid/app/Fragment;", "url", "", "newTask", "Landroid/content/Context;", "Lorg/jetbrains/anko/AnkoContext;", "clearTask", "Landroid/content/Intent;", "clearTop", "clearWhenTaskReset", "email", "subject", "text", "excludeFromRecents", "intentFor", "T", "", "params", "", "Lkotlin/Pair;", "(Landroid/app/Fragment;[Lkotlin/Pair;)Landroid/content/Intent;", "(Landroid/content/Context;[Lkotlin/Pair;)Landroid/content/Intent;", "(Lorg/jetbrains/anko/AnkoContext;[Lkotlin/Pair;)Landroid/content/Intent;", "makeCall", "number", "multipleTask", "noAnimation", "noHistory", "sendSMS", "share", "singleTop", "startActivity", "", "Landroid/app/Activity;", "(Landroid/app/Fragment;[Lkotlin/Pair;)V", "(Landroid/content/Context;[Lkotlin/Pair;)V", "(Lorg/jetbrains/anko/AnkoContext;[Lkotlin/Pair;)V", "startActivityForResult", "requestCode", "", "(Landroid/app/Activity;I[Lkotlin/Pair;)V", "(Landroid/app/Fragment;I[Lkotlin/Pair;)V", "startService", "Landroid/content/ComponentName;", "Landroid/app/Service;", "(Landroid/app/Fragment;[Lkotlin/Pair;)Landroid/content/ComponentName;", "(Landroid/content/Context;[Lkotlin/Pair;)Landroid/content/ComponentName;", "(Lorg/jetbrains/anko/AnkoContext;[Lkotlin/Pair;)Landroid/content/ComponentName;", "stopService", "(Landroid/app/Fragment;[Lkotlin/Pair;)Z", "(Landroid/content/Context;[Lkotlin/Pair;)Z", "(Lorg/jetbrains/anko/AnkoContext;[Lkotlin/Pair;)Z", "commons_release"}, k = 2, mv = {1, 1, 5})
/* compiled from: Intents.kt */
public final class IntentsKt {
    private static final <T extends Activity> void startActivity(@NotNull Context $receiver, Pair<String, ? extends Object>... params) {
        Intrinsics.reifiedOperationMarker(4, "T");
        AnkoInternals.internalStartActivity($receiver, Activity.class, params);
    }

    private static final <T extends Activity> void startActivity(@NotNull AnkoContext<?> $receiver, Pair<String, ? extends Object>... params) {
        Context ctx = $receiver.getCtx();
        Intrinsics.reifiedOperationMarker(4, "T");
        AnkoInternals.internalStartActivity(ctx, Activity.class, params);
    }

    private static final <T extends Activity> void startActivity(@NotNull Fragment $receiver, Pair<String, ? extends Object>... params) {
        Activity activity = $receiver.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity");
        Context context = activity;
        Intrinsics.reifiedOperationMarker(4, "T");
        AnkoInternals.internalStartActivity(context, Activity.class, params);
    }

    private static final <T extends Activity> void startActivityForResult(@NotNull Activity $receiver, int requestCode, Pair<String, ? extends Object>... params) {
        Intrinsics.reifiedOperationMarker(4, "T");
        AnkoInternals.internalStartActivityForResult($receiver, Activity.class, requestCode, params);
    }

    private static final <T extends Service> ComponentName startService(@NotNull Context $receiver, Pair<String, ? extends Object>... params) {
        Intrinsics.reifiedOperationMarker(4, "T");
        return AnkoInternals.internalStartService($receiver, Service.class, params);
    }

    private static final <T extends Service> ComponentName startService(@NotNull AnkoContext<?> $receiver, Pair<String, ? extends Object>... params) {
        Context ctx = $receiver.getCtx();
        Intrinsics.reifiedOperationMarker(4, "T");
        return AnkoInternals.internalStartService(ctx, Service.class, params);
    }

    private static final <T extends Service> ComponentName startService(@NotNull Fragment $receiver, Pair<String, ? extends Object>... params) {
        Activity activity = $receiver.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity");
        Context context = activity;
        Intrinsics.reifiedOperationMarker(4, "T");
        return AnkoInternals.internalStartService(context, Service.class, params);
    }

    private static final <T extends Service> boolean stopService(@NotNull Context $receiver, Pair<String, ? extends Object>... params) {
        Intrinsics.reifiedOperationMarker(4, "T");
        return AnkoInternals.internalStopService($receiver, Service.class, params);
    }

    private static final <T extends Service> boolean stopService(@NotNull AnkoContext<?> $receiver, Pair<String, ? extends Object>... params) {
        Context ctx = $receiver.getCtx();
        Intrinsics.reifiedOperationMarker(4, "T");
        return AnkoInternals.internalStopService(ctx, Service.class, params);
    }

    private static final <T extends Service> boolean stopService(@NotNull Fragment $receiver, Pair<String, ? extends Object>... params) {
        Activity activity = $receiver.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity");
        Context context = activity;
        Intrinsics.reifiedOperationMarker(4, "T");
        return AnkoInternals.internalStopService(context, Service.class, params);
    }

    private static final <T> Intent intentFor(@NotNull Context $receiver, Pair<String, ? extends Object>... params) {
        Intrinsics.reifiedOperationMarker(4, "T");
        return AnkoInternals.createIntent($receiver, Object.class, params);
    }

    private static final <T> Intent intentFor(@NotNull AnkoContext<?> $receiver, Pair<String, ? extends Object>... params) {
        Context ctx = $receiver.getCtx();
        Intrinsics.reifiedOperationMarker(4, "T");
        return AnkoInternals.createIntent(ctx, Object.class, params);
    }

    private static final <T> Intent intentFor(@NotNull Fragment $receiver, Pair<String, ? extends Object>... params) {
        Activity activity = $receiver.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity");
        Context context = activity;
        Intrinsics.reifiedOperationMarker(4, "T");
        return AnkoInternals.createIntent(context, Object.class, params);
    }

    @NotNull
    public static final Intent clearTask(@NotNull Intent $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addFlags(32768);
        return $receiver;
    }

    @NotNull
    public static final Intent clearTop(@NotNull Intent $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addFlags(67108864);
        return $receiver;
    }

    @NotNull
    public static final Intent clearWhenTaskReset(@NotNull Intent $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addFlags(524288);
        return $receiver;
    }

    @NotNull
    public static final Intent excludeFromRecents(@NotNull Intent $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addFlags(8388608);
        return $receiver;
    }

    @NotNull
    public static final Intent multipleTask(@NotNull Intent $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addFlags(134217728);
        return $receiver;
    }

    @NotNull
    public static final Intent newTask(@NotNull Intent $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addFlags(268435456);
        return $receiver;
    }

    @NotNull
    public static final Intent noAnimation(@NotNull Intent $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addFlags(65536);
        return $receiver;
    }

    @NotNull
    public static final Intent noHistory(@NotNull Intent $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addFlags(1073741824);
        return $receiver;
    }

    @NotNull
    public static final Intent singleTop(@NotNull Intent $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addFlags(536870912);
        return $receiver;
    }

    public static final boolean browse(@NotNull AnkoContext<?> $receiver, @NotNull String url, boolean newTask) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(url, "url");
        return browse($receiver.getCtx(), url, newTask);
    }

    public static final boolean browse(@NotNull Fragment $receiver, @NotNull String url, boolean newTask) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(url, "url");
        return browse((Context) $receiver.getActivity(), url, newTask);
    }

    public static final boolean browse(@NotNull Context $receiver, @NotNull String url, boolean newTask) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(url, "url");
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(url));
            if (newTask) {
                intent.addFlags(268435456);
            }
            $receiver.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static final boolean share(@NotNull AnkoContext<?> $receiver, @NotNull String text, @NotNull String subject) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(subject, "subject");
        return share($receiver.getCtx(), text, subject);
    }

    public static final boolean share(@NotNull Fragment $receiver, @NotNull String text, @NotNull String subject) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(subject, "subject");
        return share((Context) $receiver.getActivity(), text, subject);
    }

    public static final boolean share(@NotNull Context $receiver, @NotNull String text, @NotNull String subject) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(subject, "subject");
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType(NanoHTTPD.MIME_PLAINTEXT);
            intent.putExtra("android.intent.extra.SUBJECT", subject);
            intent.putExtra("android.intent.extra.TEXT", text);
            $receiver.startActivity(Intent.createChooser(intent, null));
            return true;
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static final boolean email(@NotNull AnkoContext<?> $receiver, @NotNull String email, @NotNull String subject, @NotNull String text) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(email, "email");
        Intrinsics.checkParameterIsNotNull(subject, "subject");
        Intrinsics.checkParameterIsNotNull(text, "text");
        return email($receiver.getCtx(), email, subject, text);
    }

    public static final boolean email(@NotNull Fragment $receiver, @NotNull String email, @NotNull String subject, @NotNull String text) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(email, "email");
        Intrinsics.checkParameterIsNotNull(subject, "subject");
        Intrinsics.checkParameterIsNotNull(text, "text");
        return email((Context) $receiver.getActivity(), email, subject, text);
    }

    public static final boolean email(@NotNull Context $receiver, @NotNull String email, @NotNull String subject, @NotNull String text) {
        int i;
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(email, "email");
        Intrinsics.checkParameterIsNotNull(subject, "subject");
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra("android.intent.extra.EMAIL", (String[]) ((Object[]) new String[]{email}));
        if ((((CharSequence) subject).length() > 0 ? 1 : 0) != 0) {
            intent.putExtra("android.intent.extra.SUBJECT", subject);
        }
        if (text.length() > 0) {
            i = 1;
        } else {
            i = 0;
        }
        if (i != 0) {
            intent.putExtra("android.intent.extra.TEXT", text);
        }
        if (intent.resolveActivity($receiver.getPackageManager()) == null) {
            return false;
        }
        $receiver.startActivity(intent);
        return true;
    }

    public static final boolean makeCall(@NotNull AnkoContext<?> $receiver, @NotNull String number) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(number, "number");
        return makeCall($receiver.getCtx(), number);
    }

    public static final boolean makeCall(@NotNull Fragment $receiver, @NotNull String number) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(number, "number");
        return makeCall((Context) $receiver.getActivity(), number);
    }

    public static final boolean makeCall(@NotNull Context $receiver, @NotNull String number) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(number, "number");
        try {
            $receiver.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + number)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static final boolean sendSMS(@NotNull AnkoContext<?> $receiver, @NotNull String number, @NotNull String text) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(number, "number");
        Intrinsics.checkParameterIsNotNull(text, "text");
        return sendSMS($receiver.getCtx(), number, text);
    }

    public static final boolean sendSMS(@NotNull Fragment $receiver, @NotNull String number, @NotNull String text) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(number, "number");
        Intrinsics.checkParameterIsNotNull(text, "text");
        return sendSMS((Context) $receiver.getActivity(), number, text);
    }

    public static final boolean sendSMS(@NotNull Context $receiver, @NotNull String number, @NotNull String text) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(number, "number");
        Intrinsics.checkParameterIsNotNull(text, "text");
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("sms:" + number));
            intent.putExtra("sms_body", text);
            $receiver.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static final <T extends Activity> void startActivityForResult(@NotNull Fragment $receiver, int requestCode, Pair<String, ? extends Object>... params) {
        Activity activity = $receiver.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity");
        Context context = activity;
        Intrinsics.reifiedOperationMarker(4, "T");
        $receiver.startActivityForResult(AnkoInternals.createIntent(context, Activity.class, params), requestCode);
    }
}
