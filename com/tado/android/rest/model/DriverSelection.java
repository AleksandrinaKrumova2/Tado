package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;

public class DriverSelection {
    @SerializedName("discriminator")
    private int discriminator;

    public void setDiscriminator(int discriminator) {
        this.discriminator = discriminator;
    }
}
