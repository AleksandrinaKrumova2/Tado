package com.tado.android.mvp.presenters;

import android.animation.ArgbEvaluator;
import android.support.v4.util.Pair;
import android.text.SpannableStringBuilder;
import android.view.MotionEvent;
import com.squareup.otto.Subscribe;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import com.tado.android.control_panel.model.TimerValue;
import com.tado.android.controllers.CapabilitiesController;
import com.tado.android.controllers.ZoneController;
import com.tado.android.mvp.common.BasePresenter;
import com.tado.android.mvp.views.WeatherView;
import com.tado.android.mvp.views.ZoneView;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.CallForHeatEnum;
import com.tado.android.rest.model.HomeState;
import com.tado.android.rest.model.Link;
import com.tado.android.rest.model.MobileDevicesWrapper;
import com.tado.android.rest.model.OpenWindow;
import com.tado.android.rest.model.OverlayTerminationCondition;
import com.tado.android.rest.model.Zone.TypeEnum;
import com.tado.android.rest.model.ZoneOverlay;
import com.tado.android.rest.model.ZoneState;
import com.tado.android.rest.model.installation.CoolingZoneSetting;
import com.tado.android.rest.model.installation.FanSpeedEnum;
import com.tado.android.rest.model.installation.GenericZoneSetting;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.times.view.model.ModeEnum;
import com.tado.android.utils.ColorFactory;
import com.tado.android.utils.ResourceFactory;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.TimeUtils;
import com.tado.android.utils.UserConfig;
import retrofit2.Call;
import retrofit2.Response;

public class ZonePresenter implements BasePresenter<ZoneView> {
    private static final String TAG = "ZonePresenter";
    private HomeState currentHomeState;
    private ZoneState currentZoneState;
    private boolean endManualControlButtonWasShownUncollapsed = false;
    private float mTemperatureStep = 1.0f;
    int mZoneId;
    ZoneView mZoneView;
    private WeatherPresenter weatherPresenter;

    class C10361 extends TadoCallback<Void> {
        C10361() {
        }

        public void onResponse(Call<Void> call, Response<Void> response) {
            super.onResponse(call, response);
            if (response.isSuccessful()) {
                ZoneController.INSTANCE.callGetCurrentZoneState();
            }
        }

        public void onFailure(Call<Void> call, Throwable t) {
            super.onFailure(call, t);
        }
    }

    public ZonePresenter(WeatherPresenter weatherPresenter) {
        this.weatherPresenter = weatherPresenter;
    }

    public void bindView(ZoneView view) {
        Snitcher.start().log(3, TAG, "%d bindView zoneid %d", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(this.mZoneId));
        TadoApplication.getBus().register(this);
        this.mZoneView = view;
        this.weatherPresenter.bindView((WeatherView) view);
    }

    public void unbindView() {
        Snitcher.start().log(3, TAG, "%d unbindView zoneid %d", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(this.mZoneId));
        TadoApplication.getBus().unregister(this);
        this.weatherPresenter.unbindView(this.mZoneView);
        this.mZoneView = null;
    }

    public void setZoneId(int zoneId) {
        this.mZoneId = zoneId;
    }

    public void loadState() {
        ZoneController.INSTANCE.callGetZoneState(this.mZoneId);
        ZoneController.INSTANCE.callGetHomeState();
        this.currentZoneState = ZoneController.INSTANCE.getCurrentZoneState();
        if (this.currentZoneState != null) {
            updateState(this.currentZoneState);
        }
    }

    public void updateZoneName() {
        Snitcher.start().log(3, TAG, "%d updateZoneName zoneid %d", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(this.mZoneId));
        this.mZoneView.setZoneName(ZoneController.INSTANCE.getZoneName(this.mZoneId));
    }

