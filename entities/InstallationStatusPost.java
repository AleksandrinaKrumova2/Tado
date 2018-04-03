package com.tado.android.entities;

import com.google.gson.annotations.Expose;

public class InstallationStatusPost {
    @Expose
    private String installationType;
    @Expose
    private String productSetType;
    @Expose
    private int revision;
    @Expose
    private Device wirelessRemote;

    public String getInstallationType() {
        return this.installationType;
    }

    public void setInstallationType(String installationType) {
        this.installationType = installationType;
    }

    public String getProductSetType() {
        return this.productSetType;
    }

    public void setProductSetType(String productSetType) {
        this.productSetType = productSetType;
    }

    public int getRevision() {
        return this.revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public Device getWirelessRemote() {
        return this.wirelessRemote;
    }

    public void setWirelessRemote(Device wirelessRemote) {
        this.wirelessRemote = wirelessRemote;
    }
}
