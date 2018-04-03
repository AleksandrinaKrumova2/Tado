package com.tado.android.app;

import android.content.Context;
import com.tado.android.installation.InvitationReviewActivity;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.Invitation;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import org.jetbrains.anko.internals.AnkoInternals;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Response;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\"\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J(\u0010\n\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00072\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"com/tado/android/app/DeepLinkingManager$parseInvitation$1", "Lcom/tado/android/rest/callback/TadoCallback;", "Lcom/tado/android/rest/model/Invitation;", "(Landroid/content/Context;)V", "onFailure", "", "call", "Lretrofit2/Call;", "t", "", "onResponse", "response", "Lretrofit2/Response;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: DeepLinkingManager.kt */
public final class DeepLinkingManager$parseInvitation$1 extends TadoCallback<Invitation> {
    final /* synthetic */ Context $context;

    DeepLinkingManager$parseInvitation$1(Context $captured_local_variable$0) {
        this.$context = $captured_local_variable$0;
    }

    public void onResponse(@Nullable Call<Invitation> call, @Nullable Response<Invitation> response) {
        Boolean bool = null;
        super.onResponse(call, response);
        Invitation invitation = new Invitation(null);
        if (response != null) {
            bool = Boolean.valueOf(response.isSuccessful());
        }
        if (bool == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
        }
        if (bool.booleanValue()) {
            Object body = response.body();
            if (body == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.tado.android.rest.model.Invitation");
            }
            invitation = (Invitation) body;
        }
        AnkoInternals.internalStartActivity(this.$context, InvitationReviewActivity.class, new Pair[]{TuplesKt.to("keyInvitation", invitation)});
    }

    public void onFailure(@Nullable Call<Invitation> call, @Nullable Throwable t) {
        super.onFailure(call, t);
        AnkoInternals.internalStartActivity(this.$context, InvitationReviewActivity.class, new Pair[]{TuplesKt.to("keyInvitation", new Invitation(null))});
    }
}
