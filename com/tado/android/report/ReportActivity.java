package com.tado.android.report;

import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import com.squareup.otto.Subscribe;
import com.tado.C0676R;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.app.TadoApplication;
import com.tado.android.controllers.ZoneController;
import com.tado.android.onboarding.OnboardingActivity;
import com.tado.android.onboarding.TutorialUtil;
import com.tado.android.onboarding.data.FeatureDataSource;
import com.tado.android.onboarding.data.FeatureDataSource.LoadVersionCallback;
import com.tado.android.premium.PremiumCarouselActivity;
import com.tado.android.rest.model.HomeInfo.LicenseEnum;
import com.tado.android.utils.UserConfig;
import com.tado.android.views.CustomViewPager;
import com.tado.android.views.MorphingButton;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener;
import java.util.Calendar;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Days;

public class ReportActivity extends FragmentActivity {
    private static final DateTime DATE_NEW_REPORT_AVAILABLE = new DateTime().withDate(2016, 12, 7);
    private static final int OFFSCREEN_PAGE_LIMIT = 1;
    private static final int ON_BOARDING_RESULT = 873;
    private int MAX_DAYS = MorphingButton.COLLAPSE_ANIMATION_DELAY;
    private DateTime currentDate;
    private int lastPage = (this.MAX_DAYS - 1);
    private FeatureDataSource mTutorialInstance;
    private int pageViews = 1;
    private DateTime startDate;

    class C10553 implements OnPageChangeListener {
        C10553() {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        public void onPageSelected(int position) {
            if (position < ReportActivity.this.lastPage) {
                ReportActivity.this.lastPage = position;
                ReportActivity.this.pageViews = ReportActivity.this.pageViews + 1;
                AnalyticsHelper.trackEvent(ReportActivity.this.getApplication(), Screen.REPORT, "ReportPageView", Long.valueOf((long) position));
            }
        }

        public void onPageScrollStateChanged(int state) {
        }
    }

    class C10564 implements LoadVersionCallback {
        C10564() {
        }

        public void onVersionLoaded(int version) {
            if (UserConfig.getLastTutorialVersionShown(ReportActivity.this.mTutorialInstance.getType(), 0) < version) {
                UserConfig.setLastTutorialVersionShown(ReportActivity.this.mTutorialInstance.getType(), version);
                Intent intent = new Intent(ReportActivity.this, OnboardingActivity.class);
                intent.putExtra(OnboardingActivity.KEY_TITLE, ReportActivity.this.getString(C0676R.string.tutorials_report_title));
                intent.putExtra(OnboardingActivity.KEY_TUTORIAL_DATA_SOURCE, ReportActivity.this.mTutorialInstance.getType());
                ReportActivity.this.startActivityForResult(intent, ReportActivity.ON_BOARDING_RESULT);
            }
        }

        public void onVersionNoFeaturesToShow() {
        }

        public void onLoadingError(String msg) {
        }
    }

    class C10575 implements OnDateSetListener {
        C10575() {
        }

