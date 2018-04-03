package com.tado.android.rest.model.hvac;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tado.android.rest.model.HardwareDeviceCapabilities;
import java.util.ArrayList;
import java.util.List;

public class State {
    @SerializedName("characteristics.capabilities")
    @Expose
    private List<HardwareDeviceCapabilities> characteristicsCapabilities = new ArrayList();
    @SerializedName("connectionState")
    @Expose
    private ConnectionState connectionState;
    @SerializedName("connectionState.value")
    @Expose
    private String connectionStateValue;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("locale")
    @Expose
    private String locale;
    @SerializedName("mountingState")
    @Expose
    private MountingState mountingState;
    @SerializedName("mountingState.value")
    @Expose
    private String mountingStateValue;
    @SerializedName("previousStepId")
    @Expose
    private Integer previousStepId;
    @SerializedName("stack")
    @Expose
    private List<Integer> stack = new ArrayList();

    public String getLocale() {
        return this.locale;
    }

    public String getCountry() {
        return this.country;
    }

    public String getConnectionStateValue() {
        return this.connectionStateValue;
    }

    public ConnectionState getConnectionState() {
        return this.connectionState;
    }

    public String getMountingStateValue() {
        return this.mountingStateValue;
    }

    public MountingState getMountingState() {
        return this.mountingState;
    }

    public Integer getPreviousStepId() {
        return this.previousStepId;
    }

    public List<Integer> getStack() {
        return this.stack;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setConnectionStateValue(String connectionStateValue) {
        this.connectionStateValue = connectionStateValue;
    }

    public void setConnectionState(ConnectionState connectionState) {
        this.connectionState = connectionState;
    }

    public void setMountingStateValue(String mountingStateValue) {
        this.mountingStateValue = mountingStateValue;
    }

    public void setMountingState(MountingState mountingState) {
        this.mountingState = mountingState;
    }

    public void setPreviousStepId(Integer previousStepId) {
        this.previousStepId = previousStepId;
    }

    public void setStack(List<Integer> stack) {
        this.stack = stack;
    }

    public List<String> getCharacteristicsCapabilities() {
        List<String> capabilities = new ArrayList(this.characteristicsCapabilities.size());
        for (HardwareDeviceCapabilities capability : this.characteristicsCapabilities) {
            capabilities.add(capability.toString());
        }
        return capabilities;
    }

    public void setCharacteristicsCapabilities(List<HardwareDeviceCapabilities> characteristicsCapabilities) {
        this.characteristicsCapabilities = characteristicsCapabilities;
    }
}
