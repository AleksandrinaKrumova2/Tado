package okhttp3;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.internal.http.HttpHeaders;

public final class CacheControl {
    public static final CacheControl FORCE_CACHE = new Builder().onlyIfCached().maxStale(Integer.MAX_VALUE, TimeUnit.SECONDS).build();
    public static final CacheControl FORCE_NETWORK = new Builder().noCache().build();
    @Nullable
    String headerValue;
    private final boolean immutable;
    private final boolean isPrivate;
    private final boolean isPublic;
    private final int maxAgeSeconds;
    private final int maxStaleSeconds;
    private final int minFreshSeconds;
    private final boolean mustRevalidate;
    private final boolean noCache;
    private final boolean noStore;
    private final boolean noTransform;
    private final boolean onlyIfCached;
    private final int sMaxAgeSeconds;

    private CacheControl(boolean noCache, boolean noStore, int maxAgeSeconds, int sMaxAgeSeconds, boolean isPrivate, boolean isPublic, boolean mustRevalidate, int maxStaleSeconds, int minFreshSeconds, boolean onlyIfCached, boolean noTransform, boolean immutable, @Nullable String headerValue) {
        this.noCache = noCache;
        this.noStore = noStore;
        this.maxAgeSeconds = maxAgeSeconds;
        this.sMaxAgeSeconds = sMaxAgeSeconds;
        this.isPrivate = isPrivate;
        this.isPublic = isPublic;
        this.mustRevalidate = mustRevalidate;
        this.maxStaleSeconds = maxStaleSeconds;
        this.minFreshSeconds = minFreshSeconds;
        this.onlyIfCached = onlyIfCached;
        this.noTransform = noTransform;
        this.immutable = immutable;
        this.headerValue = headerValue;
    }

    CacheControl(Builder builder) {
        this.noCache = builder.noCache;
        this.noStore = builder.noStore;
        this.maxAgeSeconds = builder.maxAgeSeconds;
        this.sMaxAgeSeconds = -1;
        this.isPrivate = false;
        this.isPublic = false;
        this.mustRevalidate = false;
        this.maxStaleSeconds = builder.maxStaleSeconds;
        this.minFreshSeconds = builder.minFreshSeconds;
        this.onlyIfCached = builder.onlyIfCached;
        this.noTransform = builder.noTransform;
        this.immutable = builder.immutable;
    }

    public boolean noCache() {
        return this.noCache;
    }

    public boolean noStore() {
        return this.noStore;
    }

    public int maxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    public int sMaxAgeSeconds() {
        return this.sMaxAgeSeconds;
    }

    public boolean isPrivate() {
        return this.isPrivate;
    }

    public boolean isPublic() {
        return this.isPublic;
    }

    public boolean mustRevalidate() {
        return this.mustRevalidate;
    }

    public int maxStaleSeconds() {
        return this.maxStaleSeconds;
    }

    public int minFreshSeconds() {
        return this.minFreshSeconds;
    }

    public boolean onlyIfCached() {
        return this.onlyIfCached;
    }

    public boolean noTransform() {
        return this.noTransform;
    }

    public boolean immutable() {
        return this.immutable;
    }

