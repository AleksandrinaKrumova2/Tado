package com.tado.android.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.tado.android.rest.model.Humidity;
import java.lang.reflect.Type;

public class HumidityDeserializer implements JsonDeserializer<Humidity> {
    public Humidity deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
        return new Humidity(element.getAsFloat());
    }
}
