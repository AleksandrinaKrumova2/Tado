package com.tado.android.entities;

public class UploadCommandSetState {
    public static final String FAILED = "FAILED";
    public static final String FINISH = "FINISHED";
    public static final String IN_PROGRESS = "IN_PROGRESS";
    private String state;

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public UploadCommandSetState(String state) {
        this.state = state;
    }
}
