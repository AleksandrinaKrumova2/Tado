package com.tado.android.settings.zonesettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import com.tado.C0676R;
import com.tado.android.controllers.CapabilitiesController;
import com.tado.android.controllers.ZoneController;
import com.tado.android.dialogs.AlertDialogs;
import com.tado.android.dialogs.AlertWarningDialogListener;
import com.tado.android.menu.ZoneItem;
import com.tado.android.rest.callback.RetryCallback;
import com.tado.android.rest.callback.presenters.SendingErrorAlertPresenter;
import com.tado.android.rest.model.Temperature;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.Constants;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Response;

public class TemperatureOffsetFragment extends PreferenceFragment implements OnPreferenceChangeListener {
    public static final String KEY_MEASURING_DEVICE = "measuring_device";
    public static final String KEY_OFFSET = "offset";
    private Preference currentTemperaturePreference;
    private boolean hasChanges = false;
    private Temperature offset;
    private float offsetDiff = 0.0f;
    private ZoneItem zoneItem;

    interface FragmentInterface {
        void onOffsetSaved(Temperature temperature);
    }

    public static TemperatureOffsetFragment getInstance(int zoneId, Temperature currentOffset) {
        TemperatureOffsetFragment fragment = new TemperatureOffsetFragment();
        Bundle bundle = new Bundle(3);
        bundle.putInt(ZonePreferenceActivity.KEY_ZONE_ID, zoneId);
        bundle.putSerializable(KEY_OFFSET, currentOffset);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.zoneItem = ZoneController.INSTANCE.getZoneItemById(getArguments().getInt(ZonePreferenceActivity.KEY_ZONE_ID, -1));
        this.offset = (Temperature) getArguments().getSerializable(KEY_OFFSET);
        if (this.zoneItem == null) {
            getActivity().finish();
        }
    }

    public void onResume() {
        super.onResume();
        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(getActivity());
        this.currentTemperaturePreference = addCurrentTemperaturePreference(screen);
        PreferenceCategory offsetCategory = new PreferenceCategory(getActivity());
        offsetCategory.setTitle(C0676R.string.settings_zoneSettings_measurements_temperatureOffset_offsetLabel);
        screen.addPreference(offsetCategory);
        addOffsetPreference(screen);
        addDescription(screen);
        setPreferenceScreen(screen);
    }

    private Preference addCurrentTemperaturePreference(PreferenceScreen screen) {
        Preference currentTemperature = new Preference(getActivity());
        currentTemperature.setTitle(C0676R.string.settings_zoneSettings_measurements_temperatureOffset_temperatureLabel);
        currentTemperature.setKey("preference_current");
        currentTemperature.setPersistent(false);
        if (this.zoneItem.getTemperatureValue() != null) {
            currentTemperature.setSummary(Temperature.fromValue(this.zoneItem.getTemperatureValue().floatValue()).getFormattedTemperatureValue(this.zoneItem.getPrecisionValue().floatValue()));
        } else {
            currentTemperature.setSummary("-°");
            currentTemperature.setEnabled(false);
        }
        screen.addPreference(currentTemperature);
        return currentTemperature;
    }

    private TemperatureOffsetPreference addOffsetPreference(PreferenceScreen screen) {
        TemperatureOffsetPreference temperatureOffsetPreference = new TemperatureOffsetPreference(getActivity());
        temperatureOffsetPreference.setLayoutResource(C0676R.layout.preference_temperature_offset);
        temperatureOffsetPreference.setMinValue(getMinLimit(CapabilitiesController.INSTANCE.isCelsiusTemperatureUnit(), this.zoneItem.getPrecisionValue().floatValue()));
        temperatureOffsetPreference.setMaxValue(getMaxLimit(CapabilitiesController.INSTANCE.isCelsiusTemperatureUnit()));
        temperatureOffsetPreference.setStep(this.zoneItem.getPrecisionValue().floatValue());
        temperatureOffsetPreference.setCurrentOffset(this.offset.getTemperatureValue());
        temperatureOffsetPreference.setKey("preference_offset");
        temperatureOffsetPreference.setPersistent(true);
        temperatureOffsetPreference.setOnPreferenceChangeListener(this);
        screen.addPreference(temperatureOffsetPreference);
        return temperatureOffsetPreference;
    }