    public void updateState(ZoneState zoneState) {
        boolean z;
        boolean z2 = true;
        this.currentZoneState = zoneState;
        Snitcher.start().log(3, TAG, "%d updateState zoneid %d", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(this.mZoneId));
        this.mZoneView.updateMobileDevices(ZoneController.INSTANCE.getMobileDevices());
        this.mTemperatureStep = CapabilitiesController.INSTANCE.getModeTemperatureStep(ModeEnum.getModeFromString(zoneState.getSetting().getMode()), TypeEnum.valueOf(zoneState.getSetting().getType()));
        updateBackgroundColors(zoneState);
        this.mZoneView.updateCallForHeatIconVisibility(isCallForHeatOn(zoneState), getCallForHeatResourcesPair(zoneState));
        this.mZoneView.updateZoneStateHotWaterLayoutVisibility(shouldZoneStateShowHotWaterLayout(zoneState));
        this.mZoneView.setZoneStateHotWaterIcon(ResourceFactory.getZoneStateHotWaterIcon(zoneState.getSetting().getPowerBoolean()));
        this.mZoneView.updateZoneStateTemperatureLayoutVisibility(shouldShowZoneStateTemperatureLayout(zoneState));
        setStateTemperatureValues(zoneState);
        this.mZoneView.updateStateIconLayoutVisibility(shouldShowStateIconLayout(zoneState));
        this.mZoneView.setStateIcon(ResourceFactory.getStateIconDrawable(zoneState.getTadoMode(), hasOverlay(zoneState.getOverlay()), zoneState.getOpenWindow() != null));
        this.mZoneView.updateHumidityLayoutVisibility(shouldShowHumidityLayout(zoneState));
        if (shouldShowHumidityLayout(zoneState)) {
            this.mZoneView.setHumidityValue(((int) zoneState.getSensorDataPoints().getHumidity().getPercentage()) + "%");
        }
        this.mZoneView.updateStateNoConnectionLayoutVisibility(shouldShowNoConnectionLayout(zoneState));
        updateModeSettingLayout(zoneState);
        updateEndOverlayButtonState(zoneState);
        this.mZoneView.resetOverlayLayout();
        this.mZoneView.updateOverlayTimerLayoutVisibility(shouldShowOverlayTimerTextView(zoneState));
        this.mZoneView.updateUserLayoutVisibility(!shouldHideUserLayout(zoneState));
        this.mZoneView.updateOverlayTerminationInfoLayoutVisibility(shouldShowOverlayTerminationInfoLayout(zoneState));
        ZoneView zoneView = this.mZoneView;
        if (shouldShowEarlyStartLayout(zoneState) || !shouldCenterOverlayTerminationInfoLayout(zoneState)) {
            z = true;
        } else {
            z = false;
        }
        zoneView.setAdditionalInfoLayoutVisibility(z);
        this.mZoneView.setAdditionalInfoText(getEarlyStartText(zoneState));
        ZoneView zoneView2 = this.mZoneView;
        if (zoneState.getOpenWindow() == null || !zoneState.getLink().isOnline()) {
            z2 = false;
        }
        zoneView2.setIgnoreOpenWindowDetectionButtonVisibility(z2);
        this.mZoneView.setOverlayTerminationInfoTextValue(getOverlayTerminationText(zoneState), shouldCenterOverlayTerminationInfoLayout(zoneState));
        this.mZoneView.setOverlayTerminationInfoIcon(ResourceFactory.getOverlayTerminationIcon(zoneState));
        updateZoneName();
        updateSwitchHomePresenceButton();
        updateUnmaskOpenWindowButton();
    }

    private boolean shouldEnableMainModeLayout(ZoneState zoneState) {
        return zoneState.getOpenWindow() == null;
    }

    private Pair<Integer, Integer> getCallForHeatResourcesPair(ZoneState zoneState) {
        float power = 0.0f;
        if (!(zoneState.getActivityDataPoints() == null || zoneState.getActivityDataPoints().getHeatingPower() == null)) {
            power = zoneState.getActivityDataPoints().getHeatingPower().getPercentage();
        }
        return ResourceFactory.getCallForHeatResourcesPair(CallForHeatEnum.getEnumForPower(power));
    }

