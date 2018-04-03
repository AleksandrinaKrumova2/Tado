package com.tado.android.settings.zonesettings;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tado.C0676R;
import com.tado.android.controllers.ZoneController;
import com.tado.android.menu.ZoneItem;
import com.tado.android.views.progressbar.ProgressBarComponent;

public class ZonePreferenceActivity extends AppCompatActivity {
    public static final String KEY_ZONE_ID = "zoneid";
    @BindView(2131296880)
    ProgressBarComponent progressBar;
    private ZoneItem zoneItem;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_zone_preferences);
        ButterKnife.bind((Activity) this);
        int zoneId = getIntent().getIntExtra(KEY_ZONE_ID, -1);
        setupActionBar(ZoneController.INSTANCE.getZoneName(zoneId));
        getFragmentManager().beginTransaction().replace(16908290, ZonePreferenceFragment.getInstance(zoneId), "zone-preferences").commit();
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

    public void showProgressBar() {
        if (this.progressBar != null) {
            this.progressBar.showView();
        }
    }

    public void hideProgressBar(boolean withAnimation) {
        if (this.progressBar == null) {
            return;
        }
        if (withAnimation) {
            this.progressBar.hideViewWithAnimation();
        } else {
            this.progressBar.hideView();
        }
    }
}
