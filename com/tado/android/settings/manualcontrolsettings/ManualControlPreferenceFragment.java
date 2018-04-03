package com.tado.android.settings.manualcontrolsettings;

import android.content.Intent;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import com.tado.C0676R;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.controllers.ZoneController;
import com.tado.android.menu.ZoneItem;
import com.tado.android.premium.AutomaticChangeUpsellingActivity;
import com.tado.android.rest.callback.presenters.GeneralErrorSnackbarPresenter;
import com.tado.android.rest.callback.presenters.SendingErrorAlertPresenter;
import com.tado.android.rest.model.HomeInfo.LicenseEnum;
import com.tado.android.rest.model.OverlayTerminationCondition;
import com.tado.android.rest.model.OverlayTerminationConditionResponse;
import com.tado.android.settings.interfaces.GenericCallbackListener;
import com.tado.android.settings.zonesettings.ZonePreferenceActivity;
import com.tado.android.settings.zonesettings.ZonePreferenceFragment;
import com.tado.android.utils.TimeUtils;
import com.tado.android.utils.UserConfig;
import java.util.Locale;

public class ManualControlPreferenceFragment extends PreferenceFragment {
    private OverlayTerminationConditionAdapter adapter = new OverlayTerminationConditionAdapter(new OverlayTerminationCondition[]{new OverlayTerminationCondition(OverlayTerminationCondition.TADO_MODE), new OverlayTerminationCondition(OverlayTerminationCondition.TIMER), new OverlayTerminationCondition("MANUAL")});
    private GenericCallbackListener<OverlayTerminationConditionResponse> listener = new C11263();
    private OverlayTerminationCondition overlayTerminationCondition;
    private PreferenceScreen screen;
    private ZoneItem zoneItem;

    class C11241 implements OnPreferenceChangeListener {
        C11241() {
        }

        public boolean onPreferenceChange(Preference preference, Object newValue) {
            if (UserConfig.getLicense() != LicenseEnum.NON_PREMIUM || !newValue.toString().equalsIgnoreCase(OverlayTerminationCondition.TADO_MODE)) {
                OverlayTerminationCondition newCondition = new OverlayTerminationCondition(newValue.toString());
                newCondition.setDurationInSeconds(ManualControlPreferenceFragment.this.overlayTerminationCondition.getDurationInSeconds());
                preference.setSummary(newCondition.getTypeValue());
                preference.setEnabled(false);
                ManualControlPreferenceFragment.this.updateOverlayTerminationCondition(newCondition);
                return true;
            } else if (ManualControlPreferenceFragment.this.getContext() == null) {
                return false;
            } else {
                Intent intent = new Intent(ManualControlPreferenceFragment.this.getActivity(), AutomaticChangeUpsellingActivity.class);
                intent.addFlags(536870912);
                ManualControlPreferenceFragment.this.getContext().startActivity(intent);
                return false;
            }
        }
    }

    class C11252 implements OnPreferenceChangeListener {
        C11252() {
        }

        public boolean onPreferenceChange(Preference timePreference, Object newValue) {
            OverlayTerminationCondition newCondition = new OverlayTerminationCondition(OverlayTerminationCondition.TIMER);
            newCondition.setDurationInSeconds((Integer) newValue);
            timePreference.setEnabled(false);
            ManualControlPreferenceFragment.this.updateOverlayTerminationCondition(newCondition);
            return true;
        }
    }

    class C11263 implements GenericCallbackListener<OverlayTerminationConditionResponse> {
        C11263() {
        }

        public void onSuccess(OverlayTerminationConditionResponse body) {
            if (ManualControlPreferenceFragment.this.isAdded()) {
                ManualControlPreferenceFragment.this.setLocalOverlayTerminationCondition(body.getOverlayTerminationCondition());
            }
        }

        public void onFailure() {
            ManualControlPreferenceFragment.this.restore();
        }
    }

    public static ManualControlPreferenceFragment newInstance(int zoneId, OverlayTerminationCondition overlayTerminationCondition) {
        Bundle args = new Bundle(2);
        args.putInt(ZonePreferenceActivity.KEY_ZONE_ID, zoneId);
        args.putSerializable(ZonePreferenceFragment.KEY_TERMINATION_CONDITION, overlayTerminationCondition);
        ManualControlPreferenceFragment fragment = new ManualControlPreferenceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.zoneItem = ZoneController.INSTANCE.getZoneItemById(getArguments().getInt(ZonePreferenceActivity.KEY_ZONE_ID, -1));
        this.overlayTerminationCondition = (OverlayTerminationCondition) getArguments().getSerializable(ZonePreferenceFragment.KEY_TERMINATION_CONDITION);
        if (this.zoneItem == null || this.overlayTerminationCondition == null) {
            getActivity().finish();
        }
        if (this.overlayTerminationCondition.getDurationInSeconds() == null || this.overlayTerminationCondition.getDurationInSeconds().intValue() == 0) {
            this.overlayTerminationCondition.setDurationInSeconds(Integer.valueOf(getPreferenceManager().getSharedPreferences().getInt(getTimePreferenceString(), 3600)));
        }
        this.screen = getPreferenceManager().createPreferenceScreen(getActivity());
        this.screen.addPreference(createManualControlPreference());
        Preference timerPreference = createTimerPreference();
        if (this.overlayTerminationCondition.isOverlayTerminationTimer()) {
            this.screen.addPreference(timerPreference);
        }
        this.screen.addPreference(createDescriptionPrefrence());
        setPreferenceScreen(this.screen);
        getOverlayTerminationCodition();
    }

    public void onResume() {
        super.onResume();
        AnalyticsHelper.trackPageView(getActivity(), Screen.MANUAL_CONTROL_SETTINGS);
    }

