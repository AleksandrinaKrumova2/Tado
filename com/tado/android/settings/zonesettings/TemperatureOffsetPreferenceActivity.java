package com.tado.android.settings.zonesettings;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.tado.C0676R;
import com.tado.android.controllers.ZoneController;
import com.tado.android.rest.model.Temperature;
import com.tado.android.rest.model.installation.GenericHardwareDevice;

public class TemperatureOffsetPreferenceActivity extends AppCompatActivity implements FragmentInterface {
    TemperatureOffsetFragment fragment;
    private GenericHardwareDevice measuringDevice;

    class C11681 implements OnClickListener {
        C11681() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    }

    class C11692 implements OnClickListener {
        C11692() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            TemperatureOffsetPreferenceActivity.this.finish();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int zoneId = getIntent().getExtras().getInt(ZonePreferenceActivity.KEY_ZONE_ID, -1);
        this.measuringDevice = (GenericHardwareDevice) getIntent().getExtras().getSerializable(TemperatureOffsetFragment.KEY_MEASURING_DEVICE);
        Temperature offset = (Temperature) getIntent().getExtras().getSerializable(TemperatureOffsetFragment.KEY_OFFSET);
        setupActionBar(ZoneController.INSTANCE.getZoneName(zoneId));
        this.fragment = TemperatureOffsetFragment.getInstance(zoneId, offset);
        getFragmentManager().beginTransaction().replace(16908290, this.fragment, "temperatureOffsetFragment").commit();
    }

    public void setupActionBar(String zoneName) {
        getSupportActionBar().setTitle((CharSequence) zoneName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0676R.menu.menu_action_save, menu);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(C0676R.id.action_save).setVisible(true);
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                onBackPressed();
                break;
            case C0676R.id.action_save:
                if (this.fragment.hasChanges()) {
                    this.fragment.saveChanges(this.measuringDevice);
                    break;
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        if (this.fragment.hasChanges()) {
            showHasChangesDialog();
        } else {
            super.onBackPressed();
        }
    }

    private void showHasChangesDialog() {
        new Builder(this).setMessage(getString(C0676R.string.smartSchedule_notifications_discardChangesMessage)).setPositiveButton(getString(C0676R.string.settings_zoneSettings_measurements_temperatureOffset_offsetConfirmation_confirmButton), new C11692()).setNegativeButton(getString(C0676R.string.settings_zoneSettings_measurements_temperatureOffset_cancelButton), new C11681()).show();
    }

    public void onOffsetSaved(Temperature offset) {
        Intent intent = new Intent(this, ZonePreferenceActivity.class);
        intent.addFlags(67108864);
        intent.putExtra(ZonePreferenceActivity.KEY_ZONE_ID, getIntent().getIntExtra(ZonePreferenceActivity.KEY_ZONE_ID, -1));
        intent.putExtra(TemperatureOffsetFragment.KEY_MEASURING_DEVICE, getIntent().getSerializableExtra(TemperatureOffsetFragment.KEY_MEASURING_DEVICE));
        intent.putExtra(TemperatureOffsetFragment.KEY_OFFSET, offset);
        startActivity(intent);
    }
}
