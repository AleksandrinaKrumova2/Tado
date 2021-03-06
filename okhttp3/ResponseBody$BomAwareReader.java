package okhttp3;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import okhttp3.internal.Util;
import okio.BufferedSource;

final class ResponseBody$BomAwareReader extends Reader {
    private final Charset charset;
    private boolean closed;
    private Reader delegate;
    private final BufferedSource source;

    ResponseBody$BomAwareReader(BufferedSource source, Charset charset) {
        this.source = source;
        this.charset = charset;
    }

    public int read(char[] cbuf, int off, int len) throws IOException {
        if (this.closed) {
            throw new IOException("Stream closed");
        }
        Reader delegate = this.delegate;
        if (delegate == null) {
            delegate = new InputStreamReader(this.source.inputStream(), Util.bomAwareCharset(this.source, this.charset));
            this.delegate = delegate;
        }
        return delegate.read(cbuf, off, len);
    }

    public void close() throws IOException {
        this.closed = true;
        if (this.delegate != null) {
            this.delegate.close();
        } else {
            this.source.close();
        }
    }
}