    public static CacheControl parse(Headers headers) {
        boolean noCache = false;
        boolean noStore = false;
        int maxAgeSeconds = -1;
        int sMaxAgeSeconds = -1;
        boolean isPrivate = false;
        boolean isPublic = false;
        boolean mustRevalidate = false;
        int maxStaleSeconds = -1;
        int minFreshSeconds = -1;
        boolean onlyIfCached = false;
        boolean noTransform = false;
        boolean immutable = false;
        boolean canUseHeaderValue = true;
        String headerValue = null;
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            String name = headers.name(i);
            String value = headers.value(i);
            if (!name.equalsIgnoreCase(HttpRequest.HEADER_CACHE_CONTROL)) {
                if (name.equalsIgnoreCase("Pragma")) {
                    canUseHeaderValue = false;
                } else {
                }
            } else if (headerValue != null) {
                canUseHeaderValue = false;
            } else {
                headerValue = value;
            }
            int pos = 0;
            while (pos < value.length()) {
                String parameter;
                int tokenStart = pos;
                pos = HttpHeaders.skipUntil(value, pos, "=,;");
                String directive = value.substring(tokenStart, pos).trim();
                if (pos == value.length() || value.charAt(pos) == ',' || value.charAt(pos) == ';') {
                    pos++;
                    parameter = null;
                } else {
                    pos = HttpHeaders.skipWhitespace(value, pos + 1);
                    int parameterStart;
                    if (pos >= value.length() || value.charAt(pos) != '\"') {
                        parameterStart = pos;
                        pos = HttpHeaders.skipUntil(value, pos, ",;");
                        parameter = value.substring(parameterStart, pos).trim();
                    } else {
                        pos++;
                        parameterStart = pos;
                        pos = HttpHeaders.skipUntil(value, pos, "\"");
                        parameter = value.substring(parameterStart, pos);
                        pos++;
                    }
                }
                if ("no-cache".equalsIgnoreCase(directive)) {
                    noCache = true;
                } else if ("no-store".equalsIgnoreCase(directive)) {
                    noStore = true;
                } else if ("max-age".equalsIgnoreCase(directive)) {
                    maxAgeSeconds = HttpHeaders.parseSeconds(parameter, -1);
                } else if ("s-maxage".equalsIgnoreCase(directive)) {
                    sMaxAgeSeconds = HttpHeaders.parseSeconds(parameter, -1);
                } else if ("private".equalsIgnoreCase(directive)) {
                    isPrivate = true;
                } else if ("public".equalsIgnoreCase(directive)) {
                    isPublic = true;
                } else if ("must-revalidate".equalsIgnoreCase(directive)) {
                    mustRevalidate = true;
                } else if ("max-stale".equalsIgnoreCase(directive)) {
                    maxStaleSeconds = HttpHeaders.parseSeconds(parameter, Integer.MAX_VALUE);
                } else if ("min-fresh".equalsIgnoreCase(directive)) {
                    minFreshSeconds = HttpHeaders.parseSeconds(parameter, -1);
                } else if ("only-if-cached".equalsIgnoreCase(directive)) {
                    onlyIfCached = true;
                } else if ("no-transform".equalsIgnoreCase(directive)) {
                    noTransform = true;
                } else if ("immutable".equalsIgnoreCase(directive)) {
                    immutable = true;
                }
            }
        }
        if (!canUseHeaderValue) {
            headerValue = null;
        }
        return new CacheControl(noCache, noStore, maxAgeSeconds, sMaxAgeSeconds, isPrivate, isPublic, mustRevalidate, maxStaleSeconds, minFreshSeconds, onlyIfCached, noTransform, immutable, headerValue);
    }

    public String toString() {
        String result = this.headerValue;
        if (result != null) {
            return result;
        }
        result = headerValue();
        this.headerValue = result;
        return result;
    }

    private String headerValue() {
        StringBuilder result = new StringBuilder();
        if (this.noCache) {
            result.append("no-cache, ");
        }
        if (this.noStore) {
            result.append("no-store, ");
        }
        if (this.maxAgeSeconds != -1) {
            result.append("max-age=").append(this.maxAgeSeconds).append(", ");
        }
        if (this.sMaxAgeSeconds != -1) {
            result.append("s-maxage=").append(this.sMaxAgeSeconds).append(", ");
        }
        if (this.isPrivate) {
            result.append("private, ");
        }
        if (this.isPublic) {
            result.append("public, ");
        }
        if (this.mustRevalidate) {
            result.append("must-revalidate, ");
        }
        if (this.maxStaleSeconds != -1) {
            result.append("max-stale=").append(this.maxStaleSeconds).append(", ");
        }
        if (this.minFreshSeconds != -1) {
            result.append("min-fresh=").append(this.minFreshSeconds).append(", ");
        }
        if (this.onlyIfCached) {
            result.append("only-if-cached, ");
        }
        if (this.noTransform) {
            result.append("no-transform, ");
        }
        if (this.immutable) {
            result.append("immutable, ");
        }
        if (result.length() == 0) {
            return "";
        }
        result.delete(result.length() - 2, result.length());
        return result.toString();
    }
}
