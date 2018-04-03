package com.tado.android.settings.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.tado.android.app.TadoApplication;
import com.tado.android.rest.model.ScheduleMode;
import com.tado.android.rest.model.Temperature;
import com.tado.android.rest.model.ZoneSetting;
import com.tado.android.utils.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TemperatureSettings {
    private static final String AC_FAN_SPEED = "acFanSpeed";
    private static final String AC_MODE = "acMode";
    private static final String AC_POWER = "acPower";
    private static final String AC_SWING_STATE = "acSwingState";
    private static final String AUTO_ADJUST = "autoAdjust";
    private static final String COLOR = "color";
    private static final String COMFORT_LEVEL = "comfortLevel";
    private static final String ID = "id";
    private static final String IN_USE_BY_BLOCK = "inUseByBlock";
    private static final String NAME = "name";
    private static final String NEW_TEMPERATURE_ID = "newTempId";
    public static final String PREFERENCES = "tempSettings";
    private static final String TADO_MODE = "tadoMode";
    private static final String TEMP = "temp";
    public static final String TEMPERATURE_AT_DAY = "temperatureAtDay";
    public static final String TEMPERATURE_AT_NIGHT = "temperatureAtNight";
    private static final String TYPE = "type";
    private static final String VALUE = "value";
    static SharedPreferences settings;

    public static ScheduleMode getTemperature(int id, boolean getAddedTemperature) {
        settings = getContext().getSharedPreferences(PREFERENCES, 0);
        int tempId = settings.getInt(NEW_TEMPERATURE_ID, -1);
        if (!getAddedTemperature || tempId == -1) {
            tempId = id;
        }
        return getTemperature(tempId);
    }

    private static String generateId(String key, int id) {
        return key + id;
    }

    public static ScheduleMode getTemperature(int id) {
        settings = getContext().getSharedPreferences(PREFERENCES, 0);
        ScheduleMode scheduleMode = new ScheduleMode();
        scheduleMode.setId(Integer.valueOf(id));
        scheduleMode.setName(settings.getString(generateId("name", id), ""));
        scheduleMode.setType(settings.getString(generateId(TYPE, id), ""));
        scheduleMode.setTadoMode(settings.getString(generateId(TADO_MODE, id), ""));
        scheduleMode.setInUseByBlock(Boolean.valueOf(settings.getBoolean(generateId(IN_USE_BY_BLOCK, id), true)));
        if (settings.contains(generateId(AUTO_ADJUST, id))) {
            scheduleMode.setAutoAdjust(Boolean.valueOf(settings.getBoolean(generateId(AUTO_ADJUST, id), false)));
        }
        if (settings.contains(generateId(COMFORT_LEVEL, id))) {
            scheduleMode.setComfortLevel(Integer.valueOf(settings.getInt(generateId(COMFORT_LEVEL, id), 0)));
        }
        ZoneSetting setting = new ZoneSetting();
        if (settings.contains(generateId(AC_FAN_SPEED, id))) {
            setting.setFanSpeed(settings.getString(generateId(AC_FAN_SPEED, id), ""));
        }
        if (settings.contains(generateId(AC_MODE, id))) {
            setting.setMode(settings.getString(generateId(AC_MODE, id), ""));
        }
        if (settings.contains(generateId(AC_SWING_STATE, id))) {
            setting.setSwing(settings.getString(generateId(AC_SWING_STATE, id), ""));
        }
        setting.setPower(settings.getString(generateId(AC_POWER, id), ""));
        if (setting.getPowerBoolean()) {
            setting.setTemperature(Temperature.fromValue(settings.getFloat(generateId("value", id), 0.0f)));
        }
        scheduleMode.setZoneSetting(setting);
        return scheduleMode;
    }

    public static List<ScheduleMode> getTemperatures() {
        settings = getContext().getSharedPreferences(PREFERENCES, 0);
        List<ScheduleMode> scheduleModes = new ArrayList();
        Set<String> ids = settings.getStringSet(TEMP, null);
        if (ids != null) {
            for (String id : ids) {
                scheduleModes.add(getTemperature(Integer.valueOf(id).intValue()));
            }
            Util.sortTemperatureList(scheduleModes);
        }
        return scheduleModes;
    }

    public static void removeTemperatures() {
        settings = getContext().getSharedPreferences(PREFERENCES, 0);
        Editor editor = settings.edit();
        editor.clear();
        editor.apply();
    }

    public static Context getContext() {
        return TadoApplication.getTadoAppContext();
    }
}
