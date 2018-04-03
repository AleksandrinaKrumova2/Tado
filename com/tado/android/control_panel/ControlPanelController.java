package com.tado.android.control_panel;

import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import com.tado.android.controllers.CapabilitiesController;
import com.tado.android.controllers.ZoneController;
import com.tado.android.rest.model.Temperature;
import com.tado.android.rest.model.ZoneOverlay;
import com.tado.android.rest.model.ZoneSetting;
import com.tado.android.rest.model.ZoneState;
import com.tado.android.times.view.model.ModeEnum;
import com.tado.android.utils.Constants;
import com.tado.android.utils.UserConfig;
import java.util.HashMap;
import java.util.Map;

public enum ControlPanelController {
    INSTANCE;
    
    private static ControlPanelController instance;
    private Map<Integer, ZoneOverlay> mZoneOverlayMap;
    private Map<Integer, ZoneState> mZoneStateMap;
    private Map<Pair<Integer, ModeEnum>, ZoneSetting> stateMap;
    private boolean timerEdited;

    public void cleanZone(int zoneId) {
        if (this.mZoneStateMap != null && this.mZoneStateMap.containsKey(Integer.valueOf(zoneId))) {
            this.mZoneStateMap.remove(Integer.valueOf(zoneId));
        }
        if (this.mZoneOverlayMap != null && this.mZoneOverlayMap.containsKey(Integer.valueOf(zoneId))) {
            this.mZoneOverlayMap.remove(Integer.valueOf(zoneId));
        }
    }

    public void clean() {
        if (this.stateMap != null) {
            this.stateMap.clear();
        }
    }

    public ZoneState getCurrentZoneState() {
        if (this.mZoneStateMap == null) {
            this.mZoneStateMap = new HashMap();
        }
        int currentZoneId = ZoneController.INSTANCE.getCurrentZoneId();
        ZoneState currentZoneStateForControlPanel = ZoneController.INSTANCE.getCurrentZoneStateForControlPanel().copy();
        if (!(this.mZoneStateMap.containsKey(Integer.valueOf(currentZoneId)) || currentZoneStateForControlPanel == null)) {
            this.mZoneStateMap.put(Integer.valueOf(currentZoneId), currentZoneStateForControlPanel);
        }
        return (ZoneState) this.mZoneStateMap.get(Integer.valueOf(currentZoneId));
    }

    public ZoneOverlay getOverlay() {
        if (getCurrentZoneState() == null) {
            return null;
        }
        if (this.mZoneOverlayMap == null) {
            this.mZoneOverlayMap = new HashMap();
        }
        int currentZoneId = ZoneController.INSTANCE.getCurrentZoneId();
        if (!this.mZoneOverlayMap.containsKey(Integer.valueOf(currentZoneId))) {
            if (getCurrentZoneState().getOverlay() == null) {
                ZoneOverlay overlay = getDefaultZoneOverlay();
                this.mZoneOverlayMap.put(Integer.valueOf(currentZoneId), overlay);
                getCurrentZoneState().setOverlay(overlay);
            } else {
                this.mZoneOverlayMap.put(Integer.valueOf(currentZoneId), getCurrentZoneState().getOverlay());
            }
        }
        return (ZoneOverlay) this.mZoneOverlayMap.get(Integer.valueOf(currentZoneId));
    }

    @NonNull
    public ZoneOverlay getDefaultZoneOverlay() {
        ZoneOverlay overlay = OverlaySettingSeriliazer.INSTANCE.restore(ZoneController.INSTANCE.getCurrentZoneId());
        if (overlay != null) {
            return overlay;
        }
        overlay = ZoneController.INSTANCE.getDefaultOverlay();
        overlay.setSetting(generateDefaultModeState(getCurrentZoneState().getSetting().getMode()));
        return overlay;
    }

    private void initStateMapWithSavedModeZoneSettings() {
        if (this.stateMap == null) {
            this.stateMap = new HashMap();
        }
        if (CapabilitiesController.INSTANCE.isCoolingZone(UserConfig.getCurrentZone().intValue())) {
            addZoneSettingToStateMap(this.stateMap, ModeEnum.COOL, CapabilitiesController.INSTANCE.isCool());
            addZoneSettingToStateMap(this.stateMap, ModeEnum.AUTO, CapabilitiesController.INSTANCE.isAuto());
            addZoneSettingToStateMap(this.stateMap, ModeEnum.DRY, CapabilitiesController.INSTANCE.isDry());
            addZoneSettingToStateMap(this.stateMap, ModeEnum.FAN, CapabilitiesController.INSTANCE.isFan());
            addZoneSettingToStateMap(this.stateMap, ModeEnum.HEAT, CapabilitiesController.INSTANCE.isHeat());
        } else if (CapabilitiesController.INSTANCE.isHeatingZone()) {
            addZoneSettingToStateMap(this.stateMap, ModeEnum.HEATING, true);
        } else if (CapabilitiesController.INSTANCE.isHotWaterZone()) {
            addZoneSettingToStateMap(this.stateMap, ModeEnum.HOT_WATER, true);
        }
    }

