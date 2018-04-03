package com.tado.android.settings.appsettings;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.dialogs.AlertDialogs;
import com.tado.android.location.LocationPermissionControler;
import com.tado.android.settings.locationbasedcontrol.HomeAreaPreferenceActivity;
import com.tado.android.settings.locationbasedcontrol.homewifi.HomeWifiPreferenceActivity;
import com.tado.android.utils.UserConfig;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\fJ\b\u0010\u000e\u001a\u00020\fH\u0002J\u0012\u0010\u000f\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010\u0012\u001a\u00020\fH\u0016J\b\u0010\u0013\u001a\u00020\fH\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/tado/android/settings/appsettings/AppSettingsPreferenceFragment;", "Landroid/preference/PreferenceFragment;", "()V", "homeWiFi", "Landroid/preference/Preference;", "getHomeWiFi", "()Landroid/preference/Preference;", "setHomeWiFi", "(Landroid/preference/Preference;)V", "preferenceChangeListener", "Landroid/preference/Preference$OnPreferenceChangeListener;", "createPreferences", "", "disableGeoLocationTracking", "init", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "showNoInternet", "Companion", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: AppSettingsPreferenceFragment.kt */
public final class AppSettingsPreferenceFragment extends PreferenceFragment {
    public static final Companion Companion = new Companion();
    @NotNull
    private static final String KEY_PREF_PRESENCE_DETECTION = KEY_PREF_PRESENCE_DETECTION;
    private HashMap _$_findViewCache;
    @Nullable
    private Preference homeWiFi;
    private final OnPreferenceChangeListener preferenceChangeListener = new AppSettingsPreferenceFragment$preferenceChangeListener$1(this);

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bR\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/tado/android/settings/appsettings/AppSettingsPreferenceFragment$Companion;", "", "()V", "KEY_PREF_PRESENCE_DETECTION", "", "getKEY_PREF_PRESENCE_DETECTION", "()Ljava/lang/String;", "newInstance", "Lcom/tado/android/settings/appsettings/AppSettingsPreferenceFragment;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: AppSettingsPreferenceFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final String getKEY_PREF_PRESENCE_DETECTION() {
            return AppSettingsPreferenceFragment.KEY_PREF_PRESENCE_DETECTION;
        }

        @NotNull
        public final AppSettingsPreferenceFragment newInstance() {
            return new AppSettingsPreferenceFragment();
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

    @Nullable
    public final Preference getHomeWiFi() {
        return this.homeWiFi;
    }

    public final void setHomeWiFi(@Nullable Preference <set-?>) {
        this.homeWiFi = <set-?>;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPreferences();
    }

    public final void createPreferences() {
        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(getActivity());
        SwitchPreference switchPreference = new SwitchPreference(getActivity());
        SwitchPreference $receiver = switchPreference;
        $receiver.setTitle(C0676R.string.settings_locationBasedControl_includeLocation_toggleButton);
        $receiver.setSummary(C0676R.string.settings_locationBasedControl_includeLocation_description);
        $receiver.setKey(Companion.getKEY_PREF_PRESENCE_DETECTION());
        $receiver.setEnabled(true);
        $receiver.setChecked(UserConfig.isLocationBasedControlEnabled());
        $receiver.setOnPreferenceChangeListener(this.preferenceChangeListener);
        screen.addPreference(switchPreference);
        Preference settings = new Preference(getActivity());
        Preference $receiver2 = settings;
        $receiver2.setTitle(C0676R.string.settings_locationBasedControl_recommendedPhoneSettings_title);
        $receiver2.setSummary(C0676R.string.settings_locationBasedControl_recommendedPhoneSettings_description);
        $receiver2.setPersistent(false);
        $receiver2.setIntent(new Intent("android.intent.action.VIEW", Uri.parse(getString(C0676R.string.settings_locationBasedControl_recommendedPhoneSettings_helpCenterURL))));
        $receiver2.setOnPreferenceClickListener(new C1083xa8e3d4b2(this));
        screen.addPreference(settings);
        Preference lbc = new Preference(getActivity());
        $receiver2 = lbc;
        $receiver2.setTitle(C0676R.string.settings_locationBasedControl_homeArea_title);
        $receiver2.setSummary(C0676R.string.settings_locationBasedControl_homeArea_description);
        $receiver2.setPersistent(false);
        com.tado.android.settings.locationbasedcontrol.HomeAreaPreferenceActivity.Companion companion = HomeAreaPreferenceActivity.Companion;
        Activity activity = getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity");
        $receiver2.setIntent(companion.newIntent(activity));
        $receiver2.setIcon(C0676R.drawable.ic_homearea);
        screen.addPreference(lbc);
        $receiver2 = new Preference(getActivity());
        $receiver2.setTitle(C0676R.string.settings_locationBasedControl_homeWifiButton);
        $receiver2.setSummary(C0676R.string.settings_locationBasedControl_homeWiFi_description);
        $receiver2.setIcon(C0676R.drawable.ic_home_wi_fi);
        $receiver2.setPersistent(false);
        com.tado.android.settings.locationbasedcontrol.homewifi.HomeWifiPreferenceActivity.Companion companion2 = HomeWifiPreferenceActivity.Companion;
        activity = getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity");
        $receiver2.setIntent(companion2.newIntent(activity));
        $receiver2.setEnabled(UserConfig.isLocationBasedControlEnabled());
        this.homeWiFi = $receiver2;
        screen.addPreference(this.homeWiFi);
        setPreferenceScreen(screen);
    }

    public void onResume() {
        super.onResume();
        AnalyticsHelper.trackPageView(getActivity(), Screen.APP_SETTINGS);
        if (UserConfig.isLocationBasedControlEnabled() && !LocationPermissionControler.INSTANCE.checkLocationPermissionsAndInitLocationApi(getActivity())) {
            LocationPermissionControler.INSTANCE.showLocationPermissionInfoDialog(getActivity());
        }
    }

    private final void init() {
        SwitchPreference detection = findPreference(Companion.getKEY_PREF_PRESENCE_DETECTION());
        if (detection == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.preference.SwitchPreference");
        }
        SwitchPreference $receiver = detection;
        $receiver.setEnabled(true);
        $receiver.setChecked(UserConfig.isLocationBasedControlEnabled());
        $receiver.setOnPreferenceChangeListener(this.preferenceChangeListener);
    }

    public final void disableGeoLocationTracking() {
        init();
    }

    private final void showNoInternet() {
        AlertDialogs.showSimpleWarning("", getResources().getString(C0676R.string.settings_no_internet_connection), getString(C0676R.string.ok), getActivity(), null);
    }
}
