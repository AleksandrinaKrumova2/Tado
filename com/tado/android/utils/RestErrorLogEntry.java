package com.tado.android.utils;

import com.google.gson.GsonBuilder;
import com.tado.android.entities.ServerError;

public class RestErrorLogEntry implements BaseLogEntryInterface {
    public static final String FAILURE = "FAILURE";
    public static final String HAS_RESPONSE = "HAS_RESPONSE";
    String mBody;
    String mFailure;
    int mResponseCode;
    String mResponseMessage;
    String mTimestamp;
    String mUrl;
    String serverErrorCode;
    String serverErrorTitle;

    public String getFailure() {
        return this.mFailure;
    }

    public void setFailure(String failure) {
        this.mFailure = failure;
    }

    public String getBody() {
        return this.mBody;
    }

    public void setBody(String body) {
        this.mBody = body;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public String getResponseMessage() {
        return this.mResponseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.mResponseMessage = responseMessage;
    }

    public String getTimestamp() {
        return this.mTimestamp;
    }

    public void setTimestamp(String timestamp) {
        this.mTimestamp = timestamp;
    }

    public int getResponseCode() {
        return this.mResponseCode;
    }

    public void setResponseCode(int responseCode) {
        this.mResponseCode = responseCode;
    }

    public byte[] getBytes() {
        return (new GsonBuilder().create().toJson((Object) this) + ",").getBytes();
    }

    public void setError(ServerError error) {
        if (error != null) {
            this.serverErrorCode = error.getCode();
            this.serverErrorTitle = error.getTitle();
        }
    }

    public ServerError getError() {
        return new ServerError(this.serverErrorCode, null, this.serverErrorTitle);
    }
}
