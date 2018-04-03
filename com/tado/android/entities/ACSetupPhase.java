package com.tado.android.entities;

public class ACSetupPhase {
    public static final String AC_SETTING_RECORDING = "AC_SETTING_RECORDING";
    public static final String AC_SPECS = "AC_SPECS";
    public static final String ON_OFF_REDUCTION = "ON_OFF_REDUCTION";
    public static final String RECORD_COMMANDS = "RECORD_COMMANDS";
    public static final String SELECT_MANUFACTURER = "SELECT_MANUFACTURER";
    private String phase;

    public ACSetupPhase(String phase) {
        this.phase = phase;
    }

    public String getPhase() {
        return this.phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }
}
