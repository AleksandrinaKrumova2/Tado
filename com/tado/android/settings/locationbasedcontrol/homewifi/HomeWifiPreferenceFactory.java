package com.tado.android.settings.locationbasedcontrol.homewifi;

import android.content.Context;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import com.tado.C0676R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/tado/android/settings/locationbasedcontrol/homewifi/HomeWifiPreferenceFactory;", "", "()V", "Companion", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: HomeWifiPreferenceFactory.kt */
public final class HomeWifiPreferenceFactory {
    public static final Companion Companion = new Companion();

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J)\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0002\u0010\u000bJ\u0018\u0010\f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0018\u0010\r\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\u000e"}, d2 = {"Lcom/tado/android/settings/locationbasedcontrol/homewifi/HomeWifiPreferenceFactory$Companion;", "", "()V", "createCategory", "Landroid/preference/PreferenceCategory;", "context", "Landroid/content/Context;", "screen", "Landroid/preference/PreferenceScreen;", "titleResId", "", "(Landroid/content/Context;Landroid/preference/PreferenceScreen;Ljava/lang/Integer;)Landroid/preference/PreferenceCategory;", "currentNetworkCategory", "homeNetworksCategory", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: HomeWifiPreferenceFactory.kt */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final PreferenceCategory currentNetworkCategory(@Nullable Context context, @NotNull PreferenceScreen screen) {
            Intrinsics.checkParameterIsNotNull(screen, "screen");
            return createCategory(context, screen, Integer.valueOf(C0676R.string.settings_locationBasedControl_homeWiFi_networkNotUsedYetLabel));
        }

        @NotNull
        public final PreferenceCategory homeNetworksCategory(@Nullable Context context, @NotNull PreferenceScreen screen) {
            Intrinsics.checkParameterIsNotNull(screen, "screen");
            return createCategory(context, screen, Integer.valueOf(C0676R.string.settings_locationBasedControl_homeWiFi_homeNetworksLabel));
        }

        private final PreferenceCategory createCategory(Context context, PreferenceScreen screen, Integer titleResId) {
            PreferenceCategory preferenceCategory = new PreferenceCategory(context);
            if (titleResId != null) {
                preferenceCategory.setTitle(titleResId.intValue());
            }
            screen.addPreference(preferenceCategory);
            return preferenceCategory;
        }
    }
}
