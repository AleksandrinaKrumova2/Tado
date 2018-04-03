package com.tado.android.entities;

public class TeachingMode {
    ServerError[] errors;
    String mode;
    String recordingState;
    TeachingRun[] runs;

    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getRecordingState() {
        return this.recordingState;
    }

    public void setRecordingState(String recordingState) {
        this.recordingState = recordingState;
    }

    public TeachingRun[] getRuns() {
        return this.runs;
    }

    public void setRuns(TeachingRun[] runs) {
        this.runs = runs;
    }

    public ServerError[] getErrors() {
        return this.errors;
    }

    public void setErrors(ServerError[] errors) {
        this.errors = errors;
    }
}
