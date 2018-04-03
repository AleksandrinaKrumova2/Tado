package com.tado.android.location;

import android.location.Location;
import com.google.android.gms.maps.model.LatLng;
import java.io.Serializable;

public class SimpleLocation implements Serializable {
    private float accuracy;
    private LatLng point;
    private String provider;
    private long time;

    public SimpleLocation(Location location) {
        this.point = new LatLng(location.getLatitude(), location.getLongitude());
        this.time = location.getTime();
        this.accuracy = location.getAccuracy();
        this.provider = location.getProvider();
    }

    public Location toLocation() {
        Location l = new Location(this.provider);
        l.setLatitude(this.point.latitude);
        l.setLongitude(this.point.longitude);
        l.setAccuracy(this.accuracy);
        l.setTime(this.time);
        return l;
    }
}
