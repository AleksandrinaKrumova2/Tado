package com.tado.android.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.VectorDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.AnyRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.Pair;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import com.tado.android.entities.WeatherEnum;
import com.tado.android.installation.srt.view.fragments.SrtRegisterDeviceFragment.DeviceTypeEnum;
import com.tado.android.mvp.model.TadoModeEnum;
import com.tado.android.rest.model.CallForHeatEnum;
import com.tado.android.rest.model.DeviceType;
import com.tado.android.rest.model.OverlayTerminationCondition;
import com.tado.android.rest.model.Zone.TypeEnum;
import com.tado.android.rest.model.ZoneState;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import com.tado.android.rest.model.installation.ModeEnum;
import io.fabric.sdk.android.services.common.AbstractSpiCall;

public class ResourceFactory {
    public static final String MODE_ATTRIBUTE_FAN = "fan";
    public static final String MODE_ATTRIBUTE_SWING = "swing";
    public static final String MODE_ATTRIBUTE_TEMPERATURE = "temperature";

    public static int getDrawableResourceForDeviceType(DeviceType type) {
        if (type.name().startsWith("RU")) {
            return C0676R.drawable.ic_st;
        }
        if (type.name().startsWith("VA")) {
            return C0676R.drawable.ic_srt;
        }
        if (type.name().startsWith("WR")) {
            return C0676R.drawable.ic_sacc;
        }
        if (type.name().startsWith("BU")) {
            return C0676R.drawable.ic_ek;
        }
        if (type.name().startsWith("TS")) {
            return C0676R.drawable.ic_ts;
        }
        if (type.name().startsWith("BY") || type.name().startsWith("BX")) {
            return C0676R.drawable.ic_bx_by;
        }
        return C0676R.drawable.ic_st;
    }

    public static int getImageResourceForDeviceType(DeviceType type) {
        if (type.name().startsWith("RU")) {
            return C0676R.drawable.choose_heating;
        }
        if (type.name().startsWith("VA")) {
            return C0676R.drawable.choose_srt;
        }
        if (type.name().startsWith("WR")) {
            return C0676R.drawable.choose_cooling;
        }
        if (type.name().startsWith("BU")) {
            return C0676R.drawable.choose_ek;
        }
        if (type.name().startsWith("TS")) {
            return C0676R.drawable.choose_ts;
        }
        if (type.name().startsWith("BY") || type.name().startsWith("BX")) {
            return C0676R.drawable.choose_bx_by;
        }
        if (type.name().startsWith("GW")) {
            return C0676R.drawable.choose_gw;
        }
        if (type.name().startsWith("IB")) {
            return C0676R.drawable.choose_ib;
        }
        return C0676R.drawable.choose_heating;
    }

    public static Drawable getActionIconDrawable(Context context, String action) {
        int resourceID = C0676R.drawable.zone_list_menu_settings;
        if (action.equalsIgnoreCase(context.getString(C0676R.string.menu_addDeviceButton))) {
            resourceID = C0676R.drawable.zone_list_menu_add_device;
        } else if (action.equalsIgnoreCase(context.getString(C0676R.string.menu_energySavingsReportButton))) {
            resourceID = C0676R.drawable.zone_list_menu_energy_savings;
        } else if (action.equalsIgnoreCase(context.getString(C0676R.string.menu_settingsButton))) {
            resourceID = C0676R.drawable.zone_list_menu_settings;
        } else if (action.equalsIgnoreCase(context.getString(C0676R.string.menu_appsBetaButton))) {
            resourceID = C0676R.drawable.zone_list_menu_beta;
        } else if (action.equalsIgnoreCase(context.getString(C0676R.string.menu_feedbackButton))) {
            resourceID = C0676R.drawable.zone_list_menu_feedback;
        } else if (action.equalsIgnoreCase(context.getString(C0676R.string.menu_supportButton))) {
            resourceID = C0676R.drawable.zone_list_menu_help;
        } else if (action.equalsIgnoreCase(context.getString(C0676R.string.menu_enjoyingTadoButton))) {
            resourceID = C0676R.drawable.zone_list_menu_rate_app;
        } else if (action.equalsIgnoreCase(context.getString(C0676R.string.menu_referralButton))) {
            resourceID = C0676R.drawable.ic_referral_program;
        } else if (action.equalsIgnoreCase(context.getString(C0676R.string.menu_repairServicesButton))) {
            resourceID = C0676R.drawable.ic_repair_services;
        }
        try {
            if (VERSION.SDK_INT >= 21) {
                return getVectorDrawable(context, resourceID);
            }
            return getTintedDrawable(context, resourceID, C0676R.color.zone_settings_icon_color);
        } catch (NotFoundException e) {
            return getTintedVectorDrawable(context, resourceID, C0676R.color.zone_settings_icon_color);
        }
    }

