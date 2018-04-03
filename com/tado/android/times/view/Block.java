package com.tado.android.times.view;

import com.tado.android.times.view.model.TimeBlockTypeEnum;

public class Block {
    private String id;
    private boolean mAlwaysActive;
    private String mDayType;
    private boolean mDeleteCandidate;
    private int mFrom;
    private String mHomeColor;
    private boolean mInEditMode;
    private boolean mPower;
    private String mTemperatureValue;
    private int mTo;
    private String mWeekdaysType;
    private String mode;
    private TimeBlockTypeEnum type;
    private String zoneType;

    public boolean isPower() {
        return this.mPower;
    }

    public void setPower(boolean power) {
        this.mPower = power;
    }

    public String getZoneType() {
        return this.zoneType;
    }

    public void setZoneType(String zoneType) {
        this.zoneType = zoneType;
    }

    public int getFrom() {
        return this.mFrom;
    }

    public void setFrom(int from) {
        this.mFrom = from;
    }

    public int getTo() {
        return this.mTo;
    }

    public void setTo(int to) {
        this.mTo = to;
    }

    public TimeBlockTypeEnum getType() {
        return this.type;
    }

    public void setType(TimeBlockTypeEnum type) {
        this.type = type;
    }

    public boolean isAlwaysActive() {
        return this.mAlwaysActive;
    }

    public void setAlwaysActive(boolean alwaysActive) {
        this.mAlwaysActive = alwaysActive;
    }

    public boolean isInEditMode() {
        return this.mInEditMode;
    }

    public void setInEditMode(boolean inEditMode) {
        this.mInEditMode = inEditMode;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isDeleteCandidate() {
        return this.mDeleteCandidate;
    }

    public void setDeleteCandidate(boolean deleteCandidate) {
        this.mDeleteCandidate = deleteCandidate;
    }

    public String getHomeColor() {
        return this.mHomeColor;
    }

    public void setHomeColor(String homeColor) {
        this.mHomeColor = homeColor;
    }

    public String getTemperatureValue() {
        return this.mTemperatureValue;
    }

    public void setTemperatureValue(String temperatureValue) {
        this.mTemperatureValue = temperatureValue;
    }

    public String getWeekdaysType() {
        return this.mWeekdaysType;
    }

    public void setWeekdaysType(String weekdaysType) {
        this.mWeekdaysType = weekdaysType;
    }

    public String getDayType() {
        return this.mDayType;
    }

    public void setDayType(String dayType) {
        this.mDayType = dayType;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
