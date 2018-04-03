package com.tado.android.installation.acsetup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;

public class TestConfirmedCommandSetsNoteActivity extends ACInstallationBaseActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_test_confirmed_command_sets_note);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_setupAC_selectCommandSet_title);
        this.titleTemplateTextview.setText(C0676R.string.installation_sacc_setupAC_selectCommandSet_noSpecialModesSupported_message);
        this.proceedButton.setText(C0676R.string.installation_sacc_setupAC_selectCommandSet_noSpecialModesSupported_confirmButton);
        this.centerImage.setImageResource(C0676R.drawable.missing_mode);
    }

    public void proceedClick(View view) {
        InstallationProcessController.startActivity((Activity) this, TestCommandSetActivity.class, false);
    }
}