    @StringRes
    public static int getDeviceName(DeviceType deviceType) {
        switch (deviceType) {
            case TS01:
            case TS02:
                return C0676R.string.products_temperatureSensor;
            case BY01:
            case BX01:
            case BX02:
            case RU01:
            case RU02:
                return C0676R.string.products_smartThermostat;
            case BU01:
                return C0676R.string.products_extensionKit;
            case WR01:
                return C0676R.string.products_smartAcControl;
            case VA01:
                return C0676R.string.products_smartRadiatorThermostat;
            case GW01:
            case GW02:
            case GW03:
                return C0676R.string.products_bridge;
            case IB01:
                return C0676R.string.products_internetBridge;
            default:
                return C0676R.string.empty;
        }
    }

    public static Drawable getStateIconDrawable(TadoModeEnum tadoMode, boolean hasOverlay, boolean isOpenWindowDetected) {
        int iconId;
        if (isOpenWindowDetected) {
            iconId = C0676R.drawable.ic_owd;
        } else if (hasOverlay) {
            iconId = C0676R.drawable.ic_manual;
        } else if (TadoModeEnum.HOME == tadoMode) {
            iconId = C0676R.drawable.ic_home;
        } else {
            iconId = C0676R.drawable.ic_away;
        }
        return getTintedVectorSupportDrawable(TadoApplication.getTadoAppContext(), iconId, C0676R.color.white);
    }

    public static int getZoneStateHotWaterIcon(boolean on) {
        return on ? C0676R.drawable.hotwater_state_on : C0676R.drawable.hotwater_state;
    }

    public static int getModeDrawableIcon(ModeEnum mode, boolean power) {
        return getModeDrawableIcon(mode != null ? com.tado.android.times.view.model.ModeEnum.getModeFromString(mode.toString()) : null, power);
    }

    public static int getModeDrawableIcon(com.tado.android.times.view.model.ModeEnum mode, boolean power) {
        if (!power) {
            return C0676R.drawable.zone_list_ac_power;
        }
        if (com.tado.android.times.view.model.ModeEnum.COOL == mode) {
            return C0676R.drawable.ic_ac_mode_cool;
        }
        if (com.tado.android.times.view.model.ModeEnum.DRY == mode) {
            return C0676R.drawable.ic_ac_mode_dry;
        }
        if (com.tado.android.times.view.model.ModeEnum.FAN == mode) {
            return C0676R.drawable.ic_ac_mode_fan;
        }
        if (com.tado.android.times.view.model.ModeEnum.AUTO == mode) {
            return C0676R.drawable.ic_ac_mode_auto;
        }
        return C0676R.drawable.ic_ac_mode_heat;
    }

    public static int getModeAttrDrawableIcon(String attribute) {
        if (MODE_ATTRIBUTE_TEMPERATURE.equalsIgnoreCase(attribute)) {
            return C0676R.drawable.ic_temperature;
        }
        if ("fan".equalsIgnoreCase(attribute)) {
            return C0676R.drawable.ic_fan_speed_icon;
        }
        return C0676R.drawable.list_schedule_swing_swing;
    }

    public static int getHeatingMainModeIconId(ZoneState state) {
        if (state.getSetting().getPowerBoolean()) {
            return C0676R.drawable.ic_temperature;
        }
        return C0676R.drawable.ic_heating;
    }

    public static int getMainModeIconId(ZoneState state) {
        if (state.getSetting().getType().equalsIgnoreCase("AIR_CONDITIONING")) {
            return getModeDrawableIcon(com.tado.android.times.view.model.ModeEnum.getModeFromString(state.getSetting().getMode()), state.getSetting().getPowerBoolean());
        }
        if (state.getSetting().getType().equalsIgnoreCase("HOT_WATER")) {
            return C0676R.drawable.zone_list_device_hot_water;
        }
        return getHeatingMainModeIconId(state);
    }

