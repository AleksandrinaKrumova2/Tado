package com.tado.android.repairServices;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/tado/android/repairServices/RepairServicesButton;", "", "titleResId", "", "backgroundResId", "trackingInfo", "", "(IILjava/lang/String;)V", "getBackgroundResId", "()I", "getTitleResId", "getTrackingInfo", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: RepairServicesActivity.kt */
public final class RepairServicesButton {
    private final int backgroundResId;
    private final int titleResId;
    @NotNull
    private final String trackingInfo;

    @NotNull
    public static /* bridge */ /* synthetic */ RepairServicesButton copy$default(RepairServicesButton repairServicesButton, int i, int i2, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = repairServicesButton.titleResId;
        }
        if ((i3 & 2) != 0) {
            i2 = repairServicesButton.backgroundResId;
        }
        if ((i3 & 4) != 0) {
            str = repairServicesButton.trackingInfo;
        }
        return repairServicesButton.copy(i, i2, str);
    }

    public final int component1() {
        return this.titleResId;
    }

    public final int component2() {
        return this.backgroundResId;
    }

    @NotNull
    public final String component3() {
        return this.trackingInfo;
    }

    @NotNull
    public final RepairServicesButton copy(int titleResId, int backgroundResId, @NotNull String trackingInfo) {
        Intrinsics.checkParameterIsNotNull(trackingInfo, "trackingInfo");
        return new RepairServicesButton(titleResId, backgroundResId, trackingInfo);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (!(obj instanceof RepairServicesButton)) {
                return false;
            }
            RepairServicesButton repairServicesButton = (RepairServicesButton) obj;
            if (!(this.titleResId == repairServicesButton.titleResId)) {
                return false;
            }
            if (!((this.backgroundResId == repairServicesButton.backgroundResId) && Intrinsics.areEqual(this.trackingInfo, repairServicesButton.trackingInfo))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = ((this.titleResId * 31) + this.backgroundResId) * 31;
        String str = this.trackingInfo;
        return (str != null ? str.hashCode() : 0) + i;
    }

    public String toString() {
        return "RepairServicesButton(titleResId=" + this.titleResId + ", backgroundResId=" + this.backgroundResId + ", trackingInfo=" + this.trackingInfo + ")";
    }

    public RepairServicesButton(int titleResId, int backgroundResId, @NotNull String trackingInfo) {
        Intrinsics.checkParameterIsNotNull(trackingInfo, "trackingInfo");
        this.titleResId = titleResId;
        this.backgroundResId = backgroundResId;
        this.trackingInfo = trackingInfo;
    }

    public final int getBackgroundResId() {
        return this.backgroundResId;
    }

    public final int getTitleResId() {
        return this.titleResId;
    }

    @NotNull
    public final String getTrackingInfo() {
        return this.trackingInfo;
    }
}
