package com.tado.android.settings.cooling;

public interface AcConfigurationUpdatePollingHelperInterface {
    void onFailed();

    void onInProgress();

    void onSuccess();
}
