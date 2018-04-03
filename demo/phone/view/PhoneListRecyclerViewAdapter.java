package com.tado.android.demo.phone.view;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tado.C0676R;
import com.tado.android.demo.phone.model.AbstractPhoneNumberListItem;
import com.tado.android.demo.phone.model.PhoneNumberItemType;
import com.tado.android.demo.phone.model.PhoneNumberListHeaderItem;
import com.tado.android.demo.phone.model.PhoneNumberListItem;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0016J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u001a\u0010\u0012\u001a\u00020\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000e\u001a\u00020\fH\u0016J\u001a\u0010\u0014\u001a\u00020\u00022\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\fH\u0016J\u001a\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/tado/android/demo/phone/view/PhoneListRecyclerViewAdapter;", "Landroid/support/v7/widget/RecyclerView$Adapter;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "items", "", "Lcom/tado/android/demo/phone/model/AbstractPhoneNumberListItem;", "callback", "Lkotlin/Function1;", "Lcom/tado/android/demo/phone/model/PhoneNumberListItem;", "", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;)V", "getItemCount", "", "getItemViewType", "position", "layout", "type", "Lcom/tado/android/demo/phone/model/PhoneNumberItemType;", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "viewHolder", "itemView", "Landroid/view/View;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: PhoneListRecyclerViewAdapter.kt */
public final class PhoneListRecyclerViewAdapter extends Adapter<ViewHolder> {
    private final Function1<PhoneNumberListItem, Unit> callback;
    private final List<AbstractPhoneNumberListItem> items;

    public PhoneListRecyclerViewAdapter(@NotNull List<? extends AbstractPhoneNumberListItem> items, @NotNull Function1<? super PhoneNumberListItem, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(items, "items");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.items = items;
        this.callback = callback;
    }

    public void onBindViewHolder(@Nullable ViewHolder holder, int position) {
        AbstractPhoneNumberListItem item = (AbstractPhoneNumberListItem) this.items.get(position);
        switch (item.getType()) {
            case HEADER:
                if (holder == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.tado.android.demo.phone.view.PhoneNumberHeaderViewHolder");
                }
                PhoneNumberHeaderViewHolder phoneNumberHeaderViewHolder = (PhoneNumberHeaderViewHolder) holder;
                if (item == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.tado.android.demo.phone.model.PhoneNumberListHeaderItem");
                }
                phoneNumberHeaderViewHolder.bind((PhoneNumberListHeaderItem) item);
                return;
            case ITEM:
                if (holder == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.tado.android.demo.phone.view.PhoneNumberItemViewHolder");
                }
                PhoneNumberItemViewHolder phoneNumberItemViewHolder = (PhoneNumberItemViewHolder) holder;
                if (item == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.tado.android.demo.phone.model.PhoneNumberListItem");
                }
                phoneNumberItemViewHolder.bind((PhoneNumberListItem) item);
                return;
            default:
                return;
        }
    }

    public int getItemCount() {
        return this.items.size();
    }

    @NotNull
    public ViewHolder onCreateViewHolder(@Nullable ViewGroup parent, int viewType) {
        return viewHolder(PhoneNumberItemType.values()[viewType], LayoutInflater.from(parent != null ? parent.getContext() : null).inflate(layout(PhoneNumberItemType.values()[viewType]), parent, false));
    }

    public int getItemViewType(int position) {
        return ((AbstractPhoneNumberListItem) this.items.get(position)).getType().ordinal();
    }

    private final int layout(PhoneNumberItemType type) {
        switch (type) {
            case HEADER:
                return C0676R.layout.phone_list_header_layout;
            case ITEM:
                return C0676R.layout.phone_list_item_layout;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final ViewHolder viewHolder(PhoneNumberItemType type, View itemView) {
        switch (type) {
            case HEADER:
                return new PhoneNumberHeaderViewHolder(itemView);
            case ITEM:
                return new PhoneNumberItemViewHolder(itemView, this.callback);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
