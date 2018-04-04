package okhttp3.internal.cache2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

final class Relay {
    private static final long FILE_HEADER_SIZE = 32;
    static final ByteString PREFIX_CLEAN = ByteString.encodeUtf8("OkHttp cache v1\n");
    static final ByteString PREFIX_DIRTY = ByteString.encodeUtf8("OkHttp DIRTY :(\n");
    private static final int SOURCE_FILE = 2;
    private static final int SOURCE_UPSTREAM = 1;
    final Buffer buffer = new Buffer();
    final long bufferMaxSize;
    boolean complete;
    RandomAccessFile file;
    private final ByteString metadata;
    int sourceCount;
    Source upstream;
    final Buffer upstreamBuffer = new Buffer();
    long upstreamPos;
    Thread upstreamReader;

    class RelaySource implements Source {
        private FileOperator fileOperator = new FileOperator(Relay.this.file.getChannel());
        private long sourcePos;
        private final Timeout timeout = new Timeout();

        RelaySource() {
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public long read(okio.Buffer r29, long r30) throws java.io.IOException {
            /*
            r28 = this;
            r0 = r28;
            r4 = r0.fileOperator;
            if (r4 != 0) goto L_0x000f;
        L_0x0006:
            r4 = new java.lang.IllegalStateException;
            r5 = "closed";
            r4.<init>(r5);
            throw r4;
        L_0x000f:
            r0 = r28;
            r10 = okhttp3.internal.cache2.Relay.this;
            monitor-enter(r10);
        L_0x0014:
            r0 = r28;
            r4 = r0.sourcePos;	 Catch:{ all -> 0x0044 }
            r0 = r28;
            r6 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0044 }
            r0 = r6.upstreamPos;	 Catch:{ all -> 0x0044 }
            r26 = r0;
            r4 = (r4 > r26 ? 1 : (r4 == r26 ? 0 : -1));
            if (r4 != 0) goto L_0x0080;
        L_0x0024:
            r0 = r28;
            r4 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0044 }
            r4 = r4.complete;	 Catch:{ all -> 0x0044 }
            if (r4 == 0) goto L_0x0030;
        L_0x002c:
            r8 = -1;
            monitor-exit(r10);	 Catch:{ all -> 0x0044 }
        L_0x002f:
            return r8;
        L_0x0030:
            r0 = r28;
            r4 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0044 }
            r4 = r4.upstreamReader;	 Catch:{ all -> 0x0044 }
            if (r4 == 0) goto L_0x0047;
        L_0x0038:
            r0 = r28;
            r4 = r0.timeout;	 Catch:{ all -> 0x0044 }
            r0 = r28;
            r5 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0044 }
            r4.waitUntilNotified(r5);	 Catch:{ all -> 0x0044 }
            goto L_0x0014;
        L_0x0044:
            r4 = move-exception;
            monitor-exit(r10);	 Catch:{ all -> 0x0044 }
            throw r4;
        L_0x0047:
            r0 = r28;
            r4 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0044 }
            r5 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0044 }
            r4.upstreamReader = r5;	 Catch:{ all -> 0x0044 }
            r24 = 1;
            monitor-exit(r10);	 Catch:{ all -> 0x0044 }
        L_0x0054:
            r4 = 2;
            r0 = r24;
            if (r0 != r4) goto L_0x00c1;
        L_0x0059:
            r0 = r28;
            r4 = r0.sourcePos;
            r4 = r26 - r4;
            r0 = r30;
            r8 = java.lang.Math.min(r0, r4);
            r0 = r28;
            r4 = r0.fileOperator;
            r6 = 32;
            r0 = r28;
            r10 = r0.sourcePos;
            r5 = r6 + r10;
            r7 = r29;
            r4.read(r5, r7, r8);
            r0 = r28;
            r4 = r0.sourcePos;
            r4 = r4 + r8;
            r0 = r28;
            r0.sourcePos = r4;
            goto L_0x002f;
        L_0x0080:
            r0 = r28;
            r4 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0044 }
            r4 = r4.buffer;	 Catch:{ all -> 0x0044 }
            r4 = r4.size();	 Catch:{ all -> 0x0044 }
            r22 = r26 - r4;
            r0 = r28;
            r4 = r0.sourcePos;	 Catch:{ all -> 0x0044 }
            r4 = (r4 > r22 ? 1 : (r4 == r22 ? 0 : -1));
            if (r4 >= 0) goto L_0x0098;
        L_0x0094:
            r24 = 2;
            monitor-exit(r10);	 Catch:{ all -> 0x0044 }
            goto L_0x0054;
        L_0x0098:
            r0 = r28;
            r4 = r0.sourcePos;	 Catch:{ all -> 0x0044 }
            r4 = r26 - r4;
            r0 = r30;
            r8 = java.lang.Math.min(r0, r4);	 Catch:{ all -> 0x0044 }
            r0 = r28;
            r4 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x0044 }
            r4 = r4.buffer;	 Catch:{ all -> 0x0044 }
            r0 = r28;
            r6 = r0.sourcePos;	 Catch:{ all -> 0x0044 }
            r6 = r6 - r22;
            r5 = r29;
            r4.copyTo(r5, r6, r8);	 Catch:{ all -> 0x0044 }
            r0 = r28;
            r4 = r0.sourcePos;	 Catch:{ all -> 0x0044 }
            r4 = r4 + r8;
            r0 = r28;
            r0.sourcePos = r4;	 Catch:{ all -> 0x0044 }
            monitor-exit(r10);	 Catch:{ all -> 0x0044 }
            goto L_0x002f;
        L_0x00c1:
            r0 = r28;
            r4 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x019f }
            r4 = r4.upstream;	 Catch:{ all -> 0x019f }
            r0 = r28;
            r5 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x019f }
            r5 = r5.upstreamBuffer;	 Catch:{ all -> 0x019f }
            r0 = r28;
            r6 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x019f }
            r6 = r6.bufferMaxSize;	 Catch:{ all -> 0x019f }
            r20 = r4.read(r5, r6);	 Catch:{ all -> 0x019f }
            r4 = -1;
            r4 = (r20 > r4 ? 1 : (r20 == r4 ? 0 : -1));
            if (r4 != 0) goto L_0x0101;
        L_0x00dd:
            r0 = r28;
            r4 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x019f }
            r0 = r26;
            r4.commit(r0);	 Catch:{ all -> 0x019f }
            r8 = -1;
            r0 = r28;
            r5 = okhttp3.internal.cache2.Relay.this;
            monitor-enter(r5);
            r0 = r28;
            r4 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x00fe }
            r6 = 0;
            r4.upstreamReader = r6;	 Catch:{ all -> 0x00fe }
            r0 = r28;
            r4 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x00fe }
            r4.notifyAll();	 Catch:{ all -> 0x00fe }
            monitor-exit(r5);	 Catch:{ all -> 0x00fe }
            goto L_0x002f;
        L_0x00fe:
            r4 = move-exception;
            monitor-exit(r5);	 Catch:{ all -> 0x00fe }
            throw r4;
        L_0x0101:
            r0 = r20;
            r2 = r30;
            r14 = java.lang.Math.min(r0, r2);	 Catch:{ all -> 0x019f }
            r0 = r28;
            r4 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x019f }
            r10 = r4.upstreamBuffer;	 Catch:{ all -> 0x019f }
            r12 = 0;
            r11 = r29;
            r10.copyTo(r11, r12, r14);	 Catch:{ all -> 0x019f }
            r0 = r28;
            r4 = r0.sourcePos;	 Catch:{ all -> 0x019f }
            r4 = r4 + r14;
            r0 = r28;
            r0.sourcePos = r4;	 Catch:{ all -> 0x019f }
            r0 = r28;
            r0 = r0.fileOperator;	 Catch:{ all -> 0x019f }
            r16 = r0;
            r4 = 32;
            r17 = r4 + r26;
            r0 = r28;
            r4 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x019f }
            r4 = r4.upstreamBuffer;	 Catch:{ all -> 0x019f }
            r19 = r4.clone();	 Catch:{ all -> 0x019f }
            r16.write(r17, r19, r20);	 Catch:{ all -> 0x019f }
            r0 = r28;
            r5 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x019f }
            monitor-enter(r5);	 Catch:{ all -> 0x019f }
            r0 = r28;
            r4 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x019c }
            r4 = r4.buffer;	 Catch:{ all -> 0x019c }
            r0 = r28;
            r6 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x019c }
            r6 = r6.upstreamBuffer;	 Catch:{ all -> 0x019c }
            r0 = r20;
            r4.write(r6, r0);	 Catch:{ all -> 0x019c }
            r0 = r28;
            r4 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x019c }
            r4 = r4.buffer;	 Catch:{ all -> 0x019c }
            r6 = r4.size();	 Catch:{ all -> 0x019c }
            r0 = r28;
            r4 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x019c }
            r10 = r4.bufferMaxSize;	 Catch:{ all -> 0x019c }
            r4 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1));
            if (r4 <= 0) goto L_0x017a;
        L_0x0160:
            r0 = r28;
            r4 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x019c }
            r4 = r4.buffer;	 Catch:{ all -> 0x019c }
            r0 = r28;
            r6 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x019c }
            r6 = r6.buffer;	 Catch:{ all -> 0x019c }
            r6 = r6.size();	 Catch:{ all -> 0x019c }
            r0 = r28;
            r10 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x019c }
            r10 = r10.bufferMaxSize;	 Catch:{ all -> 0x019c }
            r6 = r6 - r10;
            r4.skip(r6);	 Catch:{ all -> 0x019c }
        L_0x017a:
            r0 = r28;
            r4 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x019c }
            r6 = r4.upstreamPos;	 Catch:{ all -> 0x019c }
            r6 = r6 + r20;
            r4.upstreamPos = r6;	 Catch:{ all -> 0x019c }
            monitor-exit(r5);	 Catch:{ all -> 0x019c }
            r0 = r28;
            r5 = okhttp3.internal.cache2.Relay.this;
            monitor-enter(r5);
            r0 = r28;
            r4 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x01b5 }
            r6 = 0;
            r4.upstreamReader = r6;	 Catch:{ all -> 0x01b5 }
            r0 = r28;
            r4 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x01b5 }
            r4.notifyAll();	 Catch:{ all -> 0x01b5 }
            monitor-exit(r5);	 Catch:{ all -> 0x01b5 }
            r8 = r14;
            goto L_0x002f;
        L_0x019c:
            r4 = move-exception;
            monitor-exit(r5);	 Catch:{ all -> 0x019c }
            throw r4;	 Catch:{ all -> 0x019f }
        L_0x019f:
            r4 = move-exception;
            r0 = r28;
            r5 = okhttp3.internal.cache2.Relay.this;
            monitor-enter(r5);
            r0 = r28;
            r6 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x01b8 }
            r7 = 0;
            r6.upstreamReader = r7;	 Catch:{ all -> 0x01b8 }
            r0 = r28;
            r6 = okhttp3.internal.cache2.Relay.this;	 Catch:{ all -> 0x01b8 }
            r6.notifyAll();	 Catch:{ all -> 0x01b8 }
            monitor-exit(r5);	 Catch:{ all -> 0x01b8 }
            throw r4;
        L_0x01b5:
            r4 = move-exception;
            monitor-exit(r5);	 Catch:{ all -> 0x01b5 }
            throw r4;
        L_0x01b8:
            r4 = move-exception;
            monitor-exit(r5);	 Catch:{ all -> 0x01b8 }
            throw r4;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache2.Relay.RelaySource.read(okio.Buffer, long):long");
        }

        public Timeout timeout() {
            return this.timeout;
        }

        public void close() throws IOException {
            if (this.fileOperator != null) {
                this.fileOperator = null;
                RandomAccessFile fileToClose = null;
                synchronized (Relay.this) {
                    Relay relay = Relay.this;
                    relay.sourceCount--;
                    if (Relay.this.sourceCount == 0) {
                        fileToClose = Relay.this.file;
                        Relay.this.file = null;
                    }
                }
                if (fileToClose != null) {
                    Util.closeQuietly(fileToClose);
                }
            }
        }
    }

    private Relay(RandomAccessFile file, Source upstream, long upstreamPos, ByteString metadata, long bufferMaxSize) {
        this.file = file;
        this.upstream = upstream;
        this.complete = upstream == null;
        this.upstreamPos = upstreamPos;
        this.metadata = metadata;
        this.bufferMaxSize = bufferMaxSize;
    }

    public static Relay edit(File file, Source upstream, ByteString metadata, long bufferMaxSize) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        Relay result = new Relay(randomAccessFile, upstream, 0, metadata, bufferMaxSize);
        randomAccessFile.setLength(0);
        result.writeHeader(PREFIX_DIRTY, -1, -1);
        return result;
    }

    public static Relay read(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        FileOperator fileOperator = new FileOperator(randomAccessFile.getChannel());
        Buffer header = new Buffer();
        fileOperator.read(0, header, 32);
        if (header.readByteString((long) PREFIX_CLEAN.size()).equals(PREFIX_CLEAN)) {
            long upstreamSize = header.readLong();
            long metadataSize = header.readLong();
            Buffer metadataBuffer = new Buffer();
            fileOperator.read(32 + upstreamSize, metadataBuffer, metadataSize);
            return new Relay(randomAccessFile, null, upstreamSize, metadataBuffer.readByteString(), 0);
        }
        throw new IOException("unreadable cache file");
    }

    private void writeHeader(ByteString prefix, long upstreamSize, long metadataSize) throws IOException {
        Buffer header = new Buffer();
        header.write(prefix);
        header.writeLong(upstreamSize);
        header.writeLong(metadataSize);
        if (header.size() != 32) {
            throw new IllegalArgumentException();
        }
        new FileOperator(this.file.getChannel()).write(0, header, 32);
    }

    private void writeMetadata(long upstreamSize) throws IOException {
        Buffer metadataBuffer = new Buffer();
        metadataBuffer.write(this.metadata);
        new FileOperator(this.file.getChannel()).write(32 + upstreamSize, metadataBuffer, (long) this.metadata.size());
    }

    void commit(long upstreamSize) throws IOException {
        writeMetadata(upstreamSize);
        this.file.getChannel().force(false);
        writeHeader(PREFIX_CLEAN, upstreamSize, (long) this.metadata.size());
        this.file.getChannel().force(false);
        synchronized (this) {
            this.complete = true;
        }
        Util.closeQuietly(this.upstream);
        this.upstream = null;
    }

    boolean isClosed() {
        return this.file == null;
    }

    public ByteString metadata() {
        return this.metadata;
    }

    public Source newSource() {
        synchronized (this) {
            if (this.file == null) {
                return null;
            }
            this.sourceCount++;
            return new RelaySource();
        }
    }
}