    @Nullable
    public static Integer getOverlayTerminationIcon(ZoneState state) {
        if (state.getOverlay() != null && state.getOverlay().getTermination().isManualOverlayTermination()) {
            return Integer.valueOf(C0676R.drawable.infinity);
        }
        if (!state.getGeolocationOverride().booleanValue() || ((state.getOverlay() == null || !state.getOverlay().getTermination().isTadoModeOverlayTermination()) && state.getOverlay() != null)) {
            return null;
        }
        return Integer.valueOf(C0676R.drawable.location_based_controll_off);
    }

    public static int getOverlayTerminationIconForControlPanel(ZoneState state) {
        if (state.getOverlay() == null || state.getOverlay().getTermination() == null || state.getOverlay().getTermination().getType() == null) {
            return C0676R.drawable.termination_manually;
        }
        if (state.getOverlay().getTermination().getType().equalsIgnoreCase(OverlayTerminationCondition.TIMER)) {
            return C0676R.drawable.termination_timer;
        }
        if (state.getOverlay().getTermination().getType().equalsIgnoreCase("MANUAL")) {
            return C0676R.drawable.termination_manually;
        }
        return C0676R.drawable.termination_mode;
    }

    public static int getImageResourceForRssi(int rssi) {
        if (rssi > -50) {
            return C0676R.drawable.wifi_signal_high;
        }
        if (rssi > -60) {
            return C0676R.drawable.wifi_signal_mid;
        }
        if (rssi > -70) {
            return C0676R.drawable.wifi_signal_low;
        }
        return C0676R.drawable.wifi_signal_very_low;
    }

    public static Drawable getTintedDrawable(Context context, @DrawableRes int resourceDrawable, @ColorRes int colorResource) {
        Drawable wrapped = DrawableCompat.wrap(ContextCompat.getDrawable(context, resourceDrawable));
        DrawableCompat.setTint(wrapped.mutate(), ContextCompat.getColor(context, colorResource));
        return wrapped;
    }

    public static int getWeatherResource(WeatherEnum type) {
        if (WeatherEnum.CLOUDY == type) {
            return C0676R.drawable.weather_cloudy;
        }
        if (WeatherEnum.CLOUDY_MOSTLY == type) {
            return C0676R.drawable.weather_cloudy_mostly;
        }
        if (WeatherEnum.CLOUDY_PARTLY == type) {
            return C0676R.drawable.weather_cloudy_partly;
        }
        if (WeatherEnum.DRIZZLE == type) {
            return C0676R.drawable.weather_drizzle;
        }
        if (WeatherEnum.FOGGY == type) {
            return C0676R.drawable.weather_foggy;
        }
        if (WeatherEnum.FREEZING == type) {
            return C0676R.drawable.weather_freezing;
        }
        if (WeatherEnum.HAIL == type) {
            return C0676R.drawable.weather_hail;
        }
        if (WeatherEnum.NIGHT_CLEAR == type) {
            return C0676R.drawable.weather_night_clear;
        }
        if (WeatherEnum.NIGHT_CLOUDY == type) {
            return C0676R.drawable.weather_night_cloudy;
        }
        if (WeatherEnum.RAIN == type) {
            return C0676R.drawable.weather_rainy;
        }
        if (WeatherEnum.RAIN_HAIL == type) {
            return C0676R.drawable.weather_rainy_hail;
        }
        if (WeatherEnum.RAIN_SNOW == type) {
            return C0676R.drawable.weather_rainy_snow;
        }
        if (WeatherEnum.SCATTERED_RAIN == type) {
            return C0676R.drawable.weather_rainy_scattered;
        }
        if (WeatherEnum.SCATTERED_RAIN_SNOW == type) {
            return C0676R.drawable.weather_rainy_snow_scattered;
        }
        if (WeatherEnum.SCATTERED_SNOW == type) {
            return C0676R.drawable.weather_snow_scattered;
        }
        if (WeatherEnum.SNOW == type) {
            return C0676R.drawable.weather_snow;
        }
        if (WeatherEnum.SUN == type) {
            return C0676R.drawable.weather_sunny;
        }
        if (WeatherEnum.THUNDERSTORM == type) {
            return C0676R.drawable.weather_thunderstorms;
        }
        if (WeatherEnum.WIND == type) {
            return C0676R.drawable.weather_windyv;
        }
        return C0676R.drawable.outside_temperature;
    }

    @StringRes
    public static int getRegisterProductNameStringResource(DeviceTypeEnum deviceType) {
        switch (deviceType) {
            case GATEWAY:
                return C0676R.string.installation_scanToRegister_productNameIB;
            case VALVE:
                return C0676R.string.installation_scanToRegister_productNameSRT;
            default:
                return -1;
        }
    }

