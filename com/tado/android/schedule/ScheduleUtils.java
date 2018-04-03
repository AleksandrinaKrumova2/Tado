package com.tado.android.schedule;

import com.tado.android.controllers.CapabilitiesController;
import com.tado.android.times.view.model.ModeEnum;
import com.tado.android.utils.Constants;

public class ScheduleUtils {
    public static int getPickerMin() {
        if (CapabilitiesController.INSTANCE.isHotWaterZone() && CapabilitiesController.INSTANCE.canSetCurrentZoneHotWaterTemperature()) {
            return CapabilitiesController.INSTANCE.getTemperatureMin(ModeEnum.HOT_WATER);
        }
        if (CapabilitiesController.INSTANCE.isHeatingZone()) {
            return CapabilitiesController.INSTANCE.getTemperatureMin(ModeEnum.HEATING);
        }
        return 10;
    }

    public static int getPickerMax() {
        if (CapabilitiesController.INSTANCE.isHotWaterZone() && CapabilitiesController.INSTANCE.canSetCurrentZoneHotWaterTemperature()) {
            return CapabilitiesController.INSTANCE.getTemperatureMax(ModeEnum.HOT_WATER);
        }
        if (CapabilitiesController.INSTANCE.isHeatingZone()) {
            return CapabilitiesController.INSTANCE.getTemperatureMax(ModeEnum.HEATING);
        }
        return 25;
    }

    public static int getPickerDefaultValue() {
        if (CapabilitiesController.INSTANCE.isHotWaterZone() && CapabilitiesController.INSTANCE.canSetCurrentZoneHotWaterTemperature()) {
            if (CapabilitiesController.INSTANCE.isCelsiusTemperatureUnit()) {
                return 55;
            }
            return Constants.DEFAULT_HOT_WATER_TEMPERATURE_FAHRENHEIT;
        } else if (!CapabilitiesController.INSTANCE.isHeatingZone() || CapabilitiesController.INSTANCE.isCelsiusTemperatureUnit()) {
            return 21;
        } else {
            return 70;
        }
    }
}
