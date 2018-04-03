package com.tado.android.times;

import com.tado.android.rest.model.GenericAwayConfiguration;
import com.tado.android.rest.model.ZoneSetting;
import com.tado.android.views.TadoZoneSettingsView.OnZoneSettingChangedListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/tado/android/rest/model/ZoneSetting;", "kotlin.jvm.PlatformType", "onZoneSettingChanged"}, k = 3, mv = {1, 1, 9})
/* compiled from: AwayTemperatureBottomSheet.kt */
final class AwayTemperatureBottomSheet$onViewCreated$1 implements OnZoneSettingChangedListener {
    final /* synthetic */ AwayTemperatureBottomSheet this$0;

    AwayTemperatureBottomSheet$onViewCreated$1(AwayTemperatureBottomSheet awayTemperatureBottomSheet) {
        this.this$0 = awayTemperatureBottomSheet;
    }

    public final void onZoneSettingChanged(ZoneSetting it) {
        GenericAwayConfiguration mAwayConfiguration = this.this$0.getMAwayConfiguration();
        if (mAwayConfiguration != null) {
            mAwayConfiguration.copyFromZoneSettings(it);
        }
    }
}
