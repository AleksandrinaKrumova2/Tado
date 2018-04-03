package com.tado.android.rest.model;

import android.support.annotation.NonNull;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.tado.android.rest.model.installation.HotWaterZoneSetting;
import com.tado.android.utils.Constants;
import com.tado.android.utils.ResourceFactory;
import java.util.Observer;

public class HotWaterAwayConfiguration extends GenericAwayConfiguration {
    private Observer mObserver;
    @SerializedName("setting")
    private HotWaterZoneSetting mSetting;

    public HotWaterZoneSetting getSetting() {
        return this.mSetting;
    }

    public JsonObject serialize() {
        JsonObject root = new JsonObject();
        if (this.mSetting != null) {
            JsonObject jsonObjectSetting = new JsonObject();
            jsonObjectSetting.addProperty("type", this.mSetting.getType().name());
            jsonObjectSetting.addProperty("power", this.mSetting.getPower());
            if (this.mSetting.getPowerBoolean() && this.mSetting.getTemperature() != null) {
                JsonObject jsonObjectTemperature = new JsonObject();
                jsonObjectTemperature.addProperty(Constants.CELSIUS, Float.valueOf(this.mSetting.getTemperature().getCelsius()));
                jsonObjectTemperature.addProperty(Constants.FAHRENHEIT, Float.valueOf(this.mSetting.getTemperature().getFahrenheit()));
                jsonObjectSetting.add(ResourceFactory.MODE_ATTRIBUTE_TEMPERATURE, jsonObjectTemperature);
            }
            root.add("setting", jsonObjectSetting);
        }
        return root;
    }

    public void prepareModelForUpdate() {
        this.mSetting.deleteObservers();
        if (!(this.mSetting == null || this.mSetting.getPowerBoolean())) {
            this.mSetting.setTemperature(null);
        }
        this.mSetting.addObserver(this.mObserver);
    }

    public void addObserver(Observer observer) {
        super.addObserver(observer);
        this.mObserver = observer;
        if (this.mSetting != null) {
            this.mSetting.addObserver(observer);
        }
    }

    public void copyFromZoneSettings(@NonNull ZoneSetting zoneSetting) {
        if (this.mSetting == null) {
            this.mSetting = new HotWaterZoneSetting();
        }
        this.mSetting.deleteObservers();
        this.mSetting.setTemperature(zoneSetting.getTemperature());
        this.mSetting.setPowerBoolean(zoneSetting.getPowerBoolean());
        this.mSetting.addObserver(this.mObserver);
        setChanged();
        notifyObservers(this);
    }
}
