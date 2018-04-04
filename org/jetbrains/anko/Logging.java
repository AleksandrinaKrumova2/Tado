package org.jetbrains.anko;

import android.util.Log;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0015\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\b\u001a\u0012\u0010\u0000\u001a\u00020\u00012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0014\u0010\b\u001a\u00020\u00072\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002\u001ag\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0018\u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n0\u00122\u001e\u0010\u0013\u001a\u001a\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\n0\u0014H\b\u001a\u001d\u0010\u0015\u001a\u00020\n*\u00020\u00012\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0016H\b\u001a \u0010\u0015\u001a\u00020\n*\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u001a\u001d\u0010\u0017\u001a\u00020\n*\u00020\u00012\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0016H\b\u001a \u0010\u0017\u001a\u00020\n*\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u001a\r\u0010\u0018\u001a\u00020\u0007*\u00020\u000eH\b\u001a\u001d\u0010\u0019\u001a\u00020\n*\u00020\u00012\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0016H\b\u001a \u0010\u0019\u001a\u00020\n*\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u001a\u001d\u0010\u001a\u001a\u00020\n*\u00020\u00012\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0016H\b\u001a \u0010\u001a\u001a\u00020\n*\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u001a\u001d\u0010\u001b\u001a\u00020\n*\u00020\u00012\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0016H\b\u001a \u0010\u001b\u001a\u00020\n*\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u001a \u0010\u001c\u001a\u00020\n*\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e¨\u0006\u001d"}, d2 = {"AnkoLogger", "Lorg/jetbrains/anko/AnkoLogger;", "T", "", "clazz", "Ljava/lang/Class;", "tag", "", "getTag", "log", "", "logger", "message", "thr", "", "level", "", "f", "Lkotlin/Function2;", "fThrowable", "Lkotlin/Function3;", "debug", "Lkotlin/Function0;", "error", "getStackTraceString", "info", "verbose", "warn", "wtf", "commons_release"}, k = 2, mv = {1, 1, 5})
@JvmName(name = "Logging")
/* compiled from: Logging.kt */
public final class Logging {
    @NotNull
    public static final AnkoLogger AnkoLogger(@NotNull Class<?> clazz) {
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        return new Logging$AnkoLogger$1(clazz);
    }

