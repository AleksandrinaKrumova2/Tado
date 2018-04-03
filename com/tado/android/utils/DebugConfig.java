package com.tado.android.utils;

import android.content.SharedPreferences;
import com.tado.android.app.TadoApplication;
import java.util.concurrent.TimeUnit;
import retrofit2.mock.NetworkBehavior;

public class DebugConfig {
    public static final String KEY_LOCATION_NOTIFICATION = "locationNotification";
    public static final String KEY_NETWORK_BEHAVIOR = "networkBehavior";
    public static final String KEY_PREMIUM = "premiumEnabled";
    public static final String KEY_USE_FUSED = "fusedEnabled";
    public static final String KEY_USE_GEOFENCES = "geofencingEnabled";
    public static final int NORMAL_NETWORK_BEHAVIOR = 0;
    public static final String PREFERENCES = "debugPreferences";
    public static final int SLOW_NETWORK_BEHAVIOR = 1;
    public static final int TIMEOUT_NETWORK_BEHAVIOR = 2;

    private static NetworkBehavior getSlowNetworkBehavior() {
        NetworkBehavior behavior = NetworkBehavior.create();
        behavior.setDelay(7000, TimeUnit.MILLISECONDS);
        return behavior;
    }

    private static NetworkBehavior getTimeoutNetworkBehavior() {
        NetworkBehavior behavior = NetworkBehavior.create();
        behavior.setDelay(3000, TimeUnit.MILLISECONDS);
        behavior.setFailurePercent(100);
        return behavior;
    }

    private static NetworkBehavior getDefaultNetworkBehavior() {
        NetworkBehavior behavior = NetworkBehavior.create();
        behavior.setDelay(0, TimeUnit.MILLISECONDS);
        return behavior;
    }

    public static String getNetworkBehaviorValue() {
        return getPreferences().getString(KEY_NETWORK_BEHAVIOR, String.valueOf(0));
    }

    public static NetworkBehavior getNetworkBehavior(int behavior) {
        switch (behavior) {
            case 0:
                return getDefaultNetworkBehavior();
            case 1:
                return getSlowNetworkBehavior();
            case 2:
                return getTimeoutNetworkBehavior();
            default:
                return getDefaultNetworkBehavior();
        }
    }

    public static NetworkBehavior getNetworkBehavior() {
        return getNetworkBehavior(Integer.parseInt(getNetworkBehaviorValue()));
    }

    public static boolean isLocationNotificationEnabled() {
        return getPreferences().getBoolean(KEY_LOCATION_NOTIFICATION, false);
    }

    public static void setLocationNotificationEnabled(boolean enabled) {
        getPreferences().edit().putBoolean(KEY_LOCATION_NOTIFICATION, enabled).apply();
    }

    private static SharedPreferences getPreferences() {
        return TadoApplication.getTadoAppContext().getSharedPreferences(PREFERENCES, 0);
    }

    public static boolean isGeofencingEnabled() {
        return getPreferences().getBoolean(KEY_USE_GEOFENCES, true);
    }

    public static boolean isFusedEnabled() {
        return getPreferences().getBoolean(KEY_USE_FUSED, false);
    }
}
