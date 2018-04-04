package okhttp3;

import java.util.concurrent.TimeUnit;

public final class CacheControl$Builder {
    boolean immutable;
    int maxAgeSeconds = -1;
    int maxStaleSeconds = -1;
    int minFreshSeconds = -1;
    boolean noCache;
    boolean noStore;
    boolean noTransform;
    boolean onlyIfCached;

    public CacheControl$Builder noCache() {
        this.noCache = true;
        return this;
    }

    public CacheControl$Builder noStore() {
        this.noStore = true;
        return this;
    }

    public CacheControl$Builder maxAge(int maxAge, TimeUnit timeUnit) {
        if (maxAge < 0) {
            throw new IllegalArgumentException("maxAge < 0: " + maxAge);
        }
        int i;
        long maxAgeSecondsLong = timeUnit.toSeconds((long) maxAge);
        if (maxAgeSecondsLong > 2147483647L) {
            i = Integer.MAX_VALUE;
        } else {
            i = (int) maxAgeSecondsLong;
        }
        this.maxAgeSeconds = i;
        return this;
    }

    public CacheControl$Builder maxStale(int maxStale, TimeUnit timeUnit) {
        if (maxStale < 0) {
            throw new IllegalArgumentException("maxStale < 0: " + maxStale);
        }
        int i;
        long maxStaleSecondsLong = timeUnit.toSeconds((long) maxStale);
        if (maxStaleSecondsLong > 2147483647L) {
            i = Integer.MAX_VALUE;
        } else {
            i = (int) maxStaleSecondsLong;
        }
        this.maxStaleSeconds = i;
        return this;
    }

    public CacheControl$Builder minFresh(int minFresh, TimeUnit timeUnit) {
        if (minFresh < 0) {
            throw new IllegalArgumentException("minFresh < 0: " + minFresh);
        }
        int i;
        long minFreshSecondsLong = timeUnit.toSeconds((long) minFresh);
        if (minFreshSecondsLong > 2147483647L) {
            i = Integer.MAX_VALUE;
        } else {
            i = (int) minFreshSecondsLong;
        }
        this.minFreshSeconds = i;
        return this;
    }

    public CacheControl$Builder onlyIfCached() {
        this.onlyIfCached = true;
        return this;
    }

    public CacheControl$Builder noTransform() {
        this.noTransform = true;
        return this;
    }

    public CacheControl$Builder immutable() {
        this.immutable = true;
        return this;
    }

    public CacheControl build() {
        return new CacheControl(this);
    }
}
