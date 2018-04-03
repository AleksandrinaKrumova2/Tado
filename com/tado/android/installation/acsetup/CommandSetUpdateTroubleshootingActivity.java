package com.tado.android.installation.acsetup;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity;

public class CommandSetUpdateTroubleshootingActivity extends ACInstallationBaseActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_command_set_update);
        this.titleBarTextview.setText(C0676R.string.installation_sacc_uploadCommandTable_title);
        this.titleTemplateTextview.setText(C0676R.string.installation_sacc_uploadCommandTable_updating_updateFailedTitle);
        this.textView.setText(C0676R.string.installation_sacc_uploadCommandTable_updating_updateFailedMessage);
        this.centerImageOverlay.setImageResource(C0676R.drawable.device_ac_update);
        this.centerImageOverlay.setVisibility(0);
        findViewById(C0676R.id.troubleshooting).setVisibility(0);
        findViewById(C0676R.id.progressBar).setVisibility(4);
    }

    public void troubleshoot(View view) {
        Intent i = new Intent("android.intent.action.VIEW");
        i.setData(Uri.parse(getString(C0676R.string.installation_sacc_uploadCommandTable_updating_troubleshootingURL)));
        startActivity(i);
    }
}
