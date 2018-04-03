package com.tado.android.rest.model;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

public class CommandSet {
    @Expose
    private Integer code;
    @Expose
    private String commandType;
    @Expose
    private Id id;
    @Expose
    private String name;
    @Expose
    private String origin;
    @Expose
    private List<String> supportedModes = new ArrayList();
    @Expose
    private String temperatureUnit;

    public Id getId() {
        return this.id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getTemperatureUnit() {
        return this.temperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    public List<String> getSupportedModes() {
        return this.supportedModes;
    }

    public void setSupportedModes(List<String> supportedModes) {
        this.supportedModes = supportedModes;
    }

    public String getCommandType() {
        return this.commandType;
    }

    public Integer getCode() {
        return this.code;
    }
}
