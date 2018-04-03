package com.tado.android.settings.users;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v14.preference.PreferenceFragment;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.preference.Preference;
import android.support.v7.preference.Preference.OnPreferenceClickListener;
import android.support.v7.preference.PreferenceScreen;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import com.tado.android.login.LoginActivity;
import com.tado.android.rest.callback.RetryCallback;
import com.tado.android.rest.callback.presenters.SendingErrorAlertPresenter;
import com.tado.android.rest.model.MobileDevice;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.settings.LinkablePreference;
import com.tado.android.settings.appsettings.AppSettingsActivity;
import com.tado.android.settings.appsettings.AppSettingsPreferenceFragment;
import com.tado.android.utils.ResourceFactory;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import retrofit2.Call;
import retrofit2.Response;

public class MobileDeviceDetailsPreferencesFragment extends PreferenceFragment {
    private static final String KEY_LOCATION_BASED_CONTROL = "keyLocationBasedControl";
    private MobileDevice mobileDevice;
    private String nameOfUser;

    class C11291 implements OnPreferenceClickListener {
        C11291() {
        }

        public boolean onPreferenceClick(Preference preference) {
            MobileDeviceDetailsPreferencesFragment.this.startActivityForResult(new Intent(MobileDeviceDetailsPreferencesFragment.this.getActivity(), AppSettingsActivity.class), MobileDeviceDetailsPreferencesActivity.LOCATION_BASED_CONTROL_RESULT);
            return true;
        }
    }

    class C11322 implements OnPreferenceClickListener {
        C11322() {
        }

