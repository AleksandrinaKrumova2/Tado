package com.tado.android.mvp.presenters;

import android.util.Pair;
import android.view.MotionEvent;
import com.crashlytics.android.Crashlytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.squareup.otto.Subscribe;
import com.tado.C0676R;
import com.tado.android.app.NavigationDrawerFragment.NavigationDrawerCallbacks;
import com.tado.android.app.TadoApplication;
import com.tado.android.control_panel.ControlPanelController;
import com.tado.android.control_panel.ControlPanelFragment.OnControlPanelFragmentInteractionListener;
import com.tado.android.control_panel.ControlPanelOverlayTimerFragment.OnControlOverlayTimerFragmentInteractionListener;
import com.tado.android.controllers.HomeRestrictionEvent;
import com.tado.android.controllers.ZoneController;
import com.tado.android.controllers.ZoneListLoadedEvent;
import com.tado.android.demo.DemoUtils;
import com.tado.android.demo.marketing.MarketingAlertsManager;
import com.tado.android.entities.InstallationInfo;
import com.tado.android.fcm.FCMInstanceService;
import com.tado.android.mvp.common.BasePresenter;
import com.tado.android.mvp.model.LowBatteryRepository;
import com.tado.android.mvp.model.TadoModeEnum;
import com.tado.android.mvp.views.MainZoneNoOpView;
import com.tado.android.mvp.views.MainZoneView;
import com.tado.android.notifications.NotificationUtil;
import com.tado.android.rest.model.GeolocationUpdate;
import com.tado.android.rest.model.Link;
import com.tado.android.rest.model.MobileDevicesWrapper;
import com.tado.android.rest.model.Zone;
import com.tado.android.rest.model.ZoneState;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import com.tado.android.utils.ConnectivityChangeListener;
import com.tado.android.utils.ConnectivityChangeListener.ConnectionChangeCallback;
import com.tado.android.utils.ConnectivityChangeListener.ConnectivityBroadcastReceiver;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.ViewEnabler;
import com.tado.android.views.ControlPanelOverlayTerminationListFragment.OnControlPanelOverlayTerminationListFragmentInteractionListener;
import java.util.Iterator;
import java.util.List;
import retrofit2.Response;

public class MainZonePresenter implements BasePresenter<MainZoneView>, NavigationDrawerCallbacks, OnControlPanelFragmentInteractionListener, OnControlPanelOverlayTerminationListFragmentInteractionListener, OnControlOverlayTimerFragmentInteractionListener, ConnectionChangeCallback {
    private static final String TAG = "MainZonePresenter";
    private ConnectivityBroadcastReceiver connectivityBroadcastReceiver;
    private TadoModeEnum currentMode;
    private boolean endManualControlButtonWasShownUncollapsed = false;
    private InstallationInfo installationInfo;
    private boolean mHasActiveOverlay = false;
    private boolean mIsZoneListLoading = true;
    private MainZoneView mMainZoneView;
    private boolean mManualControlPanelHidden = true;
    private MobileDevicesWrapper mMobileDevicesWrapper;
    private ZoneState mZoneState;
    private WeatherPresenter weatherPresenter;

    public void bindView(MainZoneView view) {
        Snitcher.start().log(3, TAG, "%d bindView", Integer.valueOf(System.identityHashCode(this)));
        this.mMainZoneView = view;
        this.weatherPresenter = new WeatherPresenter();
    }

    public void unbindView() {
        Snitcher.start().log(3, TAG, "%d unbindView", Integer.valueOf(System.identityHashCode(this)));
        this.mMainZoneView = new MainZoneNoOpView();
    }

    public void showLoadingScreen() {
        this.mMainZoneView.setLoadingLogo(UserConfig.getPartner());
        this.mMainZoneView.updateLoadingScreenVisibility(true);
    }

    public void hideLoadingScreen() {
        this.mMainZoneView.updateLoadingScreenVisibility(false);
    }

    public void onCreate(InstallationInfo installationInfo) {
        this.installationInfo = installationInfo;
        this.mMainZoneView.prepareProgressBarOnLoadingScreen();
        this.mMainZoneView.prepareNavigationDrawer(installationInfo);
        if (shouldShowOnboarding() && !installationInfo.isStartInstallation()) {
            this.mMainZoneView.initOnboarding();
        }
        if (DemoUtils.isInDemoMode()) {
            this.mMainZoneView.prepareDemoBottomSheet();
        }
        registerPushNotifications();
        initCrashlyticsTags();
    }

