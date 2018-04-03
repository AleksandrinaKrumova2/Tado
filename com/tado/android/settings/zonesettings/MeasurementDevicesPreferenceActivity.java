package com.tado.android.settings.zonesettings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.tado.android.controllers.ZoneController;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import java.util.List;

public class MeasurementDevicesPreferenceActivity extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<GenericHardwareDevice> devices = (List) getIntent().getSerializableExtra(MeasurementDevicesPreferenceFragment.KEY_DEVICES);
        int zoneId = getIntent().getIntExtra(ZonePreferenceActivity.KEY_ZONE_ID, -1);
        String selectedDeviceSerialNumber = getIntent().getStringExtra("selected");
        setupActionBar(ZoneController.INSTANCE.getZoneName(zoneId));
        getFragmentManager().beginTransaction().replace(16908290, MeasurementDevicesPreferenceFragment.getInstance(devices, zoneId, selectedDeviceSerialNumber), "measurement-devices").commit();
    }

    public void setupActionBar(String zoneName) {
        getSupportActionBar().setTitle((CharSequence) zoneName);
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
