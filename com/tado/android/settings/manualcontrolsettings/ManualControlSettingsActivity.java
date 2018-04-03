package com.tado.android.settings.manualcontrolsettings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.tado.android.controllers.ZoneController;
import com.tado.android.rest.model.OverlayTerminationCondition;
import com.tado.android.settings.zonesettings.ZonePreferenceActivity;
import com.tado.android.settings.zonesettings.ZonePreferenceFragment;

public class ManualControlSettingsActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int zoneId = getIntent().getExtras().getInt(ZonePreferenceActivity.KEY_ZONE_ID, -1);
        getFragmentManager().beginTransaction().replace(16908290, ManualControlPreferenceFragment.newInstance(zoneId, (OverlayTerminationCondition) getIntent().getExtras().getSerializable(ZonePreferenceFragment.KEY_TERMINATION_CONDITION))).commit();
        setupActionBar(ZoneController.INSTANCE.getZoneName(zoneId));
    }

    public void setupActionBar(String title) {
        getSupportActionBar().setTitle((CharSequence) title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
