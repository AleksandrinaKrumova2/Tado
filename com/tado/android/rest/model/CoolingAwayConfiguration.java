package com.tado.android.rest.model;

import android.support.annotation.NonNull;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.tado.android.rest.model.installation.CoolingZoneSetting;
import com.tado.android.rest.model.installation.FanSpeedEnum;
import com.tado.android.rest.model.installation.ModeEnum;
import com.tado.android.utils.Constants;
import com.tado.android.utils.ResourceFactory;
import java.util.Observer;

public class CoolingAwayConfiguration extends GenericAwayConfiguration {
    private Observer mObserver;
    @SerializedName("setting")
    private CoolingZoneSetting mSetting;

    public CoolingZoneSetting getSetting() {
        return this.mSetting;
    }

    public JsonObject serialize() {
        JsonObject root = new JsonObject();
        if (this.mSetting != null) {
            JsonObject jsonObjectSetting = new JsonObject();
            jsonObjectSetting.addProperty("type", this.mSetting.getType().name());
            jsonObjectSetting.addProperty("power", this.mSetting.getPower());
            if (this.mSetting.getPowerBoolean()) {
                jsonObjectSetting.addProperty("mode", this.mSetting.getMode().name());
                if (this.mSetting.getSwing() != null) {
                    jsonObjectSetting.addProperty(ResourceFactory.MODE_ATTRIBUTE_SWING, this.mSetting.getSwing());
                }
                if (this.mSetting.getFanSpeed() != null) {
                    jsonObjectSetting.addProperty("fanSpeed", this.mSetting.getFanSpeed().name());
                }
                if (this.mSetting.getPowerBoolean() && this.mSetting.getTemperature() != null) {
                    JsonObject jsonObjectTemperature = new JsonObject();
                    jsonObjectTemperature.addProperty(Constants.CELSIUS, Float.valueOf(this.mSetting.getTemperature().getCelsius()));
                    jsonObjectTemperature.addProperty(Constants.FAHRENHEIT, Float.valueOf(this.mSetting.getTemperature().getFahrenheit()));
                    jsonObjectSetting.add(ResourceFactory.MODE_ATTRIBUTE_TEMPERATURE, jsonObjectTemperature);
                }
            }
            root.add("setting", jsonObjectSetting);
        }
        return root;
    }

    public void setSetting(CoolingZoneSetting setting) {
        this.mSetting = setting;
        if (this.mObserver != null && this.mSetting != null) {
            this.mSetting.addObserver(this.mObserver);
        }
    }

    public void prepareModelForUpdate() {
        if (this.mSetting != null && !this.mSetting.getPowerBoolean()) {
            this.mSetting.deleteObservers();
            this.mSetting.setMode(null);
            this.mSetting.setSwing(null);
            this.mSetting.setFanSpeed(null);
            this.mSetting.addObserver(this.mObserver);
        }
    }

    public void copyFromZoneSettings(@NonNull ZoneSetting zoneSetting) {
        FanSpeedEnum fanSpeedEnum = null;
        this.mSetting.deleteObservers();
        this.mSetting.setSwing(zoneSetting.getSwing() != null ? zoneSetting.getSwing() : null);
        CoolingZoneSetting coolingZoneSetting = this.mSetting;
        if (zoneSetting.getFanSpeed() != null) {
            fanSpeedEnum = FanSpeedEnum.valueOf(zoneSetting.getFanSpeed());
        }
        coolingZoneSetting.setFanSpeed(fanSpeedEnum);
        this.mSetting.setMode(ModeEnum.valueOf(zoneSetting.getMode()));
        this.mSetting.setTemperature(zoneSetting.getTemperature());
        this.mSetting.setPowerBoolean(zoneSetting.getPowerBoolean());
        this.mSetting.addObserver(this.mObserver);
        setChanged();
        notifyObservers(this);
    }

    public void addObserver(Observer observer) {
        super.addObserver(observer);
        this.mObserver = observer;
        if (this.mSetting != null) {
            this.mSetting.addObserver(observer);
        }
    }
}
