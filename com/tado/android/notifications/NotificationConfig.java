package com.tado.android.notifications;

import android.content.SharedPreferences;
import com.tado.android.app.TadoApplication;

public class NotificationConfig {
    private static final String PREFERENCES = "NotificationPreferences";

    private static SharedPreferences getPreferences() {
        return TadoApplication.getTadoAppContext().getSharedPreferences(PREFERENCES, 0);
    }

    static void setBoolean(String key, boolean value) {
        getPreferences().edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(String key) {
        return getPreferences().getBoolean(key, false);
    }
}
