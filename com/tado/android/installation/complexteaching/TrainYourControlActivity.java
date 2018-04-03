package com.tado.android.installation.complexteaching;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.installation.teaching.SetACToSettingActivity;

public class TrainYourControlActivity extends ACInstallationBaseActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_teaching_overview);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_title);
        this.titleTemplateTextview.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_teachingExplanation_title);
        this.textView.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_teachingExplanation_message);
        this.proceedButton.setText(C0676R.string.installation_sacc_setupAC_recordACSettingCommands_teachingExplanation_confirmButton);
        this.centerImage.setImageResource(C0676R.drawable.train_yout_smart_ac_control);
    }

    public void proceedClick(View view) {
        InstallationProcessController.startActivity((Activity) this, SetACToSettingActivity.class, false);
    }
}
