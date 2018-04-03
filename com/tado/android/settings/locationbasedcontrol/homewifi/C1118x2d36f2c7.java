package com.tado.android.settings.locationbasedcontrol.homewifi;

import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import com.tado.android.utils.UserConfig;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/preference/Preference;", "kotlin.jvm.PlatformType", "newValue", "", "onPreferenceChange"}, k = 3, mv = {1, 1, 9})
/* compiled from: HomeWifiPreferenceFragment.kt */
final class C1118x2d36f2c7 implements OnPreferenceChangeListener {
    final /* synthetic */ HomeWifiPreferenceFragment this$0;

    C1118x2d36f2c7(HomeWifiPreferenceFragment homeWifiPreferenceFragment) {
        this.this$0 = homeWifiPreferenceFragment;
    }

    public final boolean onPreferenceChange(Preference $noName_0, Object newValue) {
        if (newValue == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
        }
        PreferenceScreen preferenceScreen;
        UserConfig.setHomeWifiDetectionEnabled(((Boolean) newValue).booleanValue());
        PreferenceCategory it = this.this$0.currentNetworkCategory;
        if (it != null) {
            preferenceScreen = this.this$0.getPreferenceScreen();
            if (preferenceScreen != null) {
                preferenceScreen.removePreference(it);
            }
        }
        it = this.this$0.homeNetworkCategory;
        if (it != null) {
            preferenceScreen = this.this$0.getPreferenceScreen();
            if (preferenceScreen != null) {
                preferenceScreen.removePreference(it);
            }
        }
        if (((Boolean) newValue).booleanValue()) {
            HomeWifiPreferenceFragment homeWifiPreferenceFragment = this.this$0;
            PreferenceScreen preferenceScreen2 = this.this$0.getPreferenceScreen();
            Intrinsics.checkExpressionValueIsNotNull(preferenceScreen2, "preferenceScreen");
            homeWifiPreferenceFragment.initNetworkPreferences(preferenceScreen2);
        }
        return true;
    }
}
