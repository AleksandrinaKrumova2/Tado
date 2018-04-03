package com.tado.android.installation.complexteaching;

import android.os.Bundle;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.entities.ACSettingCommandSetRecording;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.service.RestServiceGenerator;
import retrofit2.Call;
import retrofit2.Response;

public class PointToACActivity extends ACInstallationBaseActivity {

    class C09081 extends TadoCallback<ACSettingCommandSetRecording> {
        C09081() {
        }

        public void onResponse(Call<ACSettingCommandSetRecording> call, Response<ACSettingCommandSetRecording> response) {
            super.onResponse(call, response);
            PointToACActivity.this.dismissLoadingUI();
            if (response.isSuccessful()) {
                ComplexTeachingController.getComplexTeachingController().initalize((ACSettingCommandSetRecording) response.body());
                InstallationProcessController.startActivity(PointToACActivity.this, TestACSettingCommandsActivity.class, true);
                return;
            }
            PointToACActivity.this.handleServerError(this.serverError, PointToACActivity.this, call, response.code(), this);
        }

        public void onFailure(Call<ACSettingCommandSetRecording> call, Throwable t) {
            super.onFailure(call, t);
            PointToACActivity.this.dismissLoadingUI();
            InstallationProcessController.showConnectionErrorRetrofit(PointToACActivity.this, call, this);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_point_to_ac);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_setupAC_testACSettingCommands_title);
        this.titleTemplateTextview.setText(C0676R.string.installation_sacc_setupAC_testACSettingCommands_pointTowardsAC_title);
        this.textView.setVisibility(8);
        this.centerImage.setImageResource(C0676R.drawable.test_tado_instruction);
        this.proceedButton.setText(C0676R.string.installation_sacc_setupAC_testACSettingCommands_pointTowardsAC_confirmButton);
    }

    public void proceedClick(View view) {
        loadAcModeRecordings();
    }

    private void loadAcModeRecordings() {
        showLoadingUI();
        RestServiceGenerator.getTadoInstallationRestService().showAcSettingCommandSetRecording(Long.valueOf((long) ComplexTeachingController.getComplexTeachingController().getRecordingId())).enqueue(new C09081());
    }
}
