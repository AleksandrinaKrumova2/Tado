package com.tado.android.entities;

public class TeachingRun {
    String recordingState;
    TeachingStep[] steps;
    TeachingRunSummary summary;

    public String getRecordingState() {
        return this.recordingState;
    }

    public void setRecordingState(String recordingState) {
        this.recordingState = recordingState;
    }

    public TeachingStep[] getSteps() {
        return this.steps;
    }

    public void setSteps(TeachingStep[] steps) {
        this.steps = steps;
    }

    public TeachingRunSummary getSummary() {
        return this.summary;
    }

    public void setSummary(TeachingRunSummary summary) {
        this.summary = summary;
    }
}
