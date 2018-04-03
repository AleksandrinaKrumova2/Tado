package com.tado.android.installation.srt.common;

import android.support.annotation.StringRes;
import com.tado.android.rest.model.hvac.BridgeReplacementInstallation;
import com.tado.android.rest.model.installation.GenericHardwareDevice;

public interface SrtNavigationCallback {
    void onAssignedZone(int i);

    void onBackFinishHvacStep();

    void onBackStep();

    void onNextFinishHvacStep();

    void onRegisteredDevice(GenericHardwareDevice genericHardwareDevice);

    void onReplacementCreated(BridgeReplacementInstallation bridgeReplacementInstallation);

    void onServerCallFailure();

    void setNextButtonState(boolean z);

    void setNextButtonText(@StringRes int i);
}
