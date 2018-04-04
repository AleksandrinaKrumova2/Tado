package okhttp3.internal.cache;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Headers$Builder;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.internal.Internal;
import okhttp3.internal.http.HttpDate;
import okhttp3.internal.http.HttpHeaders;

public class CacheStrategy$Factory {
    private int ageSeconds = -1;
    final Response cacheResponse;
    private String etag;
    private Date expires;
    private Date lastModified;
    private String lastModifiedString;
    final long nowMillis;
    private long receivedResponseMillis;
    final Request request;
    private long sentRequestMillis;
    private Date servedDate;
    private String servedDateString;

    public CacheStrategy$Factory(long nowMillis, Request request, Response cacheResponse) {
        this.nowMillis = nowMillis;
        this.request = request;
        this.cacheResponse = cacheResponse;
        if (cacheResponse != null) {
            this.sentRequestMillis = cacheResponse.sentRequestAtMillis();
            this.receivedResponseMillis = cacheResponse.receivedResponseAtMillis();
            Headers headers = cacheResponse.headers();
            int size = headers.size();
            for (int i = 0; i < size; i++) {
                String fieldName = headers.name(i);
                String value = headers.value(i);
                if (HttpRequest.HEADER_DATE.equalsIgnoreCase(fieldName)) {
                    this.servedDate = HttpDate.parse(value);
                    this.servedDateString = value;
                } else if (HttpRequest.HEADER_EXPIRES.equalsIgnoreCase(fieldName)) {
                    this.expires = HttpDate.parse(value);
                } else if (HttpRequest.HEADER_LAST_MODIFIED.equalsIgnoreCase(fieldName)) {
                    this.lastModified = HttpDate.parse(value);
                    this.lastModifiedString = value;
                } else if (HttpRequest.HEADER_ETAG.equalsIgnoreCase(fieldName)) {
                    this.etag = value;
                } else if ("Age".equalsIgnoreCase(fieldName)) {
                    this.ageSeconds = HttpHeaders.parseSeconds(value, -1);
                }
            }
        }
    }

    public CacheStrategy get() {
        CacheStrategy candidate = getCandidate();
        if (candidate.networkRequest == null || !this.request.cacheControl().onlyIfCached()) {
            return candidate;
        }
        return new CacheStrategy(null, null);
    }

    private CacheStrategy getCandidate() {
        if (this.cacheResponse == null) {
            return new CacheStrategy(this.request, null);
        }
        if (this.request.isHttps() && this.cacheResponse.handshake() == null) {
            return new CacheStrategy(this.request, null);
        }
        if (!CacheStrategy.isCacheable(this.cacheResponse, this.request)) {
            return new CacheStrategy(this.request, null);
        }
        CacheControl requestCaching = this.request.cacheControl();
        if (requestCaching.noCache() || hasConditions(this.request)) {
            return new CacheStrategy(this.request, null);
        }
        CacheControl responseCaching = this.cacheResponse.cacheControl();
        if (responseCaching.immutable()) {
            return new CacheStrategy(null, this.cacheResponse);
        }
        long ageMillis = cacheResponseAge();
        long freshMillis = computeFreshnessLifetime();
        if (requestCaching.maxAgeSeconds() != -1) {
            freshMillis = Math.min(freshMillis, TimeUnit.SECONDS.toMillis((long) requestCaching.maxAgeSeconds()));
        }
        long minFreshMillis = 0;
        if (requestCaching.minFreshSeconds() != -1) {
            minFreshMillis = TimeUnit.SECONDS.toMillis((long) requestCaching.minFreshSeconds());
        }
        long maxStaleMillis = 0;
        if (!(responseCaching.mustRevalidate() || requestCaching.maxStaleSeconds() == -1)) {
            maxStaleMillis = TimeUnit.SECONDS.toMillis((long) requestCaching.maxStaleSeconds());
        }
        if (responseCaching.noCache() || ageMillis + minFreshMillis >= freshMillis + maxStaleMillis) {
            String conditionName;
            String conditionValue;
            if (this.etag != null) {
                conditionName = HttpRequest.HEADER_IF_NONE_MATCH;
                conditionValue = this.etag;
            } else if (this.lastModified != null) {
                conditionName = "If-Modified-Since";
                conditionValue = this.lastModifiedString;
            } else if (this.servedDate == null) {
                return new CacheStrategy(this.request, null);
            } else {
                conditionName = "If-Modified-Since";
                conditionValue = this.servedDateString;
            }
            Headers$Builder conditionalRequestHeaders = this.request.headers().newBuilder();
            Internal.instance.addLenient(conditionalRequestHeaders, conditionName, conditionValue);
            return new CacheStrategy(this.request.newBuilder().headers(conditionalRequestHeaders.build()).build(), this.cacheResponse);
        }
        Builder builder = this.cacheResponse.newBuilder();
        if (ageMillis + minFreshMillis >= freshMillis) {
            builder.addHeader("Warning", "110 HttpURLConnection \"Response is stale\"");
        }
        if (ageMillis > 86400000 && isFreshnessLifetimeHeuristic()) {
            builder.addHeader("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
        }
        return new CacheStrategy(null, builder.build());
    }

    private long computeFreshnessLifetime() {
        CacheControl responseCaching = this.cacheResponse.cacheControl();
        if (responseCaching.maxAgeSeconds() != -1) {
            return TimeUnit.SECONDS.toMillis((long) responseCaching.maxAgeSeconds());
        }
        long servedMillis;
        long delta;
        if (this.expires != null) {
            if (this.servedDate != null) {
                servedMillis = this.servedDate.getTime();
            } else {
                servedMillis = this.receivedResponseMillis;
            }
            delta = this.expires.getTime() - servedMillis;
            if (delta <= 0) {
                delta = 0;
            }
            return delta;
        } else if (this.lastModified == null || this.cacheResponse.request().url().query() != null) {
            return 0;
        } else {
            if (this.servedDate != null) {
                servedMillis = this.servedDate.getTime();
            } else {
                servedMillis = this.sentRequestMillis;
            }
            delta = servedMillis - this.lastModified.getTime();
            if (delta > 0) {
                return delta / 10;
            }
            return 0;
        }
    }

    private long cacheResponseAge() {
        long receivedAge;
        long apparentReceivedAge = 0;
        if (this.servedDate != null) {
            apparentReceivedAge = Math.max(0, this.receivedResponseMillis - this.servedDate.getTime());
        }
        if (this.ageSeconds != -1) {
            receivedAge = Math.max(apparentReceivedAge, TimeUnit.SECONDS.toMillis((long) this.ageSeconds));
        } else {
            receivedAge = apparentReceivedAge;
        }
        return (receivedAge + (this.receivedResponseMillis - this.sentRequestMillis)) + (this.nowMillis - this.receivedResponseMillis);
    }

    private boolean isFreshnessLifetimeHeuristic() {
        return this.cacheResponse.cacheControl().maxAgeSeconds() == -1 && this.expires == null;
    }

    private static boolean hasConditions(Request request) {
        return (request.header("If-Modified-Since") == null && request.header(HttpRequest.HEADER_IF_NONE_MATCH) == null) ? false : true;
    }
}
