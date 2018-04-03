package com.tado.android.settings.zonesettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import com.tado.C0676R;
import com.tado.android.rest.callback.RetryCallback;
import com.tado.android.rest.callback.presenters.GeneralErrorAlertPresenter;
import com.tado.android.rest.model.HomeInfo.LicenseEnum;
import com.tado.android.rest.model.OpenWindowDetectionConfiguration;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import retrofit2.Call;
import retrofit2.Response;

public class OpenWindowDetectionPreferencesFragment extends PreferenceFragment {
    private static final String PREF_KEY_DURATION = "prefKeyDuration";
    private static final String PREF_KEY_ENABLED = "prefKeyEnabled";
    private int currentOwdDurationInMinutes;
    private boolean currentOwdEnabled;
    private int owdDurationInMinutes = 15;
    private boolean owdEnabled = false;
    private boolean preferencesHaveBeenReset = false;
    private int zoneId;

    class C11631 implements OnPreferenceChangeListener {
        C11631() {
        }

        public boolean onPreferenceChange(Preference preference, Object newValue) {
            OpenWindowDetectionPreferencesFragment.this.owdEnabled = ((Boolean) newValue).booleanValue();
            OpenWindowDetectionPreferencesFragment.this.updateSettings();
            return true;
        }
    }

    class C11642 implements OnPreferenceChangeListener {
        C11642() {
        }

        public boolean onPreferenceChange(Preference timePreference, Object newValue) {
            OpenWindowDetectionPreferencesFragment.this.owdDurationInMinutes = ((Integer) newValue).intValue();
            timePreference.setSummary(OpenWindowDetectionPreferencesFragment.this.formatDurationMinutes(OpenWindowDetectionPreferencesFragment.this.owdDurationInMinutes));
            OpenWindowDetectionPreferencesFragment.this.updateSettings();
            return true;
        }
    }

