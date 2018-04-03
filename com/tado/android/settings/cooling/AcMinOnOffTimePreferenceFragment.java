package com.tado.android.settings.cooling;

import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.SwitchPreference;
import com.tado.C0676R;
import com.tado.android.rest.model.Hysteresis;
import com.tado.android.settings.zonesettings.NumberPickerPreference;
import com.tado.android.utils.Util;

public class AcMinOnOffTimePreferenceFragment extends AcConfigPreferenceFragment {
    private static final String KEY_DURATION_PREFERENCE = "DURATION_PREFERENCE";

    class C10901 implements OnPreferenceChangeListener {
        C10901() {
        }

        public boolean onPreferenceChange(Preference preference, Object newValue) {
            boolean enabled = ((Boolean) newValue).booleanValue();
            if (enabled && AcMinOnOffTimePreferenceFragment.this.minOnOffTimeInSeconds == null) {
                AcMinOnOffTimePreferenceFragment.this.minOnOffTimeInSeconds = Integer.valueOf(60);
            } else if (!enabled) {
                AcMinOnOffTimePreferenceFragment.this.minOnOffTimeInSeconds = null;
            }
            ((NumberPickerPreference) AcMinOnOffTimePreferenceFragment.this.findPreference(AcMinOnOffTimePreferenceFragment.KEY_DURATION_PREFERENCE)).setEnabled(enabled);
            return true;
        }
    }

    class C10912 implements OnPreferenceChangeListener {
        C10912() {
        }

        public boolean onPreferenceChange(Preference timePreference, Object newValue) {
            int minutes = ((Integer) newValue).intValue();
            timePreference.setSummary(Util.getText(AcMinOnOffTimePreferenceFragment.this.getActivity().getApplicationContext(), C0676R.string.settings_zoneSettings_airConditioning_minimumOnOffTime_activeTimeDescriptionLabel, Integer.valueOf(minutes)));
            AcMinOnOffTimePreferenceFragment.this.minOnOffTimeInSeconds = Integer.valueOf(minutes * 60);
            return true;
        }
    }

    protected static AcConfigPreferenceFragment getFragmentInstance() {
        return new AcMinOnOffTimePreferenceFragment();
    }

    public static AcMinOnOffTimePreferenceFragment getInstance(int zoneId, int driverDisc, Integer minOnOffTimeInSeconds, Hysteresis hysteresis) {
        AcMinOnOffTimePreferenceFragment fragment = new AcMinOnOffTimePreferenceFragment();
        AcConfigPreferenceFragment.setFragmentArguments(zoneId, driverDisc, minOnOffTimeInSeconds, hysteresis, fragment);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        boolean z;
        int intValue;
        super.onCreate(savedInstanceState);
        setPreferenceScreen(getPreferenceManager().createPreferenceScreen(getActivity()));
        SwitchPreference onOffTimeCheckBoxPreference = new SwitchPreference(getActivity());
        onOffTimeCheckBoxPreference.setTitle(C0676R.string.settings_zoneSettings_airConditioning_minimumOnOffTimeLabel);
        if (this.minOnOffTimeInSeconds != null) {
            z = true;
        } else {
            z = false;
        }
        onOffTimeCheckBoxPreference.setChecked(z);
        onOffTimeCheckBoxPreference.setOnPreferenceChangeListener(new C10901());
        getPreferenceScreen().addPreference(onOffTimeCheckBoxPreference);
        NumberPickerPreference durationPreference = new NumberPickerPreference(getActivity());
        durationPreference.setKey(KEY_DURATION_PREFERENCE);
        durationPreference.setEnabled(onOffTimeCheckBoxPreference.isChecked());
        durationPreference.setPersistent(false);
        if (this.minOnOffTimeInSeconds != null) {
            intValue = this.minOnOffTimeInSeconds.intValue() / 60;
        } else {
            intValue = 1;
        }
        durationPreference.setDefaultValue(Integer.valueOf(intValue));
        Context applicationContext = getActivity().getApplicationContext();
        Object[] objArr = new Object[1];
        if (this.minOnOffTimeInSeconds != null) {
            intValue = this.minOnOffTimeInSeconds.intValue() / 60;
        } else {
            intValue = 1;
        }
        objArr[0] = Integer.valueOf(intValue);
        durationPreference.setSummary(Util.getText(applicationContext, C0676R.string.settings_zoneSettings_airConditioning_minimumOnOffTime_activeTimeDescriptionLabel, objArr));
        durationPreference.setTitle(C0676R.string.settings_zoneSettings_airConditioning_minimumOnOffTimeLabel);
        durationPreference.setMinValue(1);
        durationPreference.setOnPreferenceChangeListener(new C10912());
        getPreferenceScreen().addPreference(durationPreference);
    }
}
