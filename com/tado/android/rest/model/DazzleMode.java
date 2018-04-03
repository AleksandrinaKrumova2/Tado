package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;

public class DazzleMode {
    @SerializedName("enabled")
    boolean enabled;

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
