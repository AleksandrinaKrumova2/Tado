package com.tado.android.settings.homedetails;

import android.app.Dialog;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.settings.interfaces.GenericCallbackListener;
import com.tado.android.utils.SupportPreferenceEnabler;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016R$\u0010\u0003\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0014"}, d2 = {"Lcom/tado/android/settings/homedetails/CancelSubscriptionDialog;", "Landroid/support/v4/app/DialogFragment;", "()V", "listener", "Lcom/tado/android/settings/interfaces/GenericCallbackListener;", "Ljava/lang/Void;", "getListener", "()Lcom/tado/android/settings/interfaces/GenericCallbackListener;", "setListener", "(Lcom/tado/android/settings/interfaces/GenericCallbackListener;)V", "preferenceEnabler", "Lcom/tado/android/utils/SupportPreferenceEnabler;", "getPreferenceEnabler", "()Lcom/tado/android/utils/SupportPreferenceEnabler;", "setPreferenceEnabler", "(Lcom/tado/android/utils/SupportPreferenceEnabler;)V", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: CancelSubscriptionDialog.kt */
public final class CancelSubscriptionDialog extends DialogFragment {
    private HashMap _$_findViewCache;
    @Nullable
    private GenericCallbackListener<Void> listener;
    @Nullable
    private SupportPreferenceEnabler preferenceEnabler;

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

    @Nullable
    public final GenericCallbackListener<Void> getListener() {
        return this.listener;
    }

    public final void setListener(@Nullable GenericCallbackListener<Void> <set-?>) {
        this.listener = <set-?>;
    }

    @Nullable
    public final SupportPreferenceEnabler getPreferenceEnabler() {
        return this.preferenceEnabler;
    }

    public final void setPreferenceEnabler(@Nullable SupportPreferenceEnabler <set-?>) {
        this.preferenceEnabler = <set-?>;
    }

    @NotNull
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        Builder builder = new Builder(activity);
        builder.setMessage((int) C0676R.string.premiumUpgrade_subscriptionDetails_cancelConfirmation_message).setPositiveButton((int) C0676R.string.premiumUpgrade_subscriptionDetails_cancelConfirmation_confirmButton, (OnClickListener) new CancelSubscriptionDialog$onCreateDialog$1(this)).setNegativeButton((int) C0676R.string.premiumUpgrade_subscriptionDetails_cancelConfirmation_cancelButton, (OnClickListener) new CancelSubscriptionDialog$onCreateDialog$2(this));
        AlertDialog create = builder.create();
        Intrinsics.checkExpressionValueIsNotNull(create, "builder.create()");
        return create;
    }
}