    @NotNull
    public static final AnkoLogger AnkoLogger(@NotNull String tag) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        return new Logging$AnkoLogger$2(tag);
    }

    private static final <T> AnkoLogger AnkoLogger() {
        Intrinsics.reifiedOperationMarker(4, "T");
        return AnkoLogger(Object.class);
    }

    public static /* bridge */ /* synthetic */ void verbose$default(AnkoLogger ankoLogger, Object obj, Throwable th, int i, Object obj2) {
        verbose(ankoLogger, obj, (i & 2) != 0 ? (Throwable) null : th);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void verbose(@org.jetbrains.annotations.NotNull org.jetbrains.anko.AnkoLogger r4, @org.jetbrains.annotations.Nullable java.lang.Object r5, @org.jetbrains.annotations.Nullable java.lang.Throwable r6) {
        /*
        r3 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r3);
        r0 = 2;
        r2 = r4.getLoggerTag();
        r3 = android.util.Log.isLoggable(r2, r0);
        if (r3 == 0) goto L_0x0025;
    L_0x0011:
        if (r6 == 0) goto L_0x002a;
    L_0x0013:
        if (r5 == 0) goto L_0x0026;
    L_0x0015:
        r3 = r5.toString();
        if (r3 == 0) goto L_0x0026;
    L_0x001b:
        r6 = (java.lang.Throwable) r6;
        r3 = (java.lang.String) r3;
        r1 = r2;
        r1 = (java.lang.String) r1;
        android.util.Log.v(r1, r3, r6);
    L_0x0025:
        return;
    L_0x0026:
        r3 = "null";
        goto L_0x001b;
    L_0x002a:
        if (r5 == 0) goto L_0x003b;
    L_0x002c:
        r3 = r5.toString();
        if (r3 == 0) goto L_0x003b;
    L_0x0032:
        r3 = (java.lang.String) r3;
        r1 = r2;
        r1 = (java.lang.String) r1;
        android.util.Log.v(r1, r3);
        goto L_0x0025;
    L_0x003b:
        r3 = "null";
        goto L_0x0032;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.Logging.verbose(org.jetbrains.anko.AnkoLogger, java.lang.Object, java.lang.Throwable):void");
    }

    public static /* bridge */ /* synthetic */ void debug$default(AnkoLogger ankoLogger, Object obj, Throwable th, int i, Object obj2) {
        debug(ankoLogger, obj, (i & 2) != 0 ? (Throwable) null : th);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void debug(@org.jetbrains.annotations.NotNull org.jetbrains.anko.AnkoLogger r4, @org.jetbrains.annotations.Nullable java.lang.Object r5, @org.jetbrains.annotations.Nullable java.lang.Throwable r6) {
        /*
        r3 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r3);
        r0 = 3;
        r2 = r4.getLoggerTag();
        r3 = android.util.Log.isLoggable(r2, r0);
        if (r3 == 0) goto L_0x0025;
    L_0x0011:
        if (r6 == 0) goto L_0x002a;
    L_0x0013:
        if (r5 == 0) goto L_0x0026;
    L_0x0015:
        r3 = r5.toString();
        if (r3 == 0) goto L_0x0026;
    L_0x001b:
        r6 = (java.lang.Throwable) r6;
        r3 = (java.lang.String) r3;
        r1 = r2;
        r1 = (java.lang.String) r1;
        android.util.Log.d(r1, r3, r6);
    L_0x0025:
        return;
    L_0x0026:
        r3 = "null";
        goto L_0x001b;
    L_0x002a:
        if (r5 == 0) goto L_0x003b;
    L_0x002c:
        r3 = r5.toString();
        if (r3 == 0) goto L_0x003b;
    L_0x0032:
        r3 = (java.lang.String) r3;
        r1 = r2;
        r1 = (java.lang.String) r1;
        android.util.Log.d(r1, r3);
        goto L_0x0025;
    L_0x003b:
        r3 = "null";
        goto L_0x0032;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.Logging.debug(org.jetbrains.anko.AnkoLogger, java.lang.Object, java.lang.Throwable):void");
    }

    public static /* bridge */ /* synthetic */ void info$default(AnkoLogger ankoLogger, Object obj, Throwable th, int i, Object obj2) {
        info(ankoLogger, obj, (i & 2) != 0 ? (Throwable) null : th);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void info(@org.jetbrains.annotations.NotNull org.jetbrains.anko.AnkoLogger r4, @org.jetbrains.annotations.Nullable java.lang.Object r5, @org.jetbrains.annotations.Nullable java.lang.Throwable r6) {
        /*
        r3 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r3);
        r0 = 4;
        r2 = r4.getLoggerTag();
        r3 = android.util.Log.isLoggable(r2, r0);
        if (r3 == 0) goto L_0x0025;
    L_0x0011:
        if (r6 == 0) goto L_0x002a;
    L_0x0013:
        if (r5 == 0) goto L_0x0026;
    L_0x0015:
        r3 = r5.toString();
        if (r3 == 0) goto L_0x0026;
    L_0x001b:
        r6 = (java.lang.Throwable) r6;
        r3 = (java.lang.String) r3;
        r1 = r2;
        r1 = (java.lang.String) r1;
        android.util.Log.i(r1, r3, r6);
    L_0x0025:
        return;
    L_0x0026:
        r3 = "null";
        goto L_0x001b;
    L_0x002a:
        if (r5 == 0) goto L_0x003b;
    L_0x002c:
        r3 = r5.toString();
        if (r3 == 0) goto L_0x003b;
    L_0x0032:
        r3 = (java.lang.String) r3;
        r1 = r2;
        r1 = (java.lang.String) r1;
        android.util.Log.i(r1, r3);
        goto L_0x0025;
    L_0x003b:
        r3 = "null";
        goto L_0x0032;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.Logging.info(org.jetbrains.anko.AnkoLogger, java.lang.Object, java.lang.Throwable):void");
    }

    public static /* bridge */ /* synthetic */ void warn$default(AnkoLogger ankoLogger, Object obj, Throwable th, int i, Object obj2) {
        warn(ankoLogger, obj, (i & 2) != 0 ? (Throwable) null : th);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void warn(@org.jetbrains.annotations.NotNull org.jetbrains.anko.AnkoLogger r4, @org.jetbrains.annotations.Nullable java.lang.Object r5, @org.jetbrains.annotations.Nullable java.lang.Throwable r6) {
        /*
        r3 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r3);
        r0 = 5;
        r2 = r4.getLoggerTag();
        r3 = android.util.Log.isLoggable(r2, r0);
        if (r3 == 0) goto L_0x0025;
    L_0x0011:
        if (r6 == 0) goto L_0x002a;
    L_0x0013:
        if (r5 == 0) goto L_0x0026;
    L_0x0015:
        r3 = r5.toString();
        if (r3 == 0) goto L_0x0026;
    L_0x001b:
        r6 = (java.lang.Throwable) r6;
        r3 = (java.lang.String) r3;
        r1 = r2;
        r1 = (java.lang.String) r1;
        android.util.Log.w(r1, r3, r6);
    L_0x0025:
        return;
    L_0x0026:
        r3 = "null";
        goto L_0x001b;
    L_0x002a:
        if (r5 == 0) goto L_0x003b;
    L_0x002c:
        r3 = r5.toString();
        if (r3 == 0) goto L_0x003b;
    L_0x0032:
        r3 = (java.lang.String) r3;
        r1 = r2;
        r1 = (java.lang.String) r1;
        android.util.Log.w(r1, r3);
        goto L_0x0025;
    L_0x003b:
        r3 = "null";
        goto L_0x0032;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.Logging.warn(org.jetbrains.anko.AnkoLogger, java.lang.Object, java.lang.Throwable):void");
    }

    public static /* bridge */ /* synthetic */ void error$default(AnkoLogger ankoLogger, Object obj, Throwable th, int i, Object obj2) {
        error(ankoLogger, obj, (i & 2) != 0 ? (Throwable) null : th);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void error(@org.jetbrains.annotations.NotNull org.jetbrains.anko.AnkoLogger r4, @org.jetbrains.annotations.Nullable java.lang.Object r5, @org.jetbrains.annotations.Nullable java.lang.Throwable r6) {
        /*
        r3 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r3);
        r0 = 6;
        r2 = r4.getLoggerTag();
        r3 = android.util.Log.isLoggable(r2, r0);
        if (r3 == 0) goto L_0x0025;
    L_0x0011:
        if (r6 == 0) goto L_0x002a;
    L_0x0013:
        if (r5 == 0) goto L_0x0026;
    L_0x0015:
        r3 = r5.toString();
        if (r3 == 0) goto L_0x0026;
    L_0x001b:
        r6 = (java.lang.Throwable) r6;
        r3 = (java.lang.String) r3;
        r1 = r2;
        r1 = (java.lang.String) r1;
        android.util.Log.e(r1, r3, r6);
    L_0x0025:
        return;
    L_0x0026:
        r3 = "null";
        goto L_0x001b;
    L_0x002a:
        if (r5 == 0) goto L_0x003b;
    L_0x002c:
        r3 = r5.toString();
        if (r3 == 0) goto L_0x003b;
    L_0x0032:
        r3 = (java.lang.String) r3;
        r1 = r2;
        r1 = (java.lang.String) r1;
        android.util.Log.e(r1, r3);
        goto L_0x0025;
    L_0x003b:
        r3 = "null";
        goto L_0x0032;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.Logging.error(org.jetbrains.anko.AnkoLogger, java.lang.Object, java.lang.Throwable):void");
    }

    public static /* bridge */ /* synthetic */ void wtf$default(AnkoLogger ankoLogger, Object obj, Throwable th, int i, Object obj2) {
        wtf(ankoLogger, obj, (i & 2) != 0 ? (Throwable) null : th);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void wtf(@org.jetbrains.annotations.NotNull org.jetbrains.anko.AnkoLogger r2, @org.jetbrains.annotations.Nullable java.lang.Object r3, @org.jetbrains.annotations.Nullable java.lang.Throwable r4) {
        /*
        r0 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0);
        if (r4 == 0) goto L_0x001c;
    L_0x0008:
        r1 = r2.getLoggerTag();
        if (r3 == 0) goto L_0x0018;
    L_0x000e:
        r0 = r3.toString();
        if (r0 == 0) goto L_0x0018;
    L_0x0014:
        android.util.Log.wtf(r1, r0, r4);
    L_0x0017:
        return;
    L_0x0018:
        r0 = "null";
        goto L_0x0014;
    L_0x001c:
        r1 = r2.getLoggerTag();
        if (r3 == 0) goto L_0x002c;
    L_0x0022:
        r0 = r3.toString();
        if (r0 == 0) goto L_0x002c;
    L_0x0028:
        android.util.Log.wtf(r1, r0);
        goto L_0x0017;
    L_0x002c:
        r0 = "null";
        goto L_0x0028;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.Logging.wtf(org.jetbrains.anko.AnkoLogger, java.lang.Object, java.lang.Throwable):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void verbose(@org.jetbrains.annotations.NotNull org.jetbrains.anko.AnkoLogger r2, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function0<? extends java.lang.Object> r3) {
        /*
        r1 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r1);
        r1 = "message";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r1);
        r0 = r2.getLoggerTag();
        r1 = 2;
        r1 = android.util.Log.isLoggable(r0, r1);
        if (r1 == 0) goto L_0x0026;
    L_0x0017:
        r1 = r3.invoke();
        if (r1 == 0) goto L_0x0027;
    L_0x001d:
        r1 = r1.toString();
        if (r1 == 0) goto L_0x0027;
    L_0x0023:
        android.util.Log.v(r0, r1);
    L_0x0026:
        return;
    L_0x0027:
        r1 = "null";
        goto L_0x0023;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.Logging.verbose(org.jetbrains.anko.AnkoLogger, kotlin.jvm.functions.Function0):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void debug(@org.jetbrains.annotations.NotNull org.jetbrains.anko.AnkoLogger r2, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function0<? extends java.lang.Object> r3) {
        /*
        r1 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r1);
        r1 = "message";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r1);
        r0 = r2.getLoggerTag();
        r1 = 3;
        r1 = android.util.Log.isLoggable(r0, r1);
        if (r1 == 0) goto L_0x0026;
    L_0x0017:
        r1 = r3.invoke();
        if (r1 == 0) goto L_0x0027;
    L_0x001d:
        r1 = r1.toString();
        if (r1 == 0) goto L_0x0027;
    L_0x0023:
        android.util.Log.d(r0, r1);
    L_0x0026:
        return;
    L_0x0027:
        r1 = "null";
        goto L_0x0023;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.Logging.debug(org.jetbrains.anko.AnkoLogger, kotlin.jvm.functions.Function0):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void info(@org.jetbrains.annotations.NotNull org.jetbrains.anko.AnkoLogger r2, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function0<? extends java.lang.Object> r3) {
        /*
        r1 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r1);
        r1 = "message";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r1);
        r0 = r2.getLoggerTag();
        r1 = 4;
        r1 = android.util.Log.isLoggable(r0, r1);
        if (r1 == 0) goto L_0x0026;
    L_0x0017:
        r1 = r3.invoke();
        if (r1 == 0) goto L_0x0027;
    L_0x001d:
        r1 = r1.toString();
        if (r1 == 0) goto L_0x0027;
    L_0x0023:
        android.util.Log.i(r0, r1);
    L_0x0026:
        return;
    L_0x0027:
        r1 = "null";
        goto L_0x0023;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.Logging.info(org.jetbrains.anko.AnkoLogger, kotlin.jvm.functions.Function0):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void warn(@org.jetbrains.annotations.NotNull org.jetbrains.anko.AnkoLogger r2, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function0<? extends java.lang.Object> r3) {
        /*
        r1 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r1);
        r1 = "message";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r1);
        r0 = r2.getLoggerTag();
        r1 = 5;
        r1 = android.util.Log.isLoggable(r0, r1);
        if (r1 == 0) goto L_0x0026;
    L_0x0017:
        r1 = r3.invoke();
        if (r1 == 0) goto L_0x0027;
    L_0x001d:
        r1 = r1.toString();
        if (r1 == 0) goto L_0x0027;
    L_0x0023:
        android.util.Log.w(r0, r1);
    L_0x0026:
        return;
    L_0x0027:
        r1 = "null";
        goto L_0x0023;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.Logging.warn(org.jetbrains.anko.AnkoLogger, kotlin.jvm.functions.Function0):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void error(@org.jetbrains.annotations.NotNull org.jetbrains.anko.AnkoLogger r2, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function0<? extends java.lang.Object> r3) {
        /*
        r1 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r1);
        r1 = "message";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r1);
        r0 = r2.getLoggerTag();
        r1 = 6;
        r1 = android.util.Log.isLoggable(r0, r1);
        if (r1 == 0) goto L_0x0026;
    L_0x0017:
        r1 = r3.invoke();
        if (r1 == 0) goto L_0x0027;
    L_0x001d:
        r1 = r1.toString();
        if (r1 == 0) goto L_0x0027;
    L_0x0023:
        android.util.Log.e(r0, r1);
    L_0x0026:
        return;
    L_0x0027:
        r1 = "null";
        goto L_0x0023;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.Logging.error(org.jetbrains.anko.AnkoLogger, kotlin.jvm.functions.Function0):void");
    }

    @NotNull
    public static final String getStackTraceString(@NotNull Throwable $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        String stackTraceString = Log.getStackTraceString($receiver);
        Intrinsics.checkExpressionValueIsNotNull(stackTraceString, "Log.getStackTraceString(this)");
        return stackTraceString;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final void log(org.jetbrains.anko.AnkoLogger r2, java.lang.Object r3, java.lang.Throwable r4, int r5, kotlin.jvm.functions.Function2<? super java.lang.String, ? super java.lang.String, kotlin.Unit> r6, kotlin.jvm.functions.Function3<? super java.lang.String, ? super java.lang.String, ? super java.lang.Throwable, kotlin.Unit> r7) {
        /*
        r0 = r2.getLoggerTag();
        r1 = android.util.Log.isLoggable(r0, r5);
        if (r1 == 0) goto L_0x0017;
    L_0x000a:
        if (r4 == 0) goto L_0x001c;
    L_0x000c:
        if (r3 == 0) goto L_0x0018;
    L_0x000e:
        r1 = r3.toString();
        if (r1 == 0) goto L_0x0018;
    L_0x0014:
        r7.invoke(r0, r1, r4);
    L_0x0017:
        return;
    L_0x0018:
        r1 = "null";
        goto L_0x0014;
    L_0x001c:
        if (r3 == 0) goto L_0x0028;
    L_0x001e:
        r1 = r3.toString();
        if (r1 == 0) goto L_0x0028;
    L_0x0024:
        r6.invoke(r0, r1);
        goto L_0x0017;
    L_0x0028:
        r1 = "null";
        goto L_0x0024;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.Logging.log(org.jetbrains.anko.AnkoLogger, java.lang.Object, java.lang.Throwable, int, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3):void");
    }

    private static final String getTag(Class<?> clazz) {
        String tag = clazz.getSimpleName();
        if (tag.length() <= 23) {
            Intrinsics.checkExpressionValueIsNotNull(tag, "tag");
            return tag;
        } else if (tag == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        } else {
            tag = tag.substring(0, 23);
            Intrinsics.checkExpressionValueIsNotNull(tag, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return tag;
        }
    }
}
