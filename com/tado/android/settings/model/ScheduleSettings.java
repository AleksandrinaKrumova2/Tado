package com.tado.android.settings.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tado.android.app.TadoApplication;
import com.tado.android.controllers.ZoneController;
import com.tado.android.rest.callback.RetryCallback;
import com.tado.android.rest.callback.presenters.SendingErrorAlertPresenter;
import com.tado.android.rest.model.AwayConfigurationAdapter;
import com.tado.android.rest.model.Block;
import com.tado.android.rest.model.GenericAwayConfiguration;
import com.tado.android.rest.model.Zone;
import com.tado.android.rest.model.ZoneSettingClassAdapter;
import com.tado.android.rest.model.installation.GenericZoneSetting;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.schedule.model.ScheduleDayEnum;
import com.tado.android.schedule.model.ScheduleTypeEnum;
import com.tado.android.settings.interfaces.GenericCallbackListener;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import retrofit2.Call;
import retrofit2.Response;

public class ScheduleSettings {
    private static final String LOAD_ACTIVE_WEEKDAYS_TYPE = "loadActiveWeekdaysType";
    public static final String PREFERENCES = "scheduleSettings";
    public static final String SCHEDULE_PREFERENCES = "schedulePreferences";
    public static final String SELECTED_SCHEDULE_ID = "selectedScheduleId";
    public static final String SELECTED_SCHEDULE_TYPE = "selectedScheduleType";
    private static final String S_BLOCK_ALWAYS_ACTIVE = "alwaysActive";
    private static final String S_BLOCK_DAY_TYPE = "dayType";
    private static final String S_BLOCK_END = "end";
    private static final String S_BLOCK_ID = "blockId";
    private static final String S_BLOCK_START = "start";
    private static final String S_BLOCK_TEMPERATURE_ID = "temperature";
    private static final String S_BLOCK_TYPE = "type";
    private static final String S_BLOCK_WEEKDAYS_TYPE = "weekdaysType";
    public static final String UPDATE_IN_PROGRESS = "updateInProgress";
    static SharedPreferences settings;

    public static void setUpdateInProgress(boolean progress) {
        Editor editor = getSettings().edit();
        editor.putBoolean(UPDATE_IN_PROGRESS, progress);
        editor.apply();
    }

    public static boolean isUpdateInProgress() {
        return getSettings().getBoolean(UPDATE_IN_PROGRESS, false);
    }

    public static void saveAwayConfiguration(int zoneId, GenericAwayConfiguration awayConfiguration) {
        String json = getAwayConfigurationGson().toJson((Object) awayConfiguration);
        Editor editor = getSettings().edit();
        editor.putString(String.valueOf(zoneId), json);
        editor.apply();
    }

    @NonNull
    private static Gson getAwayConfigurationGson() {
        return new GsonBuilder().registerTypeAdapter(GenericAwayConfiguration.class, new AwayConfigurationAdapter()).create();
    }

    private static List<Block> getBlocksFromDay(ScheduleDayEnum day, List<Block> blocks) {
        List<Block> blockFromDay = new ArrayList();
        for (Block block : blocks) {
            if (block.getDayType().equals(day.getDay())) {
                blockFromDay.add(block);
            }
        }
        return blockFromDay;
    }

    public static void saveBlocks(int zoneId, List<Block> blocks) {
        for (int i = 0; i < ScheduleDayEnum.values().length; i++) {
            List<Block> blockList = getBlocksFromDay(ScheduleDayEnum.values()[i], blocks);
            if (!(blockList == null || blockList.size() == 0)) {
                saveBlocks(zoneId, ScheduleDayEnum.values()[i], blockList);
            }
        }
    }

