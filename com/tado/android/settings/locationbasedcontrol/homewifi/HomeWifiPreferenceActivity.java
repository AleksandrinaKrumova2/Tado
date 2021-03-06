package com.tado.android.settings.locationbasedcontrol.homewifi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tado.C0676R;
import com.tado.android.views.progressbar.ProgressBarComponent;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.internals.AnkoInternals;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u000e\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0013R \u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0000@\u0000X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/tado/android/settings/locationbasedcontrol/homewifi/HomeWifiPreferenceActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "progressBar", "Lcom/tado/android/views/progressbar/ProgressBarComponent;", "getProgressBar$4_9_3_1500409030_tadoRelease", "()Lcom/tado/android/views/progressbar/ProgressBarComponent;", "setProgressBar$4_9_3_1500409030_tadoRelease", "(Lcom/tado/android/views/progressbar/ProgressBarComponent;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "setupActionBar", "title", "", "Companion", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: HomeWifiPreferenceActivity.kt */
public final class HomeWifiPreferenceActivity extends AppCompatActivity {
    public static final Companion Companion = new Companion();
    private HashMap _$_findViewCache;
    @Nullable
    @BindView(2131296880)
    private ProgressBarComponent progressBar;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/tado/android/settings/locationbasedcontrol/homewifi/HomeWifiPreferenceActivity$Companion;", "", "()V", "newIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: HomeWifiPreferenceActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final Intent newIntent(@NotNull Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            return AnkoInternals.createIntent(context, HomeWifiPreferenceActivity.class, new Pair[0]);
        }
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        view = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), view);
        return view;
    }

    @Nullable
    public final ProgressBarComponent getProgressBar$4_9_3_1500409030_tadoRelease() {
        return this.progressBar;
    }

    public final void setProgressBar$4_9_3_1500409030_tadoRelease(@Nullable ProgressBarComponent <set-?>) {
        this.progressBar = <set-?>;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_homewifi_preferences);
        ButterKnife.bind((Activity) this);
        String string = getString(C0676R.string.settings_locationBasedControl_homeWiFi_title);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.setti…edControl_homeWiFi_title)");
        setupActionBar(string);
        getFragmentManager().beginTransaction().replace(16908290, HomeWifiPreferenceFragment.Companion.getInstance(), "home-wifi-preferences").commit();
    }

    public final void setupActionBar(@NotNull String title) {
        Intrinsics.checkParameterIsNotNull(title, "title");
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle((CharSequence) title);
        }
        supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        switch (item.getItemId()) {
            case 16908332:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