    public static OpenWindowDetectionPreferencesFragment newInstance(boolean owdEnabled, int owdDuration, int zoneId) {
        OpenWindowDetectionPreferencesFragment fragment = new OpenWindowDetectionPreferencesFragment();
        Bundle bundle = new Bundle(2);
        bundle.putBoolean(OpenWindowDetectionPreferenceActivity.KEY_OWD_ENABLED, owdEnabled);
        bundle.putInt(OpenWindowDetectionPreferenceActivity.KEY_OWD_SECONDS, owdDuration);
        bundle.putInt(OpenWindowDetectionPreferenceActivity.KEY_ZONE_ID, zoneId);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(OpenWindowDetectionPreferenceActivity.KEY_OWD_SECONDS) && getArguments().containsKey(OpenWindowDetectionPreferenceActivity.KEY_OWD_ENABLED) && getArguments().containsKey(OpenWindowDetectionPreferenceActivity.KEY_ZONE_ID)) {
            this.owdEnabled = getArguments().getBoolean(OpenWindowDetectionPreferenceActivity.KEY_OWD_ENABLED);
            this.owdDurationInMinutes = getArguments().getInt(OpenWindowDetectionPreferenceActivity.KEY_OWD_SECONDS);
            this.zoneId = getArguments().getInt(OpenWindowDetectionPreferenceActivity.KEY_ZONE_ID);
            this.currentOwdDurationInMinutes = this.owdDurationInMinutes;
            this.currentOwdEnabled = this.owdEnabled;
        }
        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(getActivity());
        SwitchPreference owdPreference = new SwitchPreference(getActivity());
        owdPreference.setKey(PREF_KEY_ENABLED);
        owdPreference.setTitle(C0676R.string.settings_zoneSettings_openWindowDetection_enabledSwitchLabel);
        if (UserConfig.getLicense() == LicenseEnum.NON_PREMIUM) {
            owdPreference.setSummary(C0676R.string.premiumUpgrade_openWindowSettings_descriptionLabel);
        } else {
            owdPreference.setSummary(C0676R.string.settings_zoneSettings_openWindowDetection_descriptionLabel);
        }
        owdPreference.setPersistent(false);
        owdPreference.setChecked(this.owdEnabled);
        owdPreference.setOnPreferenceChangeListener(new C11631());
        screen.addPreference(owdPreference);
        screen.addPreference(createTimerPreference());
        setPreferenceScreen(screen);
    }

    private Preference createTimerPreference() {
        NumberPickerPreference durationPreference = new NumberPickerPreference(getActivity());
        durationPreference.setKey(PREF_KEY_DURATION);
        durationPreference.setEnabled(this.owdEnabled);
        durationPreference.setPersistent(false);
        durationPreference.setDefaultValue(Integer.valueOf(this.owdDurationInMinutes));
        durationPreference.setSummary(formatDurationMinutes(this.owdDurationInMinutes));
        durationPreference.setTitle(C0676R.string.settings_zoneSettings_openWindowDetection_activeTimeLabel);
        durationPreference.setOnPreferenceChangeListener(new C11642());
        return durationPreference;
    }

    private CharSequence formatDurationMinutes(int minutes) {
        return Util.getText(getActivity().getApplicationContext(), C0676R.string.settings_zoneSettings_openWindowDetection_activeTimeDescriptionLabel, Integer.valueOf(minutes));
    }

    private void updateSettings() {
        this.preferencesHaveBeenReset = false;
        OpenWindowDetectionConfiguration configuration = new OpenWindowDetectionConfiguration();
        configuration.setEnabled(this.owdEnabled);
        configuration.setTimeoutInSeconds(this.owdDurationInMinutes * 60);
        setPreferencesUiState(false);
        RestServiceGenerator.getTadoRestService().updateOpenWindowDetectionConfiguration(UserConfig.getHomeId(), this.zoneId, configuration, RestServiceGenerator.getCredentialsMap()).enqueue(new RetryCallback<Void>(new GeneralErrorAlertPresenter(getActivity())) {
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (OpenWindowDetectionPreferencesFragment.this.isAdded()) {
                    OpenWindowDetectionPreferencesFragment.this.setPreferencesUiState(true);
                    if (response.isSuccessful()) {
                        OpenWindowDetectionPreferencesFragment.this.currentOwdEnabled = OpenWindowDetectionPreferencesFragment.this.owdEnabled;
                        OpenWindowDetectionPreferencesFragment.this.currentOwdDurationInMinutes = OpenWindowDetectionPreferencesFragment.this.owdDurationInMinutes;
                        if (OpenWindowDetectionPreferencesFragment.this.preferencesHaveBeenReset) {
                            OpenWindowDetectionPreferencesFragment.this.resetPreferences();
                        }
                    } else {
                        OpenWindowDetectionPreferencesFragment.this.resetPreferences();
                    }
                }
                super.onResponse(call, response);
            }

            public void onFailure(Call<Void> call, Throwable t) {
                if (OpenWindowDetectionPreferencesFragment.this.isAdded()) {
                    OpenWindowDetectionPreferencesFragment.this.setPreferencesUiState(true);
                    OpenWindowDetectionPreferencesFragment.this.resetPreferences();
                }
                super.onFailure(call, t);
            }
        });
    }

    private void resetPreferences() {
        findPreference(PREF_KEY_DURATION).setSummary(formatDurationMinutes(this.currentOwdDurationInMinutes));
        ((SwitchPreference) findPreference(PREF_KEY_ENABLED)).setChecked(this.currentOwdEnabled);
        this.preferencesHaveBeenReset = true;
    }

    private void setPreferencesUiState(boolean enabled) {
        findPreference(PREF_KEY_ENABLED).setEnabled(enabled);
        Preference findPreference = findPreference(PREF_KEY_DURATION);
        boolean z = enabled && this.owdEnabled;
        findPreference.setEnabled(z);
    }
}
