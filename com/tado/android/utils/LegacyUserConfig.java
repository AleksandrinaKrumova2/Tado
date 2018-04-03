package com.tado.android.utils;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LegacyUserConfig {
    private static final String HOME_BSSIDS = "homeBSSIDs";
    private static final String HOME_WIFIS = "homeWifis";
    private static final String IGNORED_WIFIS = "ignoredWifis";
    private SharedPreferences preferences;

    class C12521 extends TypeToken<HashMap<String, String>> {
        C12521() {
        }
    }

    class C12532 extends TypeToken<HashMap<String, String>> {
        C12532() {
        }
    }

    LegacyUserConfig(@NonNull SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public Set<String> getOldBSSIDs() {
        return this.preferences.getStringSet(HOME_BSSIDS, new HashSet());
    }

    public void clearOldBSSIDs() {
        this.preferences.edit().remove(HOME_BSSIDS).apply();
    }

    @NonNull
    public HashMap<String, String> getHomeWifis() {
        HashMap homeWifis = new HashMap();
        if (this.preferences.contains(HOME_WIFIS)) {
            homeWifis.putAll((HashMap) new Gson().fromJson(this.preferences.getString(HOME_WIFIS, "{}"), new C12521().getType()));
        }
        Snitcher.start().log(3, "HomeWifi", "removing %d legacy home wifis", Integer.valueOf(homeWifis.size()));
        return homeWifis;
    }

    public void removeHomeWifis() {
        this.preferences.edit().remove(HOME_WIFIS).commit();
    }

    @NonNull
    public Map<String, String> getIgnoredWifis() {
        Map<String, String> ignoredWifis = new HashMap();
        if (this.preferences.contains(IGNORED_WIFIS)) {
            ignoredWifis = (Map) new Gson().fromJson(this.preferences.getString(IGNORED_WIFIS, "{}"), new C12532().getType());
        }
        Snitcher.start().log(3, "HomeWifi", "removing %d legacy ignored home wifis", Integer.valueOf(ignoredWifis.size()));
        return ignoredWifis;
    }

    public void removeIgnoredWifis() {
        this.preferences.edit().remove(IGNORED_WIFIS).commit();
    }
}
