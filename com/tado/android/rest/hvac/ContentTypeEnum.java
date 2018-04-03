package com.tado.android.rest.model.hvac;

public enum ContentTypeEnum {
    TEXT("Text"),
    PICTURE("Picture"),
    DEVICE_PROPERTY_PANEL("DevicePropertyPanel"),
    ALERT("Alert");
    
    private String value;

    private ContentTypeEnum(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
