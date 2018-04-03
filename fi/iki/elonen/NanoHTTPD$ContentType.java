package fi.iki.elonen;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

protected class NanoHTTPD$ContentType {
    private static final String ASCII_ENCODING = "US-ASCII";
    private static final Pattern BOUNDARY_PATTERN = Pattern.compile(BOUNDARY_REGEX, 2);
    private static final String BOUNDARY_REGEX = "[ |\t]*(boundary)[ |\t]*=[ |\t]*['|\"]?([^\"^'^;^,]*)['|\"]?";
    private static final Pattern CHARSET_PATTERN = Pattern.compile(CHARSET_REGEX, 2);
    private static final String CHARSET_REGEX = "[ |\t]*(charset)[ |\t]*=[ |\t]*['|\"]?([^\"^'^;^,]*)['|\"]?";
    private static final String CONTENT_REGEX = "[ |\t]*([^/^ ^;^,]+/[^ ^;^,]+)";
    private static final Pattern MIME_PATTERN = Pattern.compile(CONTENT_REGEX, 2);
    private static final String MULTIPART_FORM_DATA_HEADER = "multipart/form-data";
    private final String boundary;
    private final String contentType;
    private final String contentTypeHeader;
    private final String encoding;

    public NanoHTTPD$ContentType(String contentTypeHeader) {
        this.contentTypeHeader = contentTypeHeader;
        if (contentTypeHeader != null) {
            this.contentType = getDetailFromContentHeader(contentTypeHeader, MIME_PATTERN, "", 1);
            this.encoding = getDetailFromContentHeader(contentTypeHeader, CHARSET_PATTERN, null, 2);
        } else {
            this.contentType = "";
            this.encoding = HttpRequest.CHARSET_UTF8;
        }
        if (MULTIPART_FORM_DATA_HEADER.equalsIgnoreCase(this.contentType)) {
            this.boundary = getDetailFromContentHeader(contentTypeHeader, BOUNDARY_PATTERN, null, 2);
        } else {
            this.boundary = null;
        }
    }

    private String getDetailFromContentHeader(String contentTypeHeader, Pattern pattern, String defaultValue, int group) {
        Matcher matcher = pattern.matcher(contentTypeHeader);
        return matcher.find() ? matcher.group(group) : defaultValue;
    }

    public String getContentTypeHeader() {
        return this.contentTypeHeader;
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getEncoding() {
        return this.encoding == null ? ASCII_ENCODING : this.encoding;
    }

    public String getBoundary() {
        return this.boundary;
    }

    public boolean isMultipart() {
        return MULTIPART_FORM_DATA_HEADER.equalsIgnoreCase(this.contentType);
    }

    public NanoHTTPD$ContentType tryUTF8() {
        if (this.encoding == null) {
            return new NanoHTTPD$ContentType(this.contentTypeHeader + "; charset=UTF-8");
        }
        return this;
    }
}
