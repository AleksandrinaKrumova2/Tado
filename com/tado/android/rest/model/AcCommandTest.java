package com.tado.android.rest.model;

import com.google.gson.annotations.Expose;

public class AcCommandTest {
    @Expose
    private AcSetting acSetting;
    @Expose
    private CommandSetId commandSetId;

    public CommandSetId getCommandSetId() {
        return this.commandSetId;
    }

    public void setCommandSetId(CommandSetId commandSetId) {
        this.commandSetId = commandSetId;
    }

    public AcSetting getAcSetting() {
        return this.acSetting;
    }

    public void setAcSetting(AcSetting acSetting) {
        this.acSetting = acSetting;
    }
}
