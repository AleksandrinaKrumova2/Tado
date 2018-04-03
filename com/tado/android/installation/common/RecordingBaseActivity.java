package com.tado.android.installation.common;

import com.tado.android.entities.ACSettingCommandSetRecording;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.installation.complexteaching.ComplexTeachingController;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.installation.AcInstallation;
import com.tado.android.rest.model.installation.InstallationStateTransitionResult;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.times.view.model.ModeEnum;
import com.tado.android.utils.UserConfig;
import retrofit2.Call;
import retrofit2.Response;

public class RecordingBaseActivity extends ACInstallationBaseActivity {

    class C08912 extends TadoCallback<InstallationStateTransitionResult> {
        C08912() {
        }

        public void onResponse(Call<InstallationStateTransitionResult> call, Response<InstallationStateTransitionResult> response) {
            RecordingBaseActivity.this.dismissLoadingUI();
            if (response.isSuccessful()) {
                AcInstallation installation = ((InstallationStateTransitionResult) response.body()).getInstallation();
                if (installation != null) {
                    InstallationProcessController.getInstallationProcessController().goToScreenForInstallationProcessStatus(RecordingBaseActivity.this, installation);
                    return;
                } else {
                    InstallationProcessController.getInstallationProcessController().detectStatus(RecordingBaseActivity.this);
                    return;
                }
            }
            RecordingBaseActivity.this.handleServerError(this.serverError, RecordingBaseActivity.this, call, response.code(), this);
        }

        public void onFailure(Call<InstallationStateTransitionResult> call, Throwable t) {
            super.onFailure(call, t);
            RecordingBaseActivity.this.dismissLoadingUI();
            InstallationProcessController.showConnectionErrorRetrofit(RecordingBaseActivity.this, call, this);
        }
    }

    public void startRecoding(Long recordingId, final ModeEnum modeEnum, final boolean resetAllModes) {
        RestServiceGenerator.getTadoInstallationRestService().showAcSettingCommandSetRecording(recordingId).enqueue(new TadoCallback<ACSettingCommandSetRecording>() {
            public void onResponse(Call<ACSettingCommandSetRecording> call, Response<ACSettingCommandSetRecording> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    ComplexTeachingController.getComplexTeachingController().initializeCLCRecording((ACSettingCommandSetRecording) response.body(), modeEnum, resetAllModes);
                    ComplexTeachingController.getComplexTeachingController().goToNextCapabilitiesScreen(RecordingBaseActivity.this, true);
                    return;
                }
                RecordingBaseActivity.this.handleServerError(this.serverError, RecordingBaseActivity.this, call, response.code(), this);
            }

            public void onFailure(Call<ACSettingCommandSetRecording> call, Throwable t) {
                super.onFailure(call, t);
                InstallationProcessController.showConnectionErrorRetrofit(RecordingBaseActivity.this, call, this);
            }
        });
    }

    public void endCLCTeaching() {
        showLoadingUI();
        RestServiceGenerator.getTadoInstallationRestService().confirmCLCRecording(UserConfig.getHomeId(), Integer.valueOf(InstallationProcessController.getInstallationProcessController().getInstallationId()), new Object()).enqueue(new C08912());
    }
}
