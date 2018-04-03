package com.tado.android.times;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.preference.SwitchPreferenceCompat;
import android.widget.ListView;
import android.widget.Toast;
import com.tado.C0676R;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.EarlyStart;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.UserConfig;
import retrofit2.Call;
import retrofit2.Response;

public class SmartScheduleSettingsPreferenceFragment extends PreferenceFragmentCompat {
    public static final String KEY_PREF_EARLY_START = "key_pref_early_start";
    public static final String KEY_PREF_EARLY_START_IMAGE = "key_pref_early_start_image";
    private OnSharedPreferenceChangeListener mSharedPreferenceChangeListener;
    private SharedPreferences mSharedPreferences;

    class C12051 implements OnSharedPreferenceChangeListener {
        C12051() {
        }

        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            SmartScheduleSettingsPreferenceFragment.this.getPreferenceChangedListener(sharedPreferences, key);
        }
    }

    class C12062 extends TadoCallback<EarlyStart> {
        C12062() {
        }

        public void onResponse(Call<EarlyStart> call, Response<EarlyStart> response) {
            super.onResponse(call, response);
            SmartScheduleSettingsPreferenceFragment.this.enableUi(true);
            if (response.isSuccessful()) {
                SmartScheduleSettingsPreferenceFragment.this.changeEarlyStartPreferences(((EarlyStart) response.body()).getEnabled().booleanValue());
            }
            SmartScheduleSettingsPreferenceFragment.this.registerSharedPreferenceChangeListener();
        }

        public void onFailure(Call<EarlyStart> call, Throwable t) {
            super.onFailure(call, t);
            SmartScheduleSettingsPreferenceFragment.this.enableUi(true);
            SmartScheduleSettingsPreferenceFragment.this.registerSharedPreferenceChangeListener();
        }
    }

    public static SmartScheduleSettingsPreferenceFragment newInstance() {
        return new SmartScheduleSettingsPreferenceFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceManager();
        this.mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        addPreferencesFromResource(C0676R.xml.pref_smart_schedule_settings);
        this.mSharedPreferenceChangeListener = new C12051();
    }

    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    }

    public void onResume() {
        super.onResume();
        hideDividerLines();
        getEarlyStart();
    }

    public void onPause() {
        this.mSharedPreferences.unregisterOnSharedPreferenceChangeListener(this.mSharedPreferenceChangeListener);
        super.onPause();
    }

    private void registerSharedPreferenceChangeListener() {
        this.mSharedPreferences.registerOnSharedPreferenceChangeListener(this.mSharedPreferenceChangeListener);
    }

    private void unregisterSharedPreferenceChangeListener() {
        this.mSharedPreferences.unregisterOnSharedPreferenceChangeListener(this.mSharedPreferenceChangeListener);
    }

    private void getEarlyStart() {
        enableUi(false);
        RestServiceGenerator.getTadoRestService().getEarlyStart(UserConfig.getHomeId(), UserConfig.getCurrentZone().intValue(), RestServiceGenerator.getCredentialsMap()).enqueue(new C12062());
    }

    private void changePreferenceImage(boolean isEarlyStartEnabled) {
        Preference preferenceImageView = findPreference(KEY_PREF_EARLY_START_IMAGE);
        if (VERSION.SDK_INT < 21) {
            int i;
            if (isEarlyStartEnabled) {
                i = C0676R.drawable.ic_early_start_on;
            } else {
                i = C0676R.drawable.ic_early_start_off;
            }
            preferenceImageView.setIcon(i);
            return;
        }
        if (isEarlyStartEnabled) {
            preferenceImageView.setIcon((int) C0676R.drawable.early_start_animation_off_to_on);
        } else {
            preferenceImageView.setIcon((int) C0676R.drawable.early_start_animation_on_to_off);
        }
        ((AnimatedVectorDrawable) preferenceImageView.getIcon()).start();
    }

    private void changePreferenceCheckbox(boolean isEarlyStartEnabled) {
        SwitchPreferenceCompat preference = (SwitchPreferenceCompat) findPreference(KEY_PREF_EARLY_START);
        preference.setChecked(isEarlyStartEnabled);
        preference.setTitle((int) C0676R.string.smartSchedule_settings_earlyStartLabel);
        preference.setSummaryOn((int) C0676R.string.smartSchedule_settings_earlyStartEnabledDescription);
        preference.setSummaryOff((int) C0676R.string.smartSchedule_settings_earlyStartDisabledDescription);
        preference.setSummary((int) C0676R.string.smartSchedule_settings_earlyStartEnabledDescription);
    }

    private void changeEarlyStartPreferences(boolean isEarlyStartEnabled) {
        changePreferenceImage(isEarlyStartEnabled);
        changePreferenceCheckbox(isEarlyStartEnabled);
    }

    private void getPreferenceChangedListener(final SharedPreferences sharedPreferences, String key) {
        if (KEY_PREF_EARLY_START.equals(key)) {
            enableUi(false);
            RestServiceGenerator.getTadoRestService().updateEarlyStart(UserConfig.getHomeId(), UserConfig.getCurrentZone().intValue(), EarlyStart.create(sharedPreferences.getBoolean(KEY_PREF_EARLY_START, false)), RestServiceGenerator.getCredentialsMap()).enqueue(new TadoCallback<EarlyStart>() {
                public void onResponse(Call<EarlyStart> call, Response<EarlyStart> response) {
                    boolean z = true;
                    super.onResponse(call, response);
                    if (SmartScheduleSettingsPreferenceFragment.this.isAdded()) {
                        SmartScheduleSettingsPreferenceFragment.this.enableUi(true);
                        if (response.isSuccessful()) {
                            SmartScheduleSettingsPreferenceFragment.this.changeEarlyStartPreferences(((EarlyStart) response.body()).getEnabled().booleanValue());
                            return;
                        }
                        SmartScheduleSettingsPreferenceFragment.this.showError();
                        SmartScheduleSettingsPreferenceFragment.this.unregisterSharedPreferenceChangeListener();
                        Editor edit = sharedPreferences.edit();
                        String str = SmartScheduleSettingsPreferenceFragment.KEY_PREF_EARLY_START;
                        if (sharedPreferences.getBoolean(SmartScheduleSettingsPreferenceFragment.KEY_PREF_EARLY_START, false)) {
                            z = false;
                        }
                        edit.putBoolean(str, z).apply();
                        SmartScheduleSettingsPreferenceFragment.this.changeEarlyStartPreferences(sharedPreferences.getBoolean(SmartScheduleSettingsPreferenceFragment.KEY_PREF_EARLY_START, false));
                        SmartScheduleSettingsPreferenceFragment.this.registerSharedPreferenceChangeListener();
                    }
                }

                public void onFailure(Call<EarlyStart> call, Throwable t) {
                    super.onFailure(call, t);
                    if (SmartScheduleSettingsPreferenceFragment.this.isAdded()) {
                        SmartScheduleSettingsPreferenceFragment.this.unregisterSharedPreferenceChangeListener();
                        sharedPreferences.edit().putBoolean(SmartScheduleSettingsPreferenceFragment.KEY_PREF_EARLY_START, !sharedPreferences.getBoolean(SmartScheduleSettingsPreferenceFragment.KEY_PREF_EARLY_START, false)).apply();
                        SmartScheduleSettingsPreferenceFragment.this.changeEarlyStartPreferences(sharedPreferences.getBoolean(SmartScheduleSettingsPreferenceFragment.KEY_PREF_EARLY_START, false));
                        SmartScheduleSettingsPreferenceFragment.this.registerSharedPreferenceChangeListener();
                        SmartScheduleSettingsPreferenceFragment.this.enableUi(true);
                        SmartScheduleSettingsPreferenceFragment.this.showError();
                    }
                }
            });
        }
    }

    private void enableUi(boolean enabled) {
        findPreference(KEY_PREF_EARLY_START).setEnabled(enabled);
    }

    private void hideDividerLines() {
        if (getView() != null) {
            ListView list = (ListView) getView().findViewById(16908298);
            if (list != null) {
                list.setDivider(null);
            }
        }
    }

    private void showError() {
        Toast.makeText(getActivity(), getString(C0676R.string.smartSchedule_notifications_changesNotSavedMessage), 0).show();
    }
}
