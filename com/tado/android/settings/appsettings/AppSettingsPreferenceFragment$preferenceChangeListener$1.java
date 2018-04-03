package com.tado.android.settings.appsettings;

import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.SwitchPreference;
import com.tado.C0676R;
import com.tado.android.dialogs.AlertDialogs;
import com.tado.android.location.LocationPermissionControler;
import com.tado.android.settings.model.PresenceDetectionSettings;
import com.tado.android.settings.model.PresenceDetectionSettings.OnPresenceDetectionRemoteChanged;
import com.tado.android.utils.ResourceFactory;
import com.tado.android.utils.Util;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "preference", "Landroid/preference/Preference;", "kotlin.jvm.PlatformType", "newValue", "", "onPreferenceChange"}, k = 3, mv = {1, 1, 9})
/* compiled from: AppSettingsPreferenceFragment.kt */
final class AppSettingsPreferenceFragment$preferenceChangeListener$1 implements OnPreferenceChangeListener {
    final /* synthetic */ AppSettingsPreferenceFragment this$0;

    AppSettingsPreferenceFragment$preferenceChangeListener$1(AppSettingsPreferenceFragment appSettingsPreferenceFragment) {
        this.this$0 = appSettingsPreferenceFragment;
    }

    public final boolean onPreferenceChange(final Preference preference, Object newValue) {
        if (Util.isNetworkAvailable()) {
            Intrinsics.checkExpressionValueIsNotNull(preference, "preference");
            preference.setEnabled(false);
            if (!LocationPermissionControler.INSTANCE.checkLocationPermissionsAndInitLocationApi(this.this$0.getActivity())) {
                LocationPermissionControler.INSTANCE.showLocationPermissionInfoDialog(this.this$0.getActivity());
            } else if (newValue == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
            } else {
                PresenceDetectionSettings.updatePresenceDetectionSetting(((Boolean) newValue).booleanValue(), new OnPresenceDetectionRemoteChanged() {
                    public void onPresenceDetectionRemoteChanged(boolean isEnabled) {
                        if (this.this$0.isAdded()) {
                            Preference preference = preference;
                            if (preference == null) {
                                throw new TypeCastException("null cannot be cast to non-null type android.preference.SwitchPreference");
                            }
                            ((SwitchPreference) preference).setChecked(isEnabled);
                            preference.setEnabled(true);
                            preference = this.this$0.getHomeWiFi();
                            if (preference != null) {
                                preference.setEnabled(isEnabled);
                            }
                            Preference homeWiFi = this.this$0.getHomeWiFi();
                            if (homeWiFi != null) {
                                homeWiFi.setIcon(ResourceFactory.getHomeWifiDrawable(this.this$0.getActivity(), isEnabled));
                            }
                        }
                    }

                    public void onRemoteChangeFailed() {
                        if (this.this$0.isAdded()) {
                            AlertDialogs.showSimpleWarning("", this.this$0.getResources().getString(C0676R.string.settings_no_internet_connection), "OK", this.this$0.getActivity(), null);
                            Preference preference = preference;
                            Intrinsics.checkExpressionValueIsNotNull(preference, "preference");
                            preference.setEnabled(true);
                        }
                    }

                    public void onRemoteChangeNotSuccessful() {
                        if (this.this$0.isAdded()) {
                            AlertDialogs.showSimpleWarning("", this.this$0.getResources().getString(C0676R.string.settings_no_internet_connection), "OK", this.this$0.getActivity(), null);
                            Preference preference = preference;
                            Intrinsics.checkExpressionValueIsNotNull(preference, "preference");
                            preference.setEnabled(true);
                        }
                    }
                });
            }
        } else {
            this.this$0.showNoInternet();
        }
        return false;
    }
}
