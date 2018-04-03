package com.tado.android.control_panel;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.tado.android.app.TadoApplication;
import com.tado.android.rest.model.ZoneOverlay;
import com.tado.android.rest.model.ZoneSetting;
import com.tado.android.utils.Snitcher;

public enum OverlaySettingSeriliazer implements OverlaySettingSeriliazerInterface {
    INSTANCE;
    
    private static final String PREFERENCES = "overlaySettings";
    private static String lastSavedMode;
    private static int lastSavedZoneId;
    private static Editor sharedPreferenceEditor;
    private static SharedPreferences sharedPreferences;

    static {
        lastSavedMode = null;
        lastSavedZoneId = -1;
    }

    private SharedPreferences getSharedPreferences() {
        if (sharedPreferences == null) {
            sharedPreferences = TadoApplication.getTadoAppContext().getSharedPreferences(PREFERENCES, 0);
        }
        return sharedPreferences;
    }

    private Editor getSharedPreferenceEditor() {
        if (sharedPreferenceEditor == null) {
            sharedPreferenceEditor = getSharedPreferences().edit();
        }
        return sharedPreferenceEditor;
    }

    public void save(int zoneId, ZoneOverlay overlay) {
        if (!(overlay == null || overlay.getTermination() == null || overlay.getTermination().getDurationInSeconds() == null)) {
            int duration = overlay.getTermination().getDurationInSeconds().intValue();
            if (duration < 60) {
                overlay.getTermination().setDurationInSeconds(Integer.valueOf(60));
            } else if (duration % 60 < 30) {
                overlay.getTermination().setDurationInSeconds(Integer.valueOf(duration - (duration % 60)));
            } else {
                overlay.getTermination().setDurationInSeconds(Integer.valueOf((duration + 60) - (duration % 60)));
            }
        }
        if (overlay.getSetting().getMode() == null && !overlay.getSetting().getPowerBoolean()) {
            overlay.getSetting().setMode(lastSavedMode);
        }
        Gson gson = new GsonBuilder().create();
        String jsonZoneOverlay = gson.toJson((Object) overlay);
        String jsonZoneSetting = gson.toJson(overlay.getSetting());
        getSharedPreferenceEditor().putString(String.format("%d", new Object[]{Integer.valueOf(zoneId)}), jsonZoneOverlay);
        getSharedPreferenceEditor().putString(String.format("%d_%s", new Object[]{Integer.valueOf(zoneId), overlay.getSetting().getMode()}), jsonZoneSetting);
        getSharedPreferenceEditor().apply();
    }

    public ZoneOverlay restore(int zoneId) {
        String jsonZoneOverlay = getSharedPreferences().getString(String.valueOf(zoneId), "");
        if (jsonZoneOverlay.isEmpty()) {
            return null;
        }
        try {
            ZoneOverlay zoneOverlay = (ZoneOverlay) new GsonBuilder().create().fromJson(jsonZoneOverlay, ZoneOverlay.class);
            if (!(zoneOverlay == null || zoneOverlay.getSetting() == null)) {
                if (zoneOverlay.getSetting().getType() != null && zoneOverlay.getSetting().getType().equalsIgnoreCase("hotwater")) {
                    zoneOverlay.getSetting().setType("HOT_WATER");
                }
                if (zoneOverlay.getSetting().getMode() != null && zoneOverlay.getSetting().getType().equalsIgnoreCase("hotwater")) {
                    zoneOverlay.getSetting().setMode("HOT_WATER");
                }
            }
            lastSavedMode = zoneOverlay.getSetting().getMode();
            lastSavedZoneId = zoneId;
            return zoneOverlay;
        } catch (JsonSyntaxException ex) {
            Snitcher.start().toCrashlytics().logException(OverlaySettingSeriliazer.class.getCanonicalName(), ex);
            return null;
        }
    }

    public ZoneSetting restoreZoneSetting(int zoneId, String mode) {
        String jsonZoneSetting = getSharedPreferences().getString(String.format("%d_%s", new Object[]{Integer.valueOf(zoneId), mode}), "");
        if (jsonZoneSetting.isEmpty()) {
            return null;
        }
        try {
            return (ZoneSetting) new GsonBuilder().create().fromJson(jsonZoneSetting, ZoneSetting.class);
        } catch (JsonSyntaxException e) {
            Snitcher.start().toCrashlytics().logException(OverlaySettingSeriliazer.class.getCanonicalName(), e);
            return null;
        }
    }

    public String restoreLastOverlaySettingMode(int zoneId) {
        if (lastSavedMode == null || zoneId != lastSavedZoneId) {
            restore(zoneId);
        }
        return lastSavedMode;
    }

    public void clearZone(int zoneId) {
        ControlPanelController.INSTANCE.cleanZone(zoneId);
        getSharedPreferenceEditor().remove(String.valueOf(zoneId));
        getSharedPreferenceEditor().remove(String.format("%d_%s", new Object[]{Integer.valueOf(zoneId), "AUTO"}));
        getSharedPreferenceEditor().remove(String.format("%d_%s", new Object[]{Integer.valueOf(zoneId), ZoneSetting.MODE_COOL}));
        getSharedPreferenceEditor().remove(String.format("%d_%s", new Object[]{Integer.valueOf(zoneId), ZoneSetting.MODE_DRY}));
        getSharedPreferenceEditor().remove(String.format("%d_%s", new Object[]{Integer.valueOf(zoneId), ZoneSetting.MODE_FAN}));
        getSharedPreferenceEditor().remove(String.format("%d_%s", new Object[]{Integer.valueOf(zoneId), ZoneSetting.MODE_HEAT}));
        getSharedPreferenceEditor().remove(String.format("%d_%s", new Object[]{Integer.valueOf(zoneId), "HEATING"}));
        getSharedPreferenceEditor().remove(String.format("%d_%s", new Object[]{Integer.valueOf(zoneId), "HOT_WATER"}));
        getSharedPreferenceEditor().apply();
    }

    public void clear() {
        ControlPanelController.INSTANCE.clean();
        getSharedPreferenceEditor().clear();
        getSharedPreferenceEditor().apply();
    }

    public static void setLastSavedMode(String lastSavedMode, int lastSavedZoneId) {
        lastSavedMode = lastSavedMode;
        lastSavedZoneId = lastSavedZoneId;
    }
}
