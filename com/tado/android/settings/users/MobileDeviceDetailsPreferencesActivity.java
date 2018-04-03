package com.tado.android.settings.users;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.tado.C0676R;
import com.tado.android.rest.model.MobileDevice;

public class MobileDeviceDetailsPreferencesActivity extends AppCompatActivity {
    public static final String KEY_MOBILE_DEVICE = "keyMobileDevice";
    public static final String KEY_NAME_OF_USER = "keyNameOfUser";
    public static final int LOCATION_BASED_CONTROL_RESULT = 215;
    private MobileDeviceDetailsPreferencesFragment fragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle((int) C0676R.string.settings_users_existingUser_mobileDevices_title);
        if (getIntent().hasExtra(KEY_MOBILE_DEVICE) && getIntent().hasExtra(KEY_NAME_OF_USER)) {
            this.fragment = MobileDeviceDetailsPreferencesFragment.newInstance((MobileDevice) getIntent().getParcelableExtra(KEY_MOBILE_DEVICE), getIntent().getStringExtra(KEY_NAME_OF_USER));
            getFragmentManager().beginTransaction().replace(16908290, this.fragment, "mobile-device").commit();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onBackPressed() {
        if (this.fragment != null) {
            Intent result = new Intent();
            result.putExtra(KEY_MOBILE_DEVICE, this.fragment.getMobileDevice());
            setResult(-1, result);
        }
        super.onBackPressed();
    }
}
