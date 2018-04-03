package com.tado.android.installation.complexteaching;

import android.app.Activity;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tado.C0676R;
import com.tado.android.alert_dialogs.CustomDialog;
import com.tado.android.alert_dialogs.CustomDialog.CustomDialogButton;
import com.tado.android.alert_dialogs.CustomDialog.CustomDialogText;
import com.tado.android.entities.ACModeRecording;
import com.tado.android.entities.ACSettingCommandSetRecording;
import com.tado.android.entities.TeachingMode;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.installation.teaching.SetACToSettingActivity;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.installation.AcInstallation;
import com.tado.android.rest.model.installation.InstallationStateTransitionResult;
import com.tado.android.rest.model.installation.ModeEnum;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.UserConfig;
import retrofit2.Call;
import retrofit2.Response;

public class TeachingModesOverviewActivity extends ACInstallationBaseActivity {
    @BindView(2131296451)
    FloatingActionButton autoModeButton;
    @BindView(2131296452)
    LinearLayout autoModeLayout;
    @BindView(2131296453)
    TextView autoModeTextView;
    @BindView(2131297099)
    ImageView autoTick;
    @BindView(2131296447)
    FloatingActionButton coolModeButton;
    @BindView(2131296448)
    LinearLayout coolModeLayout;
    @BindView(2131296449)
    TextView coolModeTextView;
    @BindView(2131297098)
    ImageView coolTick;
    @BindView(2131296495)
    FloatingActionButton dryModeButton;
    @BindView(2131296496)
    LinearLayout dryModeLayout;
    @BindView(2131296497)
    TextView dryModeTextView;
    @BindView(2131297101)
    ImageView dryTick;
    @BindView(2131296499)
    FloatingActionButton fanModeButton;
    @BindView(2131296500)
    LinearLayout fanModeLayout;
    @BindView(2131296501)
    TextView fanModeTextView;
    @BindView(2131297102)
    ImageView fanTick;
    @BindView(2131296444)
    FloatingActionButton heatModeButton;
    @BindView(2131296445)
    LinearLayout heatModeLayout;
    @BindView(2131296446)
    TextView heatModeTextView;
    @BindView(2131297097)
    ImageView heatTick;
    @BindView(2131296732)
    TextView maybeLaterTextview;

    class C09091 extends TadoCallback<ACSettingCommandSetRecording> {
        C09091() {
        }

        public void onResponse(Call<ACSettingCommandSetRecording> call, Response<ACSettingCommandSetRecording> response) {
            super.onResponse(call, response);
            TeachingModesOverviewActivity.this.dismissLoadingUI();
            if (response.isSuccessful()) {
                ComplexTeachingController.getComplexTeachingController().initalize((ACSettingCommandSetRecording) response.body());
                TeachingModesOverviewActivity.this.initLayout();
                return;
            }
            TeachingModesOverviewActivity.this.handleServerError(this.serverError, TeachingModesOverviewActivity.this, call, response.code(), this);
        }

        public void onFailure(Call<ACSettingCommandSetRecording> call, Throwable t) {
            super.onFailure(call, t);
            TeachingModesOverviewActivity.this.dismissLoadingUI();
            InstallationProcessController.showConnectionErrorRetrofit(TeachingModesOverviewActivity.this, call, this);
        }
    }

    class C09102 extends TadoCallback<TeachingMode> {
        C09102() {
        }

        public void onResponse(Call<TeachingMode> call, Response<TeachingMode> response) {
            super.onResponse(call, response);
            TeachingModesOverviewActivity.this.dismissLoadingUI();
            if (response.isSuccessful()) {
                TeachingMode teachingMode = (TeachingMode) response.body();
                ComplexTeachingController.getComplexTeachingController().setCurrentTeachingMode(teachingMode);
                if (teachingMode.getRuns().length > 1) {
                    InstallationProcessController.startActivity(TeachingModesOverviewActivity.this, TeachingRunsOverviewActivity.class, false);
                    return;
                } else {
                    InstallationProcessController.startActivity(TeachingModesOverviewActivity.this, SetACToSettingActivity.class, false);
                    return;
                }
            }
            TeachingModesOverviewActivity.this.handleServerError(this.serverError, TeachingModesOverviewActivity.this, call, response.code(), this);
        }

