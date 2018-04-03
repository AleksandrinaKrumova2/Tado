package com.tado.android.entities;

import java.io.Serializable;
import java.util.HashMap;

public class Wifi implements Serializable {
    private static final HashMap<String, Integer> CODE_FOR_SECURITY_TYPE = new C07861();
    public static final String WIFI_SECURITY_OPEN = "OPEN";
    public static final String WIFI_SECURITY_WEP = "WEP";
    public static final String WIFI_SECURITY_WPA = "WPA";
    public static final String WIFI_SECURITY_WPA2 = "WPA2";
    public static final int WIFI_SECURITY_WPA_MIN_PASSWORD_LENGTH = 8;
    private String bssid;
    private int rssi;
    private String ssid;
    private String type;

    static class C07861 extends HashMap<String, Integer> {
        C07861() {
            put(Wifi.WIFI_SECURITY_OPEN, Integer.valueOf(0));
            put(Wifi.WIFI_SECURITY_WEP, Integer.valueOf(1));
            put(Wifi.WIFI_SECURITY_WPA, Integer.valueOf(2));
            put(Wifi.WIFI_SECURITY_WPA2, Integer.valueOf(3));
        }
    }

    public Wifi(String ssid, String type, String bssid, int rssi) {
        this.ssid = ssid;
        this.type = type;
        this.bssid = bssid;
        this.rssi = rssi;
    }

    public String getSsid() {
        return this.ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBssid() {
        return this.bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public int getRssi() {
        return this.rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public int getTypeInt() {
        return ((Integer) CODE_FOR_SECURITY_TYPE.get(this.type)).intValue();
    }
}
