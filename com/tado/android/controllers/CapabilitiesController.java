package com.tado.android.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.util.SparseArray;
import com.google.gson.GsonBuilder;
import com.tado.android.app.TadoApplication;
import com.tado.android.control_panel.OverlaySettingSeriliazer;
import com.tado.android.report.ChartUtils;
import com.tado.android.rest.model.CoolingModeCapabilities;
import com.tado.android.rest.model.SensorDataPoint;
import com.tado.android.rest.model.Temperature;
import com.tado.android.rest.model.Zone.TypeEnum;
import com.tado.android.rest.model.ZoneCapabilities;
import com.tado.android.rest.model.installation.FanSpeedEnum;
import com.tado.android.rest.model.installation.GenericZoneSetting;
import com.tado.android.rest.model.installation.SwingEnum;
import com.tado.android.rest.model.installation.TemperatureUnitEnum;
import com.tado.android.times.view.model.ModeEnum;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import com.tado.android.views.TadoZoneSettingViewConfiguration;
import com.tado.android.views.TadoZoneSettingViewModeConfiguration;
import com.tado.android.views.TadoZoneSettingViewModeConfiguration.Builder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum CapabilitiesController {
    INSTANCE;
    
    public static final String AUTO = "auto";
    public static final String COOL = "cool";
    public static final String CURRENT_CAPABILITIES_HASH = "currentCapabiitiesHash";
    public static final String DRY = "dry";
    public static final String FAN = "fan";
    public static final String HEAT = "heat";
    public static final String HEATING = "heating";
    public static final String HOT_WATER = "hotWater";
    public static final String PREFERENCES = "acsettings";
    private static SharedPreferences settings;
    private SparseArray<ZoneCapabilities> mZoneCapabilitiesMap;

    private static Context getContext() {
        return TadoApplication.getTadoAppContext();
    }

    private static SharedPreferences getSettings() {
        if (settings == null) {
            settings = getContext().getSharedPreferences(PREFERENCES, 0);
        }
        return settings;
    }

    private boolean isModeTemperaturePresent(CoolingModeCapabilities modeCapabilities) {
        return (modeCapabilities == null || modeCapabilities.getTemperatures() == null) ? false : true;
    }

    public boolean isCool() {
        return getCurrentZoneCapabilities().getCool() != null;
    }

    public boolean isDry() {
        return getCurrentZoneCapabilities().getDry() != null;
    }

    public boolean isFan() {
        return getCurrentZoneCapabilities().getFan() != null;
    }

    public boolean isAuto() {
        return getCurrentZoneCapabilities().getAuto() != null;
    }

    public boolean isHeat() {
        return getCurrentZoneCapabilities().getHeat() != null;
    }

    public boolean isHotWaterZone() {
        return getCurrentZoneCapabilities().isHotWaterZone();
    }

    public boolean isHeatingZone() {
        return getCurrentZoneCapabilities().isHeatingZone();
    }

    public boolean isCoolingZone() {
        return getCurrentZoneCapabilities().isCoolingZone();
    }

    public boolean canSetCurrentZoneHotWaterTemperature() {
        return getCurrentZoneCapabilities().isCanSetTemperature();
    }

    public boolean canSetHotWaterTemperature(int zoneId) {
        if (this.mZoneCapabilitiesMap == null || this.mZoneCapabilitiesMap.get(zoneId) == null) {
            return false;
        }
        return ((ZoneCapabilities) this.mZoneCapabilitiesMap.get(zoneId)).isCanSetTemperature();
    }

    public void addCapabilities(int zoneId, ZoneCapabilities capabilities) {
        checkAndInitCapabilitiesMap();
        this.mZoneCapabilitiesMap.put(zoneId, capabilities);
        persistZoneCapabilities(zoneId, capabilities);
        checkOrInvalidatePersistedOverlays(zoneId, capabilities);
    }

    private void persistZoneCapabilities(int zoneId, ZoneCapabilities capabilities) {
        UserConfig.addCapabilities(String.valueOf(zoneId), new GsonBuilder().create().toJson((Object) capabilities));
    }

    private void checkAndInitCapabilitiesMap() {
        if (this.mZoneCapabilitiesMap == null) {
            this.mZoneCapabilitiesMap = new SparseArray();
        }
    }

    private ZoneCapabilities getCurrentZoneCapabilities() {
        checkAndInitCapabilitiesMap();
        if (this.mZoneCapabilitiesMap.get(UserConfig.getCurrentZone().intValue()) != null) {
            return (ZoneCapabilities) this.mZoneCapabilitiesMap.get(UserConfig.getCurrentZone().intValue());
        }
        String capabilityJson = UserConfig.getCapabilities(UserConfig.getCurrentZone().toString());
        if (capabilityJson != null) {
            return (ZoneCapabilities) new GsonBuilder().create().fromJson(capabilityJson, ZoneCapabilities.class);
        }
        return new ZoneCapabilities();
    }

    public void checkOrInvalidatePersistedOverlays(int zoneId, ZoneCapabilities capabilities) {
        if (capabilities != null) {
            int hash = UserConfig.getInt(CURRENT_CAPABILITIES_HASH + zoneId, -1);
            Editor editor = getSettings().edit();
            editor.clear();
            editor.apply();
            int newHash = capabilities.hashCode();
            UserConfig.putInteger(CURRENT_CAPABILITIES_HASH + zoneId, newHash);
            editor.apply();
            if (newHash != hash) {
                OverlaySettingSeriliazer.INSTANCE.clearZone(zoneId);
            }
        }
    }

    public boolean isAcTemperaturePresent() {
        return isCoolTemperaturePresent() || isHeatTemperaturePresent();
    }

    public boolean isAcFanPresent() {
        return isFanSpeedPresent(ModeEnum.COOL) || isFanSpeedPresent(ModeEnum.FAN) || isFanSpeedPresent(ModeEnum.DRY) || isFanSpeedPresent(ModeEnum.HEAT) || isFanSpeedPresent(ModeEnum.AUTO);
    }

    public boolean isAcSwingPresent() {
        return isSwingControlPresent(ModeEnum.COOL) || isSwingControlPresent(ModeEnum.FAN) || isSwingControlPresent(ModeEnum.DRY) || isSwingControlPresent(ModeEnum.HEAT) || isSwingControlPresent(ModeEnum.AUTO);
    }

    public int getControlsHeight() {
        int numOfControls = 0;
        if (isAcTemperaturePresent()) {
            numOfControls = 0 + 1;
        }
        if (isAcFanPresent()) {
            numOfControls++;
        }
        if (isAcSwingPresent()) {
            numOfControls++;
        }
        int height = 0;
        switch (numOfControls) {
            case 0:
                height = 0;
                break;
            case 1:
                height = 110;
                break;
            case 2:
                height = 150;
                break;
            case 3:
                height = 195;
                break;
        }
        return (int) ChartUtils.getDIPValue((float) height);
    }

    public boolean isTemperaturePresent(ModeEnum mode) {
        if (mode == ModeEnum.COOL) {
            return isCoolTemperaturePresent();
        }
        if (mode == ModeEnum.HEAT) {
            return isHeatTemperaturePresent();
        }
        if (mode == ModeEnum.HEATING) {
            return isHeatingTemperaturePresent();
        }
        if (mode == ModeEnum.HOT_WATER) {
            return canSetCurrentZoneHotWaterTemperature();
        }
        return false;
    }

    public boolean isCoolTemperaturePresent() {
        return (getCurrentZoneCapabilities().getCool() == null || getCurrentZoneCapabilities().getCool().getTemperatures() == null) ? false : true;
    }

    public boolean isHeatTemperaturePresent() {
        return (getCurrentZoneCapabilities().getHeat() == null || getCurrentZoneCapabilities().getHeat().getTemperatures() == null) ? false : true;
    }

    public boolean isHeatingTemperaturePresent() {
        return isHeatingZone() && getCurrentZoneCapabilities().getTemperatures() != null;
    }

    public float getModeTemperatureStep(ModeEnum mode, TypeEnum type) {
        if (ModeEnum.COOL == mode && isModeTemperaturePresent(getCurrentZoneCapabilities().getCool())) {
            return getCurrentZoneCapabilities().getCool().getTemperatures().getStep();
        }
        if (ModeEnum.HEAT == mode && isModeTemperaturePresent(getCurrentZoneCapabilities().getHeat())) {
            return getCurrentZoneCapabilities().getHeat().getTemperatures().getStep();
        }
        if (ModeEnum.AUTO == mode && isModeTemperaturePresent(getCurrentZoneCapabilities().getAuto())) {
            return getCurrentZoneCapabilities().getAuto().getTemperatures().getStep();
        }
        if (ModeEnum.FAN == mode && isModeTemperaturePresent(getCurrentZoneCapabilities().getFan())) {
            return getCurrentZoneCapabilities().getFan().getTemperatures().getStep();
        }
        if (ModeEnum.DRY == mode && isModeTemperaturePresent(getCurrentZoneCapabilities().getDry())) {
            return getCurrentZoneCapabilities().getDry().getTemperatures().getStep();
        }
        if ((TypeEnum.HEATING == type || TypeEnum.HOT_WATER == type) && getCurrentZoneCapabilities().getTemperatures() != null) {
            return getCurrentZoneCapabilities().getTemperatures().getStep();
        }
        return 1.0f;
    }

    public float getZoneTypeTemperatureStep(GenericZoneSetting.TypeEnum type) {
        switch (type) {
            case AIR_CONDITIONING:
                if (isModeTemperaturePresent(getCurrentZoneCapabilities().getCool())) {
                    return getCurrentZoneCapabilities().getCool().getTemperatures().getStep();
                }
                break;
            case HEATING:
                return getCurrentZoneCapabilities().getTemperatures().getStep();
            case HOT_WATER:
                if (canSetCurrentZoneHotWaterTemperature()) {
                    return getCurrentZoneCapabilities().getTemperatures().getStep();
                }
                break;
        }
        return 1.0f;
    }

    public int getTemperatureMin(ModeEnum mode) {
        if (ModeEnum.COOL == mode && isModeTemperaturePresent(getCurrentZoneCapabilities().getCool())) {
            return (int) getCurrentZoneCapabilities().getCool().getTemperatures().getMinTemperature();
        }
        if (ModeEnum.HEAT == mode && isModeTemperaturePresent(getCurrentZoneCapabilities().getHeat())) {
            return (int) getCurrentZoneCapabilities().getHeat().getTemperatures().getMinTemperature();
        }
        if (ModeEnum.AUTO == mode) {
            return (int) getCurrentZoneCapabilities().getAuto().getTemperatures().getMinTemperature();
        }
        if (ModeEnum.FAN == mode) {
            return (int) getCurrentZoneCapabilities().getFan().getTemperatures().getMinTemperature();
        }
        if (ModeEnum.DRY == mode) {
            return (int) getCurrentZoneCapabilities().getDry().getTemperatures().getMinTemperature();
        }
        if ((ModeEnum.HEATING == mode || ModeEnum.HOT_WATER == mode) && getCurrentZoneCapabilities().getTemperatures() != null) {
            return (int) getCurrentZoneCapabilities().getTemperatures().getMinTemperature();
        }
        return getMinTemperature();
    }

    public int getTemperatureMax(ModeEnum mode) {
        if (ModeEnum.COOL == mode && isModeTemperaturePresent(getCurrentZoneCapabilities().getCool())) {
            return (int) getCurrentZoneCapabilities().getCool().getTemperatures().getMaxTemperature();
        }
        if (ModeEnum.HEAT == mode && isModeTemperaturePresent(getCurrentZoneCapabilities().getHeat())) {
            return (int) getCurrentZoneCapabilities().getHeat().getTemperatures().getMaxTemperature();
        }
        if (ModeEnum.AUTO == mode && isModeTemperaturePresent(getCurrentZoneCapabilities().getAuto())) {
            return (int) getCurrentZoneCapabilities().getAuto().getTemperatures().getMaxTemperature();
        }
        if (ModeEnum.FAN == mode && isModeTemperaturePresent(getCurrentZoneCapabilities().getFan())) {
            return (int) getCurrentZoneCapabilities().getFan().getTemperatures().getMaxTemperature();
        }
        if (ModeEnum.DRY == mode && isModeTemperaturePresent(getCurrentZoneCapabilities().getDry())) {
            return (int) getCurrentZoneCapabilities().getDry().getTemperatures().getMaxTemperature();
        }
        if (ModeEnum.HEATING == mode || ModeEnum.HOT_WATER == mode) {
            return (int) getCurrentZoneCapabilities().getTemperatures().getMaxTemperature();
        }
        return getMaxTemperature();
    }

    public boolean isFanSpeedPresent(ModeEnum mode) {
        if (ModeEnum.COOL == mode && isCool()) {
            if (getCurrentZoneCapabilities().getCool().getFanSpeeds() != null) {
                return true;
            }
            return false;
        } else if (ModeEnum.HEAT == mode && isHeat()) {
            if (getCurrentZoneCapabilities().getHeat().getFanSpeeds() == null) {
                return false;
            }
            return true;
        } else if (ModeEnum.AUTO == mode && isAuto()) {
            if (getCurrentZoneCapabilities().getAuto().getFanSpeeds() == null) {
                return false;
            }
            return true;
        } else if (ModeEnum.FAN == mode && isFan()) {
            if (getCurrentZoneCapabilities().getFan().getFanSpeeds() == null) {
                return false;
            }
            return true;
        } else if (ModeEnum.DRY != mode || !isDry()) {
            return false;
        } else {
            if (getCurrentZoneCapabilities().getDry().getFanSpeeds() == null) {
                return false;
            }
            return true;
        }
    }

    public boolean isSwingControlPresent(ModeEnum mode) {
        if (ModeEnum.COOL == mode && isCool()) {
            if (getCurrentZoneCapabilities().getCool().getSwings() != null) {
                return true;
            }
            return false;
        } else if (ModeEnum.HEAT == mode && isHeat()) {
            if (getCurrentZoneCapabilities().getHeat().getSwings() == null) {
                return false;
            }
            return true;
        } else if (ModeEnum.AUTO == mode && isAuto()) {
            if (getCurrentZoneCapabilities().getAuto().getSwings() == null) {
                return false;
            }
            return true;
        } else if (ModeEnum.FAN == mode && isFan()) {
            if (getCurrentZoneCapabilities().getFan().getSwings() == null) {
                return false;
            }
            return true;
        } else if (ModeEnum.DRY != mode || !isDry()) {
            return false;
        } else {
            if (getCurrentZoneCapabilities().getDry().getSwings() == null) {
                return false;
            }
            return true;
        }
    }

    public String[] getFanSpeedValues(ModeEnum mode) {
        List<String> fanSpeedsList = null;
        if (ModeEnum.COOL == mode && isFanSpeedPresent(mode)) {
            fanSpeedsList = getCurrentZoneCapabilities().getCool().getFanSpeeds();
        } else if (ModeEnum.HEAT == mode && isFanSpeedPresent(mode)) {
            fanSpeedsList = getCurrentZoneCapabilities().getHeat().getFanSpeeds();
        } else if (ModeEnum.AUTO == mode && isFanSpeedPresent(mode)) {
            fanSpeedsList = getCurrentZoneCapabilities().getAuto().getFanSpeeds();
        } else if (ModeEnum.FAN == mode && isFanSpeedPresent(mode)) {
            fanSpeedsList = getCurrentZoneCapabilities().getFan().getFanSpeeds();
        } else if (ModeEnum.DRY == mode && isFanSpeedPresent(mode)) {
            fanSpeedsList = getCurrentZoneCapabilities().getDry().getFanSpeeds();
        }
        if (fanSpeedsList == null) {
            return null;
        }
        fanSpeedsList = sortFanSpeedValues(fanSpeedsList);
        return (String[]) fanSpeedsList.toArray(new String[fanSpeedsList.size()]);
    }

    public boolean isCelsiusTemperatureUnit() {
        return getTemperatureUnitEnum() == TemperatureUnitEnum.CELSIUS;
    }

    private TemperatureUnitEnum getTemperatureUnitEnum() {
        return UserConfig.getTemperatureUnit();
    }

    private int getMinTemperature() {
        if (isCelsiusTemperatureUnit()) {
            return getCurrentZoneCapabilities().getDefaultModeCapabilities().getTemperatures().getCelsius().getMin();
        }
        return getCurrentZoneCapabilities().getDefaultModeCapabilities().getTemperatures().getFahrenheit().getMin();
    }

    private int getMaxTemperature() {
        if (isCelsiusTemperatureUnit()) {
            return getCurrentZoneCapabilities().getDefaultModeCapabilities().getTemperatures().getCelsius().getMax();
        }
        return getCurrentZoneCapabilities().getDefaultModeCapabilities().getTemperatures().getFahrenheit().getMax();
    }

    public List<String> sortFanSpeedValues(List<String> fanSpeedValues) {
        List<String> sortedFanSpeedValues = new ArrayList();
        for (String item : Arrays.asList(new String[]{"AUTO", "LOW", "MIDDLE", "HIGH"})) {
            if (fanSpeedValues.contains(item)) {
                sortedFanSpeedValues.add(item);
            }
        }
        return sortedFanSpeedValues;
    }

    public List<FanSpeedEnum> sortFanSpeedValuesV2(List<FanSpeedEnum> fanSpeedValues) {
        List<FanSpeedEnum> sortedFanSpeedValues = new ArrayList();
        for (FanSpeedEnum item : Arrays.asList(new FanSpeedEnum[]{FanSpeedEnum.AUTO, FanSpeedEnum.LOW, FanSpeedEnum.MIDDLE, FanSpeedEnum.HIGH})) {
            if (fanSpeedValues.contains(item)) {
                sortedFanSpeedValues.add(item);
            }
        }
        return sortedFanSpeedValues;
    }

    public String getDefaultMode() {
        if (isCool()) {
            return ModeEnum.COOL.getMode();
        }
        if (isDry()) {
            return ModeEnum.DRY.getMode();
        }
        if (isFan()) {
            return ModeEnum.FAN.getMode();
        }
        if (isAuto()) {
            return ModeEnum.AUTO.getMode();
        }
        if (isHeat()) {
            return ModeEnum.HEAT.getMode();
        }
        if (isHeatingZone()) {
            return ModeEnum.HEATING.getMode();
        }
        if (isHotWaterZone()) {
            return ModeEnum.HOT_WATER.getMode();
        }
        return null;
    }

    public void clear() {
        Editor editor = getSettings().edit();
        editor.clear();
        editor.apply();
        if (this.mZoneCapabilitiesMap != null) {
            this.mZoneCapabilitiesMap.clear();
        }
    }

    public boolean isZoneCapabilitiesAvailable(int id) {
        return (this.mZoneCapabilitiesMap == null || this.mZoneCapabilitiesMap.get(id) == null) ? false : true;
    }

    public boolean isCoolingZone(int zoneId) {
        if (isZoneCapabilitiesAvailable(zoneId)) {
            return ((ZoneCapabilities) this.mZoneCapabilitiesMap.get(zoneId)).getType().equalsIgnoreCase("AIR_CONDITIONING");
        }
        return false;
    }

    public TadoZoneSettingViewConfiguration getZoneSettingViewConfiguration() {
        TadoZoneSettingViewConfiguration viewConfiguration = new TadoZoneSettingViewConfiguration();
        ZoneCapabilities capabilities = getCurrentZoneCapabilities();
        viewConfiguration.setZoneType(capabilities.getType());
        viewConfiguration.setZoneId(ZoneController.INSTANCE.getCurrentZoneId());
        if (capabilities != null) {
            if (capabilities.isHeatingZone()) {
                viewConfiguration.putModeConfig(ModeEnum.HEATING, getZoneViewConfigForMode(capabilities, ModeEnum.HEATING), isHeatingTemperaturePresent());
                viewConfiguration.setDefaultMode(ModeEnum.HEATING);
            } else if (capabilities.isHotWaterZone()) {
                viewConfiguration.putModeConfig(ModeEnum.HOT_WATER, getZoneViewConfigForMode(capabilities, ModeEnum.HOT_WATER), canSetCurrentZoneHotWaterTemperature());
                viewConfiguration.setDefaultMode(ModeEnum.HOT_WATER);
            } else {
                viewConfiguration.putModeConfig(ModeEnum.COOL, getZoneViewConfigForMode(capabilities, ModeEnum.COOL), isCool());
                viewConfiguration.putModeConfig(ModeEnum.FAN, getZoneViewConfigForMode(capabilities, ModeEnum.FAN), isFan());
                viewConfiguration.putModeConfig(ModeEnum.DRY, getZoneViewConfigForMode(capabilities, ModeEnum.DRY), isDry());
                viewConfiguration.putModeConfig(ModeEnum.AUTO, getZoneViewConfigForMode(capabilities, ModeEnum.AUTO), isAuto());
                viewConfiguration.putModeConfig(ModeEnum.HEAT, getZoneViewConfigForMode(capabilities, ModeEnum.HEAT), isHeat());
                viewConfiguration.setDefaultMode(getCoolingDefaultMode());
            }
        }
        return viewConfiguration;
    }

    private ModeEnum getCoolingDefaultMode() {
        if (isCool()) {
            return ModeEnum.COOL;
        }
        if (isDry()) {
            return ModeEnum.DRY;
        }
        if (isFan()) {
            return ModeEnum.FAN;
        }
        if (isAuto()) {
            return ModeEnum.AUTO;
        }
        if (isHeat()) {
            return ModeEnum.HEAT;
        }
        return ModeEnum.COOL;
    }

    private TadoZoneSettingViewModeConfiguration getZoneViewConfigForMode(ZoneCapabilities capabilities, ModeEnum mode) {
        Builder configBuilder = new Builder();
        configBuilder.create(mode);
        if (isSwingControlPresent(mode)) {
            configBuilder.withSwing(getDefaultSwing());
        }
        if (isFanSpeedPresent(mode)) {
            configBuilder.withFanSpeed(getFanSpeedValues(mode), getDefaultFanSpeed(mode, getFanSpeedValues(mode)));
        }
        if (isTemperaturePresent(mode)) {
            configBuilder.withTemperature((float) getTemperatureMin(mode), (float) getTemperatureMax(mode), getModeTemperatureStep(mode, TypeEnum.valueOf(capabilities.getType())), getDefaultTemperature(mode, (float) getTemperatureMin(mode), (float) getTemperatureMax(mode)));
        }
        return configBuilder.build();
    }

    private float getDefaultTemperature(ModeEnum mode, float min, float max) {
        if (ModeEnum.HEATING == mode) {
            return getDefaultTemperature(min, max, 21.0f, 70.0f);
        }
        if (ModeEnum.HOT_WATER == mode) {
            return getDefaultTemperature(min, max, 55.0f, 130.0f);
        }
        return getDefaultTemperature(min, max, 20.0f, 68.0f);
    }

    public float getDefaultTemperature(float min, float max, float temperatureInCelsius, float temperatureInFahrenheit) {
        return isCelsiusTemperatureUnit() ? getsMiddleValue(min, max, temperatureInCelsius) : getsMiddleValue(min, max, temperatureInFahrenheit);
    }

    private float getsMiddleValue(float min, float max, float value) {
        return Math.min(getMaxValue(min, value), max);
    }

    private float getMaxValue(float min, float value) {
        return Math.max(value, min);
    }

    private FanSpeedEnum getDefaultFanSpeed(ModeEnum mode, String[] fanSpeedValues) {
        if (ModeEnum.HEATING == mode || ModeEnum.HOT_WATER == mode || fanSpeedValues == null || fanSpeedValues.length == 0) {
            return null;
        }
        return FanSpeedEnum.valueOf(fanSpeedValues[0]);
    }

    @Nullable
    private SwingEnum getDefaultSwing() {
        return SwingEnum.OFF;
    }

    public float getTemperatureValue(Temperature temperature) {
        return isCelsiusTemperatureUnit() ? temperature.getCelsius() : temperature.getFahrenheit();
    }

    public float getValueForHomeTemperatureUnit(float celsius, float fahrenheit) {
        return isCelsiusTemperatureUnit() ? celsius : fahrenheit;
    }

    public Pair<Float, Float> getInsideTemperaturePair(SensorDataPoint insideTemperature) {
        Float temperature = Float.valueOf(INSTANCE.getValueForHomeTemperatureUnit(insideTemperature.getCelsius(), insideTemperature.getFahrenheit()));
        Float precision = Float.valueOf(INSTANCE.getValueForHomeTemperatureUnit(insideTemperature.getPrecision().getCelsius(), insideTemperature.getPrecision().getFahrenheit()));
        return new Pair(Float.valueOf(Util.roundToStep(temperature.floatValue(), precision.floatValue())), precision);
    }
}
