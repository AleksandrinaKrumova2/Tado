package com.tado.android.installation.clc;

import android.os.Bundle;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.installation.common.RecordingBaseActivity;
import com.tado.android.rest.model.installation.AcInstallation;
import com.tado.android.times.view.model.ModeEnum;

public class TeachSmartACActivity extends RecordingBaseActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_teaching_overview);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_setupAC_createACSettingRecording_title);
        this.titleTemplateTextview.setText(C0676R.string.installation_sacc_setupAC_clcRecording_teachingExplanation_title);
        this.textView.setText(C0676R.string.installation_sacc_setupAC_clcRecording_teachingExplanation_message);
        this.proceedButton.setText(C0676R.string.installation_sacc_setupAC_clcRecording_teachingExplanation_confirmButton);
        this.centerImage.setImageResource(C0676R.drawable.rec_instructions);
    }

    public void proceedClick(View view) {
        startRecoding(((AcInstallation) InstallationProcessController.getInstallationProcessController().getInstallationInfo().getCurrentInstallation()).getAcInstallationInformation().getAcSettingCommandSetRecording().getId(), ModeEnum.COOL, true);
    }
}
