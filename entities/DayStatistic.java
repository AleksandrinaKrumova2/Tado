package com.tado.android.entities;

import com.google.gson.annotations.SerializedName;

public class DayStatistic {
    @SerializedName("AWAY")
    private long away;
    private long day;
    @SerializedName("HOME")
    private long home;
    @SerializedName("MANUAL")
    private long manual;
    @SerializedName("NO_FREEZE")
    private long noFreeze;
    @SerializedName("NO_INTERNET")
    private long noInternet;
    @SerializedName("SLEEP")
    private long sleep;
    @SerializedName("UNDEFINED")
    private long undefined;

    public long getSleep() {
        return this.sleep;
    }

    public void setSleep(long sleep) {
        this.sleep = sleep;
    }

    public long getHome() {
        return this.home;
    }

    public void setHome(long home) {
        this.home = home;
    }

    public long getAway() {
        return this.away;
    }

    public void setAway(long away) {
        this.away = away;
    }

    public long getManual() {
        return this.manual;
    }

    public void setManual(long manual) {
        this.manual = manual;
    }

    public long getNoFreeze() {
        return this.noFreeze;
    }

    public void setNoFreeze(long noFreeze) {
        this.noFreeze = noFreeze;
    }

    public long getUndefined() {
        return this.undefined;
    }

    public void setUndefined(long undefined) {
        this.undefined = undefined;
    }

    public long getNoInternet() {
        return this.noInternet;
    }

    public void setNoInternet(long noInternet) {
        this.noInternet = noInternet;
    }

    public long getDay() {
        return this.day;
    }

    public void setDay(long day) {
        this.day = day;
    }
}