        public boolean onPreferenceClick(final Preference preference) {
            CharSequence string;
            MobileDeviceDetailsPreferencesFragment.this.setPreferenceState(preference, false);
            Builder builder = new Builder(MobileDeviceDetailsPreferencesFragment.this.getActivity());
            Builder title = builder.setTitle((int) C0676R.string.settings_users_existingUser_mobileDevices_deleteDeviceConfirmation_title);
            if (MobileDeviceDetailsPreferencesFragment.this.isCurrentMobileDevice()) {
                string = MobileDeviceDetailsPreferencesFragment.this.getString(C0676R.string.settings_users_existingUser_mobileDevices_deleteDeviceConfirmation_currentDeviceMessage);
            } else {
                string = MobileDeviceDetailsPreferencesFragment.this.getString(C0676R.string.settings_users_existingUser_mobileDevices_deleteDeviceConfirmation_message, new Object[]{MobileDeviceDetailsPreferencesFragment.this.nameOfUser});
            }
            title.setMessage(string).setPositiveButton((int) C0676R.string.settings_users_existingUser_mobileDevices_deleteDeviceConfirmation_confirmButton, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    MobileDeviceDetailsPreferencesFragment.this.deleteMobileDevice(preference);
                    dialog.dismiss();
                }
            }).setNegativeButton((int) C0676R.string.settings_users_existingUser_mobileDevices_deleteDeviceConfirmation_cancelButton, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    MobileDeviceDetailsPreferencesFragment.this.setPreferenceState(preference, true);
                }
            }).setCancelable(false);
            builder.create().show();
            return true;
        }
    }

    public static MobileDeviceDetailsPreferencesFragment newInstance(MobileDevice mobileDevice, String nameOfUser) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(MobileDeviceDetailsPreferencesActivity.KEY_MOBILE_DEVICE, mobileDevice);
        bundle.putString(MobileDeviceDetailsPreferencesActivity.KEY_NAME_OF_USER, nameOfUser);
        MobileDeviceDetailsPreferencesFragment fragment = new MobileDeviceDetailsPreferencesFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        String string;
        if (getArguments().containsKey(MobileDeviceDetailsPreferencesActivity.KEY_MOBILE_DEVICE) && getArguments().containsKey(MobileDeviceDetailsPreferencesActivity.KEY_NAME_OF_USER)) {
            this.mobileDevice = (MobileDevice) getArguments().getParcelable(MobileDeviceDetailsPreferencesActivity.KEY_MOBILE_DEVICE);
            this.nameOfUser = getArguments().getString(MobileDeviceDetailsPreferencesActivity.KEY_NAME_OF_USER);
        } else {
            getActivity().finish();
        }
        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(getActivity());
        if ((this.mobileDevice.getLocation() != null && this.mobileDevice.getLocation().isStale()) || (this.mobileDevice.getLocation() == null && this.mobileDevice.getSettings().isGeoTrackingEnabled())) {
            Preference staleIconPreference = new Preference(getActivity());
            staleIconPreference.setLayoutResource(C0676R.layout.stale_preference_layout);
            if (VERSION.SDK_INT >= 21) {
                staleIconPreference.setIcon(ResourceFactory.getVectorDrawable(getActivity(), C0676R.drawable.ic_question_mark));
            } else {
                staleIconPreference.setIcon(ResourceFactory.getTintedDrawable(getActivity(), C0676R.drawable.ic_question_mark, C0676R.color.ac_red));
            }
            staleIconPreference.setPersistent(false);
            staleIconPreference.setSelectable(false);
            screen.addPreference(staleIconPreference);
            LinkablePreference staleInfoPreference = new LinkablePreference(getActivity());
            staleInfoPreference.setSummary(getText(C0676R.string.settings_users_existingUser_mobileDevices_locationBasedControl_staleDescription));
            staleInfoPreference.setPersistent(false);
            screen.addPreference(staleInfoPreference);
        }
        screen.addPreference(createPreference(screen, getString(C0676R.string.settings_users_existingUser_mobileDevices_nameLabel), this.mobileDevice.getName()));
        screen.addPreference(createPreference(screen, getString(C0676R.string.settings_users_existingUser_mobileDevices_osLabel), String.format("%s (%s)", new Object[]{this.mobileDevice.getDeviceMetadata().getPlatform(), this.mobileDevice.getDeviceMetadata().getOsVersion()})));
        Preference locationBasedControlPreference = new Preference(getActivity());
        locationBasedControlPreference.setPersistent(false);
        locationBasedControlPreference.setTitle((int) C0676R.string.settings_users_existingUser_mobileDevices_locationBasedControl_locationBasedControlLabel);
        String str = "%s %s";
        Object[] objArr = new Object[2];
        if (this.mobileDevice.getSettings().isGeoTrackingEnabled()) {
            string = getString(C0676R.string.settings_users_existingUser_mobileDevices_locationBasedControl_enabledLabel);
        } else {
            string = getString(C0676R.string.settings_users_existingUser_mobileDevices_locationBasedControl_disabledLabel);
        }
        objArr[0] = string;
        if (isCurrentMobileDevice()) {
            string = "";
        } else {
            Object[] objArr2 = new Object[1];
            objArr2[0] = getString(C0676R.string.settings_users_existingUser_mobileDevices_locationBasedControl_cannotBeChangedLabel, new Object[]{this.mobileDevice.getName()});
            string = String.format("%s", objArr2);
        }
        objArr[1] = string;
        locationBasedControlPreference.setSummary(String.format(str, objArr));
        locationBasedControlPreference.setKey(KEY_LOCATION_BASED_CONTROL);
        locationBasedControlPreference.setOnPreferenceClickListener(new C11291());
        locationBasedControlPreference.setSelectable(isCurrentMobileDevice());
        screen.addPreference(locationBasedControlPreference);
        Preference deleteDevice = new Preference(getActivity());
        deleteDevice.setLayoutResource(C0676R.layout.delete_mobile_device_preference_layout);
        deleteDevice.setTitle((int) C0676R.string.settings_users_existingUser_mobileDevices_deleteButton);
        deleteDevice.setOnPreferenceClickListener(new C11322());
        screen.addPreference(deleteDevice);
        setPreferenceScreen(screen);
    }

    private void updateLocationBasedControPreference(boolean geolocationEnabled) {
        Preference locationBasedControlPreference = findPreference(KEY_LOCATION_BASED_CONTROL);
        if (locationBasedControlPreference != null) {
            locationBasedControlPreference.setSummary(geolocationEnabled ? C0676R.string.settings_users_existingUser_mobileDevices_locationBasedControl_enabledLabel : C0676R.string.settings_users_existingUser_mobileDevices_locationBasedControl_disabledLabel);
        }
    }

    private boolean isCurrentMobileDevice() {
        return UserConfig.getMobileDeviceId() == this.mobileDevice.getId();
    }

    private void setPreferenceState(Preference preference, boolean enabled) {
        preference.setEnabled(enabled);
        preference.setSelectable(enabled);
    }

    private void deleteMobileDevice(final Preference preference) {
        RestServiceGenerator.getTadoRestService().deleteMobileDevice(UserConfig.getHomeId(), this.mobileDevice.getId(), RestServiceGenerator.getCredentialsMap()).enqueue(new RetryCallback<Void>(new SendingErrorAlertPresenter(getActivity())) {
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (MobileDeviceDetailsPreferencesFragment.this.isAdded()) {
                    if (!response.isSuccessful()) {
                        preference.setEnabled(true);
                    } else if (MobileDeviceDetailsPreferencesFragment.this.mobileDevice.getId() == UserConfig.getMobileDeviceId()) {
                        TadoApplication.locationManager.stopTracking();
                        Util.clearUserData();
                        Intent intent = new Intent(MobileDeviceDetailsPreferencesFragment.this.getActivity(), LoginActivity.class);
                        intent.setFlags(268468224);
                        MobileDeviceDetailsPreferencesFragment.this.startActivity(intent);
                    } else {
                        Intent result = new Intent();
                        result.putExtra(PeopleDetailsPreferenceActivityFragment.KEY_MOBILE_DEVICE_ID, MobileDeviceDetailsPreferencesFragment.this.mobileDevice.getId());
                        MobileDeviceDetailsPreferencesFragment.this.getActivity().setResult(-1, result);
                        MobileDeviceDetailsPreferencesFragment.this.getActivity().finish();
                    }
                }
                super.onResponse(call, response);
            }

            public void onFailure(Call<Void> call, Throwable t) {
                if (MobileDeviceDetailsPreferencesFragment.this.isAdded()) {
                    preference.setEnabled(true);
                }
                super.onFailure(call, t);
            }
        });
    }

    private Preference createPreference(PreferenceScreen screen, int title, int summary) {
        return createPreference(screen, getString(title), getString(summary));
    }

    private Preference createPreference(PreferenceScreen screen, CharSequence title, CharSequence summary) {
        Preference preference = new Preference(getActivity());
        if (title != null) {
            preference.setTitle(title);
        }
        preference.setSummary(summary);
        preference.setPersistent(false);
        preference.setSelectable(false);
        return preference;
    }

    private void showErrorMessage(String msg) {
        Snitcher.start().toCrashlytics().log("MobileDeviceDetailsPreferencesFragment", msg, new Object[0]);
        if (getView() != null && isAdded()) {
            Snackbar.make(getView(), (CharSequence) msg, 0).show();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MobileDeviceDetailsPreferencesActivity.LOCATION_BASED_CONTROL_RESULT && resultCode == -1 && data.hasExtra(AppSettingsPreferenceFragment.Companion.getKEY_PREF_PRESENCE_DETECTION())) {
            this.mobileDevice.getSettings().setGeoTrackingEnabled(data.getBooleanExtra(AppSettingsPreferenceFragment.Companion.getKEY_PREF_PRESENCE_DETECTION(), this.mobileDevice.getSettings().isGeoTrackingEnabled()));
            updateLocationBasedControPreference(this.mobileDevice.getSettings().isGeoTrackingEnabled());
        }
    }

    public MobileDevice getMobileDevice() {
        return this.mobileDevice;
    }
}
