package com.tado.android.settings.homedetails;

import com.tado.android.entities.Address;
import com.tado.android.rest.model.HomeInfo;
import com.tado.android.settings.homedetails.AbstractLoadingPresenter.DefaultImpls;
import com.tado.android.settings.interfaces.GenericCallbackListener;
import com.tado.android.utils.UserConfig;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\b"}, d2 = {"com/tado/android/settings/homedetails/HomeDetailsEnterAddressActivity$proceed$1", "Lcom/tado/android/settings/interfaces/GenericCallbackListener;", "Ljava/lang/Void;", "(Lcom/tado/android/settings/homedetails/HomeDetailsEnterAddressActivity;)V", "onFailure", "", "onSuccess", "body", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: HomeDetailsEnterAddressActivity.kt */
public final class HomeDetailsEnterAddressActivity$proceed$1 implements GenericCallbackListener<Void> {
    final /* synthetic */ HomeDetailsEnterAddressActivity this$0;

    HomeDetailsEnterAddressActivity$proceed$1(HomeDetailsEnterAddressActivity $outer) {
        this.this$0 = $outer;
    }

    public void onSuccess(@Nullable Void body) {
        try {
            HomeInfo access$getHomeInfo$p = this.this$0.homeInfo;
            if (access$getHomeInfo$p != null) {
                Address address = access$getHomeInfo$p.getAddress();
                if (address != null) {
                    String it = address.getCountry();
                    if (it != null) {
                        UserConfig.setHomeCountry(it);
                    }
                }
            }
            AbstractLoadingPresenter access$getLoadingPresenter$p = this.this$0.loadingPresenter;
            if (access$getLoadingPresenter$p != null) {
                access$getLoadingPresenter$p.stopLoading(false);
            }
            this.this$0.onBackPressed();
        } catch (IllegalStateException e) {
        }
    }

    public void onFailure() {
        AbstractLoadingPresenter access$getLoadingPresenter$p = this.this$0.loadingPresenter;
        if (access$getLoadingPresenter$p != null) {
            DefaultImpls.stopLoading$default(access$getLoadingPresenter$p, false, 1, null);
        }
    }
}
