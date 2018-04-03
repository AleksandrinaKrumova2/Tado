package com.tado.android.location;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

public class PostResponse {
    @Expose
    private Integer homeFence;
    @Expose
    private Float homeGeolocationLatitude;
    @Expose
    private Float homeGeolocationLongitude;
    @Expose
    private Float minDistance;
    @Expose
    private Integer minTime;
    @Expose
    private String mode;
    @Expose
    private List<Float> regionDistances = new ArrayList();
    @Expose
    private Boolean success;
    @Expose
    private Integer triggerInterval;
    @Expose
    private Integer wakeupInterval;

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Integer getMinTime() {
        return this.minTime;
    }

    public void setMinTime(Integer minTime) {
        this.minTime = minTime;
    }

    public Float getMinDistance() {
        return this.minDistance;
    }

    public void setMinDistance(Float minDistance) {
        this.minDistance = minDistance;
    }

    public Integer getWakeupInterval() {
        return this.wakeupInterval;
    }

    public void setWakeupInterval(Integer wakeupInterval) {
        this.wakeupInterval = wakeupInterval;
    }

    public Integer getTriggerInterval() {
        return this.triggerInterval;
    }

    public void setTriggerInterval(Integer triggerInterval) {
        this.triggerInterval = triggerInterval;
    }

    public List<Float> getRegionDistances() {
        return this.regionDistances;
    }

    public void setRegionDistances(List<Float> regionDistances) {
        this.regionDistances = regionDistances;
    }

    public Float getHomeGeolocationLatitude() {
        return this.homeGeolocationLatitude;
    }

    public void setHomeGeolocationLatitude(Float homeGeolocationLatitude) {
        this.homeGeolocationLatitude = homeGeolocationLatitude;
    }

    public Float getHomeGeolocationLongitude() {
        return this.homeGeolocationLongitude;
    }

    public void setHomeGeolocationLongitude(Float homeGeolocationLongitude) {
        this.homeGeolocationLongitude = homeGeolocationLongitude;
    }

    public Integer getHomeFence() {
        return this.homeFence;
    }

    public void setHomeFence(Integer homeFence) {
        this.homeFence = homeFence;
    }
}
