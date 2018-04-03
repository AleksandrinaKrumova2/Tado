package fi.iki.elonen;

import android.support.v4.media.session.PlaybackStateCompat;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.zip.GZIPOutputStream;

public class NanoHTTPD$Response implements Closeable {
    private boolean chunkedTransfer;
    private long contentLength;
    private InputStream data;
    private boolean encodeAsGzip;
    private final Map<String, String> header = new C13291();
    private boolean keepAlive;
    private final Map<String, String> lowerCaseHeader = new HashMap();
    private String mimeType;
    private NanoHTTPD$Method requestMethod;
    private IStatus status;

    class C13291 extends HashMap<String, String> {
        C13291() {
        }

        public String put(String key, String value) {
            NanoHTTPD$Response.this.lowerCaseHeader.put(key == null ? key : key.toLowerCase(), value);
            return (String) super.put(key, value);
        }
    }

    private static class ChunkedOutputStream extends FilterOutputStream {
        public ChunkedOutputStream(OutputStream out) {
            super(out);
        }

        public void write(int b) throws IOException {
            write(new byte[]{(byte) b}, 0, 1);
        }

        public void write(byte[] b) throws IOException {
            write(b, 0, b.length);
        }

        public void write(byte[] b, int off, int len) throws IOException {
            if (len != 0) {
                this.out.write(String.format("%x\r\n", new Object[]{Integer.valueOf(len)}).getBytes());
                this.out.write(b, off, len);
                this.out.write("\r\n".getBytes());
            }
        }

        public void finish() throws IOException {
            this.out.write("0\r\n\r\n".getBytes());
        }
    }

    public interface IStatus {
        String getDescription();

        int getRequestStatus();
    }

    protected NanoHTTPD$Response(IStatus status, String mimeType, InputStream data, long totalBytes) {
        boolean z = false;
        this.status = status;
        this.mimeType = mimeType;
        if (data == null) {
            this.data = new ByteArrayInputStream(new byte[0]);
            this.contentLength = 0;
        } else {
            this.data = data;
            this.contentLength = totalBytes;
        }
        if (this.contentLength < 0) {
            z = true;
        }
        this.chunkedTransfer = z;
        this.keepAlive = true;
    }

    public void close() throws IOException {
        if (this.data != null) {
            this.data.close();
        }
    }

    public void addHeader(String name, String value) {
        this.header.put(name, value);
    }

    public void closeConnection(boolean close) {
        if (close) {
            this.header.put("connection", "close");
        } else {
            this.header.remove("connection");
        }
    }

    public boolean isCloseConnection() {
        return "close".equals(getHeader("connection"));
    }

    public InputStream getData() {
        return this.data;
    }

