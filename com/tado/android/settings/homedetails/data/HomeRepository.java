package com.tado.android.settings.homedetails.data;

import android.content.Context;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.callback.presenters.GeneralErrorAlertPresenter;
import com.tado.android.rest.model.HomeDetails;
import com.tado.android.rest.model.HomeInfo;
import com.tado.android.rest.model.HomeInfo.LicenseEnum;
import com.tado.android.rest.model.HomeLicense;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.rest.service.TadoApiService;
import com.tado.android.settings.interfaces.GenericCallbackListener;
import com.tado.android.utils.UserConfig;
import java.util.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0010\u0010\f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010\rJ\u0014\u0010\u000f\u001a\u00020\t2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011J&\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\u000b2\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rJ \u0010\u0016\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0010\u0010\f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010\rR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0017"}, d2 = {"Lcom/tado/android/settings/homedetails/data/HomeRepository;", "Ljava/util/Observable;", "apiService", "Lcom/tado/android/rest/service/TadoApiService;", "(Lcom/tado/android/rest/service/TadoApiService;)V", "getApiService", "()Lcom/tado/android/rest/service/TadoApiService;", "setApiService", "cancelPremiumSubscription", "", "context", "Landroid/content/Context;", "listener", "Lcom/tado/android/settings/interfaces/GenericCallbackListener;", "Ljava/lang/Void;", "requestHomeDetails", "callback", "Lcom/tado/android/rest/callback/TadoCallback;", "Lcom/tado/android/rest/model/HomeInfo;", "saveHomeDetails", "homeDetails", "Lcom/tado/android/rest/model/HomeDetails;", "upgradeToPremium", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: HomeRepository.kt */
public final class HomeRepository extends Observable {
    @NotNull
    private TadoApiService apiService;

    public HomeRepository(@NotNull TadoApiService apiService) {
        Intrinsics.checkParameterIsNotNull(apiService, "apiService");
        this.apiService = apiService;
    }

    @NotNull
    public final TadoApiService getApiService() {
        return this.apiService;
    }

    public final void setApiService(@NotNull TadoApiService <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.apiService = <set-?>;
    }

    public final void requestHomeDetails(@NotNull TadoCallback<HomeInfo> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.apiService.getHome(UserConfig.getHomeId(), RestServiceGenerator.getCredentialsMap()).enqueue(callback);
    }

    public final void saveHomeDetails(@NotNull HomeDetails homeDetails, @NotNull Context context, @NotNull GenericCallbackListener<Void> listener) {
        Intrinsics.checkParameterIsNotNull(homeDetails, "homeDetails");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.apiService.putHomeDetails(UserConfig.getHomeId(), RestServiceGenerator.getCredentialsMap(), homeDetails).enqueue(new HomeRepository$saveHomeDetails$1(context, listener, new GeneralErrorAlertPresenter(context), listener));
    }

    public final void cancelPremiumSubscription(@NotNull Context context, @Nullable GenericCallbackListener<Void> listener) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.apiService.putHomeLicense(UserConfig.getHomeId(), RestServiceGenerator.getCredentialsMap(), new HomeLicense(LicenseEnum.NON_PREMIUM)).enqueue(new HomeRepository$cancelPremiumSubscription$1(context, listener, new GeneralErrorAlertPresenter(context), listener));
    }

    public final void upgradeToPremium(@NotNull Context context, @Nullable GenericCallbackListener<Void> listener) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.apiService.putHomeLicense(UserConfig.getHomeId(), RestServiceGenerator.getCredentialsMap(), new HomeLicense(LicenseEnum.PREMIUM)).enqueue(new HomeRepository$upgradeToPremium$1(context, listener, new GeneralErrorAlertPresenter(context), listener));
    }
}
