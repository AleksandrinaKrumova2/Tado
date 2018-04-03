package com.tado.android.settings.users;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.tado.C0676R;
import com.tado.android.rest.model.Invitation;
import com.tado.android.rest.model.User;

public class PeopleDetailsPreferenceActivity extends AppCompatActivity {
    public static final String KEY_INVITATION = "keyInvitation";
    public static final String KEY_USER = "keyUser";
    public static final String KEY_USERS_COUNT = "keyUsersCount";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle((int) C0676R.string.settings_users_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        PeopleDetailsPreferenceActivityFragment fragment = null;
        if (getIntent().hasExtra(KEY_USER) && getIntent().hasExtra(KEY_USERS_COUNT)) {
            fragment = PeopleDetailsPreferenceActivityFragment.newInstance((User) getIntent().getExtras().getParcelable(KEY_USER), getIntent().getExtras().getInt(KEY_USERS_COUNT));
        } else if (getIntent().hasExtra("keyInvitation")) {
            fragment = PeopleDetailsPreferenceActivityFragment.newInstance((Invitation) getIntent().getExtras().getParcelable("keyInvitation"));
        }
        if (fragment != null) {
            getFragmentManager().beginTransaction().replace(16908290, fragment, "user-detail").commit();
        }
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
