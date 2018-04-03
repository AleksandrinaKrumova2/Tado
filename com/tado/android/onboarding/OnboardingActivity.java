package com.tado.android.onboarding;

import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.onboarding.OnboardingPresenter.View;
import com.tado.android.onboarding.data.TutorialDataSourceRepository;
import com.tado.android.onboarding.data.TutorialDataSourceRepository.TutorialDataSourceRepositoryEnum;
import com.tado.android.onboarding.data.model.FeaturesOrder;
import java.util.Locale;
import me.relex.circleindicator.CircleIndicator;

public class OnboardingActivity extends AppCompatActivity implements View {
    private static final String KEY_PAGE = "keyPage";
    public static final String KEY_TITLE = "keyTitle";
    public static final String KEY_TUTORIAL_DATA_SOURCE = "keyTutorialDataSource";
    private FeaturesCarouselPagerAdapter adapter;
    private Button continueButton;
    private OnClickListener onNextClick = new C10392();
    private OnPageChangeListener onPageChanged = new C10403();
    private OnClickListener onSkipClick = new C10381();
    private OnboardingPresenter onboardingPresenter;
    private Button skipButton;
    private TextView titleView;
    private ViewPager viewPager;

    class C10381 implements OnClickListener {
        C10381() {
        }

        public void onClick(android.view.View v) {
            OnboardingActivity.this.skipOnboarding();
            OnboardingActivity.this.finish();
        }
    }

    class C10392 implements OnClickListener {
        C10392() {
        }

        public void onClick(android.view.View v) {
            OnboardingActivity.this.viewPager.setCurrentItem((OnboardingActivity.this.viewPager.getCurrentItem() + 1) % OnboardingActivity.this.viewPager.getAdapter().getCount());
        }
    }

    class C10403 implements OnPageChangeListener {
        C10403() {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        public void onPageSelected(int position) {
            if (position == OnboardingActivity.this.adapter.getCount() - 1) {
                OnboardingActivity.this.showFinishButton();
            }
        }

        public void onPageScrollStateChanged(int state) {
        }
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_onboarding);
        AnalyticsHelper.trackPageView(getApplication(), Screen.FEATURE_INTRO);
        this.titleView = (TextView) findViewById(C0676R.id.title);
        this.skipButton = (Button) findViewById(C0676R.id.button_skip);
        if (this.skipButton != null) {
            this.skipButton.setOnClickListener(this.onSkipClick);
        }
        this.continueButton = (Button) findViewById(C0676R.id.button_continue);
        if (this.continueButton != null) {
            this.continueButton.setOnClickListener(this.onNextClick);
            if (this.continueButton.getCompoundDrawables()[0] != null) {
                this.continueButton.getCompoundDrawables()[0].mutate().setColorFilter(ContextCompat.getColor(this, C0676R.color.ac_home), Mode.SRC_ATOP);
            }
        }
        this.viewPager = (ViewPager) findViewById(C0676R.id.viewPager);
        if (getIntent().hasExtra(KEY_TUTORIAL_DATA_SOURCE)) {
            TutorialDataSourceRepositoryEnum dataSourceRepositoryEnum = (TutorialDataSourceRepositoryEnum) getIntent().getSerializableExtra(KEY_TUTORIAL_DATA_SOURCE);
            this.adapter = new FeaturesCarouselPagerAdapter(getSupportFragmentManager(), dataSourceRepositoryEnum);
            this.viewPager.addOnPageChangeListener(this.onPageChanged);
            this.onboardingPresenter = new OnboardingPresenter(this, TutorialDataSourceRepository.getDataSource(dataSourceRepositoryEnum, this, Locale.getDefault()));
            this.onboardingPresenter.loadFeatures();
        } else {
            finish();
        }
        if (savedInstanceState != null) {
            this.viewPager.setCurrentItem(savedInstanceState.getInt(KEY_PAGE, 0));
        }
        if (getIntent().hasExtra(KEY_TITLE)) {
            this.titleView.setText(getIntent().getStringExtra(KEY_TITLE));
        }
    }

    public void setPages(FeaturesOrder order) {
        this.adapter.setOrder(order);
        this.viewPager.setAdapter(this.adapter);
        CircleIndicator viewPagerIndicator = (CircleIndicator) findViewById(C0676R.id.viewPagerIndicator);
        if (order.getFeatureOrder().size() > 1) {
            viewPagerIndicator.setViewPager(this.viewPager);
        } else {
            showFinishButton();
        }
    }

    public void showFinishButton() {
        this.continueButton.setText(C0676R.string.components_featureIntro_gotItButton);
        this.continueButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        this.continueButton.setOnClickListener(this.onSkipClick);
        this.skipButton.setVisibility(4);
    }

    public void showErrorMessage(String msg) {
        finish();
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_PAGE, this.viewPager.getCurrentItem());
    }

    public void onBackPressed() {
        skipOnboarding();
        super.onBackPressed();
    }

    private void skipOnboarding() {
        this.onboardingPresenter.onSkip();
        AnalyticsHelper.trackEvent(getApplication(), Screen.FEATURE_INTRO, "LastPage", this.skipButton.getVisibility() == 0 ? "Skip" : "true");
    }
}
