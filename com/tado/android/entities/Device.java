package com.tado.android.entities;

import com.google.gson.annotations.Expose;

public class Device {
    private Wifi accessPointWiFi;
    @Expose
    private String authKey;
    private boolean connected;
    private String deviceType;
    private String[] errorArray;
    private ServerError[] errors;
    private boolean isReadyToInstall;
    private String lastConnection;
    @Expose
    private String serialNo;
    private String shortSerialNo;

    public Device() {
        this.serialNo = "";
        this.authKey = "";
    }

    public Device(String serialNo, String authKey) {
        this.serialNo = serialNo;
        this.authKey = authKey;
    }

    public String getSerialNo() {
        return this.serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getAuthKey() {
        return this.authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String[] getErrorArray() {
        return this.errorArray;
    }

    public void setErrorArray(String[] errorArray) {
        this.errorArray = errorArray;
    }

    public Wifi getAccessPointWiFi() {
        return this.accessPointWiFi;
    }

    public void setAccessPointWiFi(Wifi accessPointWiFi) {
        this.accessPointWiFi = accessPointWiFi;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getShortSerialNo() {
        return this.shortSerialNo;
    }

    public void setShortSerialNo(String shortSerialNo) {
        this.shortSerialNo = shortSerialNo;
    }

    public boolean isConnected() {
        return this.connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public boolean isReadyToInstall() {
        return this.isReadyToInstall;
    }

    public void setReadyToInstall(boolean isReadyToInstall) {
        this.isReadyToInstall = isReadyToInstall;
    }

    public String getLastConnection() {
        return this.lastConnection;
    }

    public void setLastConnection(String lastConnection) {
        this.lastConnection = lastConnection;
    }

    public ServerError[] getErrors() {
        return this.errors;
    }

    public void setErrors(ServerError[] errors) {
        this.errors = errors;
    }
}
