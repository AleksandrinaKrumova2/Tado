package com.tado.android.rest.model.hvac;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BridgeReplacementInformation implements Serializable {
    private static final long serialVersionUID = -5851961429460387880L;
    @SerializedName("bridgeConnectedDevices")
    @Expose
    private List<GenericHardwareDevice> bridgeConnectedDevices = null;
    @SerializedName("oldBridge")
    @Expose
    public GenericHardwareDevice oldBridge;
    @SerializedName("radioEncryptionKeyUpdated")
    @Expose
    public Boolean radioEncryptionKeyUpdated;
    @SerializedName("reconnectionState")
    @Expose
    public ReconnectionStateEnum reconnectionState;
    @SerializedName("reconnectionTimeout")
    @Expose
    public ReconnectionTimeout reconnectionTimeout;

    public class ReconnectionTimeout implements Serializable {
        @SerializedName("expires")
        @Expose
        public long expires = 0;
        @SerializedName("remainingTimeInSeconds")
        @Expose
        public int remainingTimeInSeconds = 0;
    }

    public List<GenericHardwareDevice> getBridgeConnectedDevices() {
        return this.bridgeConnectedDevices != null ? this.bridgeConnectedDevices : new ArrayList(0);
    }
}
