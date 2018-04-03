package com.tado.android.rest.model.installation;

public enum CommandTypeEnum {
    AC_SETTING("AC_SETTING"),
    KEY("KEY");
    
    private String value;

    private CommandTypeEnum(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
