package com.tado.android.installation.acsetup;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import butterknife.OnClick;
import com.tado.C0676R;
import com.tado.android.installation.unfinished.UnfinishedInstallationDetailsActivity;
import com.tado.android.rest.model.installation.GenericHardwareDevice;

public class RemoteNotSupportedActivity extends UnfinishedInstallationDetailsActivity {
    public static final String TAG = "RemoteNotSupportedActivity";

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected int getLayoutId() {
        return C0676R.layout.activity_remote_not_supported;
    }

    @OnClick({2131296706})
    void onLearnMore() {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getString(C0676R.string.installation_sacc_incompatible_learnMoreURL))));
    }

    protected void bindActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    protected void bindDeviceDisplaySection(GenericHardwareDevice device) {
    }

    protected void bindHelpSection() {
    }
}
