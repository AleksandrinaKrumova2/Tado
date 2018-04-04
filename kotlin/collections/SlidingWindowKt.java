package kotlin.collections;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0000\u001aH\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00070\u0006\"\u0004\b\u0000\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\u00062\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0000\u001aD\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00070\u000e\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0000¨\u0006\u000f"}, d2 = {"checkWindowSizeStep", "", "size", "", "step", "windowedIterator", "", "", "T", "iterator", "partialWindows", "", "reuseBuffer", "windowedSequence", "Lkotlin/sequences/Sequence;", "kotlin-stdlib"}, k = 2, mv = {1, 1, 9})
/* compiled from: SlidingWindow.kt */
public final class SlidingWindowKt {
    public static final void checkWindowSizeStep(int r1, int r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.collections.SlidingWindowKt.checkWindowSizeStep(int, int):void
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
        if (r2 <= 0) goto L_0x003d;
    L_0x0002:
        if (r3 <= 0) goto L_0x003d;
    L_0x0004:
        r0 = 1;
    L_0x0005:
        if (r0 != 0) goto L_0x005c;
    L_0x0007:
        if (r2 == r3) goto L_0x003f;
    L_0x0009:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Both size ";
        r0 = r0.append(r1);
        r0 = r0.append(r2);
        r1 = " and step ";
        r0 = r0.append(r1);
        r0 = r0.append(r3);
        r1 = " must be greater than zero.";
        r0 = r0.append(r1);
        r0 = r0.toString();
        r1 = r0;
        r0 = new java.lang.IllegalArgumentException;
        r1 = r1.toString();
        r0.<init>(r1);
        r0 = (java.lang.Throwable) r0;
        throw r0;
    L_0x003d:
        r0 = 0;
        goto L_0x0005;
    L_0x003f:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "size ";
        r0 = r0.append(r1);
        r0 = r0.append(r2);
        r1 = " must be greater than zero.";
        r0 = r0.append(r1);
        r0 = r0.toString();
        r1 = r0;
        goto L_0x0030;
    L_0x005c:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.SlidingWindowKt.checkWindowSizeStep(int, int):void");
    }

    @NotNull
    public static final <T> Sequence<List<T>> windowedSequence(@NotNull Sequence<? extends T> $receiver, int size, int step, boolean partialWindows, boolean reuseBuffer) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        checkWindowSizeStep(size, step);
        return new SlidingWindowKt$windowedSequence$$inlined$Sequence$1($receiver, size, step, partialWindows, reuseBuffer);
    }

    @NotNull
    public static final <T> Iterator<List<T>> windowedIterator(@NotNull Iterator<? extends T> iterator, int size, int step, boolean partialWindows, boolean reuseBuffer) {
        Intrinsics.checkParameterIsNotNull(iterator, "iterator");
        if (iterator.hasNext()) {
            return SequenceBuilderKt__SequenceBuilderKt.buildIterator(new SlidingWindowKt$windowedIterator$1(step, size, iterator, reuseBuffer, partialWindows, null));
        }
        return EmptyIterator.INSTANCE;
    }
}
