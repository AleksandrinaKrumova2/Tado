package com.tado.android.settings.zonesettings;

import android.content.Intent;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v14.preference.PreferenceFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;
import android.support.v7.preference.Preference.OnPreferenceClickListener;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceScreen;
import com.crashlytics.android.Crashlytics;
import com.squareup.otto.Subscribe;
import com.tado.C0676R;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.app.TadoApplication;
import com.tado.android.controllers.CapabilitiesController;
import com.tado.android.controllers.ZoneController;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.installation.InstallationProcessZoneStatus;
import com.tado.android.installation.InstallationProcessZoneStatus.InstallationProcessZoneStatusEnum;
import com.tado.android.installation.ResetWifiCredentialsActivity;
import com.tado.android.menu.ZoneItem;
import com.tado.android.rest.callback.RetryCallback;
import com.tado.android.rest.callback.RetryListener;
import com.tado.android.rest.callback.presenters.GeneralErrorAlertPresenter;
import com.tado.android.rest.callback.presenters.GeneralErrorSnackbarPresenter;
import com.tado.android.rest.callback.presenters.SendingErrorAlertPresenter;
import com.tado.android.rest.model.AirConditioningControl;
import com.tado.android.rest.model.OverlayTerminationCondition;
import com.tado.android.rest.model.OverlayTerminationConditionResponse;
import com.tado.android.rest.model.Temperature;
import com.tado.android.rest.model.Zone;
import com.tado.android.rest.model.ZoneState;
import com.tado.android.rest.model.installation.BatteryStateEnum;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.settings.DevicePreference;
import com.tado.android.settings.LinkablePreference;
import com.tado.android.settings.cooling.AcSetupSettingsActivity;
import com.tado.android.settings.interfaces.GenericCallbackListener;
import com.tado.android.settings.manualcontrolsettings.ManualControlSettings;
import com.tado.android.settings.manualcontrolsettings.ManualControlSettingsActivity;
import com.tado.android.utils.ResourceFactory;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.TimeUtils;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Response;

