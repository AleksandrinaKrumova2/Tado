package com.tado.android.installation.connectwifi;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.utils.UserConfig;

public class TroubleshootDeviceConnectionActivity extends ACInstallationBaseActivity {
    @BindView(2131296316)
    TextView additionalTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.fragment_troubleshoot_device_connection);
        String serialNo = UserConfig.getSerialNo();
        if (!(serialNo == null || serialNo.isEmpty())) {
            String ssidNo = serialNo.substring(serialNo.length() - 4);
            this.textView.setText(getString(C0676R.string.installation_sacc_connectWifi_serverConnection_troubleshooting_message, new Object[]{ssidNo}));
        }
        this.titleBarTextview.setText(C0676R.string.installation_sacc_connectWifi_serverConnection_title);
        this.titleTemplateTextview.setText(C0676R.string.installation_sacc_connectWifi_serverConnection_troubleshooting_title);
        this.additionalTextView.setText(C0676R.string.installation_sacc_connectWifi_serverConnection_troubleshooting_troubleshootingLabel);
        this.proceedButton.setText(C0676R.string.installation_sacc_connectWifi_serverConnection_troubleshooting_confirmButton);
        this.centerImageOverlay.setImageResource(C0676R.drawable.device_ssid);
        this.centerImageOverlay.setVisibility(0);
    }

    public void troubleshoot(View view) {
        Intent i = new Intent("android.intent.action.VIEW");
        i.setData(Uri.parse(getString(C0676R.string.installation_sacc_connectWifi_serverConnection_troubleshooting_troubleshootingURL)));
        startActivity(i);
    }

    public void proceedClick(View view) {
        InstallationProcessController.startActivity((Activity) this, ConnectToDeviceActivity.class, true);
    }
}
