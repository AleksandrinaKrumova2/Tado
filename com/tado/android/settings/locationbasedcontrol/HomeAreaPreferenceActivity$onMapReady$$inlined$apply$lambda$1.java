package com.tado.android.settings.locationbasedcontrol;

import com.google.android.gms.maps.GoogleMap.OnMapLoadedCallback;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "onMapLoaded"}, k = 3, mv = {1, 1, 9})
/* compiled from: HomeAreaPreferenceActivity.kt */
final class HomeAreaPreferenceActivity$onMapReady$$inlined$apply$lambda$1 implements OnMapLoadedCallback {
    final /* synthetic */ HomeAreaPreferenceActivity this$0;

    HomeAreaPreferenceActivity$onMapReady$$inlined$apply$lambda$1(HomeAreaPreferenceActivity homeAreaPreferenceActivity) {
        this.this$0 = homeAreaPreferenceActivity;
    }

    public final void onMapLoaded() {
        this.this$0.resetHomeFence(this.this$0.getHomeFence(), false);
        HomeAreaPreferenceActivity.access$getOverlay$p(this.this$0).setEnabled(true);
    }
}
