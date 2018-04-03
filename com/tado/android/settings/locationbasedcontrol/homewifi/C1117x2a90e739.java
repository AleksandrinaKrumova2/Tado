package com.tado.android.settings.locationbasedcontrol.homewifi;

import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 9})
/* compiled from: HomeWifiPreferenceFragment.kt */
final class C1117x2a90e739 implements OnClickListener {
    final /* synthetic */ String $bssid$inlined;
    final /* synthetic */ String $ssid$inlined;
    final /* synthetic */ HomeWifiPreferenceFragment this$0;

    C1117x2a90e739(HomeWifiPreferenceFragment homeWifiPreferenceFragment, String str, String str2) {
        this.this$0 = homeWifiPreferenceFragment;
        this.$ssid$inlined = str;
        this.$bssid$inlined = str2;
    }

    public final void onClick(View it) {
        HomeWifiPreferenceFragment homeWifiPreferenceFragment = this.this$0;
        String str = this.$ssid$inlined;
        if (str == null) {
            str = "";
        }
        AlertDialog access$createResetConfirmationDialog = homeWifiPreferenceFragment.createResetConfirmationDialog(str, this.$bssid$inlined);
        if (access$createResetConfirmationDialog != null) {
            access$createResetConfirmationDialog.show();
        }
    }
}
