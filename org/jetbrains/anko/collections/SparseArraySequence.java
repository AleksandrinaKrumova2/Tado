package org.jetbrains.anko.collections;

import android.util.SparseArray;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@PublishedApi
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0002\b\u0001\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\bB\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u000f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lorg/jetbrains/anko/collections/SparseArraySequence;", "T", "Lkotlin/sequences/Sequence;", "a", "Landroid/util/SparseArray;", "(Landroid/util/SparseArray;)V", "iterator", "", "SparseArrayIterator", "commons_release"}, k = 1, mv = {1, 1, 5})
/* compiled from: Arrays.kt */
public final class SparseArraySequence<T> implements Sequence<T> {
    private final SparseArray<T> f421a;

    @Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\t\u0010\u0006\u001a\u00020\u0007H\u0002J\u000e\u0010\b\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/jetbrains/anko/collections/SparseArraySequence$SparseArrayIterator;", "", "(Lorg/jetbrains/anko/collections/SparseArraySequence;)V", "index", "", "size", "hasNext", "", "next", "()Ljava/lang/Object;", "commons_release"}, k = 1, mv = {1, 1, 5})
    /* compiled from: Arrays.kt */
    private final class SparseArrayIterator implements Iterator<T>, KMappedMarker {
        private int index;
        private final int size;

        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public SparseArrayIterator() {
            this.size = SparseArraySequence.this.f421a.size();
        }

        public boolean hasNext() {
            return this.size > this.index;
        }

        public T next() {
            if (SparseArraySequence.this.f421a.size() != this.size) {
                throw new ConcurrentModificationException();
            }
            SparseArray access$getA$p = SparseArraySequence.this.f421a;
            int i = this.index;
            this.index = i + 1;
            return access$getA$p.valueAt(i);
        }
    }

    public SparseArraySequence(@NotNull SparseArray<T> a) {
        Intrinsics.checkParameterIsNotNull(a, "a");
        this.f421a = a;
    }

    @NotNull
    public Iterator<T> iterator() {
        return new SparseArrayIterator();
    }
}
