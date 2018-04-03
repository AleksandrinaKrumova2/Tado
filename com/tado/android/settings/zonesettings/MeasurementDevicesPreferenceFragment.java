package com.tado.android.settings.zonesettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import com.tado.android.dialogs.AlertDialogs;
import com.tado.android.rest.callback.RetryCallback;
import com.tado.android.rest.callback.presenters.GeneralErrorSnackbarPresenter;
import com.tado.android.rest.callback.presenters.SendingErrorAlertPresenter;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.settings.zonesettings.IdentifyPreference.OnIdentifyClickListener;
import com.tado.android.utils.ResourceFactory;
import com.tado.android.utils.UserConfig;
import java.io.Serializable;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

public class MeasurementDevicesPreferenceFragment extends PreferenceFragment {
    public static final String KEY_DEVICES = "key-devices";
    private List<GenericHardwareDevice> devices;
    private GenericHardwareDevice selectedDevice;

    class C11591 implements OnIdentifyClickListener {
        C11591() {
        }

        public void onIdentifyClickListener(String serialNumber) {
            MeasurementDevicesPreferenceFragment.this.identify(serialNumber);
        }
    }

    public interface ZonePreferenceListener {
        void onOpenMeasurementDevices(List<GenericHardwareDevice> list);

        void onSelectDevice(GenericHardwareDevice genericHardwareDevice);
    }

    public static MeasurementDevicesPreferenceFragment getInstance(List<GenericHardwareDevice> devices, int zoneId, String selectedDeviceSerialNumber) {
        MeasurementDevicesPreferenceFragment fragment = new MeasurementDevicesPreferenceFragment();
        Bundle bundle = new Bundle(2);
        bundle.putSerializable(KEY_DEVICES, (Serializable) devices);
        bundle.putInt(ZonePreferenceActivity.KEY_ZONE_ID, zoneId);
        bundle.putString("selected", selectedDeviceSerialNumber);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.devices = (List) getArguments().getSerializable(KEY_DEVICES);
        final int zoneId = getArguments().getInt(ZonePreferenceActivity.KEY_ZONE_ID, -1);
        String selectedDeviceSerialNumber = getArguments().getString("selected");
        PreferenceCategory category = new PreferenceCategory(getActivity());
        category.setTitle(C0676R.string.settings_zoneSettings_measurements_measuringDeviceLabel);
        category.setKey("category");
        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(getActivity());
        screen.addPreference(category);
        for (final GenericHardwareDevice device : this.devices) {
            IdentifyPreference identifyPreference = new IdentifyPreference(getActivity());
            identifyPreference.setPersistent(false);
            identifyPreference.setKey(device.getShortSerialNo());
            identifyPreference.setSerialNumber(device.getShortSerialNo());
            identifyPreference.setSelected(device.getShortSerialNo().equals(selectedDeviceSerialNumber));
            identifyPreference.setIdentifiable(device.canBeIdentified());
            identifyPreference.setIdentifyClickListener(new C11591());
            identifyPreference.setIcon(ResourceFactory.getTintedDrawable(getActivity(), ResourceFactory.getDrawableResourceForDeviceType(device.getDeviceType()), C0676R.color.settings_mode_background));
            identifyPreference.setTitle(device.getShortSerialNo());
            identifyPreference.setOnPreferenceClickListener(new OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    ((IdentifyPreference) MeasurementDevicesPreferenceFragment.this.getPreferenceManager().findPreference(MeasurementDevicesPreferenceFragment.this.selectedDevice.getShortSerialNo())).setSelected(false);
                    ((IdentifyPreference) MeasurementDevicesPreferenceFragment.this.getPreferenceManager().findPreference(device.getShortSerialNo())).setSelected(true);
                    MeasurementDevicesPreferenceFragment.this.callSelectMeasurementDevice(device, zoneId);
                    return true;
                }
            });
            category.addPreference(identifyPreference);
            if (device.getShortSerialNo().equals(selectedDeviceSerialNumber)) {
                this.selectedDevice = device;
            }
        }
        setPreferenceScreen(screen);
    }

    private void callSelectMeasurementDevice(final GenericHardwareDevice device, int zoneId) {
        findPreference("category").setEnabled(false);
        GenericHardwareDevice payload = new GenericHardwareDevice();
        payload.setSerialNo(device.getSerialNo());
        RestServiceGenerator.getTadoRestService().setMeasuringDevice(UserConfig.getHomeId(), zoneId, payload, RestServiceGenerator.getCredentialsMap()).enqueue(new RetryCallback<GenericHardwareDevice>(new SendingErrorAlertPresenter(getActivity())) {
            public void onResponse(Call<GenericHardwareDevice> call, Response<GenericHardwareDevice> response) {
                MeasurementDevicesPreferenceFragment.this.findPreference("category").setEnabled(true);
                if (response.isSuccessful()) {
                    MeasurementDevicesPreferenceFragment.this.selectedDevice = device;
                    TadoApplication.getBus().post(device);
                    if (MeasurementDevicesPreferenceFragment.this.getActivity() != null) {
                        MeasurementDevicesPreferenceFragment.this.getActivity().finish();
                    }
                } else {
                    resetPreferences();
                }
                super.onResponse(call, response);
            }

            public void onFailure(Call<GenericHardwareDevice> call, Throwable t) {
                MeasurementDevicesPreferenceFragment.this.findPreference("category").setEnabled(true);
                resetPreferences();
                super.onFailure(call, t);
            }

            private void resetPreferences() {
                ((IdentifyPreference) MeasurementDevicesPreferenceFragment.this.getPreferenceManager().findPreference(MeasurementDevicesPreferenceFragment.this.selectedDevice.getShortSerialNo())).setSelected(true);
                ((IdentifyPreference) MeasurementDevicesPreferenceFragment.this.getPreferenceManager().findPreference(device.getShortSerialNo())).setSelected(false);
            }
        });
    }

    private void identify(String serialNumber) {
        if (serialNumber != null) {
            RestServiceGenerator.getTadoRestService().identifyDevice(serialNumber, RestServiceGenerator.getCredentialsMap()).enqueue(new RetryCallback<Void>(new GeneralErrorSnackbarPresenter(getView())) {
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful() && MeasurementDevicesPreferenceFragment.this.isAdded()) {
                        AlertDialogs.showSimpleWarning(MeasurementDevicesPreferenceFragment.this.getString(C0676R.string.settings_zoneSettings_measurements_selectMeasuringDevice_identifyDeviceActionLabel), MeasurementDevicesPreferenceFragment.this.getString(C0676R.string.settings_zoneSettings_measurements_selectMeasuringDevice_identifyDeviceTriggeredLabel), MeasurementDevicesPreferenceFragment.this.getString(C0676R.string.ok), MeasurementDevicesPreferenceFragment.this.getActivity(), null);
                    }
                    super.onResponse(call, response);
                }
            });
        }
    }
}