    private void addDescription(PreferenceScreen screen) {
        Preference description = new Preference(getActivity());
        description.setSummary(C0676R.string.settings_zoneSettings_measurements_temperatureOffset_offsetDescription);
        description.setPersistent(false);
        screen.addPreference(description);
    }

    private float getMaxLimit(boolean isCelsius) {
        if (isCelsius) {
            return Constants.MAX_OFFSET_CELSIUS;
        }
        return Constants.MAX_OFFSET_FAHRENHEIT;
    }

    private float getMinLimit(boolean isCelsius, float precesion) {
        return isCelsius ? precesion == 1.0f ? -9.0f : Constants.MIN_OFFSET_CELSIUS : precesion == 1.0f ? -17.0f : Constants.MIN_OFFSET_FAHRENHEIT;
    }

    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (!preference.getKey().equals("preference_offset") || this.zoneItem.getTemperatureValue() == null) {
            return false;
        }
        this.offsetDiff = ((Float) newValue).floatValue() - this.offset.getTemperatureValue();
        if (this.zoneItem.getPrecisionValue().floatValue() < 1.0f) {
            this.currentTemperaturePreference.setSummary(String.format(Locale.US, "%.1f°", new Object[]{Float.valueOf(this.zoneItem.getTemperatureValue().floatValue() + this.offsetDiff)}));
        } else {
            this.currentTemperaturePreference.setSummary(String.format(Locale.US, "%.0f°", new Object[]{Float.valueOf(this.zoneItem.getTemperatureValue().floatValue() + this.offsetDiff)}));
        }
        this.hasChanges = true;
        return true;
    }

    private void updateTemperatureOffset(final GenericHardwareDevice measuringDevice, Temperature value) {
        if (CapabilitiesController.INSTANCE.isCelsiusTemperatureUnit()) {
            value.setFahrenheit(null);
        } else {
            value.setCelsius(null);
        }
        RestServiceGenerator.getTadoRestService().setTemperatureOffset(measuringDevice.getShortSerialNo(), value, RestServiceGenerator.getCredentialsMap()).enqueue(new RetryCallback<Temperature>(new SendingErrorAlertPresenter(getActivity())) {
            public void onResponse(Call<Temperature> call, final Response<Temperature> response) {
                super.onResponse(call, response);
                if (response.isSuccessful() && TemperatureOffsetFragment.this.isAdded()) {
                    int confirmationMessageStringResource = C0676R.string.settings_zoneSettings_measurements_temperatureOffset_offsetConfirmation_changesTakeTimeMessage;
                    if (measuringDevice.isTemperatureSensor()) {
                        confirmationMessageStringResource = C0676R.string.settings_zoneSettings_measurements_temperatureOffset_offsetConfirmation_changesTakeLongTimeMessage;
                    }
                    AlertDialogs.showSimpleWarning("", TemperatureOffsetFragment.this.getResources().getString(confirmationMessageStringResource), TemperatureOffsetFragment.this.getString(C0676R.string.settings_zoneSettings_measurements_temperatureOffset_offsetConfirmation_confirmButton), TemperatureOffsetFragment.this.getActivity(), new AlertWarningDialogListener() {
                        public void OnOKClicked() {
                            ((FragmentInterface) TemperatureOffsetFragment.this.getActivity()).onOffsetSaved((Temperature) response.body());
                        }
                    });
                }
            }
        });
    }

    public void saveChanges(GenericHardwareDevice measuringDevice) {
        updateTemperatureOffset(measuringDevice, this.offset.add(this.offsetDiff));
    }

    public boolean hasChanges() {
        return this.hasChanges;
    }
}
