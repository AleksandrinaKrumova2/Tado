package com.tado.android.settings.locationbasedcontrol;

import com.tado.android.rest.callback.RetryCallback;
import com.tado.android.rest.callback.presenters.PresenterDelegate;
import com.tado.android.utils.UserConfig;
import kotlin.Metadata;
import kotlin.TypeCastException;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Response;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\"\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J(\u0010\n\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00072\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"com/tado/android/settings/locationbasedcontrol/HomeAreaPreferenceActivity$saveChanges$1", "Lcom/tado/android/rest/callback/RetryCallback;", "Ljava/lang/Void;", "(Lcom/tado/android/settings/locationbasedcontrol/HomeAreaPreferenceActivity;FLcom/tado/android/rest/callback/presenters/PresenterDelegate;)V", "onFailure", "", "call", "Lretrofit2/Call;", "t", "", "onResponse", "response", "Lretrofit2/Response;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: HomeAreaPreferenceActivity.kt */
public final class HomeAreaPreferenceActivity$saveChanges$1 extends RetryCallback<Void> {
    final /* synthetic */ float $distance;
    final /* synthetic */ HomeAreaPreferenceActivity this$0;

    HomeAreaPreferenceActivity$saveChanges$1(HomeAreaPreferenceActivity $outer, float $captured_local_variable$1, PresenterDelegate $super_call_param$2) {
        this.this$0 = $outer;
        this.$distance = $captured_local_variable$1;
        super($super_call_param$2);
    }

    public void onResponse(@Nullable Call<Void> call, @Nullable Response<Void> response) {
        super.onResponse(call, response);
        HomeAreaPreferenceActivity.access$getOverlay$p(this.this$0).setEnabled(true);
        Boolean valueOf = response != null ? Boolean.valueOf(response.isSuccessful()) : null;
        if (valueOf == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
        } else if (valueOf.booleanValue()) {
            UserConfig.setHomeFence(this.$distance);
            this.this$0.getProgressBar().hideView();
            this.this$0.finish();
        } else {
            this.this$0.getProgressBar().hideViewWithAnimation();
        }
    }

    public void onFailure(@Nullable Call<Void> call, @Nullable Throwable t) {
        super.onFailure(call, t);
        HomeAreaPreferenceActivity.access$getOverlay$p(this.this$0).setEnabled(true);
        this.this$0.getProgressBar().hideViewWithAnimation();
    }
}
