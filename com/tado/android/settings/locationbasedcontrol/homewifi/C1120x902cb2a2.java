package com.tado.android.settings.locationbasedcontrol.homewifi;

import android.net.wifi.WifiInfo;
import android.view.View;
import android.view.View.OnClickListener;
import com.tado.android.mvp.model.HomeWifiRepository;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 9})
/* compiled from: HomeWifiPreferenceFragment.kt */
final class C1120x902cb2a2 implements OnClickListener {
    final /* synthetic */ WifiInfo $currentWifiCopy$inlined;
    final /* synthetic */ HomeWifiPreferenceFragment this$0;

    C1120x902cb2a2(HomeWifiPreferenceFragment homeWifiPreferenceFragment, WifiInfo wifiInfo) {
        this.this$0 = homeWifiPreferenceFragment;
        this.$currentWifiCopy$inlined = wifiInfo;
    }

    public final void onClick(View it) {
        this.this$0.executeWithManualUpdate(new Function0<Unit>() {
            public final void invoke() {
                HomeWifiRepository homeWifiRepository = HomeWifiRepository.INSTANCE;
                String bssid = this.$currentWifiCopy$inlined.getBSSID();
                Intrinsics.checkExpressionValueIsNotNull(bssid, "currentWifiCopy.bssid");
                String ssid = this.$currentWifiCopy$inlined.getSSID();
                Intrinsics.checkExpressionValueIsNotNull(ssid, "currentWifiCopy.ssid");
                homeWifiRepository.addHomeWifi(bssid, ssid);
            }
        });
    }
}
