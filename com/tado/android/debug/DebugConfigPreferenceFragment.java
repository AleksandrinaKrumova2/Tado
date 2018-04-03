package com.tado.android.debug;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;
import android.support.v7.preference.Preference.OnPreferenceClickListener;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.widget.Toast;
import com.tado.android.app.TadoApplication;
import com.tado.android.dialogs.AlertDialogs;
import com.tado.android.location.LocationConfiguration.Builder;
import com.tado.android.location.LocationUtil;
import com.tado.android.notifications.NotificationUtil;
import com.tado.android.rest.model.HomeInfo.LicenseEnum;
import com.tado.android.utils.DebugConfig;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UserConfig;

public class DebugConfigPreferenceFragment extends PreferenceFragmentCompat implements OnSharedPreferenceChangeListener {

    class C07651 implements OnPreferenceChangeListener {
        C07651() {
        }

        public boolean onPreferenceChange(Preference preference, Object newValue) {
            UserConfig.setLicense(((Boolean) newValue).booleanValue() ? LicenseEnum.PREMIUM : LicenseEnum.NON_PREMIUM);
            return true;
        }
    }

    class C07662 implements OnPreferenceClickListener {
        C07662() {
        }

        public boolean onPreferenceClick(Preference preference) {
            Snitcher.start().log("token", "FCM push token: %s", String.valueOf(UserConfig.getFcmToken()));
            Toast.makeText(DebugConfigPreferenceFragment.this.getContext(), "token printed to logcat", 1).show();
            return true;
        }
    }

    class C07673 implements OnPreferenceChangeListener {
        C07673() {
        }

        public boolean onPreferenceChange(Preference preference, Object newValue) {
            preference.setSummary(((ListPreference) preference).getEntries()[Integer.parseInt((String) newValue)]);
            return true;
        }
    }

    class C07684 implements OnPreferenceChangeListener {
        C07684() {
        }

        public boolean onPreferenceChange(Preference preference, Object newValue) {
            boolean enabled = ((Boolean) newValue).booleanValue();
            if (UserConfig.getLocationConfiguration() != null) {
                TadoApplication.locationManager.updateLocationConfigurationAndStartTrackingIfEnabled(new Builder(UserConfig.getLocationConfiguration()).setGeofencing(enabled, enabled).setFused(DebugConfig.isFusedEnabled(), DebugConfig.isFusedEnabled()).build());
                return true;
            }
            AlertDialogs.showSimpleWarning("Error", "No configuration found", "OK", DebugConfigPreferenceFragment.this.getContext(), null);
            return false;
        }
    }

    class C07695 implements OnPreferenceChangeListener {
        C07695() {
        }

        public boolean onPreferenceChange(Preference preference, Object newValue) {
            boolean enabled = ((Boolean) newValue).booleanValue();
            if (UserConfig.getLocationConfiguration() != null) {
                TadoApplication.locationManager.updateLocationConfigurationAndStartTrackingIfEnabled(new Builder(UserConfig.getLocationConfiguration()).setFused(enabled, enabled).setGeofencing(DebugConfig.isGeofencingEnabled(), DebugConfig.isGeofencingEnabled()).build());
                return true;
            }
            AlertDialogs.showSimpleWarning("Error", "No configuration found", "OK", DebugConfigPreferenceFragment.this.getContext(), null);
            return false;
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceManager().setSharedPreferencesName(DebugConfig.PREFERENCES);
        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(getContext());
        screen.addPreference(getNetworkSpeedPreference());
        screen.addPreference(getLocationNotificationPreference());
        screen.addPreference(getUseGeofencesPreference());
        screen.addPreference(getUsedFusedPreference());
        screen.addPreference(getPremiumPreference());
        screen.addPreference(getFcmPreference());
        setPreferenceScreen(screen);
    }

    private Preference getPremiumPreference() {
        boolean z = false;
        CheckBoxPreference preference = new CheckBoxPreference(getContext());
        preference.setKey(DebugConfig.KEY_PREMIUM);
        preference.setTitle((CharSequence) "Premium enabled");
        preference.setPersistent(false);
        preference.setSummaryOn((CharSequence) "Premium enabled");
        preference.setSummaryOff((CharSequence) "Non premium");
        if (UserConfig.getLicense() != LicenseEnum.NON_PREMIUM) {
            z = true;
        }
        preference.setChecked(z);
        preference.setOnPreferenceChangeListener(new C07651());
        return preference;
    }

