package fi.iki.elonen;

public enum NanoHTTPD$Method {
    GET,
    PUT,
    POST,
    DELETE,
    HEAD,
    OPTIONS,
    TRACE,
    CONNECT,
    PATCH,
    PROPFIND,
    PROPPATCH,
    MKCOL,
    MOVE,
    COPY,
    LOCK,
    UNLOCK;

    static NanoHTTPD$Method lookup(String method) {
        NanoHTTPD$Method nanoHTTPD$Method = null;
        if (method != null) {
            try {
                nanoHTTPD$Method = valueOf(method);
            } catch (IllegalArgumentException e) {
            }
        }
        return nanoHTTPD$Method;
    }
}
