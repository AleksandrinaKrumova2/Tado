package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import com.tado.android.entities.ConfirmedCommandSet;
import java.io.Serializable;

public class CommandConfiguration implements Serializable {
    @SerializedName("acCommandSet")
    private ConfirmedCommandSet mAcCommandSet;
    @SerializedName("dedicatedOnCommand")
    private boolean mDedicatedOnCommand;

    public ConfirmedCommandSet getAcCommandSet() {
        return this.mAcCommandSet;
    }

    public void setAcCommandSet(ConfirmedCommandSet acCommandSet) {
        this.mAcCommandSet = acCommandSet;
    }

    public boolean isDedicatedOnCommand() {
        return this.mDedicatedOnCommand;
    }

    public void setDedicatedOnCommand(boolean dedicatedOnCommand) {
        this.mDedicatedOnCommand = dedicatedOnCommand;
    }
}
