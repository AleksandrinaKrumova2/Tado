package com.tado.android.rest.model.hvac;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tado.android.rest.model.installation.Installation;
import java.io.Serializable;

public class BridgeReplacementInstallation extends Installation implements Serializable {
    private static final long serialVersionUID = -8389116514608735021L;
    @SerializedName("bridgeReplacementInformation")
    @Expose
    private BridgeReplacementInformation bridgeReplacementInformation;
    @SerializedName("state")
    @Expose
    private BridgeReplacementState state;

    public BridgeReplacementState getState() {
        return this.state;
    }

    public void setState(BridgeReplacementState state) {
        this.state = state;
    }

    public void setBridgeReplacementInformation(BridgeReplacementInformation bridgeReplacementInformation) {
        this.bridgeReplacementInformation = bridgeReplacementInformation;
    }

    public BridgeReplacementInformation getBridgeReplacementInformation() {
        return this.bridgeReplacementInformation;
    }

    public boolean isInstallationNotCompleted() {
        return (this.state == null || this.state == BridgeReplacementState.COMPLETED) ? false : true;
    }
}
