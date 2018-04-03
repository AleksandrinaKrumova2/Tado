package com.tado.android.requests;

import com.tado.android.responses.Response;
import com.tado.android.utils.Constants;
import com.tado.android.utils.FormParams;
import com.tado.android.utils.UserConfig;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.Date;

public abstract class Request {
    private String etag;
    private String httpMethod = this.mRequestMethod.getMethod();
    private String mContentType = HttpRequest.CONTENT_TYPE_FORM;
    private Date mModified;
    private int mPosition;
    private RequestMethodEnum mRequestMethod = RequestMethodEnum.POST;
    private boolean mScheduleApi;
    private String referer;
    private boolean useEtag;

    public enum RequestMethodEnum {
        GET(HttpRequest.METHOD_GET, 0),
        POST(HttpRequest.METHOD_POST, 1),
        PUT(HttpRequest.METHOD_PUT, 2),
        DELETE(HttpRequest.METHOD_DELETE, 3);
        
        private String mMethod;
        private int mPosition;

        private RequestMethodEnum(String method, int position) {
            this.mMethod = method;
            this.mPosition = position;
        }

        public String getMethod() {
            return this.mMethod;
        }

        public int getPosition() {
            return this.mPosition;
        }
    }

    public abstract Response createResponse();

    protected abstract String getAddress();

    public abstract byte[] toBytes();

    public int getPosition() {
        return this.mPosition;
    }

    public void setPosition(int mPosition) {
        this.mPosition = mPosition;
    }

    public Date getModified() {
        return this.mModified;
    }

    public void setModified(Date mModified) {
        this.mModified = mModified;
    }

    public void setContentType(String CntType) {
        this.mContentType = CntType;
    }

    public String getContentType() {
        return this.mContentType;
    }

    public String getUrl() throws MalformedURLException {
        return getApi() + getAddress();
    }

    public String getHttpMethod() {
        return this.httpMethod;
    }

    public void setHttpMethod(RequestMethodEnum requestMethod) {
        this.mRequestMethod = requestMethod;
        this.httpMethod = this.mRequestMethod.getMethod();
    }

    protected void setCredentialsToFormData(FormParams formData) {
        try {
            formData.put(Constants.KEY_EXTRA_USERNAME, URLEncoder.encode(UserConfig.getUsername(), HttpRequest.CHARSET_UTF8));
            formData.put(Constants.KEY_EXTRA_PASSWORD, URLEncoder.encode(UserConfig.getPassword(), HttpRequest.CHARSET_UTF8).replace("*", "%2A"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    protected boolean isScheduleApi() {
        return this.mScheduleApi;
    }

    protected void setScheduleApi(boolean scheduleApi) {
        this.mScheduleApi = scheduleApi;
        if (this.mScheduleApi) {
            this.mContentType = "application/json;charset=UTF-8";
        } else {
            this.mContentType = HttpRequest.CONTENT_TYPE_FORM;
        }
    }

    protected String getApi() {
        if (this.mScheduleApi) {
            return Constants.SERVER_API_SCHEDULE;
        }
        return Constants.SERVER_API_MOBILE;
    }

    protected String getNewApiUrlWithoutHome(String connector) {
        return getNewApiUrlWithoutHome(connector, "", "");
    }

    protected String getNewApiUrlWithoutHome(String connector, String id, String filters) {
        String query = "";
        try {
            query = "username=" + URLEncoder.encode(UserConfig.getUsername(), HttpRequest.CHARSET_UTF8) + "&password=" + URLEncoder.encode(UserConfig.getPassword(), HttpRequest.CHARSET_UTF8).replace("*", "%2A") + filters;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return connector + id + "?" + query;
    }

    protected String getNewApiUrl(String connector) {
        return getNewApiUrl(connector, "", "");
    }

    protected String getNewApiUrl(String connector, String id, String filters) {
        return getNewApiUrlWithoutHome(Constants.URL_HOME + UserConfig.getHomeId() + connector, id, filters);
    }

    public String getEtag() {
        return this.etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public boolean isUseEtag() {
        return this.useEtag;
    }

    public void setUseEtag(boolean useEtag) {
        this.useEtag = useEtag;
    }

    public String getReferer() {
        return this.referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }
}
