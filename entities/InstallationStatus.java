package com.tado.android.entities;

import com.google.gson.annotations.Expose;

public class InstallationStatus {
    public static final String AC_G1 = "AC_G1";
    public static final String EK_G1 = "EK_G1";
    public static final String EX_G1 = "EX_G1";
    public static final String EY_G1 = "EY_G1";
    public static final String INSTALLATION_TYPE_INSTALL = "INSTALL";
    public static final String INSTALLATION_TYPE_SALE_FITTING = "SALE_FITTING";
    public static final String INSTALLATION_TYPE_UPGRADE = "UPGRADE";
    public static final String PS_G1 = "PS_G1";
    public static final String ST_G1 = "ST_G1";
    public static final String UX_G1 = "UX_G1";
    public static final String UY_G1 = "UY_G1";
    private ServerError[] errors;
    private int id;
    private String installationType;
    @Expose
    private int revision;
    private String state;
    private String targetProductSetType;
    private String type;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ServerError[] getErrors() {
        return this.errors;
    }

    public void setErrors(ServerError[] errors) {
        this.errors = errors;
    }

    public String getInstallationType() {
        return this.installationType;
    }

    public void setInstallationType(String installationType) {
        this.installationType = installationType;
    }

    public String getTargetProductSetType() {
        return this.targetProductSetType;
    }

    public void setTargetProductSetType(String targetProductSetType) {
        this.targetProductSetType = targetProductSetType;
    }

    public int getRevision() {
        return this.revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }
}
