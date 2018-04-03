package com.tado.android.rest.model;

import com.google.android.gms.maps.model.LatLng;
import java.io.Serializable;

public class HomeLocation implements Serializable {
    public LatLng geolocation;
    public float region;
    public float wifiRegion;

    public HomeLocation(LatLng geolocation, float region) {
        this.geolocation = geolocation;
        this.region = region;
    }
}
