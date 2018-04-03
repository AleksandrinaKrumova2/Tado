package com.tado.android.installation.complexteaching;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import butterknife.BindView;
import com.tado.C0676R;
import com.tado.android.entities.ACSettingCommandSetRecording;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.installation.AcInstallation;
import com.tado.android.rest.model.installation.AcSettingCommandSetRecordingInput;
import com.tado.android.rest.model.installation.InstallationStateTransitionResult;
import com.tado.android.rest.model.installation.ModeEnum;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.UserConfig;
import java.util.LinkedList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

public class ChooseAvailableModesActivity extends ACInstallationBaseActivity implements OnCheckedChangeListener {
    @BindView(2131296334)
    public CheckBox autoCheckbox;
    private int checkedBoxesCounter = 0;
    @BindView(2131296502)
    public CheckBox coolCheckbox;
    @BindView(2131296603)
    public CheckBox dryCheckbox;
    @BindView(2131296629)
    public CheckBox fanCheckbox;
    @BindView(2131296659)
    public CheckBox heatCheckbox;

    class C08921 extends TadoCallback<InstallationStateTransitionResult> {
        C08921() {
        }

        public void onResponse(Call<InstallationStateTransitionResult> call, Response<InstallationStateTransitionResult> response) {
            super.onResponse(call, response);
            if (response.isSuccessful()) {
                AcInstallation installation = ((InstallationStateTransitionResult) response.body()).getInstallation();
                if (installation != null) {
                    ChooseAvailableModesActivity.this.startRecoding(installation.getAcInstallationInformation().getAcSettingCommandSetRecording().getId(), installation);
                    return;
                }
                return;
            }
            ChooseAvailableModesActivity.this.handleServerError(this.serverError, ChooseAvailableModesActivity.this, call, response.code(), this);
        }

        public void onFailure(Call<InstallationStateTransitionResult> call, Throwable t) {
            super.onFailure(call, t);
            InstallationProcessController.showConnectionErrorRetrofit(ChooseAvailableModesActivity.this, call, this);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_available_modes);
        ComplexTeachingController state = ComplexTeachingController.getComplexTeachingController();
        this.titleBarTextview.setText(C0676R.string.installation_sacc_setupAC_createACSettingRecording_title);
        this.titleTemplateTextview.setText(C0676R.string.installation_sacc_setupAC_createACSettingRecording_supportedModesSelection_title);
        this.proceedButton.setText(C0676R.string.installation_sacc_setupAC_createACSettingRecording_supportedModesSelection_confirmButton);
        this.proceedButton.setEnabled(false);
        this.coolCheckbox.setOnCheckedChangeListener(this);
        this.heatCheckbox.setOnCheckedChangeListener(this);
        this.autoCheckbox.setOnCheckedChangeListener(this);
        this.fanCheckbox.setOnCheckedChangeListener(this);
        this.dryCheckbox.setOnCheckedChangeListener(this);
        ContextCompat.getDrawable(this, C0676R.drawable.ic_ac_mode_cool_gray).clearColorFilter();
        ContextCompat.getDrawable(this, C0676R.drawable.ic_ac_mode_dry_gray).clearColorFilter();
        ContextCompat.getDrawable(this, C0676R.drawable.ic_ac_mode_auto_gray).clearColorFilter();
        ContextCompat.getDrawable(this, C0676R.drawable.ic_ac_mode_fan_gray).clearColorFilter();
        ContextCompat.getDrawable(this, C0676R.drawable.ic_ac_mode_heat_gray).clearColorFilter();
    }

    public void proceedClick(View view) {
        createAcSettingCommandSetRecording();
    }

    public void createAcSettingCommandSetRecording() {
        List<ModeEnum> modesList = new LinkedList();
        if (this.coolCheckbox.isChecked()) {
            modesList.add(ModeEnum.COOL);
        }
        if (this.heatCheckbox.isChecked()) {
            modesList.add(ModeEnum.HEAT);
        }
        if (this.autoCheckbox.isChecked()) {
            modesList.add(ModeEnum.AUTO);
        }
        if (this.fanCheckbox.isChecked()) {
            modesList.add(ModeEnum.FAN);
        }
        if (this.dryCheckbox.isChecked()) {
            modesList.add(ModeEnum.DRY);
        }
        int installationId = InstallationProcessController.getInstallationProcessController().getInstallationId();
        AcSettingCommandSetRecordingInput acSettingCommandSetRecording = new AcSettingCommandSetRecordingInput();
        acSettingCommandSetRecording.setAcModes(modesList);
        RestServiceGenerator.getTadoInstallationRestService().createAcSettingCommandSetRecording(UserConfig.getHomeId(), Integer.valueOf(installationId), acSettingCommandSetRecording).enqueue(new C08921());
    }

    public void startRecoding(Long recordingId, final AcInstallation installation) {
        RestServiceGenerator.getTadoInstallationRestService().showAcSettingCommandSetRecording(recordingId).enqueue(new TadoCallback<ACSettingCommandSetRecording>() {
            public void onResponse(Call<ACSettingCommandSetRecording> call, Response<ACSettingCommandSetRecording> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    ComplexTeachingController.getComplexTeachingController().initalize((ACSettingCommandSetRecording) response.body());
                    InstallationProcessController.getInstallationProcessController().goToScreenForInstallationProcessStatus(ChooseAvailableModesActivity.this, installation);
                    return;
                }
                ChooseAvailableModesActivity.this.handleServerError(this.serverError, ChooseAvailableModesActivity.this, call, response.code(), this);
            }

            public void onFailure(Call<ACSettingCommandSetRecording> call, Throwable t) {
                super.onFailure(call, t);
                InstallationProcessController.showConnectionErrorRetrofit(ChooseAvailableModesActivity.this, call, this);
            }
        });
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            this.checkedBoxesCounter++;
        } else {
            this.checkedBoxesCounter--;
        }
        if (this.checkedBoxesCounter > 0) {
            this.proceedButton.setEnabled(true);
        } else {
            this.proceedButton.setEnabled(false);
        }
    }
}
