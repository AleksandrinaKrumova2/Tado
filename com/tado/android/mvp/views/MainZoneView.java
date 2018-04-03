package com.tado.android.mvp.views;

import android.support.v4.view.ViewPager;
import com.tado.android.entities.InstallationInfo;
import com.tado.android.menu.DrawerItem;
import com.tado.android.mvp.common.BaseView;
import com.tado.android.mvp.presenters.WeatherPresenter;
import com.tado.android.rest.model.HomeInfo.PartnerEnum;
import com.tado.android.rest.model.Zone;
import com.tado.android.utils.ViewEnabler;
import java.util.List;

public interface MainZoneView extends BaseView {
    void cleanFragmentBackStack();

    void dismissNoInternetConnectionMessage();

    ViewPager getZoneFragementPager();

    void goBackFromFragment();

    void handleBackPressed();

    void initLocationApi();

    void initOnboarding();

    void manualControlPanelSlideDown();

    void manualControlPanelSlideUp();

    void onManualControlBackClick();

    void onManualControlStartClick(ViewEnabler viewEnabler);

    void openManualControlPanelTerminationList();

    void openManualControlPanelTimer();

    void openReport();

    void openZoneOfflineSupportPage();

    void prepareDemoBottomSheet();

    void prepareNavigationDrawer(InstallationInfo installationInfo);

    void prepareProgressBarOnLoadingScreen();

    void prepareZoneFragmentPager(List<Zone> list, WeatherPresenter weatherPresenter);

    void setLoadingLogo(PartnerEnum partnerEnum);

    void setupResumeInstallationScreen();

    void setupStartInstallationScreen();

    void showCannotApplyChanges();

    void showDelayedMarketingAlert();

    void showHomeRestrictionDialog();

    void showMainZoneScreen();

    void showNoInternetConnectionMessage();

    void showRateAppDialog();

    void startManualControlFragment();

    void updateBetaViewVisibility();

    void updateLoadingScreenVisibility(boolean z);

    void updateManualControlPanelVisibility(boolean z);

    void updateNavigationDrawer(InstallationInfo installationInfo);

    void zoneDrawerUpdateZoneItems(List<DrawerItem> list);

    void zoneFragmentPagerSetCurrentItem(int i);
}