    public String getHeader(String name) {
        return (String) this.lowerCaseHeader.get(name.toLowerCase());
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public NanoHTTPD$Method getRequestMethod() {
        return this.requestMethod;
    }

    public IStatus getStatus() {
        return this.status;
    }

    public void setGzipEncoding(boolean encodeAsGzip) {
        this.encodeAsGzip = encodeAsGzip;
    }

    public void setKeepAlive(boolean useKeepAlive) {
        this.keepAlive = useKeepAlive;
    }

    protected void send(OutputStream outputStream) {
        SimpleDateFormat gmtFrmt = new SimpleDateFormat("E, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        gmtFrmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            if (this.status == null) {
                throw new Error("sendResponse(): Status can't be null.");
            }
            PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream, new NanoHTTPD$ContentType(this.mimeType).getEncoding())), false);
            pw.append("HTTP/1.1 ").append(this.status.getDescription()).append(" \r\n");
            if (this.mimeType != null) {
                printHeader(pw, HttpRequest.HEADER_CONTENT_TYPE, this.mimeType);
            }
            if (getHeader("date") == null) {
                printHeader(pw, HttpRequest.HEADER_DATE, gmtFrmt.format(new Date()));
            }
            for (Entry<String, String> entry : this.header.entrySet()) {
                printHeader(pw, (String) entry.getKey(), (String) entry.getValue());
            }
            if (getHeader("connection") == null) {
                printHeader(pw, "Connection", this.keepAlive ? "keep-alive" : "close");
            }
            if (getHeader("content-length") != null) {
                this.encodeAsGzip = false;
            }
            if (this.encodeAsGzip) {
                printHeader(pw, HttpRequest.HEADER_CONTENT_ENCODING, HttpRequest.ENCODING_GZIP);
                setChunkedTransfer(true);
            }
            long pending = this.data != null ? this.contentLength : 0;
            if (this.requestMethod != NanoHTTPD$Method.HEAD && this.chunkedTransfer) {
                printHeader(pw, "Transfer-Encoding", "chunked");
            } else if (!this.encodeAsGzip) {
                pending = sendContentLengthHeaderIfNotAlreadyPresent(pw, pending);
            }
            pw.append("\r\n");
            pw.flush();
            sendBodyWithCorrectTransferAndEncoding(outputStream, pending);
            outputStream.flush();
            NanoHTTPD.access$000(this.data);
        } catch (IOException ioe) {
            NanoHTTPD.access$200().log(Level.SEVERE, "Could not send response to the client", ioe);
        }
    }

    protected void printHeader(PrintWriter pw, String key, String value) {
        pw.append(key).append(": ").append(value).append("\r\n");
    }

    protected long sendContentLengthHeaderIfNotAlreadyPresent(PrintWriter pw, long defaultSize) {
        String contentLengthString = getHeader("content-length");
        long size = defaultSize;
        if (contentLengthString != null) {
            try {
                size = Long.parseLong(contentLengthString);
            } catch (NumberFormatException e) {
                NanoHTTPD.access$200().severe("content-length was no number " + contentLengthString);
            }
        }
        pw.print("Content-Length: " + size + "\r\n");
        return size;
    }

    private void sendBodyWithCorrectTransferAndEncoding(OutputStream outputStream, long pending) throws IOException {
        if (this.requestMethod == NanoHTTPD$Method.HEAD || !this.chunkedTransfer) {
            sendBodyWithCorrectEncoding(outputStream, pending);
            return;
        }
        ChunkedOutputStream chunkedOutputStream = new ChunkedOutputStream(outputStream);
        sendBodyWithCorrectEncoding(chunkedOutputStream, -1);
        chunkedOutputStream.finish();
    }

    private void sendBodyWithCorrectEncoding(OutputStream outputStream, long pending) throws IOException {
        if (this.encodeAsGzip) {
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
            sendBody(gzipOutputStream, -1);
            gzipOutputStream.finish();
            return;
        }
        sendBody(outputStream, pending);
    }

    private void sendBody(OutputStream outputStream, long pending) throws IOException {
        boolean sendEverything;
        byte[] buff = new byte[((int) 16384)];
        if (pending == -1) {
            sendEverything = true;
        } else {
            sendEverything = false;
        }
        while (true) {
            if (pending > 0 || sendEverything) {
                int read = this.data.read(buff, 0, (int) (sendEverything ? PlaybackStateCompat.ACTION_PREPARE : Math.min(pending, PlaybackStateCompat.ACTION_PREPARE)));
                if (read > 0) {
                    outputStream.write(buff, 0, read);
                    if (!sendEverything) {
                        pending -= (long) read;
                    }
                } else {
                    return;
                }
            }
            return;
        }
    }

    public void setChunkedTransfer(boolean chunkedTransfer) {
        this.chunkedTransfer = chunkedTransfer;
    }

    public void setData(InputStream data) {
        this.data = data;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public void setRequestMethod(NanoHTTPD$Method requestMethod) {
        this.requestMethod = requestMethod;
    }

    public void setStatus(IStatus status) {
        this.status = status;
    }
}
