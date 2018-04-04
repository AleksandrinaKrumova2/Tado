package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;

public abstract class ResponseBody implements Closeable {
    private Reader reader;

    class C13131 extends ResponseBody {
        final /* synthetic */ BufferedSource val$content;
        final /* synthetic */ long val$contentLength;
        final /* synthetic */ MediaType val$contentType;

        C13131(MediaType mediaType, long j, BufferedSource bufferedSource) {
            this.val$contentType = mediaType;
            this.val$contentLength = j;
            this.val$content = bufferedSource;
        }

        @Nullable
        public MediaType contentType() {
            return this.val$contentType;
        }

        public long contentLength() {
            return this.val$contentLength;
        }

        public BufferedSource source() {
            return this.val$content;
        }
    }

    public abstract long contentLength();

    @Nullable
    public abstract MediaType contentType();

    public abstract BufferedSource source();

    public final InputStream byteStream() {
        return source().inputStream();
    }

    public final byte[] bytes() throws IOException {
        long contentLength = contentLength();
        if (contentLength > 2147483647L) {
            throw new IOException("Cannot buffer entire body for content length: " + contentLength);
        }
        Closeable source = source();
        try {
            byte[] bytes = source.readByteArray();
            if (contentLength == -1 || contentLength == ((long) bytes.length)) {
                return bytes;
            }
            throw new IOException("Content-Length (" + contentLength + ") and stream length (" + bytes.length + ") disagree");
        } finally {
            Util.closeQuietly(source);
        }
    }

    public final Reader charStream() {
        Reader r = this.reader;
        if (r != null) {
            return r;
        }
        r = new BomAwareReader(source(), charset());
        this.reader = r;
        return r;
    }

    public final String string() throws IOException {
        Closeable source = source();
        try {
            String readString = source.readString(Util.bomAwareCharset(source, charset()));
            return readString;
        } finally {
            Util.closeQuietly(source);
        }
    }

    private Charset charset() {
        MediaType contentType = contentType();
        return contentType != null ? contentType.charset(Util.UTF_8) : Util.UTF_8;
    }

    public void close() {
        Util.closeQuietly(source());
    }

    public static ResponseBody create(@Nullable MediaType contentType, String content) {
        Charset charset = Util.UTF_8;
        if (contentType != null) {
            charset = contentType.charset();
            if (charset == null) {
                charset = Util.UTF_8;
                contentType = MediaType.parse(contentType + "; charset=utf-8");
            }
        }
        Buffer buffer = new Buffer().writeString(content, charset);
        return create(contentType, buffer.size(), buffer);
    }

    public static ResponseBody create(@Nullable MediaType contentType, byte[] content) {
        return create(contentType, (long) content.length, new Buffer().write(content));
    }

    public static ResponseBody create(@Nullable MediaType contentType, long contentLength, BufferedSource content) {
        if (content != null) {
            return new C13131(contentType, contentLength, content);
        }
        throw new NullPointerException("source == null");
    }
}
