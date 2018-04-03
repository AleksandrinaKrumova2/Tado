package com.tado.android.installation.acsetup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;

public class PreTestingCommandSetsActivity extends ACInstallationBaseActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_pre_testing_command_sets);
        this.titleBarTextview.setText(getString(C0676R.string.installation_sacc_setupAC_title));
        this.titleTemplateTextview.setText(C0676R.string.installation_sacc_setupAC_onOffReduction_acTinderExplanation_message);
        this.proceedButton.setText(C0676R.string.installation_sacc_setupAC_onOffReduction_acTinderExplanation_confirmButton);
    }

    public void proceedClick(View view) {
        InstallationProcessController.startActivity((Activity) this, TestingCommandSetsActivity.class, true);
    }
}
