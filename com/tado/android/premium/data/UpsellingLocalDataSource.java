package com.tado.android.premium.data;

import com.tado.android.mvp.model.LocalPersistenceHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0002J\b\u0010\f\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/tado/android/premium/data/UpsellingLocalDataSource;", "Lcom/tado/android/premium/data/UpsellingDataSource;", "localDataSource", "Lcom/tado/android/mvp/model/LocalPersistenceHelper;", "(Lcom/tado/android/mvp/model/LocalPersistenceHelper;)V", "dismissedKey", "", "geofencingUpsellingKey", "openWindowUpsellingKey", "assertLocalDatasourceNotNull", "", "disk", "dismissGeofencingUpsellingScreen", "dismissOpenWindowUpsellingScreen", "shouldShowGeofencingUpsellingScreen", "", "shouldShowOpenWindowUpsellingScreen", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: UpsellingLocalDataSource.kt */
public final class UpsellingLocalDataSource implements UpsellingDataSource {
    private final String dismissedKey = "dismissed";
    private final String geofencingUpsellingKey = "geofencingUpselling";
    private final LocalPersistenceHelper localDataSource;
    private final String openWindowUpsellingKey = "openWindowUpselling";

    public UpsellingLocalDataSource(@NotNull LocalPersistenceHelper localDataSource) {
        Intrinsics.checkParameterIsNotNull(localDataSource, "localDataSource");
        this.localDataSource = localDataSource;
    }

    public boolean shouldShowOpenWindowUpsellingScreen() {
        assertLocalDatasourceNotNull(this.localDataSource);
        return Intrinsics.areEqual(this.localDataSource.getString(this.openWindowUpsellingKey, ""), this.dismissedKey) ^ 1;
    }

    public void dismissOpenWindowUpsellingScreen() {
        assertLocalDatasourceNotNull(this.localDataSource);
        this.localDataSource.putString(this.openWindowUpsellingKey, this.dismissedKey);
    }

    public boolean shouldShowGeofencingUpsellingScreen() {
        assertLocalDatasourceNotNull(this.localDataSource);
        return Intrinsics.areEqual(this.localDataSource.getString(this.geofencingUpsellingKey, ""), this.dismissedKey) ^ 1;
    }

    public void dismissGeofencingUpsellingScreen() {
        assertLocalDatasourceNotNull(this.localDataSource);
        this.localDataSource.putString(this.geofencingUpsellingKey, this.dismissedKey);
    }

    private final void assertLocalDatasourceNotNull(LocalPersistenceHelper disk) {
        if (disk == null) {
            throw new IllegalStateException("local datasource is not set");
        }
    }
}
