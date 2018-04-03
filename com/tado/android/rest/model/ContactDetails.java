package com.tado.android.rest.model;

import com.tado.android.installation.CreateHomeContactDetailsActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/tado/android/rest/model/ContactDetails;", "", "name", "", "email", "phone", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "setEmail", "(Ljava/lang/String;)V", "getName", "setName", "getPhone", "setPhone", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: ContactDetails.kt */
public final class ContactDetails {
    @NotNull
    private String email;
    @NotNull
    private String name;
    @NotNull
    private String phone;

    @NotNull
    public static /* bridge */ /* synthetic */ ContactDetails copy$default(ContactDetails contactDetails, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = contactDetails.name;
        }
        if ((i & 2) != 0) {
            str2 = contactDetails.email;
        }
        if ((i & 4) != 0) {
            str3 = contactDetails.phone;
        }
        return contactDetails.copy(str, str2, str3);
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final String component2() {
        return this.email;
    }

    @NotNull
    public final String component3() {
        return this.phone;
    }

    @NotNull
    public final ContactDetails copy(@NotNull String name, @NotNull String email, @NotNull String phone) {
        Intrinsics.checkParameterIsNotNull(name, CreateHomeContactDetailsActivity.INTENT_NAME);
        Intrinsics.checkParameterIsNotNull(email, "email");
        Intrinsics.checkParameterIsNotNull(phone, CreateHomeContactDetailsActivity.INTENT_PHONE);
        return new ContactDetails(name, email, phone);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r3) {
        /*
        r2 = this;
        if (r2 == r3) goto L_0x0026;
    L_0x0002:
        r0 = r3 instanceof com.tado.android.rest.model.ContactDetails;
        if (r0 == 0) goto L_0x0028;
    L_0x0006:
        r3 = (com.tado.android.rest.model.ContactDetails) r3;
        r0 = r2.name;
        r1 = r3.name;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x0028;
    L_0x0012:
        r0 = r2.email;
        r1 = r3.email;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x0028;
    L_0x001c:
        r0 = r2.phone;
        r1 = r3.phone;
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
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.rest.model.ContactDetails.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i = 0;
        String str = this.name;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        str = this.email;
        int hashCode2 = ((str != null ? str.hashCode() : 0) + hashCode) * 31;
        String str2 = this.phone;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "ContactDetails(name=" + this.name + ", email=" + this.email + ", phone=" + this.phone + ")";
    }

    public ContactDetails(@NotNull String name, @NotNull String email, @NotNull String phone) {
        Intrinsics.checkParameterIsNotNull(name, CreateHomeContactDetailsActivity.INTENT_NAME);
        Intrinsics.checkParameterIsNotNull(email, "email");
        Intrinsics.checkParameterIsNotNull(phone, CreateHomeContactDetailsActivity.INTENT_PHONE);
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    @NotNull
    public final String getEmail() {
        return this.email;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getPhone() {
        return this.phone;
    }

    public final void setEmail(@NotNull String <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.email = <set-?>;
    }

    public final void setName(@NotNull String <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.name = <set-?>;
    }

    public final void setPhone(@NotNull String <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.phone = <set-?>;
    }
}
