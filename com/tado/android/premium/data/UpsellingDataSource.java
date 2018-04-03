package com.tado.android.premium.data;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\u0006H&¨\u0006\b"}, d2 = {"Lcom/tado/android/premium/data/UpsellingDataSource;", "", "dismissGeofencingUpsellingScreen", "", "dismissOpenWindowUpsellingScreen", "shouldShowGeofencingUpsellingScreen", "", "shouldShowOpenWindowUpsellingScreen", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: UpsellingDataSource.kt */
public interface UpsellingDataSource {
    void dismissGeofencingUpsellingScreen();

    void dismissOpenWindowUpsellingScreen();

    boolean shouldShowGeofencingUpsellingScreen();

    boolean shouldShowOpenWindowUpsellingScreen();
}
