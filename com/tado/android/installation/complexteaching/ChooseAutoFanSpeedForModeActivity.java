package com.tado.android.installation.complexteaching;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.entities.ACModeRecording;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.times.view.model.CoolingFanSpeedEnum;

public class ChooseAutoFanSpeedForModeActivity extends ACInstallationBaseActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_choose_auto_fan);
        String mode = "";
        ComplexTeachingController state = ComplexTeachingController.getComplexTeachingController();
        if (state != null) {
            ACModeRecording acModeRecording = state.getCurrentAcModeRecording();
            if (acModeRecording != null) {
                mode = acModeRecording.getMode();
                this.titleBarTextview.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_title);
                this.titleTemplateTextview.setText(getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_autoFanspeed_title, new Object[]{mode}));
                this.centerImage.setImageResource(C0676R.drawable.auto_fan);
            }
        }
        InstallationProcessController.getInstallationProcessController().detectStatus(this);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_title);
        this.titleTemplateTextview.setText(getString(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_autoFanspeed_title, new Object[]{mode}));
        this.centerImage.setImageResource(C0676R.drawable.auto_fan);
    }

    private void goToChooseFanSpeedForMode(String[] fanSpeeds) {
        ComplexTeachingController.getComplexTeachingController().getCurrentCapabilities().setFanSpeeds(fanSpeeds);
        InstallationProcessController.startActivity((Activity) this, ChooseFanspeedsForModeActivity.class, false);
    }

    public void yesClick(View view) {
        goToChooseFanSpeedForMode(new String[]{CoolingFanSpeedEnum.getStringValue(CoolingFanSpeedEnum.AUTO)});
    }

    public void noClick(View view) {
        goToChooseFanSpeedForMode(new String[0]);
    }
}
