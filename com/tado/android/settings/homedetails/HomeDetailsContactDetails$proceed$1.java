package com.tado.android.settings.homedetails;

import com.tado.android.settings.homedetails.AbstractLoadingPresenter.DefaultImpls;
import com.tado.android.settings.interfaces.GenericCallbackListener;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\b"}, d2 = {"com/tado/android/settings/homedetails/HomeDetailsContactDetails$proceed$1", "Lcom/tado/android/settings/interfaces/GenericCallbackListener;", "Ljava/lang/Void;", "(Lcom/tado/android/settings/homedetails/HomeDetailsContactDetails;)V", "onFailure", "", "onSuccess", "body", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: HomeDetailsContactDetails.kt */
public final class HomeDetailsContactDetails$proceed$1 implements GenericCallbackListener<Void> {
    final /* synthetic */ HomeDetailsContactDetails this$0;

    HomeDetailsContactDetails$proceed$1(HomeDetailsContactDetails $outer) {
        this.this$0 = $outer;
    }

    public void onSuccess(@Nullable Void body) {
        this.this$0.onBackPressed();
        AbstractLoadingPresenter access$getLoadingPresenter$p = this.this$0.loadingPresenter;
        if (access$getLoadingPresenter$p != null) {
            access$getLoadingPresenter$p.stopLoading(false);
        }
    }

    public void onFailure() {
        AbstractLoadingPresenter access$getLoadingPresenter$p = this.this$0.loadingPresenter;
        if (access$getLoadingPresenter$p != null) {
            DefaultImpls.stopLoading$default(access$getLoadingPresenter$p, false, 1, null);
        }
    }
}
