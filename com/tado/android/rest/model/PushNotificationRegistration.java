package com.tado.android.rest.model;

import com.google.firebase.messaging.FirebaseMessaging;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0005\u001a\u00020\u0003XD¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/tado/android/rest/model/PushNotificationRegistration;", "", "token", "", "(Ljava/lang/String;)V", "provider", "getToken", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: PushNotificationRegistration.kt */
public final class PushNotificationRegistration {
    private final String provider = FirebaseMessaging.INSTANCE_ID_SCOPE;
    @NotNull
    private final String token;

    @NotNull
    public static /* bridge */ /* synthetic */ PushNotificationRegistration copy$default(PushNotificationRegistration pushNotificationRegistration, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = pushNotificationRegistration.token;
        }
        return pushNotificationRegistration.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.token;
    }

    @NotNull
    public final PushNotificationRegistration copy(@NotNull String token) {
        Intrinsics.checkParameterIsNotNull(token, "token");
        return new PushNotificationRegistration(token);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r3) {
        /*
        r2 = this;
        if (r2 == r3) goto L_0x0012;
    L_0x0002:
        r0 = r3 instanceof com.tado.android.rest.model.PushNotificationRegistration;
        if (r0 == 0) goto L_0x0014;
    L_0x0006:
        r3 = (com.tado.android.rest.model.PushNotificationRegistration) r3;
        r0 = r2.token;
        r1 = r3.token;
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
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.rest.model.PushNotificationRegistration.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.token;
        return str != null ? str.hashCode() : 0;
    }

    public String toString() {
        return "PushNotificationRegistration(token=" + this.token + ")";
    }

    public PushNotificationRegistration(@NotNull String token) {
        Intrinsics.checkParameterIsNotNull(token, "token");
        this.token = token;
    }

    @NotNull
    public final String getToken() {
        return this.token;
    }
}