    public static void uploadBlocks(final ScheduleDayEnum day, List<Block> blocks, GenericCallbackListener<List<Block>> callback, Context context) {
        RestServiceGenerator.getTadoRestService().updateDayTypeBlocks(UserConfig.getHomeId(), UserConfig.getCurrentZone().intValue(), getActiveScheduleId(), day.getDay(), RestServiceGenerator.getCredentialsMap(), blocks).enqueue(new RetryCallback<List<Block>>(new SendingErrorAlertPresenter(context), callback) {
            public void onResponse(Call<List<Block>> call, Response<List<Block>> response) {
                if (response.isSuccessful()) {
                    ScheduleSettings.saveBlocks(UserConfig.getCurrentZone().intValue(), day, (List) response.body());
                }
                super.onResponse(call, response);
            }
        });
    }

    private static void saveBlocks(int zoneId, ScheduleDayEnum day, List<Block> blocks) {
        Editor editor = getContext().getSharedPreferences(getKey(zoneId, day), 0).edit();
        editor.clear();
        editor.apply();
        Set<String> ids = new HashSet();
        int i = 0;
        for (Block block : blocks) {
            if (!block.isDeleteCandidate()) {
                int i2 = i + 1;
                block.setId(i + "");
                block.calculate();
                ids.add(block.getId());
                i = i2;
            }
        }
        saveBlocks((List) blocks, editor);
        editor.putStringSet(day.getDay(), ids);
        editor.apply();
    }

    private static String getKey(int zoneId, ScheduleDayEnum day) {
        return String.format("%d_%s", new Object[]{Integer.valueOf(zoneId), day.getDay()});
    }

    public static List<Block> getBlocks(ScheduleDayEnum day) {
        int zoneId = UserConfig.getCurrentZone().intValue();
        Set<String> ids = getContext().getSharedPreferences(getKey(zoneId, day), 0).getStringSet(day.getDay(), new HashSet());
        List<Block> blocks = new ArrayList(ids.size());
        for (String id : ids) {
            blocks.add(getBlock(zoneId, day, id));
        }
        Util.sortBlockList(blocks);
        return blocks;
    }

    private static void saveBlocks(List<Block> blocks, Editor editor) {
        Gson gson = new GsonBuilder().create();
        for (Object block : blocks) {
            editor.putString(block.getId(), gson.toJson(block));
        }
    }

    public static Block getBlock(int zoneId, ScheduleDayEnum day, String id) {
        SharedPreferences preference = getContext().getSharedPreferences(getKey(zoneId, day), 0);
        Gson gson = new GsonBuilder().registerTypeAdapter(GenericZoneSetting.class, new ZoneSettingClassAdapter()).create();
        String json = preference.getString(id, null);
        Block block = new Block();
        if (json != null) {
            block = (Block) gson.fromJson(json, Block.class);
        }
        block.setId(id);
        block.calculate();
        return block;
    }

    public static void setActiveScheduleId(int id) {
        Editor editor = getScheduleSettings().edit();
        editor.putInt(SELECTED_SCHEDULE_ID, id);
        editor.apply();
    }

    public static int getActiveScheduleId() {
        return getScheduleSettings().getInt(SELECTED_SCHEDULE_ID, 0);
    }

    public static void setSelectedScheduleType(ScheduleTypeEnum scheduleType) {
        Editor editor = getScheduleSettings().edit();
        editor.putString(SELECTED_SCHEDULE_TYPE, scheduleType.getType());
        editor.apply();
    }

    public static Context getContext() {
        return TadoApplication.getTadoAppContext();
    }

    private static SharedPreferences getSettings() {
        return getContext().getSharedPreferences(PREFERENCES, 0);
    }

    private static SharedPreferences getScheduleSettings() {
        return getContext().getSharedPreferences(SCHEDULE_PREFERENCES, 0);
    }

    public static void clearData() {
        List<Zone> zones = ZoneController.INSTANCE.getZoneList();
        if (zones != null) {
            for (Zone zone : zones) {
                for (ScheduleDayEnum day : ScheduleDayEnum.values()) {
                    getContext().getSharedPreferences(getKey(zone.getId(), day), 0).edit().clear().apply();
                }
            }
        }
        getScheduleSettings().edit().clear().apply();
        getSettings().edit().clear().apply();
    }
}