    private Preference getFcmPreference() {
        Preference preference = new Preference(getContext());
        preference.setPersistent(false);
        preference.setTitle((CharSequence) "FCM token");
        preference.setSummary(UserConfig.getFcmToken());
        preference.setOnPreferenceClickListener(new C07662());
        return preference;
    }

    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    public void onPause() {
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }

    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    }

    @NonNull
    private ListPreference getNetworkSpeedPreference() {
        ListPreference networkSpeed = new ListPreference(getContext());
        networkSpeed.setKey(DebugConfig.KEY_NETWORK_BEHAVIOR);
        networkSpeed.setPersistent(true);
        networkSpeed.setTitle((CharSequence) "Network speed (zone reordering)");
        networkSpeed.setEntries(new CharSequence[]{"Normal", "Slow", "Fail"});
        networkSpeed.setEntryValues(new CharSequence[]{"0", "1", "2"});
        networkSpeed.setDefaultValue(String.valueOf(0));
        networkSpeed.setSummary(networkSpeed.getEntries()[Integer.parseInt(DebugConfig.getNetworkBehaviorValue())]);
        networkSpeed.setOnPreferenceChangeListener(new C07673());
        return networkSpeed;
    }

    private Preference getLocationNotificationPreference() {
        CheckBoxPreference preference = new CheckBoxPreference(getContext());
        preference.setKey(DebugConfig.KEY_LOCATION_NOTIFICATION);
        preference.setTitle((CharSequence) "Location notification");
        preference.setPersistent(true);
        preference.setDefaultValue(Boolean.valueOf(DebugConfig.isLocationNotificationEnabled()));
        preference.setSummaryOn((CharSequence) "Show notification");
        preference.setSummaryOff((CharSequence) "Don't show notification");
        return preference;
    }

    private Preference getUseGeofencesPreference() {
        CheckBoxPreference preference = new CheckBoxPreference(getContext());
        preference.setKey(DebugConfig.KEY_USE_GEOFENCES);
        preference.setTitle((CharSequence) "Geofencing");
        preference.setPersistent(true);
        preference.setDefaultValue(Boolean.valueOf(TadoApplication.locationManager.getLocationConfiguration().useGeofences()));
        preference.setChecked(TadoApplication.locationManager.getLocationConfiguration().useGeofences());
        preference.setSummaryOn((CharSequence) "Use geofences");
        preference.setSummaryOff((CharSequence) "Don't use geofences");
        preference.setOnPreferenceChangeListener(new C07684());
        return preference;
    }

    private Preference getUsedFusedPreference() {
        CheckBoxPreference preference = new CheckBoxPreference(getContext());
        preference.setKey(DebugConfig.KEY_USE_FUSED);
        preference.setTitle((CharSequence) "Fused provider");
        preference.setPersistent(true);
        preference.setDefaultValue(Boolean.valueOf(TadoApplication.locationManager.getLocationConfiguration().useFused()));
        preference.setChecked(TadoApplication.locationManager.getLocationConfiguration().useFused());
        preference.setSummaryOn((CharSequence) "Use fused provider");
        preference.setSummaryOff((CharSequence) "Don't use fused provider");
        preference.setOnPreferenceChangeListener(new C07695());
        return preference;
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Object obj = -1;
        switch (key.hashCode()) {
            case -259466082:
                if (key.equals(DebugConfig.KEY_USE_FUSED)) {
                    obj = 1;
                    break;
                }
                break;
            case 440814660:
                if (key.equals(DebugConfig.KEY_USE_GEOFENCES)) {
                    obj = 2;
                    break;
                }
                break;
            case 599954752:
                if (key.equals(DebugConfig.KEY_LOCATION_NOTIFICATION)) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
            case 1:
            case 2:
                NotificationUtil.notifyLocationHistory(getContext().getApplicationContext(), LocationUtil.locations);
                return;
            default:
                return;
        }
    }
}
