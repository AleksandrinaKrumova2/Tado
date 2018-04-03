package com.tado.android.installation.srt.common;

import com.tado.android.rest.model.hvac.StateLookup.StateLookupEnum;
import com.tado.android.rest.model.hvac.Step;

public interface HvacStepCallback {
    void onDevicePropertyPanelValueChanged(String str, StateLookupEnum stateLookupEnum);

    void onFailure();

    void onHvacBack();

    void onHvacNext();

    void onHvacStep(Step step);
}
