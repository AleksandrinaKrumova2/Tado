package com.tado.android.settings.locationbasedcontrol;

import com.google.android.gms.maps.GoogleMap.OnCameraIdleListener;
import com.google.android.gms.maps.GoogleMap.OnCameraMoveListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "onCameraMove"}, k = 3, mv = {1, 1, 9})
/* compiled from: HomeAreaPreferenceActivity.kt */
final class HomeAreaPreferenceActivity$adjustCameraToBounds$1 implements OnCameraMoveListener {
    final /* synthetic */ float $distanceInMeters;
    final /* synthetic */ HomeAreaPreferenceActivity this$0;

    HomeAreaPreferenceActivity$adjustCameraToBounds$1(HomeAreaPreferenceActivity homeAreaPreferenceActivity, float f) {
        this.this$0 = homeAreaPreferenceActivity;
        this.$distanceInMeters = f;
    }

    public final void onCameraMove() {
        this.this$0.fitCircleToMap(this.$distanceInMeters);
        HomeAreaPreferenceActivity.access$getMap$p(this.this$0).setOnCameraIdleListener(new OnCameraIdleListener() {
            public final void onCameraIdle() {
                HomeAreaPreferenceActivity.access$getMap$p(this.this$0).setOnCameraMoveListener(null);
            }
        });
    }
}
