package com.tado.android.demo;

import android.content.SharedPreferences;
import com.tado.android.app.TadoApplication;

public class DemoConfig {
    public static final String DEMO_START_COUNT = "demoStartCount";
    private static final String PREFERENCES = "DemoPreferences";

    private static SharedPreferences getPreferences() {
        return TadoApplication.getTadoAppContext().getSharedPreferences(PREFERENCES, 0);
    }

    public static int getStartCounter() {
        int currentCounter = getPreferences().getInt(DEMO_START_COUNT, 0) + 1;
        getPreferences().edit().putInt(DEMO_START_COUNT, currentCounter).apply();
        return currentCounter;
    }
}
