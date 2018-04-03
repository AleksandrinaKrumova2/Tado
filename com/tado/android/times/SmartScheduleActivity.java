package com.tado.android.times;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import com.tado.C0676R;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.app.TadoApplication;
import com.tado.android.controllers.CapabilitiesController;
import com.tado.android.controllers.ZoneController;
import com.tado.android.onboarding.OnboardingActivity;
import com.tado.android.onboarding.TutorialUtil;
import com.tado.android.onboarding.data.FeatureDataSource.LoadVersionCallback;
import com.tado.android.onboarding.data.TutorialDataSourceRepository.TutorialDataSourceRepositoryEnum;
import com.tado.android.onboarding.data.model.SmartScheduleTutorialDataSource;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.rest.service.TadoApiService;
import com.tado.android.times.view.FragmentEventBus;
import com.tado.android.times.view.interfaces.FragmentBackButtonHandler;
import com.tado.android.utils.UserConfig;
import java.util.List;

public class SmartScheduleActivity extends AppCompatActivity {
    private static final int ON_BOARDING_RESULT = 873;
    private final int SELECTED_COLOR_INDEX = 0;
    private final int UNSELECTED_COLOR_INDEX = 1;
    public FragmentEventBus bus;
    private int[] colors;
    private SmartScheduleFragmentPagerAdapter fragmentPagerAdapter;
    OnPageChangeListener onPageChangeListener = new C12044();
    private boolean showLoading;
    private TabLayout tabLayout;
    private TadoApiService tadoApiService = RestServiceGenerator.getTadoRestService();

    class C12011 implements OnClickListener {
        C12011() {
        }

        public void onClick(View v) {
            SmartScheduleActivity.this.onBackPressed();
        }
    }

    class C12022 implements OnTabSelectedListener {
        C12022() {
        }

        public void onTabSelected(Tab tab) {
            tab.setText(SmartScheduleActivity.this.fragmentPagerAdapter.getPageTitleWithColoredIcon(tab.getPosition(), SmartScheduleActivity.this.colors[0]));
        }

        public void onTabUnselected(Tab tab) {
            tab.setText(SmartScheduleActivity.this.fragmentPagerAdapter.getPageTitleWithColoredIcon(tab.getPosition(), SmartScheduleActivity.this.colors[1]));
        }

        public void onTabReselected(Tab tab) {
        }
    }

    class C12033 implements LoadVersionCallback {
        C12033() {
        }

        public void onVersionLoaded(int version) {
            if (UserConfig.getLastTutorialVersionShown(TutorialDataSourceRepositoryEnum.SMART_SCHEDULE, 0) < version) {
                UserConfig.setLastTutorialVersionShown(TutorialDataSourceRepositoryEnum.SMART_SCHEDULE, version);
                Intent intent = new Intent(SmartScheduleActivity.this, OnboardingActivity.class);
                intent.putExtra(OnboardingActivity.KEY_TITLE, SmartScheduleActivity.this.getString(C0676R.string.tutorials_smartSchedule_title));
                intent.putExtra(OnboardingActivity.KEY_TUTORIAL_DATA_SOURCE, TutorialDataSourceRepositoryEnum.SMART_SCHEDULE);
                SmartScheduleActivity.this.startActivityForResult(intent, SmartScheduleActivity.ON_BOARDING_RESULT);
            }
        }

        public void onVersionNoFeaturesToShow() {
        }

        public void onLoadingError(String msg) {
        }
    }

    class C12044 implements OnPageChangeListener {
        C12044() {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        public void onPageSelected(int position) {
            switch (position) {
                case 1:
                    AnalyticsHelper.trackPageView(SmartScheduleActivity.this.getApplication(), Screen.MODES_OVERVIEW);
                    return;
                default:
                    return;
            }
        }

        public void onPageScrollStateChanged(int state) {
        }
    }

    private class SmartScheduleFragmentPagerAdapter extends FragmentPagerAdapter {
        private int[] imageResId = new int[]{C0676R.drawable.tab_home, C0676R.drawable.tab_away};
        private String[] tabTitles = new String[]{SmartScheduleActivity.this.getString(C0676R.string.smartSchedule_homeLabel), SmartScheduleActivity.this.getString(C0676R.string.smartSchedule_awayLabel)};

        SmartScheduleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            if (position == 0) {
                return TimesFragment2.newInstance();
            }
            if (CapabilitiesController.INSTANCE.isCoolingZone()) {
                return CoolingAwayConfigurationFragment.newInstance();
            }
            if (CapabilitiesController.INSTANCE.isHeatingZone()) {
                return HeatingAwayConfigurationFragment.newInstance();
            }
            return HotWaterAwayConfigurationFragment.newInstance();
        }

        public int getCount() {
            return this.tabTitles.length;
        }

        public CharSequence getPageTitle(int position) {
            return getPageTitleWithColoredIcon(position, SmartScheduleActivity.this.colors[position]);
        }

        public CharSequence getPageTitleWithColoredIcon(int position, int color) {
            Drawable wrapped = DrawableCompat.wrap(ContextCompat.getDrawable(SmartScheduleActivity.this, this.imageResId[position]));
            DrawableCompat.setTint(wrapped, color);
            wrapped.setBounds(0, 0, wrapped.getIntrinsicWidth(), wrapped.getIntrinsicHeight());
            SpannableString sb = new SpannableString("   " + this.tabTitles[position]);
            sb.setSpan(new ImageSpan(wrapped, 0), 0, 1, 33);
            return sb;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_smart_schedule);
        Toolbar toolbar = (Toolbar) findViewById(C0676R.id.toolbar);
        toolbar.setBackgroundColor(ContextCompat.getColor(this, C0676R.color.app_bar_settings_background));
        toolbar.setTitle((int) C0676R.string.smartSchedule_title);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, C0676R.color.white));
        toolbar.setSubtitleTextColor(ContextCompat.getColor(this, C0676R.color.white));
        toolbar.setSubtitle(getAppBarTitle());
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon((int) C0676R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new C12011());
        this.colors = new int[]{ContextCompat.getColor(this, C0676R.color.tab_selected_color), ContextCompat.getColor(this, C0676R.color.tab_unselected_color)};
        ViewPager viewPager = (ViewPager) findViewById(C0676R.id.pager);
        this.fragmentPagerAdapter = new SmartScheduleFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(this.fragmentPagerAdapter);
        viewPager.addOnPageChangeListener(this.onPageChangeListener);
        this.tabLayout = (TabLayout) findViewById(C0676R.id.tabs);
        this.tabLayout.setupWithViewPager(viewPager);
        this.tabLayout.addOnTabSelectedListener(new C12022());
        AnalyticsHelper.trackPageView(getApplication(), Screen.TIMES_OVERVIEW);
        TutorialUtil.getScheduleTutorial(getApplicationContext()).getVersion(new C12033());
    }

    protected void onDestroy() {
        SmartScheduleTutorialDataSource.getInstance(getApplicationContext()).release();
        super.onDestroy();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0676R.menu.menu_smart_schedule, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0676R.id.action_settings) {
            return super.onOptionsItemSelected(item);
        }
        onMenuSettings();
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(C0676R.id.action_settings).setVisible(CapabilitiesController.INSTANCE.isHeatingZone());
        return super.onPrepareOptionsMenu(menu);
    }

    private void onMenuSettings() {
        startActivity(new Intent(this, SmartScheduleSettingsActivity.class));
    }

    public void finish() {
        super.finish();
        overridePendingTransition(C0676R.anim.slide_in_right, C0676R.anim.slide_out_right);
    }

    protected void onResume() {
        super.onResume();
        this.bus = null;
        this.bus = new FragmentEventBus();
        TadoApplication.getBus().register(this);
    }

    protected void onPause() {
        TadoApplication.getBus().unregister(this);
        super.onPause();
    }

    private String getAppBarTitle() {
        return ZoneController.INSTANCE.getZoneName(UserConfig.getCurrentZone().intValue());
    }

    public void onBackPressed() {
        if (!onFragmentBackPressed(getSupportFragmentManager())) {
            super.onBackPressed();
        }
    }

    private boolean onFragmentBackPressed(FragmentManager fragmentManager) {
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment instanceof FragmentBackButtonHandler) {
                    return ((FragmentBackButtonHandler) fragment).handleBackButton();
                }
                if (onFragmentBackPressed(fragment.getChildFragmentManager())) {
                    return true;
                }
            }
        }
        return false;
    }
}
