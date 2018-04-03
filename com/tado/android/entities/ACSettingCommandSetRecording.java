package com.tado.android.entities;

import java.util.Map;

public class ACSettingCommandSetRecording {
    ACModeRecording[] acModeRecordings;
    Map<String, ACModeRecordingCapability[]> capabilitiesToRecord;
    ServerError[] errors;
    int id;
    Map<String, ACModeRecordingCapability[]> recordedCapabilities;
    String temperatureUnit;

    public ServerError[] getErrors() {
        return this.errors;
    }

    public void setErrors(ServerError[] errors) {
        this.errors = errors;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ACModeRecording[] getAcModeRecordings() {
        return this.acModeRecordings;
    }

    public void setAcModeRecordings(ACModeRecording[] acModeRecordings) {
        this.acModeRecordings = acModeRecordings;
    }

    public String getTemperatureUnit() {
        return this.temperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    public Map<String, ACModeRecordingCapability[]> getRecordedCapabilities() {
        return this.recordedCapabilities;
    }

    public void setRecordedCapabilities(Map<String, ACModeRecordingCapability[]> recordedCapabilities) {
        this.recordedCapabilities = recordedCapabilities;
    }

    public Map<String, ACModeRecordingCapability[]> getCapabilitiesToRecord() {
        return this.capabilitiesToRecord;
    }

    public void setCapabilitiesToRecord(Map<String, ACModeRecordingCapability[]> capabilitiesToRecord) {
        this.capabilitiesToRecord = capabilitiesToRecord;
    }
}
