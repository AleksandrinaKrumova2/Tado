package com.tado.android.installation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.tado.android.entities.InstallationInfo;

public class InstallationBaseActivity extends AppCompatActivity {
    public static final String INSTALLATION_INFO = "installationInfo";

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey("installationInfo")) {
            InstallationProcessController.getInstallationProcessController().getInstallationInfo().init((InstallationInfo) savedInstanceState.getSerializable("installationInfo"));
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("installationInfo", InstallationProcessController.getInstallationProcessController().getInstallationInfo());
        super.onSaveInstanceState(outState);
    }
}