        public void onFailure(Call<TeachingMode> call, Throwable t) {
            super.onFailure(call, t);
            TeachingModesOverviewActivity.this.dismissLoadingUI();
            InstallationProcessController.showConnectionErrorRetrofit(TeachingModesOverviewActivity.this, call, this);
        }
    }

    class C09135 extends TadoCallback<InstallationStateTransitionResult> {
        C09135() {
        }

        public void onResponse(Call<InstallationStateTransitionResult> call, Response<InstallationStateTransitionResult> response) {
            if (response.isSuccessful()) {
                TeachingModesOverviewActivity.this.dismissLoadingUI();
                AcInstallation installation = ((InstallationStateTransitionResult) response.body()).getInstallation();
                if (installation != null) {
                    InstallationProcessController.getInstallationProcessController().goToScreenForInstallationProcessStatus(TeachingModesOverviewActivity.this, installation);
                    return;
                } else {
                    InstallationProcessController.getInstallationProcessController().detectStatus(TeachingModesOverviewActivity.this);
                    return;
                }
            }
            TeachingModesOverviewActivity.this.handleServerError(this.serverError, TeachingModesOverviewActivity.this, call, response.code(), this);
        }

        public void onFailure(Call<InstallationStateTransitionResult> call, Throwable t) {
            super.onFailure(call, t);
            TeachingModesOverviewActivity.this.dismissLoadingUI();
            InstallationProcessController.showConnectionErrorRetrofit(TeachingModesOverviewActivity.this, call, this);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_teaching_modes_overview);
        ButterKnife.bind((Activity) this);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_title);
        this.titleTemplateTextview.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_modeSelection_initialTitle);
        initUI();
        this.proceedButton.setVisibility(4);
        loadAcModeRecordings((long) ComplexTeachingController.getComplexTeachingController().getRecordingId());
    }

    private void initUI() {
        initModeButtonView(this.coolModeButton);
        initModeButtonView(this.dryModeButton);
        initModeButtonView(this.fanModeButton);
        initModeButtonView(this.autoModeButton);
        initModeButtonView(this.heatModeButton);
    }

    private void initModeButtonView(FloatingActionButton button) {
        button.setColorFilter(-1, Mode.SRC_ATOP);
        button.setBackgroundTintList(getResources().getColorStateList(C0676R.color.teaching_mode_button_background_color));
    }

    private void loadAcModeRecordings(long recordingId) {
        showLoadingUI();
        RestServiceGenerator.getTadoInstallationRestService().showAcSettingCommandSetRecording(Long.valueOf(recordingId)).enqueue(new C09091());
    }

    private void initLayout() {
        boolean allModesFinshed = true;
        boolean noModeFinished = true;
        ACModeRecording[] acModeRecordings = ComplexTeachingController.getComplexTeachingController().getAcModeRecordings();
        for (int i = 0; i < acModeRecordings.length; i++) {
            boolean enable;
            showMode(ModeEnum.valueOf(acModeRecordings[i].getMode()), true);
            if (acModeRecordings[i].getRecordingState().compareTo("FINISHED") != 0) {
                enable = true;
            } else {
                enable = false;
            }
            enableMode(ModeEnum.valueOf(acModeRecordings[i].getMode()), enable);
            if (enable) {
                allModesFinshed = false;
            } else {
                noModeFinished = false;
            }
        }
        if (noModeFinished || allModesFinshed) {
            this.maybeLaterTextview.setVisibility(8);
        } else {
            this.maybeLaterTextview.setVisibility(0);
        }
        if (allModesFinshed) {
            this.titleTemplateTextview.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_modeSelection_completedTitle);
            this.proceedButton.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_modeSelection_confirmButton);
            this.proceedButton.setVisibility(0);
            return;
        }
        this.proceedButton.setVisibility(8);
    }

    private void showMode(ModeEnum mode, boolean visible) {
        LinearLayout ll = null;
        switch (mode) {
            case COOL:
                ll = this.coolModeLayout;
                break;
            case DRY:
                ll = this.dryModeLayout;
                break;
            case FAN:
                ll = this.fanModeLayout;
                break;
            case AUTO:
                ll = this.autoModeLayout;
                break;
            case HEAT:
                ll = this.heatModeLayout;
                break;
        }
        if (visible) {
            ll.setVisibility(0);
        } else {
            ll.setVisibility(8);
        }
    }

    private void enableMode(ModeEnum mode, boolean enable) {
        FloatingActionButton buttonFloat;
        TextView textView;
        ImageView tickView;
        if (mode == ModeEnum.COOL) {
            buttonFloat = this.coolModeButton;
            textView = this.coolModeTextView;
            tickView = this.coolTick;
        } else if (mode == ModeEnum.DRY) {
            buttonFloat = this.dryModeButton;
            textView = this.dryModeTextView;
            tickView = this.dryTick;
        } else if (mode == ModeEnum.FAN) {
            buttonFloat = this.fanModeButton;
            textView = this.fanModeTextView;
            tickView = this.fanTick;
        } else if (mode == ModeEnum.AUTO) {
            buttonFloat = this.autoModeButton;
            textView = this.autoModeTextView;
            tickView = this.autoTick;
        } else if (mode == ModeEnum.HEAT) {
            buttonFloat = this.heatModeButton;
            textView = this.heatModeTextView;
            tickView = this.heatTick;
        } else {
            return;
        }
        buttonFloat.setEnabled(enable);
        textView.setEnabled(enable);
        if (enable) {
            tickView.setVisibility(4);
        } else {
            tickView.setVisibility(0);
        }
    }

    private ACModeRecording getACModeRecordingForModeString(String mode) {
        return ComplexTeachingController.getACModeRecordingForModeString(mode);
    }

    public void coolModeClick(View view) {
        startOrResumeModeRecording(getACModeRecordingForModeString(ModeEnum.COOL.toString()));
    }

    public void dryModeClick(View view) {
        startOrResumeModeRecording(getACModeRecordingForModeString(ModeEnum.DRY.toString()));
    }

    public void fanModeClick(View view) {
        startOrResumeModeRecording(getACModeRecordingForModeString(ModeEnum.FAN.toString()));
    }

    public void autoModeClick(View view) {
        startOrResumeModeRecording(getACModeRecordingForModeString(ModeEnum.AUTO.toString()));
    }

    public void heatModeClick(View view) {
        startOrResumeModeRecording(getACModeRecordingForModeString(ModeEnum.HEAT.toString()));
    }

    public void startOrResumeModeRecording(ACModeRecording selectedRecording) {
        ComplexTeachingController.getComplexTeachingController().setCurrentAcModeRecording(selectedRecording);
        if (selectedRecording != null && selectedRecording.getRecordingState().compareTo("NOT_STARTED") == 0) {
            InstallationProcessController.startActivity((Activity) this, ModeOptionsActivity.class, false);
        } else if (selectedRecording == null) {
        } else {
            if (selectedRecording.getRecordingState().compareTo("FAILED") == 0 || selectedRecording.getRecordingState().compareTo("IN_PROGRESS") == 0) {
                ComplexTeachingController state = ComplexTeachingController.getComplexTeachingController();
                long recordingId = (long) ComplexTeachingController.getComplexTeachingController().getRecordingId();
                ModeEnum mode = ModeEnum.valueOf(selectedRecording.getMode());
                showLoadingUI();
                RestServiceGenerator.getTadoInstallationRestService().showAcModeRecording(Long.valueOf(recordingId), mode).enqueue(new C09102());
            }
        }
    }

    public void proceedClick(View view) {
        endTeaching();
    }

    public void endTeachingClick(View view) {
        final CustomDialog dialog = new CustomDialog(this, CustomDialogText.CUSTOM_DIALOG_ONE_TEXT_PARAGRAPH, CustomDialogButton.CUSTOM_DIALOG_ONE_BUTTON);
        dialog.setBodyText1(getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_modeSelection_endTeaching_message));
        dialog.setButton1Text(getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_modeSelection_endTeaching_confirmButton));
        dialog.setTitle(getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_modeSelection_endTeaching_title));
        dialog.setButton1Listener(new OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
                TeachingModesOverviewActivity.this.endTeaching();
            }
        });
        dialog.setCancelButtonText(getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_modeSelection_endTeaching_cancelButton));
        dialog.setCancelButtonListener(new OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void endTeaching() {
        showLoadingUI();
        RestServiceGenerator.getTadoInstallationRestService().confirmAcSettingCommandSetRecording(UserConfig.getHomeId(), Integer.valueOf(InstallationProcessController.getInstallationProcessController().getInstallationId()), new Object()).enqueue(new C09135());
    }
}
