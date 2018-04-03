package com.tado.android.utils;

public class DeviceStateLogEntry {
    private int mBatteryLevel = 0;
    private ConnectivityStateLogEntry mConnectivityStateLogEntry;
    private String mProvider;
    private String mRadioAccessTechnology;
    private String mWifiList;

    public int getBatteryLevel() {
        return this.mBatteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.mBatteryLevel = batteryLevel;
    }

    public String getRadioAccessTechnology() {
        return this.mRadioAccessTechnology;
    }

    public void setRadioAccessTechnology(String radioAccessTechnology) {
        this.mRadioAccessTechnology = radioAccessTechnology;
    }

    public String getWifiList() {
        return this.mWifiList;
    }

    public void setWifiList(String wifiList) {
        this.mWifiList = wifiList;
    }

    public String getProvider() {
        return this.mProvider;
    }

    public void setProvider(String provider) {
        this.mProvider = provider;
    }

    public ConnectivityStateLogEntry getConnectivityStateLogEntry() {
        return this.mConnectivityStateLogEntry;
    }

    public void setConnectivityStateLogEntry(ConnectivityStateLogEntry connectivityStateLogEntry) {
        this.mConnectivityStateLogEntry = connectivityStateLogEntry;
    }
}
