package com.tado.android.premium;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.tado.android.premium.PremiumCarouselActivity.PremiumPage;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"com/tado/android/premium/PremiumCarouselActivity$PremiumPage$Companion$CREATOR$1", "Landroid/os/Parcelable$Creator;", "Lcom/tado/android/premium/PremiumCarouselActivity$PremiumPage;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/tado/android/premium/PremiumCarouselActivity$PremiumPage;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: PremiumCarouselActivity.kt */
public final class PremiumCarouselActivity$PremiumPage$Companion$CREATOR$1 implements Creator<PremiumPage> {
    PremiumCarouselActivity$PremiumPage$Companion$CREATOR$1() {
    }

    @NotNull
    public PremiumPage[] newArray(int size) {
        return new PremiumPage[size];
    }

    @NotNull
    public PremiumPage createFromParcel(@NotNull Parcel parcel) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        return new PremiumPage(parcel);
    }
}
