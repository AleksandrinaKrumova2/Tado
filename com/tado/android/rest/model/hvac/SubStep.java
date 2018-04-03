package com.tado.android.rest.model.hvac;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class SubStep {
    @SerializedName("contentType")
    @Expose
    private ContentTypeEnum contentType;
    @SerializedName("deviceProperty")
    @Expose
    private String deviceProperty;
    @SerializedName("states")
    @Expose
    private List<StateLookup> mStateLookups = new ArrayList();
    @SerializedName("pictureURL")
    @Expose
    private String pictureURL;
    @SerializedName("text")
    @Expose
    private String text;

    public ContentTypeEnum getContentType() {
        return this.contentType;
    }

    public String getPictureURL() {
        return this.pictureURL;
    }

    public String getDeviceProperty() {
        return this.deviceProperty;
    }

    public List<StateLookup> getStateLookups() {
        return this.mStateLookups;
    }

    public String getText() {
        return this.text;
    }

    public SubStep(String text, String pictureURL, String deviceProperty, ContentTypeEnum contentType) {
        this.text = text;
        this.pictureURL = pictureURL;
        this.deviceProperty = deviceProperty;
        this.contentType = contentType;
    }
}
