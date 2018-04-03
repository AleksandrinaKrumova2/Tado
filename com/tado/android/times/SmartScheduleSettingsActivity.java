package com.tado.android.times;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.tado.C0676R;

public class SmartScheduleSettingsActivity extends AppCompatActivity {
    private SmartScheduleSettingsPreferenceFragment mPreferenceFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_smart_schedule_settings);
        setupActionBar();
        this.mPreferenceFragment = SmartScheduleSettingsPreferenceFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add((int) C0676R.id.container, this.mPreferenceFragment).commit();
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle((int) C0676R.string.smartSchedule_settings_title);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != 16908332) {
            return super.onOptionsItemSelected(item);
        }
        NavUtils.navigateUpFromSameTask(this);
        return true;
    }
}