    private void registerPushNotifications() {
        String fcmToken = FirebaseInstanceId.getInstance().getToken();
        if (fcmToken != null) {
            Snitcher.start().log(TAG, "fcm push token: %s", fcmToken);
            String storedToken = UserConfig.getFcmToken();
            if (storedToken == null || !fcmToken.equals(storedToken)) {
                FCMInstanceService.sendRegistrationToServer(fcmToken);
            }
        }
    }

    private void initCrashlyticsTags() {
        if (DemoUtils.isInDemoMode()) {
            Crashlytics.setBool("demoMode", true);
            return;
        }
        Crashlytics.setInt("homeId", UserConfig.getHomeId());
        Crashlytics.setUserIdentifier(UserConfig.getNickname());
        Crashlytics.setUserEmail(UserConfig.getUsername());
        Crashlytics.setString("serverAddress", UserConfig.getServerAddress());
    }

    private boolean shouldShowOnboarding() {
        return TadoApplication.getTadoAppContext().getResources().getBoolean(C0676R.bool.onBoarding) && !DemoUtils.isInDemoMode();
    }

    public void onResume(InstallationInfo installationInfo) {
        TadoApplication.getBus().register(this);
        this.connectivityBroadcastReceiver = ConnectivityChangeListener.register(this);
        this.installationInfo = installationInfo;
        ZoneController.INSTANCE.callGetZoneList();
        ZoneController.INSTANCE.startConnectorTimers();
        if (!(installationInfo.isStartInstallation() || ZoneController.INSTANCE.getZoneList().size() == 0)) {
            showLoadingScreen();
            this.mMainZoneView.initLocationApi();
        }
        prepareMainScreenLayout();
        this.mMainZoneView.updateBetaViewVisibility();
        this.mMainZoneView.updateManualControlPanelVisibility(shouldShowManualControlPanel());
        this.mMainZoneView.showRateAppDialog();
        this.mMainZoneView.updateNavigationDrawer(installationInfo);
    }

    public void onPause() {
        TadoApplication.getBus().unregister(this);
        ConnectivityChangeListener.unregister(this.connectivityBroadcastReceiver);
        this.weatherPresenter.unregister();
        ZoneController.INSTANCE.stopConnectorTimers();
        MarketingAlertsManager.INSTANCE.cancelDelayedAlerts();
    }

    public boolean hasActiveOverlay() {
        Snitcher.start().log(3, TAG, "%d hasActiveOverlay %b", Integer.valueOf(System.identityHashCode(this)), Boolean.valueOf(this.mHasActiveOverlay));
        return this.mHasActiveOverlay;
    }

    public boolean isZoneOffline() {
        return this.mZoneState != null && this.mZoneState.getLink().getState().equalsIgnoreCase(Link.OFFLINE);
    }

    public void postTouchEvent(MotionEvent ev) {
        TadoApplication.getBus().post(ev);
    }

    public void setManualControlPanelHidden(boolean manualControlPanelHidden) {
        this.mManualControlPanelHidden = manualControlPanelHidden;
    }

    public boolean isManualControlPanelHidden() {
        return this.mManualControlPanelHidden;
    }

    public void onManualControlOpen() {
        MarketingAlertsManager.INSTANCE.cancelDelayedAlerts();
        if (isZoneOffline()) {
            this.mMainZoneView.openZoneOfflineSupportPage();
            return;
        }
        ZoneController.INSTANCE.setTimerSet(false);
        ZoneController.INSTANCE.resetCurrentZoneStateForControlPanel();
        this.endManualControlButtonWasShownUncollapsed = false;
        this.mMainZoneView.cleanFragmentBackStack();
        ControlPanelController.INSTANCE.setTimerEdited(false);
        this.mMainZoneView.startManualControlFragment();
        this.mMainZoneView.manualControlPanelSlideUp();
    }

    public void onDrawerSlide() {
        if (!isManualControlPanelHidden()) {
            closeControlPanelWithoutUpdate();
        }
    }

