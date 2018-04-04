package org.jetbrains.anko;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\u001aJ\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052!\b\n\u0010\u0007\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0001\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\b\u001aO\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\t0\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\f2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\f2!\b\n\u0010\u0007\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0001\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\b¢\u0006\u0002\u0010\r\u001a4\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\t0\u0001*\u00020\u00032\u001f\b\b\u0010\u0007\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0001\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\b\u001aG\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052!\b\u0002\u0010\u0007\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0001\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000b\u001aL\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\t0\u0001*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\f2!\b\u0002\u0010\u0007\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0001\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000b¢\u0006\u0002\u0010\u0011\u001a/\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\t0\u0001*\u00020\u000e2\u001d\u0010\u0007\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0001\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000b\u001aN\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052!\b\n\u0010\u0007\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0001\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\b\u001aS\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\t0\u0001*\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0004\u001a\u00020\f2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\f2!\b\n\u0010\u0007\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0001\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\b¢\u0006\u0002\u0010\u0013\u001a8\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\t0\u0001*\u0006\u0012\u0002\b\u00030\u00122\u001f\b\b\u0010\u0007\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0001\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\b\u001aB\u0010\u0014\u001a\u00020\u0015*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u001b\b\n\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\b\u001aG\u0010\u0014\u001a\u00020\u0015*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\f2\u001b\b\n\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\b¢\u0006\u0002\u0010\u0016\u001a?\u0010\u0014\u001a\u00020\u0015*\u00020\u000e2\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u001b\b\u0002\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000b\u001aD\u0010\u0014\u001a\u00020\u0015*\u00020\u000e2\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\f2\u001b\b\u0002\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000b¢\u0006\u0002\u0010\u0017\u001aF\u0010\u0014\u001a\u00020\u0015*\u0006\u0012\u0002\b\u00030\u00122\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u001b\b\n\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\b\u001aK\u0010\u0014\u001a\u00020\u0015*\u0006\u0012\u0002\b\u00030\u00122\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\f2\u001b\b\n\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\b¢\u0006\u0002\u0010\u0018\u001aB\u0010\u0019\u001a\u00020\u0015*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u001b\b\n\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\b\u001aG\u0010\u0019\u001a\u00020\u0015*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\f2\u001b\b\n\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\b¢\u0006\u0002\u0010\u0016\u001aI\u0010\u0019\u001a\u00020\u0015*\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u001b2\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u001b\b\u0002\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\u0002\u001a?\u0010\u0019\u001a\u00020\u0015*\u00020\u000e2\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u001b\b\u0002\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000b\u001aD\u0010\u0019\u001a\u00020\u0015*\u00020\u000e2\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\f2\u001b\b\u0002\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000b¢\u0006\u0002\u0010\u0017\u001aF\u0010\u0019\u001a\u00020\u0015*\u0006\u0012\u0002\b\u00030\u00122\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u001b\b\n\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\b\u001aK\u0010\u0019\u001a\u00020\u0015*\u0006\u0012\u0002\b\u00030\u00122\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\f2\u001b\b\n\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\b¢\u0006\u0002\u0010\u0018¨\u0006\u001c"}, d2 = {"alert", "Lorg/jetbrains/anko/AlertBuilder;", "Landroid/app/AlertDialog;", "Landroid/app/Fragment;", "message", "", "title", "init", "Lkotlin/Function1;", "Landroid/content/DialogInterface;", "", "Lkotlin/ExtensionFunctionType;", "", "(Landroid/app/Fragment;ILjava/lang/Integer;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/anko/AlertBuilder;", "Landroid/content/Context;", "messageResource", "titleResource", "(Landroid/content/Context;ILjava/lang/Integer;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/anko/AlertBuilder;", "Lorg/jetbrains/anko/AnkoContext;", "(Lorg/jetbrains/anko/AnkoContext;ILjava/lang/Integer;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/anko/AlertBuilder;", "indeterminateProgressDialog", "Landroid/app/ProgressDialog;", "(Landroid/app/Fragment;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;)Landroid/app/ProgressDialog;", "(Landroid/content/Context;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;)Landroid/app/ProgressDialog;", "(Lorg/jetbrains/anko/AnkoContext;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;)Landroid/app/ProgressDialog;", "progressDialog", "indeterminate", "", "commons_release"}, k = 2, mv = {1, 1, 5})
/* compiled from: AndroidDialogs.kt */
public final class AndroidDialogsKt {
    @org.jetbrains.annotations.NotNull
    public static final org.jetbrains.anko.AlertBuilder<android.content.DialogInterface> alert(@org.jetbrains.annotations.NotNull android.content.Context r1, int r2, @org.jetbrains.annotations.Nullable java.lang.Integer r3, @org.jetbrains.annotations.Nullable kotlin.jvm.functions.Function1<? super org.jetbrains.anko.AlertBuilder<? extends android.content.DialogInterface>, kotlin.Unit> r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: org.jetbrains.anko.AndroidDialogsKt.alert(android.content.Context, int, java.lang.Integer, kotlin.jvm.functions.Function1):org.jetbrains.anko.AlertBuilder<android.content.DialogInterface>
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
        r0 = new org.jetbrains.anko.AndroidAlertBuilder;
        r0.<init>(r2);
        r2 = r0;
        r2 = (org.jetbrains.anko.AndroidAlertBuilder) r2;
        if (r4 == 0) goto L_0x0017;
    L_0x0010:
        r1 = r4.intValue();
        r2.setTitleResource(r1);
    L_0x0017:
        r2.setMessageResource(r3);
        if (r5 == 0) goto L_0x001f;
    L_0x001c:
        r5.invoke(r2);
        r0 = (org.jetbrains.anko.AlertBuilder) r0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.AndroidDialogsKt.alert(android.content.Context, int, java.lang.Integer, kotlin.jvm.functions.Function1):org.jetbrains.anko.AlertBuilder<android.content.DialogInterface>");
    }

    @org.jetbrains.annotations.NotNull
    public static final org.jetbrains.anko.AlertBuilder<android.app.AlertDialog> alert(@org.jetbrains.annotations.NotNull android.content.Context r1, @org.jetbrains.annotations.NotNull java.lang.CharSequence r2, @org.jetbrains.annotations.Nullable java.lang.CharSequence r3, @org.jetbrains.annotations.Nullable kotlin.jvm.functions.Function1<? super org.jetbrains.anko.AlertBuilder<? extends android.content.DialogInterface>, kotlin.Unit> r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: org.jetbrains.anko.AndroidDialogsKt.alert(android.content.Context, java.lang.CharSequence, java.lang.CharSequence, kotlin.jvm.functions.Function1):org.jetbrains.anko.AlertBuilder<android.app.AlertDialog>
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
        r0 = "message";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0);
        r0 = new org.jetbrains.anko.AndroidAlertBuilder;
        r0.<init>(r1);
        r1 = r0;
        r1 = (org.jetbrains.anko.AndroidAlertBuilder) r1;
        if (r3 == 0) goto L_0x0019;
    L_0x0016:
        r1.setTitle(r3);
    L_0x0019:
        r1.setMessage(r2);
        if (r4 == 0) goto L_0x0021;
    L_0x001e:
        r4.invoke(r1);
        r0 = (org.jetbrains.anko.AlertBuilder) r0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.AndroidDialogsKt.alert(android.content.Context, java.lang.CharSequence, java.lang.CharSequence, kotlin.jvm.functions.Function1):org.jetbrains.anko.AlertBuilder<android.app.AlertDialog>");
    }

    @NotNull
    public static final AlertBuilder<AlertDialog> alert(@NotNull AnkoContext<?> $receiver, @NotNull CharSequence message, @Nullable CharSequence title, @Nullable Function1<? super AlertBuilder<? extends DialogInterface>, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, SettingsJsonConstants.PROMPT_MESSAGE_KEY);
        return alert($receiver.getCtx(), message, title, (Function1) init);
    }

    @NotNull
    public static final AlertBuilder<AlertDialog> alert(@NotNull Fragment $receiver, @NotNull CharSequence message, @Nullable CharSequence title, @Nullable Function1<? super AlertBuilder<? extends DialogInterface>, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, SettingsJsonConstants.PROMPT_MESSAGE_KEY);
        return alert((Context) $receiver.getActivity(), message, title, (Function1) init);
    }

    @NotNull
    public static final AlertBuilder<DialogInterface> alert(@NotNull AnkoContext<?> $receiver, int message, @Nullable Integer title, @Nullable Function1<? super AlertBuilder<? extends DialogInterface>, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return alert($receiver.getCtx(), message, title, (Function1) init);
    }

    @NotNull
    public static final AlertBuilder<DialogInterface> alert(@NotNull Fragment $receiver, int message, @Nullable Integer title, @Nullable Function1<? super AlertBuilder<? extends DialogInterface>, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return alert((Context) $receiver.getActivity(), message, title, (Function1) init);
    }

    @NotNull
    public static final AlertBuilder<DialogInterface> alert(@NotNull AnkoContext<?> $receiver, @NotNull Function1<? super AlertBuilder<? extends DialogInterface>, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(init, "init");
        return alert($receiver.getCtx(), (Function1) init);
    }

    @NotNull
    public static final AlertBuilder<DialogInterface> alert(@NotNull Fragment $receiver, @NotNull Function1<? super AlertBuilder<? extends DialogInterface>, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(init, "init");
        return alert((Context) $receiver.getActivity(), (Function1) init);
    }

    @NotNull
    public static final AlertBuilder<DialogInterface> alert(@NotNull Context $receiver, @NotNull Function1<? super AlertBuilder<? extends DialogInterface>, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(init, "init");
        AndroidAlertBuilder androidAlertBuilder = new AndroidAlertBuilder($receiver);
        init.invoke(androidAlertBuilder);
        return androidAlertBuilder;
    }

    @NotNull
    public static final ProgressDialog progressDialog(@NotNull AnkoContext<?> $receiver, @Nullable Integer message, @Nullable Integer title, @Nullable Function1<? super ProgressDialog, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return progressDialog($receiver.getCtx(), message, title, (Function1) init);
    }

    @NotNull
    public static final ProgressDialog progressDialog(@NotNull Fragment $receiver, @Nullable Integer message, @Nullable Integer title, @Nullable Function1<? super ProgressDialog, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return progressDialog((Context) $receiver.getActivity(), message, title, (Function1) init);
    }

    @NotNull
    public static final ProgressDialog progressDialog(@NotNull Context $receiver, @Nullable Integer message, @Nullable Integer title, @Nullable Function1<? super ProgressDialog, Unit> init) {
        String string;
        Context $receiver2;
        CharSequence charSequence;
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        if (message != null) {
            string = $receiver.getString(message.intValue());
            $receiver2 = $receiver;
        } else {
            string = null;
            $receiver2 = $receiver;
        }
        CharSequence charSequence2 = string;
        if (title != null) {
            charSequence = charSequence2;
            string = $receiver.getString(title.intValue());
        } else {
            charSequence = charSequence2;
            string = null;
        }
        return progressDialog($receiver2, false, charSequence, string, init);
    }

    @NotNull
    public static final ProgressDialog indeterminateProgressDialog(@NotNull AnkoContext<?> $receiver, @Nullable Integer message, @Nullable Integer title, @Nullable Function1<? super ProgressDialog, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return indeterminateProgressDialog($receiver.getCtx(), message, title, (Function1) init);
    }

    @NotNull
    public static final ProgressDialog indeterminateProgressDialog(@NotNull Fragment $receiver, @Nullable Integer message, @Nullable Integer title, @Nullable Function1<? super ProgressDialog, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return indeterminateProgressDialog((Context) $receiver.getActivity(), message, title, (Function1) init);
    }

    @NotNull
    public static final ProgressDialog indeterminateProgressDialog(@NotNull Context $receiver, @Nullable Integer message, @Nullable Integer title, @Nullable Function1<? super ProgressDialog, Unit> init) {
        String string;
        Context $receiver2;
        CharSequence charSequence;
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        if (message != null) {
            string = $receiver.getString(message.intValue());
            $receiver2 = $receiver;
        } else {
            string = null;
            $receiver2 = $receiver;
        }
        CharSequence charSequence2 = string;
        if (title != null) {
            charSequence = charSequence2;
            string = $receiver.getString(title.intValue());
        } else {
            charSequence = charSequence2;
            string = null;
        }
        return progressDialog($receiver2, true, charSequence, string, init);
    }

    @NotNull
    public static final ProgressDialog progressDialog(@NotNull AnkoContext<?> $receiver, @Nullable CharSequence message, @Nullable CharSequence title, @Nullable Function1<? super ProgressDialog, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return progressDialog($receiver.getCtx(), message, title, (Function1) init);
    }

    @NotNull
    public static final ProgressDialog progressDialog(@NotNull Fragment $receiver, @Nullable CharSequence message, @Nullable CharSequence title, @Nullable Function1<? super ProgressDialog, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return progressDialog((Context) $receiver.getActivity(), message, title, (Function1) init);
    }

    @NotNull
    public static final ProgressDialog progressDialog(@NotNull Context $receiver, @Nullable CharSequence message, @Nullable CharSequence title, @Nullable Function1<? super ProgressDialog, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return progressDialog($receiver, false, message, title, init);
    }

    @NotNull
    public static final ProgressDialog indeterminateProgressDialog(@NotNull AnkoContext<?> $receiver, @Nullable CharSequence message, @Nullable CharSequence title, @Nullable Function1<? super ProgressDialog, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return indeterminateProgressDialog($receiver.getCtx(), message, title, (Function1) init);
    }

    @NotNull
    public static final ProgressDialog indeterminateProgressDialog(@NotNull Fragment $receiver, @Nullable CharSequence message, @Nullable CharSequence title, @Nullable Function1<? super ProgressDialog, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return indeterminateProgressDialog((Context) $receiver.getActivity(), message, title, (Function1) init);
    }

    @NotNull
    public static final ProgressDialog indeterminateProgressDialog(@NotNull Context $receiver, @Nullable CharSequence message, @Nullable CharSequence title, @Nullable Function1<? super ProgressDialog, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return progressDialog($receiver, true, message, title, init);
    }

    static /* bridge */ /* synthetic */ ProgressDialog progressDialog$default(Context context, boolean z, CharSequence charSequence, CharSequence charSequence2, Function1 function1, int i, Object obj) {
        CharSequence charSequence3;
        if ((i & 2) != 0) {
            charSequence = (CharSequence) null;
        }
        if ((i & 4) != 0) {
            charSequence3 = (CharSequence) null;
        } else {
            charSequence3 = charSequence2;
        }
        return progressDialog(context, z, charSequence, charSequence3, (i & 8) != 0 ? (Function1) null : function1);
    }

    private static final ProgressDialog progressDialog(@NotNull Context $receiver, boolean indeterminate, CharSequence message, CharSequence title, Function1<? super ProgressDialog, Unit> init) {
        ProgressDialog progressDialog = new ProgressDialog($receiver);
        ProgressDialog $receiver2 = progressDialog;
        $receiver2.setIndeterminate(indeterminate);
        if (!indeterminate) {
            $receiver2.setProgressStyle(1);
        }
        if (message != null) {
            $receiver2.setMessage(message);
        }
        if (title != null) {
            $receiver2.setTitle(title);
        }
        if (init != null) {
            init.invoke($receiver2);
        }
        $receiver2.show();
        return progressDialog;
    }
}
