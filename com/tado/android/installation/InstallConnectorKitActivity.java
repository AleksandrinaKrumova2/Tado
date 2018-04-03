package com.tado.android.installation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import com.tado.C0676R;

public class InstallConnectorKitActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_install_connector_kit);
        ((TextView) findViewById(C0676R.id.title_bar_textview)).setText(getString(C0676R.string.installation_productSelection_connectorKit_title));
        TextView tv = (TextView) findViewById(C0676R.id.title_template_textview);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setText(C0676R.string.installation_productSelection_connectorKit_message);
    }
}
