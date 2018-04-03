package com.tado.android.installation.connectwifi;

import android.annotation.SuppressLint;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallStatusPollingController;

@SuppressLint({"Registered"})
public class DeviceStatePollingActivity extends ACInstallationBaseActivity {
    protected void onResume() {
        super.onResume();
        InstallStatusPollingController.getInstallationProcessController().pollInstallationStatus(this);
    }

    protected void onPause() {
        super.onPause();
        InstallStatusPollingController.getInstallationProcessController().stopPolling();
    }
}
