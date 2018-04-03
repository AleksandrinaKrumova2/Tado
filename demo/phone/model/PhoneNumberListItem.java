package com.tado.android.demo.phone.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8VX\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/tado/android/demo/phone/model/PhoneNumberListItem;", "Lcom/tado/android/demo/phone/model/AbstractPhoneNumberListItem;", "phoneNumber", "", "flag", "", "(Ljava/lang/String;I)V", "getFlag", "()I", "getPhoneNumber", "()Ljava/lang/String;", "type", "Lcom/tado/android/demo/phone/model/PhoneNumberItemType;", "getType", "()Lcom/tado/android/demo/phone/model/PhoneNumberItemType;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: PhoneNumberListItem.kt */
public final class PhoneNumberListItem extends AbstractPhoneNumberListItem {
    private final int flag;
    @NotNull
    private final String phoneNumber;

    public PhoneNumberListItem(@NotNull String phoneNumber, int flag) {
        Intrinsics.checkParameterIsNotNull(phoneNumber, "phoneNumber");
        this.phoneNumber = phoneNumber;
        this.flag = flag;
    }

    public final int getFlag() {
        return this.flag;
    }

    @NotNull
    public final String getPhoneNumber() {
        return this.phoneNumber;
    }

    @NotNull
    public PhoneNumberItemType getType() {
        return PhoneNumberItemType.ITEM;
    }
}
