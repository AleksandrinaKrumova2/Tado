package com.tado.android.entities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.tado.android.utils.Constants;

public class CurrentState {
    public static final String PREFERENCES = "myPreferences";
    private String autoOperation;
    private String controlPhase;
    private Boolean currentUserGeoStale;
    private Boolean currentUserPrivacyEnabled;
    private Boolean deviceUpdating;
    private Boolean fallbackOperation;
    private Boolean heatingMuscleConnected;
    private String heatingMuscleType;
    private Boolean heatingOn;
    private String helpUrl;
    private Integer homeId;
    private Float insideTemp;
    private Boolean insideTemperatureSensorConnected;
    private String insideTemperatureSensorType;
    private Boolean internetGatewayConnected;
    private String internetGatewayType;
    private String operation;
    private String operationTrigger;
    private Boolean preheating;
    private Float setPointTemp;
    private Boolean settingsEnabled;

    public CurrentState(String operation, String autoOperation, String operationTrigger, Float insideTemp, Float setPointTemp, String controlPhase, Boolean currentUserGeoStale, Boolean currentUserPrivacyEnabled, Boolean deviceUpdating, Boolean heatingOn) {
        this.operation = operation;
        this.autoOperation = autoOperation;
        this.operationTrigger = operationTrigger;
        this.insideTemp = insideTemp;
        this.setPointTemp = setPointTemp;
        this.controlPhase = controlPhase;
        this.currentUserGeoStale = currentUserGeoStale;
        this.currentUserPrivacyEnabled = currentUserPrivacyEnabled;
        this.deviceUpdating = deviceUpdating;
        this.heatingOn = heatingOn;
    }

    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperationTrigger() {
        return this.operationTrigger;
    }

    public void setOperationTrigger(String operationTrigger) {
        this.operationTrigger = operationTrigger;
    }

    public Float getInsideTemp() {
        return this.insideTemp;
    }

    public void setInsideTemp(Float insideTemp) {
        this.insideTemp = insideTemp;
    }

    public Float getSetPointTemp() {
        return this.setPointTemp;
    }

    public void setSetPointTemp(Float setPointTemp) {
        this.setPointTemp = setPointTemp;
    }

    public String getControlPhase() {
        return this.controlPhase;
    }

    public void setControlPhase(String controlPhase) {
        this.controlPhase = controlPhase;
    }

    public Boolean getCurrentUserGeoStale() {
        return this.currentUserGeoStale;
    }

    public void setCurrentUserGeoStale(Boolean currentUserGeoStale) {
        this.currentUserGeoStale = currentUserGeoStale;
    }

    public Boolean getCurrentUserPrivacyEnabled() {
        return this.currentUserPrivacyEnabled;
    }

    public void setCurrentUserPrivacyEnabled(Boolean currentUserPrivacyEnabled) {
        this.currentUserPrivacyEnabled = currentUserPrivacyEnabled;
    }

    public Boolean getDeviceUpdating() {
        return this.deviceUpdating;
    }

    public void setDeviceUpdating(Boolean deviceUpdating) {
        this.deviceUpdating = deviceUpdating;
    }

    public String getAutoOperation() {
        return this.autoOperation;
    }

    public void setAutoOperation(String autoOperation) {
        this.autoOperation = autoOperation;
    }

    public Boolean getPreheating() {
        return this.preheating;
    }

    public void setPreheating(Boolean preheating) {
        this.preheating = preheating;
    }

    public String getInternetGatewayType() {
        return this.internetGatewayType;
    }

    public void setInternetGatewayType(String internetGatewayType) {
        this.internetGatewayType = internetGatewayType;
    }

    public Boolean getInternetGatewayConnected() {
        return this.internetGatewayConnected;
    }

    public void setInternetGatewayConnected(Boolean internetGatewayConnected) {
        this.internetGatewayConnected = internetGatewayConnected;
    }

    public String getHeatingMuscleType() {
        return this.heatingMuscleType;
    }

    public void setHeatingMuscleType(String heatingMuscleType) {
        this.heatingMuscleType = heatingMuscleType;
    }

    public Boolean getHeatingMuscleConnected() {
        return this.heatingMuscleConnected;
    }

    public void setHeatingMuscleConnected(Boolean heatingMuscleConnected) {
        this.heatingMuscleConnected = heatingMuscleConnected;
    }

