package com.tado.android.location;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tado.C0676R;
import com.tado.android.MainZoneActivity;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.app.TadoApplication;
import com.tado.android.settings.model.PresenceDetectionSettings;
import com.tado.android.utils.UserConfig;

public class PermissionFeatureIntroActivity extends AppCompatActivity {
    @BindView(2131296850)
    TextView featureIntroMainInfo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_permission_feature_intro);
        ButterKnife.bind((Activity) this);
        this.featureIntroMainInfo.setMovementMethod(new LinkMovementMethod());
        AnalyticsHelper.trackPageView(getApplication(), Screen.LOCATION_PERMISSION);
        UserConfig.setPermissionIntroShown(Boolean.valueOf(true));
        if (TadoApplication.isInternal()) {
            onEnableTadoDetectionClick(null);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0676R.menu.menu_permission_feature_intro, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == C0676R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onEnableTadoDetectionClick(View view) {
        PresenceDetectionSettings.updatePresenceDetectionSetting(true, null);
        if (LocationPermissionControler.INSTANCE.areLocationPermissionsGranted(this)) {
            onButtonClicked();
        } else if (!LocationPermissionControler.INSTANCE.requestLocationPermissions(this)) {
            onButtonClicked();
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (LocationPermissionControler.INSTANCE.handlePermissionCallback(requestCode, permissions, grantResults)) {
            AnalyticsHelper.trackEvent(getApplication(), Screen.LOCATION_PERMISSION, "LocationEnabled", "true");
        }
        onButtonClicked();
    }

    public void onDisableTadoDetectionClick(View view) {
        AnalyticsHelper.trackEvent(getApplication(), Screen.LOCATION_PERMISSION, "LocationEnabled", "false");
        PresenceDetectionSettings.updatePresenceDetectionSetting(false, null);
        onButtonClicked();
    }

    private void onButtonClicked() {
        Intent intent = new Intent(this, MainZoneActivity.class);
        intent.addFlags(268435456);
        startActivity(intent);
        finish();
    }
}
