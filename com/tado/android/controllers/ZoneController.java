package com.tado.android.controllers;

import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Pair;
import com.crashlytics.android.Crashlytics;
import com.google.gson.GsonBuilder;
import com.tado.android.app.TadoApplication;
import com.tado.android.control_panel.ControlPanelController;
import com.tado.android.control_panel.OverlaySettingSeriliazer;
import com.tado.android.control_panel.model.TimerValue;
import com.tado.android.entities.ZoneOrderInput;
import com.tado.android.menu.DrawerItem;
import com.tado.android.menu.ZoneItem;
import com.tado.android.mvp.model.TadoModeEnum;
import com.tado.android.rest.callback.RetryCallback;
import com.tado.android.rest.callback.RetryListener;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.callback.presenters.PresenterDelegate;
import com.tado.android.rest.model.DazzleMode;
import com.tado.android.rest.model.HomePresenceWrapper;
import com.tado.android.rest.model.HomeState;
import com.tado.android.rest.model.MobileDevice;
import com.tado.android.rest.model.MobileDevicesWrapper;
import com.tado.android.rest.model.OverlayTerminationCondition;
import com.tado.android.rest.model.OverlayTerminationConditionResponse;
import com.tado.android.rest.model.Temperature;
import com.tado.android.rest.model.Zone;
import com.tado.android.rest.model.ZoneCapabilities;
import com.tado.android.rest.model.ZoneListWrapper;
import com.tado.android.rest.model.ZoneName;
import com.tado.android.rest.model.ZoneOverlay;
import com.tado.android.rest.model.ZoneSetting;
import com.tado.android.rest.model.ZoneState;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.settings.interfaces.GenericCallbackListener;
import com.tado.android.settings.model.TemperatureSettings;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.ViewEnabler;
import com.tado.android.views.loadingindicator.LoadingIndicator;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;
import retrofit2.Call;
import retrofit2.Response;

public enum ZoneController {
    INSTANCE;
    
    private boolean isOverlayTimerRunning;
    private WeakReference<LoadingIndicator> loadingIndicator;
    private CountDownTimer mCountDownOverlayTimer;
    private CountDownTimer mCountDownTimer;
    private ZoneState mCurrentZoneState;
    private String mTimerOverlayExpiryTimestamp;
    private MobileDevicesWrapper mobileDevicesWrapper;
    private int secondsLeft;
    private boolean setTimer;
    private boolean zoneHasActiveOverlay;
    private ZoneListWrapper zoneListWrapper;

    class C07561 extends TadoCallback<List<MobileDevice>> {
        C07561() {
        }

        public void onResponse(Call<List<MobileDevice>> call, Response<List<MobileDevice>> response) {
            super.onResponse(call, response);
            if (response.isSuccessful()) {
                ZoneController.this.mobileDevicesWrapper.setMobileDevices((List) response.body());
                TadoApplication.getBus().post(ZoneController.this.mobileDevicesWrapper);
            }
        }
    }

    class C07572 extends TadoCallback<HomeState> {
        C07572() {
        }

        public void onResponse(Call<HomeState> call, Response<HomeState> response) {
            super.onResponse(call, response);
            if (response.isSuccessful() && response.body() != null) {
                Snitcher.start().log(3, ZoneController.class.getSimpleName(), DateTime.now().toString() + "Home State", new Object[0]);
                TadoApplication.getBus().post(response.body());
            }
        }
    }

    class C07583 extends TadoCallback<List<Zone>> {
        C07583() {
        }

        public void onResponse(Call<List<Zone>> call, Response<List<Zone>> response) {
            super.onResponse(call, response);
            if (response.isSuccessful()) {
                ZoneController.this.processZoneList((List) response.body());
            }
        }
    }

    public void registerLoadingIndicator(LoadingIndicator loadingIndicator) {
        LoadingIndicator weakLoadingIndicator = (LoadingIndicator) this.loadingIndicator.get();
        if (weakLoadingIndicator != null) {
            weakLoadingIndicator.endProgress();
        }
        this.loadingIndicator = new WeakReference(loadingIndicator);
    }

    public void unregisterLoadingIndicator() {
        this.loadingIndicator = new WeakReference(null);
    }

    public void startConnectorTimers() {
        if (UserConfig.getHomeId() != -1 && UserConfig.getCurrentZone().intValue() != -1) {
            TimersManager.INSTANCE.startTimers();
        }
    }

