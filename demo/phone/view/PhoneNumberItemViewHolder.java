package com.tado.android.demo.phone.view;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.demo.phone.model.PhoneNumberListItem;
import com.tado.android.installation.CreateHomeContactDetailsActivity;
import com.tado.android.utils.ResourceFactory;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0006R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/tado/android/demo/phone/view/PhoneNumberItemViewHolder;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "callback", "Lkotlin/Function1;", "Lcom/tado/android/demo/phone/model/PhoneNumberListItem;", "", "(Landroid/view/View;Lkotlin/jvm/functions/Function1;)V", "bind", "item", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: PhoneListRecyclerViewAdapter.kt */
public final class PhoneNumberItemViewHolder extends ViewHolder {
    private final Function1<PhoneNumberListItem, Unit> callback;

    public PhoneNumberItemViewHolder(@Nullable View itemView, @NotNull Function1<? super PhoneNumberListItem, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        super(itemView);
        this.callback = callback;
    }

    public final void bind(@NotNull PhoneNumberListItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        TextView phone = (TextView) this.itemView.findViewById(C0676R.id.phone_list_item);
        Intrinsics.checkExpressionValueIsNotNull(phone, CreateHomeContactDetailsActivity.INTENT_PHONE);
        phone.setText(item.getPhoneNumber());
        View view = this.itemView;
        Intrinsics.checkExpressionValueIsNotNull(view, "itemView");
        phone.setCompoundDrawablesWithIntrinsicBounds(ResourceFactory.getVectorSupportDrawable(view.getContext(), item.getFlag()), null, null, null);
        phone.setOnClickListener(new PhoneNumberItemViewHolder$bind$1(this, item));
    }
}
