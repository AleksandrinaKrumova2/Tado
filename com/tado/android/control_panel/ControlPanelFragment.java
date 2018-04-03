package com.tado.android.control_panel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.crashlytics.android.Crashlytics;
import com.squareup.otto.Subscribe;
import com.tado.C0676R;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.app.TadoApplication;
import com.tado.android.control_panel.model.TimerValue;
import com.tado.android.controllers.CapabilitiesController;
import com.tado.android.controllers.ZoneController;
import com.tado.android.rest.callback.presenters.SendingErrorAlertPresenter;
import com.tado.android.rest.model.HomeInfo.LicenseEnum;
import com.tado.android.rest.model.OverlayTerminationCondition;
import com.tado.android.rest.model.ZoneOverlay;
import com.tado.android.rest.model.ZoneSetting;
import com.tado.android.rest.model.ZoneState;
import com.tado.android.rest.model.installation.GenericZoneSetting;
import com.tado.android.times.view.model.ModeEnum;
import com.tado.android.utils.ResourceFactory;
import com.tado.android.utils.TimeUtils;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.ViewEnabler;
import com.tado.android.views.SettingModelAdapter;
import com.tado.android.views.TadoZoneSettingViewConfiguration;
import com.tado.android.views.TadoZoneSettingsView;
import com.tado.android.views.TadoZoneSettingsView.OnZoneSettingChangedListener;

public class ControlPanelFragment extends Fragment {
    private static final String CURRENT_ZONE_STATE = "currentZoneState";
    @BindView(2131296455)
    View mControlPanelButtons;
    private String mCurrentMode;
    private ZoneSetting mCurrentSetting;
    private ZoneState mCurrentZoneState;
    private OnControlPanelFragmentInteractionListener mListener;
    @BindView(2131296468)
    TextView mOverlayTerminationView;
    private ZoneState mSavedState;
    @BindView(2131296504)
    TadoZoneSettingsView mTadoZoneSettingsView;
    @BindView(2131296478)
    View mTerminationView;
    private Unbinder unbinder;

    class C07391 implements OnZoneSettingChangedListener {
        C07391() {
        }

        public void onZoneSettingChanged(ZoneSetting zoneSetting) {
            ControlPanelFragment.this.mCurrentSetting = zoneSetting;
            ControlPanelFragment.this.mCurrentMode = zoneSetting.getMode();
            ControlPanelFragment.this.mCurrentZoneState.setSetting(ControlPanelFragment.this.mCurrentSetting);
            if (ControlPanelFragment.this.mCurrentZoneState.getOverlay() != null) {
                ControlPanelFragment.this.mCurrentZoneState.getOverlay().setSetting(ControlPanelFragment.this.mCurrentSetting);
            }
        }
    }

    class C07402 implements OnClickListener {
        C07402() {
        }

        public void onClick(View v) {
            ControlPanelFragment.this.onTerminationClick(v);
        }
    }

    public interface OnControlPanelFragmentInteractionListener {
        void onCloseControlPanel();

        void onOverlayTerminationClick();
    }

    public static ControlPanelFragment newInstance(OnControlPanelFragmentInteractionListener listener) {
        ControlPanelFragment fragment = new ControlPanelFragment();
        fragment.setArguments(new Bundle());
        registerCallback(listener, fragment);
        return fragment;
    }

