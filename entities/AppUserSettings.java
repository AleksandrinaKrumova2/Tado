package com.tado.android.entities;

import com.tado.android.utils.UserConfig;

public class AppUserSettings {
    private Boolean geoTrackingEnabled;
    private Boolean rewardNotifications;
    private Boolean settingsChangeNotifications;
    private Boolean stateChangeNotifications;
    private Boolean systemInfoNotifications;

    public Boolean getStateChangeNotifications() {
        return this.stateChangeNotifications;
    }

    public void setStateChangeNotifications(Boolean stateChangeNotifications) {
        this.stateChangeNotifications = stateChangeNotifications;
    }

    public Boolean getSettingsChangeNotifications() {
        return this.settingsChangeNotifications;
    }

    public void setSettingsChangeNotifications(Boolean settingsChangeNotifications) {
        this.settingsChangeNotifications = settingsChangeNotifications;
    }

    public Boolean getSystemInfoNotifications() {
        return this.systemInfoNotifications;
    }

    public void setSystemInfoNotifications(Boolean systemInfoNotifications) {
        this.systemInfoNotifications = systemInfoNotifications;
    }

    public Boolean getRewardNotifications() {
        return this.rewardNotifications;
    }

    public void setRewardNotifications(Boolean rewardNotifications) {
        this.rewardNotifications = rewardNotifications;
    }

    public Boolean getGeoTrackingEnabled() {
        return this.geoTrackingEnabled;
    }

    public void setGeoTrackingEnabled(Boolean geoTrackingEnabled) {
        this.geoTrackingEnabled = geoTrackingEnabled;
    }

    public void applyAppUserSettings() {
        if (getGeoTrackingEnabled() != null) {
            UserConfig.setLocationBasedControlEnabled(getGeoTrackingEnabled().booleanValue());
        }
    }

    public AppUserSettings saveActualAppUserSettings() {
        setGeoTrackingEnabled(Boolean.valueOf(UserConfig.isLocationBasedControlEnabled()));
        return this;
    }

    public boolean isEqualTo(AppUserSettings other) {
        if (getGeoTrackingEnabled() == other.geoTrackingEnabled && getRewardNotifications() == other.getRewardNotifications() && getSettingsChangeNotifications() == other.getSettingsChangeNotifications() && getStateChangeNotifications() == other.getStateChangeNotifications() && getSystemInfoNotifications() == other.getSystemInfoNotifications()) {
            return true;
        }
        return false;
    }
}
