package com.tado.android.rest.model;

import com.google.gson.annotations.Expose;

public class CommandSetItem {
    @Expose
    private CommandSet commandSet;
    @Expose
    private Integer confidence;
    @Expose
    private Integer index;

    public Integer getIndex() {
        return this.index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getConfidence() {
        return this.confidence;
    }

    public void setConfidence(Integer confidence) {
        this.confidence = confidence;
    }

    public CommandSet getCommandSet() {
        return this.commandSet;
    }

    public void setCommandSet(CommandSet commandSet) {
        this.commandSet = commandSet;
    }
}
