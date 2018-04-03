package com.tado.android.entities;

public class ACModeRecordingCapabilities {
    public static final int MAX_TEMP_C = 31;
    public static final int MAX_TEMP_F = 89;
    public static final int MIN_TEMP_C = 15;
    public static final int MIN_TEMP_C_HIGH = 16;
    public static final int MIN_TEMP_F = 59;
    public static final int MIN_TEMP_F_HIGH = 60;
    private String[] fanSpeeds;
    private String[] swings;
    private TemperatureRange temperatures;

    public String[] getFanSpeeds() {
        return this.fanSpeeds;
    }

    public void setFanSpeeds(String[] fanSpeeds) {
        this.fanSpeeds = fanSpeeds;
    }

    public String[] getSwings() {
        return this.swings;
    }

    public void setSwings(String[] swing) {
        this.swings = swing;
    }

    public TemperatureRange getTemperatures() {
        return this.temperatures;
    }

    public void setTemperatures(TemperatureRange temperatures) {
        this.temperatures = temperatures;
    }
}
