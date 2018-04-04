package kotlin.sequences;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.SequenceBuilder;
import kotlin.coroutines.experimental.jvm.internal.CoroutineImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "T", "R", "Lkotlin/coroutines/experimental/SequenceBuilder;", "invoke", "(Lkotlin/coroutines/experimental/SequenceBuilder;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 9})
/* compiled from: _Sequences.kt */
final class SequencesKt___SequencesKt$zipWithNext$2 extends CoroutineImpl implements Function2<SequenceBuilder<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2 $transform;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    private SequenceBuilder p$;
    final /* synthetic */ Sequence receiver$0;

    SequencesKt___SequencesKt$zipWithNext$2(Sequence sequence, Function2 function2, Continuation continuation) {
        this.receiver$0 = sequence;
        this.$transform = function2;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull SequenceBuilder<? super R> $receiver, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        Continuation sequencesKt___SequencesKt$zipWithNext$2 = new SequencesKt___SequencesKt$zipWithNext$2(this.receiver$0, this.$transform, continuation);
        sequencesKt___SequencesKt$zipWithNext$2.p$ = $receiver;
        return sequencesKt___SequencesKt$zipWithNext$2;
    }

    @Nullable
    public final Object invoke(@NotNull SequenceBuilder<? super R> $receiver, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        return ((SequencesKt___SequencesKt$zipWithNext$2) create((SequenceBuilder) $receiver, (Continuation) continuation)).doResume(Unit.INSTANCE, null);
    }

    @org.jetbrains.annotations.Nullable
    public final java.lang.Object doResume(@org.jetbrains.annotations.Nullable java.lang.Object r8, @org.jetbrains.annotations.Nullable java.lang.Throwable r9) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r7 = this;
        r4 = kotlin.coroutines.experimental.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r3 = r7.label;
        switch(r3) {
            case 0: goto L_0x0012;
            case 1: goto L_0x004d;
            default: goto L_0x0009;
        };
    L_0x0009:
        r3 = new java.lang.IllegalStateException;
        r4 = "call to 'resume' before 'invoke' with coroutine";
        r3.<init>(r4);
        throw r3;
    L_0x0012:
        if (r9 == 0) goto L_0x0015;
    L_0x0014:
        throw r9;
    L_0x0015:
        r3 = r7.p$;
        r5 = r7.receiver$0;
        r1 = r5.iterator();
        r5 = r1.hasNext();
        if (r5 != 0) goto L_0x0026;
    L_0x0023:
        r3 = kotlin.Unit.INSTANCE;
    L_0x0025:
        return r3;
    L_0x0026:
        r0 = r1.next();
    L_0x002a:
        r5 = r1.hasNext();
        if (r5 == 0) goto L_0x005e;
    L_0x0030:
        r2 = r1.next();
        r5 = r7.$transform;
        r5 = r5.invoke(r0, r2);
        r7.L$0 = r3;
        r7.L$1 = r1;
        r7.L$2 = r0;
        r7.L$3 = r2;
        r6 = 1;
        r7.label = r6;
        r5 = r3.yield(r5, r7);
        if (r5 != r4) goto L_0x005c;
    L_0x004b:
        r3 = r4;
        goto L_0x0025;
    L_0x004d:
        r2 = r7.L$3;
        r0 = r7.L$2;
        r1 = r7.L$1;
        r1 = (java.util.Iterator) r1;
        r3 = r7.L$0;
        r3 = (kotlin.coroutines.experimental.SequenceBuilder) r3;
        if (r9 == 0) goto L_0x005c;
    L_0x005b:
        throw r9;
    L_0x005c:
        r0 = r2;
        goto L_0x002a;
    L_0x005e:
        r3 = kotlin.Unit.INSTANCE;
        goto L_0x0025;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.sequences.SequencesKt___SequencesKt$zipWithNext$2.doResume(java.lang.Object, java.lang.Throwable):java.lang.Object");
    }
}
