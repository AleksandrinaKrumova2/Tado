package com.tado.android.rest.model;

import com.tado.android.rest.model.HomeInfo.LicenseEnum;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/tado/android/rest/model/HomeLicense;", "", "license", "Lcom/tado/android/rest/model/HomeInfo$LicenseEnum;", "(Lcom/tado/android/rest/model/HomeInfo$LicenseEnum;)V", "getLicense", "()Lcom/tado/android/rest/model/HomeInfo$LicenseEnum;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: HomeLicense.kt */
public final class HomeLicense {
    @NotNull
    private final LicenseEnum license;

    @NotNull
    public static /* bridge */ /* synthetic */ HomeLicense copy$default(HomeLicense homeLicense, LicenseEnum licenseEnum, int i, Object obj) {
        if ((i & 1) != 0) {
            licenseEnum = homeLicense.license;
        }
        return homeLicense.copy(licenseEnum);
    }

    @NotNull
    public final LicenseEnum component1() {
        return this.license;
    }

    @NotNull
    public final HomeLicense copy(@NotNull LicenseEnum license) {
        Intrinsics.checkParameterIsNotNull(license, "license");
        return new HomeLicense(license);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r3) {
        /*
        r2 = this;
        if (r2 == r3) goto L_0x0012;
    L_0x0002:
        r0 = r3 instanceof com.tado.android.rest.model.HomeLicense;
        if (r0 == 0) goto L_0x0014;
    L_0x0006:
        r3 = (com.tado.android.rest.model.HomeLicense) r3;
        r0 = r2.license;
        r1 = r3.license;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x0014;
    L_0x0012:
        r0 = 1;
    L_0x0013:
        return r0;
    L_0x0014:
        r0 = 0;
        goto L_0x0013;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.rest.model.HomeLicense.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        LicenseEnum licenseEnum = this.license;
        return licenseEnum != null ? licenseEnum.hashCode() : 0;
    }

    public String toString() {
        return "HomeLicense(license=" + this.license + ")";
    }

    public HomeLicense(@NotNull LicenseEnum license) {
        Intrinsics.checkParameterIsNotNull(license, "license");
        this.license = license;
    }

    @NotNull
    public final LicenseEnum getLicense() {
        return this.license;
    }
}