        public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
            CustomViewPager viewPager = (CustomViewPager) ReportActivity.this.findViewById(C0676R.id.report_pager);
            viewPager.setCurrentItem(Math.max(0, (viewPager.getAdapter().getCount() - 1) - Days.daysBetween(new DateTime(year, monthOfYear + 1, dayOfMonth, 0, 0), new DateTime()).getDays()));
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        PagerAdapter pageAdapter;
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        if (VERSION.SDK_INT > 16) {
            getWindow().getDecorView().setSystemUiVisibility(4);
        }
        setContentView(C0676R.layout.activity_report_layout);
        View betaView = findViewById(C0676R.id.beta_text_view);
        CustomViewPager viewPager = (CustomViewPager) findViewById(C0676R.id.report_pager);
        viewPager.setVisibility(0);
        this.currentDate = new DateTime();
        FragmentManager fm = getSupportFragmentManager();
        if (UserConfig.getLicense() == LicenseEnum.NON_PREMIUM) {
            this.startDate = this.currentDate.minusDays(1);
            this.MAX_DAYS = 2;
            this.lastPage = this.MAX_DAYS - 1;
            pageAdapter = new FragmentStatePagerAdapter(fm) {
                public int getCount() {
                    return ReportActivity.this.MAX_DAYS;
                }

                public Fragment getItem(int position) {
                    if (position == ReportActivity.this.MAX_DAYS - 1) {
                        return TadoReportScreenSlidePageFragment.newInstance(ReportActivity.this.getApplicationContext(), ReportActivity.this.getSelectedDate(position), position);
                    }
                    return TadoReportPremiumUpsellingSlidePageFragment.newInstance(ReportActivity.this.getApplicationContext(), ReportActivity.this.getSelectedDate(position), position);
                }
            };
        } else {
            this.startDate = DATE_NEW_REPORT_AVAILABLE;
            Object zoneCreatedTimestamp = ZoneController.INSTANCE.getCurrentZoneDateCreated();
            if (zoneCreatedTimestamp != null) {
                DateTime zoneCreated = new DateTime(zoneCreatedTimestamp);
                if (zoneCreated.isAfter(this.startDate)) {
                    this.startDate = zoneCreated;
                }
            }
            int days = Days.daysBetween(this.startDate.withTimeAtStartOfDay().toLocalDate(), this.currentDate.withTimeAtStartOfDay().plusDays(1).toLocalDate()).getDays();
            if (days == 0) {
                days = 1;
            }
            this.MAX_DAYS = days;
            this.lastPage = this.MAX_DAYS - 1;
            pageAdapter = new FragmentStatePagerAdapter(fm) {
                public int getCount() {
                    return ReportActivity.this.MAX_DAYS;
                }

                public Fragment getItem(int position) {
                    return TadoReportScreenSlidePageFragment.newInstance(ReportActivity.this.getApplicationContext(), ReportActivity.this.getSelectedDate(position), position);
                }
            };
        }
        viewPager.setAdapter(pageAdapter);
        viewPager.setPageMargin(30);
        viewPager.setBackgroundColor(-1);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setCurrentItem(this.MAX_DAYS - 1);
        viewPager.invalidate();
        betaView.setVisibility(getResources().getBoolean(C0676R.bool.beta) ? 0 : 8);
        viewPager.addOnPageChangeListener(new C10553());
        AnalyticsHelper.trackEvent(getApplication(), Screen.REPORT, "ReportPageView", Long.valueOf((long) this.pageViews));
        initTutorial();
    }

    private void initTutorial() {
        if (ZoneController.INSTANCE.getCurrentZone() != null) {
            this.mTutorialInstance = TutorialUtil.getReportTutorial(ZoneController.INSTANCE.getCurrentZone().getType(), getApplicationContext());
            if (this.mTutorialInstance != null) {
                this.mTutorialInstance.getVersion(new C10564());
            }
        }
    }

    protected void onResume() {
        super.onResume();
        AnalyticsHelper.trackPageView(getApplication(), Screen.REPORT);
        TadoApplication.getBus().register(this);
    }

    protected void onPause() {
        TadoApplication.getBus().unregister(this);
        super.onPause();
    }

    private DateTime getSelectedDate(int position) {
        return this.currentDate.minusDays((this.MAX_DAYS - 1) - position);
    }

    protected void onDestroy() {
        if (this.mTutorialInstance != null) {
            this.mTutorialInstance.release();
        }
        super.onDestroy();
        AnalyticsHelper.trackEvent(getApplication(), Screen.REPORT, "ReportPageReached", Long.valueOf((long) this.pageViews));
    }

    @Subscribe
    public void onDoneButtonPressed(String done) {
        if (done != null && done.equalsIgnoreCase("doneButtonPressed")) {
            AnalyticsHelper.trackEvent(getApplication(), Screen.REPORT, "ReportClose", "DoneButton");
            super.onBackPressed();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        AnalyticsHelper.trackEvent(getApplication(), Screen.REPORT, "ReportClose", "BackButton");
    }

    public void onDateClick(View view) {
        Calendar c = Calendar.getInstance();
        CustomViewPager viewPager = (CustomViewPager) findViewById(C0676R.id.report_pager);
        c.add(5, viewPager.getCurrentItem() - (viewPager.getAdapter().getCount() - 1));
        DatePickerDialog datePicker = DatePickerDialog.newInstance(new C10575(), c.get(1), c.get(2), c.get(5));
        Calendar start = Calendar.getInstance();
        start.setTime(new Date(this.startDate.getMillis()));
        datePicker.setMinDate(start);
        datePicker.setMaxDate(Calendar.getInstance());
        datePicker.show(getFragmentManager(), "datePicker");
    }

    public void onPremiumLearnMoreClick(View view) {
        startActivity(new Intent(this, PremiumCarouselActivity.class));
        finish();
    }
}
