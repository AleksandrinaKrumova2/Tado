package kotlin.text;

import kotlin.Metadata;
import kotlin.internal.InlineOnly;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\f\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0001\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0002\u001a\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0007H\n¨\u0006\b"}, d2 = {"equals", "", "", "other", "ignoreCase", "isSurrogate", "plus", "", "kotlin-stdlib"}, k = 5, mv = {1, 1, 9}, xi = 1, xs = "kotlin/text/CharsKt")
/* compiled from: Char.kt */
class CharsKt__CharKt extends CharsKt__CharJVMKt {
    @InlineOnly
    private static final String plus(char $receiver, String other) {
        return String.valueOf($receiver) + other;
    }

    public static /* bridge */ /* synthetic */ boolean equals$default(char c, char c2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return equals(c, c2, z);
    }

    public static final boolean equals(char $receiver, char other, boolean ignoreCase) {
        if ($receiver == other) {
            return true;
        }
        if (!ignoreCase) {
            return false;
        }
        if (Character.toUpperCase($receiver) == Character.toUpperCase(other) || Character.toLowerCase($receiver) == Character.toLowerCase(other)) {
            return true;
        }
        return false;
    }

    public static final boolean isSurrogate(char $receiver) {
        return '?' <= $receiver && '?' >= $receiver;
    }
}