    private Preference createManualControlPreference() {
        ListPreference listPreference = new ListPreference(getActivity());
        listPreference.setKey(String.format(Locale.getDefault(), ManualControlSettings.PREFERENCE_OVERLAY_TERMINATION_CONDITION, new Object[]{Integer.valueOf(UserConfig.getHomeId()), Integer.valueOf(this.zoneItem.getZoneId())}));
        listPreference.setTitle(C0676R.string.settings_zoneSettings_terminationCondition_title);
        listPreference.setPersistent(false);
        listPreference.setSummary(this.overlayTerminationCondition.getTypeValue());
        listPreference.setValue(this.overlayTerminationCondition.getType());
        listPreference.setEntries(this.adapter.toDescriptionsCharSequence());
        listPreference.setEntryValues(this.adapter.toValuesCharSequence());
        listPreference.setOnPreferenceChangeListener(new C11241());
        return listPreference;
    }

    private Preference createTimerPreference() {
        TimerPreference timePreference = new TimerPreference(getActivity());
        timePreference.setEnabled(this.overlayTerminationCondition.isOverlayTerminationTimer());
        timePreference.setPersistent(true);
        timePreference.setTitle(C0676R.string.components_terminationConditionSelector_timerLabel);
        timePreference.setKey(String.format(Locale.getDefault(), ManualControlSettings.PREFERENCE_TIMER_TERMINATION_CONDITION, new Object[]{Integer.valueOf(UserConfig.getHomeId()), Integer.valueOf(this.zoneItem.getZoneId())}));
        timePreference.setSummary(TimeUtils.getTimeFromSeconds(this.overlayTerminationCondition.getDurationInSeconds().intValue()));
        timePreference.setDefaultValue(this.overlayTerminationCondition.getDurationInSeconds());
        timePreference.setOnPreferenceChangeListener(new C11252());
        return timePreference;
    }

    private Preference createDescriptionPrefrence() {
        Preference descriptionPreference = new Preference(getActivity());
        descriptionPreference.setPersistent(false);
        descriptionPreference.setKey("description");
        descriptionPreference.setLayoutResource(C0676R.layout.preferences_description);
        descriptionPreference.setSummary(getDescriptionString(this.overlayTerminationCondition.getType()));
        return descriptionPreference;
    }

    private void setLocalOverlayTerminationCondition(OverlayTerminationCondition overlayTerminationCondition) {
        this.overlayTerminationCondition.setType(overlayTerminationCondition.getType());
        Preference descriptionPreference = findPreference("description");
        Preference timePreference = getTimerPreference();
        if (overlayTerminationCondition.isOverlayTerminationTimer()) {
            this.overlayTerminationCondition.setDurationInSeconds(overlayTerminationCondition.getDurationInSeconds());
            if (timePreference != null) {
                timePreference.setSummary(TimeUtils.getTimeFromSeconds(overlayTerminationCondition.getDurationInSeconds().intValue()));
                timePreference.setEnabled(true);
            } else {
                this.screen.removePreference(descriptionPreference);
                this.screen.addPreference(createTimerPreference());
                descriptionPreference = createDescriptionPrefrence();
                this.screen.addPreference(descriptionPreference);
            }
        } else if (timePreference != null) {
            this.screen.removePreference(timePreference);
        }
        descriptionPreference.setTitle(getDescriptionString(this.overlayTerminationCondition.getType()));
        ListPreference preference = (ListPreference) findPreference(String.format(Locale.getDefault(), ManualControlSettings.PREFERENCE_OVERLAY_TERMINATION_CONDITION, new Object[]{Integer.valueOf(UserConfig.getHomeId()), Integer.valueOf(this.zoneItem.getZoneId())}));
        preference.setValue(this.overlayTerminationCondition.getType());
        preference.setValueIndex(this.adapter.indexOf(this.overlayTerminationCondition));
        preference.setSummary(this.overlayTerminationCondition.getTypeValue());
        preference.setEnabled(true);
    }

    private void restore() {
        setLocalOverlayTerminationCondition(this.overlayTerminationCondition);
    }

    private int getDescriptionString(String type) {
        if (type.equals(OverlayTerminationCondition.TIMER)) {
            return C0676R.string.settings_zoneSettings_terminationCondition_mode_timerDescriptionLabel;
        }
        if (type.equals("MANUAL")) {
            return C0676R.string.settings_zoneSettings_terminationCondition_mode_manualDescriptionLabel;
        }
        return C0676R.string.settings_zoneSettings_terminationCondition_mode_tadoModeDescriptionLabel;
    }

    private void getOverlayTerminationCodition() {
        ZoneController.INSTANCE.getOverlayDefaultPreferences(this.zoneItem.getZoneId(), this.listener, new GeneralErrorSnackbarPresenter(getView()), null);
    }

    private void updateOverlayTerminationCondition(OverlayTerminationCondition overlayTerminationCondition) {
        ZoneController.INSTANCE.setOverlayDefaultPreferences(this.zoneItem.getZoneId(), overlayTerminationCondition.getType(), overlayTerminationCondition.getDurationInSeconds(), this.listener, new SendingErrorAlertPresenter(getActivity()));
    }

    private TimerPreference getTimerPreference() {
        return (TimerPreference) findPreference(getTimePreferenceString());
    }

    private String getTimePreferenceString() {
        return String.format(Locale.getDefault(), ManualControlSettings.PREFERENCE_TIMER_TERMINATION_CONDITION, new Object[]{Integer.valueOf(UserConfig.getHomeId()), Integer.valueOf(this.zoneItem.getZoneId())});
    }
}
