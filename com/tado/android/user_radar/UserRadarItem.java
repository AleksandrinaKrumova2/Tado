package com.tado.android.user_radar;

public class UserRadarItem {
    boolean geolocationEnabled;
    boolean hasLocation;
    String name;
    boolean stale;

    public UserRadarItem(String name, boolean stale, boolean geolocationEnabled, boolean hasLocation) {
        this.name = name;
        this.stale = stale;
        this.geolocationEnabled = geolocationEnabled;
        this.hasLocation = hasLocation;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStale() {
        return this.stale;
    }

    public void setStale(boolean stale) {
        this.stale = stale;
    }

    public boolean isGeolocationEnabled() {
        return this.geolocationEnabled;
    }

    public void setGeolocationEnabled(boolean geolocationEnabled) {
        this.geolocationEnabled = geolocationEnabled;
    }

    public boolean isHasLocation() {
        return this.hasLocation;
    }

    public void setHasLocation(boolean hasLocation) {
        this.hasLocation = hasLocation;
    }
}
