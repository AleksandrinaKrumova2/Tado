package okio;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.io.InputStream;

class RealBufferedSource$1 extends InputStream {
    final /* synthetic */ RealBufferedSource this$0;

    RealBufferedSource$1(RealBufferedSource this$0) {
        this.this$0 = this$0;
    }

    public int read() throws IOException {
        if (this.this$0.closed) {
            throw new IOException("closed");
        } else if (this.this$0.buffer.size == 0 && this.this$0.source.read(this.this$0.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1;
        } else {
            return this.this$0.buffer.readByte() & 255;
        }
    }

    public int read(byte[] data, int offset, int byteCount) throws IOException {
        if (this.this$0.closed) {
            throw new IOException("closed");
        }
        Util.checkOffsetAndCount((long) data.length, (long) offset, (long) byteCount);
        if (this.this$0.buffer.size == 0 && this.this$0.source.read(this.this$0.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1;
        }
        return this.this$0.buffer.read(data, offset, byteCount);
    }

    public int available() throws IOException {
        if (!this.this$0.closed) {
            return (int) Math.min(this.this$0.buffer.size, 2147483647L);
        }
        throw new IOException("closed");
    }

    public void close() throws IOException {
        this.this$0.close();
    }

    public String toString() {
        return this.this$0 + ".inputStream()";
    }
}
