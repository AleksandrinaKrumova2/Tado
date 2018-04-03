package com.tado.android.demo.phone.view;

import com.tado.android.demo.phone.model.PhoneNumberListItem;
import com.tado.android.demo.phone.presenter.PhoneListPresenter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "p1", "Lcom/tado/android/demo/phone/model/PhoneNumberListItem;", "Lkotlin/ParameterName;", "name", "item", "invoke"}, k = 3, mv = {1, 1, 9})
/* compiled from: PhoneListFragment.kt */
final class PhoneListFragment$preparePhoneList$1 extends FunctionReference implements Function1<PhoneNumberListItem, Unit> {
    PhoneListFragment$preparePhoneList$1(PhoneListPresenter phoneListPresenter) {
        super(1, phoneListPresenter);
    }

    public final String getName() {
        return "onPhoneNumberListItemClick";
    }

    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(PhoneListPresenter.class);
    }

    public final String getSignature() {
        return "onPhoneNumberListItemClick(Lcom/tado/android/demo/phone/model/PhoneNumberListItem;)V";
    }

    public final void invoke(@NotNull PhoneNumberListItem p1) {
        Intrinsics.checkParameterIsNotNull(p1, "p1");
        ((PhoneListPresenter) this.receiver).onPhoneNumberListItemClick(p1);
    }
}
