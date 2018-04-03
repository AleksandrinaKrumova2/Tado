package com.tado.android.settings.cooling;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.tado.C0676R;
import com.tado.android.rest.model.Hysteresis;

public class AcTemperatureRangeActivity extends AppCompatActivity {
    private int driverDisc;
    private AcConfigPreferenceFragment fragment;
    private Hysteresis hysteresis;
    private int minOnOffTimeInSeconds;
    private int zoneId;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle((int) C0676R.string.settings_zoneSettings_airConditioning_hysteresis_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator((int) C0676R.drawable.ic_close_white_24dp);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.zoneId = extras.getInt(AcSetupSettingsActivity.KEY_SELECTED_ZONE_ID);
            this.driverDisc = extras.getInt(AcSetupSettingsActivity.KEY_DRIVER_DISC);
            this.minOnOffTimeInSeconds = extras.getInt(AcSetupSettingsActivity.KEY_MIN_ON_OFF_TIME);
            this.hysteresis = (Hysteresis) extras.getSerializable(AcSetupSettingsActivity.KEY_HYSTERESIS);
        }
        this.fragment = AcTemperatureRangePreferenceFragment.getInstance(this.zoneId, this.driverDisc, Integer.valueOf(this.minOnOffTimeInSeconds), this.hysteresis);
        getFragmentManager().beginTransaction().replace(16908290, this.fragment, AcTemperatureRangePreferenceFragment.class.getCanonicalName()).commit();
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
                if (this.fragment != null) {
                    this.fragment.save();
                    break;
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        if (this.fragment != null && this.fragment.shouldGoBack()) {
            super.onBackPressed();
        }
    }
}
