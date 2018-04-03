package com.tado.android.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class DeviceWifiList {
    private String[] errorArray;
    private String firmeware;
    private String serialnumber;
    private String versions;
    private Wifi[] wlan;

    class WifiComparator implements Comparator<Wifi> {
        boolean mAscending = true;

        public WifiComparator(boolean ascending) {
            this.mAscending = ascending;
        }

        public int compare(Wifi lhs, Wifi rhs) {
            if (this.mAscending) {
                return lhs.getRssi() - rhs.getRssi();
            }
            return -(lhs.getRssi() - rhs.getRssi());
        }
    }

    public String[] getErrorArray() {
        return this.errorArray;
    }

    public void setErrorArray(String[] errorArray) {
        this.errorArray = errorArray;
    }

    public DeviceWifiList(String serialnumber, String firmeware, String versions, Wifi[] wlan) {
        this.serialnumber = serialnumber;
        this.firmeware = firmeware;
        this.versions = versions;
        this.wlan = wlan;
    }

    public Wifi[] getWlan() {
        return this.wlan;
    }

    public void setWlan(Wifi[] wlan) {
        this.wlan = wlan;
    }

    public String getSerialnumber() {
        return this.serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public String getFirmeware() {
        return this.firmeware;
    }

    public void setFirmeware(String firmeware) {
        this.firmeware = firmeware;
    }

    public String getVersions() {
        return this.versions;
    }

    public void setVersions(String versions) {
        this.versions = versions;
    }

    public ArrayList<Wifi> getCleanedArrayList() {
        ArrayList<Wifi> wifiArrayList = new ArrayList();
        Set<String> receivedWifis = new HashSet();
        for (int i = 0; i < this.wlan.length; i++) {
            String ssid = this.wlan[i].getSsid();
            if (!(ssid.compareTo("") == 0 || ssid.compareTo(" ") == 0 || receivedWifis.contains(ssid))) {
                wifiArrayList.add(this.wlan[i]);
                receivedWifis.add(ssid);
            }
        }
        Collections.sort(wifiArrayList, new WifiComparator(false));
        return wifiArrayList;
    }
}
