package okhttp3.internal.cache;

import java.io.IOException;
import okio.Sink;

class DiskLruCache$2 extends FaultHidingSink {
    static final /* synthetic */ boolean $assertionsDisabled = (!DiskLruCache.class.desiredAssertionStatus());
    final /* synthetic */ DiskLruCache this$0;

    DiskLruCache$2(DiskLruCache this$0, Sink delegate) {
        this.this$0 = this$0;
        super(delegate);
    }

    protected void onException(IOException e) {
        if ($assertionsDisabled || Thread.holdsLock(this.this$0)) {
            this.this$0.hasJournalErrors = true;
            return;
        }
        throw new AssertionError();
    }
}
