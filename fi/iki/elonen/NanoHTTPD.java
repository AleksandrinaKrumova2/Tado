package fi.iki.elonen;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.tado.android.installation.CreateHomeContactDetailsActivity;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.UrlUtils;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public abstract class NanoHTTPD {
    private static final Pattern CONTENT_DISPOSITION_ATTRIBUTE_PATTERN = Pattern.compile(CONTENT_DISPOSITION_ATTRIBUTE_REGEX);
    private static final String CONTENT_DISPOSITION_ATTRIBUTE_REGEX = "[ |\t]*([a-zA-Z]*)[ |\t]*=[ |\t]*['|\"]([^\"^']*)['|\"]";
    private static final Pattern CONTENT_DISPOSITION_PATTERN = Pattern.compile(CONTENT_DISPOSITION_REGEX, 2);
    private static final String CONTENT_DISPOSITION_REGEX = "([ |\t]*Content-Disposition[ |\t]*:)(.*)";
    private static final Pattern CONTENT_TYPE_PATTERN = Pattern.compile(CONTENT_TYPE_REGEX, 2);
    private static final String CONTENT_TYPE_REGEX = "([ |\t]*content-type[ |\t]*:)(.*)";
    private static final Logger LOG = Logger.getLogger(NanoHTTPD.class.getName());
    public static final String MIME_HTML = "text/html";
    public static final String MIME_PLAINTEXT = "text/plain";
    protected static Map<String, String> MIME_TYPES = null;
    private static final String QUERY_STRING_PARAMETER = "NanoHttpd.QUERY_STRING";
    public static final int SOCKET_READ_TIMEOUT = 5000;
    protected AsyncRunner asyncRunner;
    private final String hostname;
    private final int myPort;
    private volatile ServerSocket myServerSocket;
    private Thread myThread;
    private ServerSocketFactory serverSocketFactory;
    private TempFileManagerFactory tempFileManagerFactory;

    public interface IHTTPSession {
        void execute() throws IOException;

        CookieHandler getCookies();

        Map<String, String> getHeaders();

        InputStream getInputStream();

        Method getMethod();

        Map<String, List<String>> getParameters();

        @Deprecated
        Map<String, String> getParms();

        String getQueryParameterString();

        String getRemoteHostName();

        String getRemoteIpAddress();

        String getUri();

        void parseBody(Map<String, String> map) throws IOException, ResponseException;
    }

    protected class HTTPSession implements IHTTPSession {
        public static final int BUFSIZE = 8192;
        public static final int MAX_HEADER_SIZE = 1024;
        private static final int MEMORY_STORE_LIMIT = 1024;
        private static final int REQUEST_BUFFER_LEN = 512;
        private CookieHandler cookies;
        private Map<String, String> headers;
        private final BufferedInputStream inputStream;
        private Method method;
        private final OutputStream outputStream;
        private Map<String, List<String>> parms;
        private String protocolVersion;
        private String queryParameterString;
        private String remoteHostname;
        private String remoteIp;
        private int rlen;
        private int splitbyte;
        private final TempFileManager tempFileManager;
        private String uri;

        public HTTPSession(TempFileManager tempFileManager, InputStream inputStream, OutputStream outputStream) {
            this.tempFileManager = tempFileManager;
            this.inputStream = new BufferedInputStream(inputStream, 8192);
            this.outputStream = outputStream;
        }

        public HTTPSession(TempFileManager tempFileManager, InputStream inputStream, OutputStream outputStream, InetAddress inetAddress) {
            this.tempFileManager = tempFileManager;
            this.inputStream = new BufferedInputStream(inputStream, 8192);
            this.outputStream = outputStream;
            String str = (inetAddress.isLoopbackAddress() || inetAddress.isAnyLocalAddress()) ? "127.0.0.1" : inetAddress.getHostAddress().toString();
            this.remoteIp = str;
            str = (inetAddress.isLoopbackAddress() || inetAddress.isAnyLocalAddress()) ? "localhost" : inetAddress.getHostName().toString();
            this.remoteHostname = str;
            this.headers = new HashMap();
        }

        private void decodeHeader(BufferedReader in, Map<String, String> pre, Map<String, List<String>> parms, Map<String, String> headers) throws ResponseException {
            try {
                String inLine = in.readLine();
                if (inLine != null) {
                    StringTokenizer st = new StringTokenizer(inLine);
                    if (st.hasMoreTokens()) {
                        pre.put("method", st.nextToken());
                        if (st.hasMoreTokens()) {
                            String uri = st.nextToken();
                            int qmi = uri.indexOf(63);
                            if (qmi >= 0) {
                                decodeParms(uri.substring(qmi + 1), parms);
                                uri = NanoHTTPD.decodePercent(uri.substring(0, qmi));
                            } else {
                                uri = NanoHTTPD.decodePercent(uri);
                            }
                            if (st.hasMoreTokens()) {
                                this.protocolVersion = st.nextToken();
                            } else {
                                this.protocolVersion = "HTTP/1.1";
                                NanoHTTPD.LOG.log(Level.FINE, "no protocol version specified, strange. Assuming HTTP/1.1.");
                            }
                            String line = in.readLine();
                            while (line != null && !line.trim().isEmpty()) {
                                int p = line.indexOf(58);
                                if (p >= 0) {
                                    headers.put(line.substring(0, p).trim().toLowerCase(Locale.US), line.substring(p + 1).trim());
                                }
                                line = in.readLine();
                            }
                            pre.put("uri", uri);
                            return;
                        }
                        throw new ResponseException(NanoHTTPD$Response$Status.BAD_REQUEST, "BAD REQUEST: Missing URI. Usage: GET /example/file.html");
                    }
                    throw new ResponseException(NanoHTTPD$Response$Status.BAD_REQUEST, "BAD REQUEST: Syntax error. Usage: GET /example/file.html");
                }
            } catch (IOException ioe) {
                throw new ResponseException(NanoHTTPD$Response$Status.INTERNAL_ERROR, "SERVER INTERNAL ERROR: IOException: " + ioe.getMessage(), ioe);
            }
        }

        private void decodeMultipartFormData(ContentType contentType, ByteBuffer fbuf, Map<String, List<String>> parms, Map<String, String> files) throws ResponseException {
            int pcount = 0;
            ResponseException re;
            Exception e;
            try {
                int[] boundaryIdxs = getBoundaryPositions(fbuf, contentType.getBoundary().getBytes());
                if (boundaryIdxs.length < 2) {
                    throw new ResponseException(NanoHTTPD$Response$Status.BAD_REQUEST, "BAD REQUEST: Content type is multipart/form-data but contains less than two boundary strings.");
                }
                byte[] partHeaderBuff = new byte[1024];
                for (int boundaryIdx = 0; boundaryIdx < boundaryIdxs.length - 1; boundaryIdx++) {
                    fbuf.position(boundaryIdxs[boundaryIdx]);
                    int len = fbuf.remaining() < 1024 ? fbuf.remaining() : 1024;
                    fbuf.get(partHeaderBuff, 0, len);
                    BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(partHeaderBuff, 0, len), Charset.forName(contentType.getEncoding())), len);
                    String mpline = in.readLine();
                    int headerLines = 0 + 1;
                    if (mpline == null || !mpline.contains(contentType.getBoundary())) {
                        throw new ResponseException(NanoHTTPD$Response$Status.BAD_REQUEST, "BAD REQUEST: Content type is multipart/form-data but chunk does not start with boundary.");
                    }
                    String partName = null;
                    String fileName = null;
                    String partContentType = null;
                    mpline = in.readLine();
                    headerLines++;
                    while (mpline != null && mpline.trim().length() > 0) {
                        Matcher matcher = NanoHTTPD.CONTENT_DISPOSITION_PATTERN.matcher(mpline);
                        if (matcher.matches()) {
                            matcher = NanoHTTPD.CONTENT_DISPOSITION_ATTRIBUTE_PATTERN.matcher(matcher.group(2));
                            int pcount2 = pcount;
                            while (matcher.find()) {
                                try {
                                    String key = matcher.group(1);
                                    if (CreateHomeContactDetailsActivity.INTENT_NAME.equalsIgnoreCase(key)) {
                                        partName = matcher.group(2);
                                        pcount = pcount2;
                                    } else {
                                        if ("filename".equalsIgnoreCase(key)) {
                                            fileName = matcher.group(2);
                                            if (!fileName.isEmpty()) {
                                                if (pcount2 > 0) {
                                                    pcount = pcount2 + 1;
                                                    partName = partName + String.valueOf(pcount2);
                                                } else {
                                                    pcount = pcount2 + 1;
                                                }
                                            }
                                        }
                                        pcount = pcount2;
                                    }
                                    pcount2 = pcount;
                                } catch (ResponseException e2) {
                                    re = e2;
                                    pcount = pcount2;
                                } catch (Exception e3) {
                                    e = e3;
                                    pcount = pcount2;
                                }
                            }
                            pcount = pcount2;
                        }
                        matcher = NanoHTTPD.CONTENT_TYPE_PATTERN.matcher(mpline);
                        if (matcher.matches()) {
                            partContentType = matcher.group(2).trim();
                        }
                        mpline = in.readLine();
                        headerLines++;
                    }
                    int partHeaderLength = 0;
                    int headerLines2 = headerLines;
                    while (true) {
                        headerLines = headerLines2 - 1;
                        if (headerLines2 <= 0) {
                            break;
                        }
                        partHeaderLength = scipOverNewLine(partHeaderBuff, partHeaderLength);
                        headerLines2 = headerLines;
                    }
                    if (partHeaderLength >= len - 4) {
                        throw new ResponseException(NanoHTTPD$Response$Status.INTERNAL_ERROR, "Multipart header size exceeds MAX_HEADER_SIZE.");
                    }
                    int partDataStart = boundaryIdxs[boundaryIdx] + partHeaderLength;
                    int partDataEnd = boundaryIdxs[boundaryIdx + 1] - 4;
                    fbuf.position(partDataStart);
                    List<String> values = (List) parms.get(partName);
                    if (values == null) {
                        values = new ArrayList();
                        parms.put(partName, values);
                    }
                    if (partContentType == null) {
                        byte[] data_bytes = new byte[(partDataEnd - partDataStart)];
                        fbuf.get(data_bytes);
                        values.add(new String(data_bytes, contentType.getEncoding()));
                    } else {
                        String path = saveTmpFile(fbuf, partDataStart, partDataEnd - partDataStart, fileName);
                        if (files.containsKey(partName)) {
                            int count = 2;
                            while (files.containsKey(partName + count)) {
                                count++;
                            }
                            files.put(partName + count, path);
                        } else {
                            files.put(partName, path);
                        }
                        values.add(fileName);
                    }
                }
                return;
                throw new ResponseException(NanoHTTPD$Response$Status.INTERNAL_ERROR, e.toString());
                throw re;
            } catch (ResponseException e4) {
                re = e4;
            } catch (Exception e5) {
                e = e5;
            }
        }

        private int scipOverNewLine(byte[] partHeaderBuff, int index) {
            while (partHeaderBuff[index] != (byte) 10) {
                index++;
            }
            return index + 1;
        }

        private void decodeParms(String parms, Map<String, List<String>> p) {
            if (parms == null) {
                this.queryParameterString = "";
                return;
            }
            this.queryParameterString = parms;
            StringTokenizer st = new StringTokenizer(parms, "&");
            while (st.hasMoreTokens()) {
                String key;
                String value;
                String e = st.nextToken();
                int sep = e.indexOf(61);
                if (sep >= 0) {
                    key = NanoHTTPD.decodePercent(e.substring(0, sep)).trim();
                    value = NanoHTTPD.decodePercent(e.substring(sep + 1));
                } else {
                    key = NanoHTTPD.decodePercent(e).trim();
                    value = "";
                }
                List<String> values = (List) p.get(key);
                if (values == null) {
                    values = new ArrayList();
                    p.put(key, values);
                }
                values.add(value);
            }
        }

        public void execute() throws IOException {
            try {
                byte[] buf = new byte[8192];
                this.splitbyte = 0;
                this.rlen = 0;
                this.inputStream.mark(8192);
                int read = this.inputStream.read(buf, 0, 8192);
                if (read == -1) {
                    NanoHTTPD.safeClose(this.inputStream);
                    NanoHTTPD.safeClose(this.outputStream);
                    throw new SocketException("NanoHttpd Shutdown");
                }
                while (read > 0) {
                    this.rlen += read;
                    this.splitbyte = findHeaderEnd(buf, this.rlen);
                    if (this.splitbyte > 0) {
                        break;
                    }
                    read = this.inputStream.read(buf, this.rlen, 8192 - this.rlen);
                }
                if (this.splitbyte < this.rlen) {
                    this.inputStream.reset();
                    this.inputStream.skip((long) this.splitbyte);
                }
                this.parms = new HashMap();
                if (this.headers == null) {
                    this.headers = new HashMap();
                } else {
                    this.headers.clear();
                }
                BufferedReader hin = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(buf, 0, this.rlen)));
                Map<String, String> pre = new HashMap();
                decodeHeader(hin, pre, this.parms, this.headers);
                if (this.remoteIp != null) {
                    this.headers.put("remote-addr", this.remoteIp);
                    this.headers.put("http-client-ip", this.remoteIp);
                }
                this.method = Method.lookup((String) pre.get("method"));
                if (this.method == null) {
                    throw new ResponseException(NanoHTTPD$Response$Status.BAD_REQUEST, "BAD REQUEST: Syntax error. HTTP verb " + ((String) pre.get("method")) + " unhandled.");
                }
                this.uri = (String) pre.get("uri");
                this.cookies = new CookieHandler(NanoHTTPD.this, this.headers);
                String connection = (String) this.headers.get("connection");
                boolean keepAlive = "HTTP/1.1".equals(this.protocolVersion) && (connection == null || !connection.matches("(?i).*close.*"));
                Response r = NanoHTTPD.this.serve(this);
                if (r == null) {
                    throw new ResponseException(NanoHTTPD$Response$Status.INTERNAL_ERROR, "SERVER INTERNAL ERROR: Serve() returned a null response.");
                }
                String acceptEncoding = (String) this.headers.get("accept-encoding");
                this.cookies.unloadQueue(r);
                r.setRequestMethod(this.method);
                boolean z = NanoHTTPD.this.useGzipWhenAccepted(r) && acceptEncoding != null && acceptEncoding.contains(HttpRequest.ENCODING_GZIP);
                r.setGzipEncoding(z);
                r.setKeepAlive(keepAlive);
                r.send(this.outputStream);
                if (!keepAlive || r.isCloseConnection()) {
                    throw new SocketException("NanoHttpd Shutdown");
                }
                NanoHTTPD.safeClose(r);
                this.tempFileManager.clear();
            } catch (SSLException e) {
                throw e;
            } catch (IOException e2) {
                NanoHTTPD.safeClose(this.inputStream);
                NanoHTTPD.safeClose(this.outputStream);
                throw new SocketException("NanoHttpd Shutdown");
            } catch (SocketException e3) {
                throw e3;
            } catch (SocketTimeoutException ste) {
                throw ste;
            } catch (ResponseException re) {
                NanoHTTPD.newFixedLengthResponse(re.getStatus(), NanoHTTPD.MIME_PLAINTEXT, re.getMessage()).send(this.outputStream);
                NanoHTTPD.safeClose(this.outputStream);
                NanoHTTPD.safeClose(null);
                this.tempFileManager.clear();
            } catch (SSLException ssle) {
                NanoHTTPD.newFixedLengthResponse(NanoHTTPD$Response$Status.INTERNAL_ERROR, NanoHTTPD.MIME_PLAINTEXT, "SSL PROTOCOL FAILURE: " + ssle.getMessage()).send(this.outputStream);
                NanoHTTPD.safeClose(this.outputStream);
                NanoHTTPD.safeClose(null);
                this.tempFileManager.clear();
            } catch (IOException ioe) {
                NanoHTTPD.newFixedLengthResponse(NanoHTTPD$Response$Status.INTERNAL_ERROR, NanoHTTPD.MIME_PLAINTEXT, "SERVER INTERNAL ERROR: IOException: " + ioe.getMessage()).send(this.outputStream);
                NanoHTTPD.safeClose(this.outputStream);
                NanoHTTPD.safeClose(null);
                this.tempFileManager.clear();
            } catch (Throwable th) {
                NanoHTTPD.safeClose(null);
                this.tempFileManager.clear();
            }
        }

        private int findHeaderEnd(byte[] buf, int rlen) {
            int splitbyte = 0;
            while (splitbyte + 1 < rlen) {
                if (buf[splitbyte] == (byte) 13 && buf[splitbyte + 1] == (byte) 10 && splitbyte + 3 < rlen && buf[splitbyte + 2] == (byte) 13 && buf[splitbyte + 3] == (byte) 10) {
                    return splitbyte + 4;
                }
                if (buf[splitbyte] == (byte) 10 && buf[splitbyte + 1] == (byte) 10) {
                    return splitbyte + 2;
                }
                splitbyte++;
            }
            return 0;
        }

        private int[] getBoundaryPositions(ByteBuffer b, byte[] boundary) {
            int[] res = new int[0];
            if (b.remaining() < boundary.length) {
                return res;
            }
            int search_window_pos = 0;
            byte[] search_window = new byte[(boundary.length + 4096)];
            int first_fill = b.remaining() < search_window.length ? b.remaining() : search_window.length;
            b.get(search_window, 0, first_fill);
            int new_bytes = first_fill - boundary.length;
            do {
                int j = 0;
                while (j < new_bytes) {
                    int i = 0;
                    while (i < boundary.length && search_window[j + i] == boundary[i]) {
                        if (i == boundary.length - 1) {
                            int[] new_res = new int[(res.length + 1)];
                            System.arraycopy(res, 0, new_res, 0, res.length);
                            new_res[res.length] = search_window_pos + j;
                            res = new_res;
                        }
                        i++;
                    }
                    j++;
                }
                search_window_pos += new_bytes;
                System.arraycopy(search_window, search_window.length - boundary.length, search_window, 0, boundary.length);
                new_bytes = search_window.length - boundary.length;
                if (b.remaining() < new_bytes) {
                    new_bytes = b.remaining();
                }
                b.get(search_window, boundary.length, new_bytes);
            } while (new_bytes > 0);
            return res;
        }

        public CookieHandler getCookies() {
            return this.cookies;
        }

        public final Map<String, String> getHeaders() {
            return this.headers;
        }

        public final InputStream getInputStream() {
            return this.inputStream;
        }

        public final Method getMethod() {
            return this.method;
        }

        @Deprecated
        public final Map<String, String> getParms() {
            Map<String, String> result = new HashMap();
            for (String key : this.parms.keySet()) {
                result.put(key, ((List) this.parms.get(key)).get(0));
            }
            return result;
        }

        public final Map<String, List<String>> getParameters() {
            return this.parms;
        }

        public String getQueryParameterString() {
            return this.queryParameterString;
        }

        private RandomAccessFile getTmpBucket() {
            try {
                return new RandomAccessFile(this.tempFileManager.createTempFile(null).getName(), "rw");
            } catch (Exception e) {
                throw new Error(e);
            }
        }

        public final String getUri() {
            return this.uri;
        }

        public long getBodySize() {
            if (this.headers.containsKey("content-length")) {
                return Long.parseLong((String) this.headers.get("content-length"));
            }
            if (this.splitbyte < this.rlen) {
                return (long) (this.rlen - this.splitbyte);
            }
            return 0;
        }

        public void parseBody(Map<String, String> files) throws IOException, ResponseException {
            RandomAccessFile randomAccessFile = null;
            try {
                ByteBuffer fbuf;
                long size = getBodySize();
                ByteArrayOutputStream baos = null;
                if (size < PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
                    baos = new ByteArrayOutputStream();
                    DataOutput dataOutputStream = new DataOutputStream(baos);
                } else {
                    randomAccessFile = getTmpBucket();
                    Object requestDataOutput = randomAccessFile;
                }
                byte[] buf = new byte[512];
                while (this.rlen >= 0 && size > 0) {
                    this.rlen = this.inputStream.read(buf, 0, (int) Math.min(size, 512));
                    size -= (long) this.rlen;
                    if (this.rlen > 0) {
                        requestDataOutput.write(buf, 0, this.rlen);
                    }
                }
                if (baos != null) {
                    fbuf = ByteBuffer.wrap(baos.toByteArray(), 0, baos.size());
                } else {
                    fbuf = randomAccessFile.getChannel().map(MapMode.READ_ONLY, 0, randomAccessFile.length());
                    randomAccessFile.seek(0);
                }
                if (Method.POST.equals(this.method)) {
                    ContentType contentType = new ContentType((String) this.headers.get("content-type"));
                    if (!contentType.isMultipart()) {
                        byte[] postBytes = new byte[fbuf.remaining()];
                        fbuf.get(postBytes);
                        String postLine = new String(postBytes, contentType.getEncoding()).trim();
                        if (HttpRequest.CONTENT_TYPE_FORM.equalsIgnoreCase(contentType.getContentType())) {
                            decodeParms(postLine, this.parms);
                        } else if (postLine.length() != 0) {
                            files.put("postData", postLine);
                        }
                    } else if (contentType.getBoundary() == null) {
                        throw new ResponseException(NanoHTTPD$Response$Status.BAD_REQUEST, "BAD REQUEST: Content type is multipart/form-data but boundary missing. Usage: GET /example/file.html");
                    } else {
                        decodeMultipartFormData(contentType, fbuf, this.parms, files);
                    }
                } else if (Method.PUT.equals(this.method)) {
                    files.put(Param.CONTENT, saveTmpFile(fbuf, 0, fbuf.limit(), null));
                }
                NanoHTTPD.safeClose(randomAccessFile);
            } catch (Throwable th) {
                NanoHTTPD.safeClose(randomAccessFile);
            }
        }

        private String saveTmpFile(ByteBuffer b, int offset, int len, String filename_hint) {
            Exception e;
            Throwable th;
            String path = "";
            if (len > 0) {
                FileOutputStream fileOutputStream = null;
                try {
                    TempFile tempFile = this.tempFileManager.createTempFile(filename_hint);
                    ByteBuffer src = b.duplicate();
                    FileOutputStream fileOutputStream2 = new FileOutputStream(tempFile.getName());
                    try {
                        FileChannel dest = fileOutputStream2.getChannel();
                        src.position(offset).limit(offset + len);
                        dest.write(src.slice());
                        path = tempFile.getName();
                        NanoHTTPD.safeClose(fileOutputStream2);
                    } catch (Exception e2) {
                        e = e2;
                        fileOutputStream = fileOutputStream2;
                        try {
                            throw new Error(e);
                        } catch (Throwable th2) {
                            th = th2;
                            NanoHTTPD.safeClose(fileOutputStream);
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        fileOutputStream = fileOutputStream2;
                        NanoHTTPD.safeClose(fileOutputStream);
                        throw th;
                    }
                } catch (Exception e3) {
                    e = e3;
                    throw new Error(e);
                }
            }
            return path;
        }

        public String getRemoteIpAddress() {
            return this.remoteIp;
        }

        public String getRemoteHostName() {
            return this.remoteHostname;
        }
    }

    public static Map<String, String> mimeTypes() {
        if (MIME_TYPES == null) {
            MIME_TYPES = new HashMap();
            loadMimeTypes(MIME_TYPES, "META-INF/nanohttpd/default-mimetypes.properties");
            loadMimeTypes(MIME_TYPES, "META-INF/nanohttpd/mimetypes.properties");
            if (MIME_TYPES.isEmpty()) {
                LOG.log(Level.WARNING, "no mime types found in the classpath! please provide mimetypes.properties");
            }
        }
        return MIME_TYPES;
    }

    private static void loadMimeTypes(Map<String, String> result, String resourceName) {
        try {
            Enumeration<URL> resources = NanoHTTPD.class.getClassLoader().getResources(resourceName);
            while (resources.hasMoreElements()) {
                URL url = (URL) resources.nextElement();
                Properties properties = new Properties();
                InputStream inputStream = null;
                try {
                    inputStream = url.openStream();
                    properties.load(inputStream);
                } catch (IOException e) {
                    LOG.log(Level.SEVERE, "could not load mimetypes from " + url, e);
                } finally {
                    safeClose(inputStream);
                }
                result.putAll(properties);
            }
        } catch (IOException e2) {
            LOG.log(Level.INFO, "no mime types available at " + resourceName);
        }
    }

    public static SSLServerSocketFactory makeSSLSocketFactory(KeyStore loadedKeyStore, KeyManager[] keyManagers) throws IOException {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(loadedKeyStore);
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(keyManagers, trustManagerFactory.getTrustManagers(), null);
            return ctx.getServerSocketFactory();
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    public static SSLServerSocketFactory makeSSLSocketFactory(KeyStore loadedKeyStore, KeyManagerFactory loadedKeyFactory) throws IOException {
        try {
            return makeSSLSocketFactory(loadedKeyStore, loadedKeyFactory.getKeyManagers());
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    public static SSLServerSocketFactory makeSSLSocketFactory(String keyAndTrustStoreClasspathPath, char[] passphrase) throws IOException {
        try {
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream keystoreStream = NanoHTTPD.class.getResourceAsStream(keyAndTrustStoreClasspathPath);
            if (keystoreStream == null) {
                throw new IOException("Unable to load keystore from classpath: " + keyAndTrustStoreClasspathPath);
            }
            keystore.load(keystoreStream, passphrase);
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keystore, passphrase);
            return makeSSLSocketFactory(keystore, keyManagerFactory);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    public static String getMimeTypeForFile(String uri) {
        int dot = uri.lastIndexOf(46);
        String mime = null;
        if (dot >= 0) {
            mime = (String) mimeTypes().get(uri.substring(dot + 1).toLowerCase());
        }
        return mime == null ? "application/octet-stream" : mime;
    }

    private static final void safeClose(Object closeable) {
        if (closeable != null) {
            try {
                if (closeable instanceof Closeable) {
                    ((Closeable) closeable).close();
                } else if (closeable instanceof Socket) {
                    ((Socket) closeable).close();
                } else if (closeable instanceof ServerSocket) {
                    ((ServerSocket) closeable).close();
                } else {
                    throw new IllegalArgumentException("Unknown object to close");
                }
            } catch (IOException e) {
                LOG.log(Level.SEVERE, "Could not close", e);
            }
        }
    }

    public NanoHTTPD(int port) {
        this(null, port);
    }

    public NanoHTTPD(String hostname, int port) {
        this.serverSocketFactory = new DefaultServerSocketFactory();
        this.hostname = hostname;
        this.myPort = port;
        setTempFileManagerFactory(new DefaultTempFileManagerFactory(this, null));
        setAsyncRunner(new DefaultAsyncRunner());
    }

    public synchronized void closeAllConnections() {
        stop();
    }

    protected ClientHandler createClientHandler(Socket finalAccept, InputStream inputStream) {
        return new ClientHandler(this, inputStream, finalAccept);
    }

    protected ServerRunnable createServerRunnable(int timeout) {
        return new ServerRunnable(this, timeout);
    }

    protected static Map<String, List<String>> decodeParameters(Map<String, String> parms) {
        return decodeParameters((String) parms.get(QUERY_STRING_PARAMETER));
    }

    protected static Map<String, List<String>> decodeParameters(String queryString) {
        Map<String, List<String>> parms = new HashMap();
        if (queryString != null) {
            StringTokenizer st = new StringTokenizer(queryString, "&");
            while (st.hasMoreTokens()) {
                String e = st.nextToken();
                int sep = e.indexOf(61);
                String propertyName = sep >= 0 ? decodePercent(e.substring(0, sep)).trim() : decodePercent(e).trim();
                if (!parms.containsKey(propertyName)) {
                    parms.put(propertyName, new ArrayList());
                }
                String propertyValue = sep >= 0 ? decodePercent(e.substring(sep + 1)) : null;
                if (propertyValue != null) {
                    ((List) parms.get(propertyName)).add(propertyValue);
                }
            }
        }
        return parms;
    }

    protected static String decodePercent(String str) {
        String decoded = null;
        try {
            decoded = URLDecoder.decode(str, UrlUtils.UTF8);
        } catch (UnsupportedEncodingException ignored) {
            LOG.log(Level.WARNING, "Encoding not supported, ignored", ignored);
        }
        return decoded;
    }

    protected boolean useGzipWhenAccepted(Response r) {
        return r.getMimeType() != null && (r.getMimeType().toLowerCase().contains("text/") || r.getMimeType().toLowerCase().contains("/json"));
    }

    public final int getListeningPort() {
        return this.myServerSocket == null ? -1 : this.myServerSocket.getLocalPort();
    }

    public final boolean isAlive() {
        return wasStarted() && !this.myServerSocket.isClosed() && this.myThread.isAlive();
    }

    public ServerSocketFactory getServerSocketFactory() {
        return this.serverSocketFactory;
    }

    public void setServerSocketFactory(ServerSocketFactory serverSocketFactory) {
        this.serverSocketFactory = serverSocketFactory;
    }

    public String getHostname() {
        return this.hostname;
    }

    public TempFileManagerFactory getTempFileManagerFactory() {
        return this.tempFileManagerFactory;
    }

    public void makeSecure(SSLServerSocketFactory sslServerSocketFactory, String[] sslProtocols) {
        this.serverSocketFactory = new SecureServerSocketFactory(sslServerSocketFactory, sslProtocols);
    }

    public static Response newChunkedResponse(IStatus status, String mimeType, InputStream data) {
        return new Response(status, mimeType, data, -1);
    }

    public static Response newFixedLengthResponse(IStatus status, String mimeType, InputStream data, long totalBytes) {
        return new Response(status, mimeType, data, totalBytes);
    }

    public static Response newFixedLengthResponse(IStatus status, String mimeType, String txt) {
        ContentType contentType = new ContentType(mimeType);
        if (txt == null) {
            return newFixedLengthResponse(status, mimeType, new ByteArrayInputStream(new byte[0]), 0);
        }
        byte[] bytes;
        try {
            if (!Charset.forName(contentType.getEncoding()).newEncoder().canEncode(txt)) {
                contentType = contentType.tryUTF8();
            }
            bytes = txt.getBytes(contentType.getEncoding());
        } catch (UnsupportedEncodingException e) {
            LOG.log(Level.SEVERE, "encoding problem, responding nothing", e);
            bytes = new byte[0];
        }
        return newFixedLengthResponse(status, contentType.getContentTypeHeader(), new ByteArrayInputStream(bytes), (long) bytes.length);
    }

    public static Response newFixedLengthResponse(String msg) {
        return newFixedLengthResponse(NanoHTTPD$Response$Status.OK, MIME_HTML, msg);
    }

    public Response serve(IHTTPSession session) {
        Map<String, String> files = new HashMap();
        Method method = session.getMethod();
        if (Method.PUT.equals(method) || Method.POST.equals(method)) {
            try {
                session.parseBody(files);
            } catch (IOException ioe) {
                return newFixedLengthResponse(NanoHTTPD$Response$Status.INTERNAL_ERROR, MIME_PLAINTEXT, "SERVER INTERNAL ERROR: IOException: " + ioe.getMessage());
            } catch (ResponseException re) {
                return newFixedLengthResponse(re.getStatus(), MIME_PLAINTEXT, re.getMessage());
            }
        }
        Map<String, String> parms = session.getParms();
        parms.put(QUERY_STRING_PARAMETER, session.getQueryParameterString());
        return serve(session.getUri(), method, session.getHeaders(), parms, files);
    }

    @Deprecated
    public Response serve(String uri, Method method, Map<String, String> map, Map<String, String> map2, Map<String, String> map3) {
        return newFixedLengthResponse(NanoHTTPD$Response$Status.NOT_FOUND, MIME_PLAINTEXT, "Not Found");
    }

    public void setAsyncRunner(AsyncRunner asyncRunner) {
        this.asyncRunner = asyncRunner;
    }

    public void setTempFileManagerFactory(TempFileManagerFactory tempFileManagerFactory) {
        this.tempFileManagerFactory = tempFileManagerFactory;
    }

    public void start() throws IOException {
        start(SOCKET_READ_TIMEOUT);
    }

    public void start(int timeout) throws IOException {
        start(timeout, true);
    }

    public void start(int timeout, boolean daemon) throws IOException {
        this.myServerSocket = getServerSocketFactory().create();
        this.myServerSocket.setReuseAddress(true);
        ServerRunnable serverRunnable = createServerRunnable(timeout);
        this.myThread = new Thread(serverRunnable);
        this.myThread.setDaemon(daemon);
        this.myThread.setName("NanoHttpd Main Listener");
        this.myThread.start();
        while (!ServerRunnable.access$1100(serverRunnable) && ServerRunnable.access$1200(serverRunnable) == null) {
            try {
                Thread.sleep(10);
            } catch (Throwable th) {
            }
        }
        if (ServerRunnable.access$1200(serverRunnable) != null) {
            throw ServerRunnable.access$1200(serverRunnable);
        }
    }

    public void stop() {
        try {
            safeClose(this.myServerSocket);
            this.asyncRunner.closeAll();
            if (this.myThread != null) {
                this.myThread.join();
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Could not stop all connections", e);
        }
    }

    public final boolean wasStarted() {
        return (this.myServerSocket == null || this.myThread == null) ? false : true;
    }
}
