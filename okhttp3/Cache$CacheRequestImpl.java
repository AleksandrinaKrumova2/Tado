package okhttp3;

import java.io.IOException;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheRequest;
import okhttp3.internal.cache.DiskLruCache$Editor;
import okio.ForwardingSink;
import okio.Sink;

final class Cache$CacheRequestImpl implements CacheRequest {
    private Sink body;
    private Sink cacheOut;
    boolean done;
    private final DiskLruCache$Editor editor;
    final /* synthetic */ Cache this$0;

    Cache$CacheRequestImpl(final Cache cache, final DiskLruCache$Editor editor) {
        this.this$0 = cache;
        this.editor = editor;
        this.cacheOut = editor.newSink(1);
        this.body = new ForwardingSink(this.cacheOut) {
            public void close() throws IOException {
                synchronized (Cache$CacheRequestImpl.this.this$0) {
                    if (Cache$CacheRequestImpl.this.done) {
                        return;
                    }
                    Cache$CacheRequestImpl.this.done = true;
                    Cache cache = Cache$CacheRequestImpl.this.this$0;
                    cache.writeSuccessCount++;
                    super.close();
                    editor.commit();
                }
            }
        };
    }

    public void abort() {
        synchronized (this.this$0) {
            if (this.done) {
                return;
            }
            this.done = true;
            Cache cache = this.this$0;
            cache.writeAbortCount++;
            Util.closeQuietly(this.cacheOut);
            try {
                this.editor.abort();
            } catch (IOException e) {
            }
        }
    }

    public Sink body() {
        return this.body;
    }
}
