package org.jetbrains.anko;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042$\b\b\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\b2\u0006\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2!\b\n\u0010\f\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0012\u0004\u0012\u00020\r\u0018\u00010\u0006¢\u0006\u0002\b\u000eH\b¢\u0006\u0002\u0010\u000f\u001az\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042$\b\b\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\b2\u0006\u0010\t\u001a\u00020\u00102\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00102!\b\n\u0010\f\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0012\u0004\u0012\u00020\r\u0018\u00010\u0006¢\u0006\u0002\b\u000eH\b\u001ad\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042$\b\b\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\b2\u001f\b\b\u0010\f\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0012\u0004\u0012\u00020\r0\u0006¢\u0006\u0002\b\u000eH\b\u001az\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00072\"\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\b2\u0006\u0010\u0011\u001a\u00020\n2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\n2!\b\u0002\u0010\f\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0012\u0004\u0012\u00020\r\u0018\u00010\u0006¢\u0006\u0002\b\u000e¢\u0006\u0002\u0010\u0013\u001au\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00072\"\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\b2\u0006\u0010\t\u001a\u00020\u00102\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00102!\b\u0002\u0010\f\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0012\u0004\u0012\u00020\r\u0018\u00010\u0006¢\u0006\u0002\b\u000e\u001a]\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00072\"\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\b2\u001d\u0010\f\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0012\u0004\u0012\u00020\r0\u0006¢\u0006\u0002\b\u000e\u001a\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0006\u0012\u0002\b\u00030\u00142$\b\b\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\b2\u0006\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2!\b\n\u0010\f\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0012\u0004\u0012\u00020\r\u0018\u00010\u0006¢\u0006\u0002\b\u000eH\b¢\u0006\u0002\u0010\u0015\u001a~\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0006\u0012\u0002\b\u00030\u00142$\b\b\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\b2\u0006\u0010\t\u001a\u00020\u00102\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00102!\b\n\u0010\f\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0012\u0004\u0012\u00020\r\u0018\u00010\u0006¢\u0006\u0002\b\u000eH\b\u001ah\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0006\u0012\u0002\b\u00030\u00142$\b\b\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\b2\u001f\b\b\u0010\f\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0012\u0004\u0012\u00020\r0\u0006¢\u0006\u0002\b\u000eH\b*4\u0010\u0016\u001a\u0004\b\u0000\u0010\u0002\"\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u00062\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0006¨\u0006\u0017"}, d2 = {"alert", "Lorg/jetbrains/anko/AlertBuilder;", "D", "Landroid/content/DialogInterface;", "Landroid/app/Fragment;", "factory", "Lkotlin/Function1;", "Landroid/content/Context;", "Lorg/jetbrains/anko/AlertBuilderFactory;", "message", "", "title", "init", "", "Lkotlin/ExtensionFunctionType;", "(Landroid/app/Fragment;Lkotlin/jvm/functions/Function1;ILjava/lang/Integer;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/anko/AlertBuilder;", "", "messageResource", "titleResource", "(Landroid/content/Context;Lkotlin/jvm/functions/Function1;ILjava/lang/Integer;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/anko/AlertBuilder;", "Lorg/jetbrains/anko/AnkoContext;", "(Lorg/jetbrains/anko/AnkoContext;Lkotlin/jvm/functions/Function1;ILjava/lang/Integer;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/anko/AlertBuilder;", "AlertBuilderFactory", "commons_release"}, k = 2, mv = {1, 1, 5})
/* compiled from: Dialogs.kt */
public final class DialogsKt {
    @org.jetbrains.annotations.NotNull
    public static final <D extends android.content.DialogInterface> org.jetbrains.anko.AlertBuilder<D> alert(@org.jetbrains.annotations.NotNull android.content.Context r1, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super android.content.Context, ? extends org.jetbrains.anko.AlertBuilder<? extends D>> r2, int r3, @org.jetbrains.annotations.Nullable java.lang.Integer r4, @org.jetbrains.annotations.Nullable kotlin.jvm.functions.Function1<? super org.jetbrains.anko.AlertBuilder<? extends D>, kotlin.Unit> r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: org.jetbrains.anko.DialogsKt.alert(android.content.Context, kotlin.jvm.functions.Function1, int, java.lang.Integer, kotlin.jvm.functions.Function1):org.jetbrains.anko.AlertBuilder<D>
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r0 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0);
        r0 = "factory";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0);
        r0 = r3.invoke(r2);
        r2 = r0;
        r2 = (org.jetbrains.anko.AlertBuilder) r2;
        if (r5 == 0) goto L_0x001c;
    L_0x0015:
        r1 = r5.intValue();
        r2.setTitleResource(r1);
    L_0x001c:
        r2.setMessageResource(r4);
        if (r6 == 0) goto L_0x0024;
    L_0x0021:
        r6.invoke(r2);
        r0 = (org.jetbrains.anko.AlertBuilder) r0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.DialogsKt.alert(android.content.Context, kotlin.jvm.functions.Function1, int, java.lang.Integer, kotlin.jvm.functions.Function1):org.jetbrains.anko.AlertBuilder<D>");
    }

    @org.jetbrains.annotations.NotNull
    public static final <D extends android.content.DialogInterface> org.jetbrains.anko.AlertBuilder<D> alert(@org.jetbrains.annotations.NotNull android.content.Context r1, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super android.content.Context, ? extends org.jetbrains.anko.AlertBuilder<? extends D>> r2, @org.jetbrains.annotations.NotNull java.lang.String r3, @org.jetbrains.annotations.Nullable java.lang.String r4, @org.jetbrains.annotations.Nullable kotlin.jvm.functions.Function1<? super org.jetbrains.anko.AlertBuilder<? extends D>, kotlin.Unit> r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: org.jetbrains.anko.DialogsKt.alert(android.content.Context, kotlin.jvm.functions.Function1, java.lang.String, java.lang.String, kotlin.jvm.functions.Function1):org.jetbrains.anko.AlertBuilder<D>
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r0 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r1, r0);
        r0 = "factory";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0);
        r0 = "message";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0);
        r0 = r2.invoke(r1);
        r1 = r0;
        r1 = (org.jetbrains.anko.AlertBuilder) r1;
        if (r4 == 0) goto L_0x0020;
    L_0x001b:
        r4 = (java.lang.CharSequence) r4;
        r1.setTitle(r4);
    L_0x0020:
        r3 = (java.lang.CharSequence) r3;
        r1.setMessage(r3);
        if (r5 == 0) goto L_0x002a;
    L_0x0027:
        r5.invoke(r1);
        r0 = (org.jetbrains.anko.AlertBuilder) r0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.DialogsKt.alert(android.content.Context, kotlin.jvm.functions.Function1, java.lang.String, java.lang.String, kotlin.jvm.functions.Function1):org.jetbrains.anko.AlertBuilder<D>");
    }

    @NotNull
    public static final <D extends DialogInterface> AlertBuilder<D> alert(@NotNull AnkoContext<?> $receiver, @NotNull Function1<? super Context, ? extends AlertBuilder<? extends D>> factory, @NotNull String message, @Nullable String title, @Nullable Function1<? super AlertBuilder<? extends D>, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(message, SettingsJsonConstants.PROMPT_MESSAGE_KEY);
        return alert($receiver.getCtx(), (Function1) factory, message, title, (Function1) init);
    }

    @NotNull
    public static final <D extends DialogInterface> AlertBuilder<D> alert(@NotNull Fragment $receiver, @NotNull Function1<? super Context, ? extends AlertBuilder<? extends D>> factory, @NotNull String message, @Nullable String title, @Nullable Function1<? super AlertBuilder<? extends D>, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(message, SettingsJsonConstants.PROMPT_MESSAGE_KEY);
        return alert((Context) $receiver.getActivity(), (Function1) factory, message, title, (Function1) init);
    }

    @NotNull
    public static final <D extends DialogInterface> AlertBuilder<D> alert(@NotNull AnkoContext<?> $receiver, @NotNull Function1<? super Context, ? extends AlertBuilder<? extends D>> factory, int message, @Nullable Integer title, @Nullable Function1<? super AlertBuilder<? extends D>, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        return alert($receiver.getCtx(), (Function1) factory, message, title, (Function1) init);
    }

    @NotNull
    public static final <D extends DialogInterface> AlertBuilder<D> alert(@NotNull Fragment $receiver, @NotNull Function1<? super Context, ? extends AlertBuilder<? extends D>> factory, int message, @Nullable Integer title, @Nullable Function1<? super AlertBuilder<? extends D>, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        return alert((Context) $receiver.getActivity(), (Function1) factory, message, title, (Function1) init);
    }

    @NotNull
    public static final <D extends DialogInterface> AlertBuilder<D> alert(@NotNull AnkoContext<?> $receiver, @NotNull Function1<? super Context, ? extends AlertBuilder<? extends D>> factory, @NotNull Function1<? super AlertBuilder<? extends D>, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(init, "init");
        return alert($receiver.getCtx(), (Function1) factory, (Function1) init);
    }

    @NotNull
    public static final <D extends DialogInterface> AlertBuilder<D> alert(@NotNull Fragment $receiver, @NotNull Function1<? super Context, ? extends AlertBuilder<? extends D>> factory, @NotNull Function1<? super AlertBuilder<? extends D>, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(init, "init");
        return alert((Context) $receiver.getActivity(), (Function1) factory, (Function1) init);
    }

    @NotNull
    public static final <D extends DialogInterface> AlertBuilder<D> alert(@NotNull Context $receiver, @NotNull Function1<? super Context, ? extends AlertBuilder<? extends D>> factory, @NotNull Function1<? super AlertBuilder<? extends D>, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(init, "init");
        AlertBuilder invoke = factory.invoke($receiver);
        init.invoke(invoke);
        return invoke;
    }
}
