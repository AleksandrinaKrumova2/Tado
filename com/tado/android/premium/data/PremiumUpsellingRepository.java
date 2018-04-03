package com.tado.android.premium.data;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/tado/android/premium/data/PremiumUpsellingRepository;", "Lcom/tado/android/premium/data/UpsellingDataSource;", "()V", "localDataSource", "Lcom/tado/android/premium/data/UpsellingLocalDataSource;", "getLocalDataSource", "()Lcom/tado/android/premium/data/UpsellingLocalDataSource;", "setLocalDataSource", "(Lcom/tado/android/premium/data/UpsellingLocalDataSource;)V", "dismissGeofencingUpsellingScreen", "", "dismissOpenWindowUpsellingScreen", "shouldShowGeofencingUpsellingScreen", "", "shouldShowOpenWindowUpsellingScreen", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: PremiumUpsellingRepository.kt */
public final class PremiumUpsellingRepository implements UpsellingDataSource {
    public static final PremiumUpsellingRepository INSTANCE = new PremiumUpsellingRepository();
    @Nullable
    private static UpsellingLocalDataSource localDataSource;

    private PremiumUpsellingRepository() {
    }

    @Nullable
    public final UpsellingLocalDataSource getLocalDataSource() {
        return localDataSource;
    }

    public final void setLocalDataSource(@Nullable UpsellingLocalDataSource <set-?>) {
        localDataSource = <set-?>;
    }

    public boolean shouldShowOpenWindowUpsellingScreen() {
        UpsellingLocalDataSource upsellingLocalDataSource = localDataSource;
        return upsellingLocalDataSource != null && upsellingLocalDataSource.shouldShowOpenWindowUpsellingScreen();
    }

    public void dismissOpenWindowUpsellingScreen() {
        UpsellingLocalDataSource upsellingLocalDataSource = localDataSource;
        if (upsellingLocalDataSource != null) {
            upsellingLocalDataSource.dismissOpenWindowUpsellingScreen();
        }
    }

    public boolean shouldShowGeofencingUpsellingScreen() {
        UpsellingLocalDataSource upsellingLocalDataSource = localDataSource;
        return upsellingLocalDataSource != null && upsellingLocalDataSource.shouldShowGeofencingUpsellingScreen();
    }

    public void dismissGeofencingUpsellingScreen() {
        UpsellingLocalDataSource upsellingLocalDataSource = localDataSource;
        if (upsellingLocalDataSource != null) {
            upsellingLocalDataSource.dismissGeofencingUpsellingScreen();
        }
    }
}
