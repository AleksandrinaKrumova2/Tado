package com.tado.android.location;

import java.util.List;

public interface ILocationConfiguration {
    int getBackgroundIntervalMillis();

    String getConfigName();

    int getFusedAccuracy();

    int getFusedAccuracyFilter();

    int getFusedDistanceFilter();

    int getFusedFastestIntervalMillis();

    int getFusedIntervalMillis();

    int getFusedSpeedDeltaFilter();

    float getFusedSpeedFilter();

    int getFusedTimeFilterMillis();

    float getHomeWifiThreshold();

    int getInstantAccuracyFilter();

    int getInstantTimeDeltaFilterMillis();

    List<Float> getRegions();

    int getWakeupIntervalMillis();

    boolean postFused();

    boolean postGeofences();

    boolean useFused();

    boolean useGeofences();
}
