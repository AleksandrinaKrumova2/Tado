package com.tado.android.views;

import android.support.annotation.Nullable;
import com.tado.android.controllers.CapabilitiesController;
import com.tado.android.rest.model.Zone.TypeEnum;
import com.tado.android.rest.model.installation.CoolingZoneSetting;
import com.tado.android.rest.model.installation.FanSpeedEnum;
import com.tado.android.rest.model.installation.GenericZoneSetting;
import com.tado.android.rest.model.installation.SwingEnum;
import com.tado.android.times.view.model.ModeEnum;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TadoZoneSettingViewConfiguration implements Serializable {
    private ModeEnum defaultMode;
    private Map<ModeEnum, TadoZoneSettingViewModeConfiguration> modeViewConfigurationMap;
    private List<ModeEnum> supportedModes;
    private int zoneId;
    private TypeEnum zoneType;

    public void putModeConfig(ModeEnum mode, TadoZoneSettingViewModeConfiguration zoneViewConfigForMode, boolean isSupported) {
        if (this.modeViewConfigurationMap == null) {
            this.modeViewConfigurationMap = new HashMap();
            this.supportedModes = new ArrayList();
        }
        this.modeViewConfigurationMap.put(mode, zoneViewConfigForMode);
        if (isSupported) {
            this.supportedModes.add(mode);
        }
    }

    public boolean isTemperatureSupported(ModeEnum mode) {
        return this.modeViewConfigurationMap != null && this.modeViewConfigurationMap.containsKey(mode) && ((TadoZoneSettingViewModeConfiguration) this.modeViewConfigurationMap.get(mode)).isTemperature();
    }

    private ModeEnum getMode(String mode) {
        return ModeEnum.valueOf(mode);
    }

    public boolean isSwingSupported(ModeEnum mode) {
        return this.modeViewConfigurationMap != null && this.modeViewConfigurationMap.containsKey(mode) && ((TadoZoneSettingViewModeConfiguration) this.modeViewConfigurationMap.get(mode)).isSwing();
    }

    public boolean isFanSupported(ModeEnum mode) {
        return this.modeViewConfigurationMap != null && this.modeViewConfigurationMap.containsKey(mode) && ((TadoZoneSettingViewModeConfiguration) this.modeViewConfigurationMap.get(mode)).isFan();
    }

    public boolean isHeatingZone() {
        return TypeEnum.HEATING == this.zoneType;
    }

    public boolean isHotWaterZone() {
        return TypeEnum.HOT_WATER == this.zoneType;
    }

    public boolean isCoolingZone() {
        return TypeEnum.AIR_CONDITIONING == this.zoneType;
    }

    public float getTemperatureMin(ModeEnum mode) {
        if (isModePresent(mode)) {
            return ((TadoZoneSettingViewModeConfiguration) this.modeViewConfigurationMap.get(mode)).getMinTemperature();
        }
        return 0.0f;
    }

    public float getTemperatureMax(ModeEnum mode) {
        if (isModePresent(mode)) {
            return ((TadoZoneSettingViewModeConfiguration) this.modeViewConfigurationMap.get(mode)).getMaxTemperature();
        }
        return 0.0f;
    }

    private boolean isModePresent(ModeEnum mode) {
        return this.modeViewConfigurationMap != null && this.modeViewConfigurationMap.containsKey(mode);
    }

    public float getTemperatureStep(ModeEnum mode) {
        if (isModePresent(mode)) {
            return ((TadoZoneSettingViewModeConfiguration) this.modeViewConfigurationMap.get(mode)).getTemperatureStep();
        }
        return 1.0f;
    }

    public void setZoneType(String zoneType) {
        this.zoneType = TypeEnum.valueOf(zoneType);
    }

    public int getZoneId() {
        return this.zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public ModeEnum getDefaultMode() {
        return this.defaultMode;
    }

    public void setDefaultMode(ModeEnum defaultMode) {
        this.defaultMode = defaultMode;
    }

    public boolean getDefaultPower(ModeEnum defaultMode) {
        return true;
    }

    public float getDefaultTemperature(ModeEnum defaultMode) {
        if (isModePresent(defaultMode)) {
            return ((TadoZoneSettingViewModeConfiguration) this.modeViewConfigurationMap.get(defaultMode)).getDefaultTemperature();
        }
        return 0.0f;
    }

    @Nullable
    public FanSpeedEnum getDefaultFanSpeed(ModeEnum defaultMode) {
        if (isModePresent(defaultMode)) {
            return ((TadoZoneSettingViewModeConfiguration) this.modeViewConfigurationMap.get(defaultMode)).getDefaultFanSpeed();
        }
        return null;
    }

    @Nullable
    public SwingEnum getDefaultSwing(ModeEnum defaultMode) {
        if (isModePresent(defaultMode)) {
            return ((TadoZoneSettingViewModeConfiguration) this.modeViewConfigurationMap.get(defaultMode)).getDefaultSwing();
        }
        return null;
    }

    public void setDefaultModeSetting(ModeEnum mode, GenericZoneSetting genericZoneSetting) {
        if (isModePresent(mode)) {
            TadoZoneSettingViewModeConfiguration modeConfiguration = (TadoZoneSettingViewModeConfiguration) this.modeViewConfigurationMap.get(mode);
            if (genericZoneSetting.getTemperature() != null) {
                modeConfiguration.setDefaultTemperature(getDefaultTemperature(genericZoneSetting, modeConfiguration));
            }
            if (GenericZoneSetting.TypeEnum.AIR_CONDITIONING == genericZoneSetting.getType()) {
                CoolingZoneSetting coolingZoneSetting = (CoolingZoneSetting) genericZoneSetting;
                modeConfiguration.setDefaultFanSpeed(coolingZoneSetting.getFanSpeed());
                modeConfiguration.setDefaultSwing(coolingZoneSetting.getSwing() != null ? SwingEnum.valueOf(coolingZoneSetting.getSwing()) : null);
            }
        }
    }

    private float getDefaultTemperature(GenericZoneSetting genericZoneSetting, TadoZoneSettingViewModeConfiguration modeConfiguration) {
        return CapabilitiesController.INSTANCE.getDefaultTemperature(modeConfiguration.getMinTemperature(), modeConfiguration.getMaxTemperature(), genericZoneSetting.getTemperature().getCelsius(), genericZoneSetting.getTemperature().getFahrenheit());
    }

    public List<ModeEnum> getSupportedModes() {
        return this.supportedModes;
    }
}
