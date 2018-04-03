package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;

public class OverlayTerminationConditionResponse {
    @SerializedName("terminationCondition")
    OverlayTerminationCondition overlayTerminationCondition;

    public OverlayTerminationConditionResponse(OverlayTerminationCondition overlayTerminationCondition) {
        this.overlayTerminationCondition = overlayTerminationCondition;
    }

    public OverlayTerminationCondition getOverlayTerminationCondition() {
        return this.overlayTerminationCondition;
    }

    public void setOverlayTerminationCondition(OverlayTerminationCondition overlayTerminationCondition) {
        this.overlayTerminationCondition = overlayTerminationCondition;
    }
}
