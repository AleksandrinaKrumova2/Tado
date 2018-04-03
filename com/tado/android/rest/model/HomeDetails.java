package com.tado.android.rest.model;

import com.google.android.gms.maps.model.LatLng;
import com.tado.android.entities.Address;
import com.tado.android.installation.CreateHomeContactDetailsActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\tHÆ\u0003J1\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/tado/android/rest/model/HomeDetails;", "", "name", "", "geolocation", "Lcom/google/android/gms/maps/model/LatLng;", "contactDetails", "Lcom/tado/android/rest/model/ContactDetails;", "address", "Lcom/tado/android/entities/Address;", "(Ljava/lang/String;Lcom/google/android/gms/maps/model/LatLng;Lcom/tado/android/rest/model/ContactDetails;Lcom/tado/android/entities/Address;)V", "getAddress", "()Lcom/tado/android/entities/Address;", "getContactDetails", "()Lcom/tado/android/rest/model/ContactDetails;", "getGeolocation", "()Lcom/google/android/gms/maps/model/LatLng;", "getName", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: HomeDetails.kt */
public final class HomeDetails {
    @NotNull
    private final Address address;
    @NotNull
    private final ContactDetails contactDetails;
    @NotNull
    private final LatLng geolocation;
    @NotNull
    private final String name;

    @NotNull
    public static /* bridge */ /* synthetic */ HomeDetails copy$default(HomeDetails homeDetails, String str, LatLng latLng, ContactDetails contactDetails, Address address, int i, Object obj) {
        if ((i & 1) != 0) {
            str = homeDetails.name;
        }
        if ((i & 2) != 0) {
            latLng = homeDetails.geolocation;
        }
        if ((i & 4) != 0) {
            contactDetails = homeDetails.contactDetails;
        }
        if ((i & 8) != 0) {
            address = homeDetails.address;
        }
        return homeDetails.copy(str, latLng, contactDetails, address);
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final LatLng component2() {
        return this.geolocation;
    }

    @NotNull
    public final ContactDetails component3() {
        return this.contactDetails;
    }

    @NotNull
    public final Address component4() {
        return this.address;
    }

    @NotNull
    public final HomeDetails copy(@NotNull String name, @NotNull LatLng geolocation, @NotNull ContactDetails contactDetails, @NotNull Address address) {
        Intrinsics.checkParameterIsNotNull(name, CreateHomeContactDetailsActivity.INTENT_NAME);
        Intrinsics.checkParameterIsNotNull(geolocation, "geolocation");
        Intrinsics.checkParameterIsNotNull(contactDetails, "contactDetails");
        Intrinsics.checkParameterIsNotNull(address, "address");
        return new HomeDetails(name, geolocation, contactDetails, address);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r3) {
        /*
        r2 = this;
        if (r2 == r3) goto L_0x0030;
    L_0x0002:
        r0 = r3 instanceof com.tado.android.rest.model.HomeDetails;
        if (r0 == 0) goto L_0x0032;
    L_0x0006:
        r3 = (com.tado.android.rest.model.HomeDetails) r3;
        r0 = r2.name;
        r1 = r3.name;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x0032;
    L_0x0012:
        r0 = r2.geolocation;
        r1 = r3.geolocation;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x0032;
    L_0x001c:
        r0 = r2.contactDetails;
        r1 = r3.contactDetails;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x0032;
    L_0x0026:
        r0 = r2.address;
        r1 = r3.address;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x0032;
    L_0x0030:
        r0 = 1;
    L_0x0031:
        return r0;
    L_0x0032:
        r0 = 0;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.rest.model.HomeDetails.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i = 0;
        String str = this.name;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        LatLng latLng = this.geolocation;
        hashCode = ((latLng != null ? latLng.hashCode() : 0) + hashCode) * 31;
        ContactDetails contactDetails = this.contactDetails;
        int hashCode2 = ((contactDetails != null ? contactDetails.hashCode() : 0) + hashCode) * 31;
        Address address = this.address;
        if (address != null) {
            i = address.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "HomeDetails(name=" + this.name + ", geolocation=" + this.geolocation + ", contactDetails=" + this.contactDetails + ", address=" + this.address + ")";
    }

    public HomeDetails(@NotNull String name, @NotNull LatLng geolocation, @NotNull ContactDetails contactDetails, @NotNull Address address) {
        Intrinsics.checkParameterIsNotNull(name, CreateHomeContactDetailsActivity.INTENT_NAME);
        Intrinsics.checkParameterIsNotNull(geolocation, "geolocation");
        Intrinsics.checkParameterIsNotNull(contactDetails, "contactDetails");
        Intrinsics.checkParameterIsNotNull(address, "address");
        this.name = name;
        this.geolocation = geolocation;
        this.contactDetails = contactDetails;
        this.address = address;
    }

    @NotNull
    public final Address getAddress() {
        return this.address;
    }

    @NotNull
    public final ContactDetails getContactDetails() {
        return this.contactDetails;
    }

    @NotNull
    public final LatLng getGeolocation() {
        return this.geolocation;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }
}
