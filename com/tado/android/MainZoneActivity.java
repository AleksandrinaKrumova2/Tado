package com.tado.android;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff.Mode;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetBehavior.BottomSheetCallback;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crashlytics.android.Crashlytics;
import com.nhaarman.supertooltips.ToolTipView;
import com.tado.C0676R;
import com.tado.android.adapters.demo.DemoBottomSheetItem;
import com.tado.android.adapters.demo.DemoBottomSheetRecyclerViewAdapter;
import com.tado.android.alert_dialogs.RestrictionDialogFragment;
import com.tado.android.analytics.AnalyticsConstants.Events;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.app.NavigationDrawerFragment;
import com.tado.android.app.TadoApplication;
import com.tado.android.control_panel.ControlPanelFragment;
import com.tado.android.control_panel.ControlPanelOverlayTimerFragment;
import com.tado.android.controllers.RateAppUtil;
import com.tado.android.controllers.ZoneController;
import com.tado.android.demo.DemoMenuFactory;
import com.tado.android.demo.DemoUtils;
import com.tado.android.demo.marketing.MarketingAlertsManager;
import com.tado.android.entities.InstallationInfo;
import com.tado.android.installation.ChooseProductActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.installation.unfinished.UnfinishedInstallationsActivity;
import com.tado.android.location.LocationPermissionControler;
import com.tado.android.location.LocationTrackingFragment;
import com.tado.android.menu.DrawerItem;
import com.tado.android.mvp.EndManualControlButtonInteraction;
import com.tado.android.mvp.presenters.MainZonePresenter;
import com.tado.android.mvp.presenters.WeatherPresenter;
import com.tado.android.mvp.views.MainZoneView;
import com.tado.android.mvp.views.ZoneView;
import com.tado.android.onboarding.OnboardingActivity;
import com.tado.android.onboarding.data.FeatureAssetsDataSource;
import com.tado.android.onboarding.data.FeatureDataSource.LoadVersionCallback;
import com.tado.android.onboarding.data.TutorialDataSourceRepository.TutorialDataSourceRepositoryEnum;
import com.tado.android.report.ReportActivity;
import com.tado.android.rest.model.HomeInfo.PartnerEnum;
import com.tado.android.rest.model.Zone;
import com.tado.android.utils.ColorFactory;
import com.tado.android.utils.ResourceFactory;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.ViewEnabler;
import com.tado.android.views.ControlPanelOverlayTerminationListFragment;
import com.tado.android.views.loadingindicator.LoadingIndicator;
import java.util.List;
import java.util.Locale;

public class MainZoneActivity extends AppCompatActivity implements MainZoneView {
    public static final String INSTALLATION_INFO = "installationInfo";
    private static final int ON_BOARDING_RESULT = 873;
    private static final String TAG = "MainZoneActivity";
    @BindView(2131296375)
    View bottomSheetParentView;
    @BindView(2131296376)
    RecyclerView bottomSheetRecyclerView;
    private ControlPanelFragment controlPanelFragment;
    private ControlPanelOverlayTerminationListFragment controlPanelListFragment;
    private ControlPanelOverlayTimerFragment controlPanelTimerFragment;
    @BindView(2131296549)
    Button demoButton;
    @BindView(2131296695)
    Button installationButton;
    @BindView(2131296696)
    ImageView installationImage;
    @BindView(2131296697)
    View installationLayout;
    @BindView(2131296698)
    TextView installationMessage;
    @BindView(2131296699)
    TextView installationTitle;
    @BindView(2131296678)
    ImageView loadingLogo;
    @BindView(2131296724)
    View loadingScreen;
    @BindView(2131296367)
    View mBetaView;
    @BindView(2131296456)
    RelativeLayout mControlPanelLayout;
    private MainZonePresenter mMainZonePresenter;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    @BindView(2131296847)
    ProgressBar mProgressBar;
    @BindView(2131297212)
    ViewPager mZoneFragmentPager;
    private Snackbar noInternetSnackbar;

    class C06771 implements LoadVersionCallback {
        C06771() {
        }

