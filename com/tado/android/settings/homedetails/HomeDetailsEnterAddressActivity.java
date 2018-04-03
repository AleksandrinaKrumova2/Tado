package com.tado.android.settings.homedetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.google.android.gms.maps.model.LatLng;
import com.tado.C0676R;
import com.tado.android.entities.Address;
import com.tado.android.installation.CreateHomeContactDetailsActivity;
import com.tado.android.installation.CreateHomeEnterAddressActivity;
import com.tado.android.rest.model.ContactDetails;
import com.tado.android.rest.model.HomeDetails;
import com.tado.android.rest.model.HomeInfo;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.rest.service.TadoApiService;
import com.tado.android.settings.homedetails.data.HomeRepository;
import com.tado.android.views.progressbar.ProgressBarComponent;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0003\u0018\u0000 $2\u00020\u0001:\u0001$B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0019H\u0016JJ\u0010\u001a\u001a\u00020\u00102\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001c2\b\u0010 \u001a\u0004\u0018\u00010\u001c2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0014R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/tado/android/settings/homedetails/HomeDetailsEnterAddressActivity;", "Lcom/tado/android/installation/CreateHomeEnterAddressActivity;", "()V", "homeInfo", "Lcom/tado/android/rest/model/HomeInfo;", "loadingPresenter", "Lcom/tado/android/settings/homedetails/AbstractLoadingPresenter;", "progressBar", "Lcom/tado/android/views/progressbar/ProgressBarComponent;", "getProgressBar$4_9_3_1500409030_tadoRelease", "()Lcom/tado/android/views/progressbar/ProgressBarComponent;", "setProgressBar$4_9_3_1500409030_tadoRelease", "(Lcom/tado/android/views/progressbar/ProgressBarComponent;)V", "rootView", "Landroid/view/View;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "proceed", "addressLine1", "", "addressLine2", "zipCode", "city", "country", "latitude", "", "longitude", "Companion", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: HomeDetailsEnterAddressActivity.kt */
public final class HomeDetailsEnterAddressActivity extends CreateHomeEnterAddressActivity {
    public static final Companion Companion = new Companion();
    private static final String INTENT_HOME_INFO = INTENT_HOME_INFO;
    private HashMap _$_findViewCache;
    private HomeInfo homeInfo;
    private AbstractLoadingPresenter loadingPresenter;
    @Nullable
    private ProgressBarComponent progressBar;
    private View rootView;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/tado/android/settings/homedetails/HomeDetailsEnterAddressActivity$Companion;", "", "()V", "INTENT_HOME_INFO", "", "getINTENT_HOME_INFO", "()Ljava/lang/String;", "newIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "homeInfo", "Lcom/tado/android/rest/model/HomeInfo;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: HomeDetailsEnterAddressActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        private final String getINTENT_HOME_INFO() {
            return HomeDetailsEnterAddressActivity.INTENT_HOME_INFO;
        }

        @NotNull
        public final Intent newIntent(@NotNull Context context, @NotNull HomeInfo homeInfo) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(homeInfo, HomeDetailsEnterAddressActivity.INTENT_HOME_INFO);
            Intent intent = new Intent(context, HomeDetailsEnterAddressActivity.class);
            Bundle $receiver = new Bundle();
            $receiver.putParcelable(HomeDetailsEnterAddressActivity.Companion.getINTENT_HOME_INFO(), homeInfo);
            String str = CreateHomeContactDetailsActivity.INTENT_ADDRESS1;
            Address address = homeInfo.getAddress();
            Intrinsics.checkExpressionValueIsNotNull(address, "homeInfo.address");
            $receiver.putString(str, address.getAddressLine1());
            str = CreateHomeContactDetailsActivity.INTENT_ADDRESS2;
            address = homeInfo.getAddress();
            Intrinsics.checkExpressionValueIsNotNull(address, "homeInfo.address");
            $receiver.putString(str, address.getAddressLine2());
            address = homeInfo.getAddress();
            Intrinsics.checkExpressionValueIsNotNull(address, "homeInfo.address");
            $receiver.putString("zipCode", address.getZipCode());
            str = CreateHomeContactDetailsActivity.INTENT_CITY;
            address = homeInfo.getAddress();
            Intrinsics.checkExpressionValueIsNotNull(address, "homeInfo.address");
            $receiver.putString(str, address.getCity());
            str = CreateHomeContactDetailsActivity.INTENT_COUNTRY;
            address = homeInfo.getAddress();
            Intrinsics.checkExpressionValueIsNotNull(address, "homeInfo.address");
            $receiver.putString(str, address.getCountry());
            intent.putExtras($receiver);
            return intent;
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

    @Nullable
    public final ProgressBarComponent getProgressBar$4_9_3_1500409030_tadoRelease() {
        return this.progressBar;
    }

    public final void setProgressBar$4_9_3_1500409030_tadoRelease(@Nullable ProgressBarComponent <set-?>) {
        this.progressBar = <set-?>;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        HomeInfo homeInfo;
        super.onCreate(savedInstanceState);
        this.progressBar = (ProgressBarComponent) findViewById(C0676R.id.progressBar);
        this.rootView = findViewById(C0676R.id.rootLayout);
        this.loadingPresenter = new LoadingBarPresenter(this.progressBar, this.rootView);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle((CharSequence) getString(C0676R.string.components_homeDetails_homeAddressLabel));
        }
        ActionBar supportActionBar2 = getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setDisplayHomeAsUpEnabled(true);
        }
        initTexts(null, null, null);
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                homeInfo = (HomeInfo) extras.getParcelable(Companion.getINTENT_HOME_INFO());
                this.homeInfo = homeInfo;
            }
        }
        homeInfo = null;
        this.homeInfo = homeInfo;
    }

    public boolean onCreateOptionsMenu(@NotNull Menu menu) {
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        getMenuInflater().inflate(C0676R.menu.menu_action_save, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        switch (item.getItemId()) {
            case 16908332:
                onBackPressed();
                break;
            case C0676R.id.action_save:
                proceedClick(null);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void proceed(@Nullable String addressLine1, @Nullable String addressLine2, @Nullable String zipCode, @Nullable String city, @Nullable String country, double latitude, double longitude) {
        Address $receiver = new Address();
        $receiver.setAddressLine1(addressLine1);
        $receiver.setAddressLine2(addressLine2);
        $receiver.setZipCode(zipCode);
        $receiver.setCity(city);
        $receiver.setCountry(country);
        Address address = $receiver;
        AbstractLoadingPresenter abstractLoadingPresenter = this.loadingPresenter;
        if (abstractLoadingPresenter != null) {
            abstractLoadingPresenter.startLoading();
        }
        TadoApiService tadoRestService = RestServiceGenerator.getTadoRestService();
        Intrinsics.checkExpressionValueIsNotNull(tadoRestService, "RestServiceGenerator.getTadoRestService()");
        HomeRepository homeRepository = new HomeRepository(tadoRestService);
        HomeInfo homeInfo = this.homeInfo;
        if (homeInfo == null) {
            Intrinsics.throwNpe();
        }
        String name = homeInfo.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "homeInfo!!.name");
        LatLng latLng = new LatLng(latitude, longitude);
        HomeInfo homeInfo2 = this.homeInfo;
        if (homeInfo2 == null) {
            Intrinsics.throwNpe();
        }
        ContactDetails contactDetails = homeInfo2.getContactDetails();
        Intrinsics.checkExpressionValueIsNotNull(contactDetails, "homeInfo!!.contactDetails");
        homeRepository.saveHomeDetails(new HomeDetails(name, latLng, contactDetails, address), this, new HomeDetailsEnterAddressActivity$proceed$1(this));
    }
}
