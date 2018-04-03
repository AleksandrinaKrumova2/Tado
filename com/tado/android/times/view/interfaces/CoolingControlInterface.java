package com.tado.android.times.view.interfaces;

import com.tado.android.entities.CoolingState;
import com.tado.android.times.view.model.ModeEnum;

public interface CoolingControlInterface {
    void initButtonsLayoutSelectedState(int i);

    void initComponentAvailability(boolean z);

    void initModeLayout(ModeEnum modeEnum, CoolingState coolingState);
}