    @StringRes
    public static int getConfirmProductNameStringResource(DeviceTypeEnum deviceType) {
        switch (deviceType) {
            case GATEWAY:
                return C0676R.string.installation_scanToRegister_confirm_productNameIB;
            case VALVE:
                return C0676R.string.installation_scanToRegister_confirm_productNameSRT;
            default:
                return -1;
        }
    }

    public static Pair<Integer, Integer> getCallForHeatResourcesPair(CallForHeatEnum callForHeat) {
        if (CallForHeatEnum.NONE == callForHeat) {
            return new Pair(Integer.valueOf(C0676R.drawable.ic_call_for_heat_none), Integer.valueOf(C0676R.string.mainScreen_heatRequest_noneLabel));
        }
        if (CallForHeatEnum.LOW == callForHeat) {
            return new Pair(Integer.valueOf(C0676R.drawable.ic_call_for_heat_low), Integer.valueOf(C0676R.string.mainScreen_heatRequest_lowLabel));
        }
        if (CallForHeatEnum.MEDIUM == callForHeat) {
            return new Pair(Integer.valueOf(C0676R.drawable.ic_call_for_heat_medium), Integer.valueOf(C0676R.string.mainScreen_heatRequest_mediumLabel));
        }
        return new Pair(Integer.valueOf(C0676R.drawable.ic_call_for_heat_high), Integer.valueOf(C0676R.string.mainScreen_heatRequest_highLabel));
    }

    public static VectorDrawableCompat getVectorDrawable(@NonNull Context context, @DrawableRes int resourceDrawable) {
        return VectorDrawableCompat.create(context.getResources(), resourceDrawable, context.getTheme());
    }

    private static VectorDrawableCompat getTintedVectorDrawable(@NonNull Context context, @DrawableRes int resourceDrawable, @ColorRes int color) {
        VectorDrawableCompat drawable = getVectorDrawable(context, resourceDrawable);
        if (drawable != null) {
            drawable.mutate().setTint(ContextCompat.getColor(context, color));
        }
        return drawable;
    }

    public static int getDescriptionString(GenericHardwareDevice device, TypeEnum zoneType) {
        if (device.isCooling()) {
            return C0676R.string.settings_zoneSettings_devices_deviceDetails_roles_airConditioning_wr;
        }
        if (TypeEnum.HOT_WATER == zoneType) {
            return getHotWaterString(device);
        }
        return getHeatingString(device);
    }

    private static int getHeatingString(GenericHardwareDevice device) {
        if (device.isSmartThermostat() && device.isOnlyCircuitDriver()) {
            return C0676R.string.settings_zoneSettings_devices_deviceDetails_roles_heating_bxByRuAsCircuitDriver;
        }
        if (device.isRoomUnit() && device.isOnlyZoneUI()) {
            return C0676R.string.settings_zoneSettings_devices_deviceDetails_roles_heating_ruForUserInterface;
        }
        if (device.isRoomUnit() && device.isCircuitDriverAndZoneUI()) {
            return C0676R.string.settings_zoneSettings_devices_deviceDetails_roles_heating_ruAsCircuitDriverAndForUserInterface;
        }
        if (device.isValve()) {
            return C0676R.string.settings_zoneSettings_devices_deviceDetails_roles_heating_va;
        }
        if (device.isOldGenerationSmartThermostat() && device.isCircuitDriverAndZoneUI()) {
            return C0676R.string.settings_zoneSettings_devices_deviceDetails_roles_heating_bxByAsCircuitDriverAndForUserInterface;
        }
        if (device.isTemperatureSensor()) {
            return C0676R.string.settings_zoneSettings_devices_deviceDetails_roles_heating_ts;
        }
        if (device.isBoilerUnit() && device.isOnlyCircuitDriver()) {
            return C0676R.string.settings_zoneSettings_devices_deviceDetails_roles_heating_buAsCircuitDriver;
        }
        return C0676R.string.empty;
    }

    private static int getHotWaterString(GenericHardwareDevice device) {
        if (device.isRoomUnit() && device.isZoneDriverAndZoneUI()) {
            return C0676R.string.settings_zoneSettings_devices_deviceDetails_roles_hotWater_ruAsDriverAndForUserInterface;
        }
        if (device.isRoomUnit() && device.isOnlyZoneUI()) {
            return C0676R.string.settings_zoneSettings_devices_deviceDetails_roles_hotWater_ruForUserInterface;
        }
        if (device.isBoilerUnit() && device.isOnlyZoneDriver()) {
            return C0676R.string.settings_zoneSettings_devices_deviceDetails_roles_hotWater_buAsDriver;
        }
        if (device.isOldGenerationSmartThermostat() && device.isOnlyZoneDriver()) {
            return C0676R.string.settings_zoneSettings_devices_deviceDetails_roles_hotWater_bxByAsDriver;
        }
        return C0676R.string.empty;
    }

