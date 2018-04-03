package fi.iki.elonen;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import fi.iki.elonen.NanoHTTPD.Response.IStatus;
import okhttp3.internal.http.StatusLine;

public enum NanoHTTPD$Response$Status implements IStatus {
    SWITCH_PROTOCOL(101, "Switching Protocols"),
    OK(Callback.DEFAULT_DRAG_ANIMATION_DURATION, "OK"),
    CREATED(201, "Created"),
    ACCEPTED(202, "Accepted"),
    NO_CONTENT(204, "No Content"),
    PARTIAL_CONTENT(206, "Partial Content"),
    MULTI_STATUS(207, "Multi-Status"),
    REDIRECT(301, "Moved Permanently"),
    FOUND(302, "Found"),
    REDIRECT_SEE_OTHER(303, "See Other"),
    NOT_MODIFIED(304, "Not Modified"),
    TEMPORARY_REDIRECT(StatusLine.HTTP_TEMP_REDIRECT, "Temporary Redirect"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    REQUEST_TIMEOUT(408, "Request Timeout"),
    CONFLICT(409, "Conflict"),
    GONE(410, "Gone"),
    LENGTH_REQUIRED(411, "Length Required"),
    PRECONDITION_FAILED(412, "Precondition Failed"),
    PAYLOAD_TOO_LARGE(413, "Payload Too Large"),
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
    RANGE_NOT_SATISFIABLE(416, "Requested Range Not Satisfiable"),
    EXPECTATION_FAILED(417, "Expectation Failed"),
    TOO_MANY_REQUESTS(429, "Too Many Requests"),
    INTERNAL_ERROR(500, "Internal Server Error"),
    NOT_IMPLEMENTED(501, "Not Implemented"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    UNSUPPORTED_HTTP_VERSION(505, "HTTP Version Not Supported");
    
    private final String description;
    private final int requestStatus;

    private NanoHTTPD$Response$Status(int requestStatus, String description) {
        this.requestStatus = requestStatus;
        this.description = description;
    }

    public static NanoHTTPD$Response$Status lookup(int requestStatus) {
        for (NanoHTTPD$Response$Status status : values()) {
            if (status.getRequestStatus() == requestStatus) {
                return status;
            }
        }
        return null;
    }

    public String getDescription() {
        return "" + this.requestStatus + " " + this.description;
    }

    public int getRequestStatus() {
        return this.requestStatus;
    }
}
