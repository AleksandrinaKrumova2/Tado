package com.tado.android.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ZoneName {
    @SerializedName("name")
    @Expose
    private String mName;

    public ZoneName(String name) {
        this.mName = name;
    }

    public String getName() {
        return this.mName;
    }
}
