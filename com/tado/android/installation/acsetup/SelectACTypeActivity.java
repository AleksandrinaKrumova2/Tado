package com.tado.android.installation.acsetup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;

public class SelectACTypeActivity extends ACInstallationBaseActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_select_ac_type);
        this.titleBarTextview.setText(getString(C0676R.string.installation_sacc_acSpecs_title));
        this.titleTemplateTextview.setText(getString(C0676R.string.installation_sacc_acSpecs_acDisplay_title));
    }

    public void acWithDisplay(View view) {
        proceed(true);
    }

    public void acWithoutDisplay(View view) {
        proceed(false);
    }

    private void proceed(boolean acUnitDisplaysSetPointTemperature) {
        InstallationProcessController.getInstallationProcessController().getAcSpecs().setAcUnitDisplaysSetPointTemperature(Boolean.valueOf(acUnitDisplaysSetPointTemperature));
        InstallationProcessController.startActivity((Activity) this, SelectManufacturerActivity.class, false);
    }
}
