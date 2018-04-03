package com.tado.android.menu;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import com.tado.android.controllers.CapabilitiesController;
import com.tado.android.menu.DrawerItem.DrawerItemEnum;
import com.tado.android.rest.model.DeviceType;
import com.tado.android.rest.model.Zone;
import com.tado.android.rest.model.Zone.TypeEnum;
import com.tado.android.rest.model.ZoneState;
import com.tado.android.times.view.model.CoolingPowerEnum;
import java.io.Serializable;

public class ZoneItem extends DrawerItem implements Serializable {
    private String mMode;
    private boolean mOnline;
    private boolean mPower;
    private Float mPrecisionValue = Float.valueOf(0.1f);
    private String mTemperatureSetting;
    private Float mTemperatureValue;
    private ZoneState mZoneState;
    private Zone zone;

    public ZoneItem(@NonNull Zone zone) {
        setItemType(DrawerItemEnum.ZONE_ITEM);
        this.zone = zone;
        ZoneState zoneState = zone.getZoneState();
        this.mZoneState = zoneState;
        if (zoneState != null) {
            this.mPower = CoolingPowerEnum.getBooleanValue(zone.getZoneState().getSetting().getPower());
            this.mOnline = zone.getZoneState().getLink().isOnline();
            this.mMode = zone.getZoneState().getSetting().getMode();
            if (!(zone.getZoneState().getSensorDataPoints() == null || zone.getZoneState().getSensorDataPoints().getInsideTemperature() == null)) {
                Pair<Float, Float> insideTemperaturePair = CapabilitiesController.INSTANCE.getInsideTemperaturePair(zone.getZoneState().getSensorDataPoints().getInsideTemperature());
                this.mTemperatureValue = (Float) insideTemperaturePair.first;
                this.mPrecisionValue = (Float) insideTemperaturePair.second;
            }
            if (zone.getZoneState().getSetting().getTemperature() != null) {
                setTemperatureSetting(zone.getZoneState().getSetting().getTemperature().getFormattedTemperatureValue(zone.isHeatingZone() ? getPrecisionValue().floatValue() : 1.0f));
            } else if (!zone.isCoolingZone()) {
                String string;
                if (zone.getZoneState().getSetting().getPowerBoolean()) {
                    string = TadoApplication.getTadoAppContext().getString(C0676R.string.components_hotWaterSettingDisplay_onLabel);
                } else {
                    string = TadoApplication.getTadoAppContext().getString(getSettingsDisplayOffLabel(zone.getType().name()));
                }
                setTemperatureSetting(string);
            }
        }
    }

    private int getSettingsDisplayOffLabel(String type) {
        return TypeEnum.HEATING == TypeEnum.valueOf(type) ? C0676R.string.components_heatingSettingDisplay_offLabel : C0676R.string.components_hotWaterSettingDisplay_offLabel;
    }

    public String getTemperatureSetting() {
        return this.mTemperatureSetting;
    }

    private void setTemperatureSetting(String temperatureSetting) {
        this.mTemperatureSetting = temperatureSetting;
    }

    public TypeEnum getZoneType() {
        return this.zone.getType();
    }

    public boolean isOnline() {
        return this.mOnline;
    }

    public String getZoneName() {
        return this.zone.getName();
    }

    public void setZoneName(String zoneName) {
        this.zone.setName(zoneName);
    }

    @Nullable
    public Float getTemperatureValue() {
        return this.mTemperatureValue;
    }

    @NonNull
    public Float getPrecisionValue() {
        return Float.valueOf(this.mPrecisionValue != null ? this.mPrecisionValue.floatValue() : 0.1f);
    }

    public boolean isPower() {
        return this.mPower;
    }

    public void setPower(boolean power) {
        this.mPower = power;
    }

    public String getMode() {
        return this.mMode;
    }

    public void setMode(String mode) {
        this.mMode = mode;
    }

    public int getZoneId() {
        return this.zone.getId();
    }

    public ZoneState getZoneState() {
        return this.mZoneState;
    }

    public int getZoneImageResource() {
        if (isHeatingZone()) {
            if (hasRoomUnitAndValves()) {
                return C0676R.drawable.zone_list_device_st_srt;
            }
            if (hasMultiValves() && !hasRoomUnit()) {
                return C0676R.drawable.zone_list_device_srts;
            }
            if (!hasSingleValve() || hasRoomUnit()) {
                return !hasValves() ? C0676R.drawable.zone_list_device_st : C0676R.drawable.zone_list_device_st;
            } else {
                return C0676R.drawable.zone_list_device_srt;
            }
        } else if (isCoolingZone()) {
            return C0676R.drawable.zone_list_device_saac;
        } else {
            if (isHotWaterZone()) {
                return C0676R.drawable.zone_list_device_hot_water;
            }
            return C0676R.drawable.zone_list_device_st;
        }
    }

    public boolean isCoolingZone() {
        return this.zone.getType() == TypeEnum.AIR_CONDITIONING;
    }

    public boolean isHotWaterZone() {
        return this.zone.getType() == TypeEnum.HOT_WATER;
    }

    public boolean isHeatingZone() {
        return this.zone.getType() == TypeEnum.HEATING;
    }

    private boolean hasValves() {
        return this.zone.getDeviceTypes() != null && this.zone.getDeviceTypes().contains(DeviceType.VA01);
    }

    private boolean hasRoomUnitAndValves() {
        return hasValves() && hasRoomUnit();
    }

    private boolean hasSingleValve() {
        return getValvesCount() == 1;
    }

    private boolean hasMultiValves() {
        return getValvesCount() > 1;
    }

    private boolean hasRoomUnit() {
        return this.zone.getDeviceTypes() != null && (this.zone.getDeviceTypes().contains(DeviceType.RU01) || this.zone.getDeviceTypes().contains(DeviceType.RU02));
    }

    private int getValvesCount() {
        int counter = 0;
        if (this.zone.getDeviceTypes() != null) {
            for (DeviceType deviceType : this.zone.getDeviceTypes()) {
                if (deviceType.equals(DeviceType.VA01)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public boolean isSupportsDazzle() {
        return this.zone.getDazzleMode().isSupported();
    }

    public boolean isDazzleEnabled() {
        return this.zone.getDazzleMode().isEnabled();
    }

    public void setDazzleEnabled(boolean dazzleEnabled) {
        this.zone.getDazzleMode().setEnabled(dazzleEnabled);
    }

    public String toString() {
        return this.zone.getName();
    }

    public boolean isOpenWindowDetectionSupported() {
        return this.zone.getOpenWindowDetectionConfiguration() != null && this.zone.getOpenWindowDetectionConfiguration().isSupported();
    }

    public boolean isOpenWindowDetectionEnabled() {
        return this.zone.getOpenWindowDetectionConfiguration() != null && this.zone.getOpenWindowDetectionConfiguration().isEnabled();
    }

    public int getOpenWindowDetectionIntervalInMinutes() {
        return this.zone.getOpenWindowDetectionConfiguration() == null ? 15 : this.zone.getOpenWindowDetectionConfiguration().getTimeoutInSeconds() / 60;
    }

    public boolean hasBadge() {
        return this.zone.hasLowBattery();
    }
}
