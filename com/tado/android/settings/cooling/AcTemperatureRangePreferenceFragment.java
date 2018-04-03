package com.tado.android.settings.cooling;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import com.tado.C0676R;
import com.tado.android.controllers.CapabilitiesController;
import com.tado.android.rest.model.Hysteresis;
import com.tado.android.settings.zonesettings.TemperatureOffsetPreference;

public class AcTemperatureRangePreferenceFragment extends AcConfigPreferenceFragment implements OnPreferenceChangeListener {
    private static final float MAX_RANGE_CELSIUS = 2.0f;
    private static final float MAX_RANGE_FAHRENHEIT = 4.0f;
    private static final float MIN_RANGE_CELSIUS = 0.1f;
    private static final float MIN_RANGE_FAHRENHEIT = 0.2f;
    private static final float STEP_CELSIUS = 0.1f;
    private static final float STEP_FAHRENHEIT = 0.1f;

    public static AcTemperatureRangePreferenceFragment getInstance(int zoneId, int driverDisc, Integer minOnOffTimeInSeconds, Hysteresis hysteresis) {
        AcTemperatureRangePreferenceFragment fragment = new AcTemperatureRangePreferenceFragment();
        AcConfigPreferenceFragment.setFragmentArguments(zoneId, driverDisc, minOnOffTimeInSeconds, hysteresis, fragment);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPreferenceScreen(getPreferenceManager().createPreferenceScreen(getActivity()));
        TemperatureOffsetPreference rangePreference = new TemperatureOffsetPreference(getActivity());
        rangePreference.setLayoutResource(C0676R.layout.preference_temperature_offset);
        rangePreference.setMinValue(getMinLimit(CapabilitiesController.INSTANCE.isCelsiusTemperatureUnit()));
        rangePreference.setMaxValue(getMaxLimit(CapabilitiesController.INSTANCE.isCelsiusTemperatureUnit()));
        rangePreference.setStep(getStep(CapabilitiesController.INSTANCE.isCelsiusTemperatureUnit()));
        rangePreference.setCurrentOffset(this.hysteresis.getValue());
        rangePreference.setOnPreferenceChangeListener(this);
        getPreferenceScreen().addPreference(rangePreference);
    }

    public boolean onPreferenceChange(Preference preference, Object newValue) {
        this.hysteresis.setValue(((Float) newValue).floatValue());
        return true;
    }

    private float getMinLimit(boolean isCelsius) {
        return isCelsius ? 0.1f : MIN_RANGE_FAHRENHEIT;
    }

    private float getMaxLimit(boolean isCelsius) {
        return isCelsius ? MAX_RANGE_CELSIUS : MAX_RANGE_FAHRENHEIT;
    }

    private float getStep(boolean isCelsius) {
        return isCelsius ? 0.1f : 0.1f;
    }
}