        public void onVersionLoaded(int version) {
            if (UserConfig.getLastOnboardingVersionShown() < version) {
                UserConfig.setLastOnboardingVersionShown(version);
                Intent intent = new Intent(MainZoneActivity.this, OnboardingActivity.class);
                intent.putExtra(OnboardingActivity.KEY_TUTORIAL_DATA_SOURCE, TutorialDataSourceRepositoryEnum.ON_BOARDING);
                MainZoneActivity.this.startActivityForResult(intent, MainZoneActivity.ON_BOARDING_RESULT);
                return;
            }
            MainZoneActivity.this.onFinishOnboarding();
        }

        public void onVersionNoFeaturesToShow() {
            MainZoneActivity.this.onFinishOnboarding();
        }

        public void onLoadingError(String msg) {
            Snitcher.start().toCrashlytics().log(6, MainZoneActivity.TAG, msg, new Object[0]);
            MainZoneActivity.this.onFinishOnboarding();
        }
    }

    class C06782 implements OnClickListener {
        C06782() {
        }

        public void onClick(View v) {
            InstallationProcessController.startActivity(MainZoneActivity.this, ChooseProductActivity.class, true);
        }
    }

    class C06793 implements OnClickListener {
        C06793() {
        }

        public void onClick(View v) {
            MainZoneActivity.this.startActivity(new Intent(MainZoneActivity.this, UnfinishedInstallationsActivity.class));
        }
    }

    class C06804 implements OnClickListener {
        C06804() {
        }

        public void onClick(View v) {
            int position = MainZoneActivity.this.bottomSheetRecyclerView.getChildAdapterPosition(v);
            if (position != -1) {
                DemoBottomSheetItem item = ((DemoBottomSheetRecyclerViewAdapter) MainZoneActivity.this.bottomSheetRecyclerView.getAdapter()).getItem(position);
                if (item.shouldCloseParentOnClick()) {
                    MainZoneActivity.this.collapseBottomSheet(false);
                }
                item.onClick();
            }
        }
    }

