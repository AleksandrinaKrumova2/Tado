package com.tado.android.demo.phone.view;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.demo.phone.model.PhoneNumberListHeaderItem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/tado/android/demo/phone/view/PhoneNumberHeaderViewHolder;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "bind", "", "item", "Lcom/tado/android/demo/phone/model/PhoneNumberListHeaderItem;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: PhoneListRecyclerViewAdapter.kt */
public final class PhoneNumberHeaderViewHolder extends ViewHolder {
    public PhoneNumberHeaderViewHolder(@Nullable View itemView) {
        super(itemView);
    }

    public final void bind(@NotNull PhoneNumberListHeaderItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        TextView header = (TextView) this.itemView.findViewById(C0676R.id.phone_list_header);
        Intrinsics.checkExpressionValueIsNotNull(header, "header");
        View view = this.itemView;
        Intrinsics.checkExpressionValueIsNotNull(view, "itemView");
        header.setText(view.getContext().getString(item.getHeader()));
    }
}
