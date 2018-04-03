package com.tado.android.settings.notifications;

import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.rest.model.HomeInfo.LicenseEnum;
import com.tado.android.utils.UserConfig;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010\t\u001a\u00020\u0004H\u0016¨\u0006\n"}, d2 = {"Lcom/tado/android/settings/notifications/NotificationPreferenceFragment;", "Landroid/support/v7/preference/PreferenceFragmentCompat;", "()V", "onCreatePreferences", "", "savedInstanceState", "Landroid/os/Bundle;", "rootKey", "", "onResume", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: NotificationPreferenceFragment.kt */
public final class NotificationPreferenceFragment extends PreferenceFragmentCompat {
    private HashMap _$_findViewCache;

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
        view = getView();
        if (view == null) {
            return null;
        }
        view = view.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), view);
        return view;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        addPreferencesFromResource(C0676R.xml.pref_notification_settings);
    }

    public void onResume() {
        super.onResume();
        boolean isNonPremium = Intrinsics.areEqual(UserConfig.getLicense(), LicenseEnum.NON_PREMIUM);
        Preference findPreference = findPreference(NotificationPreferenceActivity.KEY_AWAY_MODE_NOTIFICATION);
        Intrinsics.checkExpressionValueIsNotNull(findPreference, "findPreference(Notificat…Y_AWAY_MODE_NOTIFICATION)");
        findPreference.setEnabled(UserConfig.isLocationBasedControlEnabled());
        findPreference = findPreference(NotificationPreferenceActivity.KEY_HOME_MODE_NOTIFICATION);
        Intrinsics.checkExpressionValueIsNotNull(findPreference, "findPreference(Notificat…Y_HOME_MODE_NOTIFICATION)");
        findPreference.setEnabled(UserConfig.isLocationBasedControlEnabled());
        findPreference = findPreference(NotificationPreferenceActivity.KEY_AWAY_MODE_NOTIFICATION);
        Intrinsics.checkExpressionValueIsNotNull(findPreference, "findPreference(Notificat…Y_AWAY_MODE_NOTIFICATION)");
        findPreference.setVisible(isNonPremium);
        findPreference = findPreference(NotificationPreferenceActivity.KEY_HOME_MODE_NOTIFICATION);
        Intrinsics.checkExpressionValueIsNotNull(findPreference, "findPreference(Notificat…Y_HOME_MODE_NOTIFICATION)");
        findPreference.setVisible(isNonPremium);
        findPreference = findPreference(NotificationPreferenceActivity.KEY_OWD_NOTIFICATION);
        Intrinsics.checkExpressionValueIsNotNull(findPreference, "findPreference(Notificat…ity.KEY_OWD_NOTIFICATION)");
        findPreference.setVisible(isNonPremium);
    }
}
