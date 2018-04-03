package com.tado.android.settings.homedetails;

import com.tado.android.rest.model.HomeInfo;
import com.tado.android.settings.interfaces.GenericCallbackListener;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\b"}, d2 = {"com/tado/android/settings/homedetails/HomeDetailsPreferenceFragment$loadData$2", "Lcom/tado/android/settings/interfaces/GenericCallbackListener;", "Lcom/tado/android/rest/model/HomeInfo;", "(Lcom/tado/android/settings/homedetails/HomeDetailsPreferenceFragment;)V", "onFailure", "", "onSuccess", "body", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: HomeDetailsPreferenceFragment.kt */
public final class HomeDetailsPreferenceFragment$loadData$2 implements GenericCallbackListener<HomeInfo> {
    final /* synthetic */ HomeDetailsPreferenceFragment this$0;

    HomeDetailsPreferenceFragment$loadData$2(HomeDetailsPreferenceFragment $outer) {
        this.this$0 = $outer;
    }

    public void onSuccess(@Nullable HomeInfo body) {
        this.this$0.homeInfo = body;
        if (this.this$0.isAdded()) {
            this.this$0.initPreferences();
        }
    }

    public void onFailure() {
    }
}
