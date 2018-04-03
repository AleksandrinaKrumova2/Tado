package com.tado.android.installation.teaching;

import android.app.Activity;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.tado.C0676R;
import com.tado.android.control_panel.CoolingControlHelper;
import com.tado.android.entities.ACSetting;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.installation.complexteaching.ComplexTeachingController;
import com.tado.android.times.view.model.CoolingPowerEnum;
import com.tado.android.times.view.model.ModeEnum;
import com.tado.android.utils.TemperatureUtils;
import java.util.Locale;

public class SetACToSettingActivity extends ACInstallationBaseActivity {
    @BindView(2131296270)
    View acModeFanLayout;
    @BindView(2131296272)
    View acModeLayout;
    @BindView(2131296273)
    View acModeSwingLayout;
    @BindView(2131296274)
    View acModeTempLayout;
    @BindView(2131296632)
    ImageView fanIconImageView;
    @BindView(2131296631)
    TextView fanTextView;
    @BindView(2131296792)
    ImageView modeImageView;
    @BindView(2131296805)
    TextView modeTextView;
    @BindView(2131296999)
    ImageView swingImageView;
    @BindView(2131297002)
    TextView swingTextView;
    @BindView(2131297032)
    ImageView tempImageView;
    @BindView(2131297046)
    TextView tempTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_set_acto_setting);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_title);
        this.centerImage.setImageResource(C0676R.drawable.bubble_remote_settings);
        ComplexTeachingController state = ComplexTeachingController.getComplexTeachingController();
        ACSetting acSetting = state.getCurrentTeachingMode().getRuns()[state.getCurrentRunIndex()].getSteps()[state.getCurrentStepIndex()].getAcSettingToStartFrom();
        if (InstallationProcessController.getInstallationProcessController().getInstallationInfo().isCLCRecording()) {
            this.titleTemplateTextview.setText(getString(C0676R.string.installation_sacc_setupAC_clcRecording_prepareRemoteControl_title, new Object[]{teachingMode.getMode(), TemperatureUtils.getFormattedTemperatureValue((float) acSetting.getTemperature().intValue(), 1.0f, InstallationProcessController.getInstallationProcessController().getAcSpecs().getRemoteControl().getTemperatureUnit(), Locale.getDefault())}));
            this.textView.setText(getString(C0676R.string.installation_sacc_setupAC_clcRecording_prepareRemoteControl_message));
            this.proceedButton.setText(C0676R.string.installation_sacc_setupAC_clcRecording_prepareRemoteControl_confirmButton);
        } else {
            this.titleTemplateTextview.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_prepareRemoteControl_title);
            this.proceedButton.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_prepareRemoteControl_confirmButton);
            this.textView.setVisibility(8);
        }
        initLayoutForAcSetting(acSetting);
    }

    private void initLayoutForAcSetting(ACSetting acSetting) {
        String mode = acSetting.getMode();
        Integer temp = acSetting.getTemperature();
        String fan = acSetting.getFanSpeed();
        String swing = acSetting.getSwing();
        if (CoolingPowerEnum.getBooleanValue(acSetting.getPower())) {
            if (mode != null) {
                this.acModeLayout.setVisibility(0);
                ModeEnum modeEnum = ModeEnum.getModeFromString(mode);
                this.modeImageView.setImageResource(CoolingControlHelper.getModeDrawableIcon(modeEnum));
                this.modeTextView.setText(CoolingControlHelper.getModeText(modeEnum));
                this.modeTextView.setTextColor(-1);
                this.modeImageView.setColorFilter(-1, Mode.SRC_ATOP);
            }
            if (temp != null) {
                this.acModeTempLayout.setVisibility(0);
                this.tempTextView.setText(String.valueOf(temp) + getString(C0676R.string.degree_symbol));
                this.tempTextView.setTextColor(-1);
                this.tempImageView.setColorFilter(-1, Mode.SRC_ATOP);
            }
            if (fan != null) {
                this.acModeFanLayout.setVisibility(0);
                this.fanTextView.setTextColor(-1);
                this.fanTextView.setText(fan);
                this.fanIconImageView.setColorFilter(-1, Mode.SRC_ATOP);
            }
            if (swing != null) {
                this.acModeSwingLayout.setVisibility(0);
                this.swingTextView.setText(swing);
                this.swingTextView.setTextColor(-1);
                this.swingImageView.setColorFilter(-1, Mode.SRC_ATOP);
                return;
            }
            return;
        }
        this.acModeLayout.setVisibility(0);
        this.modeImageView.setImageResource(C0676R.drawable.zone_list_ac_power);
        this.modeTextView.setText(acSetting.getPower());
        this.modeTextView.setTextColor(-1);
        this.modeImageView.setColorFilter(-1, Mode.SRC_ATOP);
    }

    public void proceedClick(View view) {
        InstallationProcessController.startActivity((Activity) this, StartTeachingActivity.class, false);
    }
}
