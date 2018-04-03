package com.tado.android.entities;

public class TeachingStep {
    ACSetting acSettingToRecord;
    ACSetting acSettingToStartFrom;
    String keyToPressToRecordThisCommand;
    String recordingState;

    public ACSetting getAcSettingToStartFrom() {
        return this.acSettingToStartFrom;
    }

    public void setAcSettingToStartFrom(ACSetting acSettingToStartFrom) {
        this.acSettingToStartFrom = acSettingToStartFrom;
    }

    public ACSetting getAcSettingToRecord() {
        return this.acSettingToRecord;
    }

    public void setAcSettingToRecord(ACSetting acSettingToRecord) {
        this.acSettingToRecord = acSettingToRecord;
    }

    public String getRecordingState() {
        return this.recordingState;
    }

    public void setRecordingState(String recordingState) {
        this.recordingState = recordingState;
    }

    public String getKeyToPressToRecordThisCommand() {
        return this.keyToPressToRecordThisCommand;
    }

    public void setKeyToPressToRecordThisCommand(String keyToPressToRecordThisCommand) {
        this.keyToPressToRecordThisCommand = keyToPressToRecordThisCommand;
    }
}
