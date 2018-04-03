package com.tado.android.installation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.tado.C0676R;
import com.tado.android.controllers.NavigationController;
import com.tado.android.utils.Util;

public class ResetWifiCredentialsActivity extends ACInstallationBaseActivity {
    public static final String RESET_WIFI_CREDENTIALS_SERIAL_NO = "serial_no";
    public static final String RESET_WIFI_CREDENTIALS_ZONE_ID = "zone_id";
    @BindView(2131296926)
    TextView resetWifiCredentialsText;
    private int zoneId = -1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_reset_wifi_credentials);
        String ssidNo = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(RESET_WIFI_CREDENTIALS_SERIAL_NO) && extras.containsKey(RESET_WIFI_CREDENTIALS_ZONE_ID)) {
            String serialNo = extras.getString(RESET_WIFI_CREDENTIALS_SERIAL_NO);
            if (serialNo != null) {
                ssidNo = serialNo.substring(serialNo.length() - 4);
            }
            this.zoneId = extras.getInt(RESET_WIFI_CREDENTIALS_ZONE_ID);
        }
        this.resetWifiCredentialsText.setText(Util.getText(this, C0676R.string.settings_airConditioning_resetWifiCredentials_message, ssidNo));
        this.titleBarTextview.setText(C0676R.string.settings_airConditioning_resetWifiCredentials_title);
        this.proceedButton.setText(C0676R.string.settings_airConditioning_resetWifiCredentials_confirmButton);
    }

    public void onHowToResetWifiCredentialsClick(View view) {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getString(C0676R.string.settings_airConditioning_resetWifiCredentials_instructionsURL))));
    }

    public void proceedClick(View view) {
        NavigationController.navigateToConnectToDevice(this, RESET_WIFI_CREDENTIALS_ZONE_ID, this.zoneId);
    }
}
