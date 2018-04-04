package kotlin.io;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000z\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001c\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t\u001a!\u0010\n\u001a\u00020\u000b*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\rH\b\u001a!\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\rH\b\u001aB\u0010\u0010\u001a\u00020\u0001*\u00020\u000226\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00010\u0012\u001aJ\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\r26\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00010\u0012\u001a7\u0010\u0018\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00010\u0019\u001a\r\u0010\u001b\u001a\u00020\u001c*\u00020\u0002H\b\u001a\r\u0010\u001d\u001a\u00020\u001e*\u00020\u0002H\b\u001a\u0017\u0010\u001f\u001a\u00020 *\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\b\u001a\n\u0010!\u001a\u00020\u0004*\u00020\u0002\u001a\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00070#*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0014\u0010$\u001a\u00020\u0007*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0017\u0010%\u001a\u00020&*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\b\u001a?\u0010'\u001a\u0002H(\"\u0004\b\u0000\u0010(*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2\u0018\u0010)\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070*\u0012\u0004\u0012\u0002H(0\u0019H\bø\u0001\u0000¢\u0006\u0002\u0010,\u001a\u0012\u0010-\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001c\u0010.\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0017\u0010/\u001a\u000200*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\b\u0002\b\n\u0006\b\u0011(+0\u0001¨\u00061"}, d2 = {"appendBytes", "", "Ljava/io/File;", "array", "", "appendText", "text", "", "charset", "Ljava/nio/charset/Charset;", "bufferedReader", "Ljava/io/BufferedReader;", "bufferSize", "", "bufferedWriter", "Ljava/io/BufferedWriter;", "forEachBlock", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "buffer", "bytesRead", "blockSize", "forEachLine", "Lkotlin/Function1;", "line", "inputStream", "Ljava/io/FileInputStream;", "outputStream", "Ljava/io/FileOutputStream;", "printWriter", "Ljava/io/PrintWriter;", "readBytes", "readLines", "", "readText", "reader", "Ljava/io/InputStreamReader;", "useLines", "T", "block", "Lkotlin/sequences/Sequence;", "Requires newer compiler version to be inlined correctly.", "(Ljava/io/File;Ljava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "writeBytes", "writeText", "writer", "Ljava/io/OutputStreamWriter;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 9}, xi = 1, xs = "kotlin/io/FilesKt")
/* compiled from: FileReadWrite.kt */
class FilesKt__FileReadWriteKt extends FilesKt__FilePathComponentsKt {
    @org.jetbrains.annotations.NotNull
    public static final byte[] readBytes(@org.jetbrains.annotations.NotNull java.io.File r1) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.io.FilesKt__FileReadWriteKt.readBytes(java.io.File):byte[]
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r9 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r15, r9);
        r9 = new java.io.FileInputStream;
        r9.<init>(r15);
        r9 = (java.io.Closeable) r9;
        r10 = 0;
        r10 = (java.lang.Throwable) r10;
        r0 = r9;	 Catch:{ Throwable -> 0x0050 }
        r0 = (java.io.FileInputStream) r0;	 Catch:{ Throwable -> 0x0050 }
        r2 = r0;	 Catch:{ Throwable -> 0x0050 }
        r3 = 0;	 Catch:{ Throwable -> 0x0050 }
        r4 = r15.length();	 Catch:{ Throwable -> 0x0050 }
        r11 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;	 Catch:{ Throwable -> 0x0050 }
        r12 = (long) r11;	 Catch:{ Throwable -> 0x0050 }
        r11 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1));	 Catch:{ Throwable -> 0x0050 }
        if (r11 <= 0) goto L_0x005a;	 Catch:{ Throwable -> 0x0050 }
    L_0x0022:
        r11 = new java.lang.OutOfMemoryError;	 Catch:{ Throwable -> 0x0050 }
        r12 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0050 }
        r12.<init>();	 Catch:{ Throwable -> 0x0050 }
        r13 = "File ";	 Catch:{ Throwable -> 0x0050 }
        r12 = r12.append(r13);	 Catch:{ Throwable -> 0x0050 }
        r12 = r12.append(r15);	 Catch:{ Throwable -> 0x0050 }
        r13 = " is too big (";	 Catch:{ Throwable -> 0x0050 }
        r12 = r12.append(r13);	 Catch:{ Throwable -> 0x0050 }
        r12 = r12.append(r4);	 Catch:{ Throwable -> 0x0050 }
        r13 = " bytes) to fit in memory.";	 Catch:{ Throwable -> 0x0050 }
        r12 = r12.append(r13);	 Catch:{ Throwable -> 0x0050 }
        r12 = r12.toString();	 Catch:{ Throwable -> 0x0050 }
        r11.<init>(r12);	 Catch:{ Throwable -> 0x0050 }
        r11 = (java.lang.Throwable) r11;	 Catch:{ Throwable -> 0x0050 }
        throw r11;	 Catch:{ Throwable -> 0x0050 }
    L_0x0050:
        r10 = move-exception;
        throw r10;	 Catch:{ all -> 0x0052 }
    L_0x0052:
        r11 = move-exception;
        r14 = r11;
        r11 = r10;
        r10 = r14;
        kotlin.io.CloseableKt.closeFinally(r9, r11);
        throw r10;
        r7 = (int) r4;
        r8 = new byte[r7];	 Catch:{ Throwable -> 0x0050 }
        if (r7 <= 0) goto L_0x0066;	 Catch:{ Throwable -> 0x0050 }
        r6 = r2.read(r8, r3, r7);	 Catch:{ Throwable -> 0x0050 }
        if (r6 >= 0) goto L_0x006d;
        if (r7 != 0) goto L_0x0070;
        kotlin.io.CloseableKt.closeFinally(r9, r10);
        return r8;
        r7 = r7 - r6;
        r3 = r3 + r6;
        goto L_0x005e;
        r8 = java.util.Arrays.copyOf(r8, r3);	 Catch:{ Throwable -> 0x0050 }
        r11 = "java.util.Arrays.copyOf(this, newSize)";	 Catch:{ Throwable -> 0x0050 }
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r11);	 Catch:{ Throwable -> 0x0050 }
        goto L_0x0068;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FileReadWriteKt.readBytes(java.io.File):byte[]");
    }

    public static final <T> T useLines(@org.jetbrains.annotations.NotNull java.io.File r1, @org.jetbrains.annotations.NotNull java.nio.charset.Charset r2, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super kotlin.sequences.Sequence<java.lang.String>, ? extends T> r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.io.FilesKt__FileReadWriteKt.useLines(java.io.File, java.nio.charset.Charset, kotlin.jvm.functions.Function1):T
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
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
        r2 = "charset";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r2);
        r2 = "block";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r10, r2);
        r4 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r2 = new java.io.FileInputStream;
        r2.<init>(r8);
        r2 = (java.io.InputStream) r2;
        r3 = new java.io.InputStreamReader;
        r3.<init>(r2, r9);
        r2 = r3;
        r2 = (java.io.Reader) r2;
        r3 = r2 instanceof java.io.BufferedReader;
        if (r3 == 0) goto L_0x004d;
    L_0x0029:
        r2 = (java.io.BufferedReader) r2;
    L_0x002b:
        r2 = (java.io.Closeable) r2;
        r3 = 0;
        r3 = (java.lang.Throwable) r3;
        r0 = r2;	 Catch:{ Throwable -> 0x0058 }
        r0 = (java.io.BufferedReader) r0;	 Catch:{ Throwable -> 0x0058 }
        r1 = r0;	 Catch:{ Throwable -> 0x0058 }
        r4 = kotlin.io.TextStreamsKt.lineSequence(r1);	 Catch:{ Throwable -> 0x0058 }
        r4 = r10.invoke(r4);	 Catch:{ Throwable -> 0x0058 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r6);
        r5 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r6, r6, r5);
        if (r5 == 0) goto L_0x0054;
    L_0x0046:
        kotlin.io.CloseableKt.closeFinally(r2, r3);
    L_0x0049:
        kotlin.jvm.internal.InlineMarker.finallyEnd(r6);
        return r4;
    L_0x004d:
        r3 = new java.io.BufferedReader;
        r3.<init>(r2, r4);
        r2 = r3;
        goto L_0x002b;
    L_0x0054:
        r2.close();
        goto L_0x0049;
    L_0x0058:
        r3 = move-exception;
        throw r3;	 Catch:{ all -> 0x005a }
    L_0x005a:
        r4 = move-exception;
        r7 = r4;
        r4 = r3;
        r3 = r7;
        kotlin.jvm.internal.InlineMarker.finallyStart(r6);
        r5 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r6, r6, r5);
        if (r5 == 0) goto L_0x006e;
    L_0x0067:
        kotlin.io.CloseableKt.closeFinally(r2, r4);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r6);
        throw r3;
    L_0x006e:
        if (r4 != 0) goto L_0x0074;
    L_0x0070:
        r2.close();
        goto L_0x006a;
        r2.close();	 Catch:{ Throwable -> 0x0079 }
        goto L_0x006a;
    L_0x0079:
        r2 = move-exception;
        goto L_0x006a;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FileReadWriteKt.useLines(java.io.File, java.nio.charset.Charset, kotlin.jvm.functions.Function1):T");
    }

    public static /* bridge */ /* synthetic */ java.lang.Object useLines$default(java.io.File r1, java.nio.charset.Charset r2, kotlin.jvm.functions.Function1 r3, int r4, java.lang.Object r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.io.FilesKt__FileReadWriteKt.useLines$default(java.io.File, java.nio.charset.Charset, kotlin.jvm.functions.Function1, int, java.lang.Object):java.lang.Object
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r5 = 0;
        r6 = 1;
        r2 = r11 & 1;
        if (r2 == 0) goto L_0x0008;
    L_0x0006:
        r9 = kotlin.text.Charsets.UTF_8;
    L_0x0008:
        r2 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r2);
        r2 = "charset";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r2);
        r2 = "block";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r10, r2);
        r4 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r2 = new java.io.FileInputStream;
        r2.<init>(r8);
        r2 = (java.io.InputStream) r2;
        r3 = new java.io.InputStreamReader;
        r3.<init>(r2, r9);
        r2 = r3;
        r2 = (java.io.Reader) r2;
        r3 = r2 instanceof java.io.BufferedReader;
        if (r3 == 0) goto L_0x0053;
    L_0x002f:
        r2 = (java.io.BufferedReader) r2;
    L_0x0031:
        r2 = (java.io.Closeable) r2;
        r3 = 0;
        r3 = (java.lang.Throwable) r3;
        r0 = r2;	 Catch:{ Throwable -> 0x005e }
        r0 = (java.io.BufferedReader) r0;	 Catch:{ Throwable -> 0x005e }
        r1 = r0;	 Catch:{ Throwable -> 0x005e }
        r4 = kotlin.io.TextStreamsKt.lineSequence(r1);	 Catch:{ Throwable -> 0x005e }
        r4 = r10.invoke(r4);	 Catch:{ Throwable -> 0x005e }
        kotlin.jvm.internal.InlineMarker.finallyStart(r6);
        r5 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r6, r6, r5);
        if (r5 == 0) goto L_0x005a;
    L_0x004c:
        kotlin.io.CloseableKt.closeFinally(r2, r3);
    L_0x004f:
        kotlin.jvm.internal.InlineMarker.finallyEnd(r6);
        return r4;
    L_0x0053:
        r3 = new java.io.BufferedReader;
        r3.<init>(r2, r4);
        r2 = r3;
        goto L_0x0031;
    L_0x005a:
        r2.close();
        goto L_0x004f;
    L_0x005e:
        r3 = move-exception;
        throw r3;	 Catch:{ all -> 0x0060 }
    L_0x0060:
        r4 = move-exception;
        r7 = r4;
        r4 = r3;
        r3 = r7;
        kotlin.jvm.internal.InlineMarker.finallyStart(r6);
        r5 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r6, r6, r5);
        if (r5 == 0) goto L_0x0074;
    L_0x006d:
        kotlin.io.CloseableKt.closeFinally(r2, r4);
        kotlin.jvm.internal.InlineMarker.finallyEnd(r6);
        throw r3;
    L_0x0074:
        if (r4 != 0) goto L_0x007a;
    L_0x0076:
        r2.close();
        goto L_0x0070;
        r2.close();	 Catch:{ Throwable -> 0x007f }
        goto L_0x0070;
    L_0x007f:
        r2 = move-exception;
        goto L_0x0070;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FileReadWriteKt.useLines$default(java.io.File, java.nio.charset.Charset, kotlin.jvm.functions.Function1, int, java.lang.Object):java.lang.Object");
    }

    @InlineOnly
    private static final InputStreamReader reader(@NotNull File $receiver, Charset charset) {
        return new InputStreamReader(new FileInputStream($receiver), charset);
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ InputStreamReader reader$default(File $receiver, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return new InputStreamReader(new FileInputStream($receiver), charset);
    }

    @InlineOnly
    private static final BufferedReader bufferedReader(@NotNull File $receiver, Charset charset, int bufferSize) {
        Reader inputStreamReader = new InputStreamReader(new FileInputStream($receiver), charset);
        return inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, bufferSize);
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ BufferedReader bufferedReader$default(File $receiver, Charset charset, int bufferSize, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i & 2) != 0) {
            bufferSize = 8192;
        }
        Reader inputStreamReader = new InputStreamReader(new FileInputStream($receiver), charset);
        return inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, bufferSize);
    }

    @InlineOnly
    private static final OutputStreamWriter writer(@NotNull File $receiver, Charset charset) {
        return new OutputStreamWriter(new FileOutputStream($receiver), charset);
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ OutputStreamWriter writer$default(File $receiver, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return new OutputStreamWriter(new FileOutputStream($receiver), charset);
    }

    @InlineOnly
    private static final BufferedWriter bufferedWriter(@NotNull File $receiver, Charset charset, int bufferSize) {
        Writer outputStreamWriter = new OutputStreamWriter(new FileOutputStream($receiver), charset);
        return outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, bufferSize);
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ BufferedWriter bufferedWriter$default(File $receiver, Charset charset, int bufferSize, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i & 2) != 0) {
            bufferSize = 8192;
        }
        Writer outputStreamWriter = new OutputStreamWriter(new FileOutputStream($receiver), charset);
        return outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, bufferSize);
    }

    @InlineOnly
    private static final PrintWriter printWriter(@NotNull File $receiver, Charset charset) {
        Writer outputStreamWriter = new OutputStreamWriter(new FileOutputStream($receiver), charset);
        return new PrintWriter(outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, 8192));
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ PrintWriter printWriter$default(File $receiver, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Writer outputStreamWriter = new OutputStreamWriter(new FileOutputStream($receiver), charset);
        return new PrintWriter(outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, 8192));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void writeBytes(@org.jetbrains.annotations.NotNull java.io.File r6, @org.jetbrains.annotations.NotNull byte[] r7) {
        /*
        r2 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r2);
        r2 = "array";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r2);
        r2 = new java.io.FileOutputStream;
        r2.<init>(r6);
        r2 = (java.io.Closeable) r2;
        r3 = 0;
        r3 = (java.lang.Throwable) r3;
        r0 = r2;
        r0 = (java.io.FileOutputStream) r0;	 Catch:{ Throwable -> 0x0024 }
        r1 = r0;
        r1.write(r7);	 Catch:{ Throwable -> 0x0024 }
        r4 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0024 }
        kotlin.io.CloseableKt.closeFinally(r2, r3);
        return;
    L_0x0024:
        r3 = move-exception;
        throw r3;	 Catch:{ all -> 0x0026 }
    L_0x0026:
        r4 = move-exception;
        r5 = r4;
        r4 = r3;
        r3 = r5;
        kotlin.io.CloseableKt.closeFinally(r2, r4);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FileReadWriteKt.writeBytes(java.io.File, byte[]):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void appendBytes(@org.jetbrains.annotations.NotNull java.io.File r6, @org.jetbrains.annotations.NotNull byte[] r7) {
        /*
        r2 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r2);
        r2 = "array";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r2);
        r2 = new java.io.FileOutputStream;
        r3 = 1;
        r2.<init>(r6, r3);
        r2 = (java.io.Closeable) r2;
        r3 = 0;
        r3 = (java.lang.Throwable) r3;
        r0 = r2;
        r0 = (java.io.FileOutputStream) r0;	 Catch:{ Throwable -> 0x0025 }
        r1 = r0;
        r1.write(r7);	 Catch:{ Throwable -> 0x0025 }
        r4 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0025 }
        kotlin.io.CloseableKt.closeFinally(r2, r3);
        return;
    L_0x0025:
        r3 = move-exception;
        throw r3;	 Catch:{ all -> 0x0027 }
    L_0x0027:
        r4 = move-exception;
        r5 = r4;
        r4 = r3;
        r3 = r5;
        kotlin.io.CloseableKt.closeFinally(r2, r4);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FileReadWriteKt.appendBytes(java.io.File, byte[]):void");
    }

    @NotNull
    public static final String readText(@NotNull File $receiver, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(charset, HttpRequest.PARAM_CHARSET);
        return new String(readBytes($receiver), charset);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String readText$default(File file, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return readText(file, charset);
    }

    public static final void writeText(@NotNull File $receiver, @NotNull String text, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(charset, HttpRequest.PARAM_CHARSET);
        Object bytes = text.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        writeBytes($receiver, bytes);
    }

    public static /* bridge */ /* synthetic */ void writeText$default(File file, String str, Charset charset, int i, Object obj) {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        writeText(file, str, charset);
    }

    public static final void appendText(@NotNull File $receiver, @NotNull String text, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(charset, HttpRequest.PARAM_CHARSET);
        Object bytes = text.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        appendBytes($receiver, bytes);
    }

    public static /* bridge */ /* synthetic */ void appendText$default(File file, String str, Charset charset, int i, Object obj) {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        appendText(file, str, charset);
    }

    public static final void forEachBlock(@NotNull File $receiver, @NotNull Function2<? super byte[], ? super Integer, Unit> action) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(action, "action");
        forEachBlock($receiver, 4096, action);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void forEachBlock(@org.jetbrains.annotations.NotNull java.io.File r8, int r9, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super byte[], ? super java.lang.Integer, kotlin.Unit> r10) {
        /*
        r4 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r4);
        r4 = "action";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r10, r4);
        r4 = 512; // 0x200 float:7.175E-43 double:2.53E-321;
        r4 = kotlin.ranges.RangesKt___RangesKt.coerceAtLeast(r9, r4);
        r1 = new byte[r4];
        r4 = new java.io.FileInputStream;
        r4.<init>(r8);
        r4 = (java.io.Closeable) r4;
        r5 = 0;
        r5 = (java.lang.Throwable) r5;
        r0 = r4;
        r0 = (java.io.FileInputStream) r0;	 Catch:{ Throwable -> 0x0038 }
        r2 = r0;
    L_0x0023:
        r3 = r2.read(r1);	 Catch:{ Throwable -> 0x0038 }
        if (r3 > 0) goto L_0x0030;
    L_0x002a:
        r6 = kotlin.Unit.INSTANCE;	 Catch:{ Throwable -> 0x0038 }
        kotlin.io.CloseableKt.closeFinally(r4, r5);
        return;
    L_0x0030:
        r6 = java.lang.Integer.valueOf(r3);	 Catch:{ Throwable -> 0x0038 }
        r10.invoke(r1, r6);	 Catch:{ Throwable -> 0x0038 }
        goto L_0x0023;
    L_0x0038:
        r5 = move-exception;
        throw r5;	 Catch:{ all -> 0x003a }
    L_0x003a:
        r6 = move-exception;
        r7 = r6;
        r6 = r5;
        r5 = r7;
        kotlin.io.CloseableKt.closeFinally(r4, r6);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FileReadWriteKt.forEachBlock(java.io.File, int, kotlin.jvm.functions.Function2):void");
    }

    public static /* bridge */ /* synthetic */ void forEachLine$default(File file, Charset charset, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        forEachLine(file, charset, function1);
    }

    public static final void forEachLine(@NotNull File $receiver, @NotNull Charset charset, @NotNull Function1<? super String, Unit> action) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(charset, HttpRequest.PARAM_CHARSET);
        Intrinsics.checkParameterIsNotNull(action, "action");
        TextStreamsKt.forEachLine(new BufferedReader(new InputStreamReader(new FileInputStream($receiver), charset)), action);
    }

    @InlineOnly
    private static final FileInputStream inputStream(@NotNull File $receiver) {
        return new FileInputStream($receiver);
    }

    @InlineOnly
    private static final FileOutputStream outputStream(@NotNull File $receiver) {
        return new FileOutputStream($receiver);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ List readLines$default(File file, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return readLines(file, charset);
    }

    @NotNull
    public static final List<String> readLines(@NotNull File $receiver, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(charset, HttpRequest.PARAM_CHARSET);
        ArrayList result = new ArrayList();
        forEachLine($receiver, charset, new FilesKt__FileReadWriteKt$readLines$1(result));
        return result;
    }
}