public class ZonePreferenceFragment extends PreferenceFragment implements RetryListener {
    private static final String KEY_COOLING_PREFERENCE = "coolingPreference";
    public static final String KEY_TERMINATION_CONDITION = "terminationCondition";
    private GenericCallbackListener<Void> dazzleModeCallback = new GenericCallbackListener<Void>() {
        public void onSuccess(Void param) {
            updateDazzlePreference(true);
        }

        public void onFailure() {
            if (ZonePreferenceFragment.this.isAdded() && ZonePreferenceFragment.this.zoneItem != null) {
                ZonePreferenceFragment.this.zoneItem.setDazzleEnabled(!ZonePreferenceFragment.this.zoneItem.isDazzleEnabled());
                updateDazzlePreference(true);
            }
        }

        private void updateDazzlePreference(boolean enabled) {
            if (ZonePreferenceFragment.this.isAdded() && ZonePreferenceFragment.this.zoneItem != null) {
                Preference dazzlePreference = ZonePreferenceFragment.this.findPreference("preference_dazzle");
                dazzlePreference.setDefaultValue(Boolean.valueOf(ZonePreferenceFragment.this.zoneItem.isDazzleEnabled()));
                dazzlePreference.setEnabled(enabled);
            }
        }
    };
    private GenericCallbackListener<Zone> getZoneNameListener = new C11789();
    private GenericCallbackListener<List<GenericHardwareDevice>> listMeasurementDevicesListener = new GenericCallbackListener<List<GenericHardwareDevice>>() {
        public void onSuccess(List<GenericHardwareDevice> devices) {
            Collections.sort(devices);
            ZonePreferenceFragment.this.measuringDevices = ZonePreferenceFragment.this.processMeasuringDevices(devices);
            ZonePreferenceFragment.this.addDevicesList(devices);
        }

        public void onFailure() {
        }
    };
    private GenericHardwareDevice measuringDevice;
    private GenericCallbackListener measuringDeviceSelectedListener = new GenericCallbackListener<GenericHardwareDevice>() {
        public void onSuccess(GenericHardwareDevice measuringDevice) {
            ZonePreferenceFragment.this.updateMeasurementDevice(measuringDevice);
        }

        public void onFailure() {
            if (ZonePreferenceFragment.this.isAdded()) {
                Preference preference = ZonePreferenceFragment.this.findPreference("preference_offset_category");
                preference.setEnabled(false);
                preference.setSummary((CharSequence) "-");
                if (ZonePreferenceFragment.this.zoneItem != null && CapabilitiesController.INSTANCE.isCoolingZone(ZonePreferenceFragment.this.zoneItem.getZoneId())) {
                    ZonePreferenceFragment.this.findPreference("preference_reset_wifi_credentials_category").setEnabled(false);
                }
            }
        }
    };
    private List<GenericHardwareDevice> measuringDevices = new ArrayList(0);
    private Temperature offset = Temperature.fromCelsius(0.0f);
    private GenericCallbackListener<Temperature> offsetTemperatureListener = new GenericCallbackListener<Temperature>() {
        public void onSuccess(Temperature newOffset) {
            if (ZonePreferenceFragment.this.isAdded() && ZonePreferenceFragment.this.zoneItem != null) {
                Preference preference = ZonePreferenceFragment.this.findPreference("preference_offset");
                ZonePreferenceFragment.this.zoneItem = ZoneController.INSTANCE.getZoneItemById(ZonePreferenceFragment.this.zoneItem.getZoneId());
                if (ZonePreferenceFragment.this.zoneItem != null) {
                    preference.setSummary(newOffset.getFormattedTemperatureValue(ZonePreferenceFragment.this.zoneItem.getPrecisionValue().floatValue()));
                }
            }
            ZonePreferenceFragment.this.offset = newOffset;
        }

        public void onFailure() {
            if (ZonePreferenceFragment.this.isAdded()) {
                ZonePreferenceFragment.this.findPreference("preference_offset").setSummary((CharSequence) "-");
            }
        }
    };
    private GenericCallbackListener<OverlayTerminationConditionResponse> overlayDefaultPreferencesListener = new GenericCallbackListener<OverlayTerminationConditionResponse>() {
        public void onSuccess(OverlayTerminationConditionResponse body) {
            OverlayTerminationCondition overlayTerminationCondition = body.getOverlayTerminationCondition();
            if (ZonePreferenceFragment.this.isAdded()) {
                Preference preference = ZonePreferenceFragment.this.findPreference(String.format(Locale.US, ManualControlSettings.PREFERENCE_OVERLAY_TERMINATION_CONDITION, new Object[]{Integer.valueOf(UserConfig.getHomeId()), Integer.valueOf(ZonePreferenceFragment.this.zoneItem.getZoneId())}));
                if (overlayTerminationCondition.getType().equals(OverlayTerminationCondition.TIMER)) {
                    preference.setSummary(overlayTerminationCondition.getTimerTypeValue(TimeUtils.getTimeFromSeconds(overlayTerminationCondition.getDurationInSeconds().intValue())));
                } else {
                    preference.setSummary(overlayTerminationCondition.getTypeValue());
                }
                preference.setEnabled(true);
                if (ZonePreferenceFragment.this.getActivity() != null && ZonePreferenceFragment.this.isAdded() && ZonePreferenceFragment.this.zoneItem != null) {
                    Intent intent = new Intent(ZonePreferenceFragment.this.getActivity(), ManualControlSettingsActivity.class);
                    intent.putExtra(ZonePreferenceActivity.KEY_ZONE_ID, ZonePreferenceFragment.this.zoneItem.getZoneId());
                    intent.putExtra(ZonePreferenceFragment.KEY_TERMINATION_CONDITION, overlayTerminationCondition);
                    preference.setIntent(intent);
                }
            }
        }

        public void onFailure() {
        }
    };
    private Preference owdPreference;
    private GenericCallbackListener<Zone> setZoneNameListener = new GenericCallbackListener<Zone>() {
        public void onSuccess(Zone body) {
            String newName = body.getName();
            AnalyticsHelper.trackEvent(ZonePreferenceFragment.this.getActivity(), Screen.ZONE_SETTINGS, "NameChange");
            if (ZonePreferenceFragment.this.isAdded() && ZonePreferenceFragment.this.zoneItem != null) {
                ZonePreferenceFragment.this.zoneItem.setZoneName(newName);
                ZonePreferenceFragment.this.updateZoneName(newName);
            }
        }

        public void onFailure() {
            AnalyticsHelper.trackEvent(ZonePreferenceFragment.this.getActivity(), Screen.ZONE_SETTINGS, "NameFailed");
            if (ZonePreferenceFragment.this.isAdded() && ZonePreferenceFragment.this.zoneItem != null) {
                ZonePreferenceFragment.this.updateZoneName(ZonePreferenceFragment.this.zoneItem.getZoneName());
            }
        }
    };
    private ZoneItem zoneItem;

