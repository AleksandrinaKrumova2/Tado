package com.tado.android.installation.teaching;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.installation.complexteaching.ComplexRecordKeyCommandActivity;

public class StartTeachingActivity extends ACInstallationBaseActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_teaching_overview);
        this.titleBarTextview.setText(getString(C0676R.string.teaching_key_command_set_titlebar));
        this.titleTemplateTextview.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_pointTowardsAC_title);
        this.textView.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_pointTowardsAC_message);
        this.proceedButton.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_pointTowardsAC_confirmButton);
        this.centerImage.setImageResource(C0676R.drawable.max20cm);
    }

    public void proceedClick(View view) {
        InstallationProcessController.startActivity((Activity) this, ComplexRecordKeyCommandActivity.class, true);
    }
}
