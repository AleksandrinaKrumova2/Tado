package com.tado.android.rest.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.tado.android.rest.model.DeviceType;
import java.lang.reflect.Type;

public class DeviceTypeDeserializer implements JsonDeserializer<DeviceType> {
    public DeviceType deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
        try {
            return DeviceType.valueOf(element.getAsString());
        } catch (Exception e) {
            return DeviceType.UNKNOWN;
        }
    }
}
