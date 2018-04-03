package com.tado.android.installation.connectwifi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;

public class PlugInDeviceActivity extends ACInstallationBaseActivity {
    @BindView(2131296856)
    TextView pluginDeviceTextview;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.fragment_plugin_device);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_connectWifi_powerSupply_title);
        this.titleTemplateTextview.setText(C0676R.string.installation_sacc_connectWifi_powerSupply_message);
        this.proceedButton.setText(C0676R.string.installation_sacc_connectWifi_powerSupply_confirmButton);
        this.centerImage.setImageResource(C0676R.drawable.plugin);
        this.pluginDeviceTextview.setText(C0676R.string.installation_sacc_connectWifi_powerSupply_description);
    }

    protected void onPause() {
        super.onPause();
    }

    public void proceedClick(View view) {
        InstallationProcessController.startActivity((Activity) this, ConnectToDeviceActivity.class, false);
    }
}
