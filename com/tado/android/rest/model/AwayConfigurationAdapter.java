package com.tado.android.rest.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.tado.android.rest.model.installation.GenericZoneSetting.TypeEnum;
import com.tado.android.utils.Snitcher;
import java.lang.reflect.Type;

public class AwayConfigurationAdapter implements JsonDeserializer<GenericAwayConfiguration>, JsonSerializer<GenericAwayConfiguration> {
    public GenericAwayConfiguration deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String classType;
        JsonObject jsonObject = json.getAsJsonObject();
        if (AwayType.valueOf(jsonObject.get("type").getAsString()) == AwayType.HEATING) {
            classType = HeatingAwayConfiguration.class.getName();
        } else if (TypeEnum.valueOf(jsonObject.getAsJsonObject("setting").get("type").getAsString()) == TypeEnum.AIR_CONDITIONING) {
            classType = CoolingAwayConfiguration.class.getName();
        } else {
            classType = HotWaterAwayConfiguration.class.getName();
        }
        try {
            return (GenericAwayConfiguration) context.deserialize(jsonObject, Class.forName(classType));
        } catch (ClassNotFoundException e) {
            Snitcher.start().toCrashlytics().toLogger().logException("Unknown awayConfiguration type: 16843169", e);
            throw new JsonParseException("Unknown awayConfiguration type: 16843169", e);
        }
    }

    public JsonElement serialize(GenericAwayConfiguration src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = src.serialize();
        jsonObject.addProperty("type", src.getType().name());
        return jsonObject;
    }
}
