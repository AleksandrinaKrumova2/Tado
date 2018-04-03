package com.tado.android.installation.connectwifi;

import android.os.Bundle;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.installation.InstallationStateTransitionResult;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.UserConfig;
import retrofit2.Call;
import retrofit2.Response;

public class UpdateFirmwareSuccessfulActivity extends ACInstallationBaseActivity {

    class C09421 extends TadoCallback<InstallationStateTransitionResult> {
        C09421() {
        }

        public void onResponse(Call<InstallationStateTransitionResult> call, Response<InstallationStateTransitionResult> response) {
            super.onResponse(call, response);
            UpdateFirmwareSuccessfulActivity.this.dismissLoadingUI();
            if (response.isSuccessful()) {
                InstallationStateTransitionResult installation = (InstallationStateTransitionResult) response.body();
                if (installation != null) {
                    InstallationProcessController.getInstallationProcessController().goToScreenForInstallationProcessStatus(UpdateFirmwareSuccessfulActivity.this, installation.getInstallation());
                    return;
                } else {
                    InstallationProcessController.getInstallationProcessController().detectStatus(UpdateFirmwareSuccessfulActivity.this);
                    return;
                }
            }
            UpdateFirmwareSuccessfulActivity.this.handleServerError(this.serverError, UpdateFirmwareSuccessfulActivity.this, call, response.code(), this);
        }

        public void onFailure(Call<InstallationStateTransitionResult> call, Throwable t) {
            super.onFailure(call, t);
            UpdateFirmwareSuccessfulActivity.this.dismissLoadingUI();
            InstallationProcessController.showConnectionErrorRetrofit(UpdateFirmwareSuccessfulActivity.this, call, this);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.fragment_update_firmware_successful);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_firmwareUpdate_title);
        this.titleTemplateTextview.setText(C0676R.string.installation_sacc_firmwareUpdate_message);
        this.textView.setText(C0676R.string.installation_sacc_firmwareUpdate_successLabel);
        this.proceedButton.setText(C0676R.string.installation_sacc_firmwareUpdate_confirmButton);
        this.centerImageOverlay.setImageResource(C0676R.drawable.device_download);
        this.centerImageOverlay.setVisibility(0);
    }

    public void proceedClick(View view) {
        showLoadingUI();
        RestServiceGenerator.getTadoInstallationRestService().confirmWirelessRemoteFirmwareUpdate(UserConfig.getHomeId(), Integer.valueOf(InstallationProcessController.getInstallationProcessController().getInstallationId()), new Object()).enqueue(new C09421());
    }
}
