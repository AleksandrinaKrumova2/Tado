package com.tado.android.rest.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.tado.android.rest.model.hvac.BridgeReplacementInstallation;
import com.tado.android.rest.model.installation.AcInstallation;
import com.tado.android.rest.model.installation.HeatingInstallation;
import com.tado.android.rest.model.installation.Installation;
import com.tado.android.rest.model.installation.Installation.TypeEnum;
import com.tado.android.rest.model.installation.SaleFittingInstallation;
import com.tado.android.utils.Snitcher;
import java.lang.reflect.Type;

public class InstallationClassAdapter implements JsonDeserializer<Installation> {
    public Installation deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        if (type.equalsIgnoreCase(TypeEnum.INSTALL_AC_G1.toString())) {
            type = AcInstallation.class.getName();
        } else if (type.equalsIgnoreCase(TypeEnum.SALE_FITTING_ST_G1.toString())) {
            type = SaleFittingInstallation.class.getName();
        } else if (type.equalsIgnoreCase(TypeEnum.REPLACE_BRIDGE.toString())) {
            type = BridgeReplacementInstallation.class.getName();
        } else {
            type = HeatingInstallation.class.getName();
        }
        try {
            return (Installation) context.deserialize(jsonObject, Class.forName(type));
        } catch (ClassNotFoundException e) {
            Snitcher.start().toCrashlytics().toLogger().logException("Unknown installation type: " + type, e);
            throw new JsonParseException("Unknown installation type: " + type, e);
        }
    }
}
