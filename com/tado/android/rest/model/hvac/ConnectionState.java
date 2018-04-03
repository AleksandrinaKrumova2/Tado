package com.tado.android.rest.model.hvac;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConnectionState {
    @SerializedName("value")
    @Expose
    private String value;

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
