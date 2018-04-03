package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ZoneDazzleMode implements Serializable {
    @SerializedName("enabled")
    boolean enabled;
    @SerializedName("supported")
    boolean supported;

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean isSupported() {
        return this.supported;
    }
}
