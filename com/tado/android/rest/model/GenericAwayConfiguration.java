package com.tado.android.rest.model;

import android.support.annotation.NonNull;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.tado.android.rest.model.installation.GenericZoneSetting;
import com.tado.android.rest.model.installation.GenericZoneSetting.TypeEnum;
import java.io.Serializable;
import java.util.Observable;

public abstract class GenericAwayConfiguration extends Observable implements Serializable {
    @SerializedName("type")
    private AwayType type;
    private TypeEnum zoneType;

    enum AwayType {
        HEATING,
        FIXED_SETTING
    }

    public abstract void copyFromZoneSettings(@NonNull ZoneSetting zoneSetting);

    public abstract void prepareModelForUpdate();

    public abstract JsonObject serialize();

    public AwayType getType() {
        return this.type;
    }

    public void setType(AwayType type) {
        this.type = type;
    }

    public TypeEnum getZoneType() {
        return this.zoneType;
    }

    public GenericZoneSetting getSetting() {
        return null;
    }
}
