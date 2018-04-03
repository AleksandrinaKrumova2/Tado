package com.tado.android.rest.model;

import com.google.gson.annotations.Expose;

@Deprecated
public class CommandSetId {
    @Expose
    private Integer code;
    @Expose
    private String commandType;

    public String getCommandType() {
        return this.commandType;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
