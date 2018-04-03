package com.tado.android.settings.notifications;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.tado.C0676R;
import java.util.HashMap;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\b"}, d2 = {"Lcom/tado/android/settings/notifications/NotificationPreferenceActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: NotificationPreferenceActivity.kt */
public final class NotificationPreferenceActivity extends AppCompatActivity {
    public static final Companion Companion = new Companion();
    @NotNull
    public static final String KEY_AWAY_MODE_NOTIFICATION = "key_pref_away_mode_notification";
    @NotNull
    public static final String KEY_ESR_NOTIFICATION = "key_pref_esr_notification";
    @NotNull
    public static final String KEY_HOME_MODE_NOTIFICATION = "key_pref_home_mode_notification";
    @NotNull
    public static final String KEY_LOW_BATTERY_NOTIFICATION = "key_pref_low_battery_notification";
    @NotNull
    public static final String KEY_OWD_NOTIFICATION = "key_pref_owd_notification";
    private HashMap _$_findViewCache;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/tado/android/settings/notifications/NotificationPreferenceActivity$Companion;", "", "()V", "KEY_AWAY_MODE_NOTIFICATION", "", "KEY_ESR_NOTIFICATION", "KEY_HOME_MODE_NOTIFICATION", "KEY_LOW_BATTERY_NOTIFICATION", "KEY_OWD_NOTIFICATION", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: NotificationPreferenceActivity.kt */
    public static final class Companion {
        private Companion() {
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

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(16908290, new NotificationPreferenceFragment()).commit();
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(getText(C0676R.string.settings_notifications_title));
        }
    }
}
