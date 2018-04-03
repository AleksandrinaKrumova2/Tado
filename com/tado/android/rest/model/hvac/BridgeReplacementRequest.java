package com.tado.android.rest.model.hvac;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tado.android.rest.model.DeviceInput;
import java.io.Serializable;

public class BridgeReplacementRequest implements Serializable {
    private static final long serialVersionUID = 3700095190988421224L;
    @SerializedName("bridge")
    @Expose
    public DeviceInput bridge;
    @SerializedName("revision")
    @Expose
    public Integer revision;
    @SerializedName("type")
    @Expose
    public String type;

    public BridgeReplacementRequest(String type, Integer revision, DeviceInput bridge) {
        this.type = type;
        this.revision = revision;
        this.bridge = bridge;
    }
}
