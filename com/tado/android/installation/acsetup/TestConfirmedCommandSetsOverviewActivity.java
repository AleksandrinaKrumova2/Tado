package com.tado.android.installation.acsetup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.entities.CandidateRating;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.installation.AcCommandSet;
import com.tado.android.rest.model.installation.AvailableCommandSets;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.UserConfig;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

public class TestConfirmedCommandSetsOverviewActivity extends ACInstallationBaseActivity {

    class C08551 extends TadoCallback<AvailableCommandSets> {
        C08551() {
        }

        public void onResponse(Call<AvailableCommandSets> call, Response<AvailableCommandSets> response) {
            super.onResponse(call, response);
            TestConfirmedCommandSetsOverviewActivity.this.dismissLoadingUI();
            if (response.isSuccessful()) {
                List<AcCommandSet> listConfirmedCommandSets = ((AvailableCommandSets) response.body()).getAvailableCommandSets();
                TestConfirmedCommandSetsOverviewActivity.this.titleTemplateTextview.setText(TestConfirmedCommandSetsOverviewActivity.this.getString(C0676R.string.installation_sacc_setupAC_selectCommandSet_testingExplanation_message, new Object[]{Integer.valueOf(listConfirmedCommandSets.size())}));
                return;
            }
            TestConfirmedCommandSetsOverviewActivity.this.handleServerError(this.serverError, TestConfirmedCommandSetsOverviewActivity.this, call, response.code(), this);
        }

        public void onFailure(Call<AvailableCommandSets> call, Throwable t) {
            super.onFailure(call, t);
            TestConfirmedCommandSetsOverviewActivity.this.dismissLoadingUI();
            InstallationProcessController.showConnectionErrorRetrofit(TestConfirmedCommandSetsOverviewActivity.this, call, this);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_test_confirmed_command_sets_overview);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_setupAC_selectCommandSet_title);
        this.centerImage.setImageResource(C0676R.drawable.test_tado_instruction);
        this.proceedButton.setText(C0676R.string.installation_sacc_setupAC_selectCommandSet_testingExplanation_confirmButton);
        callGetCommandSetsApi();
        InstallationProcessController.getInstallationProcessController().setSelectedAcCommandSet(null);
    }

    public void proceedClick(View view) {
        InstallationProcessController.startActivity((Activity) this, TestConfirmedCommandSetsNoteActivity.class, false);
    }

    private void callGetCommandSetsApi() {
        showLoadingUI();
        RestServiceGenerator.getCredentialsMap().put("rating", CandidateRating.CONFIRM);
        RestServiceGenerator.getTadoInstallationRestService().listAvailableCommandSets(UserConfig.getHomeId(), Integer.valueOf(InstallationProcessController.getInstallationProcessController().getInstallationId())).enqueue(new C08551());
    }
}