    public void onDrawerClosed() {
        this.mMainZoneView.showDelayedMarketingAlert();
    }

    public void onDrawerOpened() {
        MarketingAlertsManager.INSTANCE.cancelDelayedAlerts();
    }

    public void onManualControlStartClick(ViewEnabler viewEnabler) {
        this.mMainZoneView.showDelayedMarketingAlert();
        ControlPanelController.INSTANCE.cleanZone(ZoneController.INSTANCE.getCurrentZoneId());
        this.mMainZoneView.onManualControlStartClick(viewEnabler);
    }

    public void onManualControlCancelClick() {
        this.mMainZoneView.showDelayedMarketingAlert();
        closeControlPanelWithoutUpdate();
        ControlPanelController.INSTANCE.cleanZone(ZoneController.INSTANCE.getCurrentZoneId());
    }

    public void onManualControlBackClick() {
        this.mMainZoneView.onManualControlBackClick();
    }

    public void zoneFragmentPagerOnPagerStateChanged() {
        closeControlPanelWithoutUpdate();
    }

    public void zoneFragmentPagerOnZoneSelected(Zone zone) {
        ZoneController.INSTANCE.selectZone(zone);
        initZoneFragmentWithData();
    }

    public void closeManualControlPanelWithoutUpdate() {
        closeControlPanelWithoutUpdate();
    }

    public void initZoneFragmentWithData() {
        this.mMainZoneView.showDelayedMarketingAlert();
        if (this.mMobileDevicesWrapper != null) {
            TadoApplication.getBus().post(this.mMobileDevicesWrapper);
        }
    }

    public void onReportClick() {
        this.mMainZoneView.openReport();
    }

    public void onBackPressed() {
        this.mMainZoneView.handleBackPressed();
    }

    public void onSelectZoneId(int zoneId) {
        int position = 0;
        Iterator it = ZoneController.INSTANCE.getZoneList().iterator();
        while (it.hasNext() && ((Zone) it.next()).getId() != zoneId) {
            position++;
        }
        this.mMainZoneView.showMainZoneScreen();
        this.mMainZoneView.zoneFragmentPagerSetCurrentItem(position);
        initZoneFragmentWithData();
        ZoneController.INSTANCE.selectZone(ZoneController.INSTANCE.getCurrentZoneId());
    }

    public void onInstallation() {
        if (this.installationInfo.isStartInstallation()) {
            this.mMainZoneView.setupStartInstallationScreen();
        } else {
            this.mMainZoneView.setupResumeInstallationScreen();
        }
    }

    public void onOverlayTerminationClick() {
        this.mMainZoneView.openManualControlPanelTerminationList();
    }

    public void onCloseControlPanel() {
        closeControlPanelWithoutUpdate();
        this.mMainZoneView.showDelayedMarketingAlert();
    }

    public void onOverlayTerminationTimerClick() {
        this.mMainZoneView.openManualControlPanelTimer();
    }

    public void onOverlayListTerminationBackClick() {
        this.mMainZoneView.goBackFromFragment();
    }

    public void onTimerSet(int seconds, boolean setTimer) {
        if (setTimer) {
            ZoneController.INSTANCE.setOverlayTimerDurationInSeconds(seconds);
        }
    }

    public void onBackButton() {
        this.mMainZoneView.goBackFromFragment();
    }

    private void prepareZoneFragmentPager() {
        if (ZoneController.INSTANCE.getZoneList().isEmpty()) {
            showLoadingScreen();
            ZoneController.INSTANCE.callGetZoneList();
            this.mIsZoneListLoading = true;
            return;
        }
        this.mMainZoneView.prepareZoneFragmentPager(ZoneController.INSTANCE.getZoneList(), this.weatherPresenter);
        this.mIsZoneListLoading = false;
        ZoneController.INSTANCE.selectZone(ZoneController.INSTANCE.getCurrentZoneId());
        initZoneFragmentWithData();
    }

    private void updateZoneState(ZoneState zoneState) {
        Snitcher.start().log(3, TAG, "%d updateZoneState id=%d currentzone=%d", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(zoneState.getId()), UserConfig.getCurrentZone());
        this.mZoneState = zoneState;
        if (!(this.currentMode == null || this.currentMode == zoneState.getTadoMode())) {
            ZoneController.INSTANCE.callGetMobileDevice();
        }
        this.currentMode = zoneState.getTadoMode();
    }