    class C11701 implements OnPreferenceClickListener {
        C11701() {
        }

        public boolean onPreferenceClick(Preference preference) {
            Intent intent = new Intent(ZonePreferenceFragment.this.getActivity(), OpenWindowDetectionPreferenceActivity.class);
            intent.putExtra(OpenWindowDetectionPreferenceActivity.KEY_OWD_ENABLED, ZonePreferenceFragment.this.zoneItem.isOpenWindowDetectionEnabled());
            intent.putExtra(OpenWindowDetectionPreferenceActivity.KEY_OWD_SECONDS, ZonePreferenceFragment.this.zoneItem.getOpenWindowDetectionIntervalInMinutes());
            intent.putExtra(OpenWindowDetectionPreferenceActivity.KEY_ZONE_ID, ZonePreferenceFragment.this.zoneItem.getZoneId());
            ZonePreferenceFragment.this.startActivity(intent);
            return true;
        }
    }

    class C11712 implements OnPreferenceClickListener {
        C11712() {
        }

        public boolean onPreferenceClick(Preference preference) {
            boolean z;
            ZoneItem access$000 = ZonePreferenceFragment.this.zoneItem;
            if (ZonePreferenceFragment.this.zoneItem.isDazzleEnabled()) {
                z = false;
            } else {
                z = true;
            }
            access$000.setDazzleEnabled(z);
            preference.setEnabled(false);
            ZonePreferenceFragment.this.setDazzleMode();
            return true;
        }
    }

