package com.tado.android.entities;

public class RecordState {
    public static final String FAILED = "FAILED";
    public static final String FINISHED = "FINISHED";
    public static final String IN_PROGRESS = "IN_PROGRESS";
    public static final String NOT_STARTED = "NOT_STARTED";
    private String state;

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
