package com.tado.android.rest.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.tado.android.rest.model.installation.CoolingZoneSetting;
import com.tado.android.rest.model.installation.GenericZoneSetting;
import com.tado.android.rest.model.installation.GenericZoneSetting.TypeEnum;
import com.tado.android.rest.model.installation.HeatingZoneSetting;
import com.tado.android.rest.model.installation.HotWaterZoneSetting;
import com.tado.android.utils.Constants;
import com.tado.android.utils.ResourceFactory;
import com.tado.android.utils.Snitcher;
import java.lang.reflect.Type;

public class ZoneSettingClassAdapter implements JsonDeserializer<GenericZoneSetting>, JsonSerializer<GenericZoneSetting> {
    public GenericZoneSetting deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String classType;
        JsonObject jsonObject = json.getAsJsonObject();
        TypeEnum type = TypeEnum.valueOf(jsonObject.get("type").getAsString());
        if (type == TypeEnum.AIR_CONDITIONING) {
            classType = CoolingZoneSetting.class.getName();
        } else if (type == TypeEnum.HEATING) {
            classType = HeatingZoneSetting.class.getName();
        } else {
            classType = HotWaterZoneSetting.class.getName();
        }
        try {
            return (GenericZoneSetting) context.deserialize(jsonObject, Class.forName(classType));
        } catch (ClassNotFoundException e) {
            Snitcher.start().toCrashlytics().toLogger().logException("Unknown zoneSettings type: " + type, e);
            throw new JsonParseException("Unknown zoneSettings type: " + type, e);
        }
    }

    public JsonElement serialize(GenericZoneSetting src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", src.getType().name());
        jsonObject.addProperty("power", src.getPower());
        JsonObject jsonTemperatureObject = new JsonObject();
        if (src.getPowerBoolean()) {
            if (src.getTemperature() != null) {
                jsonTemperatureObject.addProperty(Constants.CELSIUS, Float.valueOf(src.getTemperature().getCelsius()));
                jsonTemperatureObject.addProperty(Constants.FAHRENHEIT, Float.valueOf(src.getTemperature().getFahrenheit()));
                jsonObject.add(ResourceFactory.MODE_ATTRIBUTE_TEMPERATURE, jsonTemperatureObject);
            }
            if (src instanceof CoolingZoneSetting) {
                CoolingZoneSetting coolingZoneSettingSrc = (CoolingZoneSetting) src;
                jsonObject.addProperty("mode", coolingZoneSettingSrc.getMode().name());
                if (coolingZoneSettingSrc.getFanSpeed() != null) {
                    jsonObject.addProperty("fanSpeed", coolingZoneSettingSrc.getFanSpeed().name());
                }
                if (coolingZoneSettingSrc.getSwing() != null) {
                    jsonObject.addProperty(ResourceFactory.MODE_ATTRIBUTE_SWING, coolingZoneSettingSrc.getSwing());
                }
            }
        }
        return jsonObject;
    }
}
