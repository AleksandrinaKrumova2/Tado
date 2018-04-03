package com.tado.android.installation.acsetup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.installation.AcSetupPhase;
import com.tado.android.rest.model.installation.AcSetupPhase.PhaseEnum;
import com.tado.android.rest.model.installation.InstallationStateTransitionResult;
import com.tado.android.rest.model.installation.OnOffCandidate;
import com.tado.android.rest.model.installation.OnOffCandidateList;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.UserConfig;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

public class PrePointToAcActivity extends ACInstallationBaseActivity {
    @BindView(2131296405)
    ImageView centerImage;
    List<OnOffCandidate> commandSetEntries;

    class C08371 extends TadoCallback<OnOffCandidateList> {
        C08371() {
        }

        public void onResponse(Call<OnOffCandidateList> call, Response<OnOffCandidateList> response) {
            super.onResponse(call, response);
            PrePointToAcActivity.this.dismissLoadingUI();
            if (response.isSuccessful()) {
                PrePointToAcActivity.this.commandSetEntries = ((OnOffCandidateList) response.body()).getCandidates();
                PrePointToAcActivity.this.titleTemplateTextview.setText(PrePointToAcActivity.this.getCommandSetText(PrePointToAcActivity.this.commandSetEntries.size(), UserConfig.getAcManufacturerName()));
                if (PrePointToAcActivity.this.commandSetEntries.size() == 0) {
                    PrePointToAcActivity.this.handleEmptyCommandSets();
                    return;
                }
                return;
            }
            PrePointToAcActivity.this.handleServerError(this.serverError, PrePointToAcActivity.this, call, response.code(), this);
        }

        public void onFailure(Call<OnOffCandidateList> call, Throwable t) {
            super.onFailure(call, t);
            PrePointToAcActivity.this.dismissLoadingUI();
            InstallationProcessController.showConnectionErrorRetrofit(PrePointToAcActivity.this, call, this);
        }
    }

    class C08382 extends TadoCallback<InstallationStateTransitionResult> {
        C08382() {
        }

        public void onResponse(Call<InstallationStateTransitionResult> call, Response<InstallationStateTransitionResult> response) {
            super.onResponse(call, response);
            PrePointToAcActivity.this.dismissLoadingUI();
            if (response.isSuccessful()) {
                InstallationProcessController.getInstallationProcessController().goToScreenForInstallationProcessStatus(PrePointToAcActivity.this, ((InstallationStateTransitionResult) response.body()).getInstallation());
                return;
            }
            PrePointToAcActivity.this.handleServerError(this.serverError, PrePointToAcActivity.this, call, response.code(), this);
        }

        public void onFailure(Call<InstallationStateTransitionResult> call, Throwable t) {
            super.onFailure(call, t);
            PrePointToAcActivity.this.dismissLoadingUI();
            InstallationProcessController.showConnectionErrorRetrofit(PrePointToAcActivity.this, call, this);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_pre_point_to_ac);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_setupAC_title);
        this.centerImage.setImageResource(C0676R.drawable.ac_setup_prepoint_to_ac_instruction);
        getCandidateCommandSetList();
    }

    public void proceedClick(View view) {
        InstallationProcessController.startActivity((Activity) this, PointToACActivity.class, true);
    }

    private String getCommandSetText(int numberOfCommands, String brand) {
        if (numberOfCommands > 1) {
            return getString(C0676R.string.installation_sacc_setupAC_onOffReduction_existingCommandSets_multipleCommandSetsLabel, new Object[]{brand, Integer.valueOf(numberOfCommands)});
        }
        return getString(C0676R.string.installation_sacc_setupAC_onOffReduction_existingCommandSets_singleCommandSetLabel, new Object[]{brand});
    }

    private void getCandidateCommandSetList() {
        showLoadingUI();
        RestServiceGenerator.getTadoInstallationRestService().listOnOffCandidates(UserConfig.getHomeId(), Integer.valueOf(InstallationProcessController.getInstallationProcessController().getInstallationId())).enqueue(new C08371());
    }

    private void handleEmptyCommandSets() {
        showLoadingUI();
        int installationId = InstallationProcessController.getInstallationProcessController().getInstallationId();
        RestServiceGenerator.getTadoInstallationRestService().restartAcSetupPhase(UserConfig.getHomeId(), Integer.valueOf(installationId), new AcSetupPhase(PhaseEnum.AC_SPECS)).enqueue(new C08382());
    }
}
