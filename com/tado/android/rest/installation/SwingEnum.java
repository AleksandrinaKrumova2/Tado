package com.tado.android.rest.model.installation;

public enum SwingEnum {
    ON("ON"),
    OFF("OFF");
    
    private String value;

    private SwingEnum(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