    private static void registerCallback(OnControlPanelFragmentInteractionListener listener, ControlPanelFragment fragment) {
        if (listener != null) {
            fragment.mListener = listener;
        } else {
            Crashlytics.log("Presenter must implement OnControlPanelFragmentInteractionListener.");
            throw new ClassCastException("Presenter must implement OnControlPanelFragmentInteractionListener.");
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0676R.layout.fragment_cooling_control_panel_main_layout, container, false);
        this.unbinder = ButterKnife.bind((Object) this, view);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey(CURRENT_ZONE_STATE)) {
            this.mSavedState = (ZoneState) savedInstanceState.getSerializable(CURRENT_ZONE_STATE);
        }
    }

    public void onResume() {
        super.onResume();
        TadoApplication.getBus().register(this);
        AnalyticsHelper.trackPageView(getActivity(), Screen.MANUAL_CONTROL);
        Crashlytics.log("ControlPanelFragment.onResume() mControlPanelButtons is " + (this.mControlPanelButtons != null ? "not null (ok)" : "null (very bad)"));
        this.mCurrentZoneState = ControlPanelController.INSTANCE.getCurrentZoneState();
        if (this.mCurrentZoneState == null) {
            Crashlytics.log("Current zone state is null, saved state is " + (this.mSavedState != null ? "not null (ok)" : "null (bad)"));
            this.mCurrentZoneState = this.mSavedState;
        }
        ZoneOverlay overlay = ControlPanelController.INSTANCE.getOverlay();
        if (overlay == null || overlay.getSetting() == null) {
            Crashlytics.log("overlay is null, current zone state is " + (this.mCurrentZoneState != null ? "not null (ok)" : "null (very bad)"));
            this.mCurrentSetting = this.mCurrentZoneState.getSetting();
        } else {
            Crashlytics.log("overlay.getSetting() is " + (overlay.getSetting() != null ? "not null (ok)" : "null (very bad)"));
            this.mCurrentSetting = overlay.getSetting().clone();
        }
        try {
            this.mCurrentMode = this.mCurrentSetting.getMode();
            ZoneOverlay currentOverlay = this.mCurrentZoneState.getOverlay();
            if (currentOverlay == null) {
                Crashlytics.log("mCurrentZoneState.getOverlay is null, setting default overlay");
                currentOverlay = ZoneController.INSTANCE.getDefaultOverlay();
                this.mCurrentZoneState.setOverlay(currentOverlay);
            }
            if (UserConfig.getLicense() == LicenseEnum.NON_PREMIUM && currentOverlay.getTermination().getType().equalsIgnoreCase(OverlayTerminationCondition.TADO_MODE)) {
                currentOverlay.setTermination(new OverlayTerminationCondition("MANUAL"));
                this.mCurrentZoneState.setOverlay(currentOverlay);
            }
            String conditionText = currentOverlay.getTermination().getTypeValue();
            if (currentOverlay.getTermination().getType().equalsIgnoreCase(OverlayTerminationCondition.TIMER)) {
                if (currentOverlay.getTermination().getDurationInSeconds() == null) {
                    currentOverlay.getTermination().setDurationInSeconds(Integer.valueOf(60));
                }
                conditionText = currentOverlay.getTermination().getTimerTypeValue(TimeUtils.getTimeFromSeconds(currentOverlay.getTermination().getDurationInSeconds().intValue()));
            }
            this.mOverlayTerminationView.setText(conditionText);
            setOverlayTerminationIcon();
            initListeners();
            TadoZoneSettingViewConfiguration viewConfiguration = CapabilitiesController.INSTANCE.getZoneSettingViewConfiguration();
            initDefaultsForModes(viewConfiguration);
            this.mTadoZoneSettingsView.setMinimumHeight(CapabilitiesController.INSTANCE.getControlsHeight());
            this.mTadoZoneSettingsView.initViewModel(viewConfiguration, this.mCurrentSetting);
            this.mTadoZoneSettingsView.initStateMap(ControlPanelController.INSTANCE.getStateMap());
            this.mTadoZoneSettingsView.setOnZoneSettingChangedListener(new C07391());
        } catch (NullPointerException e) {
            Crashlytics.log("mCurrentSetting is null so aborting ControlPanelFragment.onResume()");
            Crashlytics.logException(e);
            this.mListener.onCloseControlPanel();
        }
    }

    private void initDefaultsForModes(TadoZoneSettingViewConfiguration viewConfiguration) {
        for (ModeEnum modeEnum : viewConfiguration.getSupportedModes()) {
            ZoneSetting zoneSetting = new ZoneSetting();
            ControlPanelController.INSTANCE.getSavedModeState(modeEnum, zoneSetting);
            zoneSetting.setMode(modeEnum.name());
            GenericZoneSetting genericZoneSetting = SettingModelAdapter.getGenericZoneSetting(ModeEnum.valueOf(zoneSetting.getMode()));
            SettingModelAdapter.copyToGenericZoneSetting(genericZoneSetting, zoneSetting);
            viewConfiguration.setDefaultModeSetting(modeEnum, genericZoneSetting);
        }
    }

    public void onPause() {
        TadoApplication.getBus().unregister(this);
        super.onPause();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CURRENT_ZONE_STATE, this.mCurrentZoneState);
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }

    private void initListeners() {
        this.mTerminationView.setOnClickListener(new C07402());
    }

    public void onTerminationClick(View view) {
        if (this.mListener != null) {
            this.mListener.onOverlayTerminationClick();
        }
    }

    private void setOverlayTerminationIcon() {
        this.mOverlayTerminationView.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(ResourceFactory.getOverlayTerminationIconForControlPanel(this.mCurrentZoneState)), null, null, null);
    }

    public void onTimerChanged() {
        ZoneOverlay activeOverlay = ControlPanelController.INSTANCE.getOverlay();
        if (this.mCurrentZoneState.getOverlay() != null && this.mCurrentZoneState.getOverlay().getTermination() != null && activeOverlay != null && activeOverlay.getTermination() != null) {
            this.mCurrentZoneState.getOverlay().getTermination().setDurationInSeconds(activeOverlay.getTermination().getDurationInSeconds());
        }
    }

    public void onApply(ViewEnabler viewEnabler) {
        this.mCurrentZoneState.setSetting(this.mCurrentSetting);
        if (this.mCurrentZoneState.getOverlay() != null) {
            this.mCurrentZoneState.getOverlay().setSetting(this.mCurrentSetting);
        }
        ZoneController.INSTANCE.callPostOverlay(this.mCurrentSetting, this.mCurrentZoneState.getOverlay().getTermination(), new SendingErrorAlertPresenter(getActivity()), viewEnabler);
        if (isAdded()) {
            AnalyticsHelper.trackEvent(getActivity(), Screen.MANUAL_CONTROL, "Start", this.mCurrentZoneState.getOverlay().getTermination().getType());
        }
    }

    @Subscribe
    public void getOverlayTimerSecondsLeft(TimerValue timerValue) {
        if (!ZoneController.INSTANCE.isTimerSet() && this.mCurrentZoneState.getOverlay() != null && this.mCurrentZoneState.getOverlay().getTermination().getType().equalsIgnoreCase(OverlayTerminationCondition.TIMER)) {
            if (timerValue.getTimerSeconds() == 0) {
                Integer defaultTerminationDuration = ControlPanelController.INSTANCE.getDefaultZoneOverlay().getTermination().getDurationInSeconds();
                timerValue.setTimerSeconds(defaultTerminationDuration != null ? defaultTerminationDuration.intValue() : 0);
                ControlPanelController.INSTANCE.getCurrentZoneState().getOverlay().getTermination().setDurationInSeconds(Integer.valueOf(timerValue.getTimerSeconds()));
            }
            this.mCurrentZoneState.getOverlay().getTermination().setDurationInSeconds(Integer.valueOf(timerValue.getTimerSeconds()));
            this.mOverlayTerminationView.setText(this.mCurrentZoneState.getOverlay().getTermination().getTimerTypeValue(TimeUtils.getTimeFromSeconds(timerValue.getTimerSeconds())));
        }
    }
}
