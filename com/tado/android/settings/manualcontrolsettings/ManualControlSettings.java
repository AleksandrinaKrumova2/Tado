package com.tado.android.settings.manualcontrolsettings;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.tado.android.app.TadoApplication;

public class ManualControlSettings {
    public static final String HOME_BUTTON_MANUAL_CONTROL_ENABLED = "homeButtonManualControlEnabled";
    public static final String MANUAL_CONTROL_ENABLED = "ManualControlEnabled";
    public static final String MANUAL_PICKER_EXPANDED = "manualPickerExpanded";
    public static final String PREFERENCES = "mysettings";
    public static final String PREFERENCE_OVERLAY_TERMINATION_CONDITION = "OVERLAY_MODE.%d.%d";
    public static final String PREFERENCE_TIMER_TERMINATION_CONDITION = "TIMER.%d.%d";
    public static final String TEMPERATURE = "temperatureManualControl1";
    static SharedPreferences settings;

    public static boolean isManualControlEnabled() {
        settings = getContext().getSharedPreferences(PREFERENCES, 0);
        return settings.getBoolean(MANUAL_CONTROL_ENABLED, false);
    }

    public static void setManualControlEnabled(boolean manualControlEnabled) {
        settings = getContext().getSharedPreferences(PREFERENCES, 0);
        Editor editor = settings.edit();
        editor.putBoolean(MANUAL_CONTROL_ENABLED, manualControlEnabled);
        editor.apply();
    }

    public static String getTemperature() {
        settings = getContext().getSharedPreferences(PREFERENCES, 0);
        return settings.getString(TEMPERATURE, "5");
    }

    public static void setTemperature(String temperature) {
        settings = getContext().getSharedPreferences(PREFERENCES, 0);
        Editor editor = settings.edit();
        editor.putString(TEMPERATURE, temperature);
        editor.apply();
    }

    public static Context getContext() {
        return TadoApplication.getTadoAppContext();
    }
}
