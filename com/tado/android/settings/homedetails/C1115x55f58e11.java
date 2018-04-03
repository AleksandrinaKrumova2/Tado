package com.tado.android.settings.homedetails;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.preference.Preference;
import android.support.v7.preference.Preference.OnPreferenceClickListener;
import com.tado.android.rest.model.HomeInfo.LicenseEnum;
import com.tado.android.settings.interfaces.GenericCallbackListener;
import com.tado.android.utils.SupportPreferenceEnabler;
import com.tado.android.utils.UserConfig;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Ref.ObjectRef;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/support/v7/preference/Preference;", "kotlin.jvm.PlatformType", "onPreferenceClick"}, k = 3, mv = {1, 1, 9})
/* compiled from: HomeDetailsPreferenceFragment.kt */
final class C1115x55f58e11 implements OnPreferenceClickListener {
    final /* synthetic */ Preference $cancelSubscriptionPreference;
    final /* synthetic */ HomeDetailsPreferenceFragment this$0;

    C1115x55f58e11(HomeDetailsPreferenceFragment homeDetailsPreferenceFragment, Preference preference) {
        this.this$0 = homeDetailsPreferenceFragment;
        this.$cancelSubscriptionPreference = preference;
    }

    public final boolean onPreferenceClick(Preference it) {
        final ObjectRef objectRef = new ObjectRef();
        objectRef.element = new SupportPreferenceEnabler(this.$cancelSubscriptionPreference);
        ((SupportPreferenceEnabler) objectRef.element).disablePreferences();
        CancelSubscriptionDialog dialog = new CancelSubscriptionDialog();
        dialog.setPreferenceEnabler((SupportPreferenceEnabler) objectRef.element);
        dialog.setListener(new GenericCallbackListener<Void>() {
            public void onSuccess(@Nullable Void body) {
                UserConfig.setLicense(LicenseEnum.NON_PREMIUM);
                this.this$0.initPreferences();
            }

            public void onFailure() {
                ((SupportPreferenceEnabler) objectRef.element).enablePreference();
            }
        });
        if (this.this$0.getActivity() != null) {
            Activity activity = this.this$0.getActivity();
            if (activity == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.support.v4.app.FragmentActivity");
            }
            dialog.show(((FragmentActivity) activity).getSupportFragmentManager(), "signOut");
        }
        return true;
    }
}
