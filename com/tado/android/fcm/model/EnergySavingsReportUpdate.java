package com.tado.android.fcm.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/tado/android/fcm/model/EnergySavingsReportUpdate;", "", "month", "", "savingsInPercent", "", "preferredNotificationTime", "(Ljava/lang/String;DLjava/lang/String;)V", "getMonth", "()Ljava/lang/String;", "getPreferredNotificationTime", "getSavingsInPercent", "()D", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: NotificationModels.kt */
public final class EnergySavingsReportUpdate {
    @NotNull
    private final String month;
    @NotNull
    private final String preferredNotificationTime;
    private final double savingsInPercent;

    @NotNull
    public static /* bridge */ /* synthetic */ EnergySavingsReportUpdate copy$default(EnergySavingsReportUpdate energySavingsReportUpdate, String str, double d, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = energySavingsReportUpdate.month;
        }
        if ((i & 2) != 0) {
            d = energySavingsReportUpdate.savingsInPercent;
        }
        if ((i & 4) != 0) {
            str2 = energySavingsReportUpdate.preferredNotificationTime;
        }
        return energySavingsReportUpdate.copy(str, d, str2);
    }

    @NotNull
    public final String component1() {
        return this.month;
    }

    public final double component2() {
        return this.savingsInPercent;
    }

    @NotNull
    public final String component3() {
        return this.preferredNotificationTime;
    }

    @NotNull
    public final EnergySavingsReportUpdate copy(@NotNull String month, double savingsInPercent, @NotNull String preferredNotificationTime) {
        Intrinsics.checkParameterIsNotNull(month, "month");
        Intrinsics.checkParameterIsNotNull(preferredNotificationTime, "preferredNotificationTime");
        return new EnergySavingsReportUpdate(month, savingsInPercent, preferredNotificationTime);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
        r4 = this;
        if (r4 == r5) goto L_0x0026;
    L_0x0002:
        r0 = r5 instanceof com.tado.android.fcm.model.EnergySavingsReportUpdate;
        if (r0 == 0) goto L_0x0028;
    L_0x0006:
        r5 = (com.tado.android.fcm.model.EnergySavingsReportUpdate) r5;
        r0 = r4.month;
        r1 = r5.month;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x0028;
    L_0x0012:
        r0 = r4.savingsInPercent;
        r2 = r5.savingsInPercent;
        r0 = java.lang.Double.compare(r0, r2);
        if (r0 != 0) goto L_0x0028;
    L_0x001c:
        r0 = r4.preferredNotificationTime;
        r1 = r5.preferredNotificationTime;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x0028;
    L_0x0026:
        r0 = 1;
    L_0x0027:
        return r0;
    L_0x0028:
        r0 = 0;
        goto L_0x0027;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.fcm.model.EnergySavingsReportUpdate.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i = 0;
        String str = this.month;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        long doubleToLongBits = Double.doubleToLongBits(this.savingsInPercent);
        hashCode = (hashCode + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31;
        String str2 = this.preferredNotificationTime;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "EnergySavingsReportUpdate(month=" + this.month + ", savingsInPercent=" + this.savingsInPercent + ", preferredNotificationTime=" + this.preferredNotificationTime + ")";
    }

    public EnergySavingsReportUpdate(@NotNull String month, double savingsInPercent, @NotNull String preferredNotificationTime) {
        Intrinsics.checkParameterIsNotNull(month, "month");
        Intrinsics.checkParameterIsNotNull(preferredNotificationTime, "preferredNotificationTime");
        this.month = month;
        this.savingsInPercent = savingsInPercent;
        this.preferredNotificationTime = preferredNotificationTime;
    }

    @NotNull
    public final String getMonth() {
        return this.month;
    }

    @NotNull
    public final String getPreferredNotificationTime() {
        return this.preferredNotificationTime;
    }

    public final double getSavingsInPercent() {
        return this.savingsInPercent;
    }
}
