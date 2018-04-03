package com.tado.android.settings.appsettings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.analytics.AnalyticsConstants.Events;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.utils.UserConfig;

public class AppSettingsActivity extends AppCompatActivity {
    private AppSettingsPreferenceFragment appSettingsPreferenceFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_app_settings);
        this.appSettingsPreferenceFragment = AppSettingsPreferenceFragment.Companion.newInstance();
        getFragmentManager().beginTransaction().add(C0676R.id.settingsFragment, this.appSettingsPreferenceFragment).commit();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(C0676R.string.settings_appSettings_locationBasedControlLabel));
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void disableGeoLocationTracking() {
        if (this.appSettingsPreferenceFragment != null) {
            this.appSettingsPreferenceFragment.disableGeoLocationTracking();
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(AppSettingsPreferenceFragment.Companion.getKEY_PREF_PRESENCE_DETECTION(), UserConfig.isLocationBasedControlEnabled());
        setResult(-1, intent);
        super.onBackPressed();
    }

    public void onHelpCenterClick(View view) {
        AnalyticsHelper.trackEvent(getApplication(), Screen.APP_SETTINGS, Events.RECOMMENDED_SETTINGS);
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getString(C0676R.string.settings_appSettings_recommendedSettings_helpCenterURL))));
    }
}
