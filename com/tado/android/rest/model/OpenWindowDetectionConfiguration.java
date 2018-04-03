package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class OpenWindowDetectionConfiguration implements Serializable {
    @SerializedName("enabled")
    private boolean enabled;
    @SerializedName("supported")
    private Boolean supported;
    @SerializedName("timeoutInSeconds")
    private int timeoutInSeconds;

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getTimeoutInSeconds() {
        return this.timeoutInSeconds;
    }

    public void setTimeoutInSeconds(int timeoutInSeconds) {
        this.timeoutInSeconds = timeoutInSeconds;
    }

    public boolean isSupported() {
        return this.supported.booleanValue();
    }
}