    public String getInsideTemperatureSensorType() {
        return this.insideTemperatureSensorType;
    }

    public void setInsideTemperatureSensorType(String insideTemperatureSensorType) {
        this.insideTemperatureSensorType = insideTemperatureSensorType;
    }

    public Boolean getInsideTemperatureSensorConnected() {
        return this.insideTemperatureSensorConnected;
    }

    public void setInsideTemperatureSensorConnected(Boolean insideTemperatureSensorConnected) {
        this.insideTemperatureSensorConnected = insideTemperatureSensorConnected;
    }

    public Boolean getSettingsEnabled() {
        return this.settingsEnabled;
    }

    public void setSettingsEnabled(Boolean settingsEnabled) {
        this.settingsEnabled = settingsEnabled;
    }

    public Boolean getFallbackOperation() {
        return this.fallbackOperation;
    }

    public void setFallbackOperation(Boolean fallbackOperation) {
        this.fallbackOperation = fallbackOperation;
    }

    public String getHelpUrl() {
        return this.helpUrl;
    }

    public void setHelpUrl(String helpUrl) {
        this.helpUrl = helpUrl;
    }

    public Boolean getHeatingOn() {
        return this.heatingOn;
    }

    public void setHeatingOn(Boolean heatingOn) {
        this.heatingOn = heatingOn;
    }

    public static void storeCurrentState(Context applicationContext, CurrentState state) {
        Editor editor = applicationContext.getSharedPreferences(PREFERENCES, 0).edit();
        if (state.getOperation() != null) {
            editor.putString("operation", state.getOperation());
        }
        if (state.getOperation() != null) {
            editor.putString("autoOperation", state.getAutoOperation());
        }
        if (state.getOperationTrigger() != null) {
            editor.putString("operationTrigger", state.getOperationTrigger());
        }
        if (state.getInsideTemp() != null) {
            editor.putFloat("insideTemp", state.getInsideTemp().floatValue());
        }
        if (state.getSetPointTemp() != null) {
            editor.putFloat("setPointTemp", state.getSetPointTemp().floatValue());
        }
        if (state.getControlPhase() != null) {
            editor.putString("controlPhase", state.getControlPhase());
        }
        if (state.getCurrentUserGeoStale() != null) {
            editor.putBoolean("currentUserGeoStale", state.getCurrentUserGeoStale().booleanValue());
        }
        if (state.getCurrentUserPrivacyEnabled() != null) {
            editor.putBoolean("currentUserPrivacyEnabled", state.getCurrentUserPrivacyEnabled().booleanValue());
        }
        if (state.getFallbackOperation() != null) {
            editor.putBoolean("fallbackOperation", state.getFallbackOperation().booleanValue());
        }
        if (state.getHeatingMuscleConnected() != null) {
            editor.putBoolean("heatingMuscleConnected", state.getHeatingMuscleConnected().booleanValue());
        }
        if (state.getInsideTemperatureSensorType() != null) {
            editor.putString("insideTemperatureSensorType", state.getInsideTemperatureSensorType());
        }
        if (state.getInternetGatewayConnected() != null) {
            editor.putBoolean("internetGatewayConnected", state.getInternetGatewayConnected().booleanValue());
        }
        if (state.getPreheating() != null) {
            editor.putBoolean("preheating", state.getPreheating().booleanValue());
        }
        if (state.getSettingsEnabled() != null) {
            editor.putBoolean("settingsEnabled", state.getSettingsEnabled().booleanValue());
        }
        if (state.getHeatingMuscleType() != null) {
            editor.putString("heatingMuscleType", state.getHeatingMuscleType());
        }
        if (state.getInsideTemperatureSensorConnected() != null) {
            editor.putBoolean("insideTemperatureSensorConnected", state.getInsideTemperatureSensorConnected().booleanValue());
        }
        if (state.getInternetGatewayType() != null) {
            editor.putString("internetGatewayType", state.getInternetGatewayType());
        }
        if (state.getDeviceUpdating() != null) {
            editor.putBoolean("deviceUpdating", state.getDeviceUpdating().booleanValue());
        }
        if (state.getHelpUrl() != null) {
            editor.putString("helpUrl", state.getHelpUrl());
        }
        if (state.getHeatingOn() != null) {
            editor.putBoolean("heatingOn", state.getHeatingOn().booleanValue());
        }
        if (state.getHomeId() != null) {
            editor.putInt("homeId", state.getHomeId().intValue());
        }
        editor.apply();
    }

