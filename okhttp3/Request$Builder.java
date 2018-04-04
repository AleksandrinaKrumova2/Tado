package okhttp3;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.net.URL;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpMethod;

public class Request$Builder {
    RequestBody body;
    Headers$Builder headers;
    String method;
    Object tag;
    HttpUrl url;

    public Request$Builder() {
        this.method = HttpRequest.METHOD_GET;
        this.headers = new Headers$Builder();
    }

    Request$Builder(Request request) {
        this.url = request.url;
        this.method = request.method;
        this.body = request.body;
        this.tag = request.tag;
        this.headers = request.headers.newBuilder();
    }

    public Request$Builder url(HttpUrl url) {
        if (url == null) {
            throw new NullPointerException("url == null");
        }
        this.url = url;
        return this;
    }

    public Request$Builder url(String url) {
        if (url == null) {
            throw new NullPointerException("url == null");
        }
        if (url.regionMatches(true, 0, "ws:", 0, 3)) {
            url = "http:" + url.substring(3);
        } else {
            if (url.regionMatches(true, 0, "wss:", 0, 4)) {
                url = "https:" + url.substring(4);
            }
        }
        HttpUrl parsed = HttpUrl.parse(url);
        if (parsed != null) {
            return url(parsed);
        }
        throw new IllegalArgumentException("unexpected url: " + url);
    }

    public Request$Builder url(URL url) {
        if (url == null) {
            throw new NullPointerException("url == null");
        }
        HttpUrl parsed = HttpUrl.get(url);
        if (parsed != null) {
            return url(parsed);
        }
        throw new IllegalArgumentException("unexpected url: " + url);
    }

    public Request$Builder header(String name, String value) {
        this.headers.set(name, value);
        return this;
    }

    public Request$Builder addHeader(String name, String value) {
        this.headers.add(name, value);
        return this;
    }

    public Request$Builder removeHeader(String name) {
        this.headers.removeAll(name);
        return this;
    }

    public Request$Builder headers(Headers headers) {
        this.headers = headers.newBuilder();
        return this;
    }

    public Request$Builder cacheControl(CacheControl cacheControl) {
        String value = cacheControl.toString();
        if (value.isEmpty()) {
            return removeHeader(HttpRequest.HEADER_CACHE_CONTROL);
        }
        return header(HttpRequest.HEADER_CACHE_CONTROL, value);
    }

    public Request$Builder get() {
        return method(HttpRequest.METHOD_GET, null);
    }

    public Request$Builder head() {
        return method(HttpRequest.METHOD_HEAD, null);
    }

    public Request$Builder post(RequestBody body) {
        return method(HttpRequest.METHOD_POST, body);
    }

    public Request$Builder delete(@Nullable RequestBody body) {
        return method(HttpRequest.METHOD_DELETE, body);
    }

    public Request$Builder delete() {
        return delete(Util.EMPTY_REQUEST);
    }

    public Request$Builder put(RequestBody body) {
        return method(HttpRequest.METHOD_PUT, body);
    }

    public Request$Builder patch(RequestBody body) {
        return method("PATCH", body);
    }

    public Request$Builder method(String method, @Nullable RequestBody body) {
        if (method == null) {
            throw new NullPointerException("method == null");
        } else if (method.length() == 0) {
            throw new IllegalArgumentException("method.length() == 0");
        } else if (body != null && !HttpMethod.permitsRequestBody(method)) {
            throw new IllegalArgumentException("method " + method + " must not have a request body.");
        } else if (body == null && HttpMethod.requiresRequestBody(method)) {
            throw new IllegalArgumentException("method " + method + " must have a request body.");
        } else {
            this.method = method;
            this.body = body;
            return this;
        }
    }

    public Request$Builder tag(Object tag) {
        this.tag = tag;
        return this;
    }

    public Request build() {
        if (this.url != null) {
            return new Request(this);
        }
        throw new IllegalStateException("url == null");
    }
}