    private String getEarlyStartText(ZoneState zoneState) {
        if (zoneState.getPreparation() == null || zoneState.getPreparation().getSetting() == null || zoneState.getPreparation().getSetting().getTemperature() == null || zoneState.getPreparation().getEnd() == null) {
            return "";
        }
        return TadoApplication.getTadoAppContext().getString(C0676R.string.mainScreen_earlyStartIndicationLabel, new Object[]{zoneState.getPreparation().getSetting().getTemperature().getFormattedTemperatureValue(this.mTemperatureStep), TimeUtils.formatTimeToLocale(TimeUtils.getHoursAndMinutesInHomeTimezone(zoneState.getPreparation().getEnd()))});
    }

    private void updateModeSettingLayout(ZoneState state) {
        boolean showMainModeSetting = shouldShowMainModeIconAndText(state);
        this.mZoneView.updateCurrentSettingsMainModeVisibility(showMainModeSetting);
        if (showMainModeSetting) {
            this.mZoneView.setCurrentSettingsMainModeIconId(ResourceFactory.getMainModeIconId(state));
            this.mZoneView.setCurrentSettingsMainModeText(getMainModeText(state));
        }
        SpannableStringBuilder noConnectionBuilder = new SpannableStringBuilder();
        noConnectionBuilder.append(TadoApplication.getTadoAppContext().getText(C0676R.string.mainScreen_noRemoteAccessLabel));
        noConnectionBuilder.append("\n");
        noConnectionBuilder.append(TadoApplication.getTadoAppContext().getText(C0676R.string.mainScreen_noRemoteAccessTroubleShootingLabel));
        this.mZoneView.setNoConnectionText(noConnectionBuilder);
        this.mZoneView.updateCurrentSettingsNoConnectionLayoutVisibility(shouldShowCurrentSettingNoConnectionLayout(state));
        this.mZoneView.updateCoolingModeLayout(state);
    }

    public void updateBackgroundColors(ZoneState zoneState) {
        int backgroundColor = getBackgroundColor(zoneState);
        this.mZoneView.setZoneBackgroundColor(backgroundColor);
        this.mZoneView.setZoneSettingsBackgroundColorAndState(getCurrentSettingsBackgroundColor(backgroundColor), shouldEnableMainModeLayout(zoneState));
        this.mZoneView.setIgnoreOpenWindowDetectionButtonBackgroundColor(getCurrentSettingsBackgroundColor(backgroundColor));
    }

    public void setTemperatureStep(float step) {
        this.mTemperatureStep = step;
    }

    private void setStateTemperatureValues(ZoneState state) {
        if (state.getSensorDataPoints() != null && state.getSensorDataPoints().getInsideTemperature() != null) {
            Pair<Float, Float> insideTemperaturePair = CapabilitiesController.INSTANCE.getInsideTemperaturePair(state.getSensorDataPoints().getInsideTemperature());
            this.mZoneView.setTadoStateTemperatureValueAndPrecision(((Float) insideTemperaturePair.first).floatValue(), ((Float) insideTemperaturePair.second).floatValue());
        }
    }

    private int getBackgroundColor(ZoneState state) {
        GenericZoneSetting setting;
        if (state.getSetting().getPowerBoolean() && state.getSetting().getTemperature() != null) {
            float temperature = state.getSetting().getTemperature().getTemperatureValue();
        }
        if (state.getSetting().getType().equalsIgnoreCase("AIR_CONDITIONING")) {
            setting = new CoolingZoneSetting(state.getSetting().getMode() != null ? com.tado.android.rest.model.installation.ModeEnum.valueOf(state.getSetting().getMode()) : null, state.getSetting().getPowerBoolean(), state.getSetting().getTemperature());
            if (state.getSetting().getFanSpeed() != null) {
                ((CoolingZoneSetting) setting).setFanSpeed(FanSpeedEnum.valueOf(state.getSetting().getFanSpeed()));
            }
            if (state.getSetting().getSwing() != null) {
                ((CoolingZoneSetting) setting).setSwing(state.getSetting().getSwing());
            }
        } else {
            setting = new GenericZoneSetting(GenericZoneSetting.TypeEnum.valueOf(state.getSetting().getType()), state.getSetting().getPowerBoolean(), state.getSetting().getTemperature());
        }
        return ColorFactory.getZoneStateBackgroundColorPair(setting, state, this.mZoneId).darkColor;
    }

