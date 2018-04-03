package com.tado.android.installation.acsetup;

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

public class ACSetupSuccessfulActivity extends ACInstallationBaseActivity {

    class C08311 extends TadoCallback<InstallationStateTransitionResult> {
        C08311() {
        }

        public void onResponse(Call<InstallationStateTransitionResult> call, Response<InstallationStateTransitionResult> response) {
            super.onResponse(call, response);
            ACSetupSuccessfulActivity.this.dismissLoadingUI();
            if (response.isSuccessful()) {
                InstallationProcessController.getInstallationProcessController().goToScreenForInstallationProcessStatus(ACSetupSuccessfulActivity.this, ((InstallationStateTransitionResult) response.body()).getInstallation());
                return;
            }
            InstallationProcessController.getInstallationProcessController().detectStatus(ACSetupSuccessfulActivity.this);
        }

        public void onFailure(Call<InstallationStateTransitionResult> call, Throwable t) {
            super.onFailure(call, t);
            ACSetupSuccessfulActivity.this.dismissLoadingUI();
            InstallationProcessController.showConnectionErrorRetrofit(ACSetupSuccessfulActivity.this, call, this);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_acsetup_successful);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_uploadCommandTable_title);
        this.titleTemplateTextview.setText(C0676R.string.installation_sacc_uploadCommandTable_completed_message);
        this.proceedButton.setText(C0676R.string.installation_sacc_uploadCommandTable_completed_confirmButton);
        this.centerImage.setImageResource(C0676R.drawable.ac_complet);
    }

    public void proceedClick(View view) {
        showLoadingUI();
        RestServiceGenerator.getTadoInstallationRestService().confirmCommandTableUpload(UserConfig.getHomeId(), Integer.valueOf(InstallationProcessController.getInstallationProcessController().getInstallationId()), new Object()).enqueue(new C08311());
    }
}
