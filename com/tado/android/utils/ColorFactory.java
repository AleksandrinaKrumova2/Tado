package com.tado.android.utils;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import com.tado.android.controllers.CapabilitiesController;
import com.tado.android.menu.ZoneItem;
import com.tado.android.rest.model.Temperature;
import com.tado.android.rest.model.ZoneState;
import com.tado.android.rest.model.installation.CoolingZoneSetting;
import com.tado.android.rest.model.installation.GenericZoneSetting;
import com.tado.android.rest.model.installation.GenericZoneSetting.TypeEnum;
import com.tado.android.rest.model.installation.ModeEnum;

public class ColorFactory {
    static int awayColor = C0676R.color.away;
    private static int[] backgroundColors = new int[]{C0676R.color.temp_off_bg, C0676R.color.temp_5_9_bg, C0676R.color.temp_10_16_bg, C0676R.color.temp_17_bg, C0676R.color.temp_18_bg, C0676R.color.temp_19_bg, C0676R.color.temp_20_21_bg, C0676R.color.temp_22_bg, C0676R.color.temp_23_bg, C0676R.color.temp_24_bg, C0676R.color.temp_25_bg};
    private static int[] foregroundColors = new int[]{C0676R.color.temp_off, C0676R.color.temp_5_9, C0676R.color.temp_10_16, C0676R.color.temp_17, C0676R.color.temp_18, C0676R.color.temp_19, C0676R.color.temp_20_21, C0676R.color.temp_22, C0676R.color.temp_23, C0676R.color.temp_24, C0676R.color.temp_25};
    static int manualColor = C0676R.color.manual;

    public static class ColorPair {
        public int darkColor;
        public int darkColorRes;
        public int lightColor;
        public int lightColorRes;

        public ColorPair(Context context, int lightColorRes, int darkColorRes) {
            this.lightColorRes = lightColorRes;
            this.darkColorRes = darkColorRes;
            this.lightColor = ContextCompat.getColor(context, lightColorRes);
            this.darkColor = ContextCompat.getColor(context, darkColorRes);
        }
    }

    public static ColorPair getColorIndexForTemperature(Context context, float celsius) {
        int colorIndex;
        if (celsius < 5.0f) {
            colorIndex = 0;
        } else if (celsius < Constants.MAX_OFFSET_CELSIUS) {
            colorIndex = 1;
        } else if (celsius < 17.0f) {
            colorIndex = 2;
        } else if (celsius < Constants.MAX_OFFSET_FAHRENHEIT) {
            colorIndex = 3;
        } else if (celsius < 19.0f) {
            colorIndex = 4;
        } else if (celsius < 20.0f) {
            colorIndex = 5;
        } else if (celsius < 22.0f) {
            colorIndex = 6;
        } else if (celsius < 23.0f) {
            colorIndex = 7;
        } else if (celsius < 24.0f) {
            colorIndex = 8;
        } else if (celsius < 25.0f) {
            colorIndex = 9;
        } else {
            colorIndex = 10;
        }
        return new ColorPair(context, backgroundColors[colorIndex], foregroundColors[colorIndex]);
    }

    public static ColorPair getColorIndexForTemperatureHotWater(Context context, float celsius) {
        int colorIndex;
        if (celsius < BitmapDescriptorFactory.HUE_ORANGE) {
            colorIndex = 0;
        } else if (celsius < 40.0f) {
            colorIndex = 1;
        } else if (celsius < 45.0f) {
            colorIndex = 2;
        } else if (celsius < 49.0f) {
            colorIndex = 3;
        } else if (celsius < 50.0f) {
            colorIndex = 4;
        } else if (celsius < 55.0f) {
            colorIndex = 5;
        } else if (celsius < BitmapDescriptorFactory.HUE_YELLOW) {
            colorIndex = 6;
        } else if (celsius < 62.0f) {
            colorIndex = 7;
        } else if (celsius < 64.0f) {
            colorIndex = 8;
        } else if (celsius < 65.0f) {
            colorIndex = 9;
        } else {
            colorIndex = 10;
        }
        return new ColorPair(context, backgroundColors[colorIndex], foregroundColors[colorIndex]);
    }

    public static ColorPair getColorForHotWaterRelay(Context context, boolean isOff) {
        if (isOff) {
            return new ColorPair(context, C0676R.color.hw_off_bg, C0676R.color.hw_off);
        }
        return new ColorPair(context, C0676R.color.hw_on_bg, C0676R.color.hw_on);
    }

