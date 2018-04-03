package com.tado.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.adapters.PromoPagerAdapter;
import com.tado.android.analytics.AnalyticsConstants.Events;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.controllers.NavigationController;
import com.tado.android.demo.DemoConfig;
import com.tado.android.installation.CreateAccountActivity;
import com.tado.android.login.LoginActivity;

public class PromoActivity extends AppCompatActivity {
    private int currentPage;
    public boolean firstStart = true;
    private int numPages;

    class C06911 extends SimpleOnPageChangeListener {
        C06911() {
        }

        public void onPageSelected(int position) {
            PromoActivity.this.currentPage = position % PromoActivity.this.numPages;
            AnalyticsHelper.trackPageView(PromoActivity.this.getApplication(), Screen.PROMO_PAGES[PromoActivity.this.currentPage]);
            PageTransformerFragment.doTransformation(PromoActivity.this.currentPage, PromoActivity.this);
        }
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_promo);
        ViewPager mPager = (ViewPager) findViewById(C0676R.id.pager);
        mPager.setAdapter(new PromoPagerAdapter(getSupportFragmentManager(), getResources().getInteger(C0676R.integer.promo_page_count)));
        mPager.setCurrentItem(90);
        this.numPages = getResources().getInteger(C0676R.integer.promo_page_count);
        mPager.addOnPageChangeListener(new C06911());
    }

    public void logIn(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void createAccount(View view) {
        startActivity(new Intent(this, CreateAccountActivity.class));
    }

    public void enterDemoMode(View view) {
        AnalyticsHelper.trackEvent((Activity) this, Events.DEMO, "startDemo", Integer.toString(DemoConfig.getStartCounter()));
        NavigationController.enterDemoMode(this);
    }

    public void getTadoHeating(View view) {
        AnalyticsHelper.trackEvent(getApplication(), "Promo", "GetTado", "Page" + (this.currentPage + 1));
        Intent i = new Intent("android.intent.action.VIEW");
        i.setData(Uri.parse(getString(C0676R.string.appLandingScreens_heatingMoodScreen_getTadoButtonURL)));
        startActivity(i);
    }

    public void getTadoCooling(View view) {
        AnalyticsHelper.trackEvent(getApplication(), "Promo", "GetTado", "Page" + (this.currentPage + 1));
        Intent i = new Intent("android.intent.action.VIEW");
        i.setData(Uri.parse(getString(C0676R.string.appLandingScreens_airconditioningMoodScreen_getTadoButtonURL)));
        startActivity(i);
    }
}
