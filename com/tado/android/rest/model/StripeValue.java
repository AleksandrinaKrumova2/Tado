package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import com.tado.android.rest.model.installation.GenericZoneSetting;

public class StripeValue {
    @SerializedName("setting")
    GenericZoneSetting setting;
    @SerializedName("stripeType")
    StripeTypeEnum type;

    public StripeTypeEnum getType() {
        return this.type;
    }

    public GenericZoneSetting getSetting() {
        return this.setting;
    }
}