    private int getCurrentSettingsBackgroundColor(int baseColor) {
        return ((Integer) new ArgbEvaluator().evaluate(0.2f, Integer.valueOf(baseColor), Integer.valueOf(-1))).intValue();
    }

    private void updateEndOverlayButtonState(ZoneState zoneState) {
        boolean hasOverlay;
        boolean isZoneOffline = zoneState.getLink().getState().equalsIgnoreCase(Link.OFFLINE);
        if (zoneState.getOverlay() != null) {
            hasOverlay = true;
        } else {
            hasOverlay = false;
        }
        Snitcher.start().log(3, TAG, "%d updateEndOverlayButtonState isZoneOffline %b hasActiveOverlay %b", Integer.valueOf(System.identityHashCode(this)), Boolean.valueOf(isZoneOffline), Boolean.valueOf(hasOverlay));
        if (isZoneOffline) {
            this.mZoneView.updateEndOverlayButtonStateForOfflineZone();
        } else if (hasOverlay) {
            this.mZoneView.updateEndOverlayButtonStateWithActiveOverlay(this.endManualControlButtonWasShownUncollapsed);
            this.endManualControlButtonWasShownUncollapsed = true;
        } else {
            this.mZoneView.updateEndOverlayButtonStateWithoutActiveOverlay();
        }
    }

    private boolean shouldShowStateIconLayout(ZoneState state) {
        return state.getPreparation() == null;
    }

    private boolean hasOverlay(ZoneOverlay overlay) {
        return (overlay == null || overlay.getTermination() == null || overlay.getTermination().getType().isEmpty()) ? false : true;
    }

    private boolean shouldShowEarlyStartLayout(ZoneState state) {
        return state.getLink().isOnline() && state.getPreparation() != null && state.getPreparation().getSetting().getType() == GenericZoneSetting.TypeEnum.HEATING;
    }

    private boolean shouldShowNoConnectionLayout(ZoneState state) {
        return !state.getLink().isOnline();
    }

    private boolean shouldShowHumidityLayout(ZoneState state) {
        return (state.getSensorDataPoints() == null || state.getSensorDataPoints().getHumidity() == null || state.getSetting().getType().equalsIgnoreCase("HOT_WATER")) ? false : true;
    }

    private boolean isCallForHeatOn(ZoneState state) {
        return state.getActivityDataPoints() != null && state.getActivityDataPoints().getHeatingPower() != null && state.getActivityDataPoints().getHeatingPower().getPercentage() >= 0.0f && state.getLink().isOnline();
    }

    private boolean shouldZoneStateShowHotWaterLayout(ZoneState state) {
        return state.getSetting().getType().equalsIgnoreCase("HOT_WATER") && state.getLink().isOnline();
    }

    private boolean shouldShowZoneStateTemperatureLayout(ZoneState state) {
        return !state.getSetting().getType().equalsIgnoreCase("HOT_WATER") && state.getLink().isOnline();
    }

    private boolean shouldShowMainModeIconAndText(ZoneState state) {
        return state.getLink().isOnline();
    }

    private String getMainModeText(ZoneState state) {
        if (state.getSetting().getType().equalsIgnoreCase("AIR_CONDITIONING")) {
            return getAcMode(state);
        }
        if (state.getSetting().getType().equalsIgnoreCase("HOT_WATER")) {
            return getHotWaterMainModeText(state);
        }
        return getHeatingMainModeText(state);
    }

    private String getHeatingMainModeText(ZoneState state) {
        if (state.getSetting().getPowerBoolean()) {
            return state.getSetting().getTemperature().getFormattedTemperatureValue(this.mTemperatureStep);
        }
        return TadoApplication.getTadoAppContext().getString(C0676R.string.components_heatingSettingDisplay_offLabel);
    }

