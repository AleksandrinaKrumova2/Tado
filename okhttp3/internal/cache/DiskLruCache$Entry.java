package okhttp3.internal.cache;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import okhttp3.internal.Util;
import okhttp3.internal.cache.DiskLruCache.Snapshot;
import okio.BufferedSink;
import okio.Source;

final class DiskLruCache$Entry {
    final File[] cleanFiles;
    DiskLruCache$Editor currentEditor;
    final File[] dirtyFiles;
    final String key;
    final long[] lengths;
    boolean readable;
    long sequenceNumber;
    final /* synthetic */ DiskLruCache this$0;

    DiskLruCache$Entry(DiskLruCache diskLruCache, String key) {
        this.this$0 = diskLruCache;
        this.key = key;
        this.lengths = new long[diskLruCache.valueCount];
        this.cleanFiles = new File[diskLruCache.valueCount];
        this.dirtyFiles = new File[diskLruCache.valueCount];
        StringBuilder fileBuilder = new StringBuilder(key).append('.');
        int truncateTo = fileBuilder.length();
        for (int i = 0; i < diskLruCache.valueCount; i++) {
            fileBuilder.append(i);
            this.cleanFiles[i] = new File(diskLruCache.directory, fileBuilder.toString());
            fileBuilder.append(".tmp");
            this.dirtyFiles[i] = new File(diskLruCache.directory, fileBuilder.toString());
            fileBuilder.setLength(truncateTo);
        }
    }

    void setLengths(String[] strings) throws IOException {
        if (strings.length != this.this$0.valueCount) {
            throw invalidLengths(strings);
        }
        int i = 0;
        while (i < strings.length) {
            try {
                this.lengths[i] = Long.parseLong(strings[i]);
                i++;
            } catch (NumberFormatException e) {
                throw invalidLengths(strings);
            }
        }
    }

    void writeLengths(BufferedSink writer) throws IOException {
        for (long length : this.lengths) {
            writer.writeByte(32).writeDecimalLong(length);
        }
    }

    private IOException invalidLengths(String[] strings) throws IOException {
        throw new IOException("unexpected journal line: " + Arrays.toString(strings));
    }

    Snapshot snapshot() {
        if (Thread.holdsLock(this.this$0)) {
            Source[] sources = new Source[this.this$0.valueCount];
            long[] lengths = (long[]) this.lengths.clone();
            int i = 0;
            while (i < this.this$0.valueCount) {
                try {
                    sources[i] = this.this$0.fileSystem.source(this.cleanFiles[i]);
                    i++;
                } catch (FileNotFoundException e) {
                    i = 0;
                    while (i < this.this$0.valueCount && sources[i] != null) {
                        Util.closeQuietly(sources[i]);
                        i++;
                    }
                    try {
                        this.this$0.removeEntry(this);
                    } catch (IOException e2) {
                    }
                    return null;
                }
            }
            return new Snapshot(this.this$0, this.key, this.sequenceNumber, sources, lengths);
        }
        throw new AssertionError();
    }
}