    public void stopConnectorTimers() {
        TimersManager.INSTANCE.stopTimers();
    }

    public void callGetMobileDevice() {
        RestServiceGenerator.getTadoRestService().getMobileDevicesForZone(UserConfig.getHomeId(), UserConfig.getCurrentZone().intValue(), RestServiceGenerator.getCredentialsMap()).enqueue(new C07561());
    }

    public void callGetAllZoneStates() {
        if (this.zoneListWrapper != null) {
            for (Zone zone : this.zoneListWrapper.getZoneList()) {
                INSTANCE.callGetZoneState(zone.getId());
            }
        }
    }

    public void callGetHomeState() {
        RestServiceGenerator.getTadoRestService().getHomeState(UserConfig.getHomeId(), RestServiceGenerator.getCredentialsMap()).enqueue(new C07572());
    }

    public void setHomePresence(TadoModeEnum presence, TadoCallback<Void> callback) {
        RestServiceGenerator.getTadoRestService().updateHomePresence(UserConfig.getHomeId(), new HomePresenceWrapper(presence), RestServiceGenerator.getCredentialsMap()).enqueue(callback);
    }

    public void unmaskOpenWindow(TadoCallback<Void> callback) {
        RestServiceGenerator.getTadoRestService().unmaskOpenWindow(UserConfig.getHomeId(), UserConfig.getCurrentZone().intValue(), RestServiceGenerator.getCredentialsMap()).enqueue(callback);
    }

    public void callGetZoneList() {
        RestServiceGenerator.getTadoRestService().getZones(UserConfig.getHomeId(), RestServiceGenerator.getCredentialsMap()).enqueue(new C07583());
    }

    public ZoneState getZoneState(int id) {
        if (this.zoneListWrapper != null && this.zoneListWrapper.getZoneState(id) != null) {
            return this.zoneListWrapper.getZoneState(id);
        }
        callGetZoneState(id);
        return new ZoneState();
    }

    public void callGetZoneState(final int id) {
        if (id != -1) {
            LoadingIndicator ld = (LoadingIndicator) this.loadingIndicator.get();
            if (ld != null) {
                ld.startProgress();
            }
            Call<ZoneState> zoneStateCall = RestServiceGenerator.getTadoRestService().getZoneState(UserConfig.getHomeId(), id, RestServiceGenerator.getCredentialsMap());
            final long requestTimestampInMillis = System.currentTimeMillis();
            zoneStateCall.enqueue(new TadoCallback<ZoneState>() {
                public void onResponse(Call<ZoneState> call, Response<ZoneState> response) {
                    boolean z = false;
                    LoadingIndicator ld = (LoadingIndicator) ZoneController.this.loadingIndicator.get();
                    if (ld != null) {
                        ld.endProgress();
                    }
                    super.onResponse(call, response);
                    if (response.isSuccessful()) {
                        if (ZoneController.this.mCountDownOverlayTimer == null) {
                            ZoneController.this.isOverlayTimerRunning = false;
                        }
                        ZoneState state = (ZoneState) response.body();
                        state.setId(id);
                        ZoneController.this.addZoneStateToMap(id, state);
                        if (state.getOpenWindow() != null && state.getId() == ZoneController.this.getCurrentZoneId()) {
                            ZoneController.this.processTimerOverlay(state.getId(), state.getOpenWindow().getExpiry(), state.getOpenWindow().getRemainingTimeInSeconds() - ZoneController.this.getRoundtripTimeInSeconds(requestTimestampInMillis));
                        } else if (state.getOverlay() != null && state.getOverlay().getTermination().isOverlayTerminationTimer() && state.getId() == ZoneController.this.getCurrentZoneId()) {
                            ZoneController.this.processTimerOverlay(state.getId(), state.getOverlay().getTermination().getExpiry(), state.getOverlay().getTermination().getRemainingTimeInSeconds().intValue() - ZoneController.this.getRoundtripTimeInSeconds(requestTimestampInMillis));
                        } else if (state.getOverlay() == null || state.getOpenWindow() == null) {
                            ZoneController.this.cancelOverlayTimer(id);
                        }
                        ZoneController zoneController = ZoneController.this;
                        if (state.getOverlay() != null) {
                            z = true;
                        }
                        zoneController.zoneHasActiveOverlay = z;
                        if (!(state == null || state.getSetting() == null || !state.getSetting().getType().equalsIgnoreCase("HOT_WATER"))) {
                            UserConfig.setHotWaterProductionControl(Boolean.valueOf(true));
                        }
                        TadoApplication.getBus().post(state);
                    } else if (response.code() == 404 && ZoneController.this.getZoneList() != null) {
                        ZoneController.this.callGetZoneList();
                    }
                }

                public void onFailure(Call<ZoneState> call, Throwable t) {
                    super.onFailure(call, t);
                    LoadingIndicator ld = (LoadingIndicator) ZoneController.this.loadingIndicator.get();
                    if (ld != null) {
                        ld.errorProgress();
                    }
                }
            });
        }
    }

