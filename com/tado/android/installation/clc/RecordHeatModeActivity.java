package com.tado.android.installation.clc;

import android.os.Bundle;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.installation.common.RecordingBaseActivity;
import com.tado.android.rest.model.installation.AcInstallation;
import com.tado.android.times.view.model.ModeEnum;

public class RecordHeatModeActivity extends RecordingBaseActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_record_heat_mode);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_title);
        this.titleTemplateTextview.setText(C0676R.string.installation_sacc_setupAC_clcRecording_heatModeRecording_title);
        this.centerImage.setImageResource(C0676R.drawable.ic_heatmode);
    }

    public void recordHeatModeClick(View view) {
        startRecoding(((AcInstallation) InstallationProcessController.getInstallationProcessController().getInstallationInfo().getCurrentInstallation()).getAcInstallationInformation().getAcSettingCommandSetRecording().getId(), ModeEnum.HEAT, false);
    }

    public void endTeachingClick(View view) {
        endCLCTeaching();
    }
}