    private void addZoneSettingToStateMap(Map<Pair<Integer, ModeEnum>, ZoneSetting> stateMap, @NonNull ModeEnum mode, boolean isModeSupported) {
        if (isModeSupported) {
            ZoneSetting tempSettings = OverlaySettingSeriliazer.INSTANCE.restoreZoneSetting(UserConfig.getCurrentZone().intValue(), mode.name());
            if (tempSettings != null) {
                stateMap.put(getKey(mode), tempSettings);
            }
        }
    }

    public void getSavedModeState(ModeEnum mode, ZoneSetting setting) {
        ZoneSetting zoneSetting;
        if (shouldInitForCurrentZone(this.stateMap)) {
            initStateMapWithSavedModeZoneSettings();
        }
        if (this.stateMap.containsKey(getKey(mode))) {
            zoneSetting = (ZoneSetting) this.stateMap.get(getKey(mode));
            if (!setting.getPowerBoolean()) {
                zoneSetting.setPowerBoolean(false);
            }
        } else {
            zoneSetting = generateDefaultModeState(mode.name());
        }
        if (!(zoneSetting.getPowerBoolean() || zoneSetting.getTemperature() != null || zoneSetting.getMode() == null)) {
            INSTANCE.getDefaultTemperature(zoneSetting.getMode(), zoneSetting);
        }
        setting.setSwing(zoneSetting.getSwing());
        setting.setTemperature(zoneSetting.getTemperature());
        setting.setFanSpeed(zoneSetting.getFanSpeed());
        setting.setPower(zoneSetting.getPower());
        setting.setMode(zoneSetting.getMode());
    }

    private boolean shouldInitForCurrentZone(Map<Pair<Integer, ModeEnum>, ZoneSetting> stateMap) {
        if (stateMap == null) {
            return true;
        }
        boolean initForCurrentZone = true;
        for (Pair<Integer, ModeEnum> key : stateMap.keySet()) {
            if (((Integer) key.first).compareTo(Integer.valueOf(ZoneController.INSTANCE.getCurrentZoneId())) == 0) {
                initForCurrentZone = false;
            }
        }
        return initForCurrentZone;
    }

    @NonNull
    private Pair<Integer, ModeEnum> getKey(ModeEnum mode) {
        return new Pair(Integer.valueOf(ZoneController.INSTANCE.getCurrentZoneId()), mode);
    }

    private ZoneSetting generateDefaultModeState(String mode) {
        ZoneSetting state = new ZoneSetting();
        state.setPowerBoolean(true);
        if (CapabilitiesController.INSTANCE.isHeatingZone()) {
            state.setType("HEATING");
        } else if (CapabilitiesController.INSTANCE.isHotWaterZone()) {
            state.setType("HOT_WATER");
        } else {
            state.setType("AIR_CONDITIONING");
        }
        String mCurrentMode = mode;
        if (mode == null) {
            if (CapabilitiesController.INSTANCE.isHeatingZone()) {
                mode = ModeEnum.HEATING.getMode();
                mCurrentMode = ModeEnum.HEATING.getMode();
            } else if (CapabilitiesController.INSTANCE.isHotWaterZone()) {
                mode = ModeEnum.HOT_WATER.getMode();
                mCurrentMode = ModeEnum.HOT_WATER.getMode();
            } else {
                mode = CapabilitiesController.INSTANCE.getDefaultMode();
                mCurrentMode = CapabilitiesController.INSTANCE.getDefaultMode();
            }
        }
        if (ModeEnum.COOL.getMode().equalsIgnoreCase(mode) && CapabilitiesController.INSTANCE.isCoolTemperaturePresent()) {
            getDefaultCoolTemperature(state);
        } else if (ModeEnum.HEAT.getMode().equalsIgnoreCase(mode) && CapabilitiesController.INSTANCE.isHeatTemperaturePresent()) {
            getDefaultHeatTemperature(state);
        } else if (ModeEnum.HEATING.getMode().equalsIgnoreCase(mode) && CapabilitiesController.INSTANCE.isHeatingTemperaturePresent()) {
            getDefaultHeatingTemperature(state);
        } else if ((ModeEnum.HOT_WATER.getMode().equalsIgnoreCase(mode) || "HOT_WATER".equalsIgnoreCase(mode)) && CapabilitiesController.INSTANCE.canSetCurrentZoneHotWaterTemperature()) {
            getDefaultHotWaterTemperature(state);
        }
        if (CapabilitiesController.INSTANCE.isFanSpeedPresent(ModeEnum.getModeFromString(mode))) {
            state.setFanSpeed(Constants.DEFAULT_AC_FAN.name());
        }
        if (CapabilitiesController.INSTANCE.isSwingControlPresent(ModeEnum.getModeFromString(mode))) {
            state.setSwing(Constants.DEFAULT_AC_SWING.name());
        }
        state.setMode(mCurrentMode);
        return state;
    }

