package com.tado.android.rest.model;

import android.support.annotation.NonNull;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class GeolocationConfig implements Serializable {
    @Expose
    private int desiredAccuracy;
    @Expose
    private int distanceFilter;
    @Expose
    private HomeLocation home;
    @Expose
    private int maxAccuracy;
    @Expose
    private int maxAge;
    @Expose
    private int minIntervalBetweenBackgroundUpdates;
    @Expose
    private int minIntervalBetweenSentUpdates;
    @Expose
    private int providerUpdateInterval;
    @Expose
    private List<Float> regions;
    private String version;
    @Expose
    private Integer wakeupInterval;

    public HomeLocation getHome() {
        return this.home;
    }

    public LatLng getHomeLocation() {
        return this.home.geolocation;
    }

    public void setHomeLocation(LatLng location) {
        this.home.geolocation = location;
    }

    public float getHomeRadius() {
        return this.home.region;
    }

    public void setHomeRadius(float radius) {
        this.home.region = radius;
    }

    public float getHomeWifiRadius() {
        return this.home.wifiRegion;
    }

    public int getWakeupIntervalMillis() {
        if (this.wakeupInterval == null) {
            return 14400000;
        }
        return this.wakeupInterval.intValue() * 1000;
    }

    @NonNull
    public List<Float> getRegions() {
        return this.regions != null ? this.regions : Collections.emptyList();
    }

    public int getDesiredAccuracy() {
        return this.desiredAccuracy;
    }

    public int getMaxAccuracy() {
        return this.maxAccuracy;
    }

    public int getDistanceFilter() {
        return this.distanceFilter;
    }

    public int getMaxAgeMillis() {
        return this.maxAge * 1000;
    }

    public int getProviderUpdateIntervalMillis() {
        return this.providerUpdateInterval * 1000;
    }

    public int getMinIntervalBetweenSentUpdatesMillis() {
        return this.minIntervalBetweenSentUpdates * 1000;
    }

    public int getMinIntervalBetweenBackgroundUpdatesMillis() {
        return this.minIntervalBetweenBackgroundUpdates * 1000;
    }

    public String getVersion() {
        return this.version != null ? this.version : "";
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