    class C11723 implements OnPreferenceChangeListener {
        C11723() {
        }

        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String trimmedInput = newValue.toString().trim();
            String value = trimmedInput.substring(0, Math.min(trimmedInput.length(), 40));
            if (value.length() == 0) {
                return false;
            }
            preference.setEnabled(false);
            ZonePreferenceFragment.this.setZoneName(value);
            return true;
        }
    }

    class C11734 implements OnPreferenceClickListener {
        C11734() {
        }

        public boolean onPreferenceClick(Preference preference) {
            UserConfig.setSerialNo(ZonePreferenceFragment.this.measuringDevice.getShortSerialNo());
            UserConfig.setDeviceSsid("");
            Intent intent = new Intent(ZonePreferenceFragment.this.getActivity(), ResetWifiCredentialsActivity.class);
            intent.putExtra(ResetWifiCredentialsActivity.RESET_WIFI_CREDENTIALS_SERIAL_NO, ZonePreferenceFragment.this.measuringDevice.getShortSerialNo());
            intent.putExtra(ResetWifiCredentialsActivity.RESET_WIFI_CREDENTIALS_ZONE_ID, ZonePreferenceFragment.this.zoneItem.getZoneId());
            ZonePreferenceFragment.this.startActivity(intent);
            return true;
        }
    }

    class C11756 implements OnPreferenceClickListener {
        C11756() {
        }

        public boolean onPreferenceClick(Preference preference) {
            InstallationProcessController.getInstallationProcessController().isInstallationCreatedForZone(ZonePreferenceFragment.this.zoneItem.getZoneId());
            return false;
        }
    }

    class C11789 implements GenericCallbackListener<Zone> {
        C11789() {
        }

        public void onSuccess(Zone zone) {
            AnalyticsHelper.trackEvent(ZonePreferenceFragment.this.getActivity(), Screen.ZONE_SETTINGS, "NameChange");
            String name = zone.getName();
            if (ZonePreferenceFragment.this.isAdded() && ZonePreferenceFragment.this.zoneItem != null) {
                ZonePreferenceFragment.this.zoneItem.setZoneName(name);
                ZonePreferenceFragment.this.updateZoneName(name);
            }
        }

        public void onFailure() {
            AnalyticsHelper.trackEvent(ZonePreferenceFragment.this.getActivity(), Screen.ZONE_SETTINGS, "NameFailed");
            if (ZonePreferenceFragment.this.isAdded() && ZonePreferenceFragment.this.zoneItem != null) {
                ZonePreferenceFragment.this.updateZoneName(ZonePreferenceFragment.this.zoneItem.getZoneName());
            }
        }
    }

    public static ZonePreferenceFragment getInstance(int zoneId) {
        ZonePreferenceFragment fragment = new ZonePreferenceFragment();
        Bundle bundle = new Bundle(1);
        bundle.putInt(ZonePreferenceActivity.KEY_ZONE_ID, zoneId);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        int zoneId = getArguments().getInt(ZonePreferenceActivity.KEY_ZONE_ID, -1);
        if (zoneId == -1) {
            getActivity().finish();
            return;
        }
        this.zoneItem = ZoneController.INSTANCE.getZoneItemById(zoneId);
        if (this.zoneItem == null) {
            getActivity().finish();
            return;
        }
        AnalyticsHelper.trackPageView(getActivity(), Screen.ZONE_SETTINGS);
        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(getActivity());
        screen.addPreference(createEditTextPreference());
        if (CapabilitiesController.INSTANCE.isCoolingZone(this.zoneItem.getZoneId())) {
            screen.addPreference(createCoolingPreference());
            screen.addPreference(createResetWifiCredentialsPreference());
        }
        screen.addPreference(createManualControlPreference());
        if (this.zoneItem.isHeatingZone() && this.zoneItem.isOpenWindowDetectionSupported()) {
            this.owdPreference = createOpenWindowDetectionPreference();
            screen.addPreference(this.owdPreference);
        }
        addDevicesPreference(screen);
        if (!this.zoneItem.isHotWaterZone()) {
            PreferenceCategory offsetCategory = addMeasurementsTitle(screen);
            addMeasurementsHelp(offsetCategory);
            addMeasurementsPreference(offsetCategory);
            addOffsetPreference(offsetCategory);
        }
        if (this.zoneItem.isSupportsDazzle()) {
            screen.addPreference(getDazzlePreference());
        }
        setPreferenceScreen(screen);
    }

    private Preference createOpenWindowDetectionPreference() {
        CharSequence text;
        Preference owdPreference = new Preference(getActivity());
        owdPreference.setKey("preference_owd");
        owdPreference.setSummary((CharSequence) "summary");
        owdPreference.setTitle((int) C0676R.string.settings_zoneSettings_openWindowDetection_title);
        if (this.zoneItem.isOpenWindowDetectionEnabled()) {
            text = Util.getText(getActivity(), C0676R.string.settings_zoneSettings_openWindowDetection_enabledLabel, Integer.valueOf(this.zoneItem.getOpenWindowDetectionIntervalInMinutes()));
        } else {
            text = getString(C0676R.string.settings_zoneSettings_openWindowDetection_disabledLabel);
        }
        owdPreference.setSummary(text);
        owdPreference.setIcon(ResourceFactory.getTintedVectorSupportDrawable(getActivity(), C0676R.drawable.ic_owd, C0676R.color.settings_mode_background));
        owdPreference.setOnPreferenceClickListener(new C11701());
        owdPreference.setPersistent(false);
        return owdPreference;
    }

    private void updateOpenWindowDetection() {
        if (isAdded() && this.zoneItem != null && this.zoneItem.isHeatingZone() && this.zoneItem.isOpenWindowDetectionSupported() && this.owdPreference != null) {
            CharSequence text;
            Preference preference = this.owdPreference;
            if (this.zoneItem.isOpenWindowDetectionEnabled()) {
                text = Util.getText(getActivity(), C0676R.string.settings_zoneSettings_openWindowDetection_enabledLabel, Integer.valueOf(this.zoneItem.getOpenWindowDetectionIntervalInMinutes()));
            } else {
                text = getString(C0676R.string.settings_zoneSettings_openWindowDetection_disabledLabel);
            }
            preference.setSummary(text);
        }
    }

    private Preference getDazzlePreference() {
        CheckBoxPreference dazzleModePreference = new CheckBoxPreference(getActivity());
        dazzleModePreference.setKey("preference_dazzle");
        dazzleModePreference.setTitle((int) C0676R.string.settings_zoneSettings_dazzle_title);
        dazzleModePreference.setSummary((int) C0676R.string.settings_zoneSettings_dazzle_descriptionLabel);
        dazzleModePreference.setPersistent(false);
        dazzleModePreference.setDefaultValue(Boolean.valueOf(this.zoneItem.isDazzleEnabled()));
        dazzleModePreference.setOnPreferenceClickListener(new C11712());
        return dazzleModePreference;
    }

    private void loadData(boolean loadMeasurementDevice) {
        getZoneName();
        getOverlayTerminationCodition();
        if (loadMeasurementDevice) {
            getMeasurementDevice();
        }
        getZoneDevices();
    }

    public void onResume() {
        super.onResume();
        TadoApplication.getBus().register(this);
        if (this.zoneItem == null) {
            this.zoneItem = ZoneController.INSTANCE.getZoneItemById(getArguments().getInt(ZonePreferenceActivity.KEY_ZONE_ID, -1));
        }
        if (this.zoneItem == null) {
            getActivity().finish();
        } else {
            loadData(!this.zoneItem.isHotWaterZone());
        }
    }

    public void onPause() {
        TadoApplication.getBus().unregister(this);
        super.onPause();
    }

    private Preference createEditTextPreference() {
        EditTextPreference zoneNameEditTextPreference = new EditTextPreference(getActivity());
        zoneNameEditTextPreference.setTitle((int) C0676R.string.settings_zoneSettings_zoneNameLabel);
        zoneNameEditTextPreference.setKey(String.valueOf(this.zoneItem.getZoneId()));
        zoneNameEditTextPreference.setPersistent(false);
        zoneNameEditTextPreference.setDefaultValue(this.zoneItem.getZoneName());
        zoneNameEditTextPreference.setDialogLayoutResource(C0676R.layout.dialog_edit_text_preference);
        if (!(this.zoneItem == null || this.zoneItem.getZoneName() == null)) {
            zoneNameEditTextPreference.setSummary(this.zoneItem.getZoneName());
        }
        zoneNameEditTextPreference.setOnPreferenceChangeListener(new C11723());
        return zoneNameEditTextPreference;
    }

    private Preference createManualControlPreference() {
        Preference manualControlPreference = new Preference(getActivity());
        manualControlPreference.setLayoutResource(C0676R.layout.preference_manual_control);
        manualControlPreference.setTitle((int) C0676R.string.settings_zoneSettings_terminationCondition_title);
        manualControlPreference.setKey(String.format(Locale.US, ManualControlSettings.PREFERENCE_OVERLAY_TERMINATION_CONDITION, new Object[]{Integer.valueOf(UserConfig.getHomeId()), Integer.valueOf(this.zoneItem.getZoneId())}));
        manualControlPreference.setSummary((int) C0676R.string.settings_loadingLabel);
        manualControlPreference.setIcon((int) C0676R.drawable.manual_control);
        manualControlPreference.getIcon().mutate().setColorFilter(ContextCompat.getColor(getActivity(), C0676R.color.settings_mode_background), Mode.SRC_ATOP);
        manualControlPreference.setEnabled(false);
        manualControlPreference.setPersistent(false);
        return manualControlPreference;
    }

    private Preference createCoolingPreference() {
        Preference coolingPreference = new Preference(getActivity());
        coolingPreference.setTitle((int) C0676R.string.settings_zoneSettings_airConditionerLabel);
        coolingPreference.setKey(KEY_COOLING_PREFERENCE);
        coolingPreference.setOnPreferenceClickListener(getAcSetupClickListener());
        coolingPreference.setPersistent(false);
        return coolingPreference;
    }

    private Preference createResetWifiCredentialsPreference() {
        Preference resetWifiCredentialsPreference = new Preference(getActivity());
        resetWifiCredentialsPreference.setTitle((int) C0676R.string.settings_airConditioning_resetWifiCredentialsButton);
        resetWifiCredentialsPreference.setOnPreferenceClickListener(getResetWifiCredentialsClickListener());
        resetWifiCredentialsPreference.setKey("preference_reset_wifi_credentials_category");
        resetWifiCredentialsPreference.setEnabled(false);
        resetWifiCredentialsPreference.setPersistent(false);
        return resetWifiCredentialsPreference;
    }

    @NonNull
    private OnPreferenceClickListener getResetWifiCredentialsClickListener() {
        return new C11734();
    }

    private void addDevicesPreference(PreferenceScreen screen) {
        PreferenceCategory preferenceCategory = new PreferenceCategory(getActivity());
        preferenceCategory.setKey("preference_devices_category");
        preferenceCategory.setTitle((int) C0676R.string.settings_zoneSettings_devices_title);
        screen.addPreference(preferenceCategory);
    }

    private PreferenceCategory addMeasurementsTitle(PreferenceScreen screen) {
        PreferenceCategory preferenceCategory = new PreferenceCategory(getActivity());
        preferenceCategory.setTitle((int) C0676R.string.settings_zoneSettings_measurements_title);
        preferenceCategory.setKey("preference_offset_category");
        preferenceCategory.setEnabled(false);
        screen.addPreference(preferenceCategory);
        return preferenceCategory;
    }

    private void addMeasurementsHelp(PreferenceCategory screen) {
        LinkablePreference descriptionPreference = new LinkablePreference(getActivity());
        descriptionPreference.setSummary(getText(C0676R.string.settings_zoneSettings_measurements_measurementsInfoLabel));
        descriptionPreference.setPersistent(false);
        screen.addPreference(descriptionPreference);
    }

    private void addMeasurementsPreference(PreferenceCategory screen) {
        Preference measurementDevice = new Preference(getActivity());
        measurementDevice.setTitle((int) C0676R.string.settings_zoneSettings_measurements_measuringDeviceLabel);
        measurementDevice.setSummary((int) C0676R.string.settings_loadingLabel);
        measurementDevice.setKey("preference_measurement");
        measurementDevice.setPersistent(false);
        screen.addPreference(measurementDevice);
    }

    private void addOffsetPreference(PreferenceCategory screen) {
        final Preference currentOffset = new Preference(getActivity());
        currentOffset.setTitle((int) C0676R.string.settings_zoneSettings_measurements_offsetLabel);
        currentOffset.setSummary((int) C0676R.string.settings_loadingLabel);
        currentOffset.setKey("preference_offset");
        currentOffset.setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                currentOffset.setIntent(ZonePreferenceFragment.this.getTemperatureOffsetIntent());
                return false;
            }
        });
        screen.addPreference(currentOffset);
    }

    private Intent getTemperatureOffsetIntent() {
        Intent intent = new Intent(getActivity(), TemperatureOffsetPreferenceActivity.class);
        intent.putExtra(ZonePreferenceActivity.KEY_ZONE_ID, this.zoneItem.getZoneId());
        intent.putExtra(TemperatureOffsetFragment.KEY_MEASURING_DEVICE, this.measuringDevice);
        intent.putExtra(TemperatureOffsetFragment.KEY_OFFSET, this.offset);
        return intent;
    }

    @NonNull
    private OnPreferenceClickListener getAcSetupClickListener() {
        return new C11756();
    }

    private void showAcSetupSettingActivity(final boolean showResetAcButton) {
        ((ZonePreferenceActivity) getActivity()).showProgressBar();
        findPreference(KEY_COOLING_PREFERENCE).setEnabled(false);
        RestServiceGenerator.getTadoRestService().getZoneControl(UserConfig.getHomeId(), this.zoneItem.getZoneId(), RestServiceGenerator.getCredentialsMap()).enqueue(new RetryCallback<AirConditioningControl>(new GeneralErrorAlertPresenter(getActivity())) {
            public void onResponse(Call<AirConditioningControl> call, Response<AirConditioningControl> response) {
                super.onResponse(call, response);
                resetPreference(false);
                if (response.isSuccessful()) {
                    AirConditioningControl control = (AirConditioningControl) response.body();
                    Intent intent = new Intent(ZonePreferenceFragment.this.getActivity(), AcSetupSettingsActivity.class);
                    intent.putExtra(AcSetupSettingsActivity.KEY_SELECTED_ZONE_ID, ZonePreferenceFragment.this.zoneItem.getZoneId());
                    intent.putExtra(AcSetupSettingsActivity.KEY_SHOW_RESET_AC_BUTTON, showResetAcButton);
                    intent.putExtra(AcSetupSettingsActivity.KEY_AC_DRIVER, control.getDriver());
                    intent.putExtra(AcSetupSettingsActivity.KEY_CONFIGURATION, control.getCommandConfiguration());
                    ZonePreferenceFragment.this.startActivity(intent);
                }
            }

            public void onFailure(Call<AirConditioningControl> call, Throwable t) {
                super.onFailure(call, t);
                resetPreference(true);
            }

            private void resetPreference(boolean animate) {
                ((ZonePreferenceActivity) ZonePreferenceFragment.this.getActivity()).hideProgressBar(animate);
                ZonePreferenceFragment.this.findPreference(ZonePreferenceFragment.KEY_COOLING_PREFERENCE).setEnabled(true);
            }
        });
    }

    private void getOverlayTerminationCodition() {
        ZoneController.INSTANCE.getOverlayDefaultPreferences(this.zoneItem.getZoneId(), this.overlayDefaultPreferencesListener, new GeneralErrorSnackbarPresenter(getView()), this);
    }

    private void getZoneName() {
        ZoneController.INSTANCE.getZoneName(this.zoneItem.getZoneId(), this.getZoneNameListener, new GeneralErrorSnackbarPresenter(getView()), this);
    }

    private void setZoneName(String newName) {
        ZoneController.INSTANCE.updateZoneName(this.zoneItem.getZoneId(), newName, this.setZoneNameListener, new SendingErrorAlertPresenter(getActivity()));
    }

    private void getMeasurementDevice() {
        ZoneController.INSTANCE.getMeasurementDevice(this.zoneItem.getZoneId(), this.measuringDeviceSelectedListener, new GeneralErrorSnackbarPresenter(getView()), this);
    }

    private void getOffsetTemperature(String serialNumber) {
        ZoneController.INSTANCE.getOffsetTemperature(serialNumber, this.offsetTemperatureListener, new GeneralErrorSnackbarPresenter(getView()));
    }

    private void getZoneDevices() {
        ZoneController.INSTANCE.getZoneDevices(UserConfig.getHomeId(), this.zoneItem.getZoneId(), this.listMeasurementDevicesListener, new GeneralErrorSnackbarPresenter(getView()), this);
    }

    private void setDazzleMode() {
        ZoneController.INSTANCE.updateDazzleMode(UserConfig.getHomeId(), this.zoneItem.getZoneId(), this.zoneItem.isDazzleEnabled(), this.dazzleModeCallback, new GeneralErrorAlertPresenter(getActivity()));
    }

    private void updateZoneName(String name) {
        if (isAdded() && this.zoneItem != null) {
            EditTextPreference zoneNameEditTextPreference = (EditTextPreference) findPreference(String.valueOf(this.zoneItem.getZoneId()));
            zoneNameEditTextPreference.setText(name);
            zoneNameEditTextPreference.setSummary((CharSequence) name);
            zoneNameEditTextPreference.setEnabled(true);
            try {
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle((CharSequence) name);
            } catch (NullPointerException e) {
                Crashlytics.logException(e);
            }
        }
    }

    public void updateMeasurementDevice(final GenericHardwareDevice measuringDevice) {
        this.measuringDevice = measuringDevice;
        Preference preference = findPreference("preference_measurement");
        if (preference != null) {
            preference.setSummary(this.measuringDevice.getShortSerialNo());
            preference.setOnPreferenceClickListener(new OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    if (ZonePreferenceFragment.this.measuringDevices == null || ZonePreferenceFragment.this.measuringDevices.size() <= 1) {
                        return false;
                    }
                    Intent intent = new Intent(ZonePreferenceFragment.this.getActivity(), MeasurementDevicesPreferenceActivity.class);
                    intent.putExtra(MeasurementDevicesPreferenceFragment.KEY_DEVICES, (Serializable) ZonePreferenceFragment.this.measuringDevices);
                    intent.putExtra(ZonePreferenceActivity.KEY_ZONE_ID, ZonePreferenceFragment.this.zoneItem.getZoneId());
                    intent.putExtra("selected", measuringDevice.getShortSerialNo());
                    ZonePreferenceFragment.this.startActivity(intent);
                    return true;
                }
            });
        }
        if (this.zoneItem != null) {
            ZoneController.INSTANCE.callGetZoneState(this.zoneItem.getZoneId());
        }
        Preference preferenceCategory = findPreference("preference_offset_category");
        if (preferenceCategory != null) {
            preferenceCategory.setEnabled(true);
        }
        if (CapabilitiesController.INSTANCE.isCoolingZone(this.zoneItem.getZoneId())) {
            findPreference("preference_reset_wifi_credentials_category").setEnabled(true);
        }
    }

    private void addDevicesList(List<GenericHardwareDevice> devices) {
        if (devices != null && isAdded()) {
            PreferenceCategory devicesCategory = (PreferenceCategory) findPreference("preference_devices_category");
            devicesCategory.removeAll();
            for (GenericHardwareDevice device : devices) {
                DevicePreference devicesPreference = new DevicePreference(getActivity());
                devicesPreference.setIcon(ResourceFactory.getTintedDrawable(getActivity(), ResourceFactory.getDrawableResourceForDeviceType(device.getDeviceType()), C0676R.color.settings_mode_background));
                devicesPreference.setTitle(ResourceFactory.getDeviceName(device.getDeviceType()));
                devicesPreference.setSummary(device.getShortSerialNo());
                devicesPreference.setHasBadge(device.getBatteryState() == BatteryStateEnum.LOW);
                devicesPreference.setIntent(DeviceDetailsActivity.getIntent(getActivity(), device, this.zoneItem.getZoneType(), this.zoneItem.getZoneId()));
                devicesCategory.addPreference(devicesPreference);
            }
        }
    }

    private List<GenericHardwareDevice> processMeasuringDevices(List<GenericHardwareDevice> devices) {
        List<GenericHardwareDevice> measuringDevices = new ArrayList();
        for (GenericHardwareDevice device : devices) {
            if (device.canMeasureTemperature()) {
                measuringDevices.add(device);
            }
        }
        return measuringDevices;
    }

    private void showErrorMessage(String msg) {
        Snitcher.start().toCrashlytics().log("ZonePreferenceFragment", msg, new Object[0]);
        if (getView() != null && isAdded()) {
            Snackbar.make(getView(), (CharSequence) msg, 0).show();
        }
    }

    @Subscribe
    public void getInstallationProcessStatus(InstallationProcessZoneStatus status) {
        if (status.getStatus() == InstallationProcessZoneStatusEnum.ZONE_INSTALLATION_COMPLETED) {
            showAcSetupSettingActivity(true);
        } else if (status.getStatus() == InstallationProcessZoneStatusEnum.ZONE_INSTALLATION_NOT_COMPLETED) {
            showAcSetupSettingActivity(false);
        }
    }

    @Subscribe
    public void onGetZoneState(ZoneState zoneState) {
        this.zoneItem = ZoneController.INSTANCE.getZoneItemById(this.zoneItem.getZoneId());
        if (this.zoneItem == null) {
            getActivity().finish();
            return;
        }
        updateOpenWindowDetection();
        updateZoneName(this.zoneItem.getZoneName());
        if (this.measuringDevice != null) {
            getOffsetTemperature(this.measuringDevice.getShortSerialNo());
        }
    }

    @Subscribe
    public void onUpdateMeasurementDevice(GenericHardwareDevice device) {
        updateMeasurementDevice(device);
    }

    public void retry() {
        loadData(!this.zoneItem.isHotWaterZone());
    }
}
