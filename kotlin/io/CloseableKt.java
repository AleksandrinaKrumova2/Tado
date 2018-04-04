package kotlin.io;

import kotlin.Metadata;
import kotlin.jvm.JvmName;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0001\u001a;\u0010\u0005\u001a\u0002H\u0006\"\n\b\u0000\u0010\u0007*\u0004\u0018\u00010\u0002\"\u0004\b\u0001\u0010\u0006*\u0002H\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u00060\tH\bø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0002\b\n\u0006\b\u0011(\n0\u0001¨\u0006\f"}, d2 = {"closeFinally", "", "Ljava/io/Closeable;", "cause", "", "use", "R", "T", "block", "Lkotlin/Function1;", "Requires newer compiler version to be inlined correctly.", "(Ljava/io/Closeable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 2, mv = {1, 1, 9})
@JvmName(name = "CloseableKt")
/* compiled from: Closeable.kt */
public final class CloseableKt {
    @kotlin.SinceKotlin(version = "1.1")
    @kotlin.PublishedApi
    public static final void closeFinally(@org.jetbrains.annotations.Nullable java.io.Closeable r1, @org.jetbrains.annotations.Nullable java.lang.Throwable r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.io.CloseableKt.closeFinally(java.io.Closeable, java.lang.Throwable):void
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        if (r1 != 0) goto L_0x0003;
    L_0x0002:
        return;
    L_0x0003:
        if (r2 != 0) goto L_0x0009;
    L_0x0005:
        r1.close();
        goto L_0x0002;
        r1.close();	 Catch:{ Throwable -> 0x000e }
        goto L_0x0002;
    L_0x000e:
        r0 = move-exception;
        kotlin.ExceptionsKt__ExceptionsKt.addSuppressed(r2, r0);
        goto L_0x0002;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.CloseableKt.closeFinally(java.io.Closeable, java.lang.Throwable):void");
    }

    @kotlin.internal.InlineOnly
    private static final <T extends java.io.Closeable, R> R use(T r1, kotlin.jvm.functions.Function1<? super T, ? extends R> r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.io.CloseableKt.use(java.io.Closeable, kotlin.jvm.functions.Function1):R
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r3 = 0;
        r4 = 1;
        r1 = 0;
        r1 = (java.lang.Throwable) r1;
        r2 = r6.invoke(r5);	 Catch:{ Throwable -> 0x0020 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r3 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r4, r4, r3);
        if (r3 == 0) goto L_0x001a;
    L_0x0013:
        closeFinally(r5, r1);
    L_0x0016:
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        return r2;
    L_0x001a:
        if (r5 == 0) goto L_0x0016;
    L_0x001c:
        r5.close();
        goto L_0x0016;
    L_0x0020:
        r0 = move-exception;
        r1 = r0;
        throw r0;	 Catch:{ all -> 0x0023 }
    L_0x0023:
        r2 = move-exception;
        kotlin.jvm.internal.InlineMarker.finallyStart(r4);
        r3 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r4, r4, r3);
        if (r3 == 0) goto L_0x0034;
    L_0x002d:
        closeFinally(r5, r1);
    L_0x0030:
        kotlin.jvm.internal.InlineMarker.finallyEnd(r4);
        throw r2;
    L_0x0034:
        if (r5 == 0) goto L_0x0030;
    L_0x0036:
        if (r1 != 0) goto L_0x003c;
    L_0x0038:
        r5.close();
        goto L_0x0030;
        r5.close();	 Catch:{ Throwable -> 0x0041 }
        goto L_0x0030;
    L_0x0041:
        r3 = move-exception;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.CloseableKt.use(java.io.Closeable, kotlin.jvm.functions.Function1):R");
    }
}
