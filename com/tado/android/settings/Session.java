package com.tado.android.settings;

import com.tado.android.rest.model.HomeInfo.LicenseEnum;
import com.tado.android.utils.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B/\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nB5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001b\u001a\u00020\bHÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\fHÆ\u0003JE\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020\u0006HÖ\u0001J\t\u0010#\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013¨\u0006$"}, d2 = {"Lcom/tado/android/settings/Session;", "", "username", "", "password", "homeId", "", "homeCreation", "", "homeTimeZone", "(Ljava/lang/String;Ljava/lang/String;IJLjava/lang/String;)V", "license", "Lcom/tado/android/rest/model/HomeInfo$LicenseEnum;", "(Ljava/lang/String;Ljava/lang/String;IJLjava/lang/String;Lcom/tado/android/rest/model/HomeInfo$LicenseEnum;)V", "getHomeCreation", "()J", "getHomeId", "()I", "getHomeTimeZone", "()Ljava/lang/String;", "getLicense", "()Lcom/tado/android/rest/model/HomeInfo$LicenseEnum;", "getPassword", "getUsername", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: Session.kt */
public final class Session {
    private final long homeCreation;
    private final int homeId;
    @NotNull
    private final String homeTimeZone;
    @NotNull
    private final LicenseEnum license;
    @NotNull
    private final String password;
    @NotNull
    private final String username;

    @NotNull
    public static /* bridge */ /* synthetic */ Session copy$default(Session session, String str, String str2, int i, long j, String str3, LicenseEnum licenseEnum, int i2, Object obj) {
        return session.copy((i2 & 1) != 0 ? session.username : str, (i2 & 2) != 0 ? session.password : str2, (i2 & 4) != 0 ? session.homeId : i, (i2 & 8) != 0 ? session.homeCreation : j, (i2 & 16) != 0 ? session.homeTimeZone : str3, (i2 & 32) != 0 ? session.license : licenseEnum);
    }

    @NotNull
    public final String component1() {
        return this.username;
    }

    @NotNull
    public final String component2() {
        return this.password;
    }

    public final int component3() {
        return this.homeId;
    }

    public final long component4() {
        return this.homeCreation;
    }

    @NotNull
    public final String component5() {
        return this.homeTimeZone;
    }

    @NotNull
    public final LicenseEnum component6() {
        return this.license;
    }

    @NotNull
    public final Session copy(@NotNull String username, @NotNull String password, int homeId, long homeCreation, @NotNull String homeTimeZone, @NotNull LicenseEnum license) {
        Intrinsics.checkParameterIsNotNull(username, Constants.KEY_EXTRA_USERNAME);
        Intrinsics.checkParameterIsNotNull(password, Constants.KEY_EXTRA_PASSWORD);
        Intrinsics.checkParameterIsNotNull(homeTimeZone, "homeTimeZone");
        Intrinsics.checkParameterIsNotNull(license, "license");
        return new Session(username, password, homeId, homeCreation, homeTimeZone, license);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (!(obj instanceof Session)) {
                return false;
            }
            Session session = (Session) obj;
            if (!Intrinsics.areEqual(this.username, session.username) || !Intrinsics.areEqual(this.password, session.password)) {
                return false;
            }
            if (!(this.homeId == session.homeId)) {
                return false;
            }
            if (!((this.homeCreation == session.homeCreation) && Intrinsics.areEqual(this.homeTimeZone, session.homeTimeZone) && Intrinsics.areEqual(this.license, session.license))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        String str = this.username;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        str = this.password;
        int hashCode2 = ((((str != null ? str.hashCode() : 0) + hashCode) * 31) + this.homeId) * 31;
        long j = this.homeCreation;
        hashCode = (hashCode2 + ((int) (j ^ (j >>> 32)))) * 31;
        str = this.homeTimeZone;
        hashCode2 = ((str != null ? str.hashCode() : 0) + hashCode) * 31;
        LicenseEnum licenseEnum = this.license;
        if (licenseEnum != null) {
            i = licenseEnum.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "Session(username=" + this.username + ", password=" + this.password + ", homeId=" + this.homeId + ", homeCreation=" + this.homeCreation + ", homeTimeZone=" + this.homeTimeZone + ", license=" + this.license + ")";
    }

    public Session(@NotNull String username, @NotNull String password, int homeId, long homeCreation, @NotNull String homeTimeZone, @NotNull LicenseEnum license) {
        Intrinsics.checkParameterIsNotNull(username, Constants.KEY_EXTRA_USERNAME);
        Intrinsics.checkParameterIsNotNull(password, Constants.KEY_EXTRA_PASSWORD);
        Intrinsics.checkParameterIsNotNull(homeTimeZone, "homeTimeZone");
        Intrinsics.checkParameterIsNotNull(license, "license");
        this.username = username;
        this.password = password;
        this.homeId = homeId;
        this.homeCreation = homeCreation;
        this.homeTimeZone = homeTimeZone;
        this.license = license;
    }

    public final long getHomeCreation() {
        return this.homeCreation;
    }

    public final int getHomeId() {
        return this.homeId;
    }

    @NotNull
    public final String getHomeTimeZone() {
        return this.homeTimeZone;
    }

    @NotNull
    public final LicenseEnum getLicense() {
        return this.license;
    }

    @NotNull
    public final String getPassword() {
        return this.password;
    }

    @NotNull
    public final String getUsername() {
        return this.username;
    }

    public Session(@NotNull String username, @NotNull String password, int homeId, long homeCreation, @NotNull String homeTimeZone) {
        Intrinsics.checkParameterIsNotNull(username, Constants.KEY_EXTRA_USERNAME);
        Intrinsics.checkParameterIsNotNull(password, Constants.KEY_EXTRA_PASSWORD);
        Intrinsics.checkParameterIsNotNull(homeTimeZone, "homeTimeZone");
        this(username, password, homeId, homeCreation, homeTimeZone, LicenseEnum.STANDARD);
    }
}
