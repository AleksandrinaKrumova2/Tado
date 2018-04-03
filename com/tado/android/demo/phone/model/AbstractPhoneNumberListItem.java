package com.tado.android.demo.phone.model;

import android.content.Context;
import com.tado.android.app.TadoApplication;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u0004\u0018\u00010\bR\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/tado/android/demo/phone/model/AbstractPhoneNumberListItem;", "", "()V", "type", "Lcom/tado/android/demo/phone/model/PhoneNumberItemType;", "getType", "()Lcom/tado/android/demo/phone/model/PhoneNumberItemType;", "context", "Landroid/content/Context;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: AbstractPhoneNumberListItem.kt */
public abstract class AbstractPhoneNumberListItem {
    @NotNull
    public abstract PhoneNumberItemType getType();

    @Nullable
    public final Context context() {
        return TadoApplication.getTadoAppContext();
    }
}
