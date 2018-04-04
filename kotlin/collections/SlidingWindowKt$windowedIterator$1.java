package kotlin.collections;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.SequenceBuilder;
import kotlin.coroutines.experimental.jvm.internal.CoroutineImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "T", "Lkotlin/coroutines/experimental/SequenceBuilder;", "", "invoke", "(Lkotlin/coroutines/experimental/SequenceBuilder;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 9})
/* compiled from: SlidingWindow.kt */
final class SlidingWindowKt$windowedIterator$1 extends CoroutineImpl implements Function2<SequenceBuilder<? super List<? extends T>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Iterator $iterator;
    final /* synthetic */ boolean $partialWindows;
    final /* synthetic */ boolean $reuseBuffer;
    final /* synthetic */ int $size;
    final /* synthetic */ int $step;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    private SequenceBuilder p$;

    SlidingWindowKt$windowedIterator$1(int i, int i2, Iterator it, boolean z, boolean z2, Continuation continuation) {
        this.$step = i;
        this.$size = i2;
        this.$iterator = it;
        this.$reuseBuffer = z;
        this.$partialWindows = z2;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull SequenceBuilder<? super List<? extends T>> $receiver, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        Continuation slidingWindowKt$windowedIterator$1 = new SlidingWindowKt$windowedIterator$1(this.$step, this.$size, this.$iterator, this.$reuseBuffer, this.$partialWindows, continuation);
        slidingWindowKt$windowedIterator$1.p$ = $receiver;
        return slidingWindowKt$windowedIterator$1;
    }

    @Nullable
    public final Object invoke(@NotNull SequenceBuilder<? super List<? extends T>> $receiver, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        return ((SlidingWindowKt$windowedIterator$1) create((SequenceBuilder) $receiver, (Continuation) continuation)).doResume(Unit.INSTANCE, null);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object doResume(@org.jetbrains.annotations.Nullable java.lang.Object r12, @org.jetbrains.annotations.Nullable java.lang.Throwable r13) {
        /*
        r11 = this;
        r10 = 0;
        r9 = 1;
        r8 = kotlin.coroutines.experimental.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r4 = r11.label;
        switch(r4) {
            case 0: goto L_0x0014;
            case 1: goto L_0x005b;
            case 2: goto L_0x00ab;
            case 3: goto L_0x00fc;
            case 4: goto L_0x014b;
            case 5: goto L_0x017e;
            default: goto L_0x000b;
        };
    L_0x000b:
        r4 = new java.lang.IllegalStateException;
        r5 = "call to 'resume' before 'invoke' with coroutine";
        r4.<init>(r5);
        throw r4;
    L_0x0014:
        if (r13 == 0) goto L_0x0017;
    L_0x0016:
        throw r13;
    L_0x0017:
        r5 = r11.p$;
        r4 = r11.$step;
        r6 = r11.$size;
        r2 = r4 - r6;
        if (r2 < 0) goto L_0x00b6;
    L_0x0021:
        r0 = new java.util.ArrayList;
        r4 = r11.$size;
        r0.<init>(r4);
        r3 = 0;
        r4 = r11.$iterator;
    L_0x002b:
        r6 = r4.hasNext();
        if (r6 == 0) goto L_0x0081;
    L_0x0031:
        r1 = r4.next();
        if (r3 <= 0) goto L_0x003a;
    L_0x0037:
        r3 = r3 + -1;
        goto L_0x002b;
    L_0x003a:
        r0.add(r1);
        r6 = r0.size();
        r7 = r11.$size;
        if (r6 != r7) goto L_0x002b;
    L_0x0045:
        r11.L$0 = r5;
        r11.I$0 = r2;
        r11.L$1 = r0;
        r11.I$1 = r3;
        r11.L$2 = r1;
        r11.L$3 = r4;
        r11.label = r9;
        r6 = r5.yield(r0, r11);
        if (r6 != r8) goto L_0x0070;
    L_0x0059:
        r4 = r8;
    L_0x005a:
        return r4;
    L_0x005b:
        r4 = r11.L$3;
        r4 = (java.util.Iterator) r4;
        r1 = r11.L$2;
        r3 = r11.I$1;
        r0 = r11.L$1;
        r0 = (java.util.ArrayList) r0;
        r2 = r11.I$0;
        r5 = r11.L$0;
        r5 = (kotlin.coroutines.experimental.SequenceBuilder) r5;
        if (r13 == 0) goto L_0x0070;
    L_0x006f:
        throw r13;
    L_0x0070:
        r6 = r11.$reuseBuffer;
        if (r6 == 0) goto L_0x0079;
    L_0x0074:
        r0.clear();
    L_0x0077:
        r3 = r2;
        goto L_0x002b;
    L_0x0079:
        r0 = new java.util.ArrayList;
        r6 = r11.$size;
        r0.<init>(r6);
        goto L_0x0077;
    L_0x0081:
        r4 = r0;
        r4 = (java.util.Collection) r4;
        r4 = r4.isEmpty();
        if (r4 != 0) goto L_0x00a9;
    L_0x008a:
        if (r9 == 0) goto L_0x0187;
    L_0x008c:
        r4 = r11.$partialWindows;
        if (r4 != 0) goto L_0x0098;
    L_0x0090:
        r4 = r0.size();
        r6 = r11.$size;
        if (r4 != r6) goto L_0x0187;
    L_0x0098:
        r11.I$0 = r2;
        r11.L$0 = r0;
        r11.I$1 = r3;
        r4 = 2;
        r11.label = r4;
        r4 = r5.yield(r0, r11);
        if (r4 != r8) goto L_0x0187;
    L_0x00a7:
        r4 = r8;
        goto L_0x005a;
    L_0x00a9:
        r9 = r10;
        goto L_0x008a;
    L_0x00ab:
        r3 = r11.I$1;
        r0 = r11.L$0;
        r0 = (java.util.ArrayList) r0;
        r2 = r11.I$0;
        if (r13 == 0) goto L_0x0187;
    L_0x00b5:
        throw r13;
    L_0x00b6:
        r0 = new kotlin.collections.RingBuffer;
        r4 = r11.$size;
        r0.<init>(r4);
        r6 = r11.$iterator;
        r7 = r5;
    L_0x00c0:
        r4 = r6.hasNext();
        if (r4 == 0) goto L_0x0119;
    L_0x00c6:
        r1 = r6.next();
        r0.add(r1);
        r4 = r0.isFull();
        if (r4 == 0) goto L_0x018b;
    L_0x00d3:
        r4 = r11.$reuseBuffer;
        if (r4 == 0) goto L_0x00f0;
    L_0x00d7:
        r4 = r0;
        r4 = (java.util.List) r4;
    L_0x00da:
        r11.L$0 = r7;
        r11.I$0 = r2;
        r11.L$1 = r0;
        r11.L$2 = r1;
        r11.L$3 = r6;
        r5 = 3;
        r11.label = r5;
        r4 = r7.yield(r4, r11);
        if (r4 != r8) goto L_0x010f;
    L_0x00ed:
        r4 = r8;
        goto L_0x005a;
    L_0x00f0:
        r5 = new java.util.ArrayList;
        r4 = r0;
        r4 = (java.util.Collection) r4;
        r5.<init>(r4);
        r4 = r5;
        r4 = (java.util.List) r4;
        goto L_0x00da;
    L_0x00fc:
        r4 = r11.L$3;
        r4 = (java.util.Iterator) r4;
        r1 = r11.L$2;
        r0 = r11.L$1;
        r0 = (kotlin.collections.RingBuffer) r0;
        r2 = r11.I$0;
        r5 = r11.L$0;
        r5 = (kotlin.coroutines.experimental.SequenceBuilder) r5;
        if (r13 == 0) goto L_0x0111;
    L_0x010e:
        throw r13;
    L_0x010f:
        r4 = r6;
        r5 = r7;
    L_0x0111:
        r6 = r11.$step;
        r0.removeFirst(r6);
    L_0x0116:
        r6 = r4;
        r7 = r5;
        goto L_0x00c0;
    L_0x0119:
        r4 = r11.$partialWindows;
        if (r4 == 0) goto L_0x0187;
    L_0x011d:
        r6 = r7;
    L_0x011e:
        r4 = r0.size();
        r5 = r11.$step;
        if (r4 <= r5) goto L_0x0160;
    L_0x0126:
        r4 = r11.$reuseBuffer;
        if (r4 == 0) goto L_0x013f;
    L_0x012a:
        r4 = r0;
        r4 = (java.util.List) r4;
    L_0x012d:
        r11.L$0 = r6;
        r11.I$0 = r2;
        r11.L$1 = r0;
        r5 = 4;
        r11.label = r5;
        r4 = r6.yield(r4, r11);
        if (r4 != r8) goto L_0x0158;
    L_0x013c:
        r4 = r8;
        goto L_0x005a;
    L_0x013f:
        r5 = new java.util.ArrayList;
        r4 = r0;
        r4 = (java.util.Collection) r4;
        r5.<init>(r4);
        r4 = r5;
        r4 = (java.util.List) r4;
        goto L_0x012d;
    L_0x014b:
        r0 = r11.L$1;
        r0 = (kotlin.collections.RingBuffer) r0;
        r2 = r11.I$0;
        r4 = r11.L$0;
        r4 = (kotlin.coroutines.experimental.SequenceBuilder) r4;
        if (r13 == 0) goto L_0x0159;
    L_0x0157:
        throw r13;
    L_0x0158:
        r4 = r6;
    L_0x0159:
        r5 = r11.$step;
        r0.removeFirst(r5);
        r6 = r4;
        goto L_0x011e;
    L_0x0160:
        r4 = r0;
        r4 = (java.util.Collection) r4;
        r4 = r4.isEmpty();
        if (r4 != 0) goto L_0x017c;
    L_0x0169:
        r4 = r9;
    L_0x016a:
        if (r4 == 0) goto L_0x0187;
    L_0x016c:
        r11.I$0 = r2;
        r11.L$0 = r0;
        r4 = 5;
        r11.label = r4;
        r4 = r6.yield(r0, r11);
        if (r4 != r8) goto L_0x0187;
    L_0x0179:
        r4 = r8;
        goto L_0x005a;
    L_0x017c:
        r4 = r10;
        goto L_0x016a;
    L_0x017e:
        r0 = r11.L$0;
        r0 = (kotlin.collections.RingBuffer) r0;
        r2 = r11.I$0;
        if (r13 == 0) goto L_0x0187;
    L_0x0186:
        throw r13;
    L_0x0187:
        r4 = kotlin.Unit.INSTANCE;
        goto L_0x005a;
    L_0x018b:
        r4 = r6;
        r5 = r7;
        goto L_0x0116;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.SlidingWindowKt$windowedIterator$1.doResume(java.lang.Object, java.lang.Throwable):java.lang.Object");
    }
}
