package com.tado.android.rest.model.installation;

public enum FanSpeedEnum {
    LOW("LOW"),
    MIDDLE("MIDDLE"),
    HIGH("HIGH"),
    AUTO("AUTO");
    
    private String value;

    private FanSpeedEnum(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