    public static CurrentState generateFromPreferences(Context applicationContext) {
        SharedPreferences preferences = applicationContext.getSharedPreferences(PREFERENCES, 0);
        CurrentState state = new CurrentState();
        state.setOperation(preferences.getString("operation", Constants.UNDEFINED));
        state.setAutoOperation(preferences.getString("autoOperation", Constants.UNDEFINED));
        state.setOperationTrigger(preferences.getString("operationTrigger", "SYSTEM"));
        state.setInsideTemp(Float.valueOf(preferences.getFloat("insideTemp", 15.0f)));
        state.setSetPointTemp(Float.valueOf(preferences.getFloat("setPointTemp", 15.0f)));
        state.setControlPhase(preferences.getString("controlPhase", Constants.UNDEFINED));
        state.setCurrentUserGeoStale(Boolean.valueOf(preferences.getBoolean("currentUserGeoStale", false)));
        state.setCurrentUserPrivacyEnabled(Boolean.valueOf(preferences.getBoolean("currentUserPrivacyEnabled", false)));
        state.setDeviceUpdating(Boolean.valueOf(preferences.getBoolean("deviceUpdating", false)));
        state.setFallbackOperation(Boolean.valueOf(preferences.getBoolean("fallbackOperation", false)));
        state.setHeatingMuscleConnected(Boolean.valueOf(preferences.getBoolean("heatingMuscleConnected", true)));
        state.setHeatingMuscleType(preferences.getString("heatingMuscleType", ""));
        state.setInsideTemperatureSensorConnected(Boolean.valueOf(preferences.getBoolean("insideTemperatureSensorConnected", true)));
        state.setInsideTemperatureSensorType(preferences.getString("insideTemperatureSensorType", ""));
        state.setPreheating(Boolean.valueOf(preferences.getBoolean("preheating", false)));
        state.setSettingsEnabled(Boolean.valueOf(preferences.getBoolean("settingsEnabled", true)));
        state.setInternetGatewayConnected(Boolean.valueOf(preferences.getBoolean("internetGatewayConnected", true)));
        state.setInternetGatewayType(preferences.getString("internetGatewayType", ""));
        state.setHelpUrl(preferences.getString("helpUrl", ""));
        state.setHeatingOn(Boolean.valueOf(preferences.getBoolean("heatingOn", false)));
        return state;
    }

    public boolean isEqualTo(CurrentState other) {
        if (this != null && other != null && getOperation().equalsIgnoreCase(other.getOperation()) && getAutoOperation().equalsIgnoreCase(other.getAutoOperation()) && getOperationTrigger().equalsIgnoreCase(other.getOperationTrigger()) && other.getInsideTemp() != null && ((double) Math.abs(getInsideTemp().floatValue() - other.getInsideTemp().floatValue())) <= 0.1d && other.getSetPointTemp() != null && ((double) Math.abs(getSetPointTemp().floatValue() - other.getSetPointTemp().floatValue())) <= 0.1d && getControlPhase().equalsIgnoreCase(other.getControlPhase()) && getCurrentUserGeoStale() == other.getCurrentUserGeoStale() && getCurrentUserPrivacyEnabled() == other.getCurrentUserPrivacyEnabled() && getDeviceUpdating() == other.getDeviceUpdating() && getFallbackOperation() == other.getFallbackOperation() && getHeatingMuscleConnected() == other.getHeatingMuscleConnected() && getInsideTemperatureSensorConnected() == other.getInsideTemperatureSensorConnected() && getInternetGatewayConnected() == other.getInternetGatewayConnected() && getPreheating() == other.getPreheating() && getSettingsEnabled() == other.getSettingsEnabled() && getHeatingMuscleType().equalsIgnoreCase(other.getHeatingMuscleType()) && getInsideTemperatureSensorType().equalsIgnoreCase(other.getInsideTemperatureSensorType()) && getInternetGatewayType().equalsIgnoreCase(other.getInternetGatewayType()) && getHelpUrl().equalsIgnoreCase(other.getHelpUrl()) && getHeatingOn() == other.getHeatingOn() && getHomeId() == other.getHomeId()) {
            return true;
        }
        return false;
    }

    public Integer getHomeId() {
        return this.homeId;
    }

    public void setHomeId(Integer homeId) {
        this.homeId = homeId;
    }
}