    public void getDefaultTemperature(@NonNull String mode, ZoneSetting state) {
        if (mode.equalsIgnoreCase(ModeEnum.COOL.getMode()) && CapabilitiesController.INSTANCE.isCoolTemperaturePresent()) {
            getDefaultCoolTemperature(state);
        } else if (mode.equalsIgnoreCase(ModeEnum.HEAT.getMode()) && CapabilitiesController.INSTANCE.isHeatTemperaturePresent()) {
            getDefaultHeatTemperature(state);
        } else if (mode.equalsIgnoreCase(ModeEnum.HEATING.getMode()) && CapabilitiesController.INSTANCE.isHeatingTemperaturePresent()) {
            getDefaultHeatingTemperature(state);
        } else if (mode.equalsIgnoreCase(ModeEnum.HOT_WATER.getMode()) && CapabilitiesController.INSTANCE.canSetCurrentZoneHotWaterTemperature()) {
            getDefaultHotWaterTemperature(state);
        }
    }

    private void getDefaultTemperature(ZoneSetting setting, ModeEnum mode, int defaultTemperature) {
        if (CapabilitiesController.INSTANCE.getTemperatureMin(mode) > defaultTemperature || CapabilitiesController.INSTANCE.getTemperatureMax(mode) < defaultTemperature) {
            setStateTemperatureValue(setting, (float) CapabilitiesController.INSTANCE.getTemperatureMin(mode));
        } else {
            setStateTemperatureValue(setting, (float) defaultTemperature);
        }
    }

    private void getDefaultCoolTemperature(ZoneSetting setting) {
        getDefaultTemperature(setting, Constants.DEFAULT_AC_MODE, getDefaultAcTemperature());
    }

    private void getDefaultHeatTemperature(ZoneSetting setting) {
        getDefaultTemperature(setting, ModeEnum.HEAT, getDefaultAcTemperature());
    }

    private void getDefaultHeatingTemperature(ZoneSetting setting) {
        getDefaultTemperature(setting, ModeEnum.HEATING, getDefaultAcTemperature());
    }

    private void getDefaultHotWaterTemperature(ZoneSetting setting) {
        getDefaultTemperature(setting, ModeEnum.HOT_WATER, getDefaultHotWaterTemperature());
    }

    private void setStateTemperatureValue(@NonNull ZoneSetting setting, float value) {
        setting.setTemperature(Temperature.fromValue(value));
    }

    private float getDefaultHeatingTemperature() {
        if (CapabilitiesController.INSTANCE.isCelsiusTemperatureUnit()) {
            return 21.0f;
        }
        return 70.0f;
    }

    private int getDefaultHotWaterTemperature() {
        if (CapabilitiesController.INSTANCE.isCelsiusTemperatureUnit()) {
            return 55;
        }
        return Constants.DEFAULT_HOT_WATER_TEMPERATURE_FAHRENHEIT;
    }

    private int getDefaultAcTemperature() {
        if (CapabilitiesController.INSTANCE.isCelsiusTemperatureUnit()) {
            return 20;
        }
        return 68;
    }

    public void setTimerEdited(boolean edited) {
        this.timerEdited = edited;
    }

    public boolean isTimerEdited() {
        return this.timerEdited;
    }

    public Map<ModeEnum, ZoneSetting> getStateMap() {
        if (shouldInitForCurrentZone(this.stateMap)) {
            initStateMapWithSavedModeZoneSettings();
        }
        Map<ModeEnum, ZoneSetting> preFillStateMap = new HashMap();
        for (Pair<Integer, ModeEnum> key : this.stateMap.keySet()) {
            if (((Integer) key.first).compareTo(Integer.valueOf(ZoneController.INSTANCE.getCurrentZoneId())) == 0) {
                preFillStateMap.put(key.second, this.stateMap.get(key));
            }
        }
        return preFillStateMap;
    }
}
