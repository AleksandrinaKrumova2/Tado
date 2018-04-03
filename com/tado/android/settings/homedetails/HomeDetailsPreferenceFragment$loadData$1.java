package com.tado.android.settings.homedetails;

import com.tado.android.rest.callback.RetryCallback;
import com.tado.android.rest.callback.presenters.PresenterDelegate;
import com.tado.android.rest.model.HomeInfo;
import com.tado.android.settings.interfaces.GenericCallbackListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"com/tado/android/settings/homedetails/HomeDetailsPreferenceFragment$loadData$1", "Lcom/tado/android/rest/callback/RetryCallback;", "Lcom/tado/android/rest/model/HomeInfo;", "(Lcom/tado/android/settings/homedetails/HomeDetailsPreferenceFragment;Lcom/tado/android/rest/callback/presenters/PresenterDelegate;Lcom/tado/android/settings/interfaces/GenericCallbackListener;)V", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: HomeDetailsPreferenceFragment.kt */
public final class HomeDetailsPreferenceFragment$loadData$1 extends RetryCallback<HomeInfo> {
    final /* synthetic */ HomeDetailsPreferenceFragment this$0;

    HomeDetailsPreferenceFragment$loadData$1(HomeDetailsPreferenceFragment $outer, PresenterDelegate $super_call_param$1, GenericCallbackListener $super_call_param$2) {
        this.this$0 = $outer;
        super($super_call_param$1, $super_call_param$2);
    }
}
