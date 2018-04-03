package com.tado.android.location;

import android.support.annotation.Nullable;
import com.squareup.otto.Bus;
import com.tado.android.rest.model.GeolocationConfig;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.util.List;
import org.joda.time.DateTimeConstants;

public class LocationConfiguration extends LocationConfigurationBase {
    private GeolocationConfig configuration;

    public static class Builder {
        LocationConfiguration config = new LocationConfiguration();

        public Builder(@Nullable GeolocationConfig configuration) {
            if (configuration != null) {
                this.config = new LocationConfiguration(configuration);
                setConfigName(configuration.getVersion()).setFusedDistanceFilter(configuration.getDistanceFilter()).setFusedAccuracyFilter(configuration.getMaxAccuracy()).setFusedTimeFilter(configuration.getMinIntervalBetweenSentUpdatesMillis()).setHomeWifiThreshold(configuration.getHomeWifiRadius()).setFusedInterval(configuration.getProviderUpdateIntervalMillis(), configuration.getProviderUpdateIntervalMillis() / 2).setWakeUpInterval(configuration.getWakeupIntervalMillis()).setBackgroundInterval(configuration.getMinIntervalBetweenBackgroundUpdatesMillis());
            }
        }

        public LocationConfiguration build() {
            return this.config;
        }

        public Builder setGeofencing(boolean useGeofences, boolean postGeofences) {
            this.config.USE_GEOFENCES = useGeofences;
            this.config.POST_GEOFENCES = postGeofences;
            return this;
        }

        public Builder setFused(boolean useFused, boolean postFused) {
            this.config.USE_FUSED_PROVIDER = useFused;
            this.config.POST_FUSED_PROVIDER = postFused;
            return this;
        }

        Builder setFusedAccuracy(int fusedAccuracy) {
            this.config.FUSED_PROVIDER_ACCURACY = fusedAccuracy;
            return this;
        }

        Builder setFusedInterval(int normalMilliseconds, int fastestMilliseconds) {
            this.config.FUSED_PROVIDER_INTERVAL_MS = normalMilliseconds;
            this.config.FUSED_PROVIDER_FASTEST_INTERVAL_MS = fastestMilliseconds;
            return this;
        }

        Builder setFusedDistanceFilter(int meters) {
            this.config.FILTER_DISTANCE_METERS = meters;
            return this;
        }

        Builder setFusedTimeFilter(int milliseconds) {
            this.config.FILTER_TIME_MILLISECONDS = milliseconds;
            return this;
        }

        Builder setFusedSpeedFilter(float fusedSpeedFilter, int deltaMilliseconds) {
            this.config.FILTER_SPEED_METERS_SECOND = fusedSpeedFilter;
            this.config.FILTER_SPEED_DELTA_SECONDS = deltaMilliseconds;
            return this;
        }

        Builder setFusedAccuracyFilter(int meters) {
            this.config.FILTER_ACCURACY_METERS = meters;
            return this;
        }

        Builder setInstantAccuracyFilter(int meters) {
            this.config.INSTANT_FIX_ACCURACY_METERS = meters;
            return this;
        }

        Builder setInstantTimeDeltaFilter(int milliseconds) {
            this.config.INSTANT_FIX_DELTA_SECONDS = milliseconds;
            return this;
        }

        Builder setHomeWifiThreshold(float meters) {
            this.config.HOME_WIFI_THRESHOLD_METERS = meters;
            return this;
        }

        Builder setWakeUpInterval(int milliseconds) {
            this.config.WAKEUP_INTERVAL_MS = milliseconds;
            return this;
        }

        Builder setConfigName(String configName) {
            this.config.CONFIG_NAME = configName;
            return this;
        }

        Builder setBackgroundInterval(int milliseconds) {
            this.config.BACKGROUND_INTERVAL_MS = milliseconds;
            return this;
        }
    }

    public /* bridge */ /* synthetic */ int getFusedAccuracy() {
        return super.getFusedAccuracy();
    }

