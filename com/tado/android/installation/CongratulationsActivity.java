package com.tado.android.installation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.tado.C0676R;
import com.tado.android.controllers.ZoneController;
import com.tado.android.installation.common.CongratulationsFragment;
import com.tado.android.installation.common.CongratulationsScreenCallback;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.settings.users.PeoplePreferenceActivity;

public class CongratulationsActivity extends ACInstallationBaseActivity implements CongratulationsScreenCallback {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0676R.layout.activity_congratulations);
        loadFragment(CongratulationsFragment.newInstance());
    }

    protected void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(C0676R.id.fragment_placeholder, fragment, fragment.getTag()).commit();
        invalidateOptionsMenu();
    }

    public void onAddNewDevice() {
        cleanupAndLoadZones();
        Intent intent = new Intent(this, ChooseProductActivity.class);
        intent.setFlags(335544320);
        startActivity(intent);
    }

    public void onFinishInstallation() {
        cleanupAndLoadZones();
        InstallationProcessController.getInstallationProcessController().detectStatus(this);
    }

    public void invitePeople() {
        startActivity(new Intent(this, PeoplePreferenceActivity.class));
    }

    private void cleanupAndLoadZones() {
        cleanUpInstallation();
        prepareZones();
    }

    private void cleanUpInstallation() {
        RestServiceGenerator.destroyHvacToolClient();
        InstallationProcessController.getInstallationProcessController().getInstallationInfo().setCurrentInstallation(null);
    }

    private void prepareZones() {
        ZoneController.INSTANCE.callGetZoneList();
    }
}
