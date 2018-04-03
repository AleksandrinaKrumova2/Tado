package com.tado.android.views;

import android.support.annotation.NonNull;
import com.tado.android.rest.model.ZoneSetting;
import com.tado.android.rest.model.installation.CoolingZoneSetting;
import com.tado.android.rest.model.installation.FanSpeedEnum;
import com.tado.android.rest.model.installation.GenericZoneSetting;
import com.tado.android.rest.model.installation.GenericZoneSetting.TypeEnum;
import com.tado.android.rest.model.installation.HeatingZoneSetting;
import com.tado.android.rest.model.installation.HotWaterZoneSetting;
import com.tado.android.rest.model.installation.ModeEnum;

public class SettingModelAdapter {
    @NonNull
    public static ZoneSetting getZoneSetting(@NonNull GenericZoneSetting genericZoneSetting) {
        ZoneSetting zoneSetting = new ZoneSetting();
        zoneSetting.setTemperature(genericZoneSetting.getTemperature());
        zoneSetting.setPowerBoolean(genericZoneSetting.getPowerBoolean());
        zoneSetting.setType(genericZoneSetting.getType().name());
        if (TypeEnum.HOT_WATER == genericZoneSetting.getType()) {
            zoneSetting.setMode(TypeEnum.HOT_WATER.name());
        } else if (TypeEnum.HEATING == genericZoneSetting.getType()) {
            zoneSetting.setMode(TypeEnum.HEATING.name());
        } else if (TypeEnum.AIR_CONDITIONING == genericZoneSetting.getType()) {
            CoolingZoneSetting coolingZoneSetting = (CoolingZoneSetting) genericZoneSetting;
            if (coolingZoneSetting.getMode() != null) {
                zoneSetting.setMode(coolingZoneSetting.getMode().name());
            }
            if (coolingZoneSetting.getSwing() != null) {
                zoneSetting.setSwing(coolingZoneSetting.getSwing());
            }
            if (coolingZoneSetting.getFanSpeed() != null) {
                zoneSetting.setFanSpeed(coolingZoneSetting.getFanSpeed().name());
            }
        }
        return zoneSetting;
    }

    @NonNull
    public static GenericZoneSetting copyToGenericZoneSetting(@NonNull GenericZoneSetting genericZoneSetting, @NonNull ZoneSetting zoneSetting) {
        FanSpeedEnum fanSpeedEnum = null;
        if (genericZoneSetting == null || zoneSetting == null) {
            throw new IllegalArgumentException(String.format("Exception while GenericZoneSetting is %s and ZoneSetting is %s", new Object[]{getExceptionMessage(genericZoneSetting), getExceptionMessage(zoneSetting)}));
        }
        if (TypeEnum.AIR_CONDITIONING == genericZoneSetting.getType()) {
            ((CoolingZoneSetting) genericZoneSetting).setSwing(zoneSetting.getSwing() != null ? zoneSetting.getSwing() : null);
            CoolingZoneSetting coolingZoneSetting = (CoolingZoneSetting) genericZoneSetting;
            if (zoneSetting.getFanSpeed() != null) {
                fanSpeedEnum = FanSpeedEnum.valueOf(zoneSetting.getFanSpeed());
            }
            coolingZoneSetting.setFanSpeed(fanSpeedEnum);
            ((CoolingZoneSetting) genericZoneSetting).setMode(ModeEnum.valueOf(zoneSetting.getMode()));
        }
        genericZoneSetting.setTemperature(zoneSetting.getTemperature());
        genericZoneSetting.setPowerBoolean(zoneSetting.getPowerBoolean());
        return genericZoneSetting;
    }

    @NonNull
    private static String getExceptionMessage(@NonNull Object object) {
        return object == null ? "NULL" : "NOT NULL";
    }

    public static GenericZoneSetting getGenericZoneSetting(com.tado.android.times.view.model.ModeEnum mode) {
        GenericZoneSetting genericZoneSetting;
        if (com.tado.android.times.view.model.ModeEnum.HEATING == mode) {
            genericZoneSetting = new HeatingZoneSetting();
            genericZoneSetting.setType(TypeEnum.HEATING);
            return genericZoneSetting;
        } else if (com.tado.android.times.view.model.ModeEnum.HOT_WATER == mode) {
            genericZoneSetting = new HotWaterZoneSetting();
            genericZoneSetting.setType(TypeEnum.HOT_WATER);
            return genericZoneSetting;
        } else {
            genericZoneSetting = new CoolingZoneSetting();
            genericZoneSetting.setType(TypeEnum.AIR_CONDITIONING);
            ((CoolingZoneSetting) genericZoneSetting).setMode(ModeEnum.valueOf(mode.name()));
            return genericZoneSetting;
        }
    }
}
