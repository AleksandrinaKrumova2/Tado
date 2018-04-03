package com.tado.android.mvp.views;

import android.support.v4.view.ViewPager;
import com.tado.android.entities.InstallationInfo;
import com.tado.android.menu.DrawerItem;
import com.tado.android.mvp.presenters.WeatherPresenter;
import com.tado.android.rest.model.HomeInfo.PartnerEnum;
import com.tado.android.rest.model.Zone;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.ViewEnabler;
import java.util.List;

public class MainZoneNoOpView implements MainZoneView {
    private static final String TAG = "MainZoneNoOpView";

    public void initLocationApi() {
        Snitcher.start().toCrashlytics().log(3, TAG, "initLocationApi", new Object[0]);
    }

    public void prepareProgressBarOnLoadingScreen() {
        Snitcher.start().toCrashlytics().log(3, TAG, "prepareProgressBarOnLoadingScreen", new Object[0]);
    }

    public void prepareNavigationDrawer(InstallationInfo installationInfo) {
        Snitcher.start().toCrashlytics().log(3, TAG, "prepareNavigationDrawer", new Object[0]);
    }

    public void prepareZoneFragmentPager(List<Zone> list, WeatherPresenter weatherPresenter) {
        Snitcher.start().toCrashlytics().log(3, TAG, "prepareZoneFragmentPager", new Object[0]);
    }

    public void showCannotApplyChanges() {
        Snitcher.start().toCrashlytics().log(3, TAG, "showCannotApplyChanges", new Object[0]);
    }

    public void showHomeRestrictionDialog() {
        Snitcher.start().toCrashlytics().log(3, TAG, "showHomeRestrictionDialog", new Object[0]);
    }

    public void manualControlPanelSlideUp() {
        Snitcher.start().toCrashlytics().log(3, TAG, "manualControlPanelSlideUp", new Object[0]);
    }

    public void manualControlPanelSlideDown() {
        Snitcher.start().toCrashlytics().log(3, TAG, "manualControlPanelSlideDown", new Object[0]);
    }

    public void zoneDrawerUpdateZoneItems(List<DrawerItem> list) {
        Snitcher.start().toCrashlytics().log(3, TAG, "zoneDrawerUpdateZoneItems", new Object[0]);
    }

    public void updateLoadingScreenVisibility(boolean visible) {
        Snitcher.start().toCrashlytics().log(3, TAG, "updateLoadingScreenVisibility", new Object[0]);
    }

    public void updateBetaViewVisibility() {
        Snitcher.start().toCrashlytics().log(3, TAG, "updateBetaViewVisibility", new Object[0]);
    }

    public void updateManualControlPanelVisibility(boolean visible) {
        Snitcher.start().toCrashlytics().log(3, TAG, "updateManualControlPanelVisibility", new Object[0]);
    }

    public void showRateAppDialog() {
        Snitcher.start().toCrashlytics().log(3, TAG, "showRateAppDialog", new Object[0]);
    }

    public void openZoneOfflineSupportPage() {
        Snitcher.start().toCrashlytics().log(3, TAG, "openZoneOfflineSupportPage", new Object[0]);
    }

    public void cleanFragmentBackStack() {
        Snitcher.start().toCrashlytics().log(3, TAG, "cleanFragmentBackStack", new Object[0]);
    }

    public void startManualControlFragment() {
        Snitcher.start().toCrashlytics().log(3, TAG, "startManualControlFragment", new Object[0]);
    }

    public void onManualControlStartClick(ViewEnabler viewEnabler) {
        Snitcher.start().toCrashlytics().log(3, TAG, "onManualControlStartClick", new Object[0]);
    }

    public void onManualControlBackClick() {
        Snitcher.start().toCrashlytics().log(3, TAG, "onManualControlBackClick", new Object[0]);
    }

    public void handleBackPressed() {
        Snitcher.start().toCrashlytics().log(3, TAG, "handleBackPressed", new Object[0]);
    }

    public void zoneFragmentPagerSetCurrentItem(int position) {
        Snitcher.start().toCrashlytics().log(3, TAG, "zoneFragmentPagerSetCurrentItem", new Object[0]);
    }

    public void openReport() {
        Snitcher.start().toCrashlytics().log(3, TAG, "openReport", new Object[0]);
    }

    public void openManualControlPanelTerminationList() {
        Snitcher.start().toCrashlytics().log(3, TAG, "openManualControlPanelTerminationList", new Object[0]);
    }

    public void openManualControlPanelTimer() {
        Snitcher.start().toCrashlytics().log(3, TAG, "openManualControlPanelTimer", new Object[0]);
    }

    public void goBackFromFragment() {
        Snitcher.start().toCrashlytics().log(3, TAG, "goBackFromFragment", new Object[0]);
    }

    public void initOnboarding() {
        Snitcher.start().toCrashlytics().log(3, TAG, "initOnboarding", new Object[0]);
    }

    public void setLoadingLogo(PartnerEnum partner) {
        Snitcher.start().toCrashlytics().log(3, TAG, "setLoadingLogo", new Object[0]);
    }

    public void setupStartInstallationScreen() {
        Snitcher.start().toCrashlytics().log(3, TAG, "setupStartInstallationScreen", new Object[0]);
    }

    public void setupResumeInstallationScreen() {
        Snitcher.start().toCrashlytics().log(3, TAG, "setupResumeInstallationScreen", new Object[0]);
    }

    public void showMainZoneScreen() {
        Snitcher.start().toCrashlytics().log(3, TAG, "showMainZoneScreen", new Object[0]);
    }

    public void updateNavigationDrawer(InstallationInfo installationInfo) {
        Snitcher.start().toCrashlytics().log(3, TAG, "updateNavigationDrawer", new Object[0]);
    }

    public ViewPager getZoneFragementPager() {
        Snitcher.start().toCrashlytics().log(3, TAG, "getZoneFragementPager", new Object[0]);
        return null;
    }

    public void prepareDemoBottomSheet() {
        Snitcher.start().toCrashlytics().log(3, TAG, "prepareDemoBottomSheet", new Object[0]);
    }

    public void showDelayedMarketingAlert() {
        Snitcher.start().toCrashlytics().log(3, TAG, "showDelayedMarketingAlert", new Object[0]);
    }

    public void showNoInternetConnectionMessage() {
    }

    public void dismissNoInternetConnectionMessage() {
    }
}
