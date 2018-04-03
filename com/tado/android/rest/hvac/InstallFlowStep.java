package com.tado.android.rest.model.hvac;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InstallFlowStep {
    @SerializedName("state")
    @Expose
    private State mState;
    @SerializedName("status")
    @Expose
    private HvacStepStatus status;
    @SerializedName("step")
    @Expose
    private Step step;

    public HvacStepStatus getStatus() {
        return this.status;
    }

    public Step getStep() {
        return this.step;
    }

    public State getState() {
        return this.mState;
    }
}
