package com.tado.android.settings.appsettings;

import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import com.tado.android.analytics.AnalyticsConstants.Events;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/preference/Preference;", "kotlin.jvm.PlatformType", "onPreferenceClick"}, k = 3, mv = {1, 1, 9})
/* compiled from: AppSettingsPreferenceFragment.kt */
final class C1083xa8e3d4b2 implements OnPreferenceClickListener {
    final /* synthetic */ AppSettingsPreferenceFragment this$0;

    C1083xa8e3d4b2(AppSettingsPreferenceFragment appSettingsPreferenceFragment) {
        this.this$0 = appSettingsPreferenceFragment;
    }

    public final boolean onPreferenceClick(Preference it) {
        AnalyticsHelper.trackEvent(this.this$0.getActivity(), Screen.APP_SETTINGS, Events.RECOMMENDED_SETTINGS);
        return false;
    }
}
