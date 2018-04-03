package com.tado.android.rest.model;

import android.support.annotation.NonNull;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Constants;
import java.io.Serializable;

public class HeatingAwayConfiguration extends GenericAwayConfiguration implements Serializable {
    @SerializedName("preheatingLevel")
    private PreheatingLevel mPreheatingLevel;
    @SerializedName("minimumAwayTemperature")
    private Temperature temperature;

    public enum PreheatingLevel {
        OFF(0),
        ECO(1),
        MEDIUM(2),
        COMFORT(3);
        
        int level;

        private PreheatingLevel(int level) {
            this.level = level;
        }

        public int getLevel() {
            return this.level;
        }

        public static PreheatingLevel valueOf(int progress) {
            try {
                return values()[progress];
            } catch (IndexOutOfBoundsException e) {
                return OFF;
            }
        }
    }

    public Temperature getTemperature() {
        return this.temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public PreheatingLevel getPreheatingLevel() {
        return this.mPreheatingLevel;
    }

    public void setPreheatingLevel(PreheatingLevel preheatingLevel) {
        if (preheatingLevel != this.mPreheatingLevel) {
            setChanged();
        }
        this.mPreheatingLevel = preheatingLevel;
        notifyObservers(this);
    }

    public void prepareModelForUpdate() {
    }

    public void copyFromZoneSettings(@NonNull ZoneSetting zoneSetting) {
        setTemperature(zoneSetting.getTemperature());
        setChanged();
        notifyObservers(this);
    }

    public JsonObject serialize() {
        JsonObject root = new JsonObject();
        root.addProperty("type", getType().name());
        JsonObject jsonObjectTemperature = new JsonObject();
        jsonObjectTemperature.addProperty(Constants.CELSIUS, Float.valueOf(getTemperature().getCelsius()));
        jsonObjectTemperature.addProperty(Constants.FAHRENHEIT, Float.valueOf(getTemperature().getFahrenheit()));
        root.add("minimumAwayTemperature", jsonObjectTemperature);
        root.addProperty("preheatingLevel", getPreheatingLevel().name());
        return root;
    }
}
