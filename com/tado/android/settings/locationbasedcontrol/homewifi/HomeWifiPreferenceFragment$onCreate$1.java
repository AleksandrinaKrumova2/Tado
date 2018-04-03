package com.tado.android.settings.locationbasedcontrol.homewifi;

import android.databinding.ObservableMap;
import android.databinding.ObservableMap.OnMapChangedCallback;
import com.tado.android.app.TadoApplication;
import com.tado.android.location.LocationAcquisitionMode;
import com.tado.android.location.LocationTrigger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002 \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J&\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u0003H\u0016¨\u0006\t"}, d2 = {"com/tado/android/settings/locationbasedcontrol/homewifi/HomeWifiPreferenceFragment$onCreate$1", "Landroid/databinding/ObservableMap$OnMapChangedCallback;", "Landroid/databinding/ObservableMap;", "", "(Lcom/tado/android/settings/locationbasedcontrol/homewifi/HomeWifiPreferenceFragment;)V", "onMapChanged", "", "sender", "key", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: HomeWifiPreferenceFragment.kt */
public final class HomeWifiPreferenceFragment$onCreate$1 extends OnMapChangedCallback<ObservableMap<String, String>, String, String> {
    final /* synthetic */ HomeWifiPreferenceFragment this$0;

    HomeWifiPreferenceFragment$onCreate$1(HomeWifiPreferenceFragment $outer) {
        this.this$0 = $outer;
    }

    public void onMapChanged(@NotNull ObservableMap<String, String> sender, @Nullable String key) {
        Intrinsics.checkParameterIsNotNull(sender, "sender");
        if (!this.this$0.updateLocked) {
            this.this$0.recreatePreferenceScreen();
        }
        if (this.this$0.isAdded()) {
            TadoApplication.locationManager.postLastKnownLocation(LocationAcquisitionMode.LAST_KNOWN_LOCATION, LocationTrigger.APP_TRIGGERED);
        }
    }
}
