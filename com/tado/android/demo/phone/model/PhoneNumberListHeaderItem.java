package com.tado.android.demo.phone.model;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8VX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/tado/android/demo/phone/model/PhoneNumberListHeaderItem;", "Lcom/tado/android/demo/phone/model/AbstractPhoneNumberListItem;", "header", "", "(I)V", "getHeader", "()I", "type", "Lcom/tado/android/demo/phone/model/PhoneNumberItemType;", "getType", "()Lcom/tado/android/demo/phone/model/PhoneNumberItemType;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: PhoneNumberListHeaderItem.kt */
public final class PhoneNumberListHeaderItem extends AbstractPhoneNumberListItem {
    private final int header;

    public PhoneNumberListHeaderItem(int header) {
        this.header = header;
    }

    public final int getHeader() {
        return this.header;
    }

    @NotNull
    public PhoneNumberItemType getType() {
        return PhoneNumberItemType.HEADER;
    }
}
