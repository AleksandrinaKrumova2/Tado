package org.jetbrains.anko.internals;

import android.app.Activity;
import android.app.Service;
import android.app.UiModeManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.NotificationCompat;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import com.google.android.gms.analytics.ecommerce.Promotion;
import java.io.Serializable;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import org.jetbrains.anko.AnkoContext;
import org.jetbrains.anko.AnkoContextImpl;
import org.jetbrains.anko.AnkoException;
import org.jetbrains.anko.Orientation;
import org.jetbrains.anko.ScreenSize;
import org.jetbrains.anko.UiMode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002UVB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\u0005\u001a\u00020\u0006\"\b\b\u0000\u0010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u0002H\u0007¢\u0006\u0002\u0010\fJ%\u0010\u0005\u001a\u00020\u0006\"\b\b\u0000\u0010\u0007*\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u0002H\u0007¢\u0006\u0002\u0010\u000fJ%\u0010\u0005\u001a\u00020\u0006\"\b\b\u0000\u0010\u0007*\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u0002H\u0007¢\u0006\u0002\u0010\u0012J\"\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\b2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00060\u0016JI\u0010\u0017\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u00072\u0006\u0010\r\u001a\u00020\u000e2\u000e\u0010\u0019\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00070\u001a2\u001c\u0010\u001b\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001d0\u001cH\u0007¢\u0006\u0002\u0010\u001eJ3\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u00182\u001c\u0010\u001b\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001d0\u001cH\u0003¢\u0006\u0002\u0010!J\u000e\u0010\"\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011J-\u0010#\u001a\u0002H\u0007\"\b\b\u0000\u0010\u0007*\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010$\u001a\b\u0012\u0004\u0012\u0002H\u00070\u001aH\u0007¢\u0006\u0002\u0010%JC\u0010&\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\u000e\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\u001a2\u001c\u0010\u001b\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001d0\u001cH\u0007¢\u0006\u0002\u0010'JK\u0010(\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\n2\u000e\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\u001a2\u0006\u0010*\u001a\u00020+2\u001c\u0010\u001b\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001d0\u001cH\u0007¢\u0006\u0002\u0010,JE\u0010-\u001a\u0004\u0018\u00010.2\u0006\u0010\r\u001a\u00020\u000e2\u000e\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u0002000\u001a2\u001c\u0010\u001b\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001d0\u001cH\u0007¢\u0006\u0002\u00101JC\u00102\u001a\u0002032\u0006\u0010\r\u001a\u00020\u000e2\u000e\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u0002000\u001a2\u001c\u0010\u001b\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001d0\u001cH\u0007¢\u0006\u0002\u00104J\u0006\u00105\u001a\u000206J\u0001\u00107\u001a\u0002032\u0006\u0010\r\u001a\u00020\u000e2\b\u00108\u001a\u0004\u0018\u0001092\u000e\u0010:\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010;2\b\u0010<\u001a\u0004\u0018\u00010\u00042\b\u0010=\u001a\u0004\u0018\u00010>2\b\u0010?\u001a\u0004\u0018\u0001032\b\u0010@\u001a\u0004\u0018\u00010+2\b\u0010A\u001a\u0004\u0018\u00010+2\b\u0010B\u001a\u0004\u0018\u00010C2\b\u0010D\u001a\u0004\u0018\u0001032\b\u0010E\u001a\u0004\u0018\u0001032\b\u0010F\u001a\u0004\u0018\u00010+H\u0007¢\u0006\u0002\u0010GJ0\u0010H\u001a\u0002H\u0007\"\u0004\b\u0000\u0010\u00072\u0006\u0010I\u001a\u00020J2\u0012\u0010K\u001a\u000e\u0012\u0004\u0012\u00020J\u0012\u0004\u0012\u0002H\u00070\u0016H\b¢\u0006\u0002\u0010LJ\u0016\u0010M\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010N\u001a\u00020+JO\u0010O\u001a\b\u0012\u0004\u0012\u0002H\u00070P\"\u0004\b\u0000\u0010\u0007*\u0002H\u00072\u0006\u0010\r\u001a\u00020\u000e2\u001d\u0010Q\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070P\u0012\u0004\u0012\u00020\u00060\u0016¢\u0006\u0002\bR2\b\b\u0002\u0010S\u001a\u000203H\b¢\u0006\u0002\u0010TR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006W"}, d2 = {"Lorg/jetbrains/anko/internals/AnkoInternals;", "", "()V", "NO_GETTER", "", "addView", "", "T", "Landroid/view/View;", "activity", "Landroid/app/Activity;", "view", "(Landroid/app/Activity;Landroid/view/View;)V", "ctx", "Landroid/content/Context;", "(Landroid/content/Context;Landroid/view/View;)V", "manager", "Landroid/view/ViewManager;", "(Landroid/view/ViewManager;Landroid/view/View;)V", "applyRecursively", "v", "style", "Lkotlin/Function1;", "createIntent", "Landroid/content/Intent;", "clazz", "Ljava/lang/Class;", "params", "", "Lkotlin/Pair;", "(Landroid/content/Context;Ljava/lang/Class;[Lkotlin/Pair;)Landroid/content/Intent;", "fillIntentArguments", "intent", "(Landroid/content/Intent;[Lkotlin/Pair;)V", "getContext", "initiateView", "viewClass", "(Landroid/content/Context;Ljava/lang/Class;)Landroid/view/View;", "internalStartActivity", "(Landroid/content/Context;Ljava/lang/Class;[Lkotlin/Pair;)V", "internalStartActivityForResult", "act", "requestCode", "", "(Landroid/app/Activity;Ljava/lang/Class;I[Lkotlin/Pair;)V", "internalStartService", "Landroid/content/ComponentName;", "service", "Landroid/app/Service;", "(Landroid/content/Context;Ljava/lang/Class;[Lkotlin/Pair;)Landroid/content/ComponentName;", "internalStopService", "", "(Landroid/content/Context;Ljava/lang/Class;[Lkotlin/Pair;)Z", "noGetter", "", "testConfiguration", "screenSize", "Lorg/jetbrains/anko/ScreenSize;", "density", "Lkotlin/ranges/ClosedRange;", "language", "orientation", "Lorg/jetbrains/anko/Orientation;", "long", "fromSdk", "sdk", "uiMode", "Lorg/jetbrains/anko/UiMode;", "nightMode", "rightToLeft", "smallestWidth", "(Landroid/content/Context;Lorg/jetbrains/anko/ScreenSize;Lkotlin/ranges/ClosedRange;Ljava/lang/String;Lorg/jetbrains/anko/Orientation;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Lorg/jetbrains/anko/UiMode;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;)Z", "useCursor", "cursor", "Landroid/database/Cursor;", "f", "(Landroid/database/Cursor;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "wrapContextIfNeeded", "theme", "createAnkoContext", "Lorg/jetbrains/anko/AnkoContext;", "init", "Lkotlin/ExtensionFunctionType;", "setContentView", "(Ljava/lang/Object;Landroid/content/Context;Lkotlin/jvm/functions/Function1;Z)Lorg/jetbrains/anko/AnkoContext;", "AnkoContextThemeWrapper", "InternalConfiguration", "commons_release"}, k = 1, mv = {1, 1, 5})
/* compiled from: Internals.kt */
public final class AnkoInternals {
    public static final AnkoInternals INSTANCE = null;
    @NotNull
    public static final String NO_GETTER = "Property does not have a getter";

    @Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/anko/internals/AnkoInternals$AnkoContextThemeWrapper;", "Landroid/view/ContextThemeWrapper;", "base", "Landroid/content/Context;", "theme", "", "(Landroid/content/Context;I)V", "getTheme", "()I", "commons_release"}, k = 1, mv = {1, 1, 5})
    /* compiled from: Internals.kt */
    private static final class AnkoContextThemeWrapper extends ContextThemeWrapper {
        private final int theme;

        public AnkoContextThemeWrapper(@Nullable Context base, int theme) {
            super(base, theme);
            this.theme = theme;
        }

        public final int getTheme() {
            return this.theme;
        }
    }

    @Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/anko/internals/AnkoInternals$InternalConfiguration;", "", "()V", "SCREENLAYOUT_LAYOUTDIR_MASK", "", "getSCREENLAYOUT_LAYOUTDIR_MASK", "()I", "SCREENLAYOUT_LAYOUTDIR_RTL", "getSCREENLAYOUT_LAYOUTDIR_RTL", "SCREENLAYOUT_LAYOUTDIR_SHIFT", "getSCREENLAYOUT_LAYOUTDIR_SHIFT", "UI_MODE_TYPE_APPLIANCE", "getUI_MODE_TYPE_APPLIANCE", "UI_MODE_TYPE_WATCH", "getUI_MODE_TYPE_WATCH", "commons_release"}, k = 1, mv = {1, 1, 5})
    /* compiled from: Internals.kt */
    private static final class InternalConfiguration {
        public static final InternalConfiguration INSTANCE = null;
        private static final int SCREENLAYOUT_LAYOUTDIR_MASK = 192;
        private static final int SCREENLAYOUT_LAYOUTDIR_RTL = 128;
        private static final int SCREENLAYOUT_LAYOUTDIR_SHIFT = 6;
        private static final int UI_MODE_TYPE_APPLIANCE = 5;
        private static final int UI_MODE_TYPE_WATCH = 6;

        static {
            InternalConfiguration internalConfiguration = new InternalConfiguration();
        }

        private InternalConfiguration() {
            INSTANCE = this;
            SCREENLAYOUT_LAYOUTDIR_MASK = SCREENLAYOUT_LAYOUTDIR_MASK;
            SCREENLAYOUT_LAYOUTDIR_SHIFT = 6;
            SCREENLAYOUT_LAYOUTDIR_RTL = 2 << SCREENLAYOUT_LAYOUTDIR_SHIFT;
            UI_MODE_TYPE_APPLIANCE = 5;
            UI_MODE_TYPE_WATCH = 6;
        }

        public final int getSCREENLAYOUT_LAYOUTDIR_MASK() {
            return SCREENLAYOUT_LAYOUTDIR_MASK;
        }

        public final int getSCREENLAYOUT_LAYOUTDIR_SHIFT() {
            return SCREENLAYOUT_LAYOUTDIR_SHIFT;
        }

        public final int getSCREENLAYOUT_LAYOUTDIR_RTL() {
            return SCREENLAYOUT_LAYOUTDIR_RTL;
        }

        public final int getUI_MODE_TYPE_APPLIANCE() {
            return UI_MODE_TYPE_APPLIANCE;
        }

        public final int getUI_MODE_TYPE_WATCH() {
            return UI_MODE_TYPE_WATCH;
        }
    }

    static {
        AnkoInternals ankoInternals = new AnkoInternals();
    }

    private AnkoInternals() {
        INSTANCE = this;
    }

    @NotNull
    public final Void noGetter() {
        throw new AnkoException(NO_GETTER);
    }

    public final <T extends View> void addView(@NotNull ViewManager manager, @NotNull T view) {
        Intrinsics.checkParameterIsNotNull(manager, "manager");
        Intrinsics.checkParameterIsNotNull(view, Promotion.ACTION_VIEW);
        if (manager instanceof ViewGroup) {
            ((ViewGroup) manager).addView(view);
        } else if (manager instanceof AnkoContext) {
            manager.addView(view, null);
        } else {
            throw new AnkoException(manager + " is the wrong parent");
        }
    }

    public final <T extends View> void addView(@NotNull Context ctx, @NotNull T view) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Intrinsics.checkParameterIsNotNull(view, Promotion.ACTION_VIEW);
        Context $receiver$iv = ctx;
        AnkoInternals ankoInternals = INSTANCE;
        AnkoContextImpl dsl$iv$iv = new AnkoContextImpl($receiver$iv, $receiver$iv, false);
        INSTANCE.addView((ViewManager) dsl$iv$iv, (View) view);
    }

    public final <T extends View> void addView(@NotNull Activity activity, @NotNull T view) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(view, Promotion.ACTION_VIEW);
        AnkoContextImpl dsl$iv = new AnkoContextImpl(activity, this, true);
        INSTANCE.addView((ViewManager) dsl$iv, (View) view);
    }

    @NotNull
    public final Context wrapContextIfNeeded(@NotNull Context ctx, int theme) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        if (theme == 0 || ((ctx instanceof AnkoContextThemeWrapper) && ((AnkoContextThemeWrapper) ctx).getTheme() == theme)) {
            return ctx;
        }
        return new AnkoContextThemeWrapper(ctx, theme);
    }

    public final void applyRecursively(@NotNull View v, @NotNull Function1<? super View, Unit> style) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        Intrinsics.checkParameterIsNotNull(style, "style");
        style.invoke(v);
        if (v instanceof ViewGroup) {
            int maxIndex = ((ViewGroup) v).getChildCount() - 1;
            int i = 0;
            if (0 <= maxIndex) {
                while (true) {
                    View it = ((ViewGroup) v).getChildAt(i);
                    if (it != null) {
                        INSTANCE.applyRecursively(it, style);
                        Unit unit = Unit.INSTANCE;
                    }
                    if (i != maxIndex) {
                        i++;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    @NotNull
    public final Context getContext(@NotNull ViewManager manager) {
        Intrinsics.checkParameterIsNotNull(manager, "manager");
        if (manager instanceof ViewGroup) {
            Context context = ((ViewGroup) manager).getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "manager.context");
            return context;
        } else if (manager instanceof AnkoContext) {
            return ((AnkoContext) manager).getCtx();
        } else {
            throw new AnkoException(manager + " is the wrong parent");
        }
    }

    @NotNull
    public static /* bridge */ /* synthetic */ AnkoContext createAnkoContext$default(AnkoInternals this_, Object $receiver, Context ctx, Function1 init, boolean setContentView, int i, Object obj) {
        if ((i & 4) != 0) {
            setContentView = false;
        }
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Intrinsics.checkParameterIsNotNull(init, "init");
        AnkoContextImpl dsl = new AnkoContextImpl(ctx, $receiver, setContentView);
        init.invoke(dsl);
        return dsl;
    }

    @NotNull
    public final <T> AnkoContext<T> createAnkoContext(T $receiver, @NotNull Context ctx, @NotNull Function1<? super AnkoContext<? extends T>, Unit> init, boolean setContentView) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Intrinsics.checkParameterIsNotNull(init, "init");
        AnkoContextImpl dsl = new AnkoContextImpl(ctx, $receiver, setContentView);
        init.invoke(dsl);
        return dsl;
    }

    @JvmStatic
    @NotNull
    public static final <T> Intent createIntent(@NotNull Context ctx, @NotNull Class<? extends T> clazz, @NotNull Pair<String, ? extends Object>[] params) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        Intrinsics.checkParameterIsNotNull(params, "params");
        Intent intent = new Intent(ctx, clazz);
        if (((Object[]) params).length == 0) {
            obj = 1;
        } else {
            obj = null;
        }
        if ((obj == null ? 1 : null) != null) {
            fillIntentArguments(intent, params);
        }
        return intent;
    }

    @JvmStatic
    public static final void internalStartActivity(@NotNull Context ctx, @NotNull Class<? extends Activity> activity, @NotNull Pair<String, ? extends Object>[] params) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(params, "params");
        ctx.startActivity(createIntent(ctx, activity, params));
    }

    @JvmStatic
    public static final void internalStartActivityForResult(@NotNull Activity act, @NotNull Class<? extends Activity> activity, int requestCode, @NotNull Pair<String, ? extends Object>[] params) {
        Intrinsics.checkParameterIsNotNull(act, "act");
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(params, "params");
        act.startActivityForResult(createIntent(act, activity, params), requestCode);
    }

    @JvmStatic
    @Nullable
    public static final ComponentName internalStartService(@NotNull Context ctx, @NotNull Class<? extends Service> service, @NotNull Pair<String, ? extends Object>[] params) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Intrinsics.checkParameterIsNotNull(service, NotificationCompat.CATEGORY_SERVICE);
        Intrinsics.checkParameterIsNotNull(params, "params");
        return ctx.startService(createIntent(ctx, service, params));
    }

    @JvmStatic
    public static final boolean internalStopService(@NotNull Context ctx, @NotNull Class<? extends Service> service, @NotNull Pair<String, ? extends Object>[] params) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Intrinsics.checkParameterIsNotNull(service, NotificationCompat.CATEGORY_SERVICE);
        Intrinsics.checkParameterIsNotNull(params, "params");
        return ctx.stopService(createIntent(ctx, service, params));
    }

    @JvmStatic
    private static final void fillIntentArguments(Intent intent, Pair<String, ? extends Object>[] params) {
        Object[] $receiver$iv = (Object[]) params;
        for (Pair it : $receiver$iv) {
            Object value = it.getSecond();
            if (Intrinsics.areEqual(value, null)) {
                intent.putExtra((String) it.getFirst(), (Serializable) null);
            } else if (value instanceof Integer) {
                intent.putExtra((String) it.getFirst(), ((Number) value).intValue());
            } else if (value instanceof Long) {
                intent.putExtra((String) it.getFirst(), ((Number) value).longValue());
            } else if (value instanceof CharSequence) {
                intent.putExtra((String) it.getFirst(), (CharSequence) value);
            } else if (value instanceof String) {
                intent.putExtra((String) it.getFirst(), (String) value);
            } else if (value instanceof Float) {
                intent.putExtra((String) it.getFirst(), ((Number) value).floatValue());
            } else if (value instanceof Double) {
                intent.putExtra((String) it.getFirst(), ((Number) value).doubleValue());
            } else if (value instanceof Character) {
                intent.putExtra((String) it.getFirst(), ((Character) value).charValue());
            } else if (value instanceof Short) {
                intent.putExtra((String) it.getFirst(), ((Number) value).shortValue());
            } else if (value instanceof Boolean) {
                intent.putExtra((String) it.getFirst(), ((Boolean) value).booleanValue());
            } else if (value instanceof Serializable) {
                intent.putExtra((String) it.getFirst(), (Serializable) value);
            } else if (value instanceof Bundle) {
                intent.putExtra((String) it.getFirst(), (Bundle) value);
            } else if (value instanceof Parcelable) {
                intent.putExtra((String) it.getFirst(), (Parcelable) value);
            } else if (value instanceof Object[]) {
                if (((Object[]) value) instanceof CharSequence[]) {
                    intent.putExtra((String) it.getFirst(), (Serializable) value);
                } else if (((Object[]) value) instanceof String[]) {
                    intent.putExtra((String) it.getFirst(), (Serializable) value);
                } else if (((Object[]) value) instanceof Parcelable[]) {
                    intent.putExtra((String) it.getFirst(), (Serializable) value);
                } else {
                    throw new AnkoException("Intent extra " + ((String) it.getFirst()) + " has wrong type " + ((Object[]) value).getClass().getName());
                }
            } else if (value instanceof int[]) {
                intent.putExtra((String) it.getFirst(), (int[]) value);
            } else if (value instanceof long[]) {
                intent.putExtra((String) it.getFirst(), (long[]) value);
            } else if (value instanceof float[]) {
                intent.putExtra((String) it.getFirst(), (float[]) value);
            } else if (value instanceof double[]) {
                intent.putExtra((String) it.getFirst(), (double[]) value);
            } else if (value instanceof char[]) {
                intent.putExtra((String) it.getFirst(), (char[]) value);
            } else if (value instanceof short[]) {
                intent.putExtra((String) it.getFirst(), (short[]) value);
            } else if (value instanceof boolean[]) {
                intent.putExtra((String) it.getFirst(), (boolean[]) value);
            } else {
                throw new AnkoException("Intent extra " + ((String) it.getFirst()) + " has wrong type " + value.getClass().getName());
            }
        }
    }

    @JvmStatic
    public static final <T> T useCursor(@NotNull Cursor cursor, @NotNull Function1<? super Cursor, ? extends T> f) {
        Intrinsics.checkParameterIsNotNull(cursor, "cursor");
        Intrinsics.checkParameterIsNotNull(f, "f");
        try {
            T invoke = f.invoke(cursor);
            try {
                cursor.close();
            } catch (Exception e) {
            }
            InlineMarker.finallyEnd(1);
            return invoke;
        } finally {
            InlineMarker.finallyStart(1);
            try {
                cursor.close();
            } catch (Exception e2) {
            }
            InlineMarker.finallyEnd(1);
        }
    }

    @JvmStatic
    @NotNull
    public static final <T extends View> T initiateView(@NotNull Context ctx, @NotNull Class<T> viewClass) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Intrinsics.checkParameterIsNotNull(viewClass, "viewClass");
        AnkoInternals$initiateView$1 getConstructor1$ = new AnkoInternals$initiateView$1(viewClass);
        AnkoInternals$initiateView$2 getConstructor2$ = new AnkoInternals$initiateView$2(viewClass);
        Object newInstance;
        try {
            newInstance = getConstructor1$.invoke().newInstance(new Object[]{ctx});
            Intrinsics.checkExpressionValueIsNotNull(newInstance, "getConstructor1().newInstance(ctx)");
            return (View) newInstance;
        } catch (NoSuchMethodException e) {
            try {
                newInstance = getConstructor2$.invoke().newInstance(new Object[]{ctx, null});
                Intrinsics.checkExpressionValueIsNotNull(newInstance, "getConstructor2().newInstance(ctx, null)");
                return (View) newInstance;
            } catch (NoSuchMethodException e2) {
                throw new AnkoException("Can't initiate View of class " + viewClass.getName() + ": can't find proper constructor");
            }
        }
    }

    @JvmStatic
    public static final boolean testConfiguration(@NotNull Context ctx, @Nullable ScreenSize screenSize, @Nullable ClosedRange<Integer> density, @Nullable String language, @Nullable Orientation orientation, @Nullable Boolean long_, @Nullable Integer fromSdk, @Nullable Integer sdk, @Nullable UiMode uiMode, @Nullable Boolean nightMode, @Nullable Boolean rightToLeft, @Nullable Integer smallestWidth) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Resources resources = ctx.getResources();
        Configuration config = resources != null ? resources.getConfiguration() : null;
        if (screenSize != null) {
            if (config != null) {
                switch (config.screenLayout & 15) {
                    case 1:
                        if ((Intrinsics.areEqual(screenSize, ScreenSize.SMALL) ^ 1) != 0) {
                            return false;
                        }
                        break;
                    case 2:
                        if ((Intrinsics.areEqual(screenSize, ScreenSize.NORMAL) ^ 1) != 0) {
                            return false;
                        }
                        break;
                    case 3:
                        if ((Intrinsics.areEqual(screenSize, ScreenSize.LARGE) ^ 1) != 0) {
                            return false;
                        }
                        break;
                    case 4:
                        if ((Intrinsics.areEqual(screenSize, ScreenSize.XLARGE) ^ 1) != 0) {
                            return false;
                        }
                        break;
                }
            }
            return false;
        }
        if (density != null) {
            resources = ctx.getResources();
            if (resources != null) {
                DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                if (displayMetrics != null) {
                    int currentDensityDpi = displayMetrics.densityDpi;
                    if ((density.contains(Integer.valueOf(currentDensityDpi)) ^ 1) != 0 || currentDensityDpi == ((Number) density.getEndInclusive()).intValue()) {
                        return false;
                    }
                }
            }
            return false;
        }
        if (language != null) {
            Locale locale = Locale.getDefault();
            if ((Intrinsics.areEqual(StringsKt__StringsKt.indexOf$default((CharSequence) language, '_', 0, false, 6, null) >= 0 ? locale.toString() : locale.getLanguage(), language) ^ 1) != 0) {
                return false;
            }
        }
        if (orientation != null) {
            if (config != null) {
                switch (config.orientation) {
                    case 1:
                        if ((Intrinsics.areEqual(orientation, Orientation.PORTRAIT) ^ 1) != 0) {
                            return false;
                        }
                        break;
                    case 2:
                        if ((Intrinsics.areEqual(orientation, Orientation.LANDSCAPE) ^ 1) != 0) {
                            return false;
                        }
                        break;
                    case 3:
                        if ((Intrinsics.areEqual(orientation, Orientation.SQUARE) ^ 1) != 0) {
                            return false;
                        }
                        break;
                }
            }
            return false;
        }
        if (long_ != null) {
            if (config == null) {
                return false;
            }
            int currentLong = config.screenLayout & 48;
            if (currentLong == 32 && !long_.booleanValue()) {
                return false;
            }
            if (currentLong == 16 && long_.booleanValue()) {
                return false;
            }
        }
        if (fromSdk != null && Intrinsics.compare(VERSION.SDK_INT, fromSdk.intValue()) < 0) {
            return false;
        }
        if (sdk != null && (Intrinsics.areEqual(Integer.valueOf(VERSION.SDK_INT), sdk) ^ 1) != 0) {
            return false;
        }
        if (uiMode != null) {
            if (config == null) {
                return false;
            }
            int i = config.uiMode & 15;
            if (i == 1) {
                if ((Intrinsics.areEqual(uiMode, UiMode.NORMAL) ^ 1) != 0) {
                    return false;
                }
            } else if (i == 2) {
                if ((Intrinsics.areEqual(uiMode, UiMode.DESK) ^ 1) != 0) {
                    return false;
                }
            } else if (i == 3) {
                if ((Intrinsics.areEqual(uiMode, UiMode.CAR) ^ 1) != 0) {
                    return false;
                }
            } else if (i == 4) {
                if ((Intrinsics.areEqual(uiMode, UiMode.TELEVISION) ^ 1) != 0) {
                    return false;
                }
            } else if (i == InternalConfiguration.INSTANCE.getUI_MODE_TYPE_APPLIANCE()) {
                if ((Intrinsics.areEqual(uiMode, UiMode.APPLIANCE) ^ 1) != 0) {
                    return false;
                }
            } else if (i == InternalConfiguration.INSTANCE.getUI_MODE_TYPE_WATCH()) {
                if ((Intrinsics.areEqual(uiMode, UiMode.WATCH) ^ 1) != 0) {
                    return false;
                }
            }
        }
        if (nightMode != null) {
            UiModeManager uiModeManager = ctx.getSystemService("uimode");
            if (!(uiModeManager instanceof UiModeManager)) {
                uiModeManager = null;
            }
            uiModeManager = uiModeManager;
            if (uiModeManager == null) {
                return false;
            }
            int currentMode = uiModeManager.getNightMode();
            if (currentMode == 2 && !nightMode.booleanValue()) {
                return false;
            }
            if (currentMode == 1 && nightMode.booleanValue()) {
                return false;
            }
        }
        if (rightToLeft != null) {
            if (config == null) {
                return false;
            }
            if ((Intrinsics.areEqual(Boolean.valueOf((config.screenLayout & InternalConfiguration.INSTANCE.getSCREENLAYOUT_LAYOUTDIR_MASK()) == InternalConfiguration.INSTANCE.getSCREENLAYOUT_LAYOUTDIR_RTL()), rightToLeft) ^ 1) != 0) {
                return false;
            }
        }
        if (smallestWidth != null) {
            if (config == null) {
                return false;
            }
            if (config.smallestScreenWidthDp == 0) {
                if ((Intrinsics.areEqual(smallestWidth, Integer.valueOf(0)) ^ 1) != 0) {
                    return false;
                }
            } else if (Intrinsics.compare(config.smallestScreenWidthDp, smallestWidth.intValue()) < 0) {
                return false;
            }
        }
        return true;
    }
}
