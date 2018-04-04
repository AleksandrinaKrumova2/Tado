package kotlin.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000X\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0017\u0010\u0000\u001a\u00020\u0005*\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\b\u001a\u001c\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u001a\u001e\u0010\n\u001a\u00020\u000b*\u00020\u00022\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000b0\r\u001a\u0010\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010*\u00020\u0001\u001a\n\u0010\u0011\u001a\u00020\u0012*\u00020\u0013\u001a\u0010\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0015*\u00020\u0002\u001a\n\u0010\u0016\u001a\u00020\u000e*\u00020\u0002\u001a\u0017\u0010\u0016\u001a\u00020\u000e*\u00020\u00132\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\b\u001a\r\u0010\u0019\u001a\u00020\u001a*\u00020\u000eH\b\u001a5\u0010\u001b\u001a\u0002H\u001c\"\u0004\b\u0000\u0010\u001c*\u00020\u00022\u0018\u0010\u001d\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u0010\u0012\u0004\u0012\u0002H\u001c0\rH\bø\u0001\u0000¢\u0006\u0002\u0010\u001f\u0002\b\n\u0006\b\u0011(\u001e0\u0001¨\u0006 "}, d2 = {"buffered", "Ljava/io/BufferedReader;", "Ljava/io/Reader;", "bufferSize", "", "Ljava/io/BufferedWriter;", "Ljava/io/Writer;", "copyTo", "", "out", "forEachLine", "", "action", "Lkotlin/Function1;", "", "lineSequence", "Lkotlin/sequences/Sequence;", "readBytes", "", "Ljava/net/URL;", "readLines", "", "readText", "charset", "Ljava/nio/charset/Charset;", "reader", "Ljava/io/StringReader;", "useLines", "T", "block", "Requires newer compiler version to be inlined correctly.", "(Ljava/io/Reader;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 2, mv = {1, 1, 9})
@JvmName(name = "TextStreamsKt")
/* compiled from: ReadWrite.kt */
public final class TextStreamsKt {
    public static final void forEachLine(@org.jetbrains.annotations.NotNull java.io.Reader r1, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.io.TextStreamsKt.forEachLine(java.io.Reader, kotlin.jvm.functions.Function1):void
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r6 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r6);
        r6 = "action";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r6);
        r1 = r11;
        r7 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r6 = r1 instanceof java.io.BufferedReader;
        if (r6 == 0) goto L_0x0041;
    L_0x0013:
        r1 = (java.io.BufferedReader) r1;
        r6 = r1;
        r6 = (java.io.Closeable) r6;
        r7 = 0;
        r7 = (java.lang.Throwable) r7;
        r0 = r6;	 Catch:{ Throwable -> 0x0037 }
        r0 = (java.io.BufferedReader) r0;	 Catch:{ Throwable -> 0x0037 }
        r5 = r0;	 Catch:{ Throwable -> 0x0037 }
        r4 = lineSequence(r5);	 Catch:{ Throwable -> 0x0037 }
        r2 = r12;	 Catch:{ Throwable -> 0x0037 }
        r8 = r4.iterator();	 Catch:{ Throwable -> 0x0037 }
        r9 = r8.hasNext();	 Catch:{ Throwable -> 0x0037 }
        if (r9 == 0) goto L_0x0047;	 Catch:{ Throwable -> 0x0037 }
    L_0x002f:
        r3 = r8.next();	 Catch:{ Throwable -> 0x0037 }
        r2.invoke(r3);	 Catch:{ Throwable -> 0x0037 }
        goto L_0x0029;
    L_0x0037:
        r7 = move-exception;
        throw r7;	 Catch:{ all -> 0x0039 }
    L_0x0039:
        r8 = move-exception;
        r10 = r8;
        r8 = r7;
        r7 = r10;
        kotlin.io.CloseableKt.closeFinally(r6, r8);
        throw r7;
    L_0x0041:
        r6 = new java.io.BufferedReader;
        r6.<init>(r1, r7);
        goto L_0x0016;
        r8 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0037 }
        kotlin.io.CloseableKt.closeFinally(r6, r7);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.TextStreamsKt.forEachLine(java.io.Reader, kotlin.jvm.functions.Function1):void");
    }

    public static final <T> T useLines(@org.jetbrains.annotations.NotNull java.io.Reader r1, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super kotlin.sequences.Sequence<java.lang.String>, ? extends T> r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.io.TextStreamsKt.useLines(java.io.Reader, kotlin.jvm.functions.Function1):T
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r5 = 0;
        r6 = 1;
        r2 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r2);
        r2 = "block";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r2);
        r3 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r2 = r8 instanceof java.io.BufferedReader;
        if (r2 == 0) goto L_0x0039;
    L_0x0014:
        r8 = (java.io.BufferedReader) r8;
        r2 = r8;
    L_0x0017:
        r2 = (java.io.Closeable) r2;
        r3 = 0;
        r3 = (java.lang.Throwable) r3;
        r0 = r2;	 Catch:{ Throwable -> 0x0043 }
        r0 = (java.io.BufferedReader) r0;	 Catch:{ Throwable -> 0x0043 }
        r1 = r0;	 Catch:{ Throwable -> 0x0043 }
        r4 = lineSequence(r1);	 Catch:{ Throwable -> 0x0043 }
        r4 = r9.invoke(r4);	 Catch:{ Throwable -> 0x0043 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r6);
        r5 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r6, r6, r5);
        if (r5 == 0) goto L_0x003f;
    L_0x0032:
        kotlin.io.CloseableKt.closeFinally(r2, r3);
    L_0x0035:
        kotlin.jvm.internal.InlineMarker.finallyEnd(r6);
        return r4;
    L_0x0039:
        r2 = new java.io.BufferedReader;
        r2.<init>(r8, r3);
        goto L_0x0017;
    L_0x003f:
        r2.close();
        goto L_0x0035;
    L_0x0043:
        r3 = move-exception;
        throw r3;	 Catch:{ all -> 0x0045 }
    L_0x0045:
        r4 = move-exception;
        r7 = r4;
        r4 = r3;
        r3 = r7;
        kotlin.jvm.internal.InlineMarker.finallyStart(r6);
        r5 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r6, r6, r5);
        if (r5 == 0) goto L_0x0059;
    L_0x0052:
        kotlin.io.CloseableKt.closeFinally(r2, r4);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r6);
        throw r3;
    L_0x0059:
        if (r4 != 0) goto L_0x005f;
    L_0x005b:
        r2.close();
        goto L_0x0055;
        r2.close();	 Catch:{ Throwable -> 0x0064 }
        goto L_0x0055;
    L_0x0064:
        r2 = move-exception;
        goto L_0x0055;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.TextStreamsKt.useLines(java.io.Reader, kotlin.jvm.functions.Function1):T");
    }

    @InlineOnly
    private static final BufferedReader buffered(@NotNull Reader $receiver, int bufferSize) {
        return $receiver instanceof BufferedReader ? (BufferedReader) $receiver : new BufferedReader($receiver, bufferSize);
    }

    @InlineOnly
    private static final BufferedWriter buffered(@NotNull Writer $receiver, int bufferSize) {
        return $receiver instanceof BufferedWriter ? (BufferedWriter) $receiver : new BufferedWriter($receiver, bufferSize);
    }

    @NotNull
    public static final List<String> readLines(@NotNull Reader $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        ArrayList result = new ArrayList();
        forEachLine($receiver, new TextStreamsKt$readLines$1(result));
        return result;
    }

    @InlineOnly
    private static final StringReader reader(@NotNull String $receiver) {
        return new StringReader($receiver);
    }

    @NotNull
    public static final Sequence<String> lineSequence(@NotNull BufferedReader $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return SequencesKt__SequencesKt.constrainOnce(new LinesSequence($receiver));
    }

    @NotNull
    public static final String readText(@NotNull Reader $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        StringWriter buffer = new StringWriter();
        copyTo$default($receiver, buffer, 0, 2, null);
        String stringWriter = buffer.toString();
        Intrinsics.checkExpressionValueIsNotNull(stringWriter, "buffer.toString()");
        return stringWriter;
    }

    public static /* bridge */ /* synthetic */ long copyTo$default(Reader reader, Writer writer, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 8192;
        }
        return copyTo(reader, writer, i);
    }

    public static final long copyTo(@NotNull Reader $receiver, @NotNull Writer out, int bufferSize) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(out, "out");
        long charsCopied = 0;
        char[] buffer = new char[bufferSize];
        int chars = $receiver.read(buffer);
        while (chars >= 0) {
            out.write(buffer, 0, chars);
            charsCopied += (long) chars;
            chars = $receiver.read(buffer);
        }
        return charsCopied;
    }

    @InlineOnly
    private static final String readText(@NotNull URL $receiver, Charset charset) {
        return new String(readBytes($receiver), charset);
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ String readText$default(URL $receiver, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return new String(readBytes($receiver), charset);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.NotNull
    public static final byte[] readBytes(@org.jetbrains.annotations.NotNull java.net.URL r8) {
        /*
        r3 = 0;
        r2 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r2);
        r2 = r8.openStream();
        r2 = (java.io.Closeable) r2;
        r3 = (java.lang.Throwable) r3;
        r0 = r2;
        r0 = (java.io.InputStream) r0;	 Catch:{ Throwable -> 0x0025 }
        r1 = r0;
        r4 = "it";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r4);	 Catch:{ Throwable -> 0x0025 }
        r4 = 0;
        r5 = 1;
        r6 = 0;
        r4 = kotlin.io.ByteStreamsKt.readBytes$default(r1, r4, r5, r6);	 Catch:{ Throwable -> 0x0025 }
        kotlin.io.CloseableKt.closeFinally(r2, r3);
        return r4;
    L_0x0025:
        r3 = move-exception;
        throw r3;	 Catch:{ all -> 0x0027 }
    L_0x0027:
        r4 = move-exception;
        r7 = r4;
        r4 = r3;
        r3 = r7;
        kotlin.io.CloseableKt.closeFinally(r2, r4);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.TextStreamsKt.readBytes(java.net.URL):byte[]");
    }
}