    private String getHotWaterMainModeText(ZoneState state) {
        if (CapabilitiesController.INSTANCE.canSetCurrentZoneHotWaterTemperature() && state.getSetting().getPowerBoolean() && state.getSetting().getTemperature() != null) {
            return state.getSetting().getTemperature().getFormattedTemperatureValue(this.mTemperatureStep);
        }
        if (state.getSetting().getPowerBoolean()) {
            return this.mZoneView.getString(C0676R.string.components_hotWaterSettingDisplay_onLabel, new Object[0]);
        }
        return this.mZoneView.getString(C0676R.string.components_hotWaterSettingDisplay_offLabel, new Object[0]);
    }

    private String getAcMode(ZoneState state) {
        return state.getSetting().getPowerBoolean() ? state.getSetting().getMode() : this.mZoneView.getString(C0676R.string.components_acSettingDisplay_offLabel, new Object[0]);
    }

    private boolean shouldShowCurrentSettingNoConnectionLayout(ZoneState state) {
        return !state.getLink().isOnline();
    }

    private boolean shouldShowOverlayTimerTextView(ZoneState state) {
        return ((state.getOverlay() == null || state.getOverlay().getTermination() == null || state.getOverlay().getTermination().getType() == null || !state.getOverlay().getTermination().getType().equalsIgnoreCase(OverlayTerminationCondition.TIMER)) && state.getOpenWindow() == null) ? false : true;
    }

    private boolean shouldShowOverlayTerminationInfoLayout(ZoneState state) {
        return state.getOverlay() != null || (state.getOverlay() == null && state.getGeolocationOverride().booleanValue());
    }

    private boolean shouldCenterOverlayTerminationInfoLayout(ZoneState state) {
        if ((state.getOverlay() == null || !state.getOverlay().getTermination().isManualOverlayTermination()) && (!state.getGeolocationOverride().booleanValue() || state.getOpenWindow() != null || (state.getOverlay() != null && state.getOverlay().getTermination().isTimerOverlayTermination()))) {
            return false;
        }
        return true;
    }

    private boolean shouldHideUserLayout(ZoneState state) {
        if ((state.getOverlay() == null || state.getOverlay().getTermination() == null || (!state.getOverlay().getTermination().isTimerOverlayTermination() && !state.getOverlay().getTermination().isManualOverlayTermination())) && !state.getGeolocationOverride().booleanValue() && state.getOpenWindow() == null) {
            return false;
        }
        return true;
    }

    private String getOverlayTerminationText(ZoneState state) {
        OpenWindow openWindow = state.getOpenWindow();
        if (openWindow != null) {
            String detectedTimeString = TimeUtils.formatTimeToLocale(TimeUtils.getHoursAndMinutesInHomeTimezone(openWindow.getDetectedTime()));
            return this.mZoneView.getString(C0676R.string.mainScreen_openWindowDetectedMessage, detectedTimeString);
        } else if (state.getOverlay() == null && state.getGeolocationOverride().booleanValue()) {
            if (state.getGeolocationOverrideDisableTime() == null) {
                return this.mZoneView.getString(C0676R.string.mainScreen_activeIndefinitelyLabel, new Object[0]);
            }
            return this.mZoneView.getString(C0676R.string.mainScreen_activeUntilTimeLabel, TimeUtils.formatTimeToLocale(TimeUtils.getHoursAndMinutesInHomeTimezone(state.getGeolocationOverrideDisableTime())));
        } else if (state.getOverlay() == null || state.getOverlay().getTermination() == null) {
            return null;
        } else {
            if (state.getOverlay().getTermination().isTadoModeOverlayTermination()) {
                if (state.getGeolocationOverride().booleanValue()) {
                    if (state.getOverlay().getTermination().getProjectedExpiry() == null) {
                        return this.mZoneView.getString(C0676R.string.mainScreen_activeIndefinitelyLabel, new Object[0]);
                    }
                    return this.mZoneView.getString(C0676R.string.mainScreen_activeUntilTimeLabel, TimeUtils.formatTimeToLocale(TimeUtils.getHoursAndMinutesInHomeTimezone(state.getOverlay().getTermination().getProjectedExpiry())));
                } else if (state.isTadoModeAway()) {
                    return this.mZoneView.getString(C0676R.string.mainScreen_activeUntilHomeLabel, new Object[0]);
                } else {
                    if (state.getOverlay().getTermination().getProjectedExpiry() == null) {
                        return this.mZoneView.getString(C0676R.string.mainScreen_activeWhileHomeLabel, new Object[0]);
                    }
                    return this.mZoneView.getString(C0676R.string.mainScreen_activeUntilTimeWhileHomeLabel, TimeUtils.getHoursAndMinutesInHomeTimezone(state.getOverlay().getTermination().getProjectedExpiry()));
                }
            } else if (state.getOverlay().getTermination().isManualOverlayTermination()) {
                return this.mZoneView.getString(C0676R.string.mainScreen_activeUntilManuallyEndedLabel, new Object[0]);
            } else {
                return this.mZoneView.getString(C0676R.string.mainScreen_activeUntilTimerEndsLabel, new Object[0]);
            }
        }
    }

