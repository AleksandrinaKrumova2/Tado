package com.tado.android.adapters.demo;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.tado.C0676R;
import com.tado.android.utils.ResourceFactory;
import java.util.List;

public class DemoBottomSheetRecyclerViewAdapter extends Adapter<ViewHolder> {
    private List<DemoBottomSheetItem> items;
    private OnClickListener listener;

    public DemoBottomSheetRecyclerViewAdapter(List<DemoBottomSheetItem> items, OnClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(C0676R.layout.demo_recycler_view_item, parent, false);
        itemView.setOnClickListener(this.listener);
        return new DemoBottomSheetRecyclerViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        int i = C0676R.color.ac_red;
        DemoBottomSheetItem item = (DemoBottomSheetItem) this.items.get(position);
        DemoBottomSheetRecyclerViewHolder viewHolder = (DemoBottomSheetRecyclerViewHolder) holder;
        viewHolder.demoItemName.setTextColor(ContextCompat.getColor(viewHolder.demoItemName.getContext(), isLastPosition(this.items, position) ? C0676R.color.ac_red : C0676R.color.action_item_text_color_enabled));
        Context context = viewHolder.demoItemName.getContext();
        int iconRes = item.getIconRes();
        if (!isLastPosition(this.items, position)) {
            i = C0676R.color.zone_settings_icon_color;
        }
        viewHolder.demoItemName.setCompoundDrawablesWithIntrinsicBounds(ResourceFactory.getTintedVectorSupportDrawable(context, iconRes, i), null, null, null);
        viewHolder.demoItemName.setText(item.getName());
    }

    public int getItemCount() {
        return this.items.size();
    }

    public DemoBottomSheetItem getItem(int position) {
        if (position >= 0 && position < this.items.size()) {
            return (DemoBottomSheetItem) this.items.get(position);
        }
        throw new IllegalArgumentException(String.format("position %d is not within the range of the adapter with size %d", new Object[]{Integer.valueOf(position), Integer.valueOf(this.items.size())}));
    }

    private boolean isLastPosition(List list, int position) {
        return list != null && list.size() - 1 == position;
    }
}
