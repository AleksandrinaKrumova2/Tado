package com.tado.android.settings.homedetails.data;

import android.content.Context;
import com.tado.android.rest.callback.RetryCallback;
import com.tado.android.rest.callback.presenters.PresenterDelegate;
import com.tado.android.settings.interfaces.GenericCallbackListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"com/tado/android/settings/homedetails/data/HomeRepository$saveHomeDetails$1", "Lcom/tado/android/rest/callback/RetryCallback;", "Ljava/lang/Void;", "(Landroid/content/Context;Lcom/tado/android/settings/interfaces/GenericCallbackListener;Lcom/tado/android/rest/callback/presenters/PresenterDelegate;Lcom/tado/android/settings/interfaces/GenericCallbackListener;)V", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: HomeRepository.kt */
public final class HomeRepository$saveHomeDetails$1 extends RetryCallback<Void> {
    final /* synthetic */ Context $context;
    final /* synthetic */ GenericCallbackListener $listener;

    HomeRepository$saveHomeDetails$1(Context $captured_local_variable$0, GenericCallbackListener $captured_local_variable$1, PresenterDelegate $super_call_param$2, GenericCallbackListener $super_call_param$3) {
        this.$context = $captured_local_variable$0;
        this.$listener = $captured_local_variable$1;
        super($super_call_param$2, $super_call_param$3);
    }
}
