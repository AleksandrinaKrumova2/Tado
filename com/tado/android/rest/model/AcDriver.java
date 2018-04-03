package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import com.tado.android.rest.model.installation.AcCommandSet;
import com.tado.android.rest.model.installation.ModeEnum;
import java.io.Serializable;
import java.util.List;

public class AcDriver implements Serializable {
    @SerializedName("commandSet")
    private AcCommandSet commandSet;
    @SerializedName("discriminator")
    private int discriminator;
    @SerializedName("hysteresis")
    private Hysteresis hysteresis;
    @SerializedName("minOnOffTimeInSeconds")
    private Integer minOnOffTimeInSeconds;
    @SerializedName("modes")
    private List<ModeEnum> modes;
    @SerializedName("type")
    private AcDriverTypeEnum type;

    public int getDiscriminator() {
        return this.discriminator;
    }

    public AcDriverTypeEnum getType() {
        return this.type;
    }

    public List<ModeEnum> getModes() {
        return this.modes;
    }

    public AcCommandSet getCommandSet() {
        return this.commandSet;
    }

    public void setCommandSet(AcCommandSet commandSet) {
        this.commandSet = commandSet;
    }

    public Hysteresis getHysteresis() {
        return this.hysteresis;
    }

    public Integer getMinOnOffTimeInSeconds() {
        return this.minOnOffTimeInSeconds;
    }
}