    public void selectZone(int id) {
        if (id != UserConfig.getCurrentZone().intValue()) {
            ControlPanelController.INSTANCE.cleanZone(UserConfig.getCurrentZone().intValue());
            selectZone(getZone(id));
        }
    }

    public void selectZone(Zone zone) {
        if (zone != null) {
            UserConfig.setCurrentZone(Integer.valueOf(zone.getId()));
            UserConfig.setCurrentZoneName(zone.getName());
            Crashlytics.setInt("zoneId", zone.getId());
            if (!(this.zoneListWrapper == null || this.zoneListWrapper.getZoneState(zone.getId()) == null)) {
                TadoApplication.getBus().post(this.zoneListWrapper.getZoneState(zone.getId()));
            }
            switchZoneCapabilities(zone.getId());
            callGetCurrentZoneState();
            TemperatureSettings.removeTemperatures();
        }
    }

    private void switchZoneCapabilities(int id) {
        if (!CapabilitiesController.INSTANCE.isZoneCapabilitiesAvailable(id)) {
            callZoneCapabilities(id);
        }
    }

    public void callGetCurrentZoneState() {
        callGetZoneState(UserConfig.getCurrentZone().intValue());
    }

    public void callZoneCapabilities(final int zoneId) {
        if (zoneId != -1) {
            RestServiceGenerator.getTadoRestService().getCapabilities(UserConfig.getHomeId(), zoneId, RestServiceGenerator.getCredentialsMap()).enqueue(new TadoCallback<ZoneCapabilities>() {
                public void onResponse(Call<ZoneCapabilities> call, Response<ZoneCapabilities> response) {
                    super.onResponse(call, response);
                    if (response.isSuccessful()) {
                        CapabilitiesController.INSTANCE.addCapabilities(zoneId, (ZoneCapabilities) response.body());
                        TadoApplication.getBus().post(new ZoneCapabilitiesLoadedEvent());
                        return;
                    }
                    try {
                        Crashlytics.log("getCapabilities unsuccessful " + response.errorBody().string());
                    } catch (IOException e) {
                        Crashlytics.logException(e);
                    }
                }
            });
        }
    }

    public void callPostOverlay(ZoneSetting setting, OverlayTerminationCondition overlayTerminationCondition, PresenterDelegate presenterDelegate, ViewEnabler viewEnabler) {
        final Object overlay = new ZoneOverlay();
        final ZoneSetting tempSetting = setting.clone();
        setting.prepareForUpdate(getCurrentZoneId());
        overlayTerminationCondition.setExpiry(null);
        overlayTerminationCondition.setProjectedExpiry(null);
        overlayTerminationCondition.setRemainingTimeInSeconds(null);
        overlay.setSetting(setting);
        overlay.setTermination(overlayTerminationCondition);
        Call<ZoneOverlay> postOverlayCall = RestServiceGenerator.getTadoRestService().putZoneOverlay(UserConfig.getHomeId(), getCurrentZoneId(), overlay, RestServiceGenerator.getCredentialsMap());
        new GsonBuilder().create().toJson(overlay);
        final long requestTimestampInMillis = System.currentTimeMillis();
        final ViewEnabler viewEnabler2 = viewEnabler;
        postOverlayCall.enqueue(new RetryCallback<ZoneOverlay>(presenterDelegate) {
            public void onResponse(Call<ZoneOverlay> call, Response<ZoneOverlay> response) {
                super.onResponse(call, response);
                TadoApplication.getBus().post(response);
                if (response.isSuccessful()) {
                    ZoneController.this.storeOverlay(overlay, tempSetting);
                    ZoneController.this.cancelOverlayTimer(ZoneController.this.getCurrentZoneId());
                    ZoneController.this.callGetCurrentZoneState();
                    ZoneOverlay overlayResponse = (ZoneOverlay) response.body();
                    if (!(overlayResponse == null || overlayResponse.getTermination() == null || overlayResponse.getTermination().getDurationInSeconds() == null)) {
                        ZoneController.this.processTimerOverlay(ZoneController.this.getCurrentZoneId(), overlayResponse.getTermination().getExpiry(), overlayResponse.getTermination().getRemainingTimeInSeconds().intValue() - ZoneController.this.getRoundtripTimeInSeconds(requestTimestampInMillis));
                    }
                } else {
                    TadoApplication.getBus().post(new HomeRestrictionEvent(this.serverError));
                }
                viewEnabler2.enableViews();
            }

            public void onFailure(Call<ZoneOverlay> call, Throwable t) {
                super.onFailure(call, t);
                viewEnabler2.enableViews();
            }
        });
    }

