package com.tado.android.rest.model.hvac;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MountingState implements Serializable {
    @SerializedName("value")
    @Expose
    private MountingStateEnum value;

    public enum MountingStateEnum {
        UNMOUNTED,
        CALIBRATING,
        CALIBRATED,
        CALIBRATION_FAILED
    }

    public MountingStateEnum getValue() {
        return this.value;
    }

    public boolean isCalibrated() {
        return MountingStateEnum.CALIBRATED == this.value;
    }
}