    @StringRes
    public static int getInstallationDrawerTitle(boolean isStartInstallation) {
        return isStartInstallation ? C0676R.string.installation_addDevice_title : C0676R.string.installation_unfinishedInstallation_title;
    }

    public static Drawable getInstallationIcon(Context context, boolean isStartInstallation) {
        return getTintedVectorSupportDrawable(context, isStartInstallation ? C0676R.drawable.zone_list_menu_add_device : C0676R.drawable.ic_pause, C0676R.color.white_transparent60);
    }

    @NonNull
    public static Drawable getHomeWifiDrawable(@NonNull Context context, boolean enabled) {
        return getTintedVectorSupportDrawable(context, C0676R.drawable.ic_home_wi_fi, enabled ? C0676R.color.zone_settings_icon_color : C0676R.color.disabled_text_color);
    }

    public static Drawable getTintedVectorSupportDrawable(Context context, @DrawableRes int drawableResource, @ColorRes int colorResource) {
        try {
            return getTintedVectorDrawable(context, drawableResource, colorResource);
        } catch (Exception e) {
            return getTintedDrawable(context, drawableResource, colorResource);
        }
    }

    public static Drawable getVectorSupportDrawable(Context context, @DrawableRes int drawableResource) {
        try {
            return getVectorDrawable(context, drawableResource);
        } catch (Exception e) {
            return ContextCompat.getDrawable(context, drawableResource);
        }
    }

    @StringRes
    public static int getSettingsDisplayOffBaconId(TypeEnum zoneType) {
        if (TypeEnum.HEATING == zoneType) {
            return C0676R.string.components_heatingSettingDisplay_offLabel;
        }
        if (TypeEnum.HOT_WATER == zoneType) {
            return C0676R.string.components_hotWaterSettingDisplay_offLabel;
        }
        return C0676R.string.components_acSettingDisplay_offLabel;
    }

    public static Drawable getScaledTintedVectorDrawable(@NonNull Context context, int drawableRes, @ColorRes int color, int width) {
        return getScaledDrawable(context, getTintedVectorSupportDrawable(context, drawableRes, color), width);
    }

    public static Drawable getScaledDrawable(@NonNull Context context, @NonNull Drawable drawable, int width) {
        float scale = (((float) drawable.getIntrinsicWidth()) / ((float) width)) * context.getResources().getDisplayMetrics().density;
        ScaleDrawable scaled = new ScaleDrawable(drawable, 17, scale, scale);
        scaled.setLevel(AbstractSpiCall.DEFAULT_TIMEOUT);
        scaled.setBounds(0, 0, (int) (((float) drawable.getIntrinsicWidth()) * scale), (int) (((float) drawable.getIntrinsicHeight()) * scale));
        return scaled;
    }

    public static final Uri getUriToResource(@NonNull Context context, @AnyRes int resId) throws NotFoundException {
        Resources res = context.getResources();
        return Uri.parse("android.resource://" + res.getResourcePackageName(resId) + '/' + res.getResourceTypeName(resId) + '/' + res.getResourceEntryName(resId));
    }

    public static Bitmap getBitmapFromResource(Context context, int drawableId) {
        Drawable drawable = getVectorSupportDrawable(context, drawableId);
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        if (drawable instanceof VectorDrawable) {
            if (VERSION.SDK_INT < 21) {
                drawable = DrawableCompat.wrap(drawable).mutate();
            }
            return getBitmap((VectorDrawable) drawable);
        } else if (drawable instanceof VectorDrawableCompat) {
            return getBitmap((VectorDrawableCompat) DrawableCompat.wrap(drawable).mutate());
        } else {
            throw new IllegalArgumentException("unsupported drawable type");
        }
    }

    @TargetApi(21)
    private static Bitmap getBitmap(Drawable vectorDrawable) {
        Bitmap bitmap = Bitmap.createBitmap(Math.round((float) vectorDrawable.getIntrinsicWidth()), Math.round((float) vectorDrawable.getIntrinsicHeight()), Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return bitmap;
    }
}
