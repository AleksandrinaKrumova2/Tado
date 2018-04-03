package com.tado.android.settings.homedetails;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v14.preference.PreferenceFragment;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceScreen;
import android.view.View;
import com.google.android.gms.maps.model.LatLng;
import com.tado.C0676R;
import com.tado.android.entities.Address;
import com.tado.android.premium.PremiumCarouselActivity;
import com.tado.android.rest.callback.presenters.ErrorAlertWithBackOnCancelPresenter;
import com.tado.android.rest.model.ContactDetails;
import com.tado.android.rest.model.HomeDetails;
import com.tado.android.rest.model.HomeInfo;
import com.tado.android.rest.model.HomeInfo.LicenseEnum;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.rest.service.TadoApiService;
import com.tado.android.settings.homedetails.data.HomeRepository;
import com.tado.android.utils.UserConfig;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0002J\b\u0010\b\u001a\u00020\u0006H\u0002J\b\u0010\t\u001a\u00020\u0006H\u0002J\b\u0010\n\u001a\u00020\u0006H\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\u0006H\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u000fH\u0002J\u001c\u0010\u0011\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u000fH\u0016J\u0010\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0015H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/tado/android/settings/homedetails/HomeDetailsPreferenceFragment;", "Landroid/support/v14/preference/PreferenceFragment;", "()V", "homeInfo", "Lcom/tado/android/rest/model/HomeInfo;", "homeNamePreference", "Landroid/support/v7/preference/Preference;", "createCancelSubscriptionPreference", "createContactDetailsPreference", "createEditTextPreference", "createHomeAddressPreference", "createPremiumCategory", "Landroid/support/v7/preference/PreferenceCategory;", "createUpgradeToPremiumPreference", "initPreferences", "", "loadData", "onCreatePreferences", "savedInstanceState", "Landroid/os/Bundle;", "rootKey", "", "onResume", "setHomeName", "newName", "Companion", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: HomeDetailsPreferenceFragment.kt */
public final class HomeDetailsPreferenceFragment extends PreferenceFragment {
    public static final Companion Companion = new Companion();
    private HashMap _$_findViewCache;
    private HomeInfo homeInfo;
    private Preference homeNamePreference;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/tado/android/settings/homedetails/HomeDetailsPreferenceFragment$Companion;", "", "()V", "instance", "Lcom/tado/android/settings/homedetails/HomeDetailsPreferenceFragment;", "getInstance", "()Lcom/tado/android/settings/homedetails/HomeDetailsPreferenceFragment;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: HomeDetailsPreferenceFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final HomeDetailsPreferenceFragment getInstance() {
            return new HomeDetailsPreferenceFragment();
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
        view = getView();
        if (view == null) {
            return null;
        }
        view = view.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), view);
        return view;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
    }

    private final void loadData() {
        TadoApiService tadoRestService = RestServiceGenerator.getTadoRestService();
        Intrinsics.checkExpressionValueIsNotNull(tadoRestService, "RestServiceGenerator.getTadoRestService()");
        new HomeRepository(tadoRestService).requestHomeDetails(new HomeDetailsPreferenceFragment$loadData$1(this, new ErrorAlertWithBackOnCancelPresenter(getActivity()), new HomeDetailsPreferenceFragment$loadData$2(this)));
    }

    private final void initPreferences() {
        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(getActivity());
        this.homeNamePreference = createEditTextPreference();
        screen.addPreference(this.homeNamePreference);
        screen.addPreference(createHomeAddressPreference());
        screen.addPreference(createContactDetailsPreference());
        LicenseEnum license = UserConfig.getLicense();
        if (Intrinsics.areEqual(license, LicenseEnum.PREMIUM) || Intrinsics.areEqual(license, LicenseEnum.NON_PREMIUM)) {
            PreferenceCategory premiumCategory = createPremiumCategory();
            screen.addPreference(premiumCategory);
            if (license != null) {
                switch (license) {
                    case NON_PREMIUM:
                        premiumCategory.addPreference(createUpgradeToPremiumPreference());
                        break;
                    case PREMIUM:
                        premiumCategory.addPreference(createCancelSubscriptionPreference());
                        break;
                    default:
                        break;
                }
            }
        }
        setPreferenceScreen(screen);
    }

    public void onResume() {
        super.onResume();
        loadData();
    }

    private final Preference createEditTextPreference() {
        EditTextPreference homeNameEditTextPreference = new EditTextPreference(getActivity());
        homeNameEditTextPreference.setTitle((int) C0676R.string.components_homeDetails_homeNameLabel);
        homeNameEditTextPreference.setPersistent(false);
        HomeInfo homeInfo = this.homeInfo;
        homeNameEditTextPreference.setKey(String.valueOf(homeInfo != null ? Integer.valueOf(homeInfo.getId()) : null));
        homeNameEditTextPreference.setDialogLayoutResource(C0676R.layout.dialog_edit_text_preference);
        if (this.homeInfo != null) {
            homeInfo = this.homeInfo;
            if (homeInfo == null) {
                Intrinsics.throwNpe();
            }
            if (homeInfo.getName() != null) {
                homeInfo = this.homeInfo;
                if (homeInfo == null) {
                    Intrinsics.throwNpe();
                }
                homeNameEditTextPreference.setDefaultValue(homeInfo.getName());
                homeInfo = this.homeInfo;
                if (homeInfo == null) {
                    Intrinsics.throwNpe();
                }
                homeNameEditTextPreference.setSummary((CharSequence) homeInfo.getName());
            }
        }
        homeNameEditTextPreference.setOnPreferenceChangeListener(new HomeDetailsPreferenceFragment$createEditTextPreference$1(this));
        return homeNameEditTextPreference;
    }

    private final Preference createHomeAddressPreference() {
        Preference addressPreference = new Preference(getActivity());
        addressPreference.setTitle((int) C0676R.string.components_homeDetails_homeAddressLabel);
        com.tado.android.settings.homedetails.HomeDetailsEnterAddressActivity.Companion companion = HomeDetailsEnterAddressActivity.Companion;
        Activity activity = getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity");
        Context context = activity;
        HomeInfo homeInfo = this.homeInfo;
        if (homeInfo == null) {
            Intrinsics.throwNpe();
        }
        addressPreference.setIntent(companion.newIntent(context, homeInfo));
        addressPreference.setPersistent(false);
        return addressPreference;
    }

    private final Preference createContactDetailsPreference() {
        Preference contactDetailsPreference = new Preference(getActivity());
        contactDetailsPreference.setTitle((int) C0676R.string.components_homeDetails_contactDetailsLabel);
        com.tado.android.settings.homedetails.HomeDetailsContactDetails.Companion companion = HomeDetailsContactDetails.Companion;
        Activity activity = getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity");
        Context context = activity;
        HomeInfo homeInfo = this.homeInfo;
        if (homeInfo == null) {
            Intrinsics.throwNpe();
        }
        contactDetailsPreference.setIntent(companion.newIntent(context, homeInfo));
        contactDetailsPreference.setPersistent(false);
        return contactDetailsPreference;
    }

    private final PreferenceCategory createPremiumCategory() {
        PreferenceCategory premiumCategory = new PreferenceCategory(getActivity());
        premiumCategory.setKey("preference_premium_category");
        premiumCategory.setTitle((int) C0676R.string.premiumUpgrade_subscriptionDetails_premiumSubscriptionCategoryLabel);
        return premiumCategory;
    }

    private final Preference createCancelSubscriptionPreference() {
        Preference cancelSubscriptionPreference = new Preference(getActivity());
        cancelSubscriptionPreference.setTitle((int) C0676R.string.premiumUpgrade_subscriptionDetails_cancelButton);
        cancelSubscriptionPreference.setLayoutResource(C0676R.layout.sign_out_layout);
        cancelSubscriptionPreference.setPersistent(false);
        cancelSubscriptionPreference.setOnPreferenceClickListener(new C1115x55f58e11(this, cancelSubscriptionPreference));
        return cancelSubscriptionPreference;
    }

    private final Preference createUpgradeToPremiumPreference() {
        Preference upgradeToPremiumPreference = new Preference(getActivity());
        upgradeToPremiumPreference.setTitle((int) C0676R.string.premiumUpgrade_subscriptionDetails_upgradeButton);
        upgradeToPremiumPreference.setPersistent(false);
        upgradeToPremiumPreference.setIntent(new Intent(getActivity(), PremiumCarouselActivity.class));
        return upgradeToPremiumPreference;
    }

    private final void setHomeName(String newName) {
        HomeInfo homeInfo = this.homeInfo;
        if (homeInfo == null) {
            Intrinsics.throwNpe();
        }
        homeInfo.setName(newName);
        homeInfo = this.homeInfo;
        if (homeInfo == null) {
            Intrinsics.throwNpe();
        }
        LatLng geolocation = homeInfo.getGeolocation();
        Intrinsics.checkExpressionValueIsNotNull(geolocation, "homeInfo!!.geolocation");
        HomeInfo homeInfo2 = this.homeInfo;
        if (homeInfo2 == null) {
            Intrinsics.throwNpe();
        }
        ContactDetails contactDetails = homeInfo2.getContactDetails();
        Intrinsics.checkExpressionValueIsNotNull(contactDetails, "homeInfo!!.contactDetails");
        HomeInfo homeInfo3 = this.homeInfo;
        if (homeInfo3 == null) {
            Intrinsics.throwNpe();
        }
        Address address = homeInfo3.getAddress();
        Intrinsics.checkExpressionValueIsNotNull(address, "homeInfo!!.address");
        HomeDetails homeDetails = new HomeDetails(newName, geolocation, contactDetails, address);
        TadoApiService tadoRestService = RestServiceGenerator.getTadoRestService();
        Intrinsics.checkExpressionValueIsNotNull(tadoRestService, "RestServiceGenerator.getTadoRestService()");
        HomeRepository homeRepository = new HomeRepository(tadoRestService);
        Activity activity = getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity");
        homeRepository.saveHomeDetails(homeDetails, activity, new HomeDetailsPreferenceFragment$setHomeName$1(this, newName));
    }
}
