package com.tado.android.repairServices;

import com.tado.android.installation.CreateHomeContactDetailsActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/tado/android/repairServices/RepairService;", "", "name", "", "url", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getUrl", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: RepairServicesLoader.kt */
public final class RepairService {
    @NotNull
    private final String name;
    @NotNull
    private final String url;

    @NotNull
    public static /* bridge */ /* synthetic */ RepairService copy$default(RepairService repairService, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = repairService.name;
        }
        if ((i & 2) != 0) {
            str2 = repairService.url;
        }
        return repairService.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final String component2() {
        return this.url;
    }

    @NotNull
    public final RepairService copy(@NotNull String name, @NotNull String url) {
        Intrinsics.checkParameterIsNotNull(name, CreateHomeContactDetailsActivity.INTENT_NAME);
        Intrinsics.checkParameterIsNotNull(url, "url");
        return new RepairService(name, url);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r3) {
        /*
        r2 = this;
        if (r2 == r3) goto L_0x001c;
    L_0x0002:
        r0 = r3 instanceof com.tado.android.repairServices.RepairService;
        if (r0 == 0) goto L_0x001e;
    L_0x0006:
        r3 = (com.tado.android.repairServices.RepairService) r3;
        r0 = r2.name;
        r1 = r3.name;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x001e;
    L_0x0012:
        r0 = r2.url;
        r1 = r3.url;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x001e;
    L_0x001c:
        r0 = 1;
    L_0x001d:
        return r0;
    L_0x001e:
        r0 = 0;
        goto L_0x001d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.repairServices.RepairService.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i = 0;
        String str = this.name;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.url;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "RepairService(name=" + this.name + ", url=" + this.url + ")";
    }

    public RepairService(@NotNull String name, @NotNull String url) {
        Intrinsics.checkParameterIsNotNull(name, CreateHomeContactDetailsActivity.INTENT_NAME);
        Intrinsics.checkParameterIsNotNull(url, "url");
        this.name = name;
        this.url = url;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getUrl() {
        return this.url;
    }
}