    class C06815 implements OnTouchListener {
        C06815() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            boolean consumed = MainZoneActivity.this.isBottomSheetExpanded();
            if (consumed) {
                MainZoneActivity.this.collapseBottomSheet(true);
            }
            return consumed;
        }
    }

    class C06826 extends BottomSheetCallback {
        C06826() {
        }

        public void onStateChanged(@NonNull View bottomSheet, int newState) {
        }

        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            MainZoneActivity.this.bottomSheetParentView.setBackgroundColor(ColorUtils.setAlphaComponent(ContextCompat.getColor(MainZoneActivity.this, C0676R.color.light_black), (int) (119.0f * slideOffset)));
        }
    }

    class C06848 implements OnPageChangeListener {
        C06848() {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        public void onPageSelected(int position) {
            ZoneController.INSTANCE.registerLoadingIndicator((LoadingIndicator) MainZoneActivity.this.mZoneFragmentPager.getAdapter().instantiateItem(MainZoneActivity.this.mZoneFragmentPager, MainZoneActivity.this.mZoneFragmentPager.getCurrentItem()));
            MainZoneActivity.this.mMainZonePresenter.zoneFragmentPagerOnZoneSelected((Zone) ((ZoneFragmentPagerStateAdapter) MainZoneActivity.this.mZoneFragmentPager.getAdapter()).items.get(position));
        }

        public void onPageScrollStateChanged(int state) {
            if (state == 1) {
                MainZoneActivity.this.mMainZonePresenter.zoneFragmentPagerOnPagerStateChanged();
            }
        }
    }

    class C06859 implements OnClickListener {
        C06859() {
        }

        public void onClick(View v) {
            MainZoneActivity.this.onApplyClick(v);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Snitcher.start().log(3, TAG, "onCreate", new Object[0]);
        setContentView((int) C0676R.layout.activity_main_zone);
        ButterKnife.bind((Activity) this);
        if (savedInstanceState != null && savedInstanceState.containsKey("installationInfo")) {
            InstallationProcessController.getInstallationProcessController().getInstallationInfo().init((InstallationInfo) savedInstanceState.getSerializable("installationInfo"));
        }
        registerPresenter();
        this.mMainZonePresenter.onCreate(InstallationProcessController.getInstallationProcessController().getInstallationInfo());
        if (TadoApplication.isInternal()) {
            blockScreenWithTrackerInfo();
        }
    }

    private void registerPresenter() {
        if (this.mMainZonePresenter == null) {
            this.mMainZonePresenter = new MainZonePresenter();
        }
        if (!this.mMainZonePresenter.isBound()) {
            this.mMainZonePresenter.bindView((MainZoneView) this);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == 1) {
            this.mMainZonePresenter.postTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    protected void onResume() {
        super.onResume();
        Snitcher.start().log(3, TAG, "onResume", new Object[0]);
        this.mMainZonePresenter.onResume(InstallationProcessController.getInstallationProcessController().getInstallationInfo());
        AnalyticsHelper.trackPageView(getApplication(), Screen.MAIN_ZONE);
        MarketingAlertsManager.INSTANCE.showAlert(this);
    }

    protected void onPause() {
        Snitcher.start().log(3, TAG, "onPause", new Object[0]);
        this.mMainZonePresenter.onPause();
        if (this.mNavigationDrawerFragment.isDrawerOpen()) {
            this.mNavigationDrawerFragment.closeDrawerWithoutAnimation();
        }
        ZoneController.INSTANCE.unregisterLoadingIndicator();
        super.onPause();
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("installationInfo", InstallationProcessController.getInstallationProcessController().getInstallationInfo());
        super.onSaveInstanceState(outState);
    }

    public void onBackPressed() {
        this.mMainZonePresenter.onBackPressed();
    }

    public void onReportClick(View view) {
        this.mMainZonePresenter.onReportClick();
    }

    public void onModesClick(View view) {
        this.mMainZonePresenter.onManualControlOpen();
    }

    public void onDrawerSlide() {
        this.mMainZonePresenter.onDrawerSlide();
    }

    public void onDrawerOpened() {
        this.mMainZonePresenter.onDrawerOpened();
    }

    public void onDrawerClosed() {
        this.mMainZonePresenter.onDrawerClosed();
    }

    public void onDrawerClick(View view) {
        this.mNavigationDrawerFragment.openDrawer();
    }

    public void onEndManualControlClick(View view) {
        Snitcher.start().log(3, TAG, "%d onEndManualControlClick", Integer.valueOf(System.identityHashCode(this)));
        AnalyticsHelper.trackEvent(getApplication(), Screen.MANUAL_CONTROL, "Stop", getCurrentEndManualControlButtonInterface().isCollapsed() ? "collapsed" : "expanded");
    }

    public void onApplyClick(View view) {
        ViewEnabler viewEnabler = new ViewEnabler(view);
        viewEnabler.disableViews();
        this.mMainZonePresenter.onManualControlStartClick(viewEnabler);
    }

    public void onCancelClick(View view) {
        view.setEnabled(false);
        AnalyticsHelper.trackEvent(getApplication(), Screen.MANUAL_CONTROL, "Cancel");
        this.mMainZonePresenter.onManualControlCancelClick();
    }

    public void updateBetaViewVisibility() {
        View view = this.mBetaView;
        int i = (!getResources().getBoolean(C0676R.bool.beta) || DemoUtils.isInDemoMode()) ? 8 : 0;
        view.setVisibility(i);
    }

    public void updateManualControlPanelVisibility(boolean visible) {
        this.mControlPanelLayout.setVisibility(visible ? 0 : 4);
        View dimView = getDimView();
        if (dimView != null) {
            dimView.setBackgroundColor(ContextCompat.getColor(this, visible ? C0676R.color.light_black : C0676R.color.transparent));
        }
    }

    public void showRateAppDialog() {
        RateAppUtil rateAppUtil = new RateAppUtil();
        if (rateAppUtil.checkRateAppDialog()) {
            rateAppUtil.showRateAppDialog(getSupportFragmentManager());
        }
    }

    public void openZoneOfflineSupportPage() {
        String url = getString(C0676R.string.url_troubleshooting);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    public void cleanFragmentBackStack() {
        getSupportFragmentManager().popBackStack(null, 1);
    }

    public void startManualControlFragment() {
        this.controlPanelFragment = ControlPanelFragment.newInstance(this.mMainZonePresenter);
        getSupportFragmentManager().beginTransaction().replace(C0676R.id.cooling_control_panel_layout_fragment_holder, this.controlPanelFragment).commitAllowingStateLoss();
    }

    public void onManualControlStartClick(ViewEnabler viewEnabler) {
        if (this.controlPanelFragment != null) {
            if (this.controlPanelTimerFragment != null && this.controlPanelTimerFragment.isVisible()) {
                this.controlPanelTimerFragment.onApply();
                this.controlPanelFragment.onTimerChanged();
            }
            this.controlPanelFragment.onApply(viewEnabler);
        }
    }

    public void onManualControlBackClick() {
        if (this.controlPanelListFragment != null) {
            this.controlPanelListFragment.onBackClick();
        }
    }

    public void handleBackPressed() {
        if (isBottomSheetExpanded()) {
            collapseBottomSheet(true);
        } else if (this.mNavigationDrawerFragment.isDrawerOpen()) {
            this.mNavigationDrawerFragment.closeDrawer();
            showDelayedMarketingAlert();
        } else if (!this.mMainZonePresenter.isManualControlPanelHidden() && getSupportFragmentManager().getBackStackEntryCount() == 0) {
            this.mMainZonePresenter.closeManualControlPanelWithoutUpdate();
            showDelayedMarketingAlert();
        } else if (!this.mMainZonePresenter.isManualControlPanelHidden() && getSupportFragmentManager().getBackStackEntryCount() != 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!this.mMainZonePresenter.isManualControlPanelHidden() || getSupportFragmentManager().getBackStackEntryCount() <= 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack(null, 1);
            super.onBackPressed();
        }
    }

    public void zoneFragmentPagerSetCurrentItem(int position) {
        if (this.mZoneFragmentPager != null) {
            this.mZoneFragmentPager.setCurrentItem(position, false);
            this.installationLayout.setVisibility(8);
        }
    }

    public void openReport() {
        startActivity(new Intent(this, ReportActivity.class));
    }

    public void openManualControlPanelTerminationList() {
        this.controlPanelListFragment = ControlPanelOverlayTerminationListFragment.newInstance(this.mMainZonePresenter);
        getSupportFragmentManager().beginTransaction().replace(C0676R.id.cooling_control_panel_layout_fragment_holder, this.controlPanelListFragment).addToBackStack(null).commitAllowingStateLoss();
    }

    public void openManualControlPanelTimer() {
        this.controlPanelTimerFragment = ControlPanelOverlayTimerFragment.newInstance(this.mMainZonePresenter);
        getSupportFragmentManager().beginTransaction().replace(C0676R.id.cooling_control_panel_layout_fragment_holder, this.controlPanelTimerFragment).addToBackStack(null).commitAllowingStateLoss();
    }

    public void goBackFromFragment() {
        getSupportFragmentManager().popBackStack();
    }

    public void initOnboarding() {
        FeatureAssetsDataSource.getInstance(getResources(), getAssets(), Locale.getDefault()).getVersion(new C06771());
    }

    private void onFinishOnboarding() {
    }

    public void setLoadingLogo(PartnerEnum partner) {
        this.loadingLogo.setImageLevel(partner.getCode());
    }

    public void setupStartInstallationScreen() {
        this.installationLayout.setVisibility(0);
        this.installationTitle.setText(C0676R.string.installation_addDevice_title);
        this.installationMessage.setText(C0676R.string.installation_addDevice_message);
        this.installationButton.setText(C0676R.string.installation_addDevice_addDeviceButton);
        this.installationImage.setImageDrawable(ResourceFactory.getInstallationIcon(this, true));
        this.installationButton.setBackgroundColor(getInstallationsColor());
        this.installationButton.setOnClickListener(new C06782());
    }

    public void setupResumeInstallationScreen() {
        this.mZoneFragmentPager.setVisibility(8);
        this.installationLayout.setVisibility(0);
        this.installationTitle.setText(C0676R.string.installation_unfinishedInstallation_title);
        this.installationMessage.setText(C0676R.string.installation_unfinishedInstallation_message);
        this.installationButton.setText(C0676R.string.installation_unfinishedInstallation_proceedButton);
        this.installationImage.setImageDrawable(ResourceFactory.getInstallationIcon(this, false));
        this.installationButton.setBackgroundColor(getInstallationsColor());
        this.installationButton.setOnClickListener(new C06793());
    }

    public void updateNavigationDrawer(InstallationInfo installationInfo) {
        if (this.mNavigationDrawerFragment != null) {
            this.mNavigationDrawerFragment.updateInstallationInfo(installationInfo);
        }
    }

    public void prepareDemoBottomSheet() {
        this.demoButton.setCompoundDrawablesWithIntrinsicBounds(ResourceFactory.getTintedVectorSupportDrawable(this, C0676R.drawable.ic_demo_menu, C0676R.color.white), null, null, null);
        this.demoButton.setVisibility(0);
        DemoBottomSheetRecyclerViewAdapter adapter = new DemoBottomSheetRecyclerViewAdapter(DemoMenuFactory.createDemoListEntries(this), new C06804());
        this.bottomSheetRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.bottomSheetRecyclerView.setAdapter(adapter);
        this.bottomSheetParentView.setOnTouchListener(new C06815());
        BottomSheetBehavior.from(this.bottomSheetRecyclerView).setBottomSheetCallback(new C06826());
    }

    public void showDelayedMarketingAlert() {
        if (ZoneController.INSTANCE.getCurrentZone() != null) {
            MarketingAlertsManager.INSTANCE.showDelayedAlert(this, ZoneController.INSTANCE.getCurrentZone().getType());
        }
    }

    private int getInstallationsColor() {
        return ColorFactory.getMixedColor(ContextCompat.getColor(this, C0676R.color.manual), -1);
    }

    public void showMainZoneScreen() {
        this.mZoneFragmentPager.setVisibility(0);
        this.installationLayout.setVisibility(8);
    }

    private void slideAnimation(boolean show) {
        if (show && !this.mMainZonePresenter.isManualControlPanelHidden()) {
            return;
        }
        if (show || !this.mMainZonePresenter.isManualControlPanelHidden()) {
            float from;
            float to;
            int fromColor;
            int toColor;
            Interpolator slideInterpolator;
            Animator dim;
            if (show) {
                this.mControlPanelLayout.setVisibility(0);
                from = this.mControlPanelLayout.getMeasuredHeight() == 0 ? 800.0f : (float) this.mControlPanelLayout.getMeasuredHeight();
                to = 0.0f;
                fromColor = C0676R.color.transparent;
                toColor = C0676R.color.light_black;
                slideInterpolator = new DecelerateInterpolator();
            } else {
                from = 0.0f;
                to = (float) this.mControlPanelLayout.getMeasuredHeight();
                fromColor = C0676R.color.light_black;
                toColor = C0676R.color.transparent;
                slideInterpolator = new AccelerateInterpolator();
            }
            this.mControlPanelLayout.clearAnimation();
            if (getCurrentEndManualControlButtonInterface() != null) {
                getCurrentEndManualControlButtonInterface().onEndManualControlButtonSlideAnimation(show);
            } else {
                StringBuilder message = new StringBuilder();
                message.append("getCurrentEndManualControlButtonInterface() is null ");
                message.append("mZoneFragmentPager is " + (this.mZoneFragmentPager == null ? "null" : "not null"));
                message.append("mZoneFragmentPager.getAdapter() is " + (this.mZoneFragmentPager.getAdapter() == null ? "null" : "not null"));
                message.append("mZoneFragmentPager.getCurrentItem() is " + this.mZoneFragmentPager.getCurrentItem());
                Crashlytics.log(6, TAG, message.toString());
            }
            ObjectAnimator slide = ObjectAnimator.ofFloat(this.mControlPanelLayout, ToolTipView.TRANSLATION_Y_COMPAT, new float[]{from, to});
            View dimView = getDimView();
            if (dimView != null) {
                dim = ObjectAnimator.ofObject(dimView, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(ContextCompat.getColor(this, fromColor)), Integer.valueOf(ContextCompat.getColor(this, toColor))});
            } else {
                dim = ObjectAnimator.ofInt(new int[]{0, 1});
            }
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setInterpolator(slideInterpolator);
            animatorSet.playTogether(new Animator[]{slide, dim});
            final boolean z = show;
            animatorSet.addListener(new AnimatorListener() {
                public void onAnimationStart(Animator animation) {
                    MainZoneActivity.this.mMainZonePresenter.setManualControlPanelHidden(!z);
                    MainZoneActivity.this.getCurrentEndManualControlButtonInterface().onAnimationStart(z, MainZoneActivity.this.mMainZonePresenter.hasActiveOverlay(), MainZoneActivity.this.mControlPanelLayout, slideInterpolator);
                }

                public void onAnimationEnd(Animator animation) {
                    MainZoneActivity.this.getCurrentEndManualControlButtonInterface().onAnimationEnd(z, MainZoneActivity.this.mMainZonePresenter.hasActiveOverlay(), MainZoneActivity.this.mControlPanelLayout, slideInterpolator);
                    if (!z) {
                        if (MainZoneActivity.this.controlPanelTimerFragment != null) {
                            MainZoneActivity.this.getSupportFragmentManager().beginTransaction().remove(MainZoneActivity.this.controlPanelTimerFragment).commitAllowingStateLoss();
                        }
                        if (MainZoneActivity.this.controlPanelListFragment != null) {
                            MainZoneActivity.this.getSupportFragmentManager().beginTransaction().remove(MainZoneActivity.this.controlPanelListFragment).commitAllowingStateLoss();
                        }
                        if (MainZoneActivity.this.controlPanelFragment != null) {
                            MainZoneActivity.this.getSupportFragmentManager().beginTransaction().remove(MainZoneActivity.this.controlPanelFragment).commitAllowingStateLoss();
                        }
                    }
                }

                public void onAnimationCancel(Animator animation) {
                    MainZoneActivity.this.getCurrentEndManualControlButtonInterface().onAnimationCancel(MainZoneActivity.this.mMainZonePresenter.hasActiveOverlay());
                }

                public void onAnimationRepeat(Animator animation) {
                }
            });
            animatorSet.start();
        }
    }

    @Nullable
    private View getDimView() {
        ZoneView view = getCurrentZoneFragment();
        if (view != null) {
            return view.getDimView();
        }
        return null;
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LocationPermissionControler.INSTANCE.handlePermissionCallback(requestCode, permissions, grantResults);
    }

    public void initLocationApi() {
        if (UserConfig.isLocationBasedControlEnabled() && !LocationPermissionControler.INSTANCE.checkLocationPermissionsAndInitLocationApi(this)) {
            LocationPermissionControler.INSTANCE.showLocationPermissionInfoDialog(this);
        }
    }

    public void prepareProgressBarOnLoadingScreen() {
        this.mProgressBar.getIndeterminateDrawable().setColorFilter(-1, Mode.SRC_ATOP);
    }

    public void prepareNavigationDrawer(InstallationInfo installationInfo) {
        this.mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(C0676R.id.navigation_drawer);
        this.mNavigationDrawerFragment.setUp(C0676R.id.navigation_drawer, (DrawerLayout) findViewById(C0676R.id.drawer_layout));
        this.mNavigationDrawerFragment.registerCallback(this.mMainZonePresenter);
        this.mNavigationDrawerFragment.setInstallationInfo(installationInfo);
        if (VERSION.SDK_INT >= 16) {
            LayoutTransition layoutTransition = this.mControlPanelLayout.getLayoutTransition();
            if (layoutTransition != null) {
                layoutTransition.enableTransitionType(1);
            }
        }
    }

    public void prepareZoneFragmentPager(List<Zone> zoneItems, WeatherPresenter weatherPresenter) {
        this.mZoneFragmentPager.clearOnPageChangeListeners();
        this.mZoneFragmentPager.addOnPageChangeListener(new C06848());
        if (this.mZoneFragmentPager.getAdapter() == null) {
            this.mZoneFragmentPager.setAdapter(new ZoneFragmentPagerStateAdapter(getSupportFragmentManager(), zoneItems, weatherPresenter, this.mZoneFragmentPager));
        } else {
            ((ZoneFragmentPagerStateAdapter) this.mZoneFragmentPager.getAdapter()).addItems(zoneItems);
        }
        int currentPosition = ((ZoneFragmentPagerStateAdapter) this.mZoneFragmentPager.getAdapter()).getPositionForZoneId(ZoneController.INSTANCE.getCurrentZoneId());
        this.mZoneFragmentPager.setCurrentItem(currentPosition);
        ZoneController.INSTANCE.registerLoadingIndicator(((ZoneFragmentPagerStateAdapter) this.mZoneFragmentPager.getAdapter()).getItem(currentPosition));
        this.mZoneFragmentPager.setOffscreenPageLimit(2);
        updateManualControlPanelVisibility(this.mControlPanelLayout.getVisibility() == 0);
    }

    public void showCannotApplyChanges() {
        Snackbar.make(findViewById(C0676R.id.container), (int) C0676R.string.manualControl_errors_changesNotApplied_message, 0).setAction((int) C0676R.string.manualControl_errors_changesNotApplied_retryButton, new C06859()).show();
    }

    public void showHomeRestrictionDialog() {
        Fragment dialogFragment = new RestrictionDialogFragment();
        if (PartnerEnum.HOMESERVE == UserConfig.getPartner()) {
            dialogFragment.setContentImageResourceId(C0676R.drawable.home_serv__logo__grey);
            dialogFragment.setIsContentImageVisible(true);
            dialogFragment.setTitleTextResourceId(C0676R.string.errors_homeRestricted_homeserve_title);
            dialogFragment.setContentTextResourceId(C0676R.string.errors_homeRestricted_homeserve_message);
        }
        getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).add((int) C0676R.id.drawer_layout, dialogFragment).addToBackStack(null).commitAllowingStateLoss();
    }

    public void manualControlPanelSlideUp() {
        slideAnimation(true);
    }

    public void manualControlPanelSlideDown() {
        slideAnimation(false);
    }

    public void zoneDrawerUpdateZoneItems(List<DrawerItem> zoneItems) {
        this.mNavigationDrawerFragment.updateZones(zoneItems);
        setDrawerBadgeVisibility(ZoneController.INSTANCE.getBatteryState());
    }

    public void updateLoadingScreenVisibility(boolean visible) {
        this.loadingScreen.setVisibility(visible ? 0 : 4);
    }

    private EndManualControlButtonInteraction getCurrentEndManualControlButtonInterface() {
        Snitcher.start().log(3, TAG, "%d getCurrentEndManualControlButtonInterface mZoneFragmentPager.getCurrentItem()=%d", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(this.mZoneFragmentPager.getCurrentItem()));
        return (EndManualControlButtonInteraction) this.mZoneFragmentPager.getAdapter().instantiateItem(this.mZoneFragmentPager, this.mZoneFragmentPager.getCurrentItem());
    }

    protected void onDestroy() {
        Snitcher.start().log(3, TAG, "onDestroy", new Object[0]);
        this.mMainZonePresenter.unbindView();
        super.onDestroy();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ON_BOARDING_RESULT) {
            onFinishOnboarding();
        }
    }

    public void onModeIconClick(View view) {
    }

    private void blockScreenWithTrackerInfo() {
        this.mNavigationDrawerFragment.lockDrawerClosed();
        getSupportFragmentManager().beginTransaction().add(C0676R.id.container, LocationTrackingFragment.newInstance(), "tracker").commit();
    }

    public ViewPager getZoneFragementPager() {
        return this.mZoneFragmentPager;
    }

    public void onDemoClick(View view) {
        AnalyticsHelper.trackEvent((Activity) this, Events.DEMO, "openMenu");
        MarketingAlertsManager.INSTANCE.cancelDelayedAlerts();
        BottomSheetBehavior.from(this.bottomSheetRecyclerView).setState(3);
    }

    public boolean isBottomSheetExpanded() {
        return BottomSheetBehavior.from(this.bottomSheetRecyclerView).getState() == 3;
    }

    public void collapseBottomSheet(boolean bottomSheetCanceled) {
        if (bottomSheetCanceled) {
            AnalyticsHelper.trackEvent((Activity) this, Events.DEMO, "cancel");
            showDelayedMarketingAlert();
        }
        BottomSheetBehavior.from(this.bottomSheetRecyclerView).setState(4);
    }

    public void showNoInternetConnectionMessage() {
        this.noInternetSnackbar = Snackbar.make(this.mZoneFragmentPager, (int) C0676R.string.errors_noInternetConnection_message, -2);
        this.noInternetSnackbar.show();
    }

    public void dismissNoInternetConnectionMessage() {
        if (this.noInternetSnackbar != null) {
            this.noInternetSnackbar.dismiss();
            this.noInternetSnackbar = null;
        }
    }

    public void setDrawerBadgeVisibility(boolean visible) {
        ZoneView fragment = getCurrentZoneFragment();
        if (fragment != null) {
            fragment.setDrawerBadgeVisibility(visible);
        }
        this.mNavigationDrawerFragment.setBadgeVisibility(visible);
    }

    @Nullable
    private ZoneView getCurrentZoneFragment() {
        if (this.mZoneFragmentPager == null || this.mZoneFragmentPager.getAdapter() == null) {
            return null;
        }
        return (ZoneView) this.mZoneFragmentPager.getAdapter().instantiateItem(this.mZoneFragmentPager, this.mZoneFragmentPager.getCurrentItem());
    }
}
