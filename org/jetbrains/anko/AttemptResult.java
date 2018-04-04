package org.jetbrains.anko;

import kotlin.Metadata;
import kotlin.PublishedApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 1}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B\u001b\b\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00018\u0000\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0011\u001a\u0004\u0018\u00018\u0000HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J,\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00018\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\n2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J)\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u0000\"\u0004\b\u0001\u0010\u001a2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\u001a0\u001cH\bJ\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\n8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\n8Æ\u0002¢\u0006\u0006\u001a\u0004\b\r\u0010\fR\u0015\u0010\u0003\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/anko/AttemptResult;", "T", "", "value", "error", "", "(Ljava/lang/Object;Ljava/lang/Throwable;)V", "getError", "()Ljava/lang/Throwable;", "hasValue", "", "getHasValue", "()Z", "isError", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "component2", "copy", "(Ljava/lang/Object;Ljava/lang/Throwable;)Lorg/jetbrains/anko/AttemptResult;", "equals", "other", "hashCode", "", "then", "R", "f", "Lkotlin/Function1;", "toString", "", "commons_release"}, k = 1, mv = {1, 1, 5})
/* compiled from: Helpers.kt */
public final class AttemptResult<T> {
    @Nullable
    private final Throwable error;
    @Nullable
    private final T value;

    @NotNull
    public static /* bridge */ /* synthetic */ AttemptResult copy$default(AttemptResult attemptResult, Object obj, Throwable th, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = attemptResult.value;
        }
        if ((i & 2) != 0) {
            th = attemptResult.error;
        }
        return attemptResult.copy(obj, th);
    }

    @Nullable
    public final T component1() {
        return this.value;
    }

    @Nullable
    public final Throwable component2() {
        return this.error;
    }

    @NotNull
    public final AttemptResult<T> copy(@Nullable T value, @Nullable Throwable error) {
        return new AttemptResult(value, error);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r3) {
        /*
        r2 = this;
        if (r2 == r3) goto L_0x001c;
    L_0x0002:
        r0 = r3 instanceof org.jetbrains.anko.AttemptResult;
        if (r0 == 0) goto L_0x001e;
    L_0x0006:
        r3 = (org.jetbrains.anko.AttemptResult) r3;
        r0 = r2.value;
        r1 = r3.value;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x001e;
    L_0x0012:
        r0 = r2.error;
        r1 = r3.error;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x001e;
    L_0x001c:
        r0 = 1;
    L_0x001d:
        return r0;
    L_0x001e:
        r0 = 0;
        goto L_0x001d;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.AttemptResult.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i = 0;
        Object obj = this.value;
        int hashCode = (obj != null ? obj.hashCode() : 0) * 31;
        Throwable th = this.error;
        if (th != null) {
            i = th.hashCode();
        }
        return hashCode + i;
    }

    @org.jetbrains.annotations.NotNull
    public final <R> org.jetbrains.anko.AttemptResult<R> then(@org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super T, ? extends R> r1) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: org.jetbrains.anko.AttemptResult.then(kotlin.jvm.functions.Function1):org.jetbrains.anko.AttemptResult<R>
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r0 = this;
        r4 = "f";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r4);
        r2 = r6;
        r4 = r2.getError();
        if (r4 == 0) goto L_0x001b;
    L_0x000d:
        r4 = 1;
        if (r4 == 0) goto L_0x0020;
    L_0x0010:
        if (r6 != 0) goto L_0x001d;
        r4 = new kotlin.TypeCastException;
        r5 = "null cannot be cast to non-null type org.jetbrains.anko.AttemptResult<R>";
        r4.<init>(r5);
        throw r4;
    L_0x001b:
        r4 = 0;
        goto L_0x000e;
        r6 = (org.jetbrains.anko.AttemptResult) r6;
        return r6;
        r3 = 0;
        r0 = 0;
        r0 = (java.lang.Throwable) r0;
        r4 = r6.getValue();	 Catch:{ Throwable -> 0x0037 }
        r4 = (java.lang.Object) r4;	 Catch:{ Throwable -> 0x0037 }
        r3 = r7.invoke(r4);	 Catch:{ Throwable -> 0x0037 }
        r6 = new org.jetbrains.anko.AttemptResult;
        r6.<init>(r3, r0);
        goto L_0x001f;
    L_0x0037:
        r1 = move-exception;
        r0 = r1;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.AttemptResult.then(kotlin.jvm.functions.Function1):org.jetbrains.anko.AttemptResult<R>");
    }

    public String toString() {
        return "AttemptResult(value=" + this.value + ", error=" + this.error + ")";
    }

    @PublishedApi
    public AttemptResult(@Nullable T value, @Nullable Throwable error) {
        this.value = value;
        this.error = error;
    }

    @Nullable
    public final Throwable getError() {
        return this.error;
    }

    @Nullable
    public final T getValue() {
        return this.value;
    }

    public final boolean isError() {
        return getError() != null;
    }

    public final boolean getHasValue() {
        return getError() == null;
    }
}