    private void closeControlPanelWithoutUpdate() {
        this.mMainZoneView.manualControlPanelSlideDown();
        ZoneController.INSTANCE.resetCurrentZoneStateForControlPanel();
    }

    private boolean shouldShowManualControlPanel() {
        return !this.mManualControlPanelHidden;
    }

    private void prepareMainScreenLayout() {
        if (this.installationInfo.isStartInstallation()) {
            this.mMainZoneView.setupStartInstallationScreen();
        } else if (!this.installationInfo.hasUnfinishedInstallations() || this.installationInfo.hasZones()) {
            this.mMainZoneView.showMainZoneScreen();
            if (ZoneController.INSTANCE.getZoneList().size() != 0) {
                prepareZoneFragmentPager();
            }
        } else {
            this.mMainZoneView.setupResumeInstallationScreen();
        }
    }

    private void checkBatteryStatus() {
        LowBatteryRepository lowBatteryRepository = new LowBatteryRepository(TadoApplication.getTadoAppContext(), UserConfig.getUsername(), UserConfig.getHomeId());
        List<Pair<Zone, GenericHardwareDevice>> zones = ZoneController.INSTANCE.getAllZonesAndDevices();
        lowBatteryRepository.updateDevices(zones);
        for (Pair<Zone, GenericHardwareDevice> zone : zones) {
            if (((GenericHardwareDevice) zone.second).getBatteryState() != null) {
                lowBatteryRepository.updateDeviceBatteryStatus(((GenericHardwareDevice) zone.second).getSerialNo(), ((GenericHardwareDevice) zone.second).getBatteryState());
            }
        }
        NotificationUtil.showBatteryNotifications(TadoApplication.getTadoAppContext(), lowBatteryRepository);
    }

    @Subscribe
    public void getZoneState(ZoneState zoneState) {
        boolean z = true;
        Snitcher.start().log(3, TAG, "%d getZoneState id=%d currentzone=%d", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(zoneState.getId()), UserConfig.getCurrentZone());
        if (zoneState.getId() == UserConfig.getCurrentZone().intValue()) {
            if (zoneState.getOverlay() == null) {
                z = false;
            }
            this.mHasActiveOverlay = z;
            if (!this.mIsZoneListLoading) {
                hideLoadingScreen();
                updateZoneState(zoneState);
                this.mMainZoneView.zoneDrawerUpdateZoneItems(ZoneController.INSTANCE.getZoneItems());
            }
        }
    }

    @Subscribe
    public void getOverlayResponse(Response response) {
        if (response.isSuccessful()) {
            closeControlPanelWithoutUpdate();
        }
    }

    @Subscribe
    public void getMobileDevice(MobileDevicesWrapper mobileDevicesWrapper) {
        this.mMobileDevicesWrapper = mobileDevicesWrapper;
    }

    @Subscribe
    public void getZoneListLoadedEvent(ZoneListLoadedEvent e) {
        this.mIsZoneListLoading = false;
        prepareMainScreenLayout();
        this.mMainZoneView.zoneDrawerUpdateZoneItems(ZoneController.INSTANCE.getZoneItems());
        checkBatteryStatus();
    }

    @Subscribe
    public void getHomeRestrictionEvent(HomeRestrictionEvent event) {
        if (event.getServerError() == null || event.getServerError().getCode() == null || !event.getServerError().getCode().contains("homeRestrictionViolated")) {
            this.mMainZoneView.showCannotApplyChanges();
        } else {
            this.mMainZoneView.showHomeRestrictionDialog();
        }
    }

    @Subscribe
    public void onLocationUpdate(GeolocationUpdate update) {
        ZoneController.INSTANCE.callGetMobileDevice();
    }

    public boolean isBound() {
        return this.mMainZoneView != null;
    }

    public void onInternetConnected() {
        if (this.mIsZoneListLoading) {
            ZoneController.INSTANCE.callGetZoneList();
        } else {
            ZoneController.INSTANCE.callGetCurrentZoneState();
        }
        this.mMainZoneView.dismissNoInternetConnectionMessage();
    }

    public void onInternetDisconnected() {
        this.mMainZoneView.showNoInternetConnectionMessage();
    }
}
