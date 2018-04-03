package com.tado.android.entities;

import com.google.gson.annotations.SerializedName;

public class Weather {
    private String etag;
    @SerializedName("solarIntensity")
    private SolarIntensity mSolarIntensity;
    @SerializedName("outsideTemperature")
    private StateTemperature mStateTemperature;
    @SerializedName("weatherState")
    private WeatherState mWeatherState;

    public SolarIntensity getSolarIzntensity() {
        return this.mSolarIntensity;
    }

    public void setSolarIntensity(SolarIntensity solarIntensity) {
        this.mSolarIntensity = solarIntensity;
    }

    public StateTemperature getStateTemperature() {
        return this.mStateTemperature;
    }

    public void setStateTemperature(StateTemperature stateTemperature) {
        this.mStateTemperature = stateTemperature;
    }

    public WeatherState getWeatherState() {
        return this.mWeatherState;
    }

    public void setWeatherState(WeatherState weatherState) {
        this.mWeatherState = weatherState;
    }

    public String getEtag() {
        return this.etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }
}