    public /* bridge */ /* synthetic */ LocationAcquisitionMode getFusedAccuracyAsEnum() {
        return super.getFusedAccuracyAsEnum();
    }

    public /* bridge */ /* synthetic */ int getFusedSpeedDeltaFilter() {
        return super.getFusedSpeedDeltaFilter();
    }

    public /* bridge */ /* synthetic */ float getFusedSpeedFilter() {
        return super.getFusedSpeedFilter();
    }

    public /* bridge */ /* synthetic */ int getInstantAccuracyFilter() {
        return super.getInstantAccuracyFilter();
    }

    public /* bridge */ /* synthetic */ int getInstantTimeDeltaFilterMillis() {
        return super.getInstantTimeDeltaFilterMillis();
    }

    public /* bridge */ /* synthetic */ int getWakeupIntervalMillis() {
        return super.getWakeupIntervalMillis();
    }

    public /* bridge */ /* synthetic */ boolean postFused() {
        return super.postFused();
    }

    public /* bridge */ /* synthetic */ boolean postGeofences() {
        return super.postGeofences();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public /* bridge */ /* synthetic */ boolean useFused() {
        return super.useFused();
    }

    public /* bridge */ /* synthetic */ boolean useGeofences() {
        return super.useGeofences();
    }

    private LocationConfiguration() {
    }

    public LocationConfiguration(GeolocationConfig configuration) {
        this.configuration = configuration;
    }

    @Nullable
    public GeolocationConfig getConfiguration() {
        return this.configuration;
    }

    public String getConfigName() {
        if (this.configuration != null) {
            return this.configuration.getVersion();
        }
        return super.getConfigName();
    }

    public int getFusedDistanceFilter() {
        if (this.configuration != null) {
            return this.configuration.getDistanceFilter();
        }
        return super.getFusedDistanceFilter();
    }

    public int getFusedAccuracyFilter() {
        if (this.configuration != null) {
            return this.configuration.getMaxAccuracy();
        }
        return super.getFusedAccuracyFilter();
    }

    public int getFusedIntervalMillis() {
        if (this.configuration != null) {
            return this.configuration.getProviderUpdateIntervalMillis();
        }
        return super.getFusedIntervalMillis();
    }

    public int getFusedFastestIntervalMillis() {
        if (this.configuration != null) {
            return this.configuration.getProviderUpdateIntervalMillis() / 2;
        }
        return super.getFusedFastestIntervalMillis();
    }

    public int getFusedTimeFilterMillis() {
        if (this.configuration != null) {
            return this.configuration.getMinIntervalBetweenSentUpdatesMillis();
        }
        return super.getFusedTimeFilterMillis();
    }

    public float getHomeWifiThreshold() {
        if (this.configuration != null) {
            return this.configuration.getHomeWifiRadius();
        }
        return super.getHomeWifiThreshold();
    }

    public int getBackgroundIntervalMillis() {
        if (this.configuration != null) {
            return this.configuration.getMinIntervalBetweenBackgroundUpdatesMillis();
        }
        return super.getBackgroundIntervalMillis();
    }

    public List<Float> getRegions() {
        if (this.configuration != null) {
            return this.configuration.getRegions();
        }
        return super.getRegions();
    }

    public static LocationConfiguration getWithConfiguration(GeolocationConfig configuration) {
        if (configuration != null) {
            return new Builder(configuration).build();
        }
        return getDefaultConfig();
    }

    private static LocationConfiguration getDefaultConfig() {
        return new Builder().setConfigName(Bus.DEFAULT_IDENTIFIER).build();
    }

    public static LocationConfiguration getFastestConfig() {
        return new Builder().setConfigName("fastest").setFused(true, true).setGeofencing(true, true).setFusedAccuracy(100).setFusedInterval(DateTimeConstants.MILLIS_PER_MINUTE, DateTimeConstants.MILLIS_PER_MINUTE).setFusedAccuracyFilter(AbstractSpiCall.DEFAULT_TIMEOUT).setFusedDistanceFilter(0).setFusedTimeFilter(0).build();
    }
}
