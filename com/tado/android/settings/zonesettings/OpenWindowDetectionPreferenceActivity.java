package com.tado.android.settings.zonesettings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.tado.android.controllers.ZoneController;
import com.tado.android.utils.Constants;
import com.tado.android.utils.UserConfig;

public class OpenWindowDetectionPreferenceActivity extends AppCompatActivity {
    public static final String KEY_OWD_ENABLED = "keyOwdEnabled";
    public static final String KEY_OWD_SECONDS = "keyOwdSeconds";
    public static final String KEY_ZONE_ID = "keyZoneId";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int owdDuration = Constants.TIMES_BLOCK_MIN_LENGTH_IN_SECONDS;
        boolean owdEnabled = false;
        int zoneId = UserConfig.getCurrentZone().intValue();
        if (getIntent().hasExtra(KEY_OWD_ENABLED) && getIntent().hasExtra(KEY_OWD_SECONDS) && getIntent().hasExtra(KEY_ZONE_ID)) {
            owdEnabled = getIntent().getBooleanExtra(KEY_OWD_ENABLED, false);
            owdDuration = getIntent().getIntExtra(KEY_OWD_SECONDS, Constants.TIMES_BLOCK_MIN_LENGTH_IN_SECONDS);
            zoneId = getIntent().getIntExtra(KEY_ZONE_ID, UserConfig.getCurrentZone().intValue());
            setupActionBar(ZoneController.INSTANCE.getZoneName(zoneId));
        }
        getFragmentManager().beginTransaction().replace(16908290, OpenWindowDetectionPreferencesFragment.newInstance(owdEnabled, owdDuration, zoneId), "owd-preferences").commit();
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
