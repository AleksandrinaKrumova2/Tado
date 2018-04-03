package com.tado.android.entities;

public class GLocation {
    double lat;
    double lng;

    public GLocation(double latitude, double longitude) {
        this.lat = latitude;
        this.lng = longitude;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return this.lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
