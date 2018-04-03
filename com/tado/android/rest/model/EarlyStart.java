package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;

public class EarlyStart {
    @SerializedName("enabled")
    private Boolean mEnabled;

    public Boolean getEnabled() {
        return this.mEnabled;
    }

    public void setEnabled(Boolean enabled) {
        this.mEnabled = enabled;
    }

    public static EarlyStart create(boolean enabled) {
        EarlyStart earlyStart = new EarlyStart();
        earlyStart.setEnabled(Boolean.valueOf(enabled));
        return earlyStart;
    }
}
