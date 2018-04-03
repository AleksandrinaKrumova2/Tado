package com.tado.android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.crashlytics.android.Crashlytics;
import com.tado.C0676R;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;

public class InfoActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_about);
        AnalyticsHelper.trackPageView(getApplication(), Screen.INFO);
        getSupportActionBar().setTitle((int) C0676R.string.settings_info_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView about = (TextView) findViewById(C0676R.id.textview_about);
        if (about != null) {
            about.setText(C0676R.string.settings_info_imprintLabel);
        }
    }

    public void goToUrlInTag(View view) {
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse((String) view.getTag())));
        } catch (Exception e) {
            Crashlytics.logException(e);
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
