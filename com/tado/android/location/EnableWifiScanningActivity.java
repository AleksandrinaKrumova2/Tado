package com.tado.android.location;

import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.crashlytics.android.Crashlytics;

public class EnableWifiScanningActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 49;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (VERSION.SDK_INT >= 18) {
            try {
                startActivityForResult(new Intent("android.net.wifi.action.REQUEST_SCAN_ALWAYS_AVAILABLE"), 49);
            } catch (Exception e) {
                Crashlytics.logException(e);
                finish();
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        finish();
    }
}
