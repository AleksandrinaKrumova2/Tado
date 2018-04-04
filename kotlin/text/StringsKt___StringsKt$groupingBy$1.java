package kotlin.text;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.Grouping;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0005\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00028\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0015\u0010\u0004\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\bH\u0016¨\u0006\t"}, d2 = {"kotlin/text/StringsKt___StringsKt$groupingBy$1", "Lkotlin/collections/Grouping;", "", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)V", "keyOf", "element", "(C)Ljava/lang/Object;", "sourceIterator", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 9})
/* compiled from: _Strings.kt */
public final class StringsKt___StringsKt$groupingBy$1 implements Grouping<Character, K> {
    final /* synthetic */ Function1 $keySelector;
    final /* synthetic */ CharSequence receiver$0;

    public StringsKt___StringsKt$groupingBy$1(CharSequence $receiver, Function1 $captured_local_variable$1) {
        this.receiver$0 = $receiver;
        this.$keySelector = $captured_local_variable$1;
    }

    @NotNull
    public Iterator<Character> sourceIterator() {
        return StringsKt__StringsKt.iterator(this.receiver$0);
    }

    public K keyOf(char element) {
        return this.$keySelector.invoke(Character.valueOf(element));
    }
}
