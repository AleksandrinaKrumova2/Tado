package com.tado.android.utils;

import com.tado.android.rest.model.installation.TemperatureUnitEnum;
import java.util.Locale;

public class TemperatureUtils {
    public static String getFormattedTemperatureValue(float value, float precision, TemperatureUnitEnum temperatureUnitEnum, Locale locale) {
        return String.format(locale, (precision < 1.0f ? "%.1f°" : "%.0f°") + (temperatureUnitEnum == TemperatureUnitEnum.CELSIUS ? "C" : "F"), new Object[]{Float.valueOf(Util.roundToStep(value, precision))});
    }
}
