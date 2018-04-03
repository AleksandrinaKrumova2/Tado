package com.tado.android.installation.acsetup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.rest.model.installation.CommandTypeEnum;

public class SelectControlTypeActivity extends ACInstallationBaseActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_select_control_type);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_acSpecs_title);
        this.titleTemplateTextview.setText(C0676R.string.installation_sacc_acSpecs_remoteControlType_title);
    }

    public void controlWithDisplay(View view) {
        InstallationProcessController.startActivity((Activity) this, SelectDisplayTypeActivity.class, false);
    }

    public void controlWithoutDisplay(View view) {
        InstallationProcessController.getInstallationProcessController().getAcSpecs().getRemoteControl().setCommandType(CommandTypeEnum.KEY);
        InstallationProcessController.startActivity((Activity) this, ChooseTemperatureUnitActivity.class, false);
    }
}
