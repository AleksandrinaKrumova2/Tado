package com.tado.android.entities;

public class PositionUser {
    private Boolean geoTrackingEnabled;
    private Boolean geolocationIsStale;
    private boolean me = false;
    private String nickname;
    private Float relativePosition;
    private String username;

    public PositionUser(String nickname, String username, Boolean geoTrackingEnabled, Boolean geolocationIsStale, Float relativePosition, boolean isMe) {
        this.nickname = nickname;
        this.username = username;
        this.geoTrackingEnabled = geoTrackingEnabled;
        this.geolocationIsStale = geolocationIsStale;
        this.relativePosition = relativePosition;
        this.me = isMe;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getGeoTrackingEnabled() {
        return this.geoTrackingEnabled;
    }

    public void setGeoTrackingEnabled(Boolean geoTrackingEnabled) {
        this.geoTrackingEnabled = geoTrackingEnabled;
    }

    public Boolean getGeolocationIsStale() {
        return this.geolocationIsStale;
    }

    public void setGeolocationIsStale(Boolean geolocationIsStale) {
        this.geolocationIsStale = geolocationIsStale;
    }

    public Float getRelativePosition() {
        return this.relativePosition;
    }

    public void setRelativePosition(Float relativePosition) {
        this.relativePosition = relativePosition;
    }

    public int getColumnMembership() {
        if (this.relativePosition.floatValue() == 0.0f) {
            return 0;
        }
        if (this.relativePosition.floatValue() > 0.0f && this.relativePosition.floatValue() <= 33.0f) {
            return 1;
        }
        if (this.relativePosition.floatValue() <= 33.0f || this.relativePosition.floatValue() > 66.0f) {
            return 3;
        }
        return 2;
    }

    public boolean isMe() {
        return this.me;
    }

    public void setMe(boolean me) {
        this.me = me;
    }
}
