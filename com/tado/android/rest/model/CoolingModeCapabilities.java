package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CoolingModeCapabilities {
    public static final String FAN_SPEED_AUTO = "AUTO";
    public static final String FAN_SPEED_HIGH = "HIGH";
    public static final String FAN_SPEED_LOW = "LOW";
    public static final String FAN_SPEED_MIDDLE = "MIDDLE";
    public static final String SWING_OFF = "OFF";
    public static final String SWING_ON = "ON";
    @SerializedName("fanSpeeds")
    private List<String> mFanSpeeds;
    @SerializedName("swings")
    private List<String> mSwings;
    @SerializedName("temperatures")
    private TemperatureRange mTemperatures;

    public TemperatureRange getTemperatures() {
        return this.mTemperatures;
    }

    public void setTemperatures(TemperatureRange temperatures) {
        this.mTemperatures = temperatures;
    }

    public List<String> getFanSpeeds() {
        return this.mFanSpeeds;
    }

    public void setFanSpeeds(List<String> fanSpeeds) {
        this.mFanSpeeds = fanSpeeds;
    }

    public List<String> getSwings() {
        return this.mSwings;
    }

    public void setSwings(List<String> swings) {
        this.mSwings = swings;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((this.mTemperatures == null ? 0 : this.mTemperatures.hashCode()) + 527) * 31) + (this.mFanSpeeds == null ? 0 : stringListHash(this.mFanSpeeds))) * 31;
        if (this.mSwings != null) {
            i = stringListHash(this.mSwings);
        }
        return hashCode + i;
    }

    private int stringListHash(List<String> list) {
        int result = 0;
        for (int i = 0; i < list.size(); i++) {
            result = (result * 31) + ((String) list.get(i)).hashCode();
        }
        return result;
    }
}
