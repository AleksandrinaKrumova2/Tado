package com.tado.android.settings.homedetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.android.gms.maps.model.LatLng;
import com.tado.C0676R;
import com.tado.android.entities.Address;
import com.tado.android.installation.CreateHomeContactDetailsActivity;
import com.tado.android.rest.model.ContactDetails;
import com.tado.android.rest.model.HomeDetails;
import com.tado.android.rest.model.HomeInfo;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.rest.service.TadoApiService;
import com.tado.android.settings.homedetails.data.HomeRepository;
import com.tado.android.views.progressbar.ProgressBarComponent;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J&\u0010\u0012\u001a\u00020\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u0014H\u0014R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/tado/android/settings/homedetails/HomeDetailsContactDetails;", "Lcom/tado/android/installation/CreateHomeContactDetailsActivity;", "()V", "homeInfo", "Lcom/tado/android/rest/model/HomeInfo;", "loadingPresenter", "Lcom/tado/android/settings/homedetails/AbstractLoadingPresenter;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "proceed", "nameString", "", "phoneString", "mailString", "Companion", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: HomeDetailsContactDetails.kt */
public final class HomeDetailsContactDetails extends CreateHomeContactDetailsActivity {
    public static final Companion Companion = new Companion();
    private static final String INTENT_HOME_INFO = INTENT_HOME_INFO;
    private HashMap _$_findViewCache;
    private HomeInfo homeInfo;
    private AbstractLoadingPresenter loadingPresenter;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/tado/android/settings/homedetails/HomeDetailsContactDetails$Companion;", "", "()V", "INTENT_HOME_INFO", "", "getINTENT_HOME_INFO", "()Ljava/lang/String;", "newIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "homeInfo", "Lcom/tado/android/rest/model/HomeInfo;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: HomeDetailsContactDetails.kt */
    public static final class Companion {
        private Companion() {
        }

        private final String getINTENT_HOME_INFO() {
            return HomeDetailsContactDetails.INTENT_HOME_INFO;
        }

        @NotNull
        public final Intent newIntent(@NotNull Context context, @NotNull HomeInfo homeInfo) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(homeInfo, HomeDetailsContactDetails.INTENT_HOME_INFO);
            Intent intent = new Intent(context, HomeDetailsContactDetails.class);
            Bundle $receiver = new Bundle();
            $receiver.putParcelable(HomeDetailsContactDetails.Companion.getINTENT_HOME_INFO(), homeInfo);
            $receiver.putString(CreateHomeContactDetailsActivity.INTENT_NAME, homeInfo.getContactDetails().getName());
            $receiver.putString("email", homeInfo.getContactDetails().getEmail());
            $receiver.putString(CreateHomeContactDetailsActivity.INTENT_PHONE, homeInfo.getContactDetails().getPhone());
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

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View findViewById = findViewById(C0676R.id.toolbar);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById<Toolbar>(R.id.toolbar)");
        ((Toolbar) findViewById).setVisibility(8);
        findViewById = findViewById(C0676R.id.proceed_button);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.Button");
        }
        ((Button) findViewById).setVisibility(8);
        this.loadingPresenter = new LoadingBarPresenter((ProgressBarComponent) findViewById(C0676R.id.progressBar), (ViewGroup) findViewById(C0676R.id.rootLayout));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(supportActionBar, "supportActionBar!!");
        supportActionBar.setTitle((CharSequence) getString(C0676R.string.components_homeDetails_contactDetailsLabel));
        ActionBar supportActionBar2 = getSupportActionBar();
        if (supportActionBar2 == null) {
            Intrinsics.throwNpe();
        }
        supportActionBar2.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, "this.intent");
        Bundle b = intent.getExtras();
        if (b != null) {
            this.homeInfo = (HomeInfo) b.getParcelable(Companion.getINTENT_HOME_INFO());
        }
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

    protected void proceed(@Nullable String nameString, @Nullable String phoneString, @Nullable String mailString) {
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
        HomeInfo homeInfo2 = this.homeInfo;
        if (homeInfo2 == null) {
            Intrinsics.throwNpe();
        }
        LatLng geolocation = homeInfo2.getGeolocation();
        Intrinsics.checkExpressionValueIsNotNull(geolocation, "homeInfo!!.geolocation");
        if (nameString == null) {
            Intrinsics.throwNpe();
        }
        if (phoneString == null) {
            Intrinsics.throwNpe();
        }
        if (mailString == null) {
            Intrinsics.throwNpe();
        }
        ContactDetails contactDetails = new ContactDetails(nameString, mailString, phoneString);
        HomeInfo homeInfo3 = this.homeInfo;
        if (homeInfo3 == null) {
            Intrinsics.throwNpe();
        }
        Address address = homeInfo3.getAddress();
        Intrinsics.checkExpressionValueIsNotNull(address, "homeInfo!!.address");
        homeRepository.saveHomeDetails(new HomeDetails(name, geolocation, contactDetails, address), this, new HomeDetailsContactDetails$proceed$1(this));
    }
}
