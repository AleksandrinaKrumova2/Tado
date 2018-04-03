package com.tado.android.responses;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.tado.android.utils.DateDeserializer;
import java.util.Date;

public abstract class Response {
    private String etag;
    private boolean isFirstStart = false;
    private boolean isUpdateResponse = false;
    private String mLocation;
    private String mUrl;
    private int statusCode;
    private boolean success;

    public abstract void parse(String str) throws NullPointerException, JsonSyntaxException;

    public GsonBuilder getGsonBuilder() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return gsonBuilder;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void setLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public String getLocation() {
        return this.mLocation;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isUpdateResponse() {
        return this.isUpdateResponse;
    }

    public void setUpdateResponse(boolean isUpdateResponse) {
        this.isUpdateResponse = isUpdateResponse;
    }

    public boolean isFirstStart() {
        return this.isFirstStart;
    }

    public void setFirstStart(boolean isFirstStart) {
        this.isFirstStart = isFirstStart;
    }

    public String getEtag() {
        return this.etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }
}