    public static ColorPair getColorForAc(Context context, ModeEnum mode, boolean isOff) {
        if (isOff) {
            return new ColorPair(context, C0676R.color.ac_off_bg, C0676R.color.ac_off);
        }
        if (mode == ModeEnum.HEAT) {
            return new ColorPair(context, C0676R.color.ac_heat_bg, C0676R.color.ac_heat);
        }
        return new ColorPair(context, C0676R.color.ac_on_bg, C0676R.color.ac_on);
    }

    public static ColorPair getZoneStateBackgroundColorPair(GenericZoneSetting setting, ZoneState zoneState, int zoneId) {
        boolean isOpenWindowDetected;
        boolean hasOverlay;
        boolean isAway = true;
        if (zoneState == null || zoneState.getOpenWindow() == null) {
            isOpenWindowDetected = false;
        } else {
            isOpenWindowDetected = true;
        }
        if (zoneState == null || zoneState.getOverlay() == null || zoneState.getOverlay().getTermination() == null) {
            hasOverlay = false;
        } else {
            hasOverlay = true;
        }
        if (zoneState == null || !zoneState.isTadoModeAway()) {
            isAway = false;
        }
        return getZoneStateBackgroundColorPair(setting, isOpenWindowDetected, hasOverlay, isAway, zoneId);
    }

    public static ColorPair getZoneStateBackgroundColorPair(GenericZoneSetting setting, boolean isOpenWindowDetected, boolean hasOverlay, boolean isAway, int zoneId) {
        if (isOpenWindowDetected) {
            return getBackgroundColorPair(setting, zoneId);
        }
        if (hasOverlay) {
            return new ColorPair(TadoApplication.getTadoAppContext(), C0676R.color.manual_transparent, C0676R.color.manual);
        }
        if (isAway) {
            return new ColorPair(TadoApplication.getTadoAppContext(), C0676R.color.away_transparent, C0676R.color.away);
        }
        return getBackgroundColorPair(setting, zoneId);
    }

    public static ColorPair getBackgroundColorPair(@NonNull GenericZoneSetting setting, int zoneId) {
        float temperature;
        boolean z = true;
        if (!setting.getPowerBoolean() || setting.getTemperature() == null) {
            temperature = 0.0f;
        } else {
            temperature = setting.getTemperature().getCelsius();
        }
        switch (setting.getType()) {
            case HEATING:
                return getColorIndexForTemperature(TadoApplication.getTadoAppContext(), temperature);
            case HOT_WATER:
                if (CapabilitiesController.INSTANCE.canSetHotWaterTemperature(zoneId)) {
                    return getColorIndexForTemperatureHotWater(TadoApplication.getTadoAppContext(), temperature);
                }
                return getColorForHotWaterRelay(TadoApplication.getTadoAppContext(), !setting.getPowerBoolean());
            case AIR_CONDITIONING:
                Context tadoAppContext = TadoApplication.getTadoAppContext();
                ModeEnum mode = ((CoolingZoneSetting) setting).getMode();
                if (setting.getPowerBoolean()) {
                    z = false;
                }
                return getColorForAc(tadoAppContext, mode, z);
            default:
                return getColorIndexForTemperature(TadoApplication.getTadoAppContext(), temperature);
        }
    }

    public static int getMixedColor(int startColor, int endColor) {
        return ((Integer) new ArgbEvaluator().evaluate(0.2f, Integer.valueOf(startColor), Integer.valueOf(endColor))).intValue();
    }

    public static int getZoneItemBackgroundColor(ZoneItem item) {
        GenericZoneSetting setting;
        Float temperature = Float.valueOf(0.0f);
        try {
            temperature = Float.valueOf(item.getTemperatureSetting().replace("°", ""));
        } catch (Exception e) {
        }
        if (item.isCoolingZone()) {
            setting = new CoolingZoneSetting(item.getMode() != null ? ModeEnum.valueOf(item.getMode()) : null, item.isPower(), Temperature.fromValue(temperature.floatValue()));
        } else {
            setting = new GenericZoneSetting(TypeEnum.valueOf(item.getZoneType().name()), item.isPower(), Temperature.fromValue(temperature.floatValue()));
        }
        return getZoneStateBackgroundColorPair(setting, item.getZoneState(), item.getZoneId()).darkColor;
    }
}
