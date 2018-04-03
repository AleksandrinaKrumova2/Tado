package com.tado.android.installation.acsetup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.rest.model.installation.CommandTypeEnum;

public class SelectDisplayTypeActivity extends ACInstallationBaseActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_select_display_type);
        this.titleBarTextview.setText(getString(C0676R.string.installation_sacc_acSpecs_title));
        this.titleTemplateTextview.setText(getString(C0676R.string.installation_sacc_acSpecs_remoteControlDisplayType_title));
    }

    public void controlWithComplexDisplay(View view) {
        InstallationProcessController.getInstallationProcessController().getAcSpecs().getRemoteControl().setCommandType(CommandTypeEnum.AC_SETTING);
        InstallationProcessController.startActivity((Activity) this, ChooseTemperatureUnitActivity.class, false);
    }

    public void controlWithSimpleDisplay(View view) {
        InstallationProcessController.getInstallationProcessController().getAcSpecs().getRemoteControl().setCommandType(CommandTypeEnum.KEY);
        InstallationProcessController.startActivity((Activity) this, ChooseTemperatureUnitActivity.class, false);
    }
}