    private int getRoundtripTimeInSeconds(long requestTimestampInMillis) {
        return ((int) ((System.currentTimeMillis() - requestTimestampInMillis) / 2)) / 1000;
    }

    private void storeOverlay(ZoneOverlay overlay, ZoneSetting tempSetting) {
        if (!(this.mCountDownOverlayTimer == null || overlay.getTermination() == null || !overlay.getTermination().isOverlayTerminationTimer())) {
            ZoneOverlay storedOverlay = OverlaySettingSeriliazer.INSTANCE.restore(getCurrentZoneId());
            if (!(storedOverlay == null || storedOverlay.getTermination() == null || storedOverlay.getTermination().getDurationInSeconds() == null)) {
                overlay.getTermination().setDurationInSeconds(storedOverlay.getTermination().getDurationInSeconds());
            }
        }
        if (!(overlay.getSetting().getPowerBoolean() || tempSetting == null)) {
            overlay.setSetting(tempSetting);
        }
        OverlaySettingSeriliazer.INSTANCE.save(getCurrentZoneId(), overlay);
    }

    public void callRemoveOverlay(PresenterDelegate presenterDelegate, final ViewEnabler viewEnabler) {
        RestServiceGenerator.getTadoRestService().deleteZoneOverlay(UserConfig.getHomeId(), getCurrentZoneId(), RestServiceGenerator.getCredentialsMap()).enqueue(new RetryCallback<Void>(presenterDelegate) {
            public void onResponse(Call<Void> call, Response<Void> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    ZoneController.this.callGetCurrentZoneState();
                }
                TadoApplication.getBus().post(response);
                viewEnabler.enableViews();
            }

            public void onFailure(Call<Void> call, Throwable t) {
                super.onFailure(call, t);
                viewEnabler.enableViews();
            }
        });
        cancelOverlayTimer(getCurrentZoneId());
    }

    private void processZoneList(List<Zone> zoneList) {
        if (zoneList != null && !zoneList.isEmpty()) {
            ZoneListWrapper oldZoneListWrapper = this.zoneListWrapper;
            this.zoneListWrapper = new ZoneListWrapper(zoneList);
            boolean currentZoneInvalid = true;
            int size = zoneList.size();
            for (int i = 0; i < size; i++) {
                Zone zone = (Zone) zoneList.get(i);
                if (oldZoneListWrapper != null) {
                    zone.setZoneState(oldZoneListWrapper.getZoneState(zone.getId()));
                }
                if (zone.getId() == UserConfig.getCurrentZone().intValue()) {
                    currentZoneInvalid = false;
                }
                updateZoneForState(zone);
                callGetZoneState(zone.getId());
                callZoneCapabilities(zone.getId());
            }
            if (currentZoneInvalid || UserConfig.getCurrentZone().intValue() == -1) {
                selectZone(((Zone) zoneList.get(0)).getId());
            }
            TadoApplication.getBus().post(new ZoneListLoadedEvent());
        } else if (zoneList != null && zoneList.isEmpty() && this.zoneListWrapper != null) {
            this.zoneListWrapper.clear();
            TadoApplication.getBus().post(new ZoneListLoadedEvent());
        }
    }

    private void updateZoneForState(Zone zone) {
        if (UserConfig.getCurrentZone().equals(Integer.valueOf(zone.getId()))) {
            UserConfig.setCurrentZoneName(zone.getName());
        }
    }

    private void addZoneStateToMap(int id, ZoneState zoneState) {
        if (this.zoneListWrapper != null) {
            this.zoneListWrapper.updateZoneState(id, zoneState);
        }
    }

    public ZoneItem getZoneItemById(int zoneId) {
        if (!(zoneId <= -1 || this.zoneListWrapper == null || this.zoneListWrapper.isEmpty() || this.zoneListWrapper.getZone(zoneId) == null)) {
            Zone zone = this.zoneListWrapper.getZone(zoneId);
            if (zone != null) {
                return new ZoneItem(zone);
            }
        }
        return null;
    }

    public List<DrawerItem> getZoneItems() {
        List<DrawerItem> zoneItems = new ArrayList();
        if (!(this.zoneListWrapper == null || this.zoneListWrapper.isEmpty() || this.zoneListWrapper.getZoneList().isEmpty())) {
            for (Zone zone : this.zoneListWrapper.getZoneList()) {
                zoneItems.add(new ZoneItem(zone));
            }
        }
        return zoneItems;
    }

    public void updateZoneListOrder(List<ZoneOrderInput> order) {
        if (this.zoneListWrapper == null) {
            return;
        }
        if (this.zoneListWrapper.isValidZoneOrder(order)) {
            this.zoneListWrapper.updateZoneOrder(order);
        } else {
            callGetZoneList();
        }
    }

    public ZoneOverlay getDefaultOverlay() {
        ZoneOverlay overlay = new ZoneOverlay();
        overlay.setTermination(new OverlayTerminationCondition("MANUAL"));
        return overlay;
    }

    public ZoneState getCurrentZoneState() {
        return getZoneState(UserConfig.getCurrentZone().intValue());
    }

    public ZoneState getCurrentZoneStateForControlPanel() {
        if (this.mCurrentZoneState == null) {
            this.mCurrentZoneState = getZoneState(UserConfig.getCurrentZone().intValue());
        }
        return this.mCurrentZoneState;
    }

    public void resetCurrentZoneStateForControlPanel() {
        this.mCurrentZoneState = null;
    }

    public int getCurrentZoneId() {
        return UserConfig.getCurrentZone().intValue();
    }

    public String getCurrentZoneName() {
        return UserConfig.getCurrentZoneName();
    }

    public void startOverlayTimer(int zoneId, int seconds) {
        if (zoneId == UserConfig.getCurrentZone().intValue()) {
            if (this.mCountDownOverlayTimer != null) {
                this.mCountDownOverlayTimer.cancel();
                this.mCountDownOverlayTimer = null;
            }
            Snitcher.start().log(3, ZoneController.class.getSimpleName(), DateTime.now().toString() + ": " + seconds, new Object[0]);
            final int i = zoneId;
            this.mCountDownOverlayTimer = new CountDownTimer((long) (seconds * 1000), 1000) {
                public void onTick(long millisUntilFinished) {
                    if (i != UserConfig.getCurrentZone().intValue()) {
                        ZoneController.this.isOverlayTimerRunning = false;
                        return;
                    }
                    Integer secondsLeft = Integer.valueOf(0);
                    secondsLeft = Integer.valueOf(((int) millisUntilFinished) / 1000);
                    ZoneController.this.isOverlayTimerRunning = true;
                    ZoneController.INSTANCE.secondsLeft = secondsLeft.intValue();
                    TimerValue timerValue = new TimerValue();
                    timerValue.setTimerSeconds(secondsLeft.intValue());
                    TadoApplication.getBus().post(timerValue);
                }

                public void onFinish() {
                    ZoneController.this.isOverlayTimerRunning = false;
                    ZoneController.this.mTimerOverlayExpiryTimestamp = null;
                    TimerValue timerValue = new TimerValue();
                    timerValue.setTimerSeconds(0);
                    TadoApplication.getBus().post(timerValue);
                    ZoneController.this.resetTimerOverlay();
                }
            };
            this.mCountDownOverlayTimer.start();
        }
    }

    public void cancelOverlayTimer(int zoneId) {
        if (zoneId == UserConfig.getCurrentZone().intValue() && this.mCountDownOverlayTimer != null) {
            this.mCountDownOverlayTimer.cancel();
            this.isOverlayTimerRunning = false;
            this.mCountDownOverlayTimer = null;
        }
    }

    public boolean isOverlayTimerRunning() {
        return this.isOverlayTimerRunning;
    }

    public boolean zoneHasActiveOverlay() {
        return this.zoneHasActiveOverlay;
    }

    public int getOverlayTimerSecondsLeft() {
        return this.secondsLeft;
    }

    public void resetTimerOverlay() {
        if (this.mCountDownOverlayTimer != null) {
            this.mCountDownOverlayTimer.cancel();
            this.mCountDownOverlayTimer = null;
            TimerValue timerValue = new TimerValue();
            timerValue.setTimerSeconds(0);
            TadoApplication.getBus().post(timerValue);
        }
    }

    public int getOverlayTimerElapsedSeconds() {
        ZoneOverlay overlay = getCurrentZoneState().getOverlay();
        if (overlay == null || overlay.getTermination() == null || overlay.getTermination().getDurationInSeconds() == null) {
            return 0;
        }
        return overlay.getTermination().getDurationInSeconds().intValue() - this.secondsLeft;
    }

    public int getOverlayTimerDurationInSeconds() {
        ZoneOverlay overlay = getCurrentZoneStateForControlPanel().getOverlay();
        if (overlay == null || overlay.getTermination() == null || overlay.getTermination().getDurationInSeconds() == null) {
            return 0;
        }
        return overlay.getTermination().getDurationInSeconds().intValue();
    }

    public boolean isTimerSet() {
        return this.setTimer;
    }

    public void setTimerSet(boolean setTimer) {
        this.setTimer = setTimer;
    }

    public void setOverlayTimerDurationInSeconds(int seconds) {
        this.setTimer = true;
        ZoneOverlay zoneOverlay = ControlPanelController.INSTANCE.getOverlay();
        if (!(zoneOverlay == null || zoneOverlay.getTermination() == null)) {
            zoneOverlay.getTermination().setDurationInSeconds(Integer.valueOf(seconds));
        }
        ZoneOverlay overlay = getCurrentZoneStateForControlPanel().getOverlay();
        if (overlay != null && overlay.getTermination() != null) {
            overlay.getTermination().setDurationInSeconds(Integer.valueOf(seconds));
        }
    }

    private void processTimerOverlay(int zoneId, String timestamp, int remainingTimeInSeconds) {
        if ((this.mTimerOverlayExpiryTimestamp == null || !this.mTimerOverlayExpiryTimestamp.equalsIgnoreCase(timestamp) || this.mCountDownOverlayTimer == null) && remainingTimeInSeconds >= 0) {
            this.mTimerOverlayExpiryTimestamp = timestamp;
            INSTANCE.startOverlayTimer(zoneId, remainingTimeInSeconds);
        }
    }

    public void updateZoneName(int zoneId, String newName, GenericCallbackListener<Zone> listener, PresenterDelegate presenterDelegate) {
        final int i = zoneId;
        final String str = newName;
        RestServiceGenerator.getTadoRestService().putZoneName(UserConfig.getHomeId(), zoneId, RestServiceGenerator.getCredentialsMap(), new ZoneName(newName)).enqueue(new RetryCallback<Zone>(presenterDelegate, listener) {
            public void onResponse(Call<Zone> call, Response<Zone> response) {
                if (response.isSuccessful()) {
                    if (i == ZoneController.this.getCurrentZoneId()) {
                        UserConfig.setCurrentZoneName(str);
                    }
                    if (!(ZoneController.this.zoneListWrapper == null || ZoneController.this.zoneListWrapper.getZone(i) == null)) {
                        ZoneController.this.zoneListWrapper.getZone(i).setName(str);
                    }
                }
                super.onResponse(call, response);
            }
        });
    }

    public boolean getZoneReportAvailable(int zoneId) {
        if (this.zoneListWrapper == null || this.zoneListWrapper.getZone(zoneId) == null) {
            return false;
        }
        return this.zoneListWrapper.getZone(zoneId).getReportAvailable();
    }

    public String getZoneName(int zoneId) {
        String zoneName = UserConfig.getCurrentZoneName();
        if (this.zoneListWrapper != null) {
            return this.zoneListWrapper.getZoneName(zoneId, zoneName);
        }
        return zoneName;
    }

    public void getZoneName(final int zoneId, GenericCallbackListener<Zone> listener, PresenterDelegate presenterDelegate, RetryListener retryListener) {
        Call<Zone> call = RestServiceGenerator.getTadoRestService().getZoneName(UserConfig.getHomeId(), zoneId, RestServiceGenerator.getCredentialsMap());
        TadoCallback callback = new RetryCallback<Zone>(presenterDelegate, listener) {
            public void onResponse(Call<Zone> call, Response<Zone> response) {
                if (response.isSuccessful()) {
                    String newName = ((Zone) response.body()).getName();
                    if (!(ZoneController.this.zoneListWrapper == null || ZoneController.this.zoneListWrapper.getZone(zoneId) == null)) {
                        ZoneController.this.zoneListWrapper.updateZone(zoneId, (Zone) response.body());
                    }
                    if (zoneId == ZoneController.this.getCurrentZoneId()) {
                        UserConfig.setCurrentZoneName(newName);
                    }
                    super.onResponse(call, response);
                }
            }
        };
        callback.setRetryListener(retryListener);
        call.enqueue(callback);
    }

    public void getOverlayDefaultPreferences(int zone_id, GenericCallbackListener<OverlayTerminationConditionResponse> listener, PresenterDelegate presenterDelegate, RetryListener retryListener) {
        Call<OverlayTerminationConditionResponse> call = RestServiceGenerator.getTadoRestService().getDefaultOverlaySettings(UserConfig.getHomeId(), zone_id, RestServiceGenerator.getCredentialsMap());
        TadoCallback<OverlayTerminationConditionResponse> callback = new RetryCallback<OverlayTerminationConditionResponse>(presenterDelegate, listener) {
        };
        callback.setRetryListener(retryListener);
        call.enqueue(callback);
    }

    public void setOverlayDefaultPreferences(int zone_id, String overlayType, Integer durationInSeconds, GenericCallbackListener<OverlayTerminationConditionResponse> listener, PresenterDelegate presenterDelegate) {
        OverlayTerminationCondition overlayTerminationCondition = new OverlayTerminationCondition(overlayType);
        if (overlayTerminationCondition.isOverlayTerminationTimer()) {
            overlayTerminationCondition.setDurationInSeconds(durationInSeconds);
        } else {
            overlayTerminationCondition.setDurationInSeconds(null);
        }
        RestServiceGenerator.getTadoRestService().putDefaultOverlaySettings(UserConfig.getHomeId(), zone_id, RestServiceGenerator.getCredentialsMap(), new OverlayTerminationConditionResponse(overlayTerminationCondition)).enqueue(new RetryCallback<OverlayTerminationConditionResponse>(presenterDelegate, listener) {
        });
    }

    public void getMeasurementDevice(int zone_id, @NonNull GenericCallbackListener listener, PresenterDelegate presenterDelegate, RetryListener retryListener) {
        TadoCallback callback = new RetryCallback<GenericHardwareDevice>(presenterDelegate, listener) {
        };
        callback.setRetryListener(retryListener);
        RestServiceGenerator.getTadoRestService().getMeasuringDevice(UserConfig.getHomeId(), zone_id, RestServiceGenerator.getCredentialsMap()).enqueue(callback);
    }

    public void getOffsetTemperature(String serialNumber, @NonNull GenericCallbackListener<Temperature> listener, PresenterDelegate presenterDelegate) {
        RestServiceGenerator.getTadoRestService().getTemperatureOffset(serialNumber, RestServiceGenerator.getCredentialsMap()).enqueue(new RetryCallback<Temperature>(presenterDelegate, listener) {
        });
    }

    public void getZoneDevices(int home_id, int zone_id, @NonNull GenericCallbackListener<List<GenericHardwareDevice>> listener, PresenterDelegate presenterDelegate, RetryListener retryListener) {
        TadoCallback callback = new RetryCallback<List<GenericHardwareDevice>>(presenterDelegate, listener) {
        };
        callback.setRetryListener(retryListener);
        RestServiceGenerator.getTadoRestService().getZoneDevices(home_id, zone_id, RestServiceGenerator.getCredentialsMap()).enqueue(callback);
    }

    @NonNull
    public List<Zone> getZoneList() {
        if (this.zoneListWrapper != null) {
            return this.zoneListWrapper.getZoneList();
        }
        callGetZoneList();
        return new ArrayList();
    }

    public void clear() {
        this.zoneListWrapper = null;
        this.mCurrentZoneState = null;
        this.mobileDevicesWrapper = new MobileDevicesWrapper();
        CapabilitiesController.INSTANCE.clear();
    }

    public boolean isHeatingHome() {
        for (Zone zone : getZoneList()) {
            if (zone.isHeatingZone()) {
                return true;
            }
        }
        return false;
    }

    public boolean containsZoneType(String targetZoneType) {
        if (this.zoneListWrapper == null) {
            return false;
        }
        for (Zone zone : this.zoneListWrapper.getZoneList()) {
            if (zone.getType().name().equalsIgnoreCase(targetZoneType)) {
                return true;
            }
        }
        return false;
    }

    public void updateDazzleMode(int homeId, int zoneId, boolean dazzleEnabled, GenericCallbackListener<Void> listener, PresenterDelegate presenterDelegate) {
        DazzleMode dazzleMode = new DazzleMode();
        dazzleMode.setEnabled(dazzleEnabled);
        final int i = zoneId;
        final boolean z = dazzleEnabled;
        RestServiceGenerator.getTadoRestService().updateDazzleMode(homeId, zoneId, dazzleMode, RestServiceGenerator.getCredentialsMap()).enqueue(new RetryCallback<Void>(presenterDelegate, listener) {
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!(!response.isSuccessful() || ZoneController.this.zoneListWrapper == null || ZoneController.this.zoneListWrapper.getZone(i) == null)) {
                    ZoneController.this.zoneListWrapper.getZone(i).getDazzleMode().setEnabled(z);
                }
                super.onResponse(call, response);
            }
        });
    }

    public List<MobileDevice> getMobileDevices() {
        return this.mobileDevicesWrapper.getMobileDevices();
    }

    public void removeZone(int zoneid) {
        this.zoneListWrapper.removeZone(zoneid);
        if (this.mCurrentZoneState != null && this.mCurrentZoneState.getId() == zoneid && this.zoneListWrapper.getZoneList().size() > 0) {
            selectZone((Zone) this.zoneListWrapper.getZoneList().get(0));
        }
    }

    @Nullable
    public Date getCurrentZoneDateCreated() {
        if (this.zoneListWrapper == null || this.zoneListWrapper.getZone(getCurrentZoneId()) == null) {
            return null;
        }
        return this.zoneListWrapper.getZone(getCurrentZoneId()).getDateCreated();
    }

    @Nullable
    public Zone getCurrentZone() {
        if (this.zoneListWrapper == null || this.zoneListWrapper.getZone(getCurrentZoneId()) == null) {
            return null;
        }
        return this.zoneListWrapper.getZone(getCurrentZoneId());
    }

    @Nullable
    public Zone getZone(int id) {
        if (this.zoneListWrapper == null || this.zoneListWrapper.getZone(id) == null) {
            return null;
        }
        return this.zoneListWrapper.getZone(id);
    }

    @Nullable
    public Integer getZoneIdByName(String name) {
        for (Zone zone : this.zoneListWrapper.getZoneList()) {
            if (name.equals(zone.getName())) {
                return Integer.valueOf(zone.getId());
            }
        }
        return null;
    }

    public boolean isZoneIdValid(int zoneId) {
        try {
            return this.zoneListWrapper.getZone(zoneId) != null;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public void setZoneListWrapper(ZoneListWrapper zoneListWrapper) {
        this.zoneListWrapper = zoneListWrapper;
        processZoneList(zoneListWrapper.getZoneList());
    }

    public boolean getBatteryState() {
        if (this.zoneListWrapper != null) {
            for (Zone zone : this.zoneListWrapper.getZoneList()) {
                if (zone.hasLowBattery()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Nullable
    public Pair<Zone, GenericHardwareDevice> findDeviceBySerialNumber(@NonNull String serialnumber) {
        if (this.zoneListWrapper != null) {
            for (Zone zone : this.zoneListWrapper.getZoneList()) {
                for (GenericHardwareDevice device : zone.getDevices()) {
                    if (device.getSerialNo().equals(serialnumber)) {
                        return new Pair(zone, device);
                    }
                }
            }
        }
        return null;
    }

    public List<Pair<Zone, GenericHardwareDevice>> getAllZonesAndDevices() {
        List<Pair<Zone, GenericHardwareDevice>> list = new ArrayList();
        if (this.zoneListWrapper != null) {
            for (Zone zone : this.zoneListWrapper.getZoneList()) {
                for (GenericHardwareDevice device : zone.getDevices()) {
                    list.add(new Pair(zone, device));
                }
            }
        }
        return list;
    }
}
