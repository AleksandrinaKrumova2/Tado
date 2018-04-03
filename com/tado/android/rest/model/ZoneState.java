package com.tado.android.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tado.android.mvp.model.TadoModeEnum;
import java.io.Serializable;

public class ZoneState implements Cloneable, Serializable {
    @SerializedName("activityDataPoints")
    @Expose
    private ActivityDataPoints mActivityDataPoints;
    @SerializedName("geolocationOverride")
    @Expose
    private Boolean mGeolocationOverride = Boolean.valueOf(false);
    @SerializedName("geolocationOverrideDisableTime")
    @Expose
    private String mGeolocationOverrideDisableTime;
    private int mId;
    @SerializedName("link")
    @Expose
    private Link mLink = new Link();
    @SerializedName("openWindowMasked")
    private Boolean mOpenWindowMasked;
    @SerializedName("overlay")
    @Expose
    private ZoneOverlay mOverlay;
    @SerializedName("preparation")
    @Expose
    private Preparation mPreparation;
    @SerializedName("sensorDataPoints")
    @Expose
    private SensorDataPoints mSensorDataPoints;
    @SerializedName("setting")
    @Expose
    private ZoneSetting mSetting = new ZoneSetting();
    @SerializedName("tadoMode")
    @Expose
    private TadoModeEnum mTadoMode = TadoModeEnum.HOME;
    @SerializedName("openWindow")
    private OpenWindow openWindow;

    public String getGeolocationOverrideDisableTime() {
        return this.mGeolocationOverrideDisableTime;
    }

    public void setGeolocationOverrideDisableTime(String geolocationOverrideDisableTime) {
        this.mGeolocationOverrideDisableTime = geolocationOverrideDisableTime;
    }

    public TadoModeEnum getTadoMode() {
        return this.mTadoMode;
    }

    public void setTadoMode(TadoModeEnum tadoMode) {
        this.mTadoMode = tadoMode;
    }

    public Boolean getGeolocationOverride() {
        return this.mGeolocationOverride;
    }

    public void setGeolocationOverride(Boolean geolocationOverride) {
        this.mGeolocationOverride = geolocationOverride;
    }

    public ZoneSetting getSetting() {
        if (this.openWindow != null) {
            this.mSetting.setPowerBoolean(false);
        }
        return this.mSetting;
    }

    public void setSetting(ZoneSetting setting) {
        this.mSetting = setting;
    }

    public Preparation getPreparation() {
        return this.mPreparation;
    }

    public void setPreparation(Preparation preparation) {
        this.mPreparation = preparation;
    }

    public ZoneOverlay getOverlay() {
        return this.openWindow == null ? this.mOverlay : null;
    }

    public void setOverlay(ZoneOverlay overlay) {
        this.mOverlay = overlay;
    }

    public Link getLink() {
        return this.mLink;
    }

    public void setLink(Link link) {
        this.mLink = link;
    }

    public SensorDataPoints getSensorDataPoints() {
        return this.mSensorDataPoints;
    }

    public void setSensorDataPoints(SensorDataPoints sensorDataPoints) {
        this.mSensorDataPoints = sensorDataPoints;
    }

    public boolean isTadoModeHome() {
        return TadoModeEnum.HOME == this.mTadoMode;
    }

    public boolean isTadoModeAway() {
        return TadoModeEnum.AWAY == this.mTadoMode;
    }

    public int getId() {
        return this.mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public ActivityDataPoints getActivityDataPoints() {
        return this.mActivityDataPoints;
    }

    public void setActivityDataPoints(ActivityDataPoints activityDataPoints) {
        this.mActivityDataPoints = activityDataPoints;
    }

    public void setOpenWindow(OpenWindow openWindow) {
        this.openWindow = openWindow;
    }

    public OpenWindow getOpenWindow() {
        return this.openWindow;
    }

    public Boolean getOpenWindowMasked() {
        return this.mOpenWindowMasked;
    }

    private void setOpenWindowMasked(Boolean mOpenWindowMasked) {
        this.mOpenWindowMasked = mOpenWindowMasked;
    }

    public boolean shouldShowReport() {
        return !getSetting().getType().equalsIgnoreCase("HOT_WATER");
    }

    public ZoneState copy() {
        ZoneState copyState = new ZoneState();
        copyState.setOverlay(this.mOverlay);
        copyState.setSetting(this.mSetting);
        copyState.setTadoMode(this.mTadoMode);
        copyState.setActivityDataPoints(this.mActivityDataPoints);
        copyState.setGeolocationOverride(this.mGeolocationOverride);
        copyState.setGeolocationOverrideDisableTime(this.mGeolocationOverrideDisableTime);
        copyState.setLink(this.mLink);
        copyState.setPreparation(this.mPreparation);
        copyState.setSensorDataPoints(this.mSensorDataPoints);
        copyState.setOpenWindow(this.openWindow);
        copyState.setOpenWindowMasked(this.mOpenWindowMasked);
        return copyState;
    }
}
