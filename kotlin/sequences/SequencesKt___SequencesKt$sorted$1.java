package kotlin.sequences;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0002¨\u0006\u0005"}, d2 = {"kotlin/sequences/SequencesKt___SequencesKt$sorted$1", "Lkotlin/sequences/Sequence;", "(Lkotlin/sequences/Sequence;)V", "iterator", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 9})
/* compiled from: _Sequences.kt */
public final class SequencesKt___SequencesKt$sorted$1 implements Sequence<T> {
    final /* synthetic */ Sequence receiver$0;

    SequencesKt___SequencesKt$sorted$1(Sequence<? extends T> $receiver) {
        this.receiver$0 = $receiver;
    }

    @NotNull
    public Iterator<T> iterator() {
        List sortedList = SequencesKt___SequencesKt.toMutableList(this.receiver$0);
        CollectionsKt__MutableCollectionsKt.sort(sortedList);
        return sortedList.iterator();
    }
}
