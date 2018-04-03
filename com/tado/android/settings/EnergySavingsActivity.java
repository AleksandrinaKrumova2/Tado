package com.tado.android.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.tado.C0676R;
import com.tado.android.demo.DemoUtils;
import com.tado.android.demo.marketing.MarketingAlertTypeEnum;
import com.tado.android.demo.marketing.MarketingAlertsManager;
import com.tado.android.dialogs.MonthPicker;
import com.tado.android.dialogs.MonthPicker.OnDateSelected;
import com.tado.android.rest.model.HomeInfo.LicenseEnum;
import com.tado.android.utils.UserConfig;
import com.tado.android.views.webview.TadoWebView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joda.time.DateTime;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u0001!B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\u000b\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\u0018\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J(\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001dH\u0016J\u0010\u0010\u001f\u001a\u00020\b2\u0006\u0010 \u001a\u00020\u0010H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/tado/android/settings/EnergySavingsActivity;", "Landroid/support/v7/app/AppCompatActivity;", "Lcom/tado/android/dialogs/MonthPicker$OnDateSelected;", "Lcom/tado/android/settings/EnergySavingsView;", "()V", "esrPresenter", "Lcom/tado/android/settings/EnergySavingsPresenter;", "initWebView", "", "webView", "Lcom/tado/android/views/webview/TadoWebView;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDateSelected", "year", "", "month", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "setMonthPickerDate", "date", "", "showMonthPicker", "selectedYear", "selectedMonth", "maxDate", "Lorg/joda/time/DateTime;", "earliestDate", "showPage", "position", "WebviewAdapter", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: EnergySavingsActivity.kt */
public final class EnergySavingsActivity extends AppCompatActivity implements OnDateSelected, EnergySavingsView {
    private HashMap _$_findViewCache;
    private final EnergySavingsPresenter esrPresenter;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u0003H\u0016J\u0018\u0010\u000f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0003H\u0016J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\rH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0014"}, d2 = {"Lcom/tado/android/settings/EnergySavingsActivity$WebviewAdapter;", "Landroid/support/v4/view/PagerAdapter;", "numberOfMonths", "", "(Lcom/tado/android/settings/EnergySavingsActivity;I)V", "getNumberOfMonths", "()I", "destroyItem", "", "container", "Landroid/view/ViewGroup;", "position", "object", "", "getCount", "instantiateItem", "isViewFromObject", "", "view", "Landroid/view/View;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: EnergySavingsActivity.kt */
    public final class WebviewAdapter extends PagerAdapter {
        private final int numberOfMonths;

        public WebviewAdapter(int numberOfMonths) {
            this.numberOfMonths = numberOfMonths;
        }

        public final int getNumberOfMonths() {
            return this.numberOfMonths;
        }

        public boolean isViewFromObject(@NotNull View view, @NotNull Object object) {
            Intrinsics.checkParameterIsNotNull(view, Promotion.ACTION_VIEW);
            Intrinsics.checkParameterIsNotNull(object, "object");
            return Intrinsics.areEqual(view, object);
        }

        public int getCount() {
            return this.numberOfMonths;
        }

        @NotNull
        public Object instantiateItem(@NotNull ViewGroup container, int position) {
            Intrinsics.checkParameterIsNotNull(container, "container");
            Context context = container.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "container.context");
            TadoWebView webView = new TadoWebView(context);
            EnergySavingsActivity.this.initWebView(webView);
            webView.loadUrl(EnergySavingsActivity.this.esrPresenter.getUrlForPosition(position));
            container.addView(webView);
            return webView;
        }

        public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {
            Intrinsics.checkParameterIsNotNull(container, "container");
            Intrinsics.checkParameterIsNotNull(object, "object");
            container.removeView((View) object);
        }
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        view = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), view);
        return view;
    }

    public EnergySavingsActivity() {
        EnergySavingsView energySavingsView = this;
        boolean isInDemoMode = DemoUtils.isInDemoMode();
        String username = UserConfig.getUsername();
        Intrinsics.checkExpressionValueIsNotNull(username, "UserConfig.getUsername()");
        String password = UserConfig.getPassword();
        Intrinsics.checkExpressionValueIsNotNull(password, "UserConfig.getPassword()");
        int homeId = UserConfig.getHomeId();
        long homeCreation = UserConfig.getHomeCreation();
        String homeTimezone = UserConfig.getHomeTimezone();
        Intrinsics.checkExpressionValueIsNotNull(homeTimezone, "UserConfig.getHomeTimezone()");
        LicenseEnum license = UserConfig.getLicense();
        Intrinsics.checkExpressionValueIsNotNull(license, "UserConfig.getLicense()");
        this.esrPresenter = new EnergySavingsPresenter(energySavingsView, isInDemoMode, new Session(username, password, homeId, homeCreation, homeTimezone, license));
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_energy_savings);
        setSupportActionBar((Toolbar) _$_findCachedViewById(C0676R.id.toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle((CharSequence) null);
        }
        ActionBar supportActionBar2 = getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setDisplayHomeAsUpEnabled(true);
        }
        Button button = (Button) _$_findCachedViewById(C0676R.id.dropdown_month_picker);
        if (button != null) {
            button.setOnClickListener(new EnergySavingsActivity$onCreate$1(this));
        }
        EnergySavingsPresenter energySavingsPresenter = this.esrPresenter;
        DateTime minusMonths = DateTime.now().minusMonths(1);
        Intrinsics.checkExpressionValueIsNotNull(minusMonths, "DateTime.now().minusMonths(1)");
        int year = minusMonths.getYear();
        DateTime minusMonths2 = DateTime.now().minusMonths(1);
        Intrinsics.checkExpressionValueIsNotNull(minusMonths2, "DateTime.now().minusMonths(1)");
        energySavingsPresenter.showReportForDate(year, minusMonths2.getMonthOfYear());
        MarketingAlertsManager.INSTANCE.featureSeen(MarketingAlertTypeEnum.ENERGY_SAVINGS);
        ViewPager viewPager = (ViewPager) _$_findCachedViewById(C0676R.id.viewPager);
        Intrinsics.checkExpressionValueIsNotNull(viewPager, "viewPager");
        viewPager.setAdapter(new WebviewAdapter(this.esrPresenter.getNumberOfReports()));
        ((ViewPager) _$_findCachedViewById(C0676R.id.viewPager)).addOnPageChangeListener(new EnergySavingsActivity$onCreate$2(this));
        viewPager = (ViewPager) _$_findCachedViewById(C0676R.id.viewPager);
        Intrinsics.checkExpressionValueIsNotNull(viewPager, "viewPager");
        ViewPager viewPager2 = (ViewPager) _$_findCachedViewById(C0676R.id.viewPager);
        Intrinsics.checkExpressionValueIsNotNull(viewPager2, "viewPager");
        PagerAdapter adapter = viewPager2.getAdapter();
        if (adapter == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.tado.android.settings.EnergySavingsActivity.WebviewAdapter");
        }
        viewPager.setCurrentItem(((WebviewAdapter) adapter).getCount() - 1);
    }

    public void showMonthPicker(int selectedYear, int selectedMonth, @NotNull DateTime maxDate, @NotNull DateTime earliestDate) {
        Intrinsics.checkParameterIsNotNull(maxDate, "maxDate");
        Intrinsics.checkParameterIsNotNull(earliestDate, "earliestDate");
        MonthPicker dialog = MonthPicker.Companion.newInstance(selectedYear, selectedMonth);
        dialog.setMaxDate(maxDate.getYear(), maxDate.getMonthOfYear());
        dialog.setMinDate(earliestDate.getYear(), earliestDate.getMonthOfYear());
        dialog.show(getSupportFragmentManager(), "picker");
    }

    private final void initWebView(TadoWebView webView) {
        webView.initWebView(new EnergySavingsActivity$initWebView$1(this));
    }

    public boolean onOptionsItemSelected(@Nullable MenuItem item) {
        Integer valueOf = item != null ? Integer.valueOf(item.getItemId()) : null;
        if (valueOf == null || valueOf.intValue() != 16908332) {
            return super.onOptionsItemSelected(item);
        }
        Intent upIntent = NavUtils.getParentActivityIntent(this);
        if (upIntent == null) {
            return false;
        }
        Intent it = upIntent;
        if (NavUtils.shouldUpRecreateTask(this, upIntent) || isTaskRoot()) {
            TaskStackBuilder.create(this).addNextIntentWithParentStack(upIntent).startActivities();
        } else {
            NavUtils.navigateUpTo(this, upIntent);
        }
        return true;
    }

    public void onDateSelected(int year, int month) {
        this.esrPresenter.showReportForDate(year, month);
    }

    public void showPage(int position) {
        ViewPager viewPager = (ViewPager) _$_findCachedViewById(C0676R.id.viewPager);
        if (viewPager != null) {
            viewPager.setCurrentItem(position);
        }
    }

    public void setMonthPickerDate(@NotNull String date) {
        Intrinsics.checkParameterIsNotNull(date, "date");
        Button button = (Button) _$_findCachedViewById(C0676R.id.dropdown_month_picker);
        if (button != null) {
            button.setText(date);
        }
    }
}
