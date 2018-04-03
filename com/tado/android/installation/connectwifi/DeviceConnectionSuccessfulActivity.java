package com.tado.android.installation.connectwifi;

import android.os.Bundle;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallStatusPollingController;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.installation.AcInstallation;
import com.tado.android.rest.model.installation.InstallationStateTransitionResult;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.UserConfig;
import retrofit2.Call;
import retrofit2.Response;

public class DeviceConnectionSuccessfulActivity extends ACInstallationBaseActivity {
    boolean mIsReadyToInstall = false;

    class C09291 extends TadoCallback<InstallationStateTransitionResult> {
        C09291() {
        }

        public void onResponse(Call<InstallationStateTransitionResult> call, Response<InstallationStateTransitionResult> response) {
            super.onResponse(call, response);
            DeviceConnectionSuccessfulActivity.this.proceedButton.setEnabled(true);
            if (response.isSuccessful()) {
                AcInstallation installationStatus = ((InstallationStateTransitionResult) response.body()).getInstallation();
                if (installationStatus != null) {
                    InstallationProcessController.getInstallationProcessController().goToScreenForInstallationProcessStatus(DeviceConnectionSuccessfulActivity.this, installationStatus);
                    return;
                } else {
                    InstallationProcessController.getInstallationProcessController().detectStatus(DeviceConnectionSuccessfulActivity.this);
                    return;
                }
            }
            DeviceConnectionSuccessfulActivity.this.handleServerError(this.serverError, DeviceConnectionSuccessfulActivity.this, call, response.code(), this);
        }

        public void onFailure(Call<InstallationStateTransitionResult> call, Throwable t) {
            super.onFailure(call, t);
            DeviceConnectionSuccessfulActivity.this.proceedButton.setEnabled(true);
            InstallationProcessController.showConnectionErrorRetrofit(DeviceConnectionSuccessfulActivity.this, call, this);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.fragment_device_connection_successful);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_connectWifi_serverConnection_title);
        this.titleTemplateTextview.setText(C0676R.string.installation_sacc_connectWifi_serverConnection_connected_title);
        this.textView.setText(C0676R.string.installation_sacc_connectWifi_serverConnection_connected_message);
        this.proceedButton.setText(C0676R.string.installation_sacc_connectWifi_serverConnection_connected_confirmButton);
        this.centerImageOverlay.setImageResource(C0676R.drawable.device_server_connected);
        this.centerImageOverlay.setVisibility(0);
    }

    public void proceedClick(View view) {
        if (InstallStatusPollingController.getInstallationProcessController().isResetWifiCredentials()) {
            InstallStatusPollingController.getInstallationProcessController().doneResetWifiCredentials();
            InstallationProcessController.getInstallationProcessController().detectStatus(this);
            return;
        }
        sendRequest();
    }

    private void sendRequest() {
        this.proceedButton.setEnabled(false);
        RestServiceGenerator.getTadoInstallationRestService().confirmWirelessRemoteInstallation(UserConfig.getHomeId(), Integer.valueOf(InstallationProcessController.getInstallationProcessController().getInstallationId())).enqueue(new C09291());
    }
}
