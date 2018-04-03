package com.tado.android.entities;

import com.google.gson.annotations.SerializedName;

public class HvacState {
    private String etag;
    @SerializedName("acSetting")
    private CoolingState mAcSetting;
    @SerializedName("geolocationOverride")
    private boolean mGeolocationOverride;
    @SerializedName("humidity")
    private Humidity mHumidity;
    @SerializedName("insideTemperature")
    private StateTemperature mInsideTemperature;
    @SerializedName("overlay")
    private String mOverlay;
    @SerializedName("tadoMode")
    private String mTadoMode;

    public String getTadoMode() {
        return this.mTadoMode;
    }

    public void setTadoMode(String tadoMode) {
        this.mTadoMode = tadoMode;
    }

    public String getOverlay() {
        return this.mOverlay;
    }

    public void setOverlay(String overlay) {
        this.mOverlay = overlay;
    }

    public CoolingState getAcSetting() {
        return this.mAcSetting;
    }

    public void setAcSetting(CoolingState acSetting) {
        this.mAcSetting = acSetting;
    }

    public StateTemperature getInsideTemperature() {
        return this.mInsideTemperature;
    }

    public void setInsideTemperature(StateTemperature insideTemperature) {
        this.mInsideTemperature = insideTemperature;
    }

    public Humidity getHumidity() {
        return this.mHumidity;
    }

    public void setHumidity(Humidity humidity) {
        this.mHumidity = humidity;
    }

    public String getEtag() {
        return this.etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public boolean isGeolocationOverride() {
        return this.mGeolocationOverride;
    }

    public void setGeolocationOverride(boolean geolocationOverride) {
        this.mGeolocationOverride = geolocationOverride;
    }
}