    public void ignoreOpenWindowDetection() {
        RestServiceGenerator.getTadoRestService().ignoreOpenWindowDetection(UserConfig.getHomeId(), UserConfig.getCurrentZone().intValue(), RestServiceGenerator.getCredentialsMap()).enqueue(new C10361());
    }

    @Subscribe
    public void getMotionEvent(MotionEvent event) {
        this.mZoneView.dispatchTouchEventToUserRadar(event);
    }

    @Subscribe
    public void getZoneState(ZoneState zoneState) {
        Snitcher.start().log(3, TAG, "%d updatezonestate %d zoneid %d", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(zoneState.getId()), Integer.valueOf(this.mZoneId));
        if (this.mZoneId == zoneState.getId()) {
            updateState(zoneState);
        }
    }

    @Subscribe
    public void getOverlayTimerSecondsLeft(TimerValue timerValue) {
        this.mZoneView.setOverlayTimerTextValue(TimeUtils.getTimeFromSeconds(timerValue.getTimerSeconds()));
        if (timerValue.getTimerSeconds() == 0) {
            ZoneController.INSTANCE.callGetCurrentZoneState();
        }
    }

    @Subscribe
    public void getMobileDevice(MobileDevicesWrapper mobileDevicesWrapper) {
        this.mZoneView.updateMobileDevices(mobileDevicesWrapper.getMobileDevices());
    }

    @Subscribe
    public void getHomeState(HomeState homeState) {
        if (!(this.currentHomeState == null || this.currentHomeState.getShowHomePresenceSwitchButton() == homeState.getShowHomePresenceSwitchButton())) {
            ZoneController.INSTANCE.callGetMobileDevice();
        }
        this.currentHomeState = homeState;
        updateSwitchHomePresenceButton();
        updateUnmaskOpenWindowButton();
    }

    private void updateSwitchHomePresenceButton() {
        if (this.currentZoneState != null && this.currentHomeState != null) {
            boolean showHomePresenceSwitch = shouldShowHomePresenceButton(this.currentHomeState) && this.currentZoneState.getOverlay() == null && this.currentZoneState.getOpenWindow() == null;
            this.mZoneView.setSwitchHomePresenceButton(showHomePresenceSwitch, this.currentHomeState.getPresence());
        }
    }

    private void updateUnmaskOpenWindowButton() {
        if (this.currentZoneState != null && this.currentHomeState != null) {
            boolean showUnmaskButton = shouldShowUnmasOpenWindowButton(this.currentZoneState) && !shouldShowHomePresenceButton(this.currentHomeState) && this.currentZoneState.getOverlay() == null;
            this.mZoneView.setUnmaskOpenWindowButton(showUnmaskButton);
        }
    }

    private boolean shouldShowHomePresenceButton(HomeState homeState) {
        return homeState.getShowHomePresenceSwitchButton() != null && homeState.getShowHomePresenceSwitchButton().booleanValue();
    }

    private boolean shouldShowUnmasOpenWindowButton(ZoneState zoneState) {
        return zoneState.getOpenWindowMasked() != null && zoneState.getOpenWindowMasked().booleanValue();
    }
}
