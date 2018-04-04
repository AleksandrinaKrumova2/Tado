package org.jetbrains.anko;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import java.io.Serializable;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000z\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a;\u00100\u001a\u0002012.\u00102\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u000205\u0012\u0006\u0012\u0004\u0018\u0001060403\"\u0010\u0012\u0004\u0012\u000205\u0012\u0006\u0012\u0004\u0018\u00010604¢\u0006\u0002\u00107\u001a(\u00108\u001a\u0002H9\"\n\b\u0000\u00109\u0018\u0001*\u00020\u0012*\u00020\u00012\b\b\u0001\u0010:\u001a\u00020;H\b¢\u0006\u0002\u0010<\u001a(\u00108\u001a\u0002H9\"\n\b\u0000\u00109\u0018\u0001*\u00020\u0012*\u00020=2\b\b\u0001\u0010:\u001a\u00020;H\b¢\u0006\u0002\u0010>\u001a(\u00108\u001a\u0002H9\"\n\b\u0000\u00109\u0018\u0001*\u00020\u0012*\u00020\u00042\b\b\u0001\u0010:\u001a\u00020;H\b¢\u0006\u0002\u0010?\u001a(\u00108\u001a\u0002H9\"\n\b\u0000\u00109\u0018\u0001*\u00020\u0012*\u00020\u00122\b\b\u0001\u0010:\u001a\u00020;H\b¢\u0006\u0002\u0010@\u001a*\u0010A\u001a\u0004\u0018\u0001H9\"\n\b\u0000\u00109\u0018\u0001*\u00020\u0012*\u00020\u00012\b\b\u0001\u0010:\u001a\u00020;H\b¢\u0006\u0002\u0010<\u001a*\u0010A\u001a\u0004\u0018\u0001H9\"\n\b\u0000\u00109\u0018\u0001*\u00020\u0012*\u00020=2\b\b\u0001\u0010:\u001a\u00020;H\b¢\u0006\u0002\u0010>\u001a*\u0010A\u001a\u0004\u0018\u0001H9\"\n\b\u0000\u00109\u0018\u0001*\u00020\u0012*\u00020\u00042\b\b\u0001\u0010:\u001a\u00020;H\b¢\u0006\u0002\u0010?\u001a*\u0010A\u001a\u0004\u0018\u0001H9\"\n\b\u0000\u00109\u0018\u0001*\u00020\u0012*\u00020\u00122\b\b\u0001\u0010:\u001a\u00020;H\b¢\u0006\u0002\u0010@\u001aL\u0010B\u001a\u0002H9\"\b\b\u0000\u00109*\u00020\u0004*\u0002H92.\u00102\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u000205\u0012\u0006\u0012\u0004\u0018\u0001060403\"\u0010\u0012\u0004\u0012\u000205\u0012\u0006\u0012\u0004\u0018\u00010604H\b¢\u0006\u0002\u0010C\"\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00018Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00048Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0005\"\u001a\u0010\u0006\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\b8Æ\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010\n\"\u0016\u0010\u000b\u001a\u00020\f*\u00020\r8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\"\u001a\u0010\u000b\u001a\u00020\f*\u0006\u0012\u0002\b\u00030\b8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0010\"\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u00018Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\"\u0016\u0010\u0015\u001a\u00020\r*\u00020\u00048Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017\"\u0016\u0010\u0015\u001a\u00020\r*\u00020\r8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0018\"\u0016\u0010\u0019\u001a\u00020\u001a*\u00020\u00048Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\"\u0016\u0010\u0019\u001a\u00020\u001a*\u00020\r8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001d\"\u001a\u0010\u0019\u001a\u00020\u001a*\u0006\u0012\u0002\b\u00030\b8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001e\"\u0016\u0010\u001f\u001a\u00020 *\u00020\r8Æ\u0002¢\u0006\u0006\u001a\u0004\b!\u0010\"\"\u001a\u0010\u001f\u001a\u00020 *\u0006\u0012\u0002\b\u00030\b8Æ\u0002¢\u0006\u0006\u001a\u0004\b!\u0010#\"\u0016\u0010$\u001a\u00020%*\u00020\f8Æ\u0002¢\u0006\u0006\u001a\u0004\b&\u0010'\"\u0016\u0010(\u001a\u00020%*\u00020\f8Æ\u0002¢\u0006\u0006\u001a\u0004\b)\u0010'\"\u0016\u0010*\u001a\u00020%*\u00020\f8Æ\u0002¢\u0006\u0006\u001a\u0004\b+\u0010'\"\u001a\u0010,\u001a\u00020-*\u0006\u0012\u0002\b\u00030\b8Æ\u0002¢\u0006\u0006\u001a\u0004\b.\u0010/¨\u0006D"}, d2 = {"act", "Landroid/app/Activity;", "getAct", "(Landroid/app/Activity;)Landroid/app/Activity;", "Landroid/app/Fragment;", "(Landroid/app/Fragment;)Landroid/app/Activity;", "assets", "Landroid/content/res/AssetManager;", "Lorg/jetbrains/anko/AnkoContext;", "getAssets", "(Lorg/jetbrains/anko/AnkoContext;)Landroid/content/res/AssetManager;", "configuration", "Landroid/content/res/Configuration;", "Landroid/content/Context;", "getConfiguration", "(Landroid/content/Context;)Landroid/content/res/Configuration;", "(Lorg/jetbrains/anko/AnkoContext;)Landroid/content/res/Configuration;", "contentView", "Landroid/view/View;", "getContentView", "(Landroid/app/Activity;)Landroid/view/View;", "ctx", "getCtx", "(Landroid/app/Fragment;)Landroid/content/Context;", "(Landroid/content/Context;)Landroid/content/Context;", "defaultSharedPreferences", "Landroid/content/SharedPreferences;", "getDefaultSharedPreferences", "(Landroid/app/Fragment;)Landroid/content/SharedPreferences;", "(Landroid/content/Context;)Landroid/content/SharedPreferences;", "(Lorg/jetbrains/anko/AnkoContext;)Landroid/content/SharedPreferences;", "displayMetrics", "Landroid/util/DisplayMetrics;", "getDisplayMetrics", "(Landroid/content/Context;)Landroid/util/DisplayMetrics;", "(Lorg/jetbrains/anko/AnkoContext;)Landroid/util/DisplayMetrics;", "landscape", "", "getLandscape", "(Landroid/content/res/Configuration;)Z", "long", "getLong", "portrait", "getPortrait", "resources", "Landroid/content/res/Resources;", "getResources", "(Lorg/jetbrains/anko/AnkoContext;)Landroid/content/res/Resources;", "bundleOf", "Landroid/os/Bundle;", "params", "", "Lkotlin/Pair;", "", "", "([Lkotlin/Pair;)Landroid/os/Bundle;", "find", "T", "id", "", "(Landroid/app/Activity;I)Landroid/view/View;", "Landroid/app/Dialog;", "(Landroid/app/Dialog;I)Landroid/view/View;", "(Landroid/app/Fragment;I)Landroid/view/View;", "(Landroid/view/View;I)Landroid/view/View;", "findOptional", "withArguments", "(Landroid/app/Fragment;[Lkotlin/Pair;)Landroid/app/Fragment;", "commons_release"}, k = 2, mv = {1, 1, 5})
/* compiled from: ContextUtils.kt */
public final class ContextUtilsKt {
    @NotNull
    public static final Resources getResources(@NotNull AnkoContext<?> $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Resources resources = $receiver.getCtx().getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "ctx.resources");
        return resources;
    }

    @NotNull
    public static final AssetManager getAssets(@NotNull AnkoContext<?> $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        AssetManager assets = $receiver.getCtx().getAssets();
        Intrinsics.checkExpressionValueIsNotNull(assets, "ctx.assets");
        return assets;
    }

    @NotNull
    public static final SharedPreferences getDefaultSharedPreferences(@NotNull AnkoContext<?> $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences($receiver.getCtx());
        Intrinsics.checkExpressionValueIsNotNull(defaultSharedPreferences, "PreferenceManager.getDefaultSharedPreferences(ctx)");
        return defaultSharedPreferences;
    }

    @NotNull
    public static final SharedPreferences getDefaultSharedPreferences(@NotNull Context $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences($receiver);
        Intrinsics.checkExpressionValueIsNotNull(defaultSharedPreferences, "PreferenceManager.getDef…ltSharedPreferences(this)");
        return defaultSharedPreferences;
    }

    @NotNull
    public static final SharedPreferences getDefaultSharedPreferences(@NotNull Fragment $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences($receiver.getActivity());
        Intrinsics.checkExpressionValueIsNotNull(defaultSharedPreferences, "PreferenceManager.getDef…aredPreferences(activity)");
        return defaultSharedPreferences;
    }

    @NotNull
    public static final Activity getAct(@NotNull Fragment $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Activity activity = $receiver.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity");
        return activity;
    }

    @NotNull
    public static final Context getCtx(@NotNull Fragment $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Activity activity = $receiver.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity");
        return activity;
    }

    @NotNull
    public static final Context getCtx(@NotNull Context $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver;
    }

    @NotNull
    public static final Activity getAct(@NotNull Activity $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver;
    }

    @Nullable
    public static final View getContentView(@NotNull Activity $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        View findViewById = $receiver.findViewById(16908290);
        if (!(findViewById instanceof ViewGroup)) {
            findViewById = null;
        }
        ViewGroup viewGroup = (ViewGroup) findViewById;
        return viewGroup != null ? viewGroup.getChildAt(0) : null;
    }

    private static final <T extends View> T find(@NotNull View $receiver, @IdRes int id) {
        View findViewById = $receiver.findViewById(id);
        Intrinsics.reifiedOperationMarker(1, "T");
        return findViewById;
    }

    private static final <T extends View> T find(@NotNull Activity $receiver, @IdRes int id) {
        View findViewById = $receiver.findViewById(id);
        Intrinsics.reifiedOperationMarker(1, "T");
        return findViewById;
    }

    private static final <T extends View> T find(@NotNull Fragment $receiver, @IdRes int id) {
        View view = $receiver.getView();
        view = view != null ? view.findViewById(id) : null;
        Intrinsics.reifiedOperationMarker(1, "T");
        return view;
    }

    private static final <T extends View> T find(@NotNull Dialog $receiver, @IdRes int id) {
        View findViewById = $receiver.findViewById(id);
        Intrinsics.reifiedOperationMarker(1, "T");
        return findViewById;
    }

    private static final <T extends View> T findOptional(@NotNull View $receiver, @IdRes int id) {
        View findViewById = $receiver.findViewById(id);
        Intrinsics.reifiedOperationMarker(2, "T");
        return findViewById;
    }

    private static final <T extends View> T findOptional(@NotNull Activity $receiver, @IdRes int id) {
        View findViewById = $receiver.findViewById(id);
        Intrinsics.reifiedOperationMarker(2, "T");
        return findViewById;
    }

    private static final <T extends View> T findOptional(@NotNull Fragment $receiver, @IdRes int id) {
        View view = $receiver.getView();
        view = view != null ? view.findViewById(id) : null;
        Intrinsics.reifiedOperationMarker(2, "T");
        return view;
    }

    private static final <T extends View> T findOptional(@NotNull Dialog $receiver, @IdRes int id) {
        View findViewById = $receiver.findViewById(id);
        Intrinsics.reifiedOperationMarker(2, "T");
        return findViewById;
    }

    @NotNull
    public static final <T extends Fragment> T withArguments(@NotNull T $receiver, @NotNull Pair<String, ? extends Object>... params) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(params, "params");
        $receiver.setArguments(bundleOf((Pair[]) Arrays.copyOf(params, params.length)));
        return $receiver;
    }

    @NotNull
    public static final Bundle bundleOf(@NotNull Pair<String, ? extends Object>... params) {
        Intrinsics.checkParameterIsNotNull(params, "params");
        Bundle b = new Bundle();
        for (Pair p : params) {
            String str = (String) p.component1();
            Object component2 = p.component2();
            if (Intrinsics.areEqual(component2, null)) {
                b.putSerializable(str, null);
            } else if (component2 instanceof Boolean) {
                b.putBoolean(str, ((Boolean) component2).booleanValue());
            } else if (component2 instanceof Byte) {
                b.putByte(str, ((Number) component2).byteValue());
            } else if (component2 instanceof Character) {
                b.putChar(str, ((Character) component2).charValue());
            } else if (component2 instanceof Short) {
                b.putShort(str, ((Number) component2).shortValue());
            } else if (component2 instanceof Integer) {
                b.putInt(str, ((Number) component2).intValue());
            } else if (component2 instanceof Long) {
                b.putLong(str, ((Number) component2).longValue());
            } else if (component2 instanceof Float) {
                b.putFloat(str, ((Number) component2).floatValue());
            } else if (component2 instanceof Double) {
                b.putDouble(str, ((Number) component2).doubleValue());
            } else if (component2 instanceof String) {
                b.putString(str, (String) component2);
            } else if (component2 instanceof CharSequence) {
                b.putCharSequence(str, (CharSequence) component2);
            } else if (component2 instanceof Parcelable) {
                b.putParcelable(str, (Parcelable) component2);
            } else if (component2 instanceof Serializable) {
                b.putSerializable(str, (Serializable) component2);
            } else if (component2 instanceof boolean[]) {
                b.putBooleanArray(str, (boolean[]) component2);
            } else if (component2 instanceof byte[]) {
                b.putByteArray(str, (byte[]) component2);
            } else if (component2 instanceof char[]) {
                b.putCharArray(str, (char[]) component2);
            } else if (component2 instanceof double[]) {
                b.putDoubleArray(str, (double[]) component2);
            } else if (component2 instanceof float[]) {
                b.putFloatArray(str, (float[]) component2);
            } else if (component2 instanceof int[]) {
                b.putIntArray(str, (int[]) component2);
            } else if (component2 instanceof long[]) {
                b.putLongArray(str, (long[]) component2);
            } else if (component2 instanceof Object[]) {
                if (((Object[]) component2) instanceof Parcelable[]) {
                    if (component2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<out android.os.Parcelable>");
                    }
                    b.putParcelableArray(str, (Parcelable[]) component2);
                } else if (((Object[]) component2) instanceof CharSequence[]) {
                    if (component2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<out kotlin.CharSequence>");
                    }
                    b.putCharSequenceArray(str, (CharSequence[]) component2);
                } else if (!(((Object[]) component2) instanceof String[])) {
                    throw new AnkoException("Unsupported bundle component (" + ((Object[]) component2).getClass() + ")");
                } else if (component2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<out kotlin.String>");
                } else {
                    b.putStringArray(str, (String[]) component2);
                }
            } else if (component2 instanceof short[]) {
                b.putShortArray(str, (short[]) component2);
            } else if (component2 instanceof Bundle) {
                b.putBundle(str, (Bundle) component2);
            } else {
                throw new AnkoException("Unsupported bundle component (" + component2.getClass() + ")");
            }
        }
        return b;
    }

    @NotNull
    public static final DisplayMetrics getDisplayMetrics(@NotNull Context $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        DisplayMetrics displayMetrics = $receiver.getResources().getDisplayMetrics();
        Intrinsics.checkExpressionValueIsNotNull(displayMetrics, "resources.displayMetrics");
        return displayMetrics;
    }

    @NotNull
    public static final Configuration getConfiguration(@NotNull Context $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Configuration configuration = $receiver.getResources().getConfiguration();
        Intrinsics.checkExpressionValueIsNotNull(configuration, "resources.configuration");
        return configuration;
    }

    @NotNull
    public static final DisplayMetrics getDisplayMetrics(@NotNull AnkoContext<?> $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        DisplayMetrics displayMetrics = $receiver.getCtx().getResources().getDisplayMetrics();
        Intrinsics.checkExpressionValueIsNotNull(displayMetrics, "ctx.resources.displayMetrics");
        return displayMetrics;
    }

    @NotNull
    public static final Configuration getConfiguration(@NotNull AnkoContext<?> $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Configuration configuration = $receiver.getCtx().getResources().getConfiguration();
        Intrinsics.checkExpressionValueIsNotNull(configuration, "ctx.resources.configuration");
        return configuration;
    }

    public static final boolean getPortrait(@NotNull Configuration $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver.orientation == 1;
    }

    public static final boolean getLandscape(@NotNull Configuration $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver.orientation == 2;
    }

    public static final boolean getLong(@NotNull Configuration $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return ($receiver.screenLayout & 32) != 0;
    }
}
