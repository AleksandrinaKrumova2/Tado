package com.tado.android.location;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.joda.time.DateTimeConstants;

abstract class LocationConfigurationBase implements ILocationConfiguration {
    int BACKGROUND_INTERVAL_MS = 1800000;
    String CONFIG_NAME = "BaseConfig";
    int FILTER_ACCURACY_METERS = 2500;
    int FILTER_DISTANCE_METERS = Callback.DEFAULT_DRAG_ANIMATION_DURATION;
    int FILTER_SPEED_DELTA_SECONDS = SettingsJsonConstants.ANALYTICS_FLUSH_INTERVAL_SECS_DEFAULT;
    float FILTER_SPEED_METERS_SECOND = 50.0f;
    int FILTER_TIME_MILLISECONDS = 120000;
    int FUSED_PROVIDER_ACCURACY = 102;
    int FUSED_PROVIDER_FASTEST_INTERVAL_MS = 120000;
    int FUSED_PROVIDER_INTERVAL_MS = CheckLocationServicesReceiver.WINDOW_LENGTH_MILLIS;
    float HOME_WIFI_THRESHOLD_METERS = 10000.0f;
    int INSTANT_FIX_ACCURACY_METERS = 2500;
    int INSTANT_FIX_DELTA_SECONDS = DateTimeConstants.MILLIS_PER_MINUTE;
    boolean POST_FUSED_PROVIDER = false;
    boolean POST_GEOFENCES = true;
    boolean USE_FUSED_PROVIDER = false;
    boolean USE_GEOFENCES = true;
    int WAKEUP_INTERVAL_MS = 14400000;

    LocationConfigurationBase() {
    }

    public boolean useGeofences() {
        return this.USE_GEOFENCES;
    }

    public boolean postGeofences() {
        return this.POST_GEOFENCES;
    }

    public boolean useFused() {
        return this.USE_FUSED_PROVIDER;
    }

    public boolean postFused() {
        return this.POST_FUSED_PROVIDER;
    }

    public int getFusedAccuracy() {
        return this.FUSED_PROVIDER_ACCURACY;
    }

    public LocationAcquisitionMode getFusedAccuracyAsEnum() {
        return getStringForPriority(getFusedAccuracy());
    }

    public int getFusedIntervalMillis() {
        return this.FUSED_PROVIDER_INTERVAL_MS;
    }

    public int getFusedFastestIntervalMillis() {
        return this.FUSED_PROVIDER_FASTEST_INTERVAL_MS;
    }

    public int getFusedDistanceFilter() {
        return this.FILTER_DISTANCE_METERS;
    }

    public int getFusedTimeFilterMillis() {
        return this.FILTER_TIME_MILLISECONDS;
    }

    public float getFusedSpeedFilter() {
        return this.FILTER_SPEED_METERS_SECOND;
    }

    public int getFusedSpeedDeltaFilter() {
        return this.FILTER_SPEED_DELTA_SECONDS;
    }

    public int getFusedAccuracyFilter() {
        return this.FILTER_ACCURACY_METERS;
    }

    public int getInstantAccuracyFilter() {
        return this.INSTANT_FIX_ACCURACY_METERS;
    }

    public int getInstantTimeDeltaFilterMillis() {
        return this.INSTANT_FIX_DELTA_SECONDS;
    }

    public float getHomeWifiThreshold() {
        return this.HOME_WIFI_THRESHOLD_METERS;
    }

    public int getWakeupIntervalMillis() {
        return this.WAKEUP_INTERVAL_MS;
    }

    public int getBackgroundIntervalMillis() {
        return this.BACKGROUND_INTERVAL_MS;
    }

    public List<Float> getRegions() {
        return Collections.emptyList();
    }

    public String getConfigName() {
        return this.CONFIG_NAME;
    }

    public String toString() {
        return String.format(Locale.US, "Name: %s \nUse/Post Fused %b / %b \nUse/Post geofences %b / %b \nPriority %s \nFused provider interval/fastestinterval %d sec. / %d sec. \nFilters: Distance %d m. Time: %d sec. Speed %.1f Km/h \nAccuracy: %d Instant Fix Accuracy: %d \nWakeup interval: %d hours. \nAlarm interval: %d minutes", new Object[]{getConfigName(), Boolean.valueOf(useFused()), Boolean.valueOf(postFused()), Boolean.valueOf(useGeofences()), Boolean.valueOf(postGeofences()), getStringForPriority(getFusedAccuracy()), Integer.valueOf(getFusedIntervalMillis() / 1000), Integer.valueOf(getFusedFastestIntervalMillis() / 1000), Integer.valueOf(getFusedDistanceFilter()), Integer.valueOf(getFusedTimeFilterMillis() / 1000), Float.valueOf(getFusedSpeedFilter() * 3.6f), Integer.valueOf(getFusedAccuracyFilter()), Integer.valueOf(getInstantAccuracyFilter()), Integer.valueOf(getWakeupIntervalMillis() / DateTimeConstants.MILLIS_PER_HOUR), Integer.valueOf(getBackgroundIntervalMillis() / DateTimeConstants.MILLIS_PER_MINUTE)});
    }

    private LocationAcquisitionMode getStringForPriority(int accuracy) {
        switch (accuracy) {
            case 100:
                return LocationAcquisitionMode.FUSED_HIGH_ACCURACY;
            case 102:
                return LocationAcquisitionMode.FUSED_BALANCED_POWER;
            case 105:
                return LocationAcquisitionMode.FUSED_NO_POWER;
            default:
                return LocationAcquisitionMode.LEGACY;
        }
    }
}
