package com.tado.android.installation.registerwr;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;

public class InstallCoolingOverviewActivity extends ACInstallationBaseActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.fragment_install_cooling_overview);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_registerDevice_installationOverview_title);
        this.titleTemplateTextview.setText(C0676R.string.installation_sacc_registerDevice_installationOverview_message);
        this.proceedButton.setText(C0676R.string.installation_sacc_registerDevice_installationOverview_confirmButton);
    }

    public void proceedClick(View view) {
        InstallationProcessController.startActivity((Activity) this, RegisterDeviceActivity.class, false);
    }
}
