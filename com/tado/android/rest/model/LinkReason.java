package com.tado.android.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class LinkReason implements Serializable {
    @SerializedName("code")
    @Expose
    private String mCode;
    @SerializedName("title")
    @Expose
    private String mTitle;

    public String getCode() {
        return this.mCode;
    }

    public void setCode(String code) {
        this.mCode = code;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }
}
